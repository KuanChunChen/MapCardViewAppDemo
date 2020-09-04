package k.c.module.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.UnknownFormatConversionException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class RetrofitNoKeyResultObserver<T> implements Observer<T> {

    private static final String TAG = "RetrofitResult";

    public RetrofitNoKeyResultObserver() {}

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        RetrofitResultException resultException;

        //non-200 error
        if(e instanceof HttpException){
            HttpException httpError =(HttpException) e;
            resultException = new RetrofitResultException(httpError.code(),"non-200 HTTP ERROR",httpError);
            Log.e(TAG,"Retrofit Http Error");
            onFailure(resultException);
        }
        //network error
        else if(e instanceof IOException){
            resultException = new RetrofitResultException(0,"NETWORK ERROR",e);
            Log.e(TAG,"Retrofit Network Error");
            onFailure(resultException);
        }

        //json parse error
        else if (e instanceof UnknownFormatConversionException || e instanceof JsonIOException || e instanceof JsonParseException){
            resultException = new RetrofitResultException(RetrofitResultException.PARSE_ERROR,"PARSE ERROR",e);
            Log.e(TAG, "Retrofit Parse Error");
            onFailure(resultException);
        }
        //unknown error
        else {
            returnUnKnow(e);
        }
    }

    private void returnUnKnow(Throwable e) {
        Log.d(TAG, e.toString());
        RetrofitResultException resultException = new RetrofitResultException(RetrofitResultException.UNKNOWN,"UNKNOWN ERROR",e);
//        ToastUtils.showShort("UnKnow error");
        onFailure(resultException);
    }

    @Override
    public void onNext(T t) {

        if(t == null) {
            onFailure(new RetrofitResultException(RetrofitResultException.PARSE_ERROR, "Return null result from server"));
            return;
        }

        NoKeyResult noKeyResult = new Gson().fromJson(new Gson().toJson(t), NoKeyResult.class);

        if (noKeyResult == null) {
            onFailure(new RetrofitResultException(RetrofitResultException.PARSE_ERROR, "Return error result from server"));
            return;
        }

        if(Constants.Http.SUCCESS == noKeyResult.result.code) {

            try {
                onSuccess(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            onResultCode(noKeyResult.result.code, noKeyResult);
        }

    }

    /**
     * call this when getting non SUCCESS code
     */
    protected void onResultCode(int resultCode, NoKeyResult result){
        Log.d(TAG,"server result [" + resultCode + "]");
    }

    public void onFailure(RetrofitResultException e){
        e.printStackTrace();
    }

    public abstract void onSuccess(T  data);


    public static class RetrofitResultException extends RuntimeException {
        public int code;
        public String msg;

        public static final int UNKNOWN = 1000;
        public static final int PARSE_ERROR = 1001;

        public RetrofitResultException(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public RetrofitResultException(int code, String msg, Throwable e){
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "http code = "+code+"\n cause = "+msg +"\n"+super.toString();
        }
    }
}

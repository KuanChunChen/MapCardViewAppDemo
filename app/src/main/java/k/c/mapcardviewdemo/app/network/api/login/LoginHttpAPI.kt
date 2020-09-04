package k.c.mapcardviewdemo.app.network.api.login

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import k.c.mapcardviewdemo.app.network.api.ApiPath
import k.c.mapcardviewdemo.app.network.model.login.AppLoginRequest
import k.c.mapcardviewdemo.app.network.model.login.AppLoginResult
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenRequest
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenResult
import k.c.module.http.RetrofitClient.http
import k.c.module.http.RetrofitNoKeyResultObserver

object LoginHttpApi {


    fun refreshToken(refreshTokenRequest: RefreshTokenRequest, result: RetrofitNoKeyResultObserver<RefreshTokenResult>) {

        http(LoginAPI::class.java).getRefreshToken(ApiPath.DIRECT_DISPATCH_AUTH,refreshTokenRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }


    fun userLogin(appLoginRequest: AppLoginRequest, result: RetrofitNoKeyResultObserver<AppLoginResult>) {

        http(LoginAPI::class.java).login(ApiPath.DIRECT_DISPATCH_AUTH, appLoginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }


}

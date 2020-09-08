package k.c.mapcardviewdemo.app.network.api.gps

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import k.c.mapcardviewdemo.app.network.api.ApiPath
import k.c.mapcardviewdemo.app.network.model.gps.*
import k.c.module.http.RetrofitClient.http
import k.c.module.http.RetrofitNoKeyResultObserver
import k.c.module.http.RetrofitResultObserver

class GpsHttpAPI {


    fun refreshGPS(fetchGPSRequest:FetchGPSRequest, result: RetrofitResultObserver<FetchGPSResult>) {

        http(GpsAPI::class.java)
            .refreshGPS(ApiPath.DISPATCH_ORDER, fetchGPSRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }



    fun queryMapMarker(queryMarkerRequest:QueryMarkerRequest, result: RetrofitNoKeyResultObserver<QueryMarkerResult>) {

        http(GpsAPI::class.java)
            .queryMapMarker(ApiPath.DIRECTORY_GIS, queryMarkerRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }

    fun fetchServiceType(fetchServiceTypeRequest:FetchServiceTypeRequest, result: RetrofitResultObserver<FetchServiceTypeResult>) {

        http(GpsAPI::class.java)
            .fetchServiceType(ApiPath.DISPATCH_ORDER, fetchServiceTypeRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }

}



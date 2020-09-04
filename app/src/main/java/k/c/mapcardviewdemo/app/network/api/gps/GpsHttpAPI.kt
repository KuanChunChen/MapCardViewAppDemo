package k.c.mapcardviewdemo.app.network.api.gps

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import k.c.mapcardviewdemo.app.network.api.ApiPath
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseRequest
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseResult
import k.c.module.http.RetrofitClient
import k.c.module.http.RetrofitResultObserver

class GpsHttpAPI {


    fun refreshGPS(gisGeocodingReverseRequest:GISGeocodingReverseRequest, result: RetrofitResultObserver<GISGeocodingReverseResult>) {

        RetrofitClient.http(GpsAPI::class.java)
            .refreshGPS(ApiPath.DISPATCH_ORDER, gisGeocodingReverseRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result)
    }

}



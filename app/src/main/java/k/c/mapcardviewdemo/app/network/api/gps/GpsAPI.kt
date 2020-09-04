package k.c.mapcardviewdemo.app.network.api.gps

import io.reactivex.Observable
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseRequest
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseResult
import k.c.module.http.Result
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface GpsAPI {


    @POST("{dir}/GISGeocodingReverse")
    fun refreshGPS(@Path("dir") id: String , @Body gISGeocodingReverseRequest: GISGeocodingReverseRequest): Observable<Result<GISGeocodingReverseResult>>





}

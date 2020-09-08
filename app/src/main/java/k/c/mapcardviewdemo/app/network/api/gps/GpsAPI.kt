package k.c.mapcardviewdemo.app.network.api.gps

import io.reactivex.Observable
import k.c.mapcardviewdemo.app.network.model.gps.*
import k.c.module.http.Result
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface GpsAPI {


    @POST("{dir}/GISGeocodingReverse")
    fun refreshGPS(@Path("dir") route: String , @Body fetchGPSRequest: FetchGPSRequest): Observable<Result<FetchGPSResult>>


    @POST("{dir}/MapMarkQuery")
    fun queryMapMarker(@Path("dir") route: String , @Body queryMarkerRequest: QueryMarkerRequest): Observable<QueryMarkerResult>

    @POST("{dir}/SrvTypeList")
    fun fetchServiceType(@Path("dir") route: String , @Body fetchServiceTypeRequest: FetchServiceTypeRequest): Observable<Result<FetchServiceTypeResult>>


}

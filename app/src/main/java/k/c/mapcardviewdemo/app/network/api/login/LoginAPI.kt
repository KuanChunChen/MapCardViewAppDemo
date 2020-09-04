package k.c.mapcardviewdemo.app.network.api.login

import io.reactivex.Observable
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseResult
import k.c.mapcardviewdemo.app.network.model.login.AppLoginRequest
import k.c.mapcardviewdemo.app.network.model.login.AppLoginResult
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenRequest
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenResult
import k.c.module.http.NoKeyResult
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface LoginAPI {


    @POST("{dir}/GetTokenByLogin")
    fun getRefreshToken(@Path("dir") id: String, @Body refreshTokenRequest: RefreshTokenRequest): Observable<RefreshTokenResult>



    @POST("{dir}/AppLogin")
    fun login(@Path("dir") dir: String, @Body appLoginRequest: AppLoginRequest): Observable<AppLoginResult>

}

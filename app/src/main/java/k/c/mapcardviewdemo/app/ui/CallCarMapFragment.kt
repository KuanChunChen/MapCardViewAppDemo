package k.c.mapcardviewdemo.app.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.CommonBusiness.CommonSingle
import k.c.mapcardviewdemo.app.base.BaseFragment
import k.c.mapcardviewdemo.app.base.dialog.ShowDialogManager
import k.c.mapcardviewdemo.app.constants.GISConstant
import k.c.mapcardviewdemo.app.constants.MapConstants
import k.c.mapcardviewdemo.app.manager.*
import k.c.mapcardviewdemo.app.model.*
import k.c.mapcardviewdemo.app.model.enum.MarkerType
import k.c.mapcardviewdemo.app.network.api.gps.GpsHttpAPI
import k.c.mapcardviewdemo.app.network.api.login.LoginHttpApi
import k.c.mapcardviewdemo.app.network.constants.TokenConstants
import k.c.mapcardviewdemo.app.network.model.gps.*
import k.c.mapcardviewdemo.app.network.model.login.AppLoginRequest
import k.c.mapcardviewdemo.app.network.model.login.AppLoginResult
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenRequest
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenResult
import k.c.mapcardviewdemo.app.ui.widget.CustomMarkerView
import k.c.mapcardviewdemo.app.util.PackageUtil
import k.c.mapcardviewdemo.app.util.SHAUtil
import k.c.mapcardviewdemo.app.util.TokenUtil
import k.c.mapcardviewdemo.app.util.UuidUtil
import k.c.module.http.RetrofitNoKeyResultObserver
import k.c.module.http.RetrofitResultObserver
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_car_address.*
import kotlinx.android.synthetic.main.partial_google_map_view.*


class CallCarMapFragment(override val contentViewLayout: Int) : BaseFragment() {


    lateinit var bottomBehavior: BottomSheetBehavior<View>


    private var isRefreshGPSRunning = false

    var isUserMoveMap = false



    override fun initView(savedInstanceState: Bundle?) {
//        testUserLogin("0900888076", "000000")
        testUserLogin("0900000000", "qqqqqq")






        mapview.onCreate(savedInstanceState)
        mapview.onResume()

        mapview.getMapAsync { googleMap ->
            onMapInit(googleMap)
        }

        image_notification.bringToFront()
        image_notification.setOnClickListener {
            activity?.let { ShowDialogManager.showHintDialog(it, "本功能暫停開放，敬啟期待！") }

        }


        bottomBehavior = BottomSheetBehavior.from(my_bottom_view)
        bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED



        image_location_pin.bringToFront()


        car_address_view.bringToFront()

    }




    companion object {

        fun newInstance(): CallCarMapFragment {
            return CallCarMapFragment(R.layout.fragment_category)
        }
    }





    @SuppressLint("MissingPermission", "ResourceType")
    fun onMapInit(googleMap: GoogleMap?) {


        val bestLocation = MainMapManager.getBestLocation()
        Log.d("test444123212333:", "a : ${bestLocation?.longitude} , llll ${bestLocation?.latitude}")


        googleMap!!.isMyLocationEnabled = LocationManagerHelper.isLocationPermissionGranted()
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isCompassEnabled = false
        googleMap.uiSettings.isMapToolbarEnabled = false

        var initLatLng = LatLng(CommonSingle.mapConfig.lastKnowLatitude, CommonSingle.mapConfig.lastKnowLongitude)
        var zoomLevel = MapConstants.MAP_INIT_ZOOM_LEVEL


        if (bestLocation != null) run {

            initLatLng = LatLng(bestLocation.latitude, bestLocation.longitude)
            zoomLevel = MapConstants.MAP_ZOOM_LEVEL_2

        }else{
            //TODO show hint (permission not open)

            activity?.let {
                PermissionManager.requestPermissions(
                    it,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    1
                )
            }

        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initLatLng, zoomLevel))


        var mapListener :MainMapListener = object : MainMapListener(googleMap) {
            override fun onMapMoveStarted(isUserMoveMap: Boolean) {
                image_edit.alpha = 0.5f

                this@CallCarMapFragment.isUserMoveMap = isUserMoveMap
            }

            override fun onMapMoveEnd() {
                image_edit.alpha = 1f
//                bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED


                if (this@CallCarMapFragment.isUserMoveMap) {
                    testRefreshGps(googleMap)
                }

                val markerTypeList =
                    intArrayOf(MarkerType.COMMON.value, MarkerType.STORE.value, MarkerType.FAVORITE.value)


//                fetchServiceType()
                testFetchMarker(1,markerTypeList)
//                val placeMarker = MarkerOptions()
//                    .position(LatLng( CommonSingle.mapConfig.lastKnowLatitude,  CommonSingle.mapConfig.lastKnowLongitude))
//                    .icon(BitmapDescriptorFactory.fromBitmap(
//                        MarkerManager.getCustomerMarker(activity as Context,R.drawable.ic_map_location_home)
//                    ))
//                    .title("test1234")
////                        .zIndex(zIndex)
//                googleMap.addMarker(placeMarker)
                addMarkers(googleMap,CommonSingle.mapConfig.lastMarkerInfo?.markList)

            }

        }


        /***
         *
         * Map Location button
         */

        val locationButton: ImageView = (mapview.findViewById<View>(1).parent as View).findViewById<View>(2) as ImageView
        locationButton.setImageResource(R.drawable.btn_nav_locate)

        val layoutParams = locationButton.layoutParams as RelativeLayout.LayoutParams

        layoutParams.rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12f, resources.displayMetrics).toInt()
        layoutParams.topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 518f, resources.displayMetrics).toInt()

        locationButton.layoutParams = layoutParams


    }




    private fun testUserLogin(account: String, password: String) {


        var appLoginRequest = AppLoginRequest()

        appLoginRequest.userId = account
        appLoginRequest.custpin = password
        appLoginRequest.serviceToken = TokenConstants.serviceToken
        appLoginRequest.carID = "1"
        appLoginRequest.uuid = UuidUtil.getUUID()
        appLoginRequest.ncpmType = 2

        appLoginRequest.appversion = PackageUtil.getVersionName()
        appLoginRequest.signature =
            SHAUtil.sha1Hash(TokenConstants.serviceToken + account + password)

//        Log.d("test 123", "appLoginRequest :"+ Gson().toJson(appLoginRequest))

        LoginHttpApi.userLogin(appLoginRequest ,object :
            RetrofitNoKeyResultObserver<AppLoginResult>() {
            override fun onSuccess(data: AppLoginResult?) {
//                Log.d("test 12341243",Gson().toJson(data))
//                Log.d("test 12341243","-----------")


                CommonSingle.refreshToken = data?.refreshToken

                CommonSingle.serverAccessToken = Gson().fromJson(Gson().toJson(data), ServerAccessToken::class.java)

                CommonSingle.customerProfile = Gson().fromJson(Gson().toJson(data!!.info), CustomerProfile::class.java)

                CommonSingle.signature = Gson().fromJson(Gson().toJson(data.appSetting?.signature), Signature::class.java)

                CommonSingle.temporaryPin = Gson().fromJson(Gson().toJson(data.tmpPin), TemporaryPin::class.java)

//
//                Log.d("test 12341243", CommonSingle.refreshToken)
//
//                Log.d("test 12341243",Gson().toJson(CommonSingle.serverAccessToken))
//
//                Log.d("test 12341243",Gson().toJson(CommonSingle.customerProfile))
//
//                Log.d("test 12341243",Gson().toJson(CommonSingle.signature))
//
//                Log.d("test 12341243",Gson().toJson(CommonSingle.temporaryPin))


            }

            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("test 12341243",e.toString())

            }
        })
    }


    //TODO  到時候 compFavorite 在確認要不要留 本fun為測試用

    private fun testRefreshGps(googleMap : GoogleMap) {

        var gpsRequest = FetchGPSRequest()

        isRefreshGPSRunning = true

        //TODO 這邊要再改流程 :refresh gps 前需要再get new token
        if (TokenUtil.isTokenExpired()) {
            testRefreshToken()
        }



        gpsRequest.mode = GISConstant.User_Edit
        gpsRequest.longitude = CommonSingle.mapConfig.lastKnowLongitude
        gpsRequest.latitude = CommonSingle.mapConfig.lastKnowLatitude
        gpsRequest.compFavorite = true
        gpsRequest.scenarioType = 1
        gpsRequest.userId = CommonSingle.customerProfile.customerAccount
        gpsRequest.accessToken = CommonSingle.serverAccessToken.accessToken
        gpsRequest.signature = CommonSingle.signature.order

        Log.d("test123456", Gson().toJson(gpsRequest))

        GpsHttpAPI().refreshGPS(gpsRequest, object :

            RetrofitResultObserver<FetchGPSResult>() {
            override fun onSuccess(data: MutableCollection<FetchGPSResult>?) {

                Log.d("test 12341243", Gson().toJson(data))
                if (data != null) {
                    tv_address.text = data.elementAt(0).address!!.substring(6)
                    CommonSingle.mapConfig.lastKnowLongitude = data.elementAt(0).lng!!
                    CommonSingle.mapConfig.lastKnowLatitude = data.elementAt(0).lat!!
//                    googleMap.moveCamera(data.elementAt(0).lng,data.elementAt(0).lat)
//                    val fromAddrLatLng = LatLng(data.elementAt(0).lat!!, data.elementAt(0).lng!!)
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromAddrLatLng, -1f))
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fromAddrLatLng))


                }

                isRefreshGPSRunning = false





            }


            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("onFailure", e.toString())
//                testRefreshToken()
                isRefreshGPSRunning = false
            }

        })

    }

    private fun testRefreshToken() {

        var refreshTokenRequest = RefreshTokenRequest()

        refreshTokenRequest.refreshToken = CommonSingle.refreshToken
        refreshTokenRequest.uDID = CommonSingle.UUID
        refreshTokenRequest.serviceToken = TokenConstants.serviceToken
        refreshTokenRequest.signature = SHAUtil.sha1Hash(refreshTokenRequest.serviceToken + refreshTokenRequest.uDID + refreshTokenRequest.refreshToken)

        LoginHttpApi.refreshToken(refreshTokenRequest ,object :
            RetrofitNoKeyResultObserver<RefreshTokenResult>(){


            override fun onSuccess(data: RefreshTokenResult?) {
                Log.d("test 333333333", Gson().toJson(data))

                CommonSingle.serverAccessToken = Gson().fromJson(Gson().toJson(data), ServerAccessToken::class.java)

                CommonSingle.signature = Gson().fromJson(Gson().toJson(data?.signature), Signature::class.java)


                CommonSingle.temporaryPin = Gson().fromJson(Gson().toJson(data?.tmpPin), TemporaryPin::class.java)


//                Log.d("test 33333333",Gson().toJson(CommonSingle.serverAccessToken))
//
//                Log.d("test 33333333",Gson().toJson(CommonSingle.signature))
//
//                Log.d("test 33333333",Gson().toJson(CommonSingle.temporaryPin))


            }

            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("onFailure", e.toString())

            }

        })


    }

    private fun testFetchMarker(scenarioType: Int, markerTypeList: IntArray) {
//        val markerTypeList = intArrayOf(MarkerType.COMMON.value,MarkerType.STORE.value,MarkerType.FAVORITE.value)

        var queryMarkerRuquest = QueryMarkerRequest()

        queryMarkerRuquest.userId = CommonSingle.customerProfile.customerAccount
        queryMarkerRuquest.northEastLat = CommonSingle.mapConfig.visibleRegionLatLngBounds?.northeast?.latitude
        queryMarkerRuquest.northEastLng = CommonSingle.mapConfig.visibleRegionLatLngBounds?.northeast?.longitude
        queryMarkerRuquest.southWestLat = CommonSingle.mapConfig.visibleRegionLatLngBounds?.southwest?.latitude
        queryMarkerRuquest.southWestLng = CommonSingle.mapConfig.visibleRegionLatLngBounds?.southwest?.longitude
        queryMarkerRuquest.zoomValue = CommonSingle.mapConfig.currentZoomLevel

//                val stringarray = arrayOf("wsqqkp")

        //TODO 缺一個 existingGeoHashList
//        queryMarkerRuquest.existingGeoHash = stringarray
        queryMarkerRuquest.markerType = markerTypeList
        queryMarkerRuquest.scenarioType = scenarioType

        queryMarkerRuquest.accessToken = CommonSingle.serverAccessToken.accessToken

        queryMarkerRuquest.signature = CommonSingle.signature.appApi


        Log.d("testa", Gson().toJson(queryMarkerRuquest))

        GpsHttpAPI().queryMapMarker(queryMarkerRuquest,
            object : RetrofitNoKeyResultObserver<QueryMarkerResult>() {
                override fun onSuccess(data: QueryMarkerResult?) {

                    Log.d("testFetchMarker", Gson().toJson(data))


                    /***MarkerInfo 初次建立的時候才init***/
                    if (CommonSingle.mapConfig.lastMarkerInfo == null) {
                        CommonSingle.mapConfig.lastMarkerInfo = MarkerInfo()
                    }

                    CommonSingle.mapConfig.lastMarkerInfo?.drawGeoHash = data?.drawGeoHash
                    CommonSingle.mapConfig.lastMarkerInfo?.markList = data?.data?.get(0)?.markList
                    CommonSingle.mapConfig.lastMarkerInfo?.geoHash = data?.data?.get(0)?.geoHash
                    Log.d("testFetchMarker Gson", Gson().toJson(CommonSingle.mapConfig.lastMarkerInfo))

                }

            })

    }

    private fun addMarkers(googleMap: GoogleMap,markerList :List<MarkListItem?>?){

        googleMap.clear()

        markerList?.forEach {
            it?.geocode?.lat
            val placeMarker = MarkerOptions()
                .position(
                    LatLng(it?.geocode?.lat!!, it?.geocode?.lng!!)
                )
                .icon(
                    BitmapDescriptorFactory.fromBitmap(
                        MarkerManager.getCustomerMarker(
                            activity as Context,
                            R.drawable.ic_map_location_home,
                            it?.markName!!
                        )
                    )
                )
                .title("test1234")
            googleMap.addMarker(placeMarker)
        }


    }

    private fun fetchServiceType() {


        var fetchServiceTypeRequest = FetchServiceTypeRequest()
        fetchServiceTypeRequest.latitude = CommonSingle.mapConfig.lastKnowLatitude
        fetchServiceTypeRequest.longitude = CommonSingle.mapConfig.lastKnowLongitude

        fetchServiceTypeRequest.accessToken = CommonSingle.serverAccessToken.accessToken
        fetchServiceTypeRequest.userId = CommonSingle.customerProfile.customerAccount

        fetchServiceTypeRequest.signature = CommonSingle.signature.order


        GpsHttpAPI().fetchServiceType(fetchServiceTypeRequest,object :RetrofitResultObserver<FetchServiceTypeResult>(){
            override fun onSuccess(data: MutableCollection<FetchServiceTypeResult>?) {
                Log.i("fetchServiceType :", Gson().toJson(data))

            }
        })
    }




}
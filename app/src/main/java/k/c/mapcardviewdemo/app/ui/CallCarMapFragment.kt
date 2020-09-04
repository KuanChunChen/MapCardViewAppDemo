package k.c.mapcardviewdemo.app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.CommonBusiness.CommonSingle
import k.c.mapcardviewdemo.app.base.BaseFragment
import k.c.mapcardviewdemo.app.base.CommonLib
import k.c.mapcardviewdemo.app.base.dialog.ShowDialogManager
import k.c.mapcardviewdemo.app.constants.GISConstant
import k.c.mapcardviewdemo.app.manager.MainMapListener
import k.c.mapcardviewdemo.app.model.*
import k.c.mapcardviewdemo.app.network.api.gps.GpsHttpAPI
import k.c.mapcardviewdemo.app.network.api.login.LoginHttpApi
import k.c.mapcardviewdemo.app.network.constants.TokenConstants
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseRequest
import k.c.mapcardviewdemo.app.network.model.gps.GISGeocodingReverseResult
import k.c.mapcardviewdemo.app.network.model.login.AppLoginRequest
import k.c.mapcardviewdemo.app.network.model.login.AppLoginResult
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenRequest
import k.c.mapcardviewdemo.app.network.model.login.RefreshTokenResult
import k.c.mapcardviewdemo.app.ui.widget.CenterLayoutManager
import k.c.mapcardviewdemo.app.ui.widget.switchRecycler.SpaceItemDecoration
import k.c.mapcardviewdemo.app.ui.widget.switchRecycler.SwitchRecyclerScrollerListener
import k.c.mapcardviewdemo.app.ui.widget.switchRecycler.SwitchRecyclerViewAdapter
import k.c.mapcardviewdemo.app.util.PackageUtil
import k.c.mapcardviewdemo.app.util.SHAUtil
import k.c.mapcardviewdemo.app.util.TokenUtil
import k.c.mapcardviewdemo.app.util.UuidUtil
import k.c.module.http.RetrofitNoKeyResultObserver
import k.c.module.http.RetrofitResultObserver
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_car_address.*
import kotlinx.android.synthetic.main.partial_bottom_card.*
import kotlinx.android.synthetic.main.partial_google_map_view.*
import java.util.*





class CallCarMapFragment(override val contentViewLayout: Int) : BaseFragment() {


    lateinit var bottomBehavior: BottomSheetBehavior<View>


    private lateinit var centerLayoutManager : CenterLayoutManager


    override fun initView(savedInstanceState: Bundle?) {
        testUserLogin("0900888076", "000000")




        var switchViewList = getList()




        val spaceItemDecoration = SpaceItemDecoration(9)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(switchView)

        val switchRecyclerViewAdapter =
            object : SwitchRecyclerViewAdapter() {
                override fun onItemViewClick(position: Int, itemView: View) {

//                    centerLayoutManager.smoothScrollToPosition(switchView, RecyclerView.State(), position)
//                    centerLayoutManager.scrollToPositionWithOffset(position,spaceItemDecoration.sideVisibleWidth)

//                    switchRecyclerViewAdapter.notifyItemChanged(position, 1)
//                    testLoadView(position,switchViewList)
                }
            }

        switchRecyclerViewAdapter.reset(switchViewList)



        centerLayoutManager = CenterLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL, false)







        switchView.adapter = switchRecyclerViewAdapter
        switchView.layoutManager = centerLayoutManager
        switchView.addItemDecoration(spaceItemDecoration)


        val currentPosition = switchView.adapter!!.itemCount / 2

        val offset = spaceItemDecoration.sideVisibleWidth



        centerLayoutManager.scrollToPositionWithOffset(currentPosition,offset)

        switchView.post{

            val galleryScrollerListener = object : SwitchRecyclerScrollerListener(spaceItemDecoration.mItemConsumeX) {
                override fun changeView(position: Int) {

                    testLoadView(position,switchViewList)

                }

            }


            switchView.addOnScrollListener(galleryScrollerListener)

            galleryScrollerListener.setItemAnim(switchView,currentPosition,0f)

            galleryScrollerListener.updatePosition(currentPosition)




        }




        mapview.onCreate(savedInstanceState)
        mapview.onResume()


        mapview.getMapAsync { googleMap ->
            onMapInit(googleMap)
        }

        btn_confirm.setOnClickListener {
            activity?.let { ShowDialogManager.showToasterHint(it, "您好，我看到您了", "司機 陳建和(TDH-1333)") }

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


    /***
     * 此線以下的數個function皆為測試用
     *
     * */

    private fun getList(): List<SwitchViewModel> {

        val titleList = ArrayList<SwitchViewModel>()
        val titleArray = arrayOf("微搬家", "酒後代駕", "現在叫車", "預約叫車","機場送機","代接電","代叫車")
        val buttonTextArray = arrayOf("輸入下車點", "輸入乘客資訊", "叫車", "輸入下車點","選擇欲前往機場","選擇CC數","輸入乘客資訊")


        for (i in 0..6) {
            val switchViewModel = SwitchViewModel()
            switchViewModel.textTitle = titleArray[i]
            switchViewModel.buttonText = buttonTextArray[i]

            titleList.add(switchViewModel)
        }
        return titleList

    }

    private fun testLoadView(position: Int , switchViewList: List<SwitchViewModel>) {

        btn_confirm.text = switchViewList.get(position).buttonText

        when (position % 7) {
            1 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_booking).into(image_icon)
            2 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_assist).into(image_icon)
            3 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_designated_a).into(image_icon)
            4 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_designated_b).into(image_icon)
            5 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_taxi).into(image_icon)
            6 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_airport).into(image_icon)
            0 -> Glide.with(activity).load<Any>(R.drawable.ic_home_services_plug).into(image_icon)
        }
    }


    @SuppressLint("MissingPermission", "ResourceType")
    fun onMapInit(googleMap: GoogleMap?) {

        googleMap!!.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isCompassEnabled = false

        googleMap.uiSettings.isMapToolbarEnabled = false
        googleMap.uiSettings.isZoomControlsEnabled = false
        googleMap.uiSettings.isTiltGesturesEnabled = false


        var count = 0

        var test12344 :MainMapListener = object : MainMapListener(googleMap) {
            override fun onMapTouched() {
                image_edit.alpha = 0.5f

            }

            override fun onMapMoveEnd() {
                image_edit.alpha = 1f
//                bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                if (count < 2) {
                    testRefreshGps(googleMap)
                    count++
                }

            }

        }
//        googleMap.setOnCameraIdleListener {
//            val handler = Handler()
//            synchronized(this) {
//                handler.postDelayed({
//                    bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//                }, 5000)
//            }
//
//        }
//
//        googleMap.setOnCameraMoveStartedListener {
//
//            bottomBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//        }


        val mLocationManager: LocationManager =
            CommonLib.appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager



        val locationButton: ImageView = (mapview.findViewById<View>(1).parent as View).findViewById<View>(2) as ImageView
        locationButton.setImageResource(R.drawable.btn_nav_locate)

        val layoutParams = locationButton.layoutParams as RelativeLayout.LayoutParams

        layoutParams.rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12f, resources.displayMetrics).toInt()
        layoutParams.topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 518f, resources.displayMetrics).toInt()

        locationButton.layoutParams = layoutParams





        var locationTest: Location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


        var isGPSEnabled = mLocationManager
            .isProviderEnabled(LocationManager.GPS_PROVIDER)

        // getting network status
        var isNetworkEnabled = mLocationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        }else {
        }

        Log.d("testa12321:", "a : ${locationTest.longitude} , llll ${locationTest.latitude} , actuty${locationTest.accuracy}")
        googleMap.isMyLocationEnabled = true

        val Lat = locationTest.latitude
        val Lng = locationTest.longitude
//        val Lat = 121.0
//
//        val Lng = 23.0
        val fromAddrLatLng = LatLng(Lat, Lng)


//        val mMarkerMiPosicion = googleMap.addMarker(
//            MarkerOptions().position(fromAddrLatLng)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_other_f))
//                .anchor(0.5f,0.5f))

//        val markerOptions = MarkerOptions()
//        markerOptions.position(LatLng(Lat, Lng))
//            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_other_f))
//            .zIndex(998F)

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromAddrLatLng, 18f))
//        googleMap.addMarker(markerOptions).hideInfoWindow()

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

        Log.d("test 123", "appLoginRequest :"+ Gson().toJson(appLoginRequest))

        LoginHttpApi.userLogin(appLoginRequest ,object :
            RetrofitNoKeyResultObserver<AppLoginResult>() {
            override fun onSuccess(data: AppLoginResult?) {
                Log.d("test 12341243",Gson().toJson(data))
                Log.d("test 12341243","-----------")


                CommonSingle.refreshToken = data?.refreshToken

                CommonSingle.serverAccessToken = Gson().fromJson(Gson().toJson(data), ServerAccessToken::class.java)

                CommonSingle.customerProfile = Gson().fromJson(Gson().toJson(data!!.info), CustomerProfile::class.java)

                CommonSingle.signature = Gson().fromJson(Gson().toJson(data.appSetting?.signature), Signature::class.java)

                CommonSingle.temporaryPin = Gson().fromJson(Gson().toJson(data.tmpPin), TemporaryPin::class.java)


                Log.d("test 12341243", CommonSingle.refreshToken)

                Log.d("test 12341243",Gson().toJson(CommonSingle.serverAccessToken))

                Log.d("test 12341243",Gson().toJson(CommonSingle.customerProfile))

                Log.d("test 12341243",Gson().toJson(CommonSingle.signature))

                Log.d("test 12341243",Gson().toJson(CommonSingle.temporaryPin))


            }

            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("test 12341243",e.toString())

            }
        })
    }


    //TODO  到時候 compFavorite 在確認要不要留 本fun為測試用

    private fun testRefreshGps(googleMap : GoogleMap) {

        var gpsRequest = GISGeocodingReverseRequest()

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

            RetrofitResultObserver<GISGeocodingReverseResult>() {
            override fun onSuccess(data: MutableCollection<GISGeocodingReverseResult>?) {

                Log.d("test 12341243", Gson().toJson(data))
                if (data != null) {
                    tv_address.text = data.elementAt(0).address!!.substring(6)
                    CommonSingle.mapConfig.lastKnowLongitude = data.elementAt(0).lng
                    CommonSingle.mapConfig.lastKnowLatitude = data.elementAt(0).lat
//                    googleMap.moveCamera(data.elementAt(0).lng,data.elementAt(0).lat)
//                    val fromAddrLatLng = LatLng(data.elementAt(0).lat!!, data.elementAt(0).lng!!)
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromAddrLatLng, 18f))
                    car_address_view.alpha = 1.0f


                }




            }


            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("onFailure", e.toString())
//                testRefreshToken()

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


                Log.d("test 33333333",Gson().toJson(CommonSingle.serverAccessToken))

                Log.d("test 33333333",Gson().toJson(CommonSingle.signature))

                Log.d("test 33333333",Gson().toJson(CommonSingle.temporaryPin))


            }

            override fun onFailure(e: RetrofitResultException?) {
                super.onFailure(e)
                Log.d("onFailure", e.toString())

            }

        })


    }





}
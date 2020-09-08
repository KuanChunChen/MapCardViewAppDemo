package k.c.mapcardviewdemo.app.manager

import android.util.Log
import com.google.android.gms.maps.GoogleMap
import k.c.mapcardviewdemo.app.CommonBusiness.CommonSingle

abstract class MainMapListener(var googleMap :GoogleMap) : GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveStartedListener , GoogleMap.OnCameraMoveCanceledListener ,GoogleMap.OnCameraMoveListener{


    init{
        googleMap.setOnCameraIdleListener(this)
        googleMap.setOnCameraMoveStartedListener(this)
        googleMap.setOnCameraMoveCanceledListener(this)
        googleMap.setOnCameraMoveListener (this)

    }

    override fun onCameraMoveStarted(reason: Int) {
        Log.d("test123456","onCameraMoveStarted")

        var isUserMoveMap = false

        when (reason) {
            GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE ,
            GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION-> {
                Log.d("tst1231", "The user gestured on the map.")

                isUserMoveMap = true
            }

            GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION -> {
                Log.d("tst1231", "he app moved the camera")
                isUserMoveMap = false
            }
        }


        onMapMoveStarted(isUserMoveMap)



    }

    override fun onCameraMoveCanceled() {
        Log.d("test123456","onCameraMoveCanceled")

    }

    override fun onCameraMove() {
        Log.d("test123456","onCameraMove")
        CommonSingle.mapConfig.lastKnowLatitude = googleMap.cameraPosition.target.latitude
        CommonSingle.mapConfig.lastKnowLongitude = googleMap.cameraPosition.target.longitude
        CommonSingle.mapConfig.currentZoomLevel = googleMap.cameraPosition.zoom
        CommonSingle.mapConfig.visibleRegionLatLngBounds = googleMap.projection.visibleRegion.latLngBounds
//        Log.d("test123456",CommonSingle.mapConfig.lastKnowLatitude.toString())
//        Log.d("test123456",CommonSingle.mapConfig.lastKnowLongitude.toString())
        Log.d("test123456","onCameraMoveEnd-------")


    }



    override fun onCameraIdle() {
//        onMapUnsettled()
        CommonSingle.mapConfig.lastKnowLatitude = googleMap.cameraPosition.target.latitude
        CommonSingle.mapConfig.lastKnowLongitude = googleMap.cameraPosition.target.longitude
        CommonSingle.mapConfig.currentZoomLevel = googleMap.cameraPosition.zoom
        CommonSingle.mapConfig.visibleRegionLatLngBounds = googleMap.projection.visibleRegion.latLngBounds

        onMapMoveEnd()
        Log.d("test123456","onCameraIdle")
    }


    abstract fun onMapMoveStarted(isUserMoveMap :Boolean)

    abstract fun onMapMoveEnd()


}
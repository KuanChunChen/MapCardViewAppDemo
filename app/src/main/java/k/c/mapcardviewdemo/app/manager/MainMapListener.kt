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

//        CommonSingle.mapConfig.lastKnowLatitude = googleMap.cameraPosition.target.latitude
//        CommonSingle.mapConfig.lastKnowLongitude = googleMap.cameraPosition.target.longitude
    }








    override fun onCameraMoveStarted(p0: Int) {
        Log.d("test123456","onCameraMoveStarted")


    }

    override fun onCameraMoveCanceled() {
        Log.d("test123456","onCameraMoveCanceled")
        onMapTouched()

    }

    override fun onCameraMove() {
        Log.d("test123456","onCameraMove")
        CommonSingle.mapConfig.lastKnowLatitude = googleMap.cameraPosition.target.latitude
        CommonSingle.mapConfig.lastKnowLongitude = googleMap.cameraPosition.target.longitude

//        Log.d("test123456",CommonSingle.mapConfig.lastKnowLatitude.toString())
//        Log.d("test123456",CommonSingle.mapConfig.lastKnowLongitude.toString())
        Log.d("test123456","onCameraMoveEnd-------")


    }



    override fun onCameraIdle() {
//        onMapUnsettled()
        onMapMoveEnd()
        Log.d("test123456","onCameraIdle")
    }


    abstract fun onMapTouched()
//
//    abstract fun onMapReleased()

//    abstract fun onMapUnsettled()
    abstract fun onMapMoveEnd()


//    abstract fun onMapSettled()

}
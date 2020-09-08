package k.c.mapcardviewdemo.app.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/***
 *
 * @param lastKnowLatitude 最新的latitude
 * @param lastKnowLongitude 最新的longitude
 * @param currentZoomLevel 當前地圖的zoom in/out 倍率
 * @param visibleRegionLatLngBounds 當前地圖的可見區域 (詳見google map官網參考此參數的定義)
 * @param lastMarkerInfo 從server取得最新的圖標/地標資訊
 */
data class MapConfig(

    val MAP_INIT_LAT_LNG: LatLng = LatLng(23.69781, 120.960515),
    val DEFAULT_LAT_LNG: LatLng = LatLng(25.0463087, 121.517878),

    var lastKnowLatitude: Double = MAP_INIT_LAT_LNG.latitude,
    var lastKnowLongitude: Double = MAP_INIT_LAT_LNG.longitude,
    var currentZoomLevel: Float = 6f,
    var visibleRegionLatLngBounds: LatLngBounds? = null,

    var lastMarkerInfo: MarkerInfo? = null



)
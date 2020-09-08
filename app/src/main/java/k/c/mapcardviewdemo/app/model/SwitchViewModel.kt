package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName


class SwitchViewModel {

    @SerializedName("title")
    var textTitle: String? = null

    @SerializedName("button_text")
    var buttonText: String? = null

    @SerializedName("image_icon")
    var imageIcon: Int? = null
}

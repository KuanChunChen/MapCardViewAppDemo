package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName


data class Signature(

    @field:SerializedName("Order")
    val order: String? = null,

    @field:SerializedName("GetIn")
    val getIn: String? = null,

    @field:SerializedName("Query")
    val query: String? = null,

    @field:SerializedName("Cancel")
    val cancel: String? = null,

    @field:SerializedName("VoIP")
    val voIP: String? = null,

    @field:SerializedName("MsgAPP")
    val msgAPP: String? = null,

    @field:SerializedName("Quotation")
    val quotation: String? = null,

    @field:SerializedName("AppApi")
    val appApi: String? = null,

    @field:SerializedName("DriInfo")
    val driInfo: String? = null,

    @field:SerializedName("Paymnt")
    val paymnt: String? = null,

    @field:SerializedName("None")
    val none: String? = null,

    @field:SerializedName("EFare")
    val eFare: String? = null,

    @field:SerializedName("MsgIVE")
    val msgIVE: String? = null,

    @field:SerializedName("Settings")
    val settings: String? = null
)
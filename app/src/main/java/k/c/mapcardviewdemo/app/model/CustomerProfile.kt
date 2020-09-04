package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.login.DiscountOptsItem


data class CustomerProfile(

    @field:SerializedName("CustAcct")
    val customerAccount: String? = null,

    @field:SerializedName("Salary")
    val salary: String? = null,

    @field:SerializedName("HappyGoInfo")
    val happyGoInfo: List<Any?>? = null,

    @field:SerializedName("VIPSDate")
    val user_vip_date: String? = null,

    @field:SerializedName("SEX")
    val user_sex: String? = null,

    @field:SerializedName("VIPEDate")
    val vIPEDate: String? = null,

    @field:SerializedName("ShowBackPhone")
    val showBackPhone: Boolean? = null,

    @field:SerializedName("EMAIL")
    val eMAIL: String? = null,

    @field:SerializedName("ReferCode")
    val referCode: String? = null,

    @field:SerializedName("Education")
    val education: String? = null,

    @field:SerializedName("WishInfo")
    val wishInfo: String? = null,

    @field:SerializedName("Job")
    val job: String? = null,

    @field:SerializedName("IsCreditBanned")
    val isCreditBanned: Boolean? = null,

    @field:SerializedName("Marriage")
    val marriage: String? = null,

    @field:SerializedName("BIRTHDAY")
    val bIRTHDAY: String? = null,

    @field:SerializedName("CID")
    val cID: Int? = null,

    @field:SerializedName("ADYN")
    val aDYN: String? = null,

    @field:SerializedName("NotArrivalNotice")
    val notArrivalNotice: Boolean? = null,

    @field:SerializedName("DiscountOpts")
    val discountOpts: List<DiscountOptsItem?>? = null,

    @field:SerializedName("CtbcBpInfo")
    val ctbcBpInfo: List<Any?>? = null,

    @field:SerializedName("UUPONInfo")
    val uUPONInfo: List<Any?>? = null,

    @field:SerializedName("CustName")
    val custName: String? = null,

    @field:SerializedName("JobType")
    val jobType: String? = null,

    @field:SerializedName("BannedStatus")
    val bannedStatus: Int? = null,

    @field:SerializedName("VIPLv")
    val vIPLv: Int? = null
)
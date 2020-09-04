package k.c.mapcardviewdemo.app.network.model.login

import com.google.gson.annotations.SerializedName

data class AppLoginResult(

    @field:SerializedName("KeyToken")
    val keyToken: String? = null,

    @field:SerializedName("VoIPUUID")
    val voIPUUID: String? = null,

    @field:SerializedName("RefreshToken")
    val refreshToken: String? = null,

    @field:SerializedName("ExpiredUTCT")
    val expiredUTCT: Long? = null,

    @field:SerializedName("TmpPin")
    val tmpPin: TmpPin? = null,

    @field:SerializedName("AccessToken")
    val accessToken: String? = null,

    @field:SerializedName("Expired")
    val expired: String? = null,

    @field:SerializedName("Info")
    val info: Info? = null,

    @field:SerializedName("AppSetting")
    val appSetting: AppSetting? = null,

    @field:SerializedName("ServerUTCT")
    val serverUTCT: Long? = null,

    @field:SerializedName("Result")
    val result: Result? = null
)

data class AutoDispatch(

    @field:SerializedName("Ex")
    val ex: Boolean? = null,

    @field:SerializedName("Taxi")
    val taxi: Boolean? = null
)


data class Info(

    @field:SerializedName("CustAcct")
    val custAcct: String? = null,

    @field:SerializedName("Salary")
    val salary: String? = null,

    @field:SerializedName("HappyGoInfo")
    val happyGoInfo: List<Any?>? = null,

    @field:SerializedName("VIPSDate")
    val vIPSDate: String? = null,

    @field:SerializedName("SEX")
    val sEX: String? = null,

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

data class AppSetting(

    @field:SerializedName("ForceRePwd")
    val forceRePwd: String? = null,

    @field:SerializedName("Signature")
    val signature: Signature? = null,

    @field:SerializedName("FeeSetting")
    val feeSetting: List<FeeSettingItem?>? = null,

    @field:SerializedName("SpecOrder1")
    val specOrder1: String? = null,

    @field:SerializedName("AppLoginAuth")
    val appLoginAuth: String? = null,

    @field:SerializedName("PaidType1")
    val paidType1: Int? = null,

    @field:SerializedName("SpecOrder2")
    val specOrder2: String? = null,

    @field:SerializedName("PaidType2")
    val paidType2: Int? = null,

    @field:SerializedName("AppThemeVer")
    val appThemeVer: Int? = null,

    @field:SerializedName("AutoDispatch")
    val autoDispatch: AutoDispatch? = null
)


data class DiscountOptsItem(

    @field:SerializedName("Type")
    val type: Int? = null
)

data class FeeSettingItem(

    @field:SerializedName("StTime")
    val stTime: String? = null,

    @field:SerializedName("LowerLmt")
    val lowerLmt: Int? = null,

    @field:SerializedName("EdTime")
    val edTime: String? = null
)

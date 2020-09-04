package k.c.mapcardviewdemo.app.util

import k.c.mapcardviewdemo.app.CommonBusiness.CommonSingle
import java.util.*

class UuidUtil {

    companion object {
        fun getUUID(): String {
            var uuid = CommonSingle.UUID
            if (uuid == "") {

                uuid = UUID.randomUUID().toString().toUpperCase()
                CommonSingle.UUID = uuid

            }
            return uuid
        }
    }
}
package k.c.mapcardviewdemo.app.util

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SHAUtil{


    companion object {
        fun sha1Hash(toHash: String): String? {
            val hash: String? = null
            try {
                val digest = MessageDigest.getInstance("SHA-1")
                var bytes = toHash.toByteArray(charset("UTF-8"))
                digest.update(bytes, 0, bytes.size)
                bytes = digest.digest()
                val encoded = Base64.encode(bytes, Base64.NO_WRAP)

                return String(encoded)
                // This is ~55x faster than looping and String.formating()
                //            hash = bytesToHex( bytes );
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

            return hash
        }
    }
}
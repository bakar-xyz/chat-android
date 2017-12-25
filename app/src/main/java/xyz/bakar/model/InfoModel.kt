package xyz.bakar.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import xyz.bakar.BuildConfig

/**
 * Created by kalapuneet on 24-12-2017.
 */
class InfoModel(val context: Context) {
    val platform = "ANDROID"
    val appVersion = BuildConfig.VERSION_NAME
    val deviceModel = Build.MANUFACTURER + " " + Build.MODEL
    @SuppressLint("MissingPermission")
    val imei = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
    val osVersion = Build.VERSION.RELEASE
    var location = HashMap<String,Any>()
    var notifications = Any()

    fun toHashMap(): HashMap<String,Any> {
        val map = HashMap<String,Any>()
        map.put("platform",platform)
        map.put("appVersion",appVersion)
        map.put("deviceModel",deviceModel)
        map.put("imei",imei)
        map.put("osVersion",osVersion)
        map.put("location",location)
        map.put("notifications",notifications)
        return map
    }

    fun fromHashMap(map:MutableMap<String,Any>): InfoModel {
        location = map["location"] as HashMap<String, Any>? ?: HashMap()
        notifications = map["notifications"] ?: Any()
        return this
    }
}
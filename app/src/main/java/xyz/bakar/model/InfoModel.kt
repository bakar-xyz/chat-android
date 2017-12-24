package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.BuildConfig
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class InfoModel() : BaseParcelable() {
    val platform = "ANDROID"
    val appVersion = BuildConfig.VERSION_NAME
    var deviceModel = ""
    var imei = ""
    var osVersion = ""
    var location = HashMap<String,Any>()
    var notifications = Any()

    constructor(parcel: Parcel) : this() {
        deviceModel = parcel.readString()
        imei = parcel.readString()
        osVersion = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(deviceModel)
        parcel.writeString(imei)
        parcel.writeString(osVersion)
    }

    companion object CREATOR : Parcelable.Creator<InfoModel> {
        override fun createFromParcel(parcel: Parcel): InfoModel {
            return InfoModel(parcel)
        }

        override fun newArray(size: Int): Array<InfoModel?> {
            return arrayOfNulls(size)
        }
    }
}
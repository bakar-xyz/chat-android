package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class ChannelModel() : BaseParcelable() {
    var id = ""
    var name = ""
    var userAccess = HashMap<String, String>()
    var purpose = ""
    var type = Type.PUBLIC
    var origin = OriginModel()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        purpose = parcel.readString()
        type = parcel.readString()
        origin = parcel.readParcelable(OriginModel::class.java.classLoader)
    }

    object Type {
        const val PUBLIC = "public"
        const val PRIVATE = "private"
        const val HIDDEN = "hidden"
    }

    fun toHashMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map.put("id", id)
        map.put("name", name)
        map.put("userAccess", userAccess)
        map.put("purpose", purpose)
        map.put("type", type)
        map.put("createdBy", origin.createdBy)
        map.put("createdAt", origin.createdAt)
        return map
    }

    fun fromHashMap(map: MutableMap<String, Any>): ChannelModel {
        this.id = map["id"] as String? ?: ""
        this.name = map["name"] as String? ?: ""
        this.userAccess = map["userAccess"] as HashMap<String, String>? ?: HashMap()
        this.purpose = map["purpose"] as String? ?: ""
        this.type = map["type"] as String? ?: Type.PUBLIC
        this.origin = OriginModel(map["createdAt"] as Long? ?: 0L, map["createdBy"] as String? ?: "")
        return this
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(purpose)
        parcel.writeString(type)
        parcel.writeParcelable(origin, flags)
    }

    companion object CREATOR : Parcelable.Creator<ChannelModel> {
        override fun createFromParcel(parcel: Parcel): ChannelModel {
            return ChannelModel(parcel)
        }

        override fun newArray(size: Int): Array<ChannelModel?> {
            return arrayOfNulls(size)
        }
    }
}
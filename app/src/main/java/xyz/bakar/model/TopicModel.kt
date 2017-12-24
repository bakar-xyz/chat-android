package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class TopicModel() : BaseParcelable() {
    var id = ""
    var title = ""
    var origin = OriginModel()
    var lastUpdatedAt = 0L
    var status = STATUS.OPEN
    var pinned = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        origin = parcel.readParcelable(OriginModel::class.java.classLoader)
        lastUpdatedAt = parcel.readLong()
        status = parcel.readString()
        pinned = parcel.readByte() != 0.toByte()
    }

    object STATUS {
        const val OPEN = "open"
        const val CLOSED = "closed"
        const val ARCHIVED = "archived"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeParcelable(origin, flags)
        parcel.writeLong(lastUpdatedAt)
        parcel.writeString(status)
        parcel.writeByte(if (pinned) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<TopicModel> {
        override fun createFromParcel(parcel: Parcel): TopicModel {
            return TopicModel(parcel)
        }

        override fun newArray(size: Int): Array<TopicModel?> {
            return arrayOfNulls(size)
        }
    }

    fun toHashMap(): HashMap<String,Any> {
        val map = HashMap<String,Any>()
        map.put("id",id)
        map.put("title",title)
        map.put("createdAt",origin.createdAt)
        map.put("createdBy",origin.createdBy)
        map.put("lastUpdatedAt",lastUpdatedAt)
        map.put("status",status)
        map.put("pinned",pinned)
        return map
    }

    fun fromHashMap(map: MutableMap<String, Any>) : TopicModel {
        id = map["id"] as String? ?: ""
        title = map["title"] as String? ?: ""
        origin = OriginModel(map["createdAt"] as Long? ?: 0L, map["createdBy"] as String? ?: "")
        lastUpdatedAt = map["lastUpdatedAt"] as Long? ?: 0L
        status = map["status"] as String? ?: ""
        pinned = map["pinned"] as Boolean? ?: false
        return this
    }
}
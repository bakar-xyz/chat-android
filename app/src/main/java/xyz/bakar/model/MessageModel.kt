package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class MessageModel() : BaseParcelable() {
    var id = ""
    var parentId = ""
    var isEdited = false
    var type = Type.TEXT
    var from = From.USER
    var origin = OriginModel()
    var status = Status.DEFAULT
    var metaData = ""
    var session = ""
    var spam = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        parentId = parcel.readString()
        isEdited = parcel.readByte() != 0.toByte()
        type = parcel.readString()
        from = parcel.readString()
        origin = parcel.readParcelable(OriginModel::class.java.classLoader)
        status = parcel.readString()
        metaData = parcel.readString()
        session = parcel.readString()
        spam = parcel.readByte() != 0.toByte()
    }

    object Type {
        const val FILE = "file"
        const val TEXT = "text"
        const val LINK = "link"
        const val INVISIBLE = "invisible"
        const val FORMS = ""
    }

    object From {
        const val USER = "user"
        const val BOT = "bot"
    }

    object Status {
        const val DEFAULT = "default"
        const val ARCHIVE = "archive"
        const val NOTIFIED = "notified"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(parentId)
        parcel.writeByte(if (isEdited) 1 else 0)
        parcel.writeString(type)
        parcel.writeString(from)
        parcel.writeParcelable(origin, flags)
        parcel.writeString(status)
        parcel.writeString(metaData)
        parcel.writeString(session)
        parcel.writeByte(if (spam) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<MessageModel> {
        override fun createFromParcel(parcel: Parcel): MessageModel {
            return MessageModel(parcel)
        }

        override fun newArray(size: Int): Array<MessageModel?> {
            return arrayOfNulls(size)
        }
    }

    fun toHashMap(): HashMap<String,Any> {
        val map = HashMap<String,Any>()
        map.put("id",id)
        map.put("parentId",parentId)
        map.put("isEdited",isEdited)
        map.put("type",type)
        map.put("from",from)
        map.put("createdAt",origin.createdAt)
        map.put("createdBy",origin.createdBy)
        map.put("status",status)
        map.put("metaData",metaData)
        map.put("session",session)
        map.put("spam",spam)
        return map
    }

    fun fromHashMap(map: MutableMap<String,Any>): MessageModel {
        id = map["id"] as String? ?: ""
        parentId = map["parentId"] as String? ?: ""
        isEdited = map["isEdited"] as Boolean? ?: false
        type = map["type"] as String? ?: ""
        from = map["from"] as String? ?: ""
        origin = OriginModel(map["createdAt"] as Long? ?:  0L, map["createdBy"] as String? ?: "")
        status = map["status"] as String? ?: ""
        metaData = map["metaData"] as String? ?: ""
        session = map["session"] as String? ?: ""
        spam = map["spam"] as Boolean? ?: false
        return this
    }
}
package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class OriginModel() : BaseParcelable() {
    var createdAt = 0L
    var createdBy = ""

    constructor(createdAt: Long, createdBy: String) : this() {
        this.createdBy = createdBy
        this.createdAt = createdAt
    }

    constructor(parcel: Parcel) : this() {
        createdAt = parcel.readLong()
        createdBy = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(createdAt)
        parcel.writeString(createdBy)
    }

    companion object CREATOR : Parcelable.Creator<OriginModel> {
        override fun createFromParcel(parcel: Parcel): OriginModel {
            return OriginModel(parcel)
        }

        override fun newArray(size: Int): Array<OriginModel?> {
            return arrayOfNulls(size)
        }
    }

    fun toHashMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map.put("createdBy", createdBy)
        map.put("createdAt", createdAt)
        return map
    }
}
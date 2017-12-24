package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by kalapuneet on 24-12-2017.
 */
class OriginModel() : Parcelable {
    var createdAt = 0L
    var createdBy = ""

    constructor(parcel: Parcel) : this() {
        createdAt = parcel.readLong()
        createdBy = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(createdAt)
        parcel.writeString(createdBy)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OriginModel> {
        override fun createFromParcel(parcel: Parcel): OriginModel {
            return OriginModel(parcel)
        }

        override fun newArray(size: Int): Array<OriginModel?> {
            return arrayOfNulls(size)
        }
    }
}
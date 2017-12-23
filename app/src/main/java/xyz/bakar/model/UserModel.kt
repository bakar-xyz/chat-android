package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import xyz.bakar.custom.BParcelable

/**
 * Created by kalapuneet on 23-12-2017.
 */
class UserModel() : BParcelable() {
    var id: String = ""
    var handle: String = ""
    var accessLevel: String = ""
    var profileImageUrl: String = ""
    var joiningDateTime: String = ""
    var dateOfBirth: String = ""
    var location: String = ""
    var active: Boolean = false
    var firstName: String = ""
    var lastName: String = ""
    var phoneNumber: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        handle = parcel.readString()
        accessLevel = parcel.readString()
        profileImageUrl = parcel.readString()
        joiningDateTime = parcel.readString()
        dateOfBirth = parcel.readString()
        location = parcel.readString()
        active = parcel.readByte() != 0.toByte()
        firstName = parcel.readString()
        lastName = parcel.readString()
        phoneNumber = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(handle)
        parcel.writeString(accessLevel)
        parcel.writeString(profileImageUrl)
        parcel.writeString(joiningDateTime)
        parcel.writeString(dateOfBirth)
        parcel.writeString(location)
        parcel.writeByte(if (active) 1 else 0)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(phoneNumber)
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
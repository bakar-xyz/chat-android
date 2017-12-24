package xyz.bakar.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import xyz.bakar.base.BaseParcelable

/**
 * Created by kalapuneet on 23-12-2017.
 */
class UserModel() : BaseParcelable() {
    var id = ""
    var name = ""
    var handle = ""
    var email = ""
    var accessLevel = AccessLevel.OWNER
    var image = ""
    var joiningDateTime = 0L
    var active = false
    var phoneNumber = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        handle = parcel.readString()
        email = parcel.readString()
        accessLevel = parcel.readString()
        image = parcel.readString()
        joiningDateTime = parcel.readLong()
        active = parcel.readByte() != 0.toByte()
        phoneNumber = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(handle)
        parcel.writeString(email)
        parcel.writeString(accessLevel)
        parcel.writeString(image)
        parcel.writeLong(joiningDateTime)
        parcel.writeByte(if (active) 1 else 0)
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

    fun toHashMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map.put("id", id)
        map.put("name", name)
        map.put("handle", handle)
        map.put("email", email)
        map.put("accessLevel", accessLevel)
        map.put("image", image)
        map.put("joiningDateTime", joiningDateTime)
        map.put("active", active)
        map.put("phoneNumber", phoneNumber)
        return map
    }

    fun fromHashMap(map: MutableMap<String, Any>): UserModel {
        id = map["id"] as String? ?: ""
        name = map["name"] as String? ?: ""
        handle = map["handle"] as String? ?: ""
        email = map["email"] as String? ?: ""
        accessLevel = map["accessLevel"] as String? ?: AccessLevel.OWNER
        image = map["image"] as String? ?: ""
        joiningDateTime = map["joiningDateTime"] as Long? ?: 0L
        active = map["active"] as Boolean? ?: false
        phoneNumber = map["phoneNumber"] as String? ?: ""
        return this
    }

    fun fromCurrentUser(currentUser: FirebaseUser?, handle: String = "", accessLevel: String = AccessLevel.OWNER, active: Boolean = false): UserModel {
        this.id = currentUser?.uid ?: ""
        this.name = currentUser?.displayName ?: ""
        this.handle = handle
        this.email = currentUser?.email ?: ""
        this.accessLevel = accessLevel
        this.image = currentUser?.photoUrl?.path ?: ""
        this.joiningDateTime = currentUser?.metadata?.creationTimestamp ?: 0L
        this.active = active
        this.phoneNumber = currentUser?.phoneNumber ?: ""
        return this
    }
}
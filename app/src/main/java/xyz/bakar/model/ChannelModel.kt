package xyz.bakar.model

/**
 * Created by kalapuneet on 24-12-2017.
 */
class ChannelModel {
    var id = ""
    var name = ""
    var userAccess = HashMap<String,String>()
    var purpose = ""
    var type = Type.PUBLIC
    var origin = OriginModel()

    object Type {
        const val PUBLIC = "public"
        const val PRIVATE = "private"
        const val HIDDEN = "hidden"
    }

    object AccessLevel {
        const val READ = "read"
        const val WRITE = "write"
        const val INVITE = "invite"
        const val REMOVE = "remove"
        const val MANAGE = "manage_access_level"
    }

    fun toHashMap(): HashMap<String,Any> {
        return HashMap()
    }

    fun fromHashMap(): ChannelModel {
        return this
    }
}
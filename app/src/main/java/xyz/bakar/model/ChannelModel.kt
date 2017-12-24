package xyz.bakar.model

/**
 * Created by kalapuneet on 24-12-2017.
 */
class ChannelModel {
    var id = ""
    var name = ""
    var userAccess = HashMap<String, String>()
    var purpose = ""
    var type = Type.PUBLIC
    var origin = OriginModel()

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
}
package xyz.bakar.network

import com.google.firebase.firestore.FirebaseFirestore
import xyz.bakar.base.BaseExecutor
import xyz.bakar.model.*

/**
 * Created by kalapuneet on 24-12-2017.
 */
class FirebaseExecutor: BaseExecutor() {

    private var userId = ""

    private var db = FirebaseFirestore.getInstance()

    private fun execute(runnable: Runnable) = runOnBackgroundThread(runnable)

    private fun usersCollection() = db.collection("users")

    private fun channelsCollection() = db.collection("channels")

    private fun topicsCollection(channelId: String) = db.collection("channels").document(channelId).collection("topics")

    private fun messagesCollection(channelId: String, topicId: String) = db.collection("channels").document(channelId).collection("topics").document(topicId).collection("messages")

    fun addUser(userModel: UserModel) = execute(Runnable {
        val users = usersCollection()
        userId = userModel.id
        users.document(userModel.id).set(userModel.toHashMap()).addOnSuccessListener {
            val channelModel = ChannelModel()
            channelModel.id = "1"
            channelModel.name = "Test Channel"
            channelModel.purpose = "To test the basic functionality"
            channelModel.origin = OriginModel(System.currentTimeMillis(),userModel.id)
            val userAccess = HashMap<String,String>()
            userAccess.put(userModel.id,AccessLevel.OWNER)
            channelModel.userAccess = userAccess
            addChannel(channelModel)
        }
    })

    fun getUser(id: String) = execute(Runnable {
        val users = usersCollection()
        users.document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val userModel = UserModel()
                userModel.fromHashMap(it.result.data)
            }
        }
    })

    fun addChannel(channelModel: ChannelModel) = execute(Runnable {
        val channels = channelsCollection()
        channels.document(channelModel.id).set(channelModel.toHashMap()).addOnSuccessListener {
            val topicModel = TopicModel()
            topicModel.id = "1"
            topicModel.title = "Test topic"
            topicModel.origin = OriginModel(System.currentTimeMillis(),userId)
            topicModel.lastUpdatedAt = System.currentTimeMillis()
            topicModel.pinned = false
            topicModel.status = TopicModel.Status.OPEN
            addTopic(topicModel,channelModel.id)
        }
    })

    fun getChannel(id: String) = execute(Runnable {
        val channels = channelsCollection()
        channels.document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val channelModel = ChannelModel()
                channelModel.fromHashMap(it.result.data)
            }
        }
    })

    fun addTopic(topicModel: TopicModel, channelId: String) = execute(Runnable {
        val topics = topicsCollection(channelId)
        topics.document(topicModel.id).set(topicModel.toHashMap()).addOnSuccessListener {
            for (i in 1..10) {
                val messageModel = MessageModel()
                messageModel.id = "" + i
                messageModel.metaData = ""
                messageModel.status = MessageModel.Status.DEFAULT
                messageModel.origin = OriginModel(System.currentTimeMillis(),userId)
                messageModel.from = MessageModel.From.USER
                messageModel.isEdited = false
                messageModel.parentId = ""
                messageModel.session = ""
                messageModel.type = MessageModel.Type.TEXT
                messageModel.spam = false
                messageModel.content = "test message content " + i
                addMessage(channelId,topicModel.id,messageModel)
            }
        }
    })

    fun getTopic(topicId: String, channelId: String) {
        val topics = topicsCollection(channelId)
        topics.document(topicId).get().addOnCompleteListener{
            if (it.isSuccessful) {
                val topicModel = TopicModel()
                topicModel.fromHashMap(it.result.data)
            }
        }
    }

    fun addMessage(channelId: String, topicId: String, messageModel: MessageModel) {
        val messages = messagesCollection(channelId, topicId)
        messages.document(messageModel.id).set(messageModel.toHashMap())
    }

    fun getMessage(topicId: String, channelId: String, messageId: String) {
        val messages = messagesCollection(channelId, topicId)
        messages.document(messageId).get().addOnCompleteListener{
            if (it.isSuccessful) {
                val messageModel = MessageModel()
                messageModel.fromHashMap(it.result.data)
            }
        }
    }
}
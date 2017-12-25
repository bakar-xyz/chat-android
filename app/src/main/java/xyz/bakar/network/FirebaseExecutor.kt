package xyz.bakar.network

import com.google.firebase.firestore.FirebaseFirestore
import xyz.bakar.base.BaseExecutor
import xyz.bakar.model.ChannelModel
import xyz.bakar.model.MessageModel
import xyz.bakar.model.TopicModel
import xyz.bakar.model.UserModel

/**
 * Created by kalapuneet on 24-12-2017.
 */
class FirebaseExecutor: BaseExecutor() {

    private var db = FirebaseFirestore.getInstance()

    private fun execute(runnable: Runnable) = runOnBackgroundThread(runnable)

    private fun usersCollection() = db.collection("users")

    private fun channelsCollection() = db.collection("channels")

    private fun topicsCollection(channelId: String) = db.collection("channels").document(channelId).collection("topics")

    private fun messagesCollection(channelId: String, topicId: String) = db.collection("channels").document(channelId).collection("topics").document(topicId).collection("messages")

    fun addUser(userModel: UserModel) = execute(Runnable {
        val users = usersCollection()
        users.document(userModel.id).set(userModel.toHashMap())
    })

    fun readUser(id: String) = execute(Runnable {
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
        channels.document(channels.id).set(channelModel.toHashMap())
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
        topics.document(topicModel.id).set(topicModel.toHashMap())
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
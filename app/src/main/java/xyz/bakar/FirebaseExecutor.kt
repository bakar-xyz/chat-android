package xyz.bakar

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import xyz.bakar.base.BaseExecutor
import xyz.bakar.model.UserModel

/**
 * Created by kalapuneet on 24-12-2017.
 */
class FirebaseExecutor: BaseExecutor() {

    private var db = FirebaseFirestore.getInstance()

    private fun execute(runnable: Runnable) = runOnBackgroundThread(runnable)

    fun addUser(userModel: UserModel) = execute(Runnable {
        val users: CollectionReference = db.collection("users")
        users.document(userModel.id).set(userModel.toHashMap())
    })

    fun readUsers(id: String) = execute(Runnable {
        val users: CollectionReference = db.collection("users")
        users.document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val userModel = UserModel()
                userModel.fromHashMap(it.result.data)
            }
        }
    })
}
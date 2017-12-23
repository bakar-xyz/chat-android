package xyz.bakar.app

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by kalapuneet on 23-12-2017.
 */
class BApplication : Application() {

    companion object {
        lateinit var mAuth: FirebaseAuth
    }

    override fun onCreate() {
        super.onCreate()
        mAuth = FirebaseAuth.getInstance()
    }
}
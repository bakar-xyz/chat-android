package xyz.bakar.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import xyz.bakar.BuildConfig
import xyz.bakar.FirebaseExecutor
import xyz.bakar.R
import xyz.bakar.app.BApplication
import xyz.bakar.custom.Constants
import xyz.bakar.custom.UiCommon
import xyz.bakar.databinding.ActivityMainBinding
import xyz.bakar.model.UserModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var uiCommon: UiCommon
    private lateinit var binding: ActivityMainBinding

    private fun startSignIn(list: MutableList<AuthUI.IdpConfig>) = startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(!BuildConfig.DEBUG).setAvailableProviders(list).build(), Constants.RC_SIGN_IN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        uiCommon = UiCommon(this)
        Handler().postDelayed({
            startSignIn(Arrays.asList(
//                    AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
            ))
        },Constants.SHORT_DELAY_TIME)
    }

    private fun onLoginSuccess() {
        FirebaseExecutor().addUser(UserModel().fromCurrentUser(BApplication.mAuth.currentUser))
        binding.tagline.text = (getString(R.string.success_sign_in) + " " + BApplication.mAuth.currentUser?.displayName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.title.visibility = View.GONE
        when (requestCode) {
            Constants.RC_SIGN_IN -> {
                val response: IdpResponse? = IdpResponse.fromResultIntent(data)
                when (resultCode) {
                    Activity.RESULT_OK -> onLoginSuccess()
                    else -> {
                        binding.tagline.setTextColor(Color.RED)
                        binding.tagline.text = getString(when {
                            response == null -> R.string.sign_in_cancelled
                            response.errorCode == ErrorCodes.NO_NETWORK -> R.string.no_internet_connection
                            response.errorCode == ErrorCodes.UNKNOWN_ERROR -> R.string.unknown_error
                            else -> R.string.unknown_sign_in_response
                        })
                    }
                }
            }
        }
    }
}

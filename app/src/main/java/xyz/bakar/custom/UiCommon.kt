package xyz.bakar.custom

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import xyz.bakar.app.BApplication

/**
 * Created by kalapuneet on 23-12-2017.
 */
class UiCommon(private val context: Context = BApplication().applicationContext) {

    fun showShortToast(text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun showShortToast(id: Int) = showShortToast(context.getString(id))
}
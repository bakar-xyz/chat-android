package xyz.bakar.custom

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Created by kalapuneet on 23-12-2017.
 */
class UiCommon(private val context: Context) {

    fun showShortToast(text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun showShortToast(id: Int) {
        showShortToast(context.getString(id))
    }
}
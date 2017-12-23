package xyz.bakar.custom

import android.os.Parcelable

/**
 * Created by kalapuneet on 23-12-2017.
 */
abstract class BParcelable : Parcelable {
    override fun describeContents(): Int = 0
}
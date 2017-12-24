package xyz.bakar.base

import android.os.Parcelable

/**
 * Created by kalapuneet on 23-12-2017.
 */
abstract class BaseParcelable : Parcelable {
    override fun describeContents(): Int = 0
}
package com.example.techtask

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

class ConstantDeclarartion {

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preferences"
        const val LOGIN_STATUS = "loginStatus"

        var contextRef: WeakReference<Context>? = null
        var locationManager: LocationManager? = null
        var locationListener: LocationListener? = null

        fun setContext(context: Context) {
            contextRef = WeakReference(context)
        }

        fun saveString(context: Context, key: String, value: String) {
            val sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getString(context: Context, key: String, defaultValue: String): String {
            val sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }
    }
}

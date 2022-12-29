package com.example.androidtestfindname.presentation.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class App : Application() {

    companion object{
        private var mContext: WeakReference<Context>? = null

        fun getContext(): Context? {
            return mContext!!.get()
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = WeakReference(this)
    }
}
package com.sunil.twitterkotlinmvvm

import android.app.Application
import android.content.Context
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

/**
 * Created by sunil on 05-11-2017.
 */
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(TwitterConfig.Builder(this).twitterAuthConfig
        (TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET))).build())

        val context: Context = MainApplication.applicationContext()
    }

}
package com.sunil.twitterkotlinmvvm.viewmodel

import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by sunil on 05-11-2017.
 */
class TimelineFragmentViewModel(val timelineRefreshListener: TimelineRefreshListener) {

    private val TAG = javaClass.simpleName

    var isLoading: ObservableInt = ObservableInt(View.VISIBLE)
    var username = "Loading."

    fun getTweets() {
        TwitterCore.getInstance().apiClient.statusesService.homeTimeline(20, null, null, false, true, false, false)
                .enqueue(object : Callback<List<Tweet>>() {
                    override fun failure(exception: TwitterException?) {
                        Log.d(TAG, "FAILURE")
                        Log.d(TAG, exception!!.message.toString())
                    }
                    override fun success(result: Result<List<Tweet>>?) {
                        timelineRefreshListener.onTimelineRefresh(result!!.data)
                        isLoading.set(View.INVISIBLE)
                    }
                })
    }
    interface TimelineRefreshListener {
        fun onTimelineRefresh(tweets: List<Tweet>)
    }
}
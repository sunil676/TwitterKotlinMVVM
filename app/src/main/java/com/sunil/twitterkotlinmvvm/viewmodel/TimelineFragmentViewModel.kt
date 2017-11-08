package com.sunil.twitterkotlinmvvm.viewmodel

import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable

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

     fun sendTweet(tweetText: String): Observable<Result<Tweet>> {
        return Observable.create { subscriber ->
            val callback = object : Callback<Tweet>() {
                override fun success(result: Result<Tweet>) {
                    Log.i(TAG, "Tweet tweeted")
                    subscriber.onNext(result)
                }

                override fun failure(e: TwitterException) {
                    Log.e(TAG, e.message, e)
                    subscriber.onError(e)
                }
            }

            TwitterCore.getInstance().apiClient.statusesService.update(tweetText, null, null, null, null, null, null, null, null).enqueue(callback)
        }
    }

    interface TimelineRefreshListener {
        fun onTimelineRefresh(tweets: List<Tweet>)
    }
}
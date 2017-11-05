package com.sunil.twitterkotlinmvvm.viewmodel

import android.databinding.ObservableField
import android.graphics.drawable.Drawable


/**
 * Created by sunil on 05-11-2017.
 */
class TimelineItemViewModel {

    private val TAG = javaClass.simpleName

    var tweet = ObservableField<String>()
    var user  = ObservableField<String>()
    var imageUrl= ObservableField<Drawable>()
    var userScreenName = ObservableField<String>()
}
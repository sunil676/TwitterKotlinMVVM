package com.sunil.twitterkotlinmvvm.viewmodel

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.sunil.twitterkotlinmvvm.R


/**
 * Created by sunil on 05-11-2017.
 */
public class ProfileViewModel(context: Context, imageUrl : String) {

    var IMAGE_URL: String? = null
    var profileImage: ObservableField<Drawable>? = null
    private var bindableFieldTarget: BindableFieldTarget? = null

    init {
        this.IMAGE_URL = imageUrl
        profileImage = ObservableField<Drawable>()
        // Picasso keeps a weak reference to the target so it needs to be stored in a field
        bindableFieldTarget = BindableFieldTarget(profileImage!!, context.getResources())
        Picasso.with(context)
                .load(IMAGE_URL)
                .placeholder(R.drawable.user)
                .into(bindableFieldTarget);
    }

    class BindableFieldTarget(private val observableField: ObservableField<Drawable>, private val resources: Resources) : Target {

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            observableField.set(BitmapDrawable(resources, bitmap))
        }

        override fun onBitmapFailed(errorDrawable: Drawable) {
            observableField.set(errorDrawable)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable) {
            observableField.set(placeHolderDrawable)
        }
    }
}
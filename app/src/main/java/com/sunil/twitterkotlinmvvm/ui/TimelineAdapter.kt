package ccom.sunil.twitterkotlinmvvm.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sunil.twitterkotlinmvvm.MainApplication
import com.sunil.twitterkotlinmvvm.R
import com.sunil.twitterkotlinmvvm.databinding.TimelineItemBinding
import com.sunil.twitterkotlinmvvm.viewmodel.ProfileViewModel
import com.sunil.twitterkotlinmvvm.viewmodel.TimelineItemViewModel
import com.twitter.sdk.android.core.models.Tweet


/**
 * Created by sunil on 05-11-2017.
 */
class TimelineAdapter(var items: List<Tweet>) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    val TAG = javaClass.simpleName

    constructor() : this(emptyList())

    // Provide a reference to the views for each data item
    class TimelineViewHolder(var view: TimelineItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: TimelineViewHolder?, position: Int) {
        // - get element from your dataset at this position & replace the contents of the view with that element
        holder!!.view.vm.tweet.set(items[position].text)
        holder.view.vm.user.set(items[position].user.screenName)
        val profileViewModel = ProfileViewModel(MainApplication.applicationContext(), items[position].user.profileImageUrl)
        holder.view.vm.imageUrl.set(profileViewModel.profileImage!!.get())
        holder.view.vm.userScreenName.set(String.format("@%s", items[position].user.screenName))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TimelineAdapter.TimelineViewHolder {
        val inflater = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: TimelineItemBinding = DataBindingUtil.inflate(inflater, R.layout.timeline_item, parent, false)
        binding.vm = TimelineItemViewModel()

        return TimelineViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(tweets: List<Tweet>){
        items = emptyList()
        items = tweets
        notifyDataSetChanged()
    }

}

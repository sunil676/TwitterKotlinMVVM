package com.sunil.twitterkotlinmvvm.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ccom.sunil.twitterkotlinmvvm.ui.TimelineAdapter
import com.sunil.twitterkotlinmvvm.R
import com.sunil.twitterkotlinmvvm.databinding.FragmentTimelineBinding
import com.sunil.twitterkotlinmvvm.viewmodel.TimelineFragmentViewModel
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by sunil on 05-11-2017.
 */
class TimelineFragment : Fragment(), TimelineFragmentViewModel.TimelineRefreshListener {

    private var text: String? = null

    private var binding: FragmentTimelineBinding? = null
    private var timelineFragmentVM: TimelineFragmentViewModel? = null

    private var timelineAdapter = TimelineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            text = arguments.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout and attach the ViewModel for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        val view: View = binding!!.root
        timelineFragmentVM = TimelineFragmentViewModel(this)
        binding!!.vm = timelineFragmentVM

        binding!!.timelineRecview.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            // specify an adapter (see also next example)
            timelineFragmentVM!!.getTweets()
            adapter = timelineAdapter
        }
        return view
    }

    override fun onTimelineRefresh(tweets: List<Tweet>) {
        timelineAdapter.updateItems(tweets)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        fun newInstance(param1: String): TimelineFragment {
            val fragment = TimelineFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}

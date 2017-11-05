package com.sunil.twitterkotlinmvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.sunil.twitterkotlinmvvm.R

/**
 * Created by sunil on 05-11-2017.
 */
class TimelineActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TimelineFragment.newInstance("Oi"))
                    .commit()
        }
    }

}
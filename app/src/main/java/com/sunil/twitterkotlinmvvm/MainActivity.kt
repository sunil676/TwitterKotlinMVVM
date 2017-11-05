package com.sunil.twitterkotlinmvvm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sunil.twitterkotlinmvvm.ui.TimelineActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class MainActivity : AppCompatActivity() {

    var loginButton: TwitterLoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        loginButton!!.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                startActivity(Intent(this@MainActivity, TimelineActivity::class.java))
                finish()
            }

            override fun failure(exception: TwitterException) {
                Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        loginButton!!.onActivityResult(requestCode, resultCode, data)
    }
}

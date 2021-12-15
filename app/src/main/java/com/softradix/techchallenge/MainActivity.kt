package com.softradix.techchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softradix.techchallenge.ui.StoreFeedFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeFeedFragment = StoreFeedFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container, storeFeedFragment,
                StoreFeedFragment.TAG
            )
            .commit()
    }

}
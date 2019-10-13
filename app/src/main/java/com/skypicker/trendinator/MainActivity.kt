package com.skypicker.trendinator

import android.os.*
import androidx.appcompat.app.*
import com.skypicker.trendinator.ui.main.*

class MainActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }
  }
}

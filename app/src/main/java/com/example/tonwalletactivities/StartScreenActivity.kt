package com.example.tonwalletactivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tonwalletactivities.ui.start.StartScreenStartFragment


class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartScreenStartFragment.newInstance())
                .commitNow()
        }
    }

    /** override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000) //millis

    } */
}

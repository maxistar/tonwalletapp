package me.maxistar.tonwallet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.start.StartScreenStartFragment


class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        val settingsService = ServiceProvider.getSettingsService()

        Log.w("URL", settingsService.getTonConfiguration(this.applicationContext))

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

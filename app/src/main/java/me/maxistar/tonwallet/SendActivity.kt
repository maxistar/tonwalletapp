package me.maxistar.tonwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.maxistar.tonwallet.ui.send.SendRecipientFragment

class SendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SendRecipientFragment.newInstance())
                .commitNow()
        }
    }
}
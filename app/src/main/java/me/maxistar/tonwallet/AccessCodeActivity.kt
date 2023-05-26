package me.maxistar.tonwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.maxistar.tonwallet.ui.access_code.AccessCodeFragment
import me.maxistar.tonwallet.ui.access_code.AccessCodeStartFragment

class AccessCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_code)

        val scanFlag = intent.getBooleanExtra("changePasscode", false)
        if (scanFlag) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AccessCodeFragment.newInstance())
                .commitNow()
            return
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AccessCodeStartFragment.newInstance())
                .commitNow()
        }
    }
}
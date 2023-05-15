package com.example.tonwalletactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tonwalletactivities.ui.create_wallet.CreateWalletAfterCreationConfirmationFragment

class AccessCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_code)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreateWalletAfterCreationConfirmationFragment.newInstance("", ""))
                .commitNow()
        }
    }
}
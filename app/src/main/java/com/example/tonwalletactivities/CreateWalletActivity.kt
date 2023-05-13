package com.example.tonwalletactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tonwalletactivities.ui.create_wallet.CreateWalletFragment
import com.example.tonwalletactivities.ui.create_wallet.StartWalletFragment

class CreateWalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartWalletFragment.newInstance())
                //.replace(R.id.container, CreateWalletFragment.newInstance())
                .commitNow()
        }
    }
}
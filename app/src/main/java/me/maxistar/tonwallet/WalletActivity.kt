package me.maxistar.tonwallet

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import me.maxistar.tonwallet.ui.wallet.WalletFragment

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WalletFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.scan_menu -> {
                val intent = Intent(this, ScanActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.quit -> {
                finishAffinity()
                val pid = Process.myPid()
                Process.killProcess(pid)
                //System.exit(0);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
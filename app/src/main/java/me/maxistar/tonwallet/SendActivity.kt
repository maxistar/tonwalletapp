package me.maxistar.tonwallet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import me.maxistar.tonwallet.ui.send.SendRecipientFragment


class SendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)

        val intent = intent
        val str = intent.getStringExtra("wallet_address")
        val address = extractWalletAddress(str)

        val scanFlag = intent.getBooleanExtra("scan", false)
        if (scanFlag) {
            scanBarcode()
            return
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SendRecipientFragment.newInstance(address))
                .commitNow()
        }
    }

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this@SendActivity, "Cancelled", Toast.LENGTH_LONG).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SendRecipientFragment.newInstance(""))
                .commitNow()
        } else {
            Toast.makeText(
                this@SendActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()

            val address = extractWalletAddress(result.contents)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SendRecipientFragment.newInstance(address))
                .commitNow()
        }
    }

    private fun extractWalletAddress(str: String?): String {
        if (str == null) {
            return "";
        }
        val prefix = "ton://transfer/"
        if (str.indexOf(prefix) == 0) {
            return str.substring(prefix.length);
        }
        return str;
    }

    fun scanBarcode() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Scan a barcode");
        options.setCameraId(0);  // Use a specific camera of the device
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        options.setOrientationLocked(false);
        barcodeLauncher.launch(options)
    }
}
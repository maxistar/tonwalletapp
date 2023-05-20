package me.maxistar.tonwallet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val button = findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            onButtonClick(it)
        }
    }

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this@ScanActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this@ScanActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun onButtonClick(view: View?) {
        barcodeLauncher.launch(ScanOptions())
    }
}
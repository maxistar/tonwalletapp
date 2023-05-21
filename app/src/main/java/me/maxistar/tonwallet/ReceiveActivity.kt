package me.maxistar.tonwallet


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import me.maxistar.tonwallet.service.ServiceProvider


class ReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)

        val walletService = ServiceProvider.getWalletService()
        val settingsService = ServiceProvider.getSettingsService()
        val address = settingsService.getWalletAddress(this)
        val addressLink = "ton://transfer/$address"

        val addressLabel = findViewById<TextView>(R.id.ton_wallet_address)
        addressLabel.text = address;

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(addressLink, BarcodeFormat.QR_CODE, 600, 600)
            val imageViewQrCode: ImageView = findViewById<View>(R.id.qrCode) as ImageView
            imageViewQrCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.message?.let { Log.w("warning", it) }
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            shareWallet(addressLink)
        }
    }

    private fun shareWallet(addressLink: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, addressLink)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
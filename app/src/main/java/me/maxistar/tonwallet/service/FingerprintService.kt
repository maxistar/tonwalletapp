package me.maxistar.tonwallet.service

import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import me.maxistar.tonwallet.R
import java.util.concurrent.Executor


class FingerprintService {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    fun authenticate(owner: Fragment, successCallback: () -> Unit, failureCallback: () -> Unit) {
        val context = owner.context as Context
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(owner, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    failureCallback()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    successCallback()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    failureCallback()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(owner.resources.getString(R.string.biometric_title))
            .setNegativeButtonText(owner.resources.getString(R.string.biometric_use_pincode))
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
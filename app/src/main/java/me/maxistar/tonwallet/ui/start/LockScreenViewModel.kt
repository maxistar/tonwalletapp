package me.maxistar.tonwallet.ui.start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.maxistar.tonwallet.model.AccessCodeModel

class LockScreenViewModel : ViewModel() {
    var code: AccessCodeModel = AccessCodeModel()

    private var ready: Boolean = false

    private val _liveReady = MutableLiveData<Boolean>().apply {
        value = ready
    }

    val liveReady: LiveData<Boolean> = _liveReady

    private var step: Int = 0

    private val _liveStep = MutableLiveData<Int>().apply {
        value = step
    }

    val liveStep: LiveData<Int> = _liveStep

    private var error: Boolean = false
    private val _liveError = MutableLiveData<Boolean>().apply {
        value = error
    }
    val liveError: LiveData<Boolean> = _liveError


    private var progress: Int = 0


    // todo simplify this!!!
    fun setCharacter(char: Char) {
        if (char == 'd') {
            if (step > 0) {
                step--
            }
            return
        }
        if (step == 0) {
            error = (error || (code.code1 != char))
        } else if (step == 1) {
            error = (error || (code.code2 != char))
        } else if (step == 2) {
            error = (error || (code.code3 != char))
        } else {
            error = (error || (code.code4 != char))
        }
        step++
        _liveStep.value = step
        if (step == 4) {
            if (error) {
                step = 0;
                _liveError.value = error
                error = false
                Log.w("Error4", error.toString())
            } else {
                ready = true
                _liveReady.value = ready
            }
        }
    }
}
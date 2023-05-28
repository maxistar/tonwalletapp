package me.maxistar.tonwallet.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.maxistar.tonwallet.model.AccessCodeModel

class LockScreenViewModel : ViewModel() {
    private var code: AccessCodeModel = AccessCodeModel()
    private var codeCompare: AccessCodeModel = AccessCodeModel()

    private var ready: Boolean = false

    private var codeLength = 4

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


    // todo simplify this!!!
    fun setCharacter(char: Char) {
        if (char == 'd') {
            if (step > 0) {
                step--
                _liveStep.value = step
            }
            return
        }
        when (step) {
            0 -> {
                codeCompare.code1 = char
            }
            1 -> {
                codeCompare.code2 = char
            }
            2 -> {
                codeCompare.code3 = char
            }
            3 -> {
                codeCompare.code4 = char
            }
            4 -> {
                codeCompare.code5 = char
            }
            else -> {
                codeCompare.code6 = char
            }
        }
        step++
        _liveStep.value = step
        if ((step == 4 && codeLength == 4) || (step == 6 && codeLength == 6)) {
            if (code.toString() != codeCompare.toString()) {
                step = 0
                _liveError.value = true
                error = false
            } else {
                ready = true
                _liveReady.value = ready
            }
        }
    }

    fun setCode(securityKey: String) {
        code.setCode(securityKey)
        if (securityKey.length > 4) {
            codeLength = 6
        }
    }
}
package me.maxistar.tonwallet.ui.access_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.maxistar.tonwallet.model.AccessCodeModel
import me.maxistar.tonwallet.model.TransactionItem

class AccessCodeViewModel : ViewModel() {
    var code: AccessCodeModel = AccessCodeModel()

    private var enterMode: Boolean = true

    private var codeLength = 4


    private val _liveEnterMode = MutableLiveData<Boolean>().apply {
        value = enterMode
    }

    val liveEnterMode: LiveData<Boolean> = _liveEnterMode

    private var ready: Boolean = false

    private val _liveReady = MutableLiveData<Boolean>().apply {
        value = ready
    }

    val liveReady: LiveData<Boolean> = _liveReady

    private var error: Boolean = false

    private var progress: Int = 0

    private var step: Int = 0

    private val _liveStep = MutableLiveData<Int>().apply {
        value = step
    }

    val liveStep: LiveData<Int> = _liveStep

    fun setCodeLength(lenght: Int) {
        codeLength = lenght
    }

    // todo simplify this!!!
    fun setCharacter(char: Char) {
        if (enterMode) {
            if (char == 'd') {
                if (step > 0) {
                    step--
                    _liveStep.value = step
                }
                return
            }
            if (step == 0) {
                code.code1 = char
            } else if (step == 1) {
                code.code2 = char
            } else if (step == 2) {
                code.code3 = char
            } else {
                code.code4 = char
            }
            step++
            _liveStep.value = step
            if (step == 4) {
                step = 0
                _liveStep.value = step
                enterMode = false
                _liveEnterMode.value = enterMode
            }
        } else { // check mode
            if (char == 'd') {
                if (step > 0) {
                    step--
                    _liveStep.value = step
                }
                return
            }
            if (step == 0) {
                error = code.code1 == char
            } else if (step == 1) {
                error = code.code2 == char
            } else if (step == 2) {
                error = code.code3 == char
            } else {
                error = code.code4 == char
            }
            step++
            _liveStep.value = step
            if (step == 4) {
                step = 0
                ready = true
                _liveReady.value = ready
            }
        }
    }
}
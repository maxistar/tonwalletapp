package me.maxistar.tonwallet.ui.access_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.maxistar.tonwallet.model.AccessCodeModel
import me.maxistar.tonwallet.model.TransactionItem

class AccessCodeViewModel : ViewModel() {
    private var code: AccessCodeModel = AccessCodeModel()

    private var enterMode: Boolean = true

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

    // todo simplify this!!!
    fun setCharacter(char: Char) {
        if (enterMode) {
            if (char == 'd') {
                if (step > 0) {
                    step--
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
            if (step == 4) {
                step = 0
                enterMode = false
                _liveEnterMode.value = enterMode
            }
        } else { // check mode
            if (char == 'd') {
                if (step > 0) {
                    step--
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
            if (step == 4) {
                step = 0
                ready = true
                _liveReady.value = ready
            }
        }
    }
}
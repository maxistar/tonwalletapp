package me.maxistar.tonwallet.ui.start

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

    private var error: Boolean = false

    private var progress: Int = 0

    private var step: Int = 0

    // todo simplify this!!!
    fun setCharacter(char: Char) {
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
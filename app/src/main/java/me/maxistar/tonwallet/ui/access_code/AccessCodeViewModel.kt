package me.maxistar.tonwallet.ui.access_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.maxistar.tonwallet.model.AccessCodeModel

class AccessCodeViewModel : ViewModel() {
    var code: AccessCodeModel = AccessCodeModel()
    private var codeRepeat: AccessCodeModel = AccessCodeModel()

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

    private val _liveError = MutableLiveData<Boolean>().apply {
        value = error
    }
    val liveError: LiveData<Boolean> = _liveError


    private var step: Int = 0

    private val _liveStep = MutableLiveData<Int>().apply {
        value = step
    }

    val liveStep: LiveData<Int> = _liveStep

    fun setCodeLength(lenght: Int) {
        codeLength = lenght
        step = 0
        _liveStep.value = step

        enterMode = true
        _liveEnterMode.value = enterMode
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
            when (step) {
                0 -> {
                    code.code1 = char
                }
                1 -> {
                    code.code2 = char
                }
                2 -> {
                    code.code3 = char
                }
                3 -> {
                    code.code4 = char
                }
                4 -> {
                    code.code5 = char
                }
                else -> {
                    code.code6 = char
                }
            }
            step++
            _liveStep.value = step
            if ((step == 4 && codeLength == 4) || (step == 6 && codeLength == 6)) {
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
            when (step) {
                0 -> {
                    codeRepeat.code1 = char
                }
                1 -> {
                    codeRepeat.code2 = char
                }
                2 -> {
                    codeRepeat.code3 = char
                }
                3 -> {
                    codeRepeat.code4 = char
                }
                4 -> {
                    codeRepeat.code5 = char
                }
                else -> {
                    codeRepeat.code6 = char
                }
            }
            step++
            _liveStep.value = step
            if ((step == 4 && codeLength == 4) || (step == 6 && codeLength == 6)) {
                if (code.toString() != codeRepeat.toString()) {
                    step = 0;
                    _liveError.value = true
                    error = false
                    enterMode = true
                    _liveEnterMode.value = enterMode
                } else {
                    ready = true
                    _liveReady.value = ready
                }
            }
        }
    }
}
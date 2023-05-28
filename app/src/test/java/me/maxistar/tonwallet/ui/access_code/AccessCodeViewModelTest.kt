package me.maxistar.tonwallet.ui.access_code

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AccessCodeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun access_code_HappyPath() {
        val model = AccessCodeViewModel()
        Assert.assertEquals(false, model.liveReady.value)
        model.code.setCode("1234")
        model.setCharacter('1')
        model.setCharacter('2')
        model.setCharacter('3')
        model.setCharacter('4')
        //repeat
        model.setCharacter('1')
        model.setCharacter('2')
        model.setCharacter('3')
        model.setCharacter('4')
        Assert.assertEquals(true, model.liveReady.value)
    }
}
package me.maxistar.tonwallet.ui.start

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class LockScreenViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun lock_screen_HappyPath() {
        val model = LockScreenViewModel()
        Assert.assertEquals(false, model.liveReady.value)
        model.setCode("1234")
        model.setCharacter('1')
        model.setCharacter('2')
        model.setCharacter('3')
        model.setCharacter('4')
        Assert.assertEquals(true, model.liveReady.value)
    }

    @Test
    fun lock_screen_HappyPath6Steps() {
        val model = LockScreenViewModel()
        Assert.assertEquals(false, model.liveReady.value)
        model.setCode("123456")
        model.setCharacter('1')
        model.setCharacter('2')
        model.setCharacter('3')
        model.setCharacter('4')
        model.setCharacter('5')
        model.setCharacter('6')
        Assert.assertEquals(true, model.liveReady.value)
    }
}
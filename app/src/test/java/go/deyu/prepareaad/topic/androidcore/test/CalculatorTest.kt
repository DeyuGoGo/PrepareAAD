package go.deyu.prepareaad.topic.androidcore.test

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class CalculatorTest {
    var mCalculator : Calculator = Calculator()
    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @Test
    fun add() {
        val resultAdd: Double = mCalculator.add(1.0, 1.0)
        assertThat(resultAdd, `is`(equalTo(2.0)))
    }

    @Test
    fun sub() {
        val resultSub = mCalculator.sub(1.0, 1.0)
        assertThat(resultSub, `is`(equalTo(0.0)))
    }

    @Test
    fun div() {
        val resultDiv = mCalculator.div(32.0, 2.0)
        assertThat(resultDiv, `is`(equalTo(16.0)))
    }

    @Test
    fun mul() {
        val resultMul = mCalculator.mul(32.0, 2.0)
        assertThat(resultMul, `is`(equalTo(64.0)))
    }
}
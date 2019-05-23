package com.example.contacts

import org.hamcrest.CoreMatchers.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun factors(){
        assertThat(factorsOf(1), `is`(emptyList()))
        assertThat(factorsOf(2), `is`(listOf(2)))
        assertThat(factorsOf(3), `is`(listOf(3)))
        assertThat(factorsOf(4), `is`(listOf(2,2)))
        assertThat(factorsOf(5), `is`(listOf(5)))
        assertThat(factorsOf(6), `is`(listOf(2,3)))
        assertThat(factorsOf(7), `is`(listOf(7)))
        assertThat(factorsOf(8), `is`(listOf(2,2,2)))
        assertThat(factorsOf(9), `is`(listOf(3,3)))
        assertThat(factorsOf(10), `is`(listOf(2,5)))
        assertThat(factorsOf(11), `is`(listOf(11)))
        assertThat(factorsOf(12), `is`(listOf(2,2,3)))
        assertThat(factorsOf(13), `is`(listOf(13)))
        assertThat(factorsOf(14), `is`(listOf(2,7)))
        assertThat(factorsOf(15), `is`(listOf(3,5)))
        assertThat(factorsOf(16), `is`(listOf(2,2,2,2)))
        assertThat(factorsOf(2*2*3*5*11*17), `is`(listOf(2,2,3,5,11,17)))
        assertThat(factorsOf(2*2*3*5*11*17), `is`(listOf(2,2,3,5,11,17)))
    }

    private fun factorsOf(nn: Int): List<Int>{
        var remainder = nn
        val factors = mutableListOf<Int>()
        var divisor = 2
        while (remainder > 1) {
            while (remainder % divisor == 0) {
                factors.add(divisor)
                remainder /= divisor
            }
            divisor++
        }

        return factors
    }
}

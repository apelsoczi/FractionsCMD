package usecase

import model.toMixedNumber
import org.apache.commons.math3.fraction.Fraction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CalculationFunctionTest {

    lateinit var outputStream: ByteArrayOutputStream
    lateinit var calculationFunction: CalculationFunction

    @BeforeEach
    fun setup() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        calculationFunction = CalculationFunction()
    }

    @Test
    fun `mixed number addition`() {
        // given
        val one = "1/2".toMixedNumber()
        val two = "2/3".toMixedNumber()
        val firstExpected = Fraction(7, 6)
        // when
        val firstFraction = calculationFunction.add(one, two)
        // then
        assert(firstFraction == firstExpected)
        assert(outputStream.toString().contains(firstExpected.toMixedNumber().toString()))
        // and given
        val three = "1&1/2".toMixedNumber()
        val four = "2&2/3".toMixedNumber()
        val secondExpected = Fraction(25, 6)
        outputStream.reset()
        // and when
        val secondFraction = calculationFunction.add(three, four)
        // and then
        assert(secondFraction == secondExpected)
        assert(outputStream.toString().contains(secondExpected.toMixedNumber().toString()))
    }

    @Test
    fun `mixed number subtraction`() {
        // given
        val one = "1/2".toMixedNumber()
        val two = "2&2/3".toMixedNumber()
        val firstExpected = Fraction(-13, 6)
        // when
        val firstFraction = calculationFunction.subtract(one, two)
        // then
        assert(firstFraction == firstExpected)
        assert(outputStream.toString().contains(firstExpected.toMixedNumber().toString()))
        // and given
        val three = "1&1/2".toMixedNumber()
        val four = "2&2/3".toMixedNumber()
        val secondExpected = Fraction(-7, 6)
        outputStream.reset()
        // and when
        val secondFraction = calculationFunction.subtract(three, four)
        // and then
        assert(secondFraction == secondExpected)
        assert(outputStream.toString().contains(secondExpected.toMixedNumber().toString()))
    }

    @Test
    fun `mixed number multiplication`() {
        // given
        val one = "1/2".toMixedNumber()
        val two = "2/3".toMixedNumber()
        val firstExpected = Fraction(1, 3)
        // when
        val firstFraction = calculationFunction.multiply(one, two)
        // then
        assert(firstFraction == firstExpected)
        assert(outputStream.toString().contains(firstExpected.toMixedNumber().toString()))
        // and given
        val three = "1&1/2".toMixedNumber()
        val four = "2&2/3".toMixedNumber()
        val secondExpected = Fraction(4)
        outputStream.reset()
        // and when
        val secondFraction = calculationFunction.multiply(three, four)
        // and then
        assert(secondFraction == secondExpected)
        assert(outputStream.toString().contains(secondExpected.toMixedNumber().toString()))
    }

    @Test
    fun `mixed number division`() {
        // given
        val one = "3/2".toMixedNumber()
        val two = "1/2".toMixedNumber()
        val firstExpected = Fraction(3)
        // when
        val firstFraction = calculationFunction.divide(one, two)
        // then
        assert(firstFraction == firstExpected)
        assert(outputStream.toString().contains(firstExpected.toMixedNumber().toString()))
        // and given
        val three = "2&2/3".toMixedNumber()
        val four = "1&1/2".toMixedNumber()
        val secondExpected = Fraction(16, 9)
        outputStream.reset()
        // and when
        val secondFraction = calculationFunction.divide(three, four)
        // and then
        assert(secondFraction == secondExpected)
        assert(outputStream.toString().contains(secondExpected.toMixedNumber().toString()))
    }

}
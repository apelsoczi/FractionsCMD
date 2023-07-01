package usecase

import model.MixedNumber
import model.toImproperFraction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculationFunctionTest {

    lateinit var calculationFunction: CalculationFunction

    @BeforeEach
    fun setup() {
        calculationFunction = CalculationFunction()
    }

    @Test
    fun `mixed number addition`() {
        // when

        // TODO: 7/1/2023 need to figure out negative values for improper fractions and signofwholenumber
        //  when whole number is 0 and numerator is negative
        val number = MixedNumber(
            whole = -10,
            numerator = 2,
            denominator = 5
        )
        val improper = number.toImproperFraction()
        val decimal = improper.decimal

        println()
    }

    /**
     *
     */

    @Test
    fun `mixed number subtraction`() {

    }

    @Test
    fun `mixed number multiplication`() {

    }

    @Test
    fun `mixed number division`() {

    }

}
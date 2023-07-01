package model

import java.math.BigDecimal
import kotlin.math.absoluteValue

data class MixedNumber(
    val whole: Int = 0,
    val numerator: Int = 0,
    val denominator: Int = 0,
) {

    val decimal: BigDecimal
        get() = toImproperFraction().let {
            BigDecimal(it.numerator).divide(BigDecimal(it.denominator))
        }
}

/**
 * Convert a string formatted as <whole>&<numerator>/<denominator> to a mixed number.
 */
fun String.toMixedNumber(): MixedNumber {
    val numbers = split('&', '/').map { it.toInt() }
    return when (numbers.size) {
        1 -> MixedNumber(whole = numbers[0])
        2 -> MixedNumber(numerator = numbers[0], denominator = numbers[1])
        else -> MixedNumber(whole = numbers[0], numerator = numbers[1], denominator = numbers[2])
    }
}

/**
 * convert all scenarios of negatives and positives for whole number, numerator, denominator to
 * an improper fraction.
 */
fun MixedNumber.toImproperFraction(): MixedNumber {
    val fraction = with(this) {
        if (whole.isPositive() && numerator.isPositive() && denominator.isPositive()) {
            val numerator = multiplyAbsoluteAndAdd(this)
            val denominator = this.denominator.absoluteValue
            val sign = 1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isPositive() && numerator.isPositive() && denominator.isNegative()) {
            val numerator = multiplyAbsoluteAndSubtract(this)
            val denominator = this.denominator.absoluteValue
            val sign = -1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isPositive() && numerator.isNegative() && denominator.isPositive()) {
            val numerator = multiplyAbsoluteAndSubtract(this)
            val denominator = this.denominator.absoluteValue
            val sign = 1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isPositive() && numerator.isNegative() && denominator.isNegative()) {
            val numerator = multiplyAbsoluteAndAdd(this)
            val denominator = this.denominator.absoluteValue
            val sign = -1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isNegative() && numerator.isPositive() && denominator.isPositive()) {
            val numerator = multiplyAbsoluteAndAdd(this)
            val denominator = this.denominator.absoluteValue
            val sign = -1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isNegative() && numerator.isPositive() && denominator.isNegative()) {
            val numerator = multiplyAbsoluteAndAdd(this)
            val denominator = this.denominator.absoluteValue
            val sign = 1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else if (whole.isNegative() && numerator.isNegative() && denominator.isPositive()) {
            val numerator = multiplyAbsoluteAndSubtract(this)
            val denominator = this.denominator.absoluteValue
            val sign = -1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        } else { // whole.isNegative() && numerator.isNegative() && denominator.isNegative()
            val numerator = multiplyAbsoluteAndSubtract(this)
            val denominator = this.denominator.absoluteValue
            val sign = 1
            val improperNumerator = if (sign.isPositive()) numerator * 1 else numerator * -1
            MixedNumber(
                numerator = improperNumerator,
                denominator = denominator,
            )
        }
    }
    return fraction
}

/**
 * check if an integer is greater than or equal to 0
 */
fun Int.isPositive() = this >= 0

/**
 * check if an integer is less than 0
 */
fun Int.isNegative() = this < 0

/**
 * Multiply the absolute value of the whole number by the absolute value of the denominator and add the
 * absolute value of the numerator.
 */
private fun multiplyAbsoluteAndAdd(number: MixedNumber): Int {
    val numerator = (number.whole.absoluteValue * number.denominator.absoluteValue) + number.numerator.absoluteValue
    return numerator
}

/**
 * Multiply the absolute value of the whole number by the absolute value of the denominator and subtract the
 * absolute value of the numerator.
 */
private fun multiplyAbsoluteAndSubtract(number: MixedNumber): Int {
    val numerator = (number.whole.absoluteValue * number.denominator.absoluteValue) - number.numerator.absoluteValue
    return numerator
}

/**
 * return 1 when a mixed numbers whole number can be considered positive and -1 otherwise
 */
private fun signOfWholeNumber(number: MixedNumber): Int {
    return if (number.whole.isPositive()) 1 else -1
}
package model

import org.apache.commons.math3.fraction.Fraction
import org.apache.commons.math3.fraction.FractionFormat
import kotlin.math.absoluteValue

data class MixedNumber(
    val whole: Int,
    val numerator: Int,
    val denominator: Int,
) {
    override fun toString(): String {
        return if (whole == 0 && numerator == 0) {
            whole.toString()
        } else {
            buildString {
                if (whole != 0) append(whole)
                if (numerator != 0 && denominator != 0) {
                    if (whole != 0) append("&")
                    append(
                        if (whole.isNegative() && numerator.isNegative()) numerator.absoluteValue
                        else numerator
                    )
                    append("/")
                    append(denominator)
                }
            }
        }
    }
}

/**
 * Convert a string formatted as <whole>&<numerator>/<denominator> to a mixed number.
 */
fun String.toMixedNumber(): MixedNumber {
    val numbers = split('&', '/').map { it.toInt() }
    return when (numbers.size) {
        1 -> MixedNumber(whole = numbers[0], numerator = 0, denominator = 1)
        2 -> MixedNumber(whole = 0, numerator = numbers[0], denominator = numbers[1])
        else -> MixedNumber(whole = numbers[0], numerator = numbers[1], denominator = numbers[2])
    }
}

/** Convert a mixed number to a fraction */
fun Fraction.toMixedNumber(): MixedNumber {
    val formatted = FractionFormat.getProperInstance().format(this)
    val parts = formatted.split(' ').filter { it != "/" }
    return when (parts.size) {
        3 -> MixedNumber(
            whole = parts[0].toInt(),
            numerator = parts[1].toInt(),
            denominator = parts[2].toInt()
        )
        2 -> MixedNumber(
            whole = 0,
            numerator = parts[0].toInt(),
            denominator = parts[1].toInt()
        )
        else -> MixedNumber(0, 0, 0)
    }
}

/**
 * convert all scenarios of negatives and positives for whole number, numerator, denominator to
 * an improper fraction.
 */
fun MixedNumber.toImproperFraction(): Fraction {
    val hasWhole = whole != 0
    val hasFraction = numerator != 0

    val improperNumerator= if (hasWhole) {
        if (hasFraction) {
            if (whole.isPositive()) {
                if (numerator.isPositive() && denominator.isPositive()) {
                    (whole * denominator) + numerator
                }
                else if (numerator.isPositive() && denominator.isNegative()) {
                    ((whole * denominator.absoluteValue) - numerator) * -1
                }
                else if (numerator.isNegative() && denominator.isPositive()) {
                    (whole * denominator) - numerator.absoluteValue
                }
                else { // numerator.isNegative() && denominator.isNegative())
                    ((whole * denominator.absoluteValue) + numerator.absoluteValue) * -1
                }
            }
            else {
                if (numerator.isPositive() && denominator.isPositive()) {
                    ((whole.absoluteValue * denominator) + numerator) * -1
                }
                else if (numerator.isPositive() && denominator.isNegative()) {
                    (whole.absoluteValue * denominator.absoluteValue) - numerator
                }
                else if (numerator.isNegative() && denominator.isPositive()) {
                    (whole * denominator) + numerator.absoluteValue
                }
                else { // numerator.isNegative() && denominator.isNegative()
                    (whole.absoluteValue * denominator.absoluteValue) + numerator.absoluteValue
                }
            }
        }
        else {
            whole
        }
    }
    else {
        if (numerator.isPositive() && denominator.isPositive()) {
            (whole * denominator) + numerator
        }
        else if (numerator.isPositive() && denominator.isNegative()) {
            (whole * denominator.absoluteValue) - numerator
        }
        else if (numerator.isNegative() && denominator.isPositive()) {
            (whole * denominator) - numerator.absoluteValue
        }
        else { // numerator.isNegative() && denominator.isNegative())
            (whole * denominator.absoluteValue) + numerator.absoluteValue
        }
    }

    return Fraction(
        improperNumerator,
        this.denominator.absoluteValue,
    )
}

/**
 * check if an integer is greater than or equal to 0
 */
fun Int.isPositive() = this > 0

/**
 * check if an integer is less than 0
 */
fun Int.isNegative() = this < 0
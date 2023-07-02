package usecase

import model.MixedNumber
import model.toImproperFraction
import model.toMixedNumber
import org.apache.commons.math3.fraction.Fraction


class CalculationFunction {

    fun add(first: MixedNumber, second: MixedNumber): Fraction {
        val (firstImproper, secondImproper) = toImproper(first, second)
        val result = firstImproper.add(secondImproper)
        println(result.toMixedNumber())
        return result
    }

    fun subtract(first: MixedNumber, second: MixedNumber): Fraction {
        val (firstImproper, secondImproper) = toImproper(first, second)
        val result = firstImproper.subtract(secondImproper)
        println(result.toMixedNumber())
        return result
    }

    fun multiply(first: MixedNumber, second: MixedNumber): Fraction {
        val (firstImproper, secondImproper) = toImproper(first, second)
        val result = firstImproper.multiply(secondImproper)
        println(result.toMixedNumber())
        return result
    }

    fun divide(first: MixedNumber, second: MixedNumber): Fraction {
        val (firstImproper, secondImproper) = toImproper(first, second)
        val result = firstImproper.divide(secondImproper)
        println(result.toMixedNumber())
        return result
    }

    private fun toImproper(
        first: MixedNumber,
        second: MixedNumber
    ): Pair<Fraction, Fraction> {
        return Pair(
            first.toImproperFraction(),
            second.toImproperFraction(),
        )
    }

}
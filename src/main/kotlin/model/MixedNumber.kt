package model

import java.math.BigDecimal

data class MixedNumber(
    val whole: Int = 0,
    val numerator: Int = 0,
    val denominator: Int = 0,
) {
    val decimal: BigDecimal
        get() {
            val numerator = whole.toBigDecimal().multiply(denominator.toBigDecimal())
                .add(numerator.toBigDecimal())
            val denominator = denominator.toBigDecimal()
            return numerator.divide(denominator)
        }
}
/** serializes and deserializes a mixed number */
class NumberSerializer {

    fun deserialize(data: String): MixedNumber {
        val data = data.trim()
        val values = data.split('&', '/').map { it.toInt() }
        return when (values.size) {
            1 -> MixedNumber(values[0], 0, 0)
            2 -> MixedNumber(0, values[0], values[1])
            else -> MixedNumber(values[0], values[1], values[2])
        }
    }

    fun serialize(number: MixedNumber): String {
        return ""
    }

}




//val math =
// "1/2 * 3&3/4"
// "2&3/8 + 9/8"
// "1&3/4 - 2"
// "2/1"

//val firstFractionEnd = math.indexOfFirst { it == ' ' } + 1
//val secondFractionStart = math.indexOfLast { it == ' ' }
//
//val firstFraction = math.substring(
//    startIndex = 0,
//    endIndex = firstFractionEnd,
//)
//
//val secondFraction = math.substring(
//    startIndex = secondFractionStart,
//    endIndex = math.length,
//)
//
//val operation = math.substring(
//    startIndex = firstFractionEnd,
//    endIndex = secondFractionStart,
//).trim()


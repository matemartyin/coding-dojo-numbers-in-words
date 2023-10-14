fun main(args: Array<String>) {
    println(3_121L.display())
    println(999_999_999_999L.display())
    println(124_234_231_230L.display())
    println(2L.display())
    println(10L.display())
    println(29320L.display())
}

fun Long.display(): String {
    val num = this.toString().padStart(12, '0')
    val blocks = num.chunked(3)

    var text = EMPTY
    blocks.forEachIndexed { index, block ->
        text += convertBlock(block)
        if (block.toInt() != 0) text += BLOCK_NAMES[blocks.size - index]
    }
    return text
}

fun convertBlock(block: String): String {
    var text = EMPTY
    block.toCharArray().forEachIndexed{ index, c ->
        val num = c.digitToInt()
        when (index) {
            0 -> text += if (num == 1) HUNDRED else ONES[num]?.let { it + HUNDRED } ?: EMPTY
            1 -> text += when {
                num == 1 && block[2].digitToInt() == 0 -> TEN
                num == 2 && block[2].digitToInt() == 0 -> TWENTY
                else -> TENS[num] ?: EMPTY
            }
            2 -> text += ONES[num] ?: EMPTY
            else -> {}
        }
    }
    return text
}

val EMPTY = ""
val TEN = "tíz"
val TWENTY = "húsz"
val HUNDRED = "száz"
val ONES = mapOf(
    1 to "egy",
    2 to "kettő",
    3 to "három",
    4 to "négy",
    5 to "öt",
    6 to "hat",
    7 to "hét",
    8 to "nyolc",
    9 to "kilenc",
)
val TENS = mapOf(
    1 to "tizen",
    2 to "huszon",
    3 to "harminc",
    4 to "negyven",
    5 to "ötven",
    6 to "hatvan",
    7 to "hetven",
    8 to "nyolcvan",
    9 to "kilencven",
)
val BLOCK_NAMES = mapOf(
    1 to "",
    2 to "ezer ",
    3 to "-millió ",
    4 to "-milliárd ",
)

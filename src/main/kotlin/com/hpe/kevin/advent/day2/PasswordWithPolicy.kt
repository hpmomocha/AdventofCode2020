package com.hpe.kevin.advent.day2

// 1-3 a: abcde
data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
) {
    // In the first part, the password policy indicates the lowest and highest number of times a given letter
    // must appear for the password to be valid.

    // x in range is equivalent to range.first <= x x <= && range.last
    fun validatePartOne() = password.count { it == letter } in range

    // In the second part, the policy describes two positions in the password,
    // where 1 means the first character, 2 means the second character, and so on (indexing starts at 1, not 0).
    // Exactly one of these positions must contain the given letter.
    // Other occurrences of the letter are irrelevant.
    fun validatePartTwo() = (password[range.first - 1] == letter) xor
            (password[range.last - 1] == letter)

    companion object {
        private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
        fun parseUsingStringUtils(line: String) = PasswordWithPolicy(
            password = line.substringAfter(": "),
            letter = line.substringBefore(":").substringAfter(" ").single(),
            range = line.substringBefore(" ").let {
                val (start, end) = it.split("-")
                start.toInt()..end.toInt()
            }
        )

        fun parseUsingRegex(line: String): PasswordWithPolicy =
            regex.matchEntire(line)!!   // !!: 通知编译器不做非空校验。如果运行时发现变量为空，就扔出异常
                .destructured
//                !!.let {
//                    PasswordWithPolicy(
//                        password = it.component4(),
//                        letter = it.component3().single(),
//                        range = it.component1().toInt()..it.component2().toInt()
//
//                    )
//                }
                ?.let { (start, end, letter, password) ->
                    PasswordWithPolicy(
                        password = password,
                        letter = letter.single(),
                        range = start.toInt()..end.toInt()
                    )
                }
    }
}
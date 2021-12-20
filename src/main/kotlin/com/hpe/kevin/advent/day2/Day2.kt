package com.hpe.kevin.advent.day2

import java.io.File

const val INPUT_FILE_PATH = "src/main/kotlin/com/hpe/kevin/advent/day2/input.txt"
fun main() {
    val passwordsPartOne = File(INPUT_FILE_PATH).readLines().map(PasswordWithPolicy::parseUsingStringUtils)
    passwordsPartOne.forEach(::println)
    println(passwordsPartOne.count { it.validatePartOne() })

    val passwordsPartTwo = File(INPUT_FILE_PATH).readLines().map {
        PasswordWithPolicy.parseUsingRegex(it)
    }
    passwordsPartTwo.forEach(::println)
    println(passwordsPartTwo.count { it?.validatePartTwo() })
}
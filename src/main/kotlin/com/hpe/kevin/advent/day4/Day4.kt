package com.hpe.kevin.advent.day4

const val INPUT_FILE_PATH = "src/main/kotlin/com/hpe/kevin/advent/day4/input.txt"

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    println(passports.count(Passport::hasAllRequiredFields))
}

fun partTwo() {
    println(passports2.count { it.hasAllRequiredFields() && it.hasValidValues() })
}
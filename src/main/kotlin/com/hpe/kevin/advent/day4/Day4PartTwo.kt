package com.hpe.kevin.advent.day4

import java.io.File

val passports2 = File(INPUT_FILE_PATH)
    .readText()
    .trim()
    .split("\n\n", "\r\n\r\n")
    .map { Passport2.fromString(it) }

class Passport2(private val map: Map<String, String>) {
    companion object {
        fun fromString(s: String): Passport2 {
            val fieldsAndValues = s.split(" ", "\n", "\r\n")
            val map = fieldsAndValues.associate {
                val (key, value) = it.split(":")
                key to value
            }
            return Passport2(map)
        }
    }

    private val requiredFields = listOf(
        Field.BYR.text,
        Field.IYR.text,
        Field.EYR.text,
        Field.HGT.text,
        Field.HCL.text,
        Field.ECL.text,
        Field.PID.text /*Field.CID*/)

    fun hasValidValues(): Boolean =
        map.all { (key, value) ->
            when (key) {
                Field.BYR.text -> value.length == 4 && value.toIntOrNull() in 1920..2002
                Field.IYR.text -> value.length == 4 && value.toIntOrNull() in 2010..2020
                Field.EYR.text -> value.length == 4 && value.toIntOrNull() in 2020..2030
                Field.PID.text -> value.length == 9 && value.all(Char::isDigit)
                Field.ECL.text -> value in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                Field.HGT.text -> when(value.takeLast(2)) {
                    "cm" -> value.removeSuffix("cm").toIntOrNull() in 150..193
                    "in" -> value.removeSuffix("in").toIntOrNull() in 59..76
                    else -> false
                }
                Field.HCL.text -> value matches Regex("""#[0-9a-f]{6}""")
                else -> true
            }
        }

    fun hasAllRequiredFields(): Boolean {
        return map.keys.containsAll(requiredFields)
    }
}

fun main() {
    println(passports2.count { it.hasAllRequiredFields() && it.hasValidValues() })
}
package com.hpe.kevin.advent.day4

import java.io.File

val passports = File(INPUT_FILE_PATH)
    .readText()
    .trim()
    .split("\n\n", "\r\n\r\n")
    .map { Passport(it) }

class Passport(private val text: String) {
//    private val requiredFields = listOf(
//        "byr",
//        "iyr",
//        "eyr",
//        "hgt",
//        "hcl",
//        "ecl",
//        "pid",
//        //"cid"
//    )

    private val requiredFields = listOf(
        Field.BYR.text,
        Field.IYR.text,
        Field.EYR.text,
        Field.HGT.text,
        Field.HCL.text,
        Field.ECL.text,
        Field.PID.text /*Field.CID*/)


    fun hasAllRequiredFields(): Boolean {
        val fieldWithValue = text.split(" ", "\n", "\r\n")
        val fieldNames = fieldWithValue.map { it.substringBefore(":") }
        return fieldNames.containsAll(requiredFields)
    }
}

fun main() {
    println(passports.count(Passport::hasAllRequiredFields))
}
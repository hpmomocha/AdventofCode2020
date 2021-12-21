package com.hpe.kevin.advent.day5

import java.io.File

const val INPUT_FILE_PATH = "src/main/kotlin/com/hpe/kevin/advent/day5/input.txt"

fun main() {

    val seatIDs = File(INPUT_FILE_PATH).readLines().map(String::toSeatID)
    val maxID = seatIDs.maxOrNull()!!
    println("Max seat ID: $maxID")

    val occupiedSeatsSet = seatIDs.toSet()
    fun isOccupied(seat: Int) = seat in occupiedSeatsSet

    val mySeat = (1..maxID).find { index ->
        !isOccupied(index) && isOccupied(index - 1) && isOccupied(index + 1)
    }
    println("My seat ID: $mySeat")
}

fun String.toSeatID(): Int = this
    .replace("B", "1").replace("F", "0")
    .replace("R", "1").replace("L", "0")
    .toInt(radix = 2)
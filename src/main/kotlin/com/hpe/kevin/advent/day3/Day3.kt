package com.hpe.kevin.advent.day3

import java.io.File

const val INPUT_FILE_PATH = "src/main/kotlin/com/hpe/kevin/advent/day3/input.txt"

fun slove(maps: List<String>, pair: Pair<Int, Int>): Int {
    val (dx, dy) = pair
    val width = maps[0].length
    val trees = maps.indices.count { idx ->
        idx % dy == 0 && maps[idx][idx / dy * dx % width] == '#'
    }
    return trees
}

fun main() {
    val maps = File(INPUT_FILE_PATH).readLines()

    val trees = slove(maps, 3 to 1)
    println(trees)

    val vectors = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
    val result = vectors.map { slove(maps, it).toBigInteger() }.reduce { a, b -> a * b }
    println(result)

//    val resultFromFold = vectors.map { slove(maps, it).toBigInteger() }.fold("1".toBigInteger()) { a, b-> a * b }
//    println(resultFromFold)
}

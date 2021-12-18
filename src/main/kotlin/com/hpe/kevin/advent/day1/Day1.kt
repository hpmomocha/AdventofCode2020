package com.hpe.kevin.advent.day1

/*
--- Day 1 ---
两数之和
三数之和

 */

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
    // map sum - x -> x
    // associateBy: 需要一个函数，以该函数根据元素的值返回为键，以集合元素作为值，构建Map
    val complements = associateBy { sum - it }

    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement -> Pair(number, complement) }
    }
}

fun List<Int>.findTripeOfSum(sum: Int): Triple<Int, Int, Int>? =
    firstNotNullOfOrNull { x ->
        findPairOfSum(sum - x)?.let {
            Triple(x, it.first, it.second)
        }
}

fun main() {
    println("Please input the sum:")
    val sum = readLine()!!.toInt()

    println("Please input the number list (press [Ctrl] + [D] to end the input):")
    val numbers = generateSequence { readLine() }.toList().map { it.toInt() }

    val pair = numbers.findPairOfSum(sum)
    println(pair?.let { (x, y) -> x * y })

//    // 打印所有的组合，但是因为符合条件的三个数会形成三个相同的组合，所以我们只需要打印第一个
//    // associateWith: 以原始集合的元素为键，并通过给定的转换函数的返回值为值，构建Map
//    val complementPairs: Map<Int, Pair<Int, Int>?> = numbers.associateWith { x ->
//        numbers.findPairOfSum(2020 - x)
//    }
//    println(complementPairs)

    // map x -> (y, z)
    val triple = numbers.findTripeOfSum(sum)
    println(triple?.let { (x, y, z) -> x * y * z })
}

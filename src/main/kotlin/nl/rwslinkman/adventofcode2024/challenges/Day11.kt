package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day11/input.txt")
object Day11 : AdventChallenge {

    override fun part1(inputString: String): Any {
        val initialStones = inputString.split(" ")

        var sortedStones = initialStones.toMutableList()
        for (iteration in 0..<25) {
            val tmp = mutableListOf<String>()
            sortedStones.forEach { stone ->
                val newStones = applyStoneRules(stone)
                tmp.addAll(newStones)
            }
            sortedStones = tmp
        }
        return sortedStones.size
    }

    private fun applyStoneRules(stone: String): List<String> {
        return if (stone == "0") {
            // Replace
            listOf("1")
        } else if (stone.length % 2 == 0) {
            // Split
            val left = stone.substring(0, stone.length / 2).toLong()
            val right = stone.substring(stone.length / 2).toLong()
            listOf("$left", "$right")
        } else {
            // Multiply
            val stoneValue = stone.toLong() * 2024
            listOf("$stoneValue")
        }
    }

    override fun part2(inputString: String): Any {
        val initialStones = inputString.split(" ")

        return initialStones.sumOf { stone ->
            iterate(stone, 75, mutableMapOf())
        }
    }

    private fun iterate(stone: String, blinks: Int, cache: MutableMap<Pair<String, Int>, Long>): Long {
        if (blinks == 0) return 1

        val key = Pair(stone, blinks)
        if (cache.contains(key)) {
            return cache[key]!!
        }

        val tmp = applyStoneRules(stone)
        var result = iterate(tmp[0], blinks - 1, cache)
        if (tmp.size == 2) {
            result += iterate(tmp[1], blinks - 1, cache)
        }

        cache[key] = result
        return result
    }
}
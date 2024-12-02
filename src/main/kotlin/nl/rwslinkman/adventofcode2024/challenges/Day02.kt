package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import kotlin.math.abs

@Puzzle("day02/input.txt")
object Day02: AdventChallenge {

    override fun part1(inputString: String): Any {
        val levels = inputString.lines().map {
            it.split(" ").map { n -> n.toInt() }
        }

        return levels.count { level ->
            level.zipWithNext { l, r -> abs(l -  r) }.all { z -> z in 1..3 }
            && (level.isOnlyIncreasing() || level.isOnlyDecreasing())
        }
    }

    override fun part2(inputString: String): Any {
        val levels = inputString.lines().map {
            it.split(" ").map { n -> n.toInt() }
        }

        return levels.map {
            createSubsets(it).any { level ->
                level.zipWithNext { l, r -> abs(l -  r) }.all { z -> z in 1..3 }
                        && (level.isOnlyIncreasing() || level.isOnlyDecreasing())
            }
        }.count { it }
    }

    private fun List<Int>.isOnlyIncreasing(): Boolean = this.zipWithNext().all { (a, b) ->  a < b }
    private fun List<Int>.isOnlyDecreasing(): Boolean = this.zipWithNext().all { (a, b) ->  a > b }

    private fun createSubsets(nums: List<Int>): List<List<Int>> {
        if (nums.size <= 1) return listOf(nums)
        val result = mutableListOf<List<Int>>()

        for (i in nums.indices) {
            val remaining = nums.toMutableList().apply { removeAt(i) }
            result.add(remaining)
        }
        return result
    }
}
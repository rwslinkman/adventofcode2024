package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day07/input.txt")
object Day07: AdventChallenge {

    override fun part1(inputString: String): Any {
        val equations: Map<Long, List<Long>> = inputString.lines().associate {
            val lineParts = it.split(": ")
            val key = lineParts[0].toLong()
            val values = lineParts[1].split(" ").map { v -> v.toLong() }
            Pair(key, values)
        }

        var total = 0L
        equations.forEach { (result, numbers) ->
            if(isValidCalculation(0, numbers, 0, result)) {
                total += result
            }
        }
        return total
    }

    private fun isValidCalculation(index: Int, numbers: List<Long>, tmp: Long, target: Long): Boolean {
        if (index == numbers.size || tmp > target) {
            return tmp == target
        }
        val newAdd = tmp + numbers[index]
        val newMul = tmp * numbers[index]
        return isValidCalculation(index + 1, numbers, newAdd, target) || isValidCalculation(index + 1, numbers, newMul, target)
    }

    override fun part2(inputString: String): Any {
        val equations: Map<Long, List<Long>> = inputString.lines().associate {
            val lineParts = it.split(": ")
            val key = lineParts[0].toLong()
            val values = lineParts[1].split(" ").map { v -> v.toLong() }
            Pair(key, values)
        }

        var total = 0L
        equations.forEach { (result, numbers) ->
            if(isValidCalculationAllowingConcat(0, numbers, 0, result)) {
                total += result
            }
        }
        return total
    }

    private fun isValidCalculationAllowingConcat(index: Int, numbers: List<Long>, tmp: Long, target: Long): Boolean {
        if (index == numbers.size || tmp > target) {
            return tmp == target
        }
        val num = numbers[index]
        val newAdd = tmp + num
        val newMul = tmp * num
        val newConcat = "$tmp$num".toLong()
        return isValidCalculationAllowingConcat(index + 1, numbers, newAdd, target) ||
                isValidCalculationAllowingConcat(index + 1, numbers, newMul, target) ||
                isValidCalculationAllowingConcat(index + 1, numbers, newConcat, target)
    }
}
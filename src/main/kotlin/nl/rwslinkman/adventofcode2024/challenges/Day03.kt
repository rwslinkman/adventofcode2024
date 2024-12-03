package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day03/input.txt")
object Day03: AdventChallenge {

    override fun part1(inputString: String): Any {
        val mulRegex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\))")
        val numRegex = Regex("(\\d{1,3}),(\\d{1,3})")

        return mulRegex.findAll(inputString).toList().sumOf {
            val (left, right) = numRegex.find(it.value)!!.destructured
            left.toInt() * right.toInt()
        }
    }

    override fun part2(inputString: String): Any {
        val mulRegex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\))")
        val numRegex = Regex("(\\d{1,3}),(\\d{1,3})")

        var isEnabled = true
        var lastPos = 0
        return mulRegex.findAll(inputString).toList().sumOf {
            val previousSection = inputString.subSequence(lastPos..it.range.first)
            if(previousSection.contains("do()")) {
                isEnabled = true
            } else if (previousSection.contains("don't()")) {
                isEnabled = false
            }

            lastPos = it.range.last()
            if(isEnabled) {
                val (left, right) = numRegex.find(it.value)!!.destructured
                left.toInt() * right.toInt()
            } else {
                0
            }
        }
    }
}
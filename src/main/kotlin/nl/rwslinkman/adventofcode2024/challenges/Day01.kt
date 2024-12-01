package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import kotlin.math.abs

@Puzzle("day01/input.txt")
object Day01: AdventChallenge {

    override fun part1(inputString: String): Any {
        val listLeft = mutableListOf<Long>()
        val listRight = mutableListOf<Long>()
        val splitter = "(\\d+) *(\\d+)".toRegex()

        inputString.lines().forEach {
            val (left, right) = splitter.find(it)!!.destructured
            listLeft.add(left.toLong())
            listRight.add(right.toLong())
        }

        listLeft.sort()
        listRight.sort()
        val pairs = listLeft.zip(listRight)
        return pairs.sumOf { (left, right) ->
            abs(left - right)
        }
    }

    override fun part2(inputString: String): Any {
        val listLeft = mutableListOf<Long>()
        val listRight = mutableListOf<Long>()
        val splitter = "(\\d+) *(\\d+)".toRegex()

        inputString.lines().forEach {
            val (left, right) = splitter.find(it)!!.destructured
            listLeft.add(left.toLong())
            listRight.add(right.toLong())
        }

        val resultList = listLeft.map { itemLeft ->
            val numCount = listRight.count { itemRight -> itemRight == itemLeft}
            itemLeft * numCount
        }
        return resultList.sum()
    }
}
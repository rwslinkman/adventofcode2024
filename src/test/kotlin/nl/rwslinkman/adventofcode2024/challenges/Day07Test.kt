package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day07Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "190: 10 19\n" +
            "3267: 81 40 27\n" +
            "83: 17 5\n" +
            "156: 15 6\n" +
            "7290: 6 8 6 15\n" +
            "161011: 16 10 13\n" +
            "192: 17 8 14\n" +
            "21037: 9 7 18 13\n" +
            "292: 11 6 16 20"

        val result = Day07.part1(exampleData)

        assertEquals(3749L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "190: 10 19\n" +
            "3267: 81 40 27\n" +
            "83: 17 5\n" +
            "156: 15 6\n" +
            "7290: 6 8 6 15\n" +
            "161011: 16 10 13\n" +
            "192: 17 8 14\n" +
            "21037: 9 7 18 13\n" +
            "292: 11 6 16 20"

        val result = Day07.part2(exampleData)

        assertEquals(11387L, result)
    }
}
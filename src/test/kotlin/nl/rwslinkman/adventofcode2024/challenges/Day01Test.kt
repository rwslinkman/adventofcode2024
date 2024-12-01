package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {


    @Test
    fun `example part 1`() {
        val exampleData =
            "3   4\n" +
            "4   3\n" +
            "2   5\n" +
            "1   3\n" +
            "3   9\n" +
            "3   3"

        val result = Day01.part1(exampleData)

        assertEquals(11L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "3   4\n" +
                    "4   3\n" +
                    "2   5\n" +
                    "1   3\n" +
                    "3   9\n" +
                    "3   3"

        val result = Day01.part2(exampleData)

        assertEquals(31L, result)
    }
}
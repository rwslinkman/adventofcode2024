package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "7 6 4 2 1\n" +
            "1 2 7 8 9\n" +
            "9 7 6 2 1\n" +
            "1 3 2 4 5\n" +
            "8 6 4 4 1\n" +
            "1 3 6 7 9"

        val result = Day02.part1(exampleData)

        assertEquals(2, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "7 6 4 2 1\n" +
            "1 2 7 8 9\n" +
            "9 7 6 2 1\n" +
            "1 3 2 4 5\n" +
            "8 6 4 4 1\n" +
            "1 3 6 7 9"

        val result = Day02.part2(exampleData)

        assertEquals(4, result)
    }
}
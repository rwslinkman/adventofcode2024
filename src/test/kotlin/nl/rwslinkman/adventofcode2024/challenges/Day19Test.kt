package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "r, wr, b, g, bwu, rb, gb, br\n" +
            "\n" +
            "brwrr\n" +
            "bggr\n" +
            "gbbr\n" +
            "rrbgbr\n" +
            "ubwu\n" +
            "bwurrg\n" +
            "brgr\n" +
            "bbrgwb"

        val result = Day19.part1(exampleData)

        assertEquals(6, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "r, wr, b, g, bwu, rb, gb, br\n" +
            "\n" +
            "brwrr\n" +
            "bggr\n" +
            "gbbr\n" +
            "rrbgbr\n" +
            "ubwu\n" +
            "bwurrg\n" +
            "brgr\n" +
            "bbrgwb"

        val result = Day19.part2(exampleData)

        assertEquals(16L, result)
    }
}
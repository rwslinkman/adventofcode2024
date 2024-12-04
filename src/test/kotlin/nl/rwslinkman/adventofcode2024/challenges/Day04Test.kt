package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX"

        val result = Day04.part1(exampleData)

        assertEquals(18, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX"

        val result = Day04.part2(exampleData)

        assertEquals(9, result)
    }
}
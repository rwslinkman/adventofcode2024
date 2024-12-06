package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "....#.....\n" +
            ".........#\n" +
            "..........\n" +
            "..#.......\n" +
            ".......#..\n" +
            "..........\n" +
            ".#..^.....\n" +
            "........#.\n" +
            "#.........\n" +
            "......#..."

        val result = Day06.part1(exampleData)

        assertEquals(41, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "....#.....\n" +
            ".........#\n" +
            "..........\n" +
            "..#.......\n" +
            ".......#..\n" +
            "..........\n" +
            ".#..^.....\n" +
            "........#.\n" +
            "#.........\n" +
            "......#..."

        val result = Day06.part2(exampleData)

        assertEquals(6, result)
    }
}
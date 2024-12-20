package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "###############\n" +
            "#...#...#.....#\n" +
            "#.#.#.#.#.###.#\n" +
            "#S#...#.#.#...#\n" +
            "#######.#.#.###\n" +
            "#######.#.#...#\n" +
            "#######.#.###.#\n" +
            "###..E#...#...#\n" +
            "###.#######.###\n" +
            "#...###...#...#\n" +
            "#.#####.#.###.#\n" +
            "#.#...#.#.#...#\n" +
            "#.#.#.#.#.#.###\n" +
            "#...#...#...###\n" +
            "###############"

        val result = Day20.part1(exampleData, desiredProfit = 64)

        assertEquals(1, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "###############\n" +
            "#...#...#.....#\n" +
            "#.#.#.#.#.###.#\n" +
            "#S#...#.#.#...#\n" +
            "#######.#.#.###\n" +
            "#######.#.#...#\n" +
            "#######.#.###.#\n" +
            "###..E#...#...#\n" +
            "###.#######.###\n" +
            "#...###...#...#\n" +
            "#.#####.#.###.#\n" +
            "#.#...#.#.#...#\n" +
            "#.#.#.#.#.#.###\n" +
            "#...#...#...###\n" +
            "###############"

        val result = Day20.part2(exampleData, desiredProfit = 76)

        assertEquals(3, result)
    }

}
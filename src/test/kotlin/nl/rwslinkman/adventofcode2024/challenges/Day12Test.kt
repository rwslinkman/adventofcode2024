package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `small example from part 1`() {
        val exampleData =
            "AAAA\n" +
            "BBCD\n" +
            "BBCC\n" +
            "EEEC"

        val result = Day12.part1(exampleData)

        assertEquals(140, result)
    }

    @Test
    fun `large example from part 1`() {
        val exampleData =
            "RRRRIICCFF\n" +
            "RRRRIICCCF\n" +
            "VVRRRCCFFF\n" +
            "VVRCCCJFFF\n" +
            "VVVVCJJCFE\n" +
            "VVIVCCJJEE\n" +
            "VVIIICJJEE\n" +
            "MIIIIIJJEE\n" +
            "MIIISIJEEE\n" +
            "MMMISSJEEE"

        val result = Day12.part1(exampleData)

        assertEquals(1930, result)
    }

    @Test
    fun `small example from part 2`() {
        val exampleData =
            "AAAA\n" +
            "BBCD\n" +
            "BBCC\n" +
            "EEEC"

        val result = Day12.part2(exampleData)

        assertEquals(80, result)
    }

    @Test
    fun `large example from part 2`() {
        val exampleData =
            "RRRRIICCFF\n" +
            "RRRRIICCCF\n" +
            "VVRRRCCFFF\n" +
            "VVRCCCJFFF\n" +
            "VVVVCJJCFE\n" +
            "VVIVCCJJEE\n" +
            "VVIIICJJEE\n" +
            "MIIIIIJJEE\n" +
            "MIIISIJEEE\n" +
            "MMMISSJEEE"

        val result = Day12.part2(exampleData)

        assertEquals(1206, result)
    }
}
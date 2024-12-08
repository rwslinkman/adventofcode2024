package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "............\n" +
            "........0...\n" +
            ".....0......\n" +
            ".......0....\n" +
            "....0.......\n" +
            "......A.....\n" +
            "............\n" +
            "............\n" +
            "........A...\n" +
            ".........A..\n" +
            "............\n" +
            "............"

        val result = Day08.part1(exampleData)

        assertEquals(14, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "............\n" +
            "........0...\n" +
            ".....0......\n" +
            ".......0....\n" +
            "....0.......\n" +
            "......A.....\n" +
            "............\n" +
            "............\n" +
            "........A...\n" +
            ".........A..\n" +
            "............\n" +
            "............"

        val result = Day08.part2(exampleData)

        assertEquals(34, result)
    }
}
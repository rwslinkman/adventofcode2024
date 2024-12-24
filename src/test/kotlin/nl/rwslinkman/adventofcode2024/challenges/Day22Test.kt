package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "1\n" +
            "10\n" +
            "100\n" +
            "2024"

        val result = Day22.part1(exampleData)

        assertEquals(37_327_623L, result)
    }

    @Test
    fun `example part 1 - mix`() {
        val result = Day22.mix(15, 42)
        assertEquals(37, result)
    }

    @Test
    fun `example part 1 - prune`() {
        val result = Day22.prune(100000000)
        assertEquals(16113920, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "1\n" +
            "2\n" +
            "3\n" +
            "2024"

        val result = Day22.part2(exampleData)

        assertEquals(23L, result)
    }
}
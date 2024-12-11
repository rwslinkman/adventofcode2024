package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `example part 1`() {
        val exampleData = "125 17"

        val result = Day11.part1(exampleData)

        assertEquals(55312, result)
    }
}
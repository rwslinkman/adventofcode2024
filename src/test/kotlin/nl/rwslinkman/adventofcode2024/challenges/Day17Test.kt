package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day17Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "Register A: 729\n" +
            "Register B: 0\n" +
            "Register C: 0\n" +
            "\n" +
            "Program: 0,1,5,4,3,0"

        val result = Day17.part1(exampleData)

        assertEquals("4,6,3,5,6,3,5,2,1,0", result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "Register A: 2024\n" +
            "Register B: 0\n" +
            "Register C: 0\n" +
            "\n" +
            "Program: 0,3,5,4,3,0"

        val result = Day17.part2(exampleData)

        assertEquals(117440L, result)
    }
}
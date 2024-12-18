package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun `example part 1`(){
        val exampleData =
            "5,4\n" +
            "4,2\n" +
            "4,5\n" +
            "3,0\n" +
            "2,1\n" +
            "6,3\n" +
            "2,4\n" +
            "1,5\n" +
            "0,6\n" +
            "3,3\n" +
            "2,6\n" +
            "5,1\n" +
            "1,2\n" +
            "5,5\n" +
            "2,5\n" +
            "6,5\n" +
            "1,4\n" +
            "0,4\n" +
            "6,4\n" +
            "1,1\n" +
            "6,1\n" +
            "1,0\n" +
            "0,5\n" +
            "1,6\n" +
            "2,0"

        val result = Day18.part1(exampleData, height = 6, width = 6, simulateBytes = 12)

        assertEquals(22, result)
    }

    @Test
    fun `example part 2`(){
        val exampleData =
            "5,4\n" +
            "4,2\n" +
            "4,5\n" +
            "3,0\n" +
            "2,1\n" +
            "6,3\n" +
            "2,4\n" +
            "1,5\n" +
            "0,6\n" +
            "3,3\n" +
            "2,6\n" +
            "5,1\n" +
            "1,2\n" +
            "5,5\n" +
            "2,5\n" +
            "6,5\n" +
            "1,4\n" +
            "0,4\n" +
            "6,4\n" +
            "1,1\n" +
            "6,1\n" +
            "1,0\n" +
            "0,5\n" +
            "1,6\n" +
            "2,0"

        val result = Day18.part2(exampleData, height = 6, width = 6)

        assertEquals("6,1", result)
    }
}
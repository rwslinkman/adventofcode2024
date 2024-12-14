package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "p=0,4 v=3,-3\n" +
            "p=6,3 v=-1,-3\n" +
            "p=10,3 v=-1,2\n" +
            "p=2,0 v=2,-1\n" +
            "p=0,0 v=1,3\n" +
            "p=3,0 v=-2,-2\n" +
            "p=7,6 v=-1,-3\n" +
            "p=3,0 v=-1,-2\n" +
            "p=9,3 v=2,3\n" +
            "p=7,3 v=-1,2\n" +
            "p=2,4 v=2,-3\n" +
            "p=9,5 v=-3,-3"

        val result = Day14.part1(exampleData, width = 11, height = 7)

        assertEquals(12, result)
    }
}
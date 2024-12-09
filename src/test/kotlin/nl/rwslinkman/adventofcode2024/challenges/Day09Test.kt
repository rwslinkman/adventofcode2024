package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun `example part 1`() {
        val exampleData = "2333133121414131402"

        val result = Day09.part1(exampleData)

        assertEquals(1928L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "2333133121414131402"

        val result = Day09.part2(exampleData)

        assertEquals(2858L, result)
    }
}
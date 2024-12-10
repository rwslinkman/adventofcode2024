package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Teste {

    @Test
    fun `example part 1`() {
        val exampleData =
            "89010123\n" +
            "78121874\n" +
            "87430965\n" +
            "96549874\n" +
            "45678903\n" +
            "32019012\n" +
            "01329801\n" +
            "10456732"

        val result = Day10.part1(exampleData)

        assertEquals(36, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "89010123\n" +
            "78121874\n" +
            "87430965\n" +
            "96549874\n" +
            "45678903\n" +
            "32019012\n" +
            "01329801\n" +
            "10456732"

        val result = Day10.part2(exampleData)

        assertEquals(81, result)
    }
}
package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class Day03Test {

    @Test
    fun `example part 1`() {
        val exampleData = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n"

        val result = Day03.part1(exampleData)

        assertEquals(161, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        val result = Day03.part2(exampleData)

        assertEquals(48, result)
    }
}
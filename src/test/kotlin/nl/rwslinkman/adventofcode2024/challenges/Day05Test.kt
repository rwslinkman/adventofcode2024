package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "47|53\n" +
            "97|13\n" +
            "97|61\n" +
            "97|47\n" +
            "75|29\n" +
            "61|13\n" +
            "75|53\n" +
            "29|13\n" +
            "97|29\n" +
            "53|29\n" +
            "61|53\n" +
            "97|53\n" +
            "61|29\n" +
            "47|13\n" +
            "75|47\n" +
            "97|75\n" +
            "47|61\n" +
            "75|61\n" +
            "47|29\n" +
            "75|13\n" +
            "53|13\n" +
            "\n" +
            "75,47,61,53,29\n" +
            "97,61,53,29,13\n" +
            "75,29,13\n" +
            "75,97,47,61,53\n" +
            "61,13,29\n" +
            "97,13,75,29,47"

        val result = Day05.part1(exampleData)

        assertEquals(143, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "47|53\n" +
            "97|13\n" +
            "97|61\n" +
            "97|47\n" +
            "75|29\n" +
            "61|13\n" +
            "75|53\n" +
            "29|13\n" +
            "97|29\n" +
            "53|29\n" +
            "61|53\n" +
            "97|53\n" +
            "61|29\n" +
            "47|13\n" +
            "75|47\n" +
            "97|75\n" +
            "47|61\n" +
            "75|61\n" +
            "47|29\n" +
            "75|13\n" +
            "53|13\n" +
            "\n" +
            "75,47,61,53,29\n" +
            "97,61,53,29,13\n" +
            "75,29,13\n" +
            "75,97,47,61,53\n" +
            "61,13,29\n" +
            "97,13,75,29,47"

        val result = Day05.part2(exampleData)

        assertEquals(123, result)
    }
}
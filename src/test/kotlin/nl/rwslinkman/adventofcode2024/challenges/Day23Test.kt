package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day23Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "kh-tc\n" +
            "qp-kh\n" +
            "de-cg\n" +
            "ka-co\n" +
            "yn-aq\n" +
            "qp-ub\n" +
            "cg-tb\n" +
            "vc-aq\n" +
            "tb-ka\n" +
            "wh-tc\n" +
            "yn-cg\n" +
            "kh-ub\n" +
            "ta-co\n" +
            "de-co\n" +
            "tc-td\n" +
            "tb-wq\n" +
            "wh-td\n" +
            "ta-ka\n" +
            "td-qp\n" +
            "aq-cg\n" +
            "wq-ub\n" +
            "ub-vc\n" +
            "de-ta\n" +
            "wq-aq\n" +
            "wq-vc\n" +
            "wh-yn\n" +
            "ka-de\n" +
            "kh-ta\n" +
            "co-tc\n" +
            "wh-qp\n" +
            "tb-vc\n" +
            "td-yn"

        val result = Day23.part1(exampleData)

        assertEquals(result, 7)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "kh-tc\n" +
            "qp-kh\n" +
            "de-cg\n" +
            "ka-co\n" +
            "yn-aq\n" +
            "qp-ub\n" +
            "cg-tb\n" +
            "vc-aq\n" +
            "tb-ka\n" +
            "wh-tc\n" +
            "yn-cg\n" +
            "kh-ub\n" +
            "ta-co\n" +
            "de-co\n" +
            "tc-td\n" +
            "tb-wq\n" +
            "wh-td\n" +
            "ta-ka\n" +
            "td-qp\n" +
            "aq-cg\n" +
            "wq-ub\n" +
            "ub-vc\n" +
            "de-ta\n" +
            "wq-aq\n" +
            "wq-vc\n" +
            "wh-yn\n" +
            "ka-de\n" +
            "kh-ta\n" +
            "co-tc\n" +
            "wh-qp\n" +
            "tb-vc\n" +
            "td-yn"

        val result = Day23.part2(exampleData)

        assertEquals("co,de,ka,ta", result)
    }
}
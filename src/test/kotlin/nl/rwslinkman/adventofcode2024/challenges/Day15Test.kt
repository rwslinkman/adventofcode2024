package nl.rwslinkman.adventofcode2024.challenges

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "##########\n" +
            "#..O..O.O#\n" +
            "#......O.#\n" +
            "#.OO..O.O#\n" +
            "#..O@..O.#\n" +
            "#O#..O...#\n" +
            "#O..O..O.#\n" +
            "#.OO.O.OO#\n" +
            "#....O...#\n" +
            "##########\n" +
            "\n" +
            "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\n" +
            "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\n" +
            "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\n" +
            "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\n" +
            "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\n" +
            "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\n" +
            ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\n" +
            "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\n" +
            "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\n" +
            "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^"

        val result = Day15.part1(exampleData)

        assertEquals(10092, result)
    }

    @Test
    fun `small example part 1`() {
        val exampleData =
            "########\n" +
            "#..O.O.#\n" +
            "##@.O..#\n" +
            "#...O..#\n" +
            "#.#.O..#\n" +
            "#...O..#\n" +
            "#......#\n" +
            "########\n" +
            "\n" +
            "<^^>>>vv<v>>v<<"

        val result = Day15.part1(exampleData)

        assertEquals(2028, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "##########\n" +
            "#..O..O.O#\n" +
            "#......O.#\n" +
            "#.OO..O.O#\n" +
            "#..O@..O.#\n" +
            "#O#..O...#\n" +
            "#O..O..O.#\n" +
            "#.OO.O.OO#\n" +
            "#....O...#\n" +
            "##########\n" +
            "\n" +
            "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\n" +
            "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\n" +
            "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\n" +
            "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\n" +
            "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\n" +
            "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\n" +
            ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\n" +
            "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\n" +
            "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\n" +
            "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^"

        val result = Day15.part2(exampleData)

        assertEquals(9021, result)
    }

    @Test
    fun `small example part 2`() {
        val exampleData =
            "#######\n" +
            "#...#.#\n" +
            "#.....#\n" +
            "#..OO@#\n" +
            "#..O..#\n" +
            "#.....#\n" +
            "#######\n" +
            "\n" +
            "<vv<<^^<<^^"

        val result = Day15.part2(exampleData) as Int

        assertTrue(result > 0)
    }
}
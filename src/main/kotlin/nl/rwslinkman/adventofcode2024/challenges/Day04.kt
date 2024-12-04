package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day04/input.txt")
object Day04 : AdventChallenge {

    override fun part1(inputString: String): Any {
        val letterRows: List<List<Char>> = inputString.lines().map { it.toList() }
        var validCount = 0

        letterRows.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if (cell == 'X') {
                    for (direction in Direction.entries) {
                        val neighborM = findNeighbor(letterRows, x, y, direction, 1)
                        val neighborA = findNeighbor(letterRows, x, y, direction, 2)
                        val neighborS = findNeighbor(letterRows, x, y, direction, 3)

                        if ('M' == neighborM && 'A' == neighborA && 'S' == neighborS) {
                            validCount++
                        }
                    }
                }
            }
        }
        return validCount
    }

    enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        LEFT_UP(-1, -1),
        LEFT_DOWN(-1, 1),
        RIGHT_UP(1, -1),
        RIGHT_DOWN(1, 1);
    }

    private fun findNeighbor(letters: List<List<Char>>, x: Int, y: Int, direction: Direction, level: Int = 1): Char? {
        val nx = x + (direction.dx * level)
        val ny = y + (direction.dy * level)
        return try {
            letters[ny][nx]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    override fun part2(inputString: String): Any {
        val letterRows: List<List<Char>> = inputString.lines().map { it.toList() }
        var validCount = 0

        letterRows.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if(cell == 'A') {
                    // Possible center point found
                    val lu = findNeighbor(letterRows, x, y, Direction.LEFT_UP)
                    val ru = findNeighbor(letterRows, x, y, Direction.RIGHT_UP)
                    val ld = findNeighbor(letterRows, x, y, Direction.LEFT_DOWN)
                    val rd = findNeighbor(letterRows, x, y, Direction.RIGHT_DOWN)

                    if(lu != null && ru != null && ld != null && rd != null) {
                        val diag1 = "${lu}A${rd}"
                        val diag2 = "${ru}A${ld}"
                        if(diag1 == "MAS" && diag2 == "MAS") {
                            validCount++
                        } else if (diag1 == "SAM" && diag2 == "MAS") {
                            validCount++
                        } else if(diag1 == "MAS" && diag2 == "SAM") {
                            validCount++
                        } else if(diag1 == "SAM" && diag2 == "SAM") {
                            validCount++
                        }
                    }
                }
            }
        }
        return validCount
    }
}
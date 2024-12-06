package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day06/input.txt")
object Day06: AdventChallenge {

    override fun part1(inputString: String): Any {
        val floorplan = inputString.lines().map { it.toList() }

        var guardX = 0
        var guardY = 0
        var guardDir = Direction.NORTH
        floorplan.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if(cell == START) {
                    guardX = x
                    guardY = y
                }
            }
        }

        val visited = mutableSetOf(Pair(guardX, guardY))
        while(true) {
            val nextPos = findNeighbor(floorplan, guardX, guardY, guardDir) ?: break

            if(OBSTRUCTION == nextPos) {
                guardDir = guardDir.turnRight()
            } else {
                guardX += guardDir.dx
                guardY += guardDir.dy
                visited.add(Pair(guardX, guardY))
            }
        }
        return visited.size
    }

    private const val START: Char = '^'
    private const val OBSTRUCTION: Char = '#'

    private enum class Direction(val dx: Int, val dy: Int) {
        NORTH(0, -1),
        EAST(1,0),
        WEST(-1, 0),
        SOUTH(0, 1);

        fun turnRight(): Direction = when(this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    private fun findNeighbor(floorplan: List<List<Char>>, x: Int, y: Int, direction: Direction): Char? {
        val nx = x + direction.dx
        val ny = y + direction.dy
        return try {
            return floorplan[ny][nx]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    override fun part2(inputString: String): Any {
        val floorplan = inputString.lines().map { it.toList() }

        var guardX = 0
        var guardY = 0
        floorplan.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if(cell == START) {
                    guardX = x
                    guardY = y
                }
            }
        }

        var total = 0
        floorplan.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if(cell == OBSTRUCTION || (x == guardX && y == guardY)) return@forEachIndexed

                val isValid = simulationWithObstructionContainsLoop(floorplan, x, y, guardX, guardY)
                if (isValid) {
                    total++
                }
            }
        }
        return total
    }

    private fun simulationWithObstructionContainsLoop(
        floorplan: List<List<Char>>,
        extraObstX: Int,
        extraObstY: Int,
        guardX: Int,
        guardY: Int
    ): Boolean {
        var simGuardX = guardX
        var simGuardY = guardY
        var simGuardDir = Direction.NORTH
        val visited = mutableSetOf("${simGuardX},${simGuardY},${simGuardDir}")
        while(true) {
            val nextPos = findNeighbor(floorplan, simGuardX, simGuardY, simGuardDir) ?: break

            val nextCell = if(extraObstX == simGuardX + simGuardDir.dx && extraObstY == simGuardY + simGuardDir.dy) {
                OBSTRUCTION
            } else nextPos

            if(OBSTRUCTION == nextCell) {
                simGuardDir = simGuardDir.turnRight()
            } else {
                simGuardX += simGuardDir.dx
                simGuardY += simGuardDir.dy
            }

            val visit = "${simGuardX},${simGuardY},${simGuardDir}"
            if(visited.contains(visit)) {
                return true
            }
            visited.add(visit)
        }
        return false
    }
}
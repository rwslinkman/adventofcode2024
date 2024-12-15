package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day15/input.txt")
object Day15 : AdventChallenge {

    private const val ROBOT = '@'
    private const val WALL = '#'
    private const val BOX = 'O'
    private const val FREE_SPACE = '.'

    override fun part1(inputString: String): Any {
        val (map, instructions) = parseInput(inputString)
        var (robotX, robotY) = findRobotPosition(map)

        while (instructions.isNotEmpty()) {
            val nextMove = instructions.removeFirst()

            val nextRobotX = robotX + nextMove.dx
            val nextRobotY = robotY + nextMove.dy
            val neighbour = map[nextRobotY][nextRobotX]
            when (neighbour) {
                BOX -> {
                    var boxesToPush = 0
                    while (true) {
                        val behindBoxX = nextRobotX + boxesToPush * nextMove.dx
                        val behindBoxY = nextRobotY + boxesToPush * nextMove.dy

                        when (map[behindBoxY][behindBoxX]) {
                            BOX -> boxesToPush++
                            WALL -> break
                            FREE_SPACE -> break
                        }
                    }
                    var freeSpaces = 0
                    for (step in 1..boxesToPush) {
                        val nextX = nextRobotX + step * nextMove.dx
                        val nextY = nextRobotY + step * nextMove.dy
                        if (map[nextY][nextX] == FREE_SPACE) {
                            freeSpaces++
                        }
                    }

                    if (freeSpaces > 0) {
                        for (step in 1..boxesToPush) {
                            val nextX = nextRobotX + step * nextMove.dx
                            val nextY = nextRobotY + step * nextMove.dy
                            map[nextY][nextX] = BOX
                        }
                        map[robotY][robotX] = FREE_SPACE
                        map[nextRobotY][nextRobotX] = ROBOT
                        robotX = nextRobotX
                        robotY = nextRobotY
                    }
                }

                FREE_SPACE -> {
                    // Free to move, robot's position becomes free, next pos becomes robot
                    map[robotY][robotX] = FREE_SPACE
                    map[nextRobotY][nextRobotX] = ROBOT
                    robotX = nextRobotX
                    robotY = nextRobotY
                }
            }
        }

        return map.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                if (cell == BOX) {
                    Pair(y, x)
                } else null
            }
        }.sumOf {
            100 * it.first + it.second
        }
    }

    private fun parseInput(inputString: String): Pair<List<MutableList<Char>>, MutableList<Instruction>> {
        val mapLines = mutableListOf<MutableList<Char>>()
        var instructions = ""
        inputString.lines().forEach { line ->
            if (line.startsWith(WALL)) {
                mapLines.add(line.toCharArray().toMutableList())
            } else if (line.isNotBlank()) {
                instructions += line
            }
        }
        val directions = instructions.map {
            Instruction.bySymbol(it)
        }.toMutableList()
        return Pair(mapLines, directions)
    }

    private enum class Instruction(val symbol: Char, val dx: Int, val dy: Int) {
        UP('^', 0, -1),
        DOWN('v', 0, 1),
        LEFT('<', -1, 0),
        RIGHT('>', 1, 0);

        companion object {
            fun bySymbol(symbol: Char): Instruction {
                return entries.find { it.symbol == symbol }!!
            }
        }
    }

    private fun findRobotPosition(map: List<MutableList<Char>>): Pair<Int, Int> {
        var robotX = -1
        var robotY = -1
        for (y in map.indices) {
            if (robotX > 0) break
            for (x in map[y].indices) {
                val cell = map[y][x]
                if (cell == ROBOT) {
                    robotX = x
                    robotY = y
                    break
                }
            }
        }
        return Pair(robotX, robotY)
    }

    private const val BOX_LEFT = '['
    private const val BOX_RIGHT = ']'

    override fun part2(inputString: String): Any {
        val (map, instructions) = parseInput(inputString)
        val biggerMap = scaleUp(map)
        var (robotX, robotY) = findRobotPosition(biggerMap)

        while (instructions.isNotEmpty()) {
            val nextMove = instructions.removeFirst()

            val moved = move(biggerMap, robotX, robotY, nextMove)
            if(moved) {
                robotX += nextMove.dx
                robotY += nextMove.dy
            }
        }

        return biggerMap.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                if (cell == BOX_LEFT) {
                    Pair(y, x)
                } else null
            }
        }.sumOf {
            100 * it.first + it.second
        }
    }

    private fun scaleUp(map: List<MutableList<Char>>): List<MutableList<Char>> = map.map { row ->
        row.flatMap {
            when (it) {
                ROBOT -> listOf(ROBOT, FREE_SPACE)
                BOX -> listOf(BOX_LEFT, BOX_RIGHT)
                else -> listOf(it, it)
            }
        }.toMutableList()
    }

    private fun move(map: List<MutableList<Char>>, x: Int, y: Int, instruction: Instruction): Boolean {
        val visited = mutableMapOf<Pair<Int, Int>, Boolean>()
        val canMove = canMove(map, x, y, instruction, visited)
        if(canMove) {
            // Apply move
            for(visitedCoord in visited.keys) {
                val targetX = visitedCoord.first + instruction.dx
                val targetY = visitedCoord.second + instruction.dy
                val currentItem = map[visitedCoord.second][visitedCoord.first]
                map[targetY][targetX] = currentItem
                map[visitedCoord.second][visitedCoord.first] = FREE_SPACE
            }
        }
        return canMove
    }

    private fun canMove(map: List<MutableList<Char>>, x: Int, y: Int, instruction: Instruction, visited: MutableMap<Pair<Int, Int>, Boolean>): Boolean {
        return visited.getOrPut(Pair(x,y)) {
            val nextX = x + instruction.dx
            val nextY = y + instruction.dy
            val nextPos = map[nextY][nextX]
            when (nextPos) {
                WALL -> false
                FREE_SPACE -> true
                BOX_LEFT -> {
                    if (instruction == Instruction.LEFT) {
                        canMove(map, nextX, nextY, instruction, visited)
                    } else {
                        canMove(map, nextX, nextY, instruction, visited) &&
                        canMove(map, nextX + 1, nextY, instruction, visited)
                    }
                }
                BOX_RIGHT -> {
                    if (instruction == Instruction.RIGHT) {
                        canMove(map, nextX, nextY, instruction, visited)
                    } else {
                        canMove(map, nextX, nextY, instruction, visited) &&
                        canMove(map, nextX - 1, nextY, instruction, visited)
                    }
                }
                else -> throw RuntimeException("Error value")
            }
        }
    }
}
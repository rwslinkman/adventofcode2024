package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import java.util.PriorityQueue

@Puzzle("day16/input.txt")
object Day16: AdventChallenge {

    private const val WALL = '#'
    private const val START = 'S'
    private const val END = 'E'

    override fun part1(inputString: String): Any {
        val (cheapestCost, _) = solve(inputString)
        return cheapestCost
    }

    private fun solve(inputString: String): Pair<Int, Int> {
        val maze = inputString.lines().mapIndexed { y, row ->
            row.mapIndexed { x, cell ->
                Coordinate(x, y, cell)
            }
        }
        val start = maze.flatten().first { it.symbol == START }
        val end = maze.flatten().first { it.symbol == END }

        val visited = mutableListOf(end)
        var cheapestCost = Int.MAX_VALUE
        val scorePerPosition = mutableMapOf<Pair<Coordinate, Direction>, Int>()
        val options = PriorityQueue<MazeStep>(compareBy { it.score })
        options.add(MazeStep(0, start, Direction.EAST, mutableListOf(start)))
        while (options.isNotEmpty()) {
            val currentItem = options.poll()

            val existingScore = scorePerPosition[Pair(currentItem.position, currentItem.direction)] ?: Int.MAX_VALUE
            if (currentItem.score > existingScore) continue else {
                scorePerPosition[Pair(currentItem.position, currentItem.direction)] = currentItem.score
            }

            if (currentItem.position == end && currentItem.score <= cheapestCost) {
                visited.addAll(currentItem.traveledPath)
                cheapestCost = currentItem.score
                continue
            }

            val nextChoices = listOf(
                Pair(currentItem.direction, 1),
                Pair(currentItem.direction.turnLeft(), 1001),
                Pair(currentItem.direction.turnRight(), 1001)
            )
            nextChoices.forEach { nextChoice ->
                val dir = nextChoice.first
                val n = findNeighbour(maze, currentItem.position.x, currentItem.position.y, dir) ?: return@forEach
                if (n.symbol != WALL) {
                    val points = nextChoice.second
                    val path = currentItem.traveledPath.toMutableList()
                    path.add(currentItem.position)
                    val nextOption = MazeStep(currentItem.score + points, n, dir, path)
                    options.add(nextOption)
                }
            }
        }
        return Pair(cheapestCost, visited.toSet().size)
    }

    private data class Coordinate(val x: Int, val y: Int, val symbol: Char)

    private data class MazeStep(val score: Int, val position: Coordinate, val direction: Direction, val traveledPath: List<Coordinate>)

    private enum class Direction(val dx: Int, val dy: Int) {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        fun turnLeft(): Direction = when(this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }

        fun turnRight(): Direction = when(this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    private fun findNeighbour(maze: List<List<Coordinate>>, x: Int, y: Int, dir: Direction): Coordinate? {
        val nx = x + dir.dx
        val ny = y + dir.dy
        return try {
            maze[ny][nx]
        } catch(ex: IndexOutOfBoundsException) {
            null
        }
    }

    override fun part2(inputString: String): Any {
        val (_, tilesVisited) = solve(inputString)
        return tilesVisited
    }
}
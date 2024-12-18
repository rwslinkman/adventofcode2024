package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import java.util.PriorityQueue

@Puzzle("day18/input.txt")
object Day18: AdventChallenge {

    override fun part1(inputString: String): Any {
        return part1(inputString, height = 70, width = 70, simulateBytes = 1024)
    }

    fun part1(inputString: String, height: Int, width: Int, simulateBytes: Int): Any  {
        val coordinates = inputString.lines().map {
            val parts = it.split(",")
            Coordinate(parts[0].toInt(), parts[1].toInt())
        }
        val coordinatesInSimulation = coordinates.take(simulateBytes)

        return findShortestPath(coordinatesInSimulation, width, height)
    }

    private fun findShortestPath(coordinatesInSimulation: List<Coordinate>, width: Int, height: Int): Int {
        val start = Coordinate(0, 0)
        val end = Coordinate(width, height)
        val visited = mutableSetOf<Coordinate>()
        visited.addAll(coordinatesInSimulation)
        val options = PriorityQueue<Step>(compareBy { it.stepsTaken })
        options.add(Step(start, 0))

        while (options.isNotEmpty()) {
            val step = options.poll()

            if (step.position == end) {
                return step.stepsTaken
            }

            val nextSteps = Direction.entries.mapNotNull {
                val nextX = step.position.x + it.dx
                val nextY = step.position.y + it.dy
                if (nextX in 0..width && nextY in 0..height) {
                    Coordinate(nextX, nextY)
                } else null
            }.filter {
                it !in visited
            }.map {
                Step(it, step.stepsTaken + 1)
            }
            options.addAll(nextSteps)
            visited.add(step.position)
            visited.addAll(nextSteps.map { it.position })
        }
        return -1
    }

    private data class Coordinate(val x: Int, val y: Int)

    private enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
    }

    private data class Step(val position: Coordinate, val stepsTaken: Int)

    override fun part2(inputString: String): Any {
        return part2(inputString, height = 70, width = 70)
    }

    fun part2(inputString: String, height: Int, width: Int): Any {
        val coordinates = inputString.lines().map {
            val parts = it.split(",")
            Coordinate(parts[0].toInt(), parts[1].toInt())
        }

        var indexPathExists = 0
        while (indexPathExists < coordinates.size) {

            val coordinatesInSimulation = coordinates.take(indexPathExists)
            val result = findShortestPath(coordinatesInSimulation, width, height)
            if(result == -1) break else {
                // Fine, try next
                indexPathExists++
            }
        }
        val firstFailingCoordinate = coordinates[indexPathExists - 1]
        return firstFailingCoordinate.let {
            "${it.x},${it.y}"
        }
    }
}
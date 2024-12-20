package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import java.util.*
import kotlin.math.abs


@Puzzle("day20/input.txt")
object Day20: AdventChallenge {

    private const val WALL = '#'
    private const val START = 'S'
    private const val END = 'E'

    override fun part1(inputString: String): Any {
        return part1(inputString, desiredProfit = 100)
    }

    fun part1(inputString: String, desiredProfit: Int): Any {
        val map = inputString.lines().flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                Coordinate(x, y, char)
            }
        }
        return countValidCheats(map, desiredProfit, cheatDuration = 2)
    }

    private fun findShortestPath(map: List<Coordinate>): Map<Coordinate, Int> {
        val start = map.first { it.symbol == START }
        val end = map.first { it.symbol == END }
        val visited = mutableSetOf(start)
        val options = PriorityQueue<Step>(compareBy { it.distanceFromStart })
        options.add(Step(start, 0))

        val shortestPath: LinkedList<Step> = LinkedList()

        while (options.isNotEmpty()) {
            val step = options.poll()
            if (step.position == end) {
                shortestPath.add(step)
                break
            }

            visited.add(step.position)
            shortestPath.add(step)

            val nextSteps = Direction.entries.mapNotNull { dir ->
                map.find { it.x == (step.position.x + dir.dx) && it.y == (step.position.y + dir.dy) }
            }.filter {
                it !in visited && it.symbol != WALL
            }.map {
                visited.add(it)
                Step(it, step.distanceFromStart + 1)
            }
            options.addAll(nextSteps)
        }
        return shortestPath.associate {
            it.position to it.distanceFromStart
        }
    }

    private data class Coordinate(val x: Int, val y: Int, val symbol: Char) {
        fun distanceTo(other: Coordinate): Int {
            return abs(x - other.x) + abs(y - other.y)
        }
    }

    private data class Step(val position: Coordinate, val distanceFromStart: Int)

    private enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
    }

    override fun part2(inputString: String): Any {
        return part2(inputString, desiredProfit = 100)
    }

    fun part2(inputString: String, desiredProfit: Int): Any {
        val map = inputString.lines().flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                Coordinate(x, y, char)
            }
        }
        return countValidCheats(map, desiredProfit, cheatDuration = 20)
    }

    private fun countValidCheats(map: List<Coordinate>, desiredProfit: Int, cheatDuration: Int): Int {
        var validCheats = 0
        val shortestPath = findShortestPath(map)
        shortestPath.entries.forEach { (coordinate, distance) ->

            for (dx in -cheatDuration..cheatDuration) {
                for (dy in -cheatDuration..cheatDuration) {
                    if (dx == 0 && dy == 0) continue
                    if (abs(dx) + abs(dy) > cheatDuration) continue

                    val nextCoord = shortestPath.keys.find {
                        it.x == coordinate.x + dx && it.y == coordinate.y + dy
                    }
                    if (nextCoord != null) {
                        val nextDistance = shortestPath[nextCoord]!!
                        val cost = distance - nextDistance
                        val cheatCost = coordinate.distanceTo(nextCoord)
                        val profit = cost - cheatCost
                        if (profit >= desiredProfit) {
                            validCheats++
                        }
                    }
                }
            }
        }
        return validCheats
    }
}
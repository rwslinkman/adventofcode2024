package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day10/input.txt")
object Day10: AdventChallenge {

    override fun part1(inputString: String): Any {
        val map = inputString.lines().mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                Position(x, y, c.digitToInt())
            }
        }
        val trailHeads = map.flatten().filter { it.height == 0 }

        val trailScores = trailHeads.map { trailHead ->
            var targetsHit = 0

            val dirOptions = mutableListOf(trailHead)
            val visited = mutableSetOf(trailHead)
            while(dirOptions.isNotEmpty()) {
                val currentPos = dirOptions.removeLast()
                visited.add(currentPos)
                if(currentPos.height == TARGET_HEIGHT) {
                    targetsHit++
                    continue
                }

                Direction.entries.forEach { dir ->
                    val neighbor = findNeighbour(map, currentPos, dir) ?: return@forEach
                    if(visited.contains(neighbor)) return@forEach

                    if(neighbor.height == currentPos.height + 1) {
                        dirOptions.add(neighbor)
                    }
                }
            }
            targetsHit
        }
        return trailScores.sum()
    }

    private enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
    }

    private data class Position(val x: Int, val y: Int, val height: Int)

    private const val TARGET_HEIGHT: Int = 9

    private fun findNeighbour(map: List<List<Position>>, position: Position, direction: Direction): Position? {
        val nx = position.x + direction.dx
        val ny = position.y + direction.dy
        return try {
            map[ny][nx]
        } catch (ex: IndexOutOfBoundsException) {
            null
        }
    }

    override fun part2(inputString: String): Any {
        val map = inputString.lines().mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                Position(x, y, c.digitToInt())
            }
        }
        val trailHeads = map.flatten().filter { it.height == 0 }

        val trailScores = trailHeads.map { trailHead ->
            var rating = 0

            val dirOptions = mutableListOf(trailHead)
            while(dirOptions.isNotEmpty()) {
                val currentPos = dirOptions.removeLast()
                if(currentPos.height == TARGET_HEIGHT) {
                    rating++
                }

                Direction.entries.forEach { dir ->
                    val neighbor = findNeighbour(map, currentPos, dir) ?: return@forEach
                    if(neighbor.height == currentPos.height + 1) {
                        dirOptions.add(neighbor)
                    }
                }
            }
            rating
        }
        return trailScores.sum()
    }
}
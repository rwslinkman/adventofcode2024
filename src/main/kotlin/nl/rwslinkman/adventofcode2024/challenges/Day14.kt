package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day14/input.txt")
object Day14: AdventChallenge {

    override fun part1(inputString: String): Any {
        return part1(inputString, width = 101, height = 103)
    }

    fun part1(inputString: String, width: Int, height: Int): Any {
        val robots = parseInput(inputString)
        robots.forEach { robot ->
            for(i in 1..100) {
                moveRobot(robot, width, height)
            }
        }

        val quadrants = getQuadrants(height, width)
        return quadrants.fold(1) { acc, q ->
            val robotsInQuarter = robots.count {
                val min = q.first
                val max = q.second
                it.posX >= min.first &&
                        it.posX <= max.first &&
                        it.posY >= min.second &&
                        it.posY <= max.second
            }
            acc * robotsInQuarter
        }
    }

    private data class Robot(var posX: Int, var posY: Int, val vX: Int, val vY: Int)

    private fun moveRobot(robot: Robot, width: Int, height: Int) {
        var nextX = (robot.posX + robot.vX) % width
        if (nextX < 0) {
            nextX += width
        }
        robot.posX = nextX

        var nextY = (robot.posY + robot.vY) % height
        if (nextY < 0) {
            nextY += height
        }
        robot.posY = nextY
    }

    private fun getQuadrants(height: Int, width: Int): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        val q1 = Pair(
            Pair(0, 0),
            Pair((width/2)-1, (height/2)-1)
        )
        val q2 = Pair(
            Pair((width/2)+1, 0),
            Pair(width-1, (height/2)-1)
        )
        val q3 = Pair(
            Pair(0, (height/2)+1),
            Pair((width/2)-1, height-1)
        )
        val q4 = Pair(
            Pair((width/2)+1, (height/2)+1),
            Pair(width-1, height-1)
        )
        return  listOf(q1, q2, q3, q4)
    }

    override fun part2(inputString: String): Any {
        val robots = parseInput(inputString)
        val width = 101
        val height = 103

        var seconds = 1
        while (seconds < width * height) {

            robots.forEach { robot ->
                moveRobot(robot, width, height)
            }

            val peakCandidates = robots.filter {
                val upX = it.posX
                val upY = it.posY - 1
                robots.none { r -> r.posX == upX && r.posY == upY }
            }
            val remaining = peakCandidates.filter { peak ->
                val diagCoords = mutableListOf<Pair<Int, Int>>()
                repeat(5) { step ->
                    val diagLX = peak.posX - step
                    val diagLY = peak.posY + step
                    diagCoords.add(Pair(diagLX, diagLY))
                    val diagRX = peak.posX + step
                    val diagRY = peak.posY + step
                    diagCoords.add(Pair(diagRX, diagRY))
                }
                diagCoords.all { c ->
                    robots.any { r -> r.posX == c.first && r.posY == c.second }
                }
            }

            if(remaining.isNotEmpty()) {
                return seconds
            }
            seconds++
        }
        return -1
    }

    private fun parseInput(inputString: String): List<Robot> {
        val parser = Regex("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)")
        val robots = inputString.lines().map {
            val (x, y, vX, vY) = parser.find(it)!!.destructured
            Robot(x.toInt(), y.toInt(), vX.toInt(), vY.toInt())
        }
        return robots
    }
}
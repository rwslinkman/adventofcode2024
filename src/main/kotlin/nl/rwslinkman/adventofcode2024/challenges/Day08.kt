package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day08/input.txt")
object Day08: AdventChallenge {

    override fun part1(inputString: String): Any {
        var maxX = 0
        var maxY = 0
        val antennas = inputString.lines().flatMapIndexed { y, row ->
            maxY = y
            row.mapIndexedNotNull { x, cell ->
                maxX = x
                if(cell == '.') null else Antenna(cell, x, y)
            }
        }
        val antennaGroups = antennas.groupBy { it.signal }

        val validAntiNodes = mutableSetOf<Pair<Int, Int>>()
        antennaGroups.forEach { (_, antennas) ->
            antennas.forEach { antenna ->
                val others = antennas.toMutableSet()
                others.remove(antenna)

                others.forEach { otherAntenna ->
                    // Distance
                    val dx = otherAntenna.x - antenna.x
                    val dy = otherAntenna.y - antenna.y

                    // Direction 1
                    val newX1 = otherAntenna.x + dx
                    val newY1 = otherAntenna.y + dy
                    if(newX1 in 0..maxX && newY1 in 0..maxY) {
                        validAntiNodes.add(Pair(newX1, newY1))
                    }

                    // Direction 2
                    val newX2 = antenna.x - dx
                    val newY2 = antenna.y - dy
                    if(newX2 in 0..maxX && newY2 in 0..maxY) {
                        validAntiNodes.add(Pair(newX2, newY2))
                    }
                }
            }
        }
        return validAntiNodes.size
    }

    private data class Antenna(val signal: Char, val x: Int, val y: Int)

    override fun part2(inputString: String): Any {
        var maxX = 0
        var maxY = 0
        val antennas = inputString.lines().flatMapIndexed { y, row ->
            maxY = y
            row.mapIndexedNotNull { x, cell ->
                maxX = x
                if(cell == '.') null else Antenna(cell, x, y)
            }
        }
        val antennaGroups = antennas.groupBy { it.signal }

        val validAntiNodes = mutableSetOf<Pair<Int, Int>>()
        antennaGroups.forEach { (_, antennas) ->
            antennas.forEach { antenna ->

                validAntiNodes.add(Pair(antenna.x, antenna.y))

                val others = antennas.toMutableSet()
                others.remove(antenna)

                others.forEach { otherAntenna ->
                    // Distance
                    val dx = otherAntenna.x - antenna.x
                    val dy = otherAntenna.y - antenna.y

                    // Direction 1
                    var newX1 = otherAntenna.x + dx
                    var newY1 = otherAntenna.y + dy
                    while(newX1 in 0..maxX && newY1 in 0..maxY) {
                        validAntiNodes.add(Pair(newX1, newY1))
                        newX1 += dx
                        newY1 += dy
                    }

                    // Direction 2
                    var newX2 = antenna.x - dx
                    var newY2 = antenna.y - dy
                    while(newX2 in 0..maxX && newY2 in 0..maxY) {
                        validAntiNodes.add(Pair(newX2, newY2))
                        newX2 -= dx
                        newY2 -= dy
                    }
                }
            }
        }
        return validAntiNodes.size
    }
}
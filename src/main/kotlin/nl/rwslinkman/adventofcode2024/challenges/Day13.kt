package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import kotlin.math.abs
import kotlin.math.roundToLong

@Puzzle("day13/input.txt")
object Day13 : AdventChallenge {

    override fun part1(inputString: String): Any {
        val clawMachineInstructions = parseClawMachineInstructions(inputString)

        return clawMachineInstructions.sumOf { clawMachine ->
            val pressCounts = countButtonPresses(clawMachine)
            if(pressCounts == null) 0 else {
                pressCounts.first * 3 + pressCounts.second
            }
        }
    }

    private data class ClawMachineInstruction(
        val dxA: Int,
        val dyA: Int,
        val dxB: Int,
        val dyB: Int,
        val prizeX: Long,
        val prizeY: Long
    )

    private fun parseClawMachineInstructions(inputString: String, offset: Long = 0L): List<ClawMachineInstruction> {
        val lines = inputString.lines()
        val groups = mutableListOf<List<String>>()
        var tmpGroup = mutableListOf<String>()
        for (l in lines.indices) {
            val line = lines[l]
            if (line.isEmpty()) {
                groups.add(tmpGroup)
                tmpGroup = mutableListOf()
            } else {
                tmpGroup.add(line)
            }
        }
        groups.add(tmpGroup) // remaining block without newline

        val buttonRegex = Regex("X\\+(\\d+), Y\\+(\\d+)")
        val prizeRegex = Regex("X=(\\d+), Y=(\\d+)")
        return groups.map { group ->
            val (xA, yA) = buttonRegex.find(group[0])!!.destructured
            val (xB, yB) = buttonRegex.find(group[1])!!.destructured
            val (prizeX, prizeY) = prizeRegex.find(group[2])!!.destructured
            ClawMachineInstruction(
                xA.toInt(),
                yA.toInt(),
                xB.toInt(),
                yB.toInt(),
                prizeX.toLong() + offset,
                prizeY.toLong() + offset
            )
        }
    }

    private fun countButtonPresses(clawMachine: ClawMachineInstruction): Pair<Long, Long>? {
        // y = ax + b
        val aA = clawMachine.dyA / clawMachine.dxA.toDouble()
        // b = y - ax
        val bA = clawMachine.prizeY - (aA * clawMachine.prizeX)

        val aB = clawMachine.dyB / clawMachine.dxB.toDouble()
        val bB = 0

        // (bDiff / aDiff) = intersection x-coord
        val xIntersect = (bB - bA) / (aA - aB)
        // Presses needed = x / stepSize
        val countB = xIntersect / clawMachine.dxB
        val countA = (clawMachine.prizeX - xIntersect) / clawMachine.dxA

        return if (countA.isCloseToRounded() && countB.isCloseToRounded()) {
            Pair(countA.roundToLong(), countB.roundToLong())
        } else null
    }

    private fun Double.isCloseToRounded(): Boolean {
        return abs(this.roundToLong() - this) < 0.001
    }

    override fun part2(inputString: String): Any {
        val clawMachineInstructions = parseClawMachineInstructions(inputString, offset = ERROR_OFFSET)

        return clawMachineInstructions.sumOf { clawMachine ->
            val pressCounts = countButtonPresses(clawMachine)
            if(pressCounts == null) 0 else {
                pressCounts.first * 3 + pressCounts.second
            }
        }
    }

    private const val ERROR_OFFSET = 10_000_000_000_000L
}
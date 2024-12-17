package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle
import kotlin.math.min
import kotlin.math.pow

@Puzzle("day17/input.txt")
object Day17 : AdventChallenge {

    override fun part1(inputString: String): Any {
        val (registers, program) = parseInput(inputString)

        val output = runComputerInstructions(program, registers)

        return output.joinToString(",")
    }

    private fun runComputerInstructions(program: List<String>, registers: MutableMap<String, Long>): MutableList<Long> {
        val instructions = program.chunked(size = 2)
        var i = 0
        val output = mutableListOf<Long>()
        while (i <= instructions.lastIndex) {
            val (opCode, operand) = instructions[i]
            when (opCode) {
                "0" -> { // adv
                    val numerator = registers["A"]!!
                    val comboValue = comboOperandValue(registers, operand)
                    val denominator = 2.0.pow(comboValue.toInt())
                    registers["A"] = (numerator / denominator).toLong()
                    i++
                }

                "1" -> { // bxl
                    val leftValue = registers["B"]!!
                    registers["B"] = leftValue xor operand.toLong()
                    i++
                }

                "2" -> { // bst
                    val leftValue = comboOperandValue(registers, operand)
                    registers["B"] = leftValue % 8
                    i++
                }

                "3" -> { // jnz
                    val leftValue = registers["A"]!!
                    if (leftValue != 0L) {
                        i = operand.toInt()
                    } else i++
                }

                "4" -> { // bcx
                    val leftValue = registers["B"]!!
                    val rightValue = registers["C"]!!
                    registers["B"] = (leftValue xor rightValue)
                    i++
                }

                "5" -> { // out
                    val leftValue = comboOperandValue(registers, operand)
                    output.add(leftValue % 8)
                    i++
                }

                "6" -> { // bdv
                    val numerator = registers["A"]!!
                    val comboValue = comboOperandValue(registers, operand)
                    val denominator = (2.0).pow(comboValue.toInt())
                    registers["B"] = (numerator / denominator).toLong()
                    i++
                }

                "7" -> { // cdv
                    val numerator = registers["A"]!!
                    val comboValue = comboOperandValue(registers, operand)
                    val denominator = 2.0.pow(comboValue.toInt())
                    registers["C"] = (numerator / denominator).toLong()
                    i++
                }
            }
        }
        return output
    }

    private fun parseInput(inputString: String): Pair<MutableMap<String, Long>, List<String>> {
        val registerParser = Regex("Register (.*): (\\d+)")
        val registers = mutableMapOf<String, Long>()
        val program = mutableListOf<String>()
        inputString.lines().forEach { line ->
            if (line.isBlank()) return@forEach
            if (line.startsWith("Register")) {
                val (registerName, registerValue) = registerParser.find(line)!!.destructured
                registers[registerName] = registerValue.toLong()
            } else {
                val prog = line.replace("Program: ", "")
                program.addAll(prog.split(","))
            }
        }
        return Pair(registers, program)
    }

    private fun comboOperandValue(registers: Map<String, Long>, operand: String): Long {
        return when (operand) {
            "0", "1", "2", "3" -> operand.toLong()
            "4" -> registers["A"]!!
            "5" -> registers["B"]!!
            "6" -> registers["C"]!!
            else -> 1L
        }
    }

    override fun part2(inputString: String): Any {
        val (_, program) = parseInput(inputString)
        val inputValues = program.map { it.toLong() }

        return findLowestRegisterValue(inputValues, 0, 0, Long.MAX_VALUE)
    }

    private fun findLowestRegisterValue(program: List<Long>, current: Long, position: Int, lowest: Long): Long {
        var local = lowest
        for(i in 0 until 8) {
            val valueA = (current shl 3) + i
            val registers = mutableMapOf(
                "A" to valueA,
                "B" to 0,
                "C" to 0
            )
            val nextResult = runComputerInstructions(program.map { it.toString() }, registers)
            val expectedResult = program.subList(program.size - position - 1, program.size)
            if(!areListsEqual(nextResult, expectedResult)) {
                continue
            }

            local = if(position == program.size - 1) {
                min(local, valueA)
            } else {
                min(local, findLowestRegisterValue(program, valueA, position + 1, local))
            }
        }
        return local
    }

    private fun areListsEqual(a: List<Long>, b: List<Long>): Boolean {
        if (a.size != b.size) return false
        val pairs = a.zip(b)
        return pairs.all {
            it.first == it.second
        }
    }
}
package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day24/input.txt")
object Day24: AdventChallenge {

    override fun part1(inputString: String): Any {
        val (initialStates, gates) = parseInput(inputString)

        val outputMap = mutableMapOf<String, Boolean>()
        val options = gates.toMutableList()
        while (options.isNotEmpty()) {
            val gate = options.removeFirst()
            val valueLeft: Boolean? = outputMap.getOrDefault(gate.inputLeft, initialStates.find { it.name == gate.inputLeft }?.value)
            val valueRight: Boolean? = outputMap.getOrDefault(gate.inputRight, initialStates.find { it.name == gate.inputRight }?.value)
            if (valueLeft == null || valueRight == null) {
                // Cannot be computed yet, return to stack
                options.add(gate)
                continue
            }

            val operationResult = when(gate.operation) {
                "XOR" -> valueLeft.xor(valueRight)
                "OR" -> valueLeft.or(valueRight)
                "AND" -> valueLeft.and(valueRight)
                else -> throw Exception("Unknown operation type ${gate.operation}")
            }
            outputMap[gate.target] = operationResult
        }
        val zOutputs = outputMap.filter {
            it.key.startsWith("z")
        }.toSortedMap()
        val binaryNumber = zOutputs.values.reversed().joinToString("") {
            it.asString()
        }
        return binaryNumber.toLong(2)
    }

    private fun parseInput(inputString: String): Pair<List<Input>, List<Gate>> {
        val sections = inputString.split("\n\n")
        val states = sections[0].lines().map {
            val stateParts = it.split(": ")
            Input(stateParts[0], stateParts[1].asBoolean())
        }
        val gateRegex = Regex("([a-z0-9]{3}) ([A-Z]{2,3}) ([a-z0-9]{3}) -> ([a-z0-9]{3})")
        val gates = sections[1].lines().map {
            val (left, gate, right, target) = gateRegex.find(it)!!.destructured
            Gate(left, right, gate, target)
        }
        return Pair(states, gates)
    }

    private data class Input(val name: String, val value: Boolean)

    private data class Gate(val inputLeft: String, val inputRight: String, val operation: String, val target: String)

    private fun String.asBoolean(): Boolean = this == "1"

    private fun Boolean.asString(): String = if (this) "1" else "0"

    override fun part2(inputString: String): Any {
        val (_, gates) = parseInput(inputString)
        val highestZ = "z45"

        val wrong = mutableSetOf<String>()
        gates.forEach { gate ->
            if (gate.target.startsWith("z") && gate.operation != "XOR" && gate.target != highestZ) {
                wrong.add(gate.target)
            }

            val gatesOfInterest = listOf("x", "y", "z")
            if(gate.operation == "XOR"
                && gate.target.firstStr() !in gatesOfInterest
                && gate.inputLeft.firstStr() !in gatesOfInterest
                && gate.inputRight.firstStr() !in gatesOfInterest
            ) {
                wrong.add(gate.target)
            }

            val inputsOfInterest = listOf(gate.inputLeft, gate.inputRight)
            if(gate.operation == "AND" && "x00" !in inputsOfInterest) {
                gates.forEach { subgate ->
                    if((gate.target == subgate.inputLeft || gate.target == subgate.inputRight) && subgate.operation != "OR"){
                        wrong.add(gate.target)
                    }
                }
            }

            if(gate.operation == "XOR") {
                gates.forEach { subgate ->
                    if((gate.target == subgate.inputLeft || gate.target == subgate.inputRight) && subgate.operation == "OR"){
                        wrong.add(gate.target)
                    }
                }
            }
        }
        return wrong.sorted().joinToString(",")
    }

    private fun String.firstStr() = this.first().toString()
}
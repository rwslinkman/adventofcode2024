package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day05/input.txt")
object Day05: AdventChallenge {

    override fun part1(inputString: String): Any {
        val pageRules = mutableMapOf<Int, MutableList<Int>>()
        val updateLines = mutableListOf<List<Int>>()
        inputString.lines().forEach { line ->
            if(line.contains("|")) {
                val lineParts = line.split("|")
                val key = lineParts[0].toInt()
                if(!pageRules.containsKey(key)) {
                    pageRules[key] = mutableListOf()
                }
                pageRules[key]!!.add(lineParts[1].toInt())

            } else if(line.contains(",")) {
                updateLines.add(line.split(",").map { it.toInt() })
            }
        }

        var total = 0
        updateLines.forEach { updateLine ->
            val isOrdered = isOrdered(updateLine, pageRules)
            if(isOrdered) {
                total += updateLine[updateLine.lastIndex / 2]
            }
        }
        return total
    }

    private fun isOrdered(update: List<Int>, rules: Map<Int, MutableList<Int>>): Boolean {
        update.forEachIndexed { index, num ->
            if(rules.containsKey(num)) {
                val requiredFirst = rules[num]!!
                val updateTillHere = update.subList(0, index)
                requiredFirst.forEach {
                    if(updateTillHere.contains(it)) return false
                }
            }
        }
        return true
    }

    override fun part2(inputString: String): Any {
        val pageRules = mutableMapOf<Int, MutableList<Int>>()
        val updateLines = mutableListOf<List<Int>>()
        inputString.lines().forEach { line ->
            if(line.contains("|")) {
                val lineParts = line.split("|")
                val key = lineParts[0].toInt()
                if(!pageRules.containsKey(key)) {
                    pageRules[key] = mutableListOf()
                }
                pageRules[key]!!.add(lineParts[1].toInt())

            } else if(line.contains(",")) {
                updateLines.add(line.split(",").map { it.toInt() })
            }
        }

        var total = 0
        updateLines.forEach { updateLine ->
            val isOrdered = isOrdered(updateLine, pageRules)
            if(!isOrdered) {
                val sorted = updateLine.sortedWith { a, b ->
                    val itemRules = pageRules[a] ?: mutableListOf()
                    if (itemRules.contains(b)) -1 else 0
                }
                total += sorted[sorted.lastIndex / 2]
            }
        }
        return total
    }
}
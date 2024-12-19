package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day19/input.txt")
object Day19: AdventChallenge {

    override fun part1(inputString: String): Any {
        val (availablePatterns, designs) = parseInput(inputString)

        var possibleDesigns = 0
        for (design in designs) {
            val isDesignPossible = searchMatch(availablePatterns, design)
            if(isDesignPossible) possibleDesigns++
        }
        return possibleDesigns
    }

    private fun searchMatch(patterns: List<String>, design: String): Boolean {
        if(design.isEmpty()) return true

        return patterns.any { pattern ->
            if(design.startsWith(pattern)) {
                val remainder = design.removePrefix(pattern)
                searchMatch(patterns, remainder)
            } else false
        }
    }

    override fun part2(inputString: String): Any {
        val (availablePatterns, designs) = parseInput(inputString)
        return designs.sumOf {
            countMatches(availablePatterns, it, mutableMapOf())
        }
    }

    private fun parseInput(inputString: String): Pair<List<String>, List<String>> {
        val inputLines = inputString.lines()
        val availablePatterns: List<String> = inputLines[0].split(",").map { it.trim() }
        val designs: List<String> = inputLines.subList(2, inputLines.lastIndex).toList()
        return Pair(availablePatterns, designs)
    }

    private fun countMatches(patterns: List<String>, design: String, cache: MutableMap<String, Long>): Long {
        if (cache.containsKey(design)) return cache[design]!!

        if(design.isEmpty()) return 1

        val matchCount = patterns.sumOf { pattern ->
            if(design.startsWith(pattern)) {
                val remainder = design.removePrefix(pattern)
                countMatches(patterns, remainder, cache)
            } else 0
        }
        cache[design] = matchCount
        return matchCount
    }
}
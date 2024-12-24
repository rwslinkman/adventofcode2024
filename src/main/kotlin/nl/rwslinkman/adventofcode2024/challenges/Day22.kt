package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day22/input.txt")
object Day22: AdventChallenge {

    override fun part1(inputString: String): Any {
        val initialSecrets = inputString.lines().map { it.toLong() }

        val iterations = 2000L
        return initialSecrets.sumOf { secret ->
            var result = calculateNextSecret(secret)
            for (i in 1 until iterations) {
                result = calculateNextSecret(result)
            }
            result
        }
    }

    fun mix(value: Long, secret: Long): Long = value xor secret

    fun prune(secret: Long): Long = secret.mod(16777216L)

    private fun calculateNextSecret(secret: Long): Long {
        var result = secret * 64L
        result = mix(result, secret)
        result = prune(result)
        var newSecret = result
        result /= 32L
        result = mix(result, newSecret)
        result = prune(result)
        newSecret = result
        result *= 2048L
        result = mix(result, newSecret)
        result = prune(result)
        return result
    }

    override fun part2(inputString: String): Any {
        val initialSecrets = inputString.lines().map { it.toLong() }

        val sequences = mutableMapOf<String, Long>()
        val iterations = 2000L
        initialSecrets.forEach { secret ->
            val firstSequence = mutableSetOf<String>()
            var previousDiffs = mutableListOf<Long>()
            val prices = mutableListOf<Long>()

            var result = secret
            var onesDigit = secret.mod(10L)
            for (i in 0 until iterations) {
                result = calculateNextSecret(result)
                val newOnesDigit = result.mod(10L)
                val diff = newOnesDigit - onesDigit
                onesDigit = newOnesDigit

                prices.add(diff)
                previousDiffs.add(diff)
                previousDiffs = previousDiffs.takeLast(4).toMutableList()

                if(i >= 3) {
                    val key = previousDiffs.joinToString("_")
                    if (key !in firstSequence) {
                        firstSequence.add(key)
                        sequences[key] = sequences.getOrDefault(key, 0L) + onesDigit
                    }
                }
            }
        }
        return sequences.values.max()
    }
}
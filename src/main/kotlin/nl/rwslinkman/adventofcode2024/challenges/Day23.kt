package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day23/input.txt")
object Day23 : AdventChallenge {

    override fun part1(inputString: String): Any {
        val links = parseInput(inputString)

        val computerSets = links.entries.flatMap { connectionGroup ->
            val pc = connectionGroup.key
            val pcConnections = connectionGroup.value

            pcConnections.flatMap { connectedPC ->
                val connections2 = links[connectedPC]!!
                val matches = connections2.filter { it in pcConnections && it != connectedPC }
                if (matches.isEmpty()) listOf() else {
                    matches.map {
                        listOf(pc, connectedPC, it).sorted()
                    }
                }
            }.toSet()
        }.toSet()

        return computerSets.count {
            it.any { pc -> pc.startsWith("t") }
        }
    }

    private fun parseInput(inputString: String): MutableMap<String, MutableSet<String>> {
        val connections = inputString.lines().map {
            val parts = it.split("-")
            Pair(parts[0], parts[1])
        }
        val links = mutableMapOf<String, MutableSet<String>>()
        connections.forEach { (pc1, pc2) ->
            links.putIfAbsent(pc1, mutableSetOf())
            links[pc1]!!.add(pc2)

            links.putIfAbsent(pc2, mutableSetOf())
            links[pc2]!!.add(pc1)
        }
        return links
    }

    override fun part2(inputString: String): Any {
        val links = parseInput(inputString)

        val visited = mutableSetOf<String>()
        var largestNetwork = emptySet<String>()
        var largestNetworkSize = 0
        links.keys.forEach { pc ->
            val queue = mutableListOf<Step>()
            queue.add(Step(pc, 1, listOf(pc)))
            while (queue.isNotEmpty()) {
                val current = queue.removeLast()
                visited.add(current.connections.joinToString("_"))

                if (current.connections.size > largestNetworkSize) {
                    largestNetworkSize = current.connections.size
                    largestNetwork = current.connections.toSet()
                }

                val connections = links[pc]!!
                for (neighbour in connections) {
                    var isConnectedToAll = true
                    for (connection in current.connections) {
                        if (neighbour !in links[connection]!!) {
                            isConnectedToAll = false
                            break
                        }
                    }

                    if (isConnectedToAll) {
                        val candidate: Set<String> = setOf(neighbour, *(current.connections.toTypedArray()))
                        val sortedCandidate = candidate.sorted()
                        if (sortedCandidate.joinToString("_") in visited) continue else {
                            queue.add(Step(neighbour, current.connectionCount + 1, sortedCandidate))
                        }
                    }
                }
            }
        }
        return largestNetwork.sorted().joinToString(",")
    }

    private data class Step(val pc: String, val connectionCount: Int, val connections: List<String>)
}
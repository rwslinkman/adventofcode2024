package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day09/input.txt")
object Day09: AdventChallenge {

    override fun part1(inputString: String): Any {
        var fileId = 0
        val block = mutableListOf<String>()
        inputString.forEachIndexed { index, num ->
            val isEven = index % 2 == 0
            if (isEven && index != 0) {
                fileId++
            }
            val symbol = if(!isEven) FREE_SPACE else "$fileId"
            for(i in 0..<num.digitToInt()) {
                block.add(symbol)
            }
        }

        var endPos = block.lastIndex
        val sorted = mutableListOf<String>()
        block.forEachIndexed { index, item ->
            if(endPos < index) return@forEachIndexed

            if(item == FREE_SPACE) {
                // Take last available
                var lastItem = block[endPos]
                while(lastItem == FREE_SPACE) {
                    endPos--
                    lastItem = block[endPos]
                }
                sorted.add(lastItem)
                endPos--
            } else {
                // Stays there
                sorted.add(item)
            }
        }

        return sorted.foldIndexed(0L) { index, acc, item ->
            val itemValue = item.toLong()
            acc + index * itemValue
        }
    }

    private const val FREE_SPACE: String = "."

    override fun part2(inputString: String): Any {
        var fileId = 0
        val block = mutableListOf<String>()
        inputString.forEachIndexed { index, num ->
            val isEven = index % 2 == 0
            if (isEven && index != 0) {
                fileId++
            }
            val symbol = if(!isEven) FREE_SPACE else "$fileId"
            for(i in 0..<num.digitToInt()) {
                block.add(symbol)
            }
        }

        val fileGroups = block
            .mapIndexed { index, item -> Pair(index, item) }
            .filter { it.second != FREE_SPACE }
            .groupBy { it.second }
            .entries
            .reversed()

        fileGroups.forEach { file ->
            if(file.key == "1") return@forEach

            val fileCount = file.value.size
            val firstFileIndex = file.value.first().first

            val freeIndexes = mutableListOf<Int>()
            block.forEachIndexed { index, item ->
                if(index >= firstFileIndex) return@forEachIndexed

                if(item == FREE_SPACE) {
                    freeIndexes.add(index)
                    if(freeIndexes.size == fileCount) {
                        freeIndexes.forEach {
                            block[it] = file.key
                        }
                        file.value.forEach {
                            block[it.first] = FREE_SPACE
                        }
                        freeIndexes.clear()
                        return@forEach
                    }
                } else {
                    freeIndexes.clear()
                }
            }
        }

        return block.foldIndexed(0L) { index, acc, item ->
            if(item == FREE_SPACE) acc else {
                val itemValue = item.toLong()
                acc + index * itemValue
            }
        }
    }
}
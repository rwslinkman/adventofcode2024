package nl.rwslinkman.adventofcode2024.challenges

import nl.rwslinkman.adventofcode2024.AdventChallenge
import nl.rwslinkman.adventofcode2024.Puzzle

@Puzzle("day12/input.txt")
object Day12: AdventChallenge {

    override fun part1(inputString: String): Any {
        val map = inputString.lines().mapIndexed { y, row ->
            row.mapIndexed { x, cell ->
                Plant(x, y , cell)
            }
        }

        val visited = mutableSetOf<Plant>()
        val allGardens = mutableListOf<Garden>()
        map.flatten().forEach { plant ->
            if(plant in visited) return@forEach

            val garden = findGardenOfPlant(map, plant)
            allGardens.add(garden)
            visited.addAll(garden.plants)
        }

        return allGardens.sumOf { garden ->
            val area = garden.plants.size
            val fenceCount = garden.plants.sumOf { plant ->
                val neighbours = Direction.entries.map { dir -> findNeighbour(map, plant, dir) }
                neighbours.count { it == null || it !in garden.plants }
            }
            area * fenceCount
        }
    }

    private fun findGardenOfPlant(map: List<List<Plant>>, plant: Plant): Garden {
        val garden = mutableSetOf<Plant>()

        val options = mutableListOf(plant)
        while(options.isNotEmpty()) {
            val item = options.removeLast()

            if(!garden.add(item)) continue
            Direction.entries.forEach { dir ->
                val neighbour = findNeighbour(map, item, dir) ?: return@forEach
                if(neighbour.plantType == item.plantType) {
                    options.add(neighbour)
                }
            }
        }
        return Garden(plant.plantType, garden)
    }

    private enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0),
    }

    private data class Plant(val x: Int, val y: Int, val plantType: Char)

    private data class Garden(val plantType: Char, val plants: Set<Plant>)

    private fun findNeighbour(map: List<List<Plant>>, current: Plant, direction: Direction): Plant? {
        val nx = current.x + direction.dx
        val ny = current.y + direction.dy
        return try {
            map[ny][nx]
        } catch (ex: IndexOutOfBoundsException) {
            null
        }
    }

    override fun part2(inputString: String): Any {
        val map = inputString.lines().mapIndexed { y, row ->
            row.mapIndexed { x, cell ->
                Plant(x, y , cell)
            }
        }

        val visited = mutableSetOf<Plant>()
        val allGardens = mutableListOf<Garden>()
        map.flatten().forEach { plant ->
            if(plant in visited) return@forEach

            val garden = findGardenOfPlant(map, plant)
            allGardens.add(garden)
            visited.addAll(garden.plants)
        }

        return allGardens.sumOf { garden ->
            var sideCount = 0
            garden.plants.forEach { plant ->
                val hasNeighbourUp = garden.plants.any { p -> p.x == plant.x && p.y == plant.y - 1 }
                val hasNeighbourDown = garden.plants.any { p -> p.x == plant.x && p.y == plant.y + 1 }
                val hasNeighbourLeft = garden.plants.any { p -> p.x == plant.x - 1 && p.y == plant.y }
                val hasNeighbourRight = garden.plants.any { p -> p.x == plant.x + 1 && p.y == plant.y }
                val hasNeighbourUpLeft = garden.plants.any { p -> p.x == plant.x - 1  && p.y == plant.y - 1 }
                val hasNeighbourUpRight = garden.plants.any { p -> p.x == plant.x + 1 && p.y == plant.y - 1 }
                val hasNeighbourDownLeft = garden.plants.any { p -> p.x == plant.x - 1 && p.y == plant.y + 1 }
                val hasNeighbourDownRight = garden.plants.any { p -> p.x == plant.x + 1 && p.y == plant.y + 1 }

                if(!hasNeighbourUp && !hasNeighbourRight) sideCount++
                if(!hasNeighbourUp && !hasNeighbourLeft) sideCount++
                if(!hasNeighbourDown && !hasNeighbourRight) sideCount++
                if(!hasNeighbourDown && !hasNeighbourLeft) sideCount++
                if(hasNeighbourUp && hasNeighbourRight && !hasNeighbourUpRight) sideCount++
                if(hasNeighbourUp && hasNeighbourLeft && !hasNeighbourUpLeft) sideCount++
                if(hasNeighbourDown && hasNeighbourRight && !hasNeighbourDownRight) sideCount++
                if(hasNeighbourDown && hasNeighbourLeft && !hasNeighbourDownLeft) sideCount++
            }
            val area = garden.plants.size
            area * sideCount
        }
    }
}
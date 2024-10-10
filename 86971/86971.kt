package `86971`

import kotlin.concurrent.fixedRateTimer
import kotlin.math.abs
import kotlin.math.min

class Solution {

    var minValue = Int.MAX_VALUE
    fun solution(n: Int, wires: Array<IntArray>): Int{

        wires.forEach {
            val newWires = wires.clone().toMutableSet()
            newWires.remove(it)

            val newWiresMap = hashMapOf<Int,MutableSet<Int>>()

            newWires.forEach { wire ->
                val wire0 = newWiresMap.getOrDefault(wire[0], mutableSetOf())
                val wire1 = newWiresMap.getOrDefault(wire[1], mutableSetOf())

                wire0.add(wire[1])
                wire1.add(wire[0])

                newWiresMap[wire[0]] = wire0
                newWiresMap[wire[1]] = wire1
            }

            bfs(n, newWiresMap)
        }

        return this.minValue
    }

    fun bfs(n: Int, wires: Map<Int,MutableSet<Int>>){

        val queue = ArrayDeque<Int>()
        queue.add(1)
        val group = mutableSetOf<Int>()
        group.add(1)

        while(queue.isNotEmpty()){
            val first = queue.first()

            wires.getOrDefault(first, mutableSetOf()).forEach{
                if(!group.contains(it)){
                    group.add(it)
                    queue.add(it)
                }
            }
        }

        val group2Size = n - group.size
        val groupSize = group.size

        this.minValue = min(minValue, abs(groupSize-group2Size))
    }
}

//9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]
fun main(){
    val solution = Solution()
    val value = solution.solution(9, arrayOf(intArrayOf(1,3),intArrayOf(2,3),intArrayOf(4,5),intArrayOf(4,6),intArrayOf(4,),intArrayOf(1,3),intArrayOf(1,3),intArrayOf(1,3)))
    println(value)
}
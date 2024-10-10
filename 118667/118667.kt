package `118667`

import kotlin.math.sign

class Solution {

    val firstQueue = ArrayDeque<Int>()
    val secondQueue = ArrayDeque<Int>()

    var firstSum = 0L
    var secondSum = 0L

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var length = queue1.size * 2
        var count = 0

        firstQueue.addAll(queue1.toList())
        secondQueue.addAll(queue2.toList())

        firstSum = firstQueue.sumOf { it.toLong() }
        secondSum = secondQueue.sumOf { it.toLong() }

        while(count <= length*2 && firstSum != secondSum){
            cycle()
            count++
        }

        if(count>length*2)
            return -1

        return count
    }

    fun cycle(){
        if(firstSum > secondSum){
            val extract = firstQueue.removeFirst()
            secondQueue.add(extract)
            firstSum = firstSum - extract
            secondSum = secondSum + extract
            return
        }
        if(secondSum > firstSum){
            val extract = secondQueue.removeFirst()
            firstQueue.add(extract)
            firstSum = firstSum + extract
            secondSum = secondSum - extract
            return
        }
    }
}

fun main(){
    val solution = Solution()
    val value = solution.solution(intArrayOf(1,1), intArrayOf(1,5))
    println(value)
}
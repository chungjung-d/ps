package `169198`

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

class Solution {
    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {

        var ans = balls.asSequence()
            .map {
                findMinValue(m,n,startX,startY,it[0],it[1])
            }.toList().toIntArray()

        return ans
    }

    fun findMinValue(m: Int, n: Int, startX: Int, startY: Int, ballX: Int, ballY: Int): Int {



        var otherSideBallX = abs(m-ballX)
        var otherSideBallY = abs(n-ballY)
        var otherSideStartX = abs(m-startX)
        var otherSideStartY = abs(n-startY)

        var list = mutableListOf<Double>();
        if(!(startX==ballX && ballY < startY)){
            var value = (ballY.toDouble()+startY.toDouble()).pow(2.0) + (abs(ballX.toDouble()-startX.toDouble())).pow(2.0)
            list.add(value)
        }
        if(!(startX==ballX && ballY > startY)){
            val value = (otherSideBallY.toDouble()+otherSideStartY.toDouble()).pow(2.0) + (abs(ballX.toDouble()-startX.toDouble())).pow(2.0)
            list.add(value)
        }

        if(!(startY==ballY && ballX < startX)){
            val value = (ballX.toDouble()+startX.toDouble()).pow(2.0) + (abs(ballY.toDouble()-startY.toDouble())).pow(2.0)
            list.add(value)
        }

        if(!(startY==ballY && ballX > startX)){
            val value = (otherSideBallX.toDouble()+otherSideStartX.toDouble()).pow(2.0) + (abs(ballY.toDouble()-startY.toDouble())).pow(2.0)
            list.add(value)
        }

        val min = list.sortedBy { it }.toList().first()

        return min.toInt()
    }
}

//main
// 10	10	3	7	[[7, 7], [2, 7], [7, 3]]
fun main() {
    println(
        Solution().solution(10,10,3,7, arrayOf(intArrayOf(7,7), intArrayOf(2,7), intArrayOf(7,3)))
    )
}
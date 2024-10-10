package `161988`

import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solution(sequence: IntArray): Long {

        val evenList = sequence
            .mapIndexed{ index, i ->
                if(index%2 == 0) -i else i
            }

        val oddList = sequence
            .mapIndexed(){ index, i ->
                if(index%2 != 0) -i else i
            }

        var minEven = if (evenList.isNotEmpty()) evenList[0].toLong() else 0L
        var currentEven = 0L
        evenList.forEach{
            var temp = currentEven + it
            currentEven = if(temp < 0 ) 0 else temp
            minEven = max(minEven, currentEven)
        }

        var minOdd = if(oddList.isNotEmpty()) oddList[0].toLong() else 0L
        var currentOdd = 0L
        oddList.forEach{
            var temp = currentOdd  + it
            currentOdd = if(temp < 0 ) 0 else temp
            minOdd = max(minOdd, currentOdd)
        }

        val minimal = max(minOdd,minEven)

        return minimal
    }
}

fun main(){
    val sol = Solution()
    println(sol.solution(intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)))
}
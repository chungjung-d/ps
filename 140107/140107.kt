package `140107`

import kotlin.math.pow
import kotlin.math.sqrt

class Solution {
    fun solution(k: Int, d: Int): Long {

        var a = 0
        var count = 0L
        while(a<=d){
            val len = length(d,a)
            count = count + len/k + 1

            a = a+ k
        }

        return count
    }

    fun length(d: Int, n:Int): Int{
        var l = d.toDouble().pow(2.0) - n.toDouble().pow(2.0)
        return sqrt(l).toInt()
    }
}

//fun main
fun main(){
    val sol = Solution()
    println(sol.solution(1, 5))
}
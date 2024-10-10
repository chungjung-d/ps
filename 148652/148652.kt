package `148652`

import kotlin.math.pow

class Solution {
    fun solution(n: Int, l: Long, r: Long): Int {
        var end = count(n,r)
        var start = count(n,l-1)
        return end-start
    }


    fun count(n: Int, k:Long):Int{

        if(k<=5 || n==1){
            return if(k<3) k.toInt() else (k-1).toInt()
        }

        val totalLength = 5.0.pow(n).toLong()
        val sectionLength = totalLength/5
        val share = k/sectionLength

        val adjustShare = if(k < (sectionLength*3)) share else share - 1
        val shareCount = adjustShare*(4.0.pow(n-1))

        var isNotZero = if(sectionLength * 2 < k && k <= sectionLength*3) false else true

        if(!isNotZero)
            return shareCount.toInt()
        else{
            return shareCount.toInt() + count(n-1,k-(sectionLength*share))
        }

    }

}

fun main(){
    val sol = Solution()
    println(sol.solution(4,125,325))
}
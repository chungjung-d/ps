package `181187`

import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

class Solution {
    fun solution(r1: Int, r2: Int): Long {

        var dots : Long = 0
        var r2Dots: Long  = 0
        var r1Dots: Long  = 0

        for(i in 1..r2){
            r2Dots = sqrt(r2.toDouble().pow(2.0)- i.toDouble().pow(2.0)).toLong();
            val r1DotsDouble = sqrt(r1.toDouble().pow(2.0)- i.toDouble().pow(2.0));
            r1Dots = r1DotsDouble.toLong()

            dots += (r2Dots - r1Dots)

            if(r1DotsDouble % 1 == 0.0 && r1Dots > 0 ) dots++

        }

        dots = dots*4 + 4*(r2-r1 +1)



        return dots
    }
}


fun main(){
    var solution = Solution()

    val ans = solution.solution(2,3)
    println(ans)
}
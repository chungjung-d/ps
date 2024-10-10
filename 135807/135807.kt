package `135807`

import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {


        var gcdA = arrayA.reduce{ a1, a2 ->
            gcd(a1,a2)
        }

        var gcdB = arrayB.reduce{ a1,a2 ->
            gcd(a1,a2)
        }

        if(arrayB.any{ it%gcdA == 0}){
            gcdA = 0
        }

        if(arrayA.any { it%gcdB == 0}){
            gcdB = 0
        }

        return max(gcdA,gcdB)




    }


    fun gcd(a: Int, b:Int): Int{
        val big = max(a,b)
        val small = min(a,b)

        if(small == 0) return big
        return gcd(small,big%small)
    }
}

package `134239`

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Solution {

    private val maps = hashMapOf<Pair<Int,Int>,Double>()
    private val seq = mutableListOf<Long>()

    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {

        var num = k.toLong()

        while(num != 1L){

            seq.add(num)

            if(num%2 == 0L){
                num /= 2
                continue
            }
            if(num%2 != 0L){
                num = num*3 + 1
                continue
            }
        }

        seq.add(1L)

        return ranges.map { splitRange(it,seq.size-1) }.toDoubleArray()

    }

    fun splitRange(range: IntArray, size: Int):Double{
        var a = range[0]
        var b = size + range[1]

        if(a>b) return -1.0
        if(a==b) return 0.0

        var start = min(a,b)
        val end = max(a,b)

        val ranges = mutableListOf<Pair<Int,Int>>()
        while(start < end){
            ranges.add(Pair(start,start+1))
            start++
        }

        return ranges
            .map {
                calculate(it)
            }
            .sum()

    }

    fun calculate(range: Pair<Int,Int>): Double {

        val value = maps.getOrPut(range,{
            val start = seq[range.first]
            val end = seq[range.second]

            abs(start.toDouble() - end.toDouble())/2.0 + min(start,end).toDouble()
        })

        return value
    }
}

//fun main
fun main(){
    val sol = Solution()
    val value = sol.solution(3, arrayOf(intArrayOf(0,0),intArrayOf(1,-2),intArrayOf(3,-3)))

    value.forEach { println(it) }
}
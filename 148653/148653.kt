package `148653`

import kotlin.math.min

class Solution {


    fun solution(storey: Int): Int {


        val queue = ArrayDeque<Pair<Int,Int>>()
        queue.add(Pair(storey,0))
        val minMap = hashMapOf<Int,Int>()
        minMap[storey] =0


        while(queue.isNotEmpty()){
            var current = queue.removeFirst()

            if(current.first == 0) continue
            if(minMap.getOrDefault(current.first, Int.MAX_VALUE) < current.second) continue


            var currentValue = current.first

            var next1 = currentValue / 10
            val next1Count = current.second + currentValue%10



            var next2 = currentValue / 10 + 1
            val next2Count = current.second + (10-currentValue%10)

            while(next1%10 == 0 && next1 != 0){
                next1 = next1 / 10
            }

            while(next2%10 == 0 && next2 != 0){
                next2 = next2 / 10
            }

            if(minMap.getOrDefault(next1, Int.MAX_VALUE) > next1Count){
                queue.add(Pair(next1,next1Count))
                minMap[next1] = next1Count
            }

            if(minMap.getOrDefault(next2, Int.MAX_VALUE) > next2Count){
                queue.add(Pair(next2,next2Count))
                minMap[next2] = next2Count
            }
        }

        return minMap.getOrDefault(0, Int.MAX_VALUE)
    }


}

//fn main
fun main(){
    val sol = Solution()
    val value = sol.solution(2554)
    println(value)
}
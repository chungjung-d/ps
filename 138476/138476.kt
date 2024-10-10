package `138476`

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {

        val map = hashMapOf<Int,Int>()

        tangerine.forEach {
            val value = map.getOrDefault(it,0)
            map[it] = value+1
        }

        val sortedMap = map.toList().sortedBy { it.second }.reversed().map { it.second }

        var count = 0

        for((index,i) in sortedMap.withIndex()){
            count = count + i
            if(count >= k){
                return index + 1
            }
        }

        return sortedMap.size
    }
}

fun main(){
    val sol = Solution()

    println(sol.solution(6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)))
}
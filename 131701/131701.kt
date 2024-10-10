package `131701`

class Solution {
    fun solution(elements: IntArray): Int {

        val size = elements.size

        val doubleElement = elements.toMutableList()
        doubleElement.addAll(elements.toMutableList())

        val set = mutableSetOf<Int>()

        var len = 0
        while(len < size){
            for ( i in 0..size-1){
                val sum = doubleElement.slice(i..i+len).sum()
                set.add(sum)
            }
            len++
        }

        return set.size
    }
}

fun main(){
    val sol = Solution()
    println(sol.solution(intArrayOf(7,9,1,1,4)));
}

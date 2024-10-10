package `147354`

import java.util.PriorityQueue

class Solution {


    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {

        val pq = PriorityQueue<IntArray>(){a1, a2 ->
            if (a1[col-1] == a2[col-1]) {
                -(a1[0].compareTo(a2[0]))
            } else {
                a1[col-1].compareTo(a2[col-1])
            }

            when{
                a1[col-1] < a2[col-1] -> -1
                (a1[col-1] == a2[col-1]) && (a1[0] > a2[0]) -> -1
                else -> 1
            }
        }

        pq.addAll(data)




        var ans = 0
        var lists = mutableListOf<IntArray>()

        while(pq.isNotEmpty()){
            lists.add(pq.poll())
        }

        for(i in row_begin..row_end){
            val list = lists[i-1]
            val sums = list.sumOf { it % i }
            ans = ans.xor(sums)
        }

        return ans;
    }

}
//[[2,2,6],[1,5,10],[4,2,9],[3,8,3]]	2	2	3
fun main(){
    val sol = Solution()
    println(sol.solution(arrayOf(intArrayOf(2,2,6), intArrayOf(1,5,10), intArrayOf(4,2,9), intArrayOf(3,8,3)),2,2,3))
}
package `142085`

import java.util.PriorityQueue
import kotlin.math.max

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {

        val pq = PriorityQueue<Int>(){
            a1, a2 -> (a1.compareTo(a2))
        }

        if(k >= enemy.size) return enemy.size

        pq.addAll(enemy.slice(0..k-1))

        var unit = n
        var round = k

        for(i in k..enemy.size-1){
            pq.add(enemy[round])
            val enemys = pq.poll()

            if(unit >= enemys){
                unit = unit-enemys
                round++
            } else break
        }

        return round

    }




}

//fun main
//7	3	[4, 2, 4, 5, 3, 3, 1]
fun main(){
    val sol = Solution()
    println(sol.solution(7,3,intArrayOf(4, 2, 4, 5, 3, 3, 1)))
}
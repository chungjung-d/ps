package `131130`

import java.util.Collections.max

class Solution {
    fun solution(cards: IntArray): Int {

        var visitedCards = mutableSetOf<Int>()
        var groups = mutableListOf<List<Int>>()


        var current = 0
        while(visitedCards.size < cards.size && current<cards.size){

            if(visitedCards.contains(current)){
                current++
                continue
            }

            var groupPoint = current
            val group = mutableListOf<Int>()
            while(true){
                if(visitedCards.contains(groupPoint)){
                    break
                }

                group.add(cards[groupPoint])
                visitedCards.add(groupPoint)
                groupPoint = cards[groupPoint]-1
            }

            groups.add(group.toList())

        }

        val sumGroups = groups.map { it.size }.toMutableList()
        val first = max(sumGroups)
        sumGroups.remove(first)
        val second = if(sumGroups.isNotEmpty()) max(sumGroups) else 0

        return first*second

    }
}

fun main(){
    val solution = Solution()

    val value = solution.solution(intArrayOf(1,2))
    println(value)
}
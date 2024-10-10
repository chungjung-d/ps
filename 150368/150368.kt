package `150368`

import java.util.PriorityQueue

class Solution {

    class User(val percent: Int, val costLimit: Int) {

        fun calculateCosts(saleRates: List<Int>, costs: List<Int>): Int {

            var totalPrice = 0

            for( (index, cost) in costs.withIndex()){
                if(saleRates[index] >= this.percent){
                    totalPrice += calculateCostOfSaleEmoticon(saleRates[index],cost)
                }
            }

            return if(totalPrice >= costLimit) -1 else totalPrice
        }

        fun calculateCostOfSaleEmoticon(salePercent: Int,emoticonCost: Int): Int{
            return emoticonCost - (salePercent * emoticonCost / 100)
        }

        companion object{
            fun intArrayToUser(array: IntArray) : User{
                return User(array[0],array[1])
            }
        }
    }

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        val getCombination = getCombination(emoticons.size)

        val pq = PriorityQueue<Pair<Int,Int>>(){ a1, a2 ->
            when{
                a1.first > a2.first -> -1
                a1.first == a2.first && a1.second > a2.second -> -1
                else -> 1
            }
        }

        val userList = users.map {
            User.intArrayToUser(it)
        }.toList()

        getCombination.forEach{ saleRates->
            val value = userList.map {
                it.calculateCosts(saleRates,emoticons.toList())
            }

            val emoticonPlus = value.count { it == -1 }
            val totalSale = value.filter { it != -1 }.sum()

            pq.add(Pair(emoticonPlus,totalSale))

        }

        return intArrayOf(pq.first().first,pq.first().second)
    }




    fun getCombination(size: Int) : List<List<Int>> {
        var queue = ArrayDeque<MutableList<Int>>()
        queue.add(mutableListOf())

        var value = mutableListOf<List<Int>>()
        var percents = listOf(10,20,30,40)

        while(queue.isNotEmpty()){

            val current = queue.removeFirst()
            if(current.size == size) {
                value.add(current.toList())
                continue
            }

            percents.forEach{
                val next = current.asSequence().toMutableList()
                next.add(it)
                queue.add(next)
            }
        }

        return value.toList()
    }

}

//fun main
//[[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]]	[1300, 1500, 1600, 4900]

fun main(){
    val solution = Solution()
    println(solution.solution(arrayOf(intArrayOf(40,2900), intArrayOf(23,10000), intArrayOf(11,5200), intArrayOf(5,5900), intArrayOf(40,3100), intArrayOf(27,9200), intArrayOf(32,6900)), intArrayOf(1300,1500,1600,4900)).toList())
}
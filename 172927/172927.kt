package `172927`

import kotlin.math.min


class Solution {
    fun solution(picks: IntArray, minerals: Array<String>): Int {

        val picksCount = picks.sum()
        val canMine = min(picksCount*5,minerals.size)

        val mineralsOfValue = minerals.map {
            when(it) {
                "diamond" -> 25
                "iron" -> 5
                "stone" -> 1
                else -> 0
            }
        }.slice(0..canMine-1)

        val mineralsSize = mineralsOfValue.size
        val setSize: Long = if ((mineralsSize % 5) == 0) (mineralsSize/5).toLong() else (mineralsSize/5).toLong() + 1

        val mineralSets = mutableListOf<List<Int>>()

        for (i in 1 ..setSize){
            val startIdx = (i-1)*5
            val endIdx = if( i*5 -1 < mineralsSize - 1) i*5 -1 else mineralsSize - 1

            mineralSets.add(mineralsOfValue.slice(startIdx.toInt()..endIdx.toInt()))
        }

        val sortedMineralSets : MutableList<List<Int>> = mineralSets.sortedBy {
            it.sum()
        }.toMutableList().asReversed()

        var ans = 0

        val pickList = mutableListOf<String>()

        for((index,p) in picks.withIndex()){
            for( i in 1..p){
                var pick = when(index){
                    0 -> "D"
                    1 -> "I"
                    2 -> "S"
                    else -> "X"
                }
                pickList.add(pick)
            }
        }


        for (p in pickList){

            if(sortedMineralSets.isEmpty()) break

            val first = sortedMineralSets.removeFirst()

            first.forEach{
                if(p == "D"){
                    val value = when(it){
                        25 -> 1
                        5 -> 1
                        1 -> 1
                        else -> 0
                    }
                    ans += value
                }
                if(p == "I"){
                    val value = when(it){
                        25 -> 5
                        5 -> 1
                        1 -> 1
                        else -> 0
                    }
                    ans += value
                }
                if(p == "S"){
                    val value = when(it){
                        25 -> 25
                        5 -> 5
                        1 -> 1
                        else -> 0
                    }
                    ans += value
                }
            }

        }

        return ans
    }
}

//main
//picks	minerals
//[0, 1, 1]	["diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"]
fun main () {
    var sol = Solution()

    var picks = intArrayOf(0,1,1)
    var minerals = arrayOf("diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond")

    print(sol.solution(picks,minerals))

}
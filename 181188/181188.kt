package `181188`

class Solution {
    fun solution(targets: Array<IntArray>): Int {

        var sortedTarget = targets.asSequence()
            .sortedBy {it[1]}
            .map { Pair<Int,Int>(it[0],it[1]) }
            .toList()

        var end = 0
        var count = 0

        sortedTarget.forEach{
            if(end <= it.first){
                end = it.second
                count += 1
            };
        }


        return count
    }



}




// [[4,5],[4,8],[10,14],[11,13],[5,12],[3,7],[1,4]]	3

fun main () {

    var sol = Solution()

    var arr = arrayOf(
        intArrayOf(4,5),
        intArrayOf(4,8),
        intArrayOf(10,14),
        intArrayOf(11,13),
        intArrayOf(5,12),
        intArrayOf(3,7),
        intArrayOf(1,4)
    )

    print(sol.solution(arr))
}
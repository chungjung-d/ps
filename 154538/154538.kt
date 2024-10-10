package `154538`

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {

        val numberSet = mutableSetOf<Int>()

        val functionList : List<(Int) -> Int> = listOf(
            {t -> t + n},
            {t -> t*2},
            {t -> t*3}
        )
        val queue = ArrayDeque<Pair<Int,Int>>()
        queue.add(Pair(x,0))

        if(x==y) return 0

        while (queue.isNotEmpty()){
            val current = queue.removeFirst()

            for(f in functionList){
                val next  = f(current.first)

                if(next == y){
                    return current.second + 1
                }

                if(!numberSet.contains(next) && next < y){
                    queue.add(Pair(next,current.second+1))
                    numberSet.add(next)
                }
            }

        }

        return -1
    }
}


fun main() {
    val sol = Solution()

    val value = sol.solution(2,5,4)
    println(value)
}
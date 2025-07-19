package `388352`

class Solution {
    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        var combs = combinations(5,n);
//        println(combs)

        q.forEachIndexed{index, value ->
            val corrects = ans[index]

            val newCombs =  combs.filter { comb ->
                comb.filter { value.contains(it) }.size == corrects
            }
            combs = newCombs
        }

        return combs.size
    }

    fun combinations(n: Int,k: Int): List<List<Int>> {
        val oddDeque = ArrayDeque<List<Int>>();
        val evenDeque = ArrayDeque<List<Int>>();
        oddDeque.add(listOf())

        for(i in 1..k){
            val currentDeque = if (i%2==0) evenDeque else oddDeque
            val pushDeque = if (i%2==0) oddDeque else evenDeque

            while(currentDeque.isNotEmpty()){
                val currentComb = currentDeque.removeFirst()
                if(currentComb.size == n){
                    pushDeque.add(currentComb)
                } else {
                    val newComb1 = currentComb + i
                    pushDeque.add(newComb1)
                    pushDeque.add(currentComb)
                }
            }
        }

        val returnDeque = if (k%2 == 0) oddDeque else evenDeque

        return returnDeque.filter { it.size == n }

    }
}


fun main(){
    val sol = Solution()
    sol.solution(15, arrayOf(
        intArrayOf(2, 3, 9, 12, 13),
        intArrayOf(1, 4, 6, 7, 9),
        intArrayOf(1, 2, 8, 10, 12),
        intArrayOf(6, 7, 11, 13, 15),
        intArrayOf(1, 4, 10, 11, 14)
    ), intArrayOf(2,1,3,0,1))
}



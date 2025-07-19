package `389479`

class Solution {
    fun solution(players: IntArray, m: Int, k: Int): Int {

        // 서버 1대는 dqfault로 존재
        val deque = ArrayDeque<Int>()
        var count = 0;

        players.forEachIndexed{ index,users ->

            println("index:" + index.toString())
            println("users:" + users)
            println(deque.joinToString(","))
            println(count)


            while ( !deque.isEmpty() ) {
                if(deque.first() < index){
                    deque.removeFirst()
                }
                else {
                    break
                }
            }

            val currentServer = deque.size + 1

            val needServer = (users / m) + 1
            val addServer = if (needServer - currentServer <= 0) 0 else needServer - currentServer

            println("currentServer: " + currentServer)
            println("needServer: " + needServer)
            println("addServer: "+ addServer)

            for(i in 0 until addServer){
                deque.addLast(index + k - 1)
                count++
            }

            println("=======================================")

        }

        return count

    }
}

fun main () {
    val array = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
    val m = 3
    val k = 5

    val sol = Solution()
    val result = sol.solution(array, m, k)
    println(result)
}
package `169199`

import java.util.*

class Solution {
    fun solution(board: Array<String>): Int {
        var answer: Int = -1
        val dx = arrayOf(0, 0, -1, 1)  // 상, 하, 좌, 우
        val dy = arrayOf(-1, 1, 0, 0)  // 상, 하, 좌, 우
        val queue = LinkedList<Pair<Int, Int>>()
        val visited = Array(board.size) { IntArray(board[0].length) }

        // 지도 셋팅
        for(i in 0 until board.size) {
            for(j in 0 until board[i].length) {
                if(board[i][j] == 'R') {    // 시작 위치
                    visited[i][j] = 1
                    queue.offer(Pair(i, j)) // 시작 점 셋팅
                }
            }
        }

        while(queue.isNotEmpty()) {
            val (x, y) = queue.poll()

            if(board[x][y] == 'G') { // 목표 위치
                answer = visited[x][y] - 1
                break;
            }

            for(i in 0 until 4) {   // 상, 하, 좌, 우
                var (nextX, nextY) = Pair(x, y)

                // 장애물 만나기 전까지 한방향으로 이동
                while(nextX + dx[i] >= 0 && nextX + dx[i] < board.size && nextY + dy[i] >= 0 && nextY + dy[i] < board[0].length && board[nextX+dx[i]][nextY+dy[i]] != 'D')  {
                    nextX += dx[i]
                    nextY += dy[i]
                }

                if(visited[nextX][nextY] == 0) { // 끝점에 도착한적이 없을 경우
                    visited[nextX][nextY] = visited[x][y] + 1 // 방문횟수 업데이트
                    queue.offer(Pair(nextX, nextY))
                }
            }
        }

        return answer
    }

}

fun main() {
    println(
        Solution().solution(
            arrayOf(
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D...."
            )
        )
    )
}

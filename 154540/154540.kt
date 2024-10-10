package `154540`

class Solution {


    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>): Pair<Int,Int>{
        return Pair(this.first + other.first, this.second + other.second);
    }

    private val direction = listOf(Pair(0,1), Pair(1,0), Pair(-1,0), Pair(0,-1))
    private var mapList = listOf<CharArray>();

    private val ansList = mutableListOf<Int>()

    fun solution(maps: Array<String>): IntArray {

        maps.forEachIndexed{ index, s ->
            
        }

        val a = MAx

        mapList = maps.asSequence()
            .map { it.toCharArray() }
            .toList();

        for((col,rows) in mapList.withIndex()){
            for((row,value) in rows.withIndex()){
                if(value != 'X'){
                    val sumValue = BFS(col,row)
                    ansList.add(sumValue)
                }
            }
        }

        return if(ansList.isEmpty()) intArrayOf(-1) else ansList.sortedBy { it }.toIntArray()

    }

    private fun BFS(colIdx : Int, rowIdx: Int): Int{
        val queue = ArrayDeque<Pair<Int,Int>>();
        val colSize = mapList.size
        val rowSize = mapList[0].size

        var sum = 0;

        queue.add(Pair(colIdx,rowIdx))
        sum = sum + Character.getNumericValue(mapList[colIdx][rowIdx]);
        mapList[colIdx][rowIdx] = 'X';

        while(queue.isNotEmpty()){
            val current = queue.removeFirst();

            direction.forEach{
                val next = current + it

                if(next.first >=0 && next.second >=0 && next.first < colSize && next.second < rowSize ){
                    if(mapList[next.first][next.second] != 'X'){
                        queue.add(next)
                        sum = sum + Character.getNumericValue(mapList[next.first][next.second]);
                        mapList[next.first][next.second] = 'X'
                    }
                }
            }

        }

        return sum

    }


}

//["X591X","X1X5X","X231X", "1XXX1"]
fun main(){

    val solution = Solution();

    println(
        solution.solution(
            arrayOf(
                "X591X",
                "X1X5X",
                "X231X",
                "1XXX1"
            )
        ).toList()
    )
}
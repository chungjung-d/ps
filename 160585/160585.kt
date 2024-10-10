package `160585`

class Solution {
    fun solution(board: Array<String>): Int {

        var oCount = 0
        var xCount = 0

        var oWin = false
        var xWin = false

        var oCoordinate = mutableListOf<Pair<Int,Int>>()
        var xCoordinate = mutableListOf<Pair<Int,Int>>()

        var winCoordinate = listOf(
            listOf(Pair(0,0),Pair(0,1),Pair(0,2)),
            listOf(Pair(1,0),Pair(1,1),Pair(1,2)),
            listOf(Pair(2,0),Pair(2,1),Pair(2,2)),

            listOf(Pair(0,0),Pair(1,0),Pair(2,0)),
            listOf(Pair(0,1),Pair(1,1),Pair(2,1)),
            listOf(Pair(0,2),Pair(1,2),Pair(2,2)),

            listOf(Pair(0,0),Pair(1,1),Pair(2,2)),
            listOf(Pair(2,0),Pair(1,1),Pair(0,2)),
        )


        for ((col,rows) in board.withIndex()){
            rows.forEachIndexed{ row, value ->
                when(value){
                    'X' -> {
                        xCount++
                        xCoordinate.add(Pair(col,row))
                    }
                    'O' -> {
                        oCount++
                        oCoordinate.add(Pair(col,row))
                    }
                }
            }
        }

        if(xCount > oCount) return 0
        if(oCount >= xCount + 2) return 0

        winCoordinate.forEach{
            if(oCoordinate.containsAll(it)) oWin = true
            if(xCoordinate.containsAll(it)) xWin = true
        }

        if(oWin == true && xWin == true) return 0
        if(oWin == true && !((oCount - xCount) == 1) ) return 0
        if(xWin == true && !((xCount-oCount)==0)) return 0


        return 1

    }
}

//main
//["OOO", "...", "XXX"]
fun main(){
    val sol = Solution()
    println(sol.solution(arrayOf("OOO", "...", "XXX")))
}

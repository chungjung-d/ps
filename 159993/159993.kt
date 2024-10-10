package `159993`


class Solution {

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int> {
        return Pair(this.first+other.first, this.second+other.second)
    }

    fun solution(maps: Array<String>): Int {

        val leverQueue = ArrayDeque<Triple<Int,Int,Int>>()
        val leverVisited = mutableSetOf<Pair<Int,Int>>()
        val mapColSize = maps.size
        val mapRowSize = maps[0].length

        val moveDirection = listOf(Pair(1,0),Pair(0,1),Pair(0,-1),Pair(-1,0))


        maps.forEachIndexed{ col,rows ->
            rows.forEachIndexed{ row, value ->
                if(value == 'S'){
                    leverQueue.add(Triple(col,row,0))
                    leverVisited.add(Pair(col,row))
                }
            }
        }


        var findLeverState = Triple<Int,Int,Int>(-1,-1,-1)

        while(leverQueue.isNotEmpty()){
            val (currentCol, currentRow, currentTime) = leverQueue.removeFirst();

            val current = Pair(currentCol,currentRow)

            for(delta in moveDirection){
                val next = current + delta
                if(next.first >=0 && next.second >=0 && next.first < mapColSize && next.second < mapRowSize){
                    when(maps[next.first][next.second]){
                        'L' -> {
                            findLeverState = Triple(next.first,next.second,currentTime+1)
                            break
                        }
                        'X' -> continue
                        else -> {
                            if(!leverVisited.contains(Pair(next.first,next.second))){
                                leverQueue.add(Triple(next.first,next.second,currentTime+1))
                                leverVisited.add(Pair(next.first,next.second))
                            }
                        }
                    }
                }
            }

            if(!findLeverState.equals(Triple(-1,-1,-1))) break;

        }

        if(findLeverState.equals(Triple(-1,-1,-1))) return -1


        val exitQueue = ArrayDeque<Triple<Int,Int,Int>>()
        exitQueue.add(findLeverState)
        val exitVisited = mutableSetOf<Pair<Int,Int>>()
        exitVisited.add(Pair(findLeverState.first,findLeverState.second))

        var answer = Triple<Int,Int,Int>(-1,-1,-1)

        while(exitQueue.isNotEmpty()){
            val (currentCol, currentRow, currentTime) = exitQueue.removeFirst();

            val current = Pair(currentCol,currentRow)

            for(delta in moveDirection){
                val next = current + delta
                if(next.first >=0 && next.second >=0 && next.first < mapColSize && next.second < mapRowSize){
                    when(maps[next.first][next.second]){
                        'E' -> {
                            answer = Triple(next.first,next.second,currentTime+1)
                            break
                        }
                        'X' -> continue
                        else -> {
                            if(!exitVisited.contains(Pair(next.first,next.second))){
                                exitQueue.add(Triple(next.first,next.second,currentTime+1))
                                exitVisited.add(Pair(next.first,next.second))
                            }
                        }
                    }
                }
            }

            if(!answer.equals(Triple(-1,-1,-1))) break;
        }

        if(answer.equals(Triple(-1,-1,-1))) return -1

        return answer.third
    }
}


//main ()
//["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"]
fun main(){
    val sol = Solution()
    println(sol.solution(arrayOf("LOOXS","OOOOX","OOOOO","OOOOO","EOOOO")))
}
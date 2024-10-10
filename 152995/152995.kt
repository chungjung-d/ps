package `152995`

class Solution {
    fun solution(scores: Array<IntArray>): Int {


        val tripleScore = scores.mapIndexed { index, ints -> Triple(index,ints[0],ints[1]) }

        val validatingSets = tripleScore.sortedWith{a1,a2 -> when{
                a1.second > a2.second -> -1
                a1.second == a2.second && a1.third < a2.third -> -1
                else -> 1
            }
        }.toMutableList()

        var maxThird = validatingSets[0].third
        val removeSets = mutableSetOf<Triple<Int,Int,Int>>()

        for( i in validatingSets ){
            if(i.third < maxThird) {
                removeSets.add(i)
                if(i == tripleScore[0])
                    return -1
            } else maxThird = i.third
        }

        validatingSets.removeAll(removeSets)

        val sortedScore = validatingSets.sortedWith { a1, a2 -> when{
                a1.third + a1.second > a2.third + a2.second -> -1
                a1.third + a1.second == a2.third + a2.second && a1.first < a2.first -> -1
                else -> 1
            }
        }

        return sortedScore.indexOf(tripleScore[0]) + 1
    }
}

fun main(){
    val solution = Solution()
    val value = solution.solution(
        arrayOf(
            intArrayOf(2,2),
            intArrayOf(1,4),
            intArrayOf(3,2),
            intArrayOf(3,2),
            intArrayOf(2,1)
        )
    )

    println(value)
}
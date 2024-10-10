package `154539`

class Solution {
    fun solution(numbers: IntArray): IntArray {

        val reverseNumber = numbers.reversed()

        val ansList = mutableListOf<Int>()
        val stack = ArrayDeque<Int>()

        reverseNumber.forEach{

            while(stack.isNotEmpty()){
                if(stack.last() > it){
                    break
                }
                stack.removeLast()
            }

            if(stack.isEmpty()) ansList.add(-1)
            else ansList.add(stack.last())

            stack.add(it)

        }

        return ansList.reversed().toIntArray()

    }
}
//[2, 3, 3, 5]
//fun main

fun main(){
    val solution = Solution()
    println(solution.solution(intArrayOf(9, 1, 5, 3, 6, 2)).toList())
}

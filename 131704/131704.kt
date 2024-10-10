package `131704`

class Solution {
    fun solution(order: IntArray): Int {

        var mutOrder = order.toMutableList()
        val boxes = order.sorted().toMutableList()

        val stack = ArrayDeque<Int>()
        var count = 0

        while ( mutOrder.isNotEmpty() ){

            val ord = mutOrder.removeFirst()

            if(stack.isNotEmpty() && boxes.isNotEmpty()){
                if(stack.last() != ord && boxes.first() > ord) break
            }
            if(stack.isEmpty() && boxes.isNotEmpty()){
                if(boxes.first() > ord) break
            }
            if(stack.isNotEmpty() && boxes.isEmpty()){
                if(stack.last() != ord) break
            }

            if(stack.isNotEmpty() && stack.last() == ord) {
                stack.removeLast()
                count++
                continue
            }

            if(boxes.isNotEmpty() && boxes.first() == ord){
                boxes.removeFirst()
                count++
                continue
            }

            while (boxes.first() < ord){
                stack.add(boxes.removeFirst())
            }

            boxes.removeFirst()
            count++
        }

        return count
    }
}

fun main(){
    val sol = Solution()
    println(sol.solution(intArrayOf(5,4,3,2,1)))
}
package `131127`

class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val hashMap = HashMap<String, Int>()

        want.forEachIndexed{index, want ->
            hashMap.put(want, number[index])
        }
        val wantList = hashMap.toList()

        var ableDay = 0

        var end = if (discount.size < 10) discount.size -1 else 9
        val discountMap = discount.sliceArray(0..end).groupBy{it}.mapValues { it.value.size }.toMutableMap()

        for(i in 0..discount.size-1){
//            println(discountMap)
//            println(wantList)
//            println(ableDay)
//            println(end)

            val canRegister = wantList.all{ it ->
                discountMap.getOrDefault(it.first,0) >= it.second
            }

            if (canRegister){
                ableDay++
            }

            discountMap.set(discount[i], discountMap.getOrDefault(discount[i],1) - 1)

            if (discount.size -1 > end){
                end++
                discountMap.set(discount[end],discountMap.getOrDefault(discount[end],0)+1)
            }
        }

        return ableDay
    }
}

fun main() {
    val solution = Solution()
    solution.solution(arrayOf("banana", "apple", "rice", "pork", "pot"), intArrayOf(3, 2, 2, 2, 1), arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"))
}
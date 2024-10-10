package `132265`

class Solution {
    fun solution(topping: IntArray): Int {

        val aTopping = hashMapOf<Int,Int>()
        val aSet = mutableSetOf<Int>()
        val bTopping = hashMapOf<Int,Int>()

        var count = 0

        topping.forEach {
            var toppingCount = aTopping.getOrDefault(it,0)
            toppingCount++
            aTopping[it] = toppingCount
            aSet.add(it)
        }

        topping.forEach {
            var aToppingCount = aTopping.get(it)!!
            aToppingCount--
            aTopping[it] = aToppingCount
            if(aToppingCount == 0) aSet.remove(it)


            var bToppingCount = bTopping.getOrDefault(it,0)
            bToppingCount++
            bTopping[it] = bToppingCount

            if(aSet.size  == bTopping.keys.size) count++

        }

        return count
    }
}
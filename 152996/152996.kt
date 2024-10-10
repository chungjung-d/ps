package `152996`

    class Solution {
        fun solution(weights: IntArray): Long {
            val fnList : List<(Double) -> Double> = listOf(
                {t -> t},
                {t -> t/2.0},
                {t -> t*2.0/3.0},
                {t -> t*3.0/4.0},
            )

            val weightMap = hashMapOf<Double,Int>()

            var ans = 0L
            weights.sort()

            weights.map{it.toDouble()}.forEachIndexed{index, value ->

                fnList.forEach{
                    val calculatedValue = it(value)
                    val count = weightMap.get(calculatedValue)

                    if(count != null){
                        ans += count
                    }
                }

                val count = weightMap.getOrDefault(value,0)
                weightMap[value] = count + 1
            }


            return ans
        }
    }

fun main(){
    val sol = Solution()
    val value = sol.solution(
        intArrayOf(
        100,180,360,100,270
        )
    )

    println(value)
}
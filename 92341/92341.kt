package `92341`

import kotlin.math.ceil

class Solution {
    fun solution(fees: IntArray, records: Array<String>):IntArray{

        val recordsTriple = mutableListOf<Triple<Int, Int, String>>()

        records.forEach {
            val record = it.split(" ")
            val time = record[0].split(":")
            val minutes = time[0].toInt()*60 + time[1].toInt()

            recordsTriple.add(Triple(minutes,record[1].toInt(),record[2]))
        }

        val recordMap  = recordsTriple.groupBy { it.second }

        val ans = recordMap.map {
            val number = it.key

            val value = it.value.toMutableList()

            if(value.size%2 != 0){
                value.add(Triple(23*60+59,number,"OUT"))
            }

            val sum =  value.sumOf { el ->
                if(el.third=="IN") -el.first else el.first
            }

            Pair(number,sum)
        }.sortedBy {
            it.first
        }

        val sumAns = ans.map {
            if(it.second < fees[0] ){
                fees[1].toDouble()
            }else{
                fees[1] + ceil((it.second - fees[0]).toDouble()/fees[2].toDouble())*fees[3]
            }
        }.map { it.toInt() }.toIntArray()


        return sumAns
    }
}

//[180, 5000, 10, 600]	["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]
fun main(){
    val solution = Solution()
    val value = solution.solution(intArrayOf(180, 5000, 10, 600), arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"))
}
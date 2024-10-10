package `178870`

import java.util.PriorityQueue

class Solution2 {
    fun solution(sequence: IntArray, k: Int): IntArray {

        val pq = PriorityQueue<IntArray> { o1, o2 ->
            when {
                o1[1] - o1[0] < o2[1] - o2[0] -> -1
                o1[1] - o1[0] == o2[1] - o2[0] && o1[0] < o2[0] -> -1
                else -> 1
            }
        }

        var sum = sequence[0]
        var startIdx = 0
        var endIdx = 0

        while(startIdx <= endIdx){
            if( sum ==k ){
                pq.add(intArrayOf(startIdx,endIdx))
                sum -= sequence[startIdx];
                startIdx++
                continue
            }

            when(sum < k){
                true -> {
                    if(endIdx == sequence.size-1) break;

                    endIdx++;
                    sum +=sequence[endIdx]
                    continue;
                }

                false -> {
                    sum -= sequence[startIdx]
                    startIdx++
                    continue;
                }
            }
        }

        return pq.poll()

    }




}
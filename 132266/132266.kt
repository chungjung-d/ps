package `132266`

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val hashMap = hashMapOf<Int,Int>()

        val roadMap = hashMapOf<Int,MutableSet<Int>>()

        roads.forEach {
            val a = roadMap.getOrDefault(it[0], mutableSetOf<Int>())
            a.add(it[1])
            roadMap[it[0]] = a

            val b = roadMap.getOrDefault(it[1], mutableSetOf<Int>())
            b.add(it[0])
            roadMap[it[1]] = b

        }

        val queue = ArrayDeque<Pair<Int,Int>>()

        hashMap[destination] = 0
        queue.add(Pair(destination,0))


        while(queue.isNotEmpty()){
            val current = queue.removeFirst()

            val map = roadMap.getOrDefault(current.first, mutableSetOf())
            map.forEach{ next ->
                if(!hashMap.contains(next)){
                    queue.add(Pair(next,current.second+1))
                    hashMap.put(next,current.second+1)
                }
            }
        }

        return sources.map { hashMap.getOrDefault(it,-1) }.toIntArray()

    }
}
package `178870`

class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {

        val reverseSequence = sequence.asSequence().toList().asReversed();
        val sequenceSize = sequence.size;

        var answerSequence = listOf<Int>()
        var answerSequenceIndex : Pair<Int,Int> = Pair(0,0)

        var sameValue : Int? = null
        var escape = false;

        var index = 0
        while (index < sequenceSize ) {
            var sumValue = 0;

            var endIndex = index - 1
            val startValue = reverseSequence[index]
            var endValue = 0;
            while (sumValue < k) {

                endIndex++;
                if (endIndex >= sequenceSize) {
                    escape = true;
                    break
                };
                sumValue += reverseSequence[endIndex];
                endValue = reverseSequence[endIndex]
            }

            if (escape) break;

            if (sumValue == k) {
                answerSequence = reverseSequence.subList(index, endIndex + 1)
                answerSequenceIndex = Pair(getIndex(sequenceSize, endIndex), getIndex(sequenceSize, index))

                if (answerSequence.all { it == answerSequence[0] }) {
                    sameValue = answerSequence[0]
                }

                break
            }

            if(startValue.equals(endValue)){
                index = reverseSequence.lastIndexOf(startValue) - (endIndex - index)
            }

            index++
        }

        if(sameValue != null){
            val ansIndex = sequence.indexOf(sameValue)
            answerSequenceIndex = Pair(ansIndex, ansIndex+answerSequence.size-1)
        }

        var answer: IntArray = intArrayOf(answerSequenceIndex.first, answerSequenceIndex.second)
        return answer
    }

    fun getIndex(size:Int, reversedIndex: Int): Int{
        return size-reversedIndex - 1
    }
}

fun main() {
    val array = intArrayOf(2, 2, 2, 2, 2)

    val solution = Solution2();

    var ans = solution.solution(array,6)

    println(ans[0])
    println(ans[1])


}


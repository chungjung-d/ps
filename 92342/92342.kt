package `92342`

class Solution {

    var result = intArrayOf(-1)
    var apeach = intArrayOf()
    var n = 0
    var maxValue = 0

    fun solution(n: Int, info: IntArray): IntArray {

        this.apeach = info
        this.n = n

        val lionArray = intArrayOf(0,0,0,0,0,0,0,0,0,0,0)
        dfs(lionArray,10)

        return this.result
    }

    fun dfs(lionArray : IntArray, k : Int){

        if(k == -1){
            lionArray[10] = n - lionArray.sum()
            val isWin = validate(lionArray)
            if(isWin) {
                result = lionArray
            }
            return
        }

        val leftArrow = n - lionArray.sum()
        if(leftArrow == 0){
            val isWin = validate(lionArray)
            if(isWin) {
                result = lionArray
            }
            return
        }

        val currentApeach = apeach[k]

        if(currentApeach < leftArrow){
            val winArray = lionArray.clone()
            winArray[k] = currentApeach + 1
            dfs(winArray, k-1)
        }


        dfs(lionArray,k-1)
    }

    fun validate(lionArray: IntArray):Boolean{

        var lionScore = 0
        var apeachScore = 0

        for((index,lion) in lionArray.withIndex()){

            if(lion == 0 && apeach[index] == 0){
                continue
            }

            if(lion > apeach[index]){
                lionScore = lionScore + (10-index)
            } else {
                apeachScore = apeachScore + (10 - index)
            }
        }

        if(lionScore - apeachScore > maxValue) {
            maxValue = lionScore - apeachScore
            return true
        } else {
            return false
        }
    }
}

fun main(){
    val solution = Solution()
    val value = solution.solution(10, intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3))

    value.forEach { println(it) }
}
package `176962`


class Solution {

    class Plan(var name: String, var time: Long, var playTime: Long, var remainTime: Long)


    fun solution(plans: Array<Array<String>>): Array<String> {

        val queue = ArrayDeque<Plan>()
        val stack = mutableListOf<Plan>()
        val ans = mutableListOf<String>()

        var planList = plans.asSequence()
            .map {
                Plan(it[0],convertTimeToMinute(it[1]),it[2].toLong(),it[2].toLong())
            }
            .sortedBy { it.time }
            .forEach { queue.addLast(it) }

        var currentTime = queue.first().time

        while(!queue.isEmpty()|| stack.size != 0){
            if(queue.isEmpty()){
                stack.reversed().forEach{ans.add(it.name)}
                break
            }

            if(currentTime == queue.first().time){

                var firstPlan = queue.removeFirst()

                if(queue.isEmpty()){
                    ans.add(firstPlan.name)
                    continue
                }

                var secondPlan = queue.first()
                if(firstPlan.time + firstPlan.remainTime > secondPlan.time){
                    val remainTime = firstPlan.time + firstPlan.remainTime - secondPlan.time
                    firstPlan.remainTime = remainTime
                    stack.add(firstPlan)

                    currentTime = secondPlan.time
                    continue
                }

                currentTime = firstPlan.time + firstPlan.remainTime
                ans.add(firstPlan.name)
                continue
            }

            if(currentTime < queue.first().time){
                var remainPlan = stack.removeLastOrNull();
                if(remainPlan == null ){
                    currentTime = queue.first().time
                    continue
                }

                if(currentTime + remainPlan.remainTime > queue.first().time){
                    val remainTime = currentTime + remainPlan.remainTime - queue.first().time
                    remainPlan.remainTime = remainTime
                    stack.add(remainPlan)

                    currentTime = queue.first().time
                    continue
                }

                currentTime = remainPlan.remainTime + currentTime;
                ans.add(remainPlan.name)
                continue
            }

        }



        return ans.toTypedArray()
    }

    fun convertTimeToMinute(time: String): Long{
        val timeData = time.split(":")
        return timeData[0].toLong()*60 + timeData[1].toLong()
    }
}

//[["korean", "11:40", "30"], ["english", "12:10", "20"], ["math", "12:30", "40"]]
fun main(){
    val sol = Solution()
    val plans = arrayOf(
        arrayOf("korean", "11:40", "30"),
        arrayOf("english", "12:10", "20"),
        arrayOf("math", "12:30", "40")
    )


    print(sol.solution(plans).contentToString())
}
package `155651`

import java.util.PriorityQueue

class Solution {


    fun solution(book_time: Array<Array<String>>): Int {

        val bookList =  mutableListOf<Pair<Int,Int>>()

        val books = book_time.asSequence()
            .map { Pair(timeToMinute(it[0]),timeToMinute(it[1])+10) }
            .sortedBy { it.first }
            .toList()

        books.forEach{

            var isEmpty = false

            for((index,book) in bookList.withIndex()){
                if(book.second <= it.first){
                    isEmpty = true
                    bookList[index] = it
                    break
                }
            }

            if(isEmpty==false){
                bookList.add(it)
            }
        }

        return bookList.size

    }

    fun timeToMinute(time: String) : Int {
        val timeArray = time.split(":")
        return timeArray[0].toInt()*60 + timeArray[1].toInt();
    }
}

//[["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]

fun main() {
    val solution = Solution();

    println(
        solution.solution(
            arrayOf(
                arrayOf("15:00", "17:00"),
                arrayOf("16:40", "18:20"),
                arrayOf("14:20", "15:20"),
                arrayOf("14:10", "19:20"),
                arrayOf("18:20", "21:20")
            )
        )
    )
}
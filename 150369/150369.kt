package `150369`

import kotlin.math.max

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {


        var distance = 0L


        val deliveryStack = ArrayDeque<Int>()
        deliveryStack.addAll(deliveries.asSequence())

        val pickupStack = ArrayDeque<Int>()
        pickupStack.addAll(pickups.asSequence())


        while(deliveryStack.isNotEmpty() || pickupStack.isNotEmpty()){

            while (deliveryStack.isNotEmpty()){
                if(deliveryStack.last() == 0){
                    deliveryStack.removeLast()
                    continue
                }
                break
            }

            while (pickupStack.isNotEmpty()){
                if(pickupStack.last() == 0){
                    pickupStack.removeLast()
                    continue
                }
                break
            }

            val pickupDistance = pickupStack.size
            val deliveryDistance = deliveryStack.size

            val currentDistance = max(pickupDistance,deliveryDistance)

            var currentDelivery = cap

            while(currentDelivery > 0 && deliveryStack.isNotEmpty()){
                if(deliveryStack.last() <= currentDelivery){
                    val toDelivery =  deliveryStack.removeLast()
                    currentDelivery -= toDelivery
                }
                else{
                    var toDelivery =  deliveryStack.removeLast()
                    deliveryStack.add(toDelivery - currentDelivery)
                    currentDelivery = 0
                }
            }

            var currentPickUp = cap

            while(currentPickUp > 0 && pickupStack.isNotEmpty()){
                if(pickupStack.last() <= currentPickUp){
                    val toPickup =  pickupStack.removeLast()
                    currentPickUp -= toPickup
                }
                else{
                    var toPickup =  pickupStack.removeLast()
                    pickupStack.add(toPickup - currentPickUp)
                    currentPickUp = 0
                }
            }

            distance += currentDistance*2
        }

        return distance
    }
}

// fun main
//2	7	[1, 0, 2, 0, 1, 0, 2]	[0, 2, 0, 1, 0, 2, 0]
fun main(){
    val solution = Solution()
    println(solution.solution(2,7,intArrayOf(1,0,3,1,2),intArrayOf(0, 2, 0, 1, 0, 2, 0)))
}
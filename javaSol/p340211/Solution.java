package javaSol.p340211;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    public static record Pair(Integer first, Integer second){

    }

    public static class Robot{

        List<Pair> routes;

        Pair current;
        Pair dest;

        boolean end = false;

        int index = 1;

        Robot(List<Pair> routes){
            this.routes = routes;
            this.current = new Pair(routes.get(0).first,routes.get(0).second);
            this.dest = new Pair(routes.get(1).first,routes.get(1).second);
        }

        public void nextStep(){

            if(current.first == dest.first && current.second == dest.second){
                if(this.index == routes.size()-1){
                    end = true;

                    return;
                }

                this.dest = new Pair(routes.get(this.index).first,routes.get(this.index).second);
                this.index = this.index+1;
            }

            if(current.first != dest.first){
                if(current.first > dest.first){
                    current = new Pair(current.first-1, current.second);
                }else {
                    current = new Pair(current.first+1, current.second);
                }
            } else {
                if(current.second > dest.second){
                    current = new Pair(current.first, current.second-1);
                }else {
                    current = new Pair(current.first, current.second+1);
                }

            }
        }
    }

    public int solution(int[][] points, int[][] routes) {

        AtomicInteger index = new AtomicInteger(1);

        Map<Integer,Pair> pointMap = Arrays.stream(points).map(p -> {
                    return new Pair(p[0],p[1]);
                })
                .collect(Collectors.toMap(
                        p-> index.getAndAdd(1),
                        p-> p
                ));

        List<Robot> robots =  Arrays.stream(routes).map(p->{

            List<Pair> route = Arrays.stream(p).mapToObj(r -> {
                Pair point = pointMap.get(r);
                return point;
            }).collect(Collectors.toList());

            return new Robot(route);

        }).collect(Collectors.toList());

        int collisionSum = 0;

        while(true){

            Map<Pair,Integer> collisionMap = new HashMap();

            boolean isEnd = robots.stream().allMatch(robot -> {
                return robot.end;
            });

            if(isEnd) break;

            robots.forEach(robot -> {

                if(robot.end != true){
                    Integer value = collisionMap.getOrDefault(robot.current, 0);
                    collisionMap.put(robot.current, value+1);
                }

                System.out.println("robot");
                System.out.println(robot.current.first + " " + robot.current.second);
                System.out.println(robot.dest.first + " " + robot.dest.second);

                robot.nextStep();

            });

            System.out.println("map");
            collisionMap.entrySet().forEach(a -> {
                System.out.println(a.getKey().first + " " +a.getKey().second);
                System.out.println(a.getValue());
            });

            collisionSum = collisionSum + collisionMap.values().stream().filter(a -> {
                return a > 1 ? true : false;
            }).collect(Collectors.toList()).size();

            System.out.println("===============");
        }

        return collisionSum;


    }



    public static void main(String[] args) {
        int[][] points = {{2,2},{2,3},{2,7},{6,6},{5,2}};
        int[][] routes = {{2,3,4,5},{1,3,4,5}};

        Solution sol = new Solution();
        int value = sol.solution(points, routes);

        System.out.println(value);

    }
}

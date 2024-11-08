/*
 * Click `Run` to execute the snippet below!
 */

package javaSol.p250136;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    static class Pair{
        public int col;
        public int row;

        Pair(int col, int row){
            this.col = col;
            this.row = row;
        }
    }

    static int[] dcol = {0,0,-1,1};
    static int[] drow = {1,-1,0,0};

    static List<List<Integer>> maps = new ArrayList<List<Integer>>();
    Map<Integer,Integer> oilMap = new HashMap<>();


    public int solution(int[][] land) {

        maps = Arrays.stream(land)
                .map(row -> Arrays
                        .stream(row)
                        .boxed()
                        .collect(Collectors.toList())
                ).collect(Collectors.toList());

        for(int col = 0; col<maps.size(); col++){
            for(int row= 0; row<maps.get(0).size(); row++){
                if(maps.get(col).get(row) == 1){
                    oilFinder(col, row);
                }
            }
        }

        return oilMap.values().stream().max(Integer::compareTo).orElse(-1);
    }

    public void oilFinder(Integer col, Integer row){

        Queue<Pair> queue = new ArrayDeque<>();
        Set<Integer> sets = new HashSet<Integer>();

        queue.add(new Pair(col,row));
        maps.get(col).set(row,0);
        sets.add(row);

        int size = 1;
        int colMaxSize = maps.size();
        int rowMaxSize = maps.get(0).size();

        while(!queue.isEmpty()){
            Pair pair = queue.poll();

            int currentCol = pair.col;
            int currentRow = pair.row;


            for(int i = 0; i<4; i++){
                int nextCol = currentCol + dcol[i];
                int nextRow = currentRow + drow[i];

                if(nextCol < colMaxSize && nextRow < rowMaxSize && nextCol >=0 && nextRow >= 0){
                    if(maps.get(nextCol).get(nextRow) == 1){
                        maps.get(nextCol).set(nextRow,0);
                        size++;
                        sets.add(nextRow);
                        queue.add(new Pair(nextCol,nextRow));
                    }
                }
            }
        }

        final int finalSize = size;

        sets.stream().forEach(rows -> {
            int oilSize = oilMap.getOrDefault(rows, 0);
            oilMap.put(rows, oilSize + finalSize);
        });

    }



    public static void main(String[] args) {
        int[][] array = {
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}
        };

        Solution sol = new Solution();
        System.out.println(sol.solution(array));

    }
}

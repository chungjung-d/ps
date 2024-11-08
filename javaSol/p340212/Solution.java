package javaSol.p340212;

/*
 * Click `Run` to execute the snippet below!
 */


class Solution {

    public Long calcaultateTime(int input, int[] diffs, int[] times, long limit){

        Long sum = 0L;

        sum = sum + times[0];

        for(int i = 1; i<diffs.length; i++){

            if(sum > limit){
                return sum;
            }

            if(diffs[i] - input <= 0){
                sum = sum + times[i];
            }
            else{
                sum = sum + (diffs[i]-input)*(times[i-1]+times[i]) + times[i];
            }
        }

        return sum;
    }


    public int solution(int[] diffs, int[] times, long limit) {


        int max = 0;

        for(int i : diffs){
            if(i > max) max = i;
        }

        int end = max;
        int start = 1;


        while(true){
            int current = (start + end)/2;

            Long result = calcaultateTime(current, diffs, times, limit);


            System.out.println("currnt: " +current);
            System.out.println("result: " +result);


            if(result > limit){
                Long result2 = calcaultateTime(current+1, diffs, times, limit);

                System.out.println("currnt2-1: " +(current+1));
                System.out.println("result2: " +result2);
                if(result2 <= limit){
                    return current+1;
                }
                start = current+1;
            }

            if(result <= limit){
                Long result2 = calcaultateTime(current-1, diffs, times, limit);

                System.out.println("currnt2-2: " +(current-1));
                System.out.println("result2: " +result2);
                if(result2 > limit){
                    return current;
                }
                end = current;
            }
            if(start >= end){
                return current;
            }

            System.out.println("======================");
        }

    }


    public static void main(String[] args) {
        int[] diffs = {1, 99999, 100000, 99995};
        int[] times = {9999, 9001, 9999, 9001};
        long limit = 3456789012L;

        Solution sol = new Solution();
        int value = sol.solution(diffs, times, limit);
        System.out.println(value);
    }
}

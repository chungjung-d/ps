package javaSol.p43163;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {


    public static record Pair(String word, int count){};

    public boolean canChange(String begin, String target){
        char[] beginArray = begin.toCharArray();
        char[] targetArray = target.toCharArray();

        int diffCount = 0;

        for(int i=0; i<beginArray.length; i++){
            if(diffCount > 1) return false;

            if(beginArray[i] != targetArray[i]) diffCount++;
        }

        return diffCount > 1 ? false : true;

    }

    public int solution(String begin, String target, String[] words) {

        Queue<Pair> queue = new ArrayDeque<Pair>();

        List<String> wordList = Arrays.asList(words);
        Set<String> wordSet = new HashSet<String>();

        if(!wordList.contains(target)) return 0;

        queue.add(new Pair(begin, 0));

        int ans = 0;

        while(!queue.isEmpty()){
            Pair currentWord = queue.poll();

            System.out.println(currentWord.word + " " + currentWord.count);

            if(currentWord.word.equals(target)){
                ans = currentWord.count;
                break;
            }

            wordList.stream().filter(word -> {
                return canChange(currentWord.word, word) && !wordSet.contains(word);
            }).forEach(word -> {
                queue.add(new Pair(word, currentWord.count+1));
                wordSet.add(word);
            });
        }

        return ans;
    }


    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        Solution sol = new Solution();
        int value = sol.solution("hit", "cog", words);
        System.out.println(value);
    }
}


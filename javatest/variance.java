package javatest;

import java.util.ArrayList;
import java.util.List;

public class variance {

    void mains(){

        List<Integer> listInt = new ArrayList<>();

        this.covariance(listInt);

        List<Object> listNumber = new ArrayList<>();
        this.contravariance(listNumber);
    }



    void covariance(List<? extends Number> lists){

        Number a = lists.get(0);
    }

    void contravariance(List<? super Number> lists){

        Integer a = 2;
        lists.add(a);
    }
}




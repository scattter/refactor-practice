package com.twu.refactoring;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public Boolean equalsIsTrue(Boolean str){
        if (){
            return true;
        }
        return false;
    }

    public int countEven() {
        int count = 0;
        for (int number : numbers) {
            if (number % 2 == 0) count++;
        }
        return count;
    }

    public int countOdd() {
        return numbers.length - countEven();
    }

    public int countPositive() {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }

    public int countNegative() {
        return numbers.length - countPositive();
    }
}

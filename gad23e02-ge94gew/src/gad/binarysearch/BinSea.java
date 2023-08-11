package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {

        int top = sortedData.length - 1;
        int bottom = 0;

        while (bottom < top) {
            int midindex = (top + bottom) / 2;
            result.addStep(midindex);

            if (sortedData[midindex] == value) return midindex;
            else if (sortedData[midindex] < value) bottom = midindex + 1;
            else top = midindex - 1;
        }

        return bottom;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        int temp = search(sortedData, value, result);

        if (lowerBound) {
            if (temp == sortedData.length - 1 && sortedData[sortedData.length - 1] < value) return -1;

            while (sortedData[temp] == value && temp - 1 >= 0) temp--;

            while (sortedData[temp] < value) temp++;
            while (sortedData[temp] >= value) temp--;
            return temp + 1;
        }

        else {
            if (temp == 0 && sortedData[0] > value) return -1;

            while (sortedData[temp] > value) temp--;
            while (sortedData[temp] <= value) temp++;
            return temp - 1;
        }

        return temp;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };

        System.out.println(search(array, 7, new StudentResult()));
        System.out.println(search(array, 100, new StudentResult()));

        System.out.println(search(array, 7, false, new StudentResult()));
        System.out.println(search(array, 100, true, new StudentResult()));

        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}
package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;

    private int growthFactor, maxOverhead;

    public DynamicArray(int growthFactor, int maxOverhead) {
        elements = new int[] {};
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;

        if (maxOverhead < 0 || growthFactor < 1) throw new IllegalArgumentException("Growth or Overhead less than zero");
        if (maxOverhead < growthFactor) throw new IllegalArgumentException("Overhead must be at least as high as Growth");


    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {


        int[] copy = new int[elements.length];
        System.arraycopy(elements, 0, copy, 0 , elements.length);

        if (minSize * growthFactor > elements.length || minSize * maxOverhead < elements.length)
            elements = new int[minSize * growthFactor];
        else return usage;

        if (usage.isEmpty()) return Interval.EmptyInterval.getEmptyInterval();



        return new Interval.NonEmptyInterval(0, usage.getTo() - usage.getFrom());
    }

    public int get(int index) {
        return elements[index];
    }

    public void set(int index, int value) {
        elements[index] = value;
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
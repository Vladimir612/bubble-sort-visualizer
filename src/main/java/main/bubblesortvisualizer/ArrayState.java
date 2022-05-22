package main.bubblesortvisualizer;

public class ArrayState {
    ArrayStatus status;
    ArrayElement[] array;
    int sortedCount = 0;

    public ArrayState(ArrayElement[] passedArray, ArrayStatus status, int sortedCount) {
        this.array = passedArray;
        this.status = status;
        this.sortedCount = sortedCount;
    }

    public ArrayStatus getStatus() {
        return status;
    }

    public void setStatus(ArrayStatus status) {
        this.status = status;
    }

    public void setArray(ArrayElement[] array) {
        this.array = array;
    }

    public ArrayElement[] getArray() {
        return array;
    }

    public int getSortedCount() {
        return sortedCount;
    }
}

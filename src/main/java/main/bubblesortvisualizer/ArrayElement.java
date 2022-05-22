package main.bubblesortvisualizer;

public class ArrayElement {
    private int number;
    private ElementStatus currentStatus;

    public ArrayElement(int num, ElementStatus status){
        this.number = num;
        this.currentStatus = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public ElementStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(ElementStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
}

package main.bubblesortvisualizer;

import javafx.application.Application;

import java.util.Arrays;

public class Starter {
    public static void main(String[] args) {
        int[] arr = {3, 4, 9, 6, 5, 1};
        String[] strArray = Arrays.stream(arr).mapToObj(String::valueOf).toArray(String[]::new);

        Application.launch(BubbleSortApplication.class, strArray);
    }
}

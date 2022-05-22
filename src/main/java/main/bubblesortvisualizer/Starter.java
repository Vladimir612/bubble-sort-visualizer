package main.bubblesortvisualizer;

import javafx.application.Application;

import java.util.Arrays;

public class Starter {
    public static void main(String[] args) {
        int[] arr = {8, 9, 6, 39, 7, 1, 12, 45, 86, 12, 32, 2, 6, 89, 65, 32, 4, 2, 75};
        String[] strArray = Arrays.stream(arr).mapToObj(String::valueOf).toArray(String[]::new);

        Application.launch(BubbleSortApplication.class, strArray);
    }
}

package main.bubblesortvisualizer;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class HelperFunctions {
    public static void bubbleSort(ArrayElement[] passedArray, ArrayList<ArrayState> statesOfArray){
        int n = passedArray.length;

       ArrayElement[] initArray = new ArrayElement[passedArray.length];
        for (int k = 0; k < passedArray.length; k++) {
            initArray[k] = new ArrayElement(passedArray[k].getNumber(), ElementStatus.DEFAULT);
        }
        ArrayState initArrayState = new ArrayState(initArray, ArrayStatus.INITIAL, 0);
        statesOfArray.add(initArrayState);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                //Adding compared array to statesOfArray
                ArrayElement[] comparedArr = new ArrayElement[passedArray.length];
                for (int k = 0; k < passedArray.length; k++) {
                    comparedArr[k] = new ArrayElement(passedArray[k].getNumber(), ElementStatus.DEFAULT);
                    if(k == j || k == j + 1){
                        comparedArr[k].setCurrentStatus(ElementStatus.COMPARED);
                    }if(k > n - i - 1){
                        comparedArr[k].setCurrentStatus(ElementStatus.FINAL);
                    }
                }

                ArrayState comparedArrayState = new ArrayState(comparedArr, ArrayStatus.COMPARED, i);
                statesOfArray.add(comparedArrayState);

                //Adding exchanged array to statesOfArray
                if (passedArray[j].getNumber() > passedArray[j + 1].getNumber()) {
                    ArrayElement temp = passedArray[j];
                    passedArray[j] = passedArray[j + 1];
                    passedArray[j + 1] = temp;

                    ArrayElement[] newArrayExchanged= new ArrayElement[passedArray.length];
                    for (int k = 0; k < passedArray.length; k++) {
                        newArrayExchanged[k] = new ArrayElement(passedArray[k].getNumber(), ElementStatus.DEFAULT);
                        if(k == j || k == j + 1) {
                            newArrayExchanged[k].setCurrentStatus(ElementStatus.EXCHANGED);
                        } if(k >= n - i) {
                            newArrayExchanged[k].setCurrentStatus(ElementStatus.FINAL);
                        }
                    }
                    ArrayState exchangedArrayState = new ArrayState(newArrayExchanged, ArrayStatus.EXCHANGED, i);
                    statesOfArray.add(exchangedArrayState);
                }
            }
        }

        ArrayElement[] finalArray = new ArrayElement[passedArray.length];
        for (int k = 0; k < finalArray.length; k++) {
            finalArray[k] = new ArrayElement(passedArray[k].getNumber(), ElementStatus.FINAL);
        }
        ArrayState finalArrayState = new ArrayState(finalArray, ArrayStatus.SORTED, n);
        statesOfArray.add(finalArrayState);
    }

    public static void updateDisplayedArray(ArrayElement[] arrayOfElements, TilePane tileArray){
        tileArray.getChildren().clear();
        if(arrayOfElements.length > 0){
            for(ArrayElement el: arrayOfElements){
                Label element = new Label(Integer.toString(el.getNumber()));
                element.setPadding(new Insets(10));
                element.setEffect(new DropShadow(4.0, 4.0, 4.0, Color.rgb(19, 26, 46, 0.1)));
                element.setTextFill(Color.rgb(19, 26, 46));
                if(el.getCurrentStatus() == ElementStatus.DEFAULT){
                    element.setBackground(new Background(new BackgroundFill(Color.rgb(172, 176, 173), new CornerRadii(10.0), new Insets(-2.0))));
                } else if(el.getCurrentStatus() == ElementStatus.COMPARED){
                    element.setBackground(new Background(new BackgroundFill(Color.rgb(234, 255, 94), new CornerRadii(10.0), new Insets(-2.0))));
                } else if(el.getCurrentStatus() == ElementStatus.FINAL){
                    element.setBackground(new Background(new BackgroundFill(Color.rgb(72, 255, 69), new CornerRadii(10.0), new Insets(-2.0))));
                } else if(el.getCurrentStatus() == ElementStatus.EXCHANGED){
                    element.setBackground(new Background(new BackgroundFill(Color.rgb(74, 119, 255), new CornerRadii(10.0), new Insets(-2.0))));
                }
                tileArray.getChildren().add(element);
            }
        }
        else{
            System.out.println("No elements to display!");
        }
    }

    public static int[] generateRandomArr(){
        //Generating random array
        Random rdm = new Random();
        int length = 99;
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rdm.nextInt(100);
        }

       return array;
    }

    public static ArrayState generateArr(int[] array){
        ArrayElement[] helpArr = new ArrayElement[array.length];
        for (int i = 0; i < array.length; i++) {
            ArrayElement element = new ArrayElement(array[i], ElementStatus.DEFAULT);
            helpArr[i] = element;
        }

        return new ArrayState(helpArr, ArrayStatus.INITIAL, 0);
    }
}

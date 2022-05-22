package main.bubblesortvisualizer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

import static main.bubblesortvisualizer.HelperFunctions.*;

public class BubbleSortApplication extends Application implements EventHandler<ActionEvent> {
    //GUI elements
    Stage window;
    Scene scene;
    Button play, stop, next;
    TilePane tileArray;
    VBox mainLayout, historyLayout;
    HBox buttonGroup;
    BorderPane borderPane;
    Slider slider = new Slider();
    ScrollPane sp = new ScrollPane();

    ListView<String> historyListView;
    ObservableList<String> history;

    //array of all states
    ArrayList<ArrayState> statesOfArray = new ArrayList<>();
    ArrayState arrayState;

    //Thread for displaying objects
    Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        //GUI properties
        mainLayout = new VBox(20);
        historyLayout = new VBox(20);
        buttonGroup = new HBox(20);
        borderPane = new BorderPane();
        mainLayout.setPadding(new Insets(15));
        tileArray = new TilePane();

        tileArray.setHgap(20);
        tileArray.setVgap(20);
        tileArray.setPrefColumns(15);
        tileArray.setPadding(new Insets(5));

        play = new Button("Play");
        play.setOnAction(this);

        stop = new Button("Stop");
        stop.setOnAction(this);

        next = new Button("Next iteration");
        next.setOnAction(this);

        buttonGroup.getChildren().addAll(play, stop, next);

        slider.setMin(10);
        slider.setMax(3000);
        slider.setValue(1000);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(100);
        slider.setMaxWidth(300);

        mainLayout.setPadding(new Insets(30, 20, 10, 20));
        mainLayout.getChildren().addAll(tileArray, slider, buttonGroup);
        sp.setContent(mainLayout);
        borderPane.setCenter(sp);

        history = FXCollections.observableArrayList();
        historyListView = new ListView<String>(history);
        historyListView.setPrefWidth(150);

        Label historyLabel = new Label("This is a history of steps in bubble sort:");
        historyLayout.getChildren().addAll(historyLabel, historyListView);
        historyLayout.setPadding(new Insets(15));

        borderPane.setLeft(historyLayout);

        window = primaryStage;
        scene = new Scene(borderPane, 1100, 600);

        window.setScene(scene);
        window.setTitle("Bubble Sort Visualizer");
        window.show();

        //everytime on change we are changing delay
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
                timeline.setRate(1000 / slider.getValue());
                if(timeline.getStatus() != Animation.Status.RUNNING){
                    return;
                }
                timeline.pause();
                timeline.play();
            }
        });

        //everytime when we click on element we are changing displayedArray
        historyListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int index = historyListView.getSelectionModel().getSelectedIndex();
                if(index == -1) {
                    return;
                }
                updateDisplayedArray(statesOfArray.get(index).getArray(), tileArray);
                System.out.println("clicked on " + historyListView.getSelectionModel().getSelectedIndex());
            }
        });

        //JavaFX thread, we are adding next history item, that will trigger changing displayed array
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNextHistoryItem();
            }
        }));
        List<String> args = getParameters().getRaw();

        if(!args.isEmpty()){
            int[] intArr = Arrays.stream(args.toArray()).map(Object::toString).mapToInt(Integer::parseInt).toArray();
            arrayState = generateArr(intArr);
        }else{
            arrayState = generateRandomArr();
        }

        //initially displaying random array
        updateDisplayedArray(arrayState.getArray(), tileArray);

        //this bubble sort will make us every step that should be made later
        bubbleSort(arrayState.getArray(), statesOfArray);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == play){
            timeline.setCycleCount(statesOfArray.size());
            timeline.play();
        }
        if(actionEvent.getSource() == stop){
            timeline.pause();
        }
        if(actionEvent.getSource() == next){
            addNextHistoryItem();
        }
    }

    public synchronized void addNextHistoryItem() {
        int count = historyListView.getItems().size();
        if(count >= statesOfArray.size()){
            return;
        }
        ArrayState next = statesOfArray.get(count);
        String text = next.getStatus().toString() + "("+ next.getSortedCount() + "/" + next.getArray().length + ")";
        historyListView.getItems().add(count, text);
        updateDisplayedArray(next.getArray(), tileArray);
    }
}

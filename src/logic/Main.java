package logic;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

//import javafx.event.EventHandler;

import java.util.concurrent.atomic.AtomicInteger;

//public class Main extends Application{

    //@Override
//    public void start (Stage primaryStage) throws Exception{
//        Circle c = new Circle();
//        c.setRadius(50);
//        c.setStroke(Color.BLACK);
//        c.setFill(new Color(0.25, 0.14, 0.333, 0.51));
//
//        Text t= new Text("Java FX");
//        t.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC,20));
//        StackPane p = new StackPane();//pane involves get children method
//        p.getChildren().addAll(c,t);
//        c.centerXProperty().bind(p.widthProperty().divide(2));
//        c.centerYProperty().bind(p.heightProperty().divide(2));
//        primaryStage.setTitle("Circle");
//        primaryStage.setScene(new Scene(p, 200, 200));
//        primaryStage.show();
////        Text t1 = new Text(50,50, "(50,50)");
////        Text t2 = new Text(50,200, "(50, 200)");
////        Text t3 = new Text(200, 50, "(200, 50)");
////        Text t4 = new Text(200,200,"200,200");
////        Pane p =new VBox();
////        p.getChildren().addAll(t1,t2,t3,t4);
////        primaryStage.setTitle("coords");
////        primaryStage.setScene(new Scene(p, 300, 250));
////        primaryStage.show();
//
//    }

//    public static void main (String[] args){
//        Application.launch(args);
//    }
//}
//
//    @Override   //allws u to drag text around
//    public void start (Stage primaryStage) throws Exception{
//        Pane pane = new Pane();
//        Text t = new Text (250, 250, "Mousing around");
//        pane.getChildren().add(t);
//
//        t.setOnMouseDragged(e -> {
//            t.setX(e.getX());
//            t.setY(e.getY());
//        });
//        primaryStage.setTitle("Circle");
//        primaryStage.setScene(new Scene(pane, 500, 500));
//        primaryStage.show();
//    }

public class Main extends Application {
    public void start (Stage primaryStage){
        try {
            Text message = new Text();

            Button bt = new Button("click for help");
            Button bt2 = new Button("remove line");
            //displays message on button click.
            bt.setOnAction((ActionEvent event) -> {
                System.out.println(event);  //output, accessing lambda expression parameters
                message.setText("looks like you should jus die :)");
            });


                VBox root = new VBox(20, bt, message);
                System.out.println("if state");
                root.setAlignment(Pos.CENTER);

            Scene scene = new Scene(root, 200, 250);
            primaryStage.setTitle("Hello World FX"); //set stage title
            primaryStage.setScene(scene);  //place scene in stage
            primaryStage.show();        //display stage

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main (String[] args){
        Application.launch(args);
    }
}


//public class Main extends Application {
//
//    Stage the_current_window;
//    Button my_button;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        the_current_window = primaryStage;      //stage = window
//        the_current_window.setTitle("First try");
//        my_button = new Button("Click for attention");
//
//        StackPane the_layout = new StackPane(); //container for arranging contents
//        the_layout.getChildren().add(my_button);
//
//        Scene scene = new Scene(the_layout, 200, 200);  //contents inside window
//
//        the_current_window.setScene(scene);
//        the_current_window.show();
//    }
//
//}


//public class Main extends Application implements EventHandler<ActionEvent> {
//    public void start(Stage primaryStage) {
//        int boardSize = 15;
//
//        HBox hbox = new HBox(50);   //horizontal distance
//
//        VBox vbox0 = new VBox();
//        VBox vbox1 = new VBox(20);
//        VBox vbox2 = new VBox(10);
//
//        for (int i = 0; i < boardSize; i++) {
//            Button bt = new Button("A-Button " + i);
//            Button bt2 = new Button("B-Button " + i);
//            Button bt3 = new Button("C-Button " + i);
//
//            vbox0.getChildren().add(bt);    //adds 14 boxes to each column.
//            vbox1.getChildren().add(bt2);
//            vbox2.getChildren().add(bt3);
//
//        }
//        GridPane gridpane = new GridPane();
//        for (int i=0; i<14;i++){
//            for (int j =0; j<14; j++) {
//                Button button = new Button("[" + i + "," + j + "]");
//                gridpane.add(button, i, j);
//            }
//            System.out.println("");
//        }
//
//
//        gridpane.add(new Button(), 1,0);
//        gridpane.add(new Label(), 2, 0);
//       // hbox.getChildren().addAll(vbox0, vbox1, vbox2, gridpane);
//        Scene scene = new Scene(gridpane, 500, 500);
//
//        primaryStage.setTitle("Scrabble");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    @Override
//    public void handle(ActionEvent actionEvent) {
//
//    }
//}


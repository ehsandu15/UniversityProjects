package net.eh_du.ehsanduwidi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Table {
    private static ArrayList<Points> points = new ArrayList<>();
    private static boolean [][] incident;
    private static VBox v1 =new VBox();
    private static GridPane root = new GridPane();
    private static BorderPane roott = new BorderPane();

    private static void reset(ArrayList<Points> mm){

        points = mm;
        incident = new boolean[mm.size()][mm.size()];
        root.getChildren().clear();
        v1.getChildren().clear();
        roott.getChildren().clear();

    }

    public static void table(ArrayList<Points> points, Scene scene, FlowPane pane ){
        reset(points);

        Label la [][] = new Label[points.size()][points.size()];
        for(int i=0; i<la.length; i++)
            for(int j=0; j<la[0].length; j++)
            {
                final Label temp= new Label("F");
                temp.setFont(new Font("Arial", 20));

                temp.setOnMouseClicked(e->{
                   if(temp.getText() == "F")
                       temp.setText("T");
                   else
                       temp.setText("F");
                });
                la[i][j] = temp;
            }
        Label six = new Label(" ");
        six.setFont(new Font("Arial", 20));
        root.add(six,0,0);
        //FOR SHOWING THE TABLE.
        for(int i = 0 ; i< points.size()+1; i++){
            for(int j=0; j<points.size()+1; j++)
            {
                if (i==0 && j>0){
                    Label temp = new Label(points.get(j-1).getName());
                    temp.setFont(new Font("Arial", 20));
                    root.add(temp,j,i);}
                else if (i>0 && j==0)
                {
                    Label temp = new Label(points.get(i-1).getName());
                    temp.setFont(new Font("Arial", 20));
                    root.add(temp,j,i);}
                else if(i>0 && j>0){
                    root.add(la[i-1][j-1],j,i);

                }

            }
            }

        Button compute = new Button("Compute");
        Button back = new Button("Back");
        back.setOnAction(e-> {scene.setRoot(pane);             points.clear();
        });
        back.setOnKeyPressed(e->{
        if (e.getCode().equals(KeyCode.ENTER))
            scene.setRoot(pane);
                    points.clear();
                }

        );
        compute.setOnAction(e->{compute(la);});



        HBox buttons = new HBox();
        buttons.getChildren().addAll(back,compute);

        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        v1.getChildren().addAll(root,buttons);
        v1.setSpacing(30);
        v1.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.setHgap(30);
        root.setVgap(30);

        roott.setPadding(new Insets(20,20,20,20));
        roott.setCenter(v1);

        scene.setRoot(roott);



        }

        private static void compute(Label[][] la){
            for(int i = 0; i<la.length; i++)
                for(int j =0; j<la.length; j++)
                   if(la[i][j].getText() == "T")
                        incident[i][j]= true;
            Drawer.drawing(incident,points);
            incident = new boolean[points.size()][points.size()];

        }





    }



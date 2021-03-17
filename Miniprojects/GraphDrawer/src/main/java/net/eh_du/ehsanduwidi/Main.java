package net.eh_du.ehsanduwidi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {



    public void action(ArrayList<Points> points,TextField values){
        String val = values.getText();
        val+=",";
        ArrayList<String> symbols = new ArrayList<>();
        if(true){
            String temp="";
            for(int i = 0; i<val.length(); i++){
                if(val.charAt(i)!=',')
                    temp+=(val.charAt(i)+"");
                else
                {  symbols.add(temp); temp ="";}
            }

        }

        if(symbols.size()<11){
            double st =0;
            double angel = 360.0/new Integer(symbols.size()).doubleValue();
            for(String i : symbols)
            {
                points.add(new Points(st,i));
                st+=angel;
            }}
        }

    @Override
    public void start(Stage p) throws Exception {
        FlowPane Rott = new FlowPane();

        VBox v1 = new VBox();
        v1.setAlignment(Pos.CENTER);

        TextField values = new TextField();


        Button export = new Button("Submit the Symbols");
        export.setMaxSize(180, 80);

        Label b = new Label("Graphs Drawer");
        b.setFont(new Font("Arial", 30));

        v1.getChildren().add(b);
        v1.getChildren().addAll(values, export);
        v1.setSpacing(20);

        Rott.getChildren().add(v1);
        Rott.setAlignment(Pos.CENTER);
        Rott.setPadding(new Insets(10, 10, 10, 10));


        p.setTitle("Graphs");
        Scene scene =new Scene(Rott, 500, 620);
        p.setScene(scene);
        p.show();

        //for actions
        ArrayList<Points> points = new ArrayList<>();

        values.setOnKeyPressed(e->{
            if (e.getCode().equals(KeyCode.ENTER))
            {
                action(points,values);
                Table.table(points,scene,Rott);

            }
        });

        export.setOnAction(a -> {
            action(points,values);
            Table.table(points,scene,Rott);



        });
    }




    public static void main(String[] args) {
        launch(args);
    }
}

package net.eh_du.ehsanduwidi;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;

public class Found {
    public static void d(String m,String f,boolean o){
        Stage p = new Stage();
        p.initModality(Modality.APPLICATION_MODAL);
        p.setTitle("Result");
        Label x = new Label(m);
        x.setTextFill(Color.PURPLE);
        x.setFont(new Font("Cambria",14));
        TextArea text = new TextArea();
        text.setMaxSize(395,120 );
        text.setText(f);
        text.setEditable(false);
        Button btn = new Button("Close");

        x.setGraphic(text);
        x.setContentDisplay(ContentDisplay.BOTTOM);
        VBox v = new VBox();
        btn.setOnAction(e-> p.close());

        v.getChildren().addAll(x,btn);
        v.setSpacing(2);
        v.setPadding(new Insets(5,10,4,0));
        Scene s = new Scene(v,400,200);
        p.setScene(s);
        p.setResizable(false);
        p.show();


    }
    public static void d(String m){
        Stage p = new Stage();
        p.initModality(Modality.APPLICATION_MODAL);
        p.setTitle("Result");
        Label x = new Label(m);
        x.setTextFill(Color.PURPLE);
        x.setFont(new Font("Cambria",14));

        Button btn = new Button("Close");
        VBox v = new VBox();
        btn.setOnAction(e-> p.close());
        v.getChildren().addAll(x,btn);
        v.setSpacing(50);
        v.setPadding(new Insets(5,0,0,12));
        Scene s = new Scene(v,150,110);
        p.setScene(s);
        p.setResizable(false);
        p.showAndWait();


    }


}

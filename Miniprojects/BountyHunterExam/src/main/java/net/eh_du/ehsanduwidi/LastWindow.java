/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;


/**
 *
 * @author ehsan687
 */
public class LastWindow {

    public static void d(String m) {
        Stage p = new Stage();
        p.initModality(Modality.APPLICATION_MODAL);
        p.setTitle("GAME FINISHED");
        Label x = new Label(m);
        x.setTextFill(Color.PURPLE);
        x.setFont(new Font("Cambria", 14));

        Button btn = new Button("Close");
        VBox v = new VBox();
        btn.setOnAction(e -> p.close());
        v.getChildren().addAll(x, btn);
        v.setSpacing(50);
        v.setPadding(new Insets(5, 0, 0, 12));
        Scene s = new Scene(v, 400, 200);
        p.setScene(s);
        p.setResizable(false);
        p.setX(800);
        p.setY(550);
        p.showAndWait();

    }

}
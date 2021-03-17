package net.eh_du.ehsanduwidi;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Drawer {
    public static void drawing(boolean incident[][] , ArrayList<Points> points){
        Pane root = new Pane();
        Scene scene = new Scene(root,800,500);
        Label[] names = new Label[points.size()];

        for(int i= 0 ; i< points.size(); i++)
        {
            points.get(i).setY(175.0,100.0);
            points.get(i).setX(400.0,100.0);
            names[i] =  new Label(points.get(i).getName());
            names[i].setFont(new Font("Arial", 30));
            names[i].setLayoutX(points.get(i).getX());
            names[i].setLayoutY(points.get(i).getY());
            root.getChildren().add(names[i]);
        }

        ImageView simpson = new ImageView(Drawer.class.getResource("/mac.png").toExternalForm());
        simpson.setFitHeight(248);
        simpson.setFitWidth(173.3511);
        root.getChildren().add(simpson);
        simpson.setLayoutX(620);
        simpson.setLayoutY(280);


        for(int i=0; i<points.size(); i++)
            for(int j=0; j<points.size(); j++)
            {
             if(incident[i][j])
                if (i==j)
                {   names[i].setTextFill(Color.web("#3157CD")); }
             else{
                boolean show = points.get(i).getY()<points.get(j).getY();
                boolean showp = points.get(i).getX() < points.get(j).getX();

                if((int)(points.get(i).getX()/10) == (int)(points.get(j).getX()/10)){
                        Arrows temp = new Arrows(
                                show? points.get(i).getX()+10: points.get(i).getX()+10,
                                show? points.get(i).getY()+45: points.get(i).getY()-5,
                                show? points.get(j).getX()+10: points.get(j).getX()+10,
                                show? points.get(j).getY()-5: points.get(j).getY()+45,13);
                        root.getChildren().add(temp);
                        System.out.print("fuck");



                    }
                else if((int)(points.get(i).getY()/10) == (int)(points.get(j).getY()/10)){
                        Arrows temp = new Arrows(
                                showp? points.get(i).getX()+25: points.get(i).getX()-5,
                                showp? points.get(i).getY()+20: points.get(i).getY()+20,
                                showp? points.get(j).getX()-5: points.get(j).getX()+25,
                                showp? points.get(j).getY()+20: points.get(j).getY()+20,13);
                    System.out.print("fuck");

                    root.getChildren().add(temp);



                    }

                else if((points.get(i).getX()<points.get(j).getX())){

                    Arrows temp = new Arrows (
                            show? points.get(i).getX()+10: points.get(i).getX()+26,
                            show? points.get(i).getY()+35: points.get(i).getY()-5,
                            show? points.get(j).getX()+1: points.get(j).getX()+10,
                            show? points.get(j).getY()+2: points.get(j).getY()+35,13);

                    root.getChildren().add(temp);




                    }

               else if((points.get(i).getX()>points.get(j).getX())){
                    Arrows temp = new Arrows (show? points.get(i).getX()+10: points.get(i).getX(),
                            show? points.get(i).getY()+35: points.get(i).getY(),
                            show? points.get(j).getX()+25: points.get(j).getX()+10,
                            show? points.get(j).getY()-5: points.get(j).getY()+35,13);


                    root.getChildren().add(temp);

                }
             }
            }



        Stage p = new Stage();
        p.setScene(scene);
        p.setResizable(true);
        p.show();
        p.setResizable(false);
        p.setOnCloseRequest(e->{
            root.getChildren().clear();
            for(int i=0; i<names.length; i++)
                names[i].setTextFill(Color.web("#000000"));
        });

    }


}

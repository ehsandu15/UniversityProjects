/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.Arrays;
/**
 *
 * @author ehsan687
 */
//
public class Bounty_HunterGraphics extends Application {
    //to determine if the program is finished
    private int c = 0;
    //name of the user
    private String name;
    //buttons
    private Button btn[] = new Button[16];
    //holds the nodes
    private BorderPane b = new BorderPane();
    private final int index[] = {
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15
    };
    private int tries = 5;
    private int treasures = 0;
    private int monster = 6;
    //Holds the order of all the elements of the game
    private ArrayList < String > bb;
    //hold where the elements of the game are (for printing)
    private ArrayList < Integer > places = new ArrayList < > ();
    //To know if a button is clicked:
    private int cl[] = {
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
    };
    //to get selected blocks
    private ArrayList < Integer > selected = new ArrayList < > ();


    private void report() {
        System.out.println();
        //the message to display:
        String s = "";
        //printing conditions
        //if tries is zero
        if (tries == 0 && monster != 5 && treasures != 3) {
            s += "- YOU LOSE\n‐ You have (0/5) tries.\n‐ You have found ONLY (" + treasures + "/3) treasure boxes\n";
        }
        //if found all the treasures
        if (treasures == 3) {
            s += "-YOU WIN\n- You have used (" + (5 - tries) + ") tries.\n- You have found the three treasure boxes.\n";

        }
        //if a monster was found
        if (monster == 5) {
            s += "- YOU LOSE\n- You have used (" + (5 - tries) + ") tries.\n- You have found (" + treasures + ") three treasure boxes.\n‐ You got killed by a monster.\n";

        }
        //invoking Writer method
        try {
            Writer(s);
        } catch (java.io.FileNotFoundException ex) {
            System.out.print("Couldn't write a file!");
            System.exit(4);
        }
    }


    //writing and making file with result
    private void Writer(String s) throws java.io.FileNotFoundException {

        java.util.Date d = new java.util.Date();
        GregorianCalendar g = new GregorianCalendar();
        String time;
        //to get time for the report
        if (d.toString().substring(11).equals(" ")) {
            time = "- Time: " + d.toString().substring(12, 19);
        } else {
            time = "- Time: " + d.toString().substring(11, 19);
        }
        //to get time for the file
        String timeF = new StringBuilder(d.toString().substring(11, 19)).deleteCharAt(2).deleteCharAt(4).toString();

        //to get date for the report
        String date = "- Date: " + g.get(java.util.Calendar.DAY_OF_MONTH) + "/" +
                (g.get(java.util.Calendar.MONTH) + 1) + "/" + (g.get(java.util.Calendar.YEAR) - 2000);
        //to get dat for the file
        String datef = "" + g.get(java.util.Calendar.DAY_OF_MONTH) +
                (g.get(java.util.Calendar.MONTH) + 1) + (g.get(java.util.Calendar.YEAR) - 2000);

        //create the file

        PrintWriter o = new PrintWriter(new File(
                new String(this.name + "_BountyHunter_" + datef + "_" + timeF + ".txt")));
        //killed by a monster?
        String killed = (monster == 5) ? "YES" : "NO";

        //writing components of the report to save time
        String[] Comp = {
                "******************** GAME SUMMARY ********************",
                new String("- User name: " + this.name),
                "- Game: BountyHunter",
                date,
                time,
                new String("- Found treasures: " + treasures + " / 3"),
                new String("- Tries: " + (5 - tries) + " / 5"),
                new String("- Killed by a monster: " + killed)
        };
        //printing report
        System.out.println();
        for (int i = 0; i < Comp.length; i++) {
            System.out.println(Comp[i]);
            o.println(Comp[i]);
        }

        //print where treasures empty boxes and monsters
        for (int i = 0; i < places.size(); i++) {
            if (i == 0) {
                o.print("‐ The treasure boxes were behind Buttons number: (");
            }

            if (i == 3) {
                o.print(")\n‐ The empty boxes were behind Buttons number: (");
            }
            if (i == 10) {
                o.print(")\n‐ The Monster boxes were behind Buttons number: (");
            }
            if (i == 2 || i == 9 || i == 15) {
                o.print(places.get(i));
            } else {
                o.print(places.get(i) + ",");
            }


        }

        o.println(")");
        //to unhide all the elements
        for (int i = 0; i < 16; i++) {
            if (bb.get(i) == "E")
                btn[i].setBackground(images(2));

            else if (bb.get(i) == "T")
                btn[i].setBackground(images(1));

            else if (bb.get(i) == "M")
                btn[i].setBackground(images(0));
        }
        //last update
        b.setBottom(down(tries, treasures));

        //to disable used buttons
        for (int i = 0; i < selected.size(); i++) {
            btn[selected.get(i).intValue()].setDisable(true);
        }
        //closing the file so the data wont to be lost in the buffer
        o.close();

        LastWindow.d(s + "\n- GAME OVER");
        //close the program
        System.exit(0);

    }

    private void randomSelection() {
        //changed it as you adviced Doctor :)
        String[] ar = {
                "E",
                "E",
                "E",
                "E",
                "E",
                "E",
                "E",
                "T",
                "T",
                "T",
                "M",
                "M",
                "M",
                "M",
                "M",
                "M"
        };
        bb = new ArrayList < > (Arrays.asList(ar));
        java.util.Collections.shuffle(bb);

        //treasure places
        for (int i = 0; i < bb.size(); i++) {
            if (bb.get(i).equals("T"))
                places.add(i + 1);
        }
        //for empty places
        for (int i = 0; i < bb.size(); i++) {
            if (bb.get(i).equals("E"))
                places.add(i + 1);
        }
        //for monster places
        for (int i = 0; i < bb.size(); i++) {
            if (bb.get(i).equals("M"))
                places.add(i + 1);
        }
    }

    //clicked button event
    private int st(int i) {
        //to add the selected button
        selected.add(i);
        //to check if a button is already pressed
        if (cl[i] == 1)
            return 0;
        if (bb.get(i) == "E") {
            btn[i].setBackground(images(2));
            tries--;
            if (tries == 0)
                report();
        }
        if (bb.get(i) == "T") {
            btn[i].setBackground(images(1));
            treasures++;
            tries--;
            if (treasures == 3 || tries == 0)
                report();
        }
        if (bb.get(i) == "M") {
            btn[i].setBackground(images(0));
            tries--;
            monster--;
            report();
        }

        //update the labels
        b.setBottom(down(tries, treasures));
        //button is pressed
        cl[i]++;
        return 1;

    }

    private Background images(int k) {
        String s = "aa";
        //0 monster, 2 empty box, 1 treasure
        Background[] ba = new Background[3];
        for (int i = 0; i < ba.length; i++) {
            s += "a";


            Image image = new Image(getClass().getResource("/" + s + ".png").toExternalForm());
            BackgroundImage bc = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            ba[i] = new Background(bc);
        }

        return ba[k];

    }

    @Override
    public void start(Stage p) {
        //getting name from Menu class
        name = Menu.name;

        //set the labels on bottom
        b.setBottom(down(5, 0));

        //set the 16 btn on the center
        b.setCenter(btn());

        //Set the header
        b.setTop(head("Bounty Hunter"));

        //invoking random selection to random order
        randomSelection();

        //Set action handlers for all button nodes
        btn[index[0]].setOnAction(e -> st(index[0]));
        btn[index[1]].setOnAction(e -> st(index[1]));
        btn[index[2]].setOnAction(e -> st(index[2]));
        btn[index[3]].setOnAction(e -> st(index[3]));
        btn[index[4]].setOnAction(e -> st(index[4]));
        btn[index[5]].setOnAction(e -> st(index[5]));
        btn[index[6]].setOnAction(e -> st(index[6]));
        btn[index[7]].setOnAction(e -> st(index[7]));
        btn[index[8]].setOnAction(e -> st(index[8]));
        btn[index[9]].setOnAction(e -> st(index[9]));
        btn[index[10]].setOnAction(e -> st(index[10]));
        btn[index[11]].setOnAction(e -> st(index[11]));
        btn[index[12]].setOnAction(e -> st(index[12]));
        btn[index[13]].setOnAction(e -> st(index[13]));
        btn[index[14]].setOnAction(e -> st(index[14]));
        btn[index[15]].setOnAction(e -> st(index[15]));

        //check if the game is finished
        if (c == 1) {
            p.close();
        }
        //set secne
        Scene scene = new Scene(b, 400, 400);

        //Reziable window = off
        p.setResizable(false);
        p.setTitle("BountyHunter");
        p.setScene(scene);
        p.show();

    }

    //the header
    protected Label head(String s) {
        Label x = new Label(s);
        x.setTextFill(Color.BLACK);
        x.setFont(new Font("Cambria", 20));
        x.setPadding(new Insets(10, 0, 0, 130));
        return x;

    }
    //to hold the labels
    protected VBox down(int s, int b) {
        Label x = new Label("Tries: " + s + " / 5");
        x.setTextFill(Color.PURPLE);
        x.setFont(new Font("Cambria", 15));
        Label z = new Label("Treasures: " + b + " / 3");
        z.setTextFill(Color.PURPLE);
        z.setFont(new Font("Cambria", 15));

        VBox v = new VBox();
        v.setPadding(new Insets(0, 0, 60, 20));
        v.getChildren().addAll(x, z);
        return v;


    }
    //to get the buttons and add them to the GridPane
    public GridPane btn() {
        GridPane g = new GridPane();
        g.setPadding(new Insets(70, 0, 0, 115));

        for (int i = 0; i < btn.length; i++) {
            btn[i] = new Button();
            btn[i].setPrefSize(40, 40);
        }
        int c = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                g.setConstraints(btn[c], j, i);
                c++;
            }

        for (int i = 0; i < 16; i++)
            g.getChildren().addAll(btn[i]);

        return g;

    }



}
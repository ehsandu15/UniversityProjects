/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 *
 * @author ehsan687
 */
public class MCQsGraphics extends Application {
    public String name;
    //hold rights answers
    private ArrayList < String > Ans = new ArrayList < > ();
    //hold user answers
    private ArrayList < String > ans = new ArrayList < > ();
    //hold the questions
    private ArrayList < String > questions = new ArrayList < > ();
    //hold the choices in string
    private ArrayList < String > choices = new ArrayList < > ();
    //hold the choices in radiobutton
    private ArrayList < RadioButton > answers = new ArrayList < > ();
    //holding four questions together
    private ArrayList < ToggleGroup > t = new ArrayList < > ();
    //result label
    private Label result = new Label();
    //submit button
    private Button btn = new Button("Submit");
    //used for questions layout
    private int start;


    private void Writer() throws Exception {
        int x = 0;
        for (int i = 0; i < ans.size(); i++)
            if (this.Ans.get(i).charAt(0) == this.ans.get(i).charAt(0))
                x++;
        //for dates
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
        //to get date for the file
        String datef = "" + g.get(java.util.Calendar.DAY_OF_MONTH) +
                (g.get(java.util.Calendar.MONTH) + 1) + (g.get(java.util.Calendar.YEAR) - 2000);

        //create the file
        PrintWriter o = new PrintWriter(new File(
                new String(this.name + "_MCQs_" + datef + "_" + timeF + ".txt")));

        //writing components of the report SAVING TIME
        String[] Comp = {
                "*************** TEST SUMMARY ***************",
                new String("- User name: " + this.name),
                "- Test Type: MCQs",
                date,
                time,
                new String("- Results: " + x + "/" + questions.size())
        };
        System.out.println();

        //printing report
        System.out.println();
        for (int i = 0; i < Comp.length; i++) {
            System.out.println(Comp[i]);
            o.println(Comp[i]);
        }

        //print answers
        for (int i = 1; i <= 3; i++) {
            System.out.printf("==> M‐%d: correct choice (%s) , your choice (%s)\n", i, this.Ans.get(i - 1), this.ans.get(i - 1));
            o.printf("==> M‐%d: correct choice (%s) , your choice (%s)\n", i, this.Ans.get(i - 1), this.ans.get(i - 1));

        }

        //closing the created file
        o.close();

    }



    private int BtnHandle() {
        //alert
        Alert al = new Alert(AlertType.INFORMATION);
        al.setHeaderText("");
        al.setTitle("Unchecked Choice");
        al.setContentText("You must check one choice in every question!");
        //check radio buttons checked
        for (int i = 0; i < t.size(); i++)
            if (t.get(i).getSelectedToggle() == null) {
                al.showAndWait();
                return 1;
            }
        //if submitted
        if (!answers.get(0).isDisable()) {
            //change result label
            result.setText(new StringBuilder(result.getText()).deleteCharAt(8).insert(8, new String(check() + "")).toString());
            //loop t color right answers with green
            int t = 0;
            boolean next = false;
            for (int i = 0; i < Ans.size(); i++) {

                for (int j = t; j < t + 4 && !next; j++) {
                    switch (j % 4) {
                        case 0:
                            if ("A".equals(Ans.get(i))) {
                                answers.get(j).setTextFill(Color.GREEN);
                                t += 4;
                                next = true;
                            }
                            break;
                        case 1:
                            if ("B".equals(Ans.get(i))) {
                                answers.get(j).setTextFill(Color.GREEN);
                                t += 4;
                                next = true;
                            }
                            break;
                        case 2:
                            if ("C".equals(Ans.get(i))) {
                                answers.get(j).setTextFill(Color.GREEN);
                                t += 4;
                                next = true;
                            }
                            break;
                        case 3:
                            if ("D".equals(Ans.get(i))) {
                                answers.get(j).setTextFill(Color.GREEN);
                                t += 4;
                                next = true;
                            }
                            break;
                    }

                }

                next = false;



            } //end loop

            //make the button to close
            btn.setText("CLOSE");
            //disable radiobutton
            for (int i = 0; i < answers.size(); i++)
                answers.get(i).setDisable(true);

            int num_of_right_Q = 0;

            try {
                Writer();
            } catch (Exception e) {}

            return 0;
        }

        //if already submitted
        System.exit(0);
        return 2;
    }

    private int check() {

        int correct = 0;
        int k = 0;
        //color selected radio buttons to red
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isSelected()) {
                switch (i % 4) {
                    case 0:
                        if ("A".equals(Ans.get(k)))
                            correct++;
                        ans.add("A");
                        answers.get(i).setTextFill(Color.RED);

                        break;
                    case 1:
                        if ("B".equals(Ans.get(k)))
                            correct++;
                        ans.add("B");
                        answers.get(i).setTextFill(Color.RED);

                        break;
                    case 2:
                        if ("C".equals(Ans.get(k)))
                            correct++;
                        ans.add("C");
                        answers.get(i).setTextFill(Color.RED);

                        break;
                    case 3:
                        if ("D".equals(Ans.get(k)))
                            correct++;
                        ans.add("D");
                        answers.get(i).setTextFill(Color.RED);

                        break;
                }
                k++;

            }

        }

        return correct;
    }
    private void gettingQuestions() throws FileNotFoundException {
        //number of right answers from the user

        //reading file
        if (!new File("MCQs.txt").exists()) {
            System.out.println("\nThere's no MCQs File!");
            System.exit(3);
        }

        Scanner read = new Scanner(new File("MCQs.txt"));
        Scanner x = new Scanner(System.in);

        while (read.hasNext()) {
            //to ask the questions
            for (int i = 0; i < 3; i++) {

                questions.add(read.nextLine());
                for (int j = 0; j < 4; j++)
                    choices.add(read.nextLine());
                this.Ans.add(read.nextLine().charAt(8) + "");


            }

        }
    }


    @Override
    public void start(Stage p) {
        name = Menu.name;
        try {
            gettingQuestions();
        } catch (Exception e) {}
        btn.setOnAction(e -> BtnHandle());

        Scene scene = new Scene(mainL(), 500, 540);
        p.setResizable(false);
        p.setScene(scene);
        p.setTitle("MCQs");
        p.show();

    }

    //mainlayout
    private VBox mainL() {
        VBox questions = new VBox();
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_LEFT);
        mainLayout.setSpacing(10);
        questions.setSpacing(10);
        //to get questions layout and save it to questions VBox
        for (int i = 0; i < this.questions.size(); i++) {

            questions.getChildren().addAll(questions(i));
        }

        //add the questions layout to scroll pane(take effect when there's more than three questions)
        ScrollPane x = new ScrollPane(questions);
        x.setStyle("-fx-background-color:transparent;");
        x.setFitToHeight(true);
        mainLayout.getChildren().add(x);

        result.setText("Result: 0 / " + this.questions.size());
        result.setFont(new Font("Cambria", 20));
        //add result label and buttom
        mainLayout.getChildren().add(result);
        mainLayout.getChildren().add(btn);


        mainLayout.setPadding(new Insets(10, 10, 10, 15));
        return mainLayout;


    }

    //questions layout
    private VBox questions(int c) {
        VBox choices = new VBox();
        VBox layout = new VBox();
        t.add(new ToggleGroup());
        choices.setSpacing(10);
        layout.setSpacing(6);
        for (int i = start; start < i + 4; start++) {
            answers.add(new RadioButton());
            answers.get(start).setToggleGroup(t.get(t.size() - 1));
            answers.get(start).setText(new StringBuilder(this.choices.get(start)).delete(1, 3).insert(1, ".").toString());
            answers.get(start).setFont(new Font("Cambria", 15));

            choices.getChildren().addAll(answers.get(start));

        }
        Label ques = new Label(questions.get(c));
        ques.setFont(new Font("Cambria", 15));
        layout.getChildren().addAll(ques);
        layout.getChildren().addAll(choices);


        return layout;
    }

}
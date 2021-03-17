package net.eh_du.ehsanduwidi;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;


public class Automatas extends Application{


    private static ArrayList<String> symbols = new ArrayList<>();
    private static int number_of_states = 0;
    private static ArrayList<String> sequence = new ArrayList<>();
    private static ArrayList<Integer> finals = new ArrayList<>();
    private static 		Label x = new Label("DFA Sequences");
    private static 		TextField t = new TextField();
    private static 		Button b = new Button("Enter");

    private static void reset() {
        sequence = new ArrayList<>();
        finals= new ArrayList<>();
        symbols = new ArrayList<>();
        number_of_states =0;

    }
    @SuppressWarnings("resource")
    private static void getSymbols() throws FileNotFoundException{
        Scanner x =new Scanner(new File("automata.txt"));
        x.nextLine();
        String w = x.nextLine();


        for (int i =0; i< w.length(); i++)
        {if (!(w.charAt(i)+"").equals(" "))
            symbols.add(w.charAt(i)+"");}
        while (x.hasNext()) {

            x.nextLine();
            number_of_states++;


        }
        x.close();
        x = new Scanner(new File("automata.txt"));
        x.nextLine();
        x.nextLine();
        while (x.hasNext()) {
            try {
                String l = x.next();
                if(l.charAt(2) == 'f')
                    finals.add(Integer.parseInt(l.charAt(1)+""));
            }
            catch(Exception e) {x.nextLine();}

        }}

    private static void alert() {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText("You entered an undefined symbol");
        al.setTitle("Undefined Symbol");


        al.setHeaderText(null);
        al.show();
    }


    @SuppressWarnings("resource")
    private static int compute(String a) throws FileNotFoundException {
        getSymbols();
        int tr =0;
        int current = 0;
        String a1="";

        //check if undefined symbol
        for (int i =0; i<a.length(); i++) {
            for (int j =0; j<symbols.size(); j++)
            {if ((a.charAt(i)+"").equals(symbols.get(j)))
                break;
            else if(j==symbols.size()-1)
            {
                alert();
                reset();

                return 2;
            }
            }}

        Scanner r= new Scanner(new File("automata.txt"));

        int z =0;

        while (true) {

            for(int i =0 ; i<current+2 ; i++)
                r.nextLine();

            r.next();
            int c= 0;
            if(finals.size()!=0 && tr ==a.length()) {
                for (int i=0; i< finals.size(); i++) {
                    if(current == finals.get(i)) {
                        String m  = "Found it!";
                        String f ="String: \n"+a+"The Sequence:\n";
                        for (String j : sequence)
                            f+=(j+"\n");
                        Found.d(m,f,true);
                        reset();
                        c++;
                        reset();

                        break;
                    }
                    System.out.println("Wpw");
                }
                if (c==1)
                    break;
            }

            if(a1.equals("f")&& tr == a.length()) {
                String m  = "Found it!";
                String f ="String: "+a+"\nThe Sequence:\n";
                for (String j : sequence)
                    f+=(j+"\n");
                Found.d(m,f,true);
                reset();
                break;

            }

            if (tr == a.length()) {
                Found.d("Not Found!");
                reset();

                break;
            }


            for (int i =0; i<symbols.size(); i++) {
                if ((a.charAt(z)+"").equals(symbols.get(i)))
                { a1 = r.next().charAt(1)+"";
                    break;}
                r.next();

            }


            try {
                if (current == number_of_states-1)
                    sequence.add(String.format("qf -> q%d, %s" ,Integer.parseInt(a1),a.charAt(z)));
                else
                    sequence.add(String.format("q%d -> q%d, %s",current ,Integer.parseInt(a1),a.charAt(z)));

                current = Integer.parseInt(a1+"");

            }
            catch(Exception e) {
                if (current == number_of_states-1)
                    sequence.add(String.format("qf -> qf, %s",a.charAt(z)));
                else
                    sequence.add(String.format("q%d -> qf, %s",current,a.charAt(z) ));
                current = number_of_states-1;


            }

            tr++;
            z++;
            r.close();
            r=new Scanner(new File("automata.txt"));




        }

        return 0;
    }



    public static void main(String[] a)  {
        launch(a);
    }

    @Override
    public void start(Stage p) throws Exception {
        // TODO Auto-generated method stub
        VBox v = new VBox();
        x.setTextFill(Color.BLACK);
        x.setFont(new Font("Cambria", 20));
        t.setPrefWidth(20.1);
        t.setPromptText("Enter a String");
        v.setPadding(new Insets(25,100,0,100));
        v.setSpacing(20);
        v.setAlignment(Pos.TOP_CENTER);
        v.getChildren().addAll(x,t,b );
        Scene s = new Scene(v,350,200);
        p.setScene(s);
        p.setResizable(false);
        p.setTitle("Sequence Generator");
        p.show();
        t.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER)
            {
                if(t.getText().trim().isEmpty())
                    alert();
                else
                    try {
                        compute(t.getText());}

                    catch(Exception ee) {

                    }

            }

        });
        b.setOnAction( i ->{
            if(t.getText().trim().isEmpty())
                alert();
            else
                try {
                    compute(t.getText());}

                catch(Exception e) {

                }
        });


    }



}

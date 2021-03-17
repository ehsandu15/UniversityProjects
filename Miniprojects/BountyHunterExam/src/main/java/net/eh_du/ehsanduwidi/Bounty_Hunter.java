/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 *
 * @author ehsan687
 */
public class Bounty_Hunter {
    //to save the name
    public String name;
    //to draw the board
    private String a[] = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16"
    };
    //Holds the order of all the elements of the game
    private ArrayList < String > b = new ArrayList < > ();
    //hold where the elements of the game are (for printing)
    private ArrayList < Integer > places = new ArrayList < > ();

    private void Writer(int t, int tr, int m) throws java.io.FileNotFoundException {

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
        String killed = (m == 5) ? "YES" : "NO";

        //writing components of the report to save time
        String[] Comp = {
                "******************** GAME SUMMARY ********************",
                new String("- User name: " + this.name),
                "- Game: BountyHunter",
                date,
                time,
                new String("- Found treasures: " + t + " / 3"),
                new String("- Tries: " + (5 - tr) + " / 5"),
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
                System.out.print("‐ The treasure boxes were behind: (");
                o.print("‐ The treasure boxes were behind: (");
            }

            if (i == 3) {
                System.out.print(")\n‐ The empty boxes were behind: (");
                o.print(")\n‐ The empty boxes were behind: (");
            }
            if (i == 10) {
                System.out.print(")\n‐ The Monster boxes were behind: (");
                o.print(")\n‐ The Monster boxes were behind: (");
            }
            if (i == 2 || i == 9 || i == 15) {
                System.out.print(places.get(i));
                o.print(places.get(i));
            } else {
                System.out.print(places.get(i) + ",");
                o.print(places.get(i) + ",");
            }


        }
        System.out.println(")");
        o.println(")");
        //closing the file so the data wont to be lost in the buffer
        o.close();

    }

    private void report(int tr, int t, int m) {
        System.out.println();
        //printing conditions
        //if tries is zero
        if (tr == 0 && m != 5 && t != 3) {
            System.out.println("‐ You have (0/5) tries.");
            System.out.printf("‐ You have found ONLY (%d/3) treasure boxes\n", t);
            System.out.println("‐ GAME OVER");
        }
        //if found all the treasures
        if (t == 3) {
            System.out.printf("‐ You have used (%d/5) tries.\n", (5 - tr));
            System.out.print("‐ You have found the three treasure boxes.\n");
            System.out.println("‐ GAME JOB");
            System.out.println("- GOOD BYE");
        }
        //if a monster was found
        if (m == 5) {
            System.out.printf("‐ You have used (%d/5) tries.", (5 - tr));
            System.out.printf("\n‐ You have found (%d/3) treasure boxes\n", t);
            System.out.println("‐ You got killed by a monster.");
            System.out.println("- GAME OVER");
        }
        System.out.println();
        //invoking Writer method
        try {
            Writer(t, tr, m);
        } catch (java.io.FileNotFoundException ex) {
            System.out.print("Couldn't write a file!");
            System.exit(4);
        }



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
        b = new ArrayList < > (Arrays.asList(ar));
        java.util.Collections.shuffle(b);

        for (int i = 0; i < 16; i++)
            System.out.println(String.format("%d- %s", (i + 1), b.get(i)));
        //treasure places
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).equals("T"))
                places.add(i + 1);
        }
        //for empty places
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).equals("E"))
                places.add(i + 1);
        }
        //for monster places
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).equals("M"))
                places.add(i + 1);
        }

    }

    private void draw(ArrayList < Integer > UI) {
        System.out.println();
        System.out.print("------------------------\n");
        // to print the arrays
        int start_n = 1;
        //counter for knowing if user entered the same number or not
        int z = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String s = "" + start_n;
                if (s.length() == 1) {
                    if (UI.size() > 0) {
                        for (int k = 0; k < UI.size(); k++)
                            if (UI.get(k).intValue() == start_n)
                                z++;
                    }
                    if (z == 1)
                        System.out.print("  " + (this.b.get(start_n - 1)) + "  |");

                    else {
                        System.out.print("  " + (this.a[start_n - 1]) + "  |");
                    }
                    z = 0;

                } else {
                    if (UI.size() > 0) {
                        for (int k = 0; k < UI.size(); k++)
                            if (UI.get(k).intValue() == start_n)
                                z++;
                    }
                    if (z == 1)
                        System.out.print("  " + (this.b.get(start_n - 1)) + "  |");
                        //for numbers of 2 digits
                    else {
                        System.out.print("  " + (this.a[start_n - 1]) + " |");
                    }
                    z = 0;

                }
                start_n++;

            }
            System.out.println();
            System.out.print("------------------------\n");
        }


    }


    Bounty_Hunter(String name) {
        //to save user inputs!
        ArrayList < Integer > UI = new ArrayList < > ();
        //keyboard input
        Scanner x = new Scanner(System.in);

        //set values
        int t = 0;
        int e = 7;
        int m = 6;
        int tries = 5;

        this.name = name;
        //constructing the values
        randomSelection();
        //lose condition
        boolean lose = tries == 0 || m == 5;
        //to draw the board
        draw(UI);

        while (!lose) {
            System.out.printf("- You have (%d/5) tries\n", tries);
            System.out.print("- Please Choose A Number: ");

            int user_input = 0;

            while (true) {



                try {
                    user_input = x.nextInt();

                    //check if a number is already occupied(Entered before)
                    int c = 0;
                    if (!UI.isEmpty())
                        for (int i = 0; i < UI.size(); i++)
                            if (user_input == UI.get(i).intValue()) {
                                System.out.println("This number is already taken!\n");
                                c++;
                                break;

                            }
                    //check if the user has entered a number on the board
                    if (user_input < 1 || user_input > 16) {
                        draw(UI);
                        System.out.print("Please Enter A Number Between 1-16: ");
                    } else if (c > 0) {
                        draw(UI);
                        System.out.printf("- You have (%d/5) tries\n", tries);
                        System.out.print("- Please Choose A Number: ");

                    } else
                        break;
                }
                //catch statement
                catch (java.util.InputMismatchException ex) {
                    System.out.print("Invalid Entry!\n");
                    draw(UI);
                    System.out.printf("- You have (%d/5) tries\n", tries);
                    System.out.print("- Please Choose A Number: ");

                    x.nextLine();
                }


            } //finished the second loop

            //add the user input to the array list
            UI.add(new Integer(user_input));

            //check what was occupied behind the user input
            if (this.b.get(user_input - 1) == "T")
                t++;
            else if (this.b.get(user_input - 1) == "E")
                e--;
            else if (this.b.get(user_input - 1) == "M")
                m--;
            //draw the board again
            draw(UI);
            //win if found three tresures
            if (t == 3)
                break;

            //decreasing tries
            tries--;
            //updating lose boolean
            lose = tries == 0 || m == 5;
        }
        report(tries, t, m);



    }


}
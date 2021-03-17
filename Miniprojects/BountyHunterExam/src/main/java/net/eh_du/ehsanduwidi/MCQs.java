/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;
import java.util.GregorianCalendar;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ehsan687
 */
public class MCQs {
    public String name;
    private ArrayList < String > Ans = new ArrayList < > ();
    private ArrayList < String > ans = new ArrayList < > ();

    private void Reader() throws FileNotFoundException {
        //number of right answers from the user
        int num_of_right_Q = 0;
        //reading file

        if (!new File("MCQs.txt").exists()) {
            System.out.println("\nThere's no MCQs File!");
            System.exit(3);
        }

        Scanner read = new Scanner(new File("MCQs.txt"));
        Scanner x = new Scanner(System.in);

        while (read.hasNext()) {
            //to ask the questions
            System.out.println();
            for (int i = 0; i < 5; i++) {
                System.out.println(read.nextLine());
            }
            //to Save the right ans
            this.Ans.add(read.nextLine().charAt(8) + "");

            System.out.print("Your Choice: ");
            //to Save user ans
            String us = x.next().charAt(0) + "";
            while (true) {
                if (us.equalsIgnoreCase("A") || us.equalsIgnoreCase("B") || us.equalsIgnoreCase("C") || us.equalsIgnoreCase("D")) {
                    this.ans.add(us.toUpperCase());

                    break;
                }
                x.nextLine();
                System.out.print("Please Enter A Valid Answer: ");
                us = x.next().charAt(0) + "";
            }
            if (read.hasNext()) {
                System.out.print("/=\\");
            }

            //to check if user is correct
            if (this.Ans.get(this.Ans.size() - 1).charAt(0) == this.ans.get(this.ans.size() - 1).charAt(0))
                num_of_right_Q++;
        }

        //invoke writer method
        try {
            Writer(num_of_right_Q);
        } catch (Exception ex) {}
    }
    private void Writer(int x) throws Exception {
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
                new String("- Results: " + x + "/" + Ans.size())
        };
        System.out.println();

        //printing report
        System.out.println();
        for (int i = 0; i < Comp.length; i++) {
            System.out.println(Comp[i]);
            o.println(Comp[i]);
        }

        //print answers
        for (int i = 1; i <= Ans.size(); i++) {
            System.out.printf("==> M‐%d: correct choice (%s) , your choice (%s)\n", i, this.Ans.get(i - 1), this.ans.get(i - 1));
            o.printf("==> M‐%d: correct choice (%s) , your choice (%s)\n", i, this.Ans.get(i - 1), this.ans.get(i - 1));

        }

        //closing the created file
        o.close();

    }

    MCQs(String name) {
        //saving name
        this.name = name;
        //invoking reader
        try {
            Reader();

        } catch (Exception e) {
            System.out.print("Something went wrong please try again!\n");
        }

    }
}
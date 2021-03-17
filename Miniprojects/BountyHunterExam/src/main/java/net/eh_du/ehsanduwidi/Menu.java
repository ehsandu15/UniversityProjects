/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eh_du.ehsanduwidi;

import java.util.Scanner;
import javafx.application.Application;

/**
 *
 * @author ehsan687
 */

public class Menu {
    //for the graphical
    public static String name;

    public static void main(String a[]) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(System.getProperty("user.dir"));
        Scanner x = new Scanner(System.in);
        String n = "";
        System.out.print("-Please, what is your name?\nYour name: ");
        n = x.next();


        name = n;
        System.out.print("\n-Please would you like to:\n1-) Take a test?\n2-) Play \"Bounty Hunter\"\n3-) Bonus* BountyHunter Javafx\n4-) Bonus* MCQs Javafx\nYour choice: ");
        while (true) {
            try {
                int ch = x.nextInt();
                if (ch == 1) {
                    new MCQs(n);
                    break;
                } else if (ch == 2) {
                    new Bounty_Hunter(n);
                    break;
                } else if (ch == 3) {
                    Application.launch(Bounty_HunterGraphics.class, a);
                    break;
                } else if (ch == 4) {
                    Application.launch(MCQsGraphics.class, a);
                    break;
                } else
                    System.out.print("Enter 1,2,3 or 4: ");
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid Entry\n\n1-) Take a test?\n2-) Play \"Bounty Hunter\"\n3-) Bonus* BountyHunter Javafx\n4-) Bonus* MCQs Javafx\nYour choice: ");
                x.nextLine();
            }

        }


    }
}
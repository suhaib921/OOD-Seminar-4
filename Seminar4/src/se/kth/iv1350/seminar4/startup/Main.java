package se.kth.iv1350.seminar4.startup;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.view.View;

/**
 * Kommentarer
 */

 public class Main{

    /**
    * The main method is the entry point for the application
    * 
    *@param args 
    */

    public static void main(String [] args){

        Controller contr = new Controller ();
        View view =new View(contr);
        view.runFakeExecution();
    }
}

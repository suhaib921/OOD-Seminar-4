package se.kth.iv1350.seminar4.startup;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.util.ErrorLogger;
import se.kth.iv1350.seminar4.view.View;

/**
 * The main class is the entry start point for the entire program.
 */

 public class Main{

    public static void main(String [] args){

        Controller contr = new Controller( new ErrorLogger());
        View view =new View(contr);

        view.runFakeExecution();
      //view.runFakeExecution();
      //view.runFakeExecution();

    }
}

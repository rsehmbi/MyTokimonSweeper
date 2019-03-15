package ca.cmpt213.gamelogic;

import ca.cmpt213.uipackage.Board;

import static java.lang.System.exit;

/**
 * @Author:RamanPreetSingh
 * STUDENTID:301326893
 * TokimonProcessor class to get the command line and arguments and check if the game is to put in cheat mode
 * TokimonProcessor class also checks if the user entered the correct argument as per requirements
 */
public class TokimonProcessor {

    public static void main(String[] args) {
        int NUM_OF_TOKI = 10;
        int NUM_OF_FOKI = 5;
        if (args.length == 0) {
            System.out.println("The default numbers of Tokimons are -> " + NUM_OF_TOKI + " and default number os Fokimons is -> " + NUM_OF_FOKI);
            System.out.println("Game is in CHEATMODE");
        }
        if (args.length == 1) {
            try {
                NUM_OF_TOKI = Integer.parseInt(args[0]);
                NUM_OF_FOKI = 5;
                checkforarguments(NUM_OF_TOKI, NUM_OF_FOKI);
                System.out.println("The numbers of Tokimons are -> " +NUM_OF_TOKI +"The  numbers of Fokimons are -> " + "  " + NUM_OF_FOKI);
            } catch (NumberFormatException notfoundexception) {
                System.out.println("The arguments must be an integer.");
                System.exit(-1);
            }
        }
        if (args.length == 2) {
            try {
                NUM_OF_TOKI = Integer.parseInt(args[0]);
                NUM_OF_FOKI = Integer.parseInt(args[1]);
                System.out.println("The  numbers of Tokimons are -> " +NUM_OF_TOKI + "  " + "The numbers of Fokimons are -> " +NUM_OF_FOKI);
                checkforarguments(NUM_OF_TOKI, NUM_OF_FOKI);
            } catch (NumberFormatException notfoundexception) {
                System.out.println("The arguments must be an Integer.");
                System.exit(-1);
            }
        }
        if (args.length == 3)
        {
            try {
                NUM_OF_TOKI = Integer.parseInt(args[0]);
                NUM_OF_FOKI = Integer.parseInt(args[1]);
                System.out.println("The numbers of Tokimons are -> "+ NUM_OF_TOKI+" "+ "The numbers of Fokimons are -> "+NUM_OF_FOKI+" "+"Three Agruments entered, Game is in Cheat mode");
                checkforarguments(NUM_OF_TOKI, NUM_OF_FOKI);
            } catch (NumberFormatException notfoundexception) {
                System.out.println("The arguments must be an Integer.");
                System.exit(-1);
            }
        }
        if (args.length > 3)
        {
            System.out.println("More than 3 arguments entered");
            exit(-1);
        }
        Board board = new Board();
        board.display();
        board.setNumOfTokiAndFoki(NUM_OF_TOKI, NUM_OF_FOKI, args.length);
    }

    private static void checkforarguments(int num_of_Toki, int num_of_Foki) {
        if (num_of_Toki <= 4 || num_of_Toki >= 100) {
            System.out.println("Please Enter Tokimons in corrent range");
            System.out.println("Tokimons should be greater than 4 and less than 100");
            exit(-1);
        }
        if (num_of_Foki <= 4 || num_of_Foki >= 100) {
            System.out.println("Please Enter Fokimons in corrent range");
            System.out.println("Tokimons should be greater than 4 and less than 100");
            exit(-1);
        }
    }
}

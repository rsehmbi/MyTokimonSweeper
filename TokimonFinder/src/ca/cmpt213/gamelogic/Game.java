package ca.cmpt213.gamelogic;

import ca.cmpt213.uipackage.Board;

import java.util.Scanner;

/**
 * Game class extends Board as Board has methods that helps Game class
 * Gets the initial input to place the user on the Board
 */

public class Game extends Board {
    private int column;
    private char row;
    private int newRow;
    private String userInput;

    //Starts the game using Game Constructor
    public void Start(int row, int column, int argumentslength)
    {
        System.out.print("Enter the column, then row, for example (B5): ");
        getInput();
        newRow = rowConversion(this.row);
        Play play = new Play(newRow, this.column,row,column, argumentslength);
    }

    //Validates Row and Column
    void checkRowAndColumn()
    {
        try {
            this.row = userInput.charAt(0);
            this.column = Integer.parseInt("" + userInput.charAt(1));
            if(userInput.length() > 2) {
                System.out.println("You Entered " + this.row + "" + this.column +" Only Considering first 2 Characters");
            }
            checkRow(this.row);
            checkColumn(this.column);
        }
        catch (NumberFormatException e)
        {
            System.out.println("The Second string should be Integer ");
            getInput();
        }
    }

    //Gets the input from user
    void getInput()
    {
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        if(userInput.length() == 0 || userInput.length() == 1)
        {
            System.out.println("Please enter row and column!! It can't be EMPTY. For example (B5): ");
            getInput();
        }
        checkRowAndColumn();
    }

    //Validates Row
    void checkRow(char row)
    {
        if (row < 'A' || row > 'J')
        {
            System.out.println("Row should be in the range of A to J");
            getInput();
        }

    }

    //Validates Column
    void checkColumn(int columnbyuser)
    {
        if (columnbyuser <= 0)
        {
            System.out.println("Row should be in the range of 1 to 10");
            getInput();
        }
    }

    //Convertes char to int
    int rowConversion(char rowbyuser)
    {
        return rowbyuser - 65;
    }
}

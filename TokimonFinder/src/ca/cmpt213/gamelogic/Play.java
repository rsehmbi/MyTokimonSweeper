package ca.cmpt213.gamelogic;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Play class extends Game class and gets the updated Row and Column
 * User will enter B5, this class has methods that can convert this location to Board location
 * This class is responsible for validating inputs, getting the inputs and moving the location of player on Board
 * Also, responsible for changing the icons and checking how many spells are remaining and if the player won or lost the game
 */

public class Play extends Game {
    private int updatedRow;
    private int updatedColumn;
    private int NUM_OF_SPELLS_REMAINING = 3;
    private int currentFoundNumOfToki;
    private int totalNumOfToki;

    //Constructor responsible for final results
    Play(int row, int column, int numofToki,int numofFoki,int argumentslength)
    {
        this.totalNumOfToki = numofToki;
        randomlyPlaceTokimonsAndFokimons(numofToki,numofFoki);
        if (argumentslength == 0 || argumentslength == 3 )
        {
            displayTokiAndFoki();
        }
        updatedRow = row;
        updatedColumn = column - 1;
        cells[updatedRow][updatedColumn].setIcon("⚫");
        display();
        takeTurns();
    }
    //Displays patterns to the User
    void takeTurns()
    {
        System.out.println("Move ⬆ Using W, Down ⬇ Using S, Left ⬅ Using A, and Right ➡︎ Using D ");
        System.out.println("Press 2 for spells");
        System.out.println("Press 0 to exit the game");
        getInputs();
    }
    //Gets input from the user
    void getInputs()
    {
        Scanner scanner = new Scanner(System.in);
        String Moves = scanner.next();
        if(Moves.isEmpty() || Moves.length() > 1)
        {
            System.out.println("Please Enter the Correct Sequence");
            takeTurns();
        }
        else if( Moves.charAt(0) == 'W')
        {
            this.updatedRow--;
            newPosition(updatedRow, updatedColumn);
        }
        else if( Moves.charAt(0) == 'S')
        {
            this.updatedRow++;
            newPosition(updatedRow, updatedColumn);
        }
        else if (Moves.charAt(0) == 'A')
        {
            this.updatedColumn--;
            newPosition(updatedRow, updatedColumn);
        }
        else if (Moves.charAt(0) == 'D')
        {
            this.updatedColumn++;
            newPosition(updatedRow, updatedColumn);
        }
        else if (Moves.charAt(0) == '2')
        {
            if( NUM_OF_SPELLS_REMAINING < 1)
            {
                System.out.println("Maximum of 3 spells Allowed");
                takeTurns();
            }
            spells();
            takeTurns();
        }
        else if (Moves.charAt(0) == '0')
        {
            System.out.println("Exiting the Game");
            exit(-1);
        }
        else
        {
            System.out.println("Please only Choose from above Options");
            System.out.println("Please Enter Input Again");
            takeTurns();
        }
        display();
        takeTurns();
    }
    //Checks how many spells are remaining and helps the user in Spell selection
    void spells()
    {
        int spellNumber = 0;
        System.out.println("1. Jump the player to another grid location\n" +
                           "2. Randomly reveal the location of one of the Tokimons or\n" +
                           "3. Randomly kill off one of the Fokimons\n");
        try {
            Scanner scanner = new Scanner(System.in);
            spellNumber = scanner.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Please enter the correct Input!!!");
            takeTurns();
        }

        if (spellNumber < 1 || spellNumber > 3)
        {
            System.out.println("Please enter the Number!!!");
            takeTurns();
        }
        else
        {
            NUM_OF_SPELLS_REMAINING--;
            spellCheck(spellNumber);
        }

    }

    //Validating Spells
    private void spellCheck(int result) {
        if(result == 1 )
        {
            Random random = new Random();
            int x,y;
            x = random.nextInt(10);
            y = random.nextInt(10);
            cells[x][y].setIcon("⚫");

            if (cells[x][y].getFokimon() == "\uD83E\uDD85")
            {
                System.out.println("Noooooooooooooooo!!! YOU LOST!!! You stepped on FOKIMON :( GAME OVER!");
                exit(0);
            }
            this.updatedRow = x;
            this.updatedColumn = y;
            display();
        }
        else if (result == 2)
        {
            for (Map.Entry<Integer, Integer> entry : indexContainer.entrySet()) {
                int x = entry.getKey();
                int y = entry.getValue();

                if (cells[x][y].getTokimon() == "\uD83D\uDC36")
                {
                    cells[x][y].setIcon("\uD83D\uDC36");
                    display();
                    indexContainer.remove(x);
                    break;
                }
            }
        }
        else if (result ==3)
        {
            for (Map.Entry<Integer, Integer> entry : indexContainer.entrySet()) {
                int x = entry.getKey();
                int y = entry.getValue();

                if (cells[x][y].getFokimon() == "\uD83E\uDD85")
                {
                    cells[x][y].setIcon("\uD83E\uDD85");
                    display();
                    indexContainer.remove(x);
                    break;
                }
            }
        }
    }

    //Checling if the location has Fokimon and Tokimon
    void newPosition(int row, int column)
    {
        row = validateRows(row);
        column = validateColumns(column);
        if(cells[row][column].getFokimon() == "\uD83E\uDD85")
        {
            System.out.println("Noooooooooooooooo!!! YOU LOST!!! You stepped on FOKIMON :( GAME OVER!");
            displayResult();
            display();
            exit(0);
        }
        else if(cells[row][column].getTokimon() == "\uD83D\uDC36")
        {
            cells[row][column].setIcon("\uD83D\uDC36");
            if(cells[row][column].getVisited() == false )
            {
                currentFoundNumOfToki++;
            }
            cells[row][column].setVisited(true);
            if(currentFoundNumOfToki == totalNumOfToki)
            {
                System.out.println("YOU DID IT!! YOU WONNNN!!! HURRAYYYYY!!!");
                displayResult();
                display();
                exit(0);
            }
        }
        else {
            cells[row][column].setIcon("⭕");
        }
    }

    private int validateRows(int row) {
        if (row < 0 )
        {
          this.updatedRow++;
        }
        else if(row >9)
        {
            this.updatedRow--;
        }
        return this.updatedRow;
    }
    private int validateColumns(int column) {
        if (column < 0 )
        {
            this.updatedColumn++;
        }
        else if(column >9)
        {
            this.updatedColumn--;
        }
        return this.updatedColumn;
    }
    //Displaying the final Results
    private void displayResult()
    {
        for (int i = 0; i < this.cells.length;i++)
        {
            for (int j = 0 ; j < this.cells.length; j++)
            {
                if ( this.cells[i][j].getTokimon() == "\uD83D\uDC36")
                {
                    this.cells[i][j].setIcon(this.cells[i][j].getTokimon());
                }
                if (this.cells[i][j].getFokimon() == "\uD83E\uDD85")
                {
                    this.cells[i][j].setIcon(this.cells[i][j].getFokimon());
                }
            }
        }
    }
}

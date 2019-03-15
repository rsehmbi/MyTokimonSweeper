package ca.cmpt213.uipackage;

import ca.cmpt213.gamelogic.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Board class that contains an 2D array of Cells and each cell contains icon, location and more information as stated in Cell Class.
 * Helps in displaying the Board on screen a
 * Helps in randomly placing Tokimon and Fokimons
 */
public class Board {
    public static final  int NUM_ROWS =10;
    public static final  int NUM_COLS =10;
    private int NUM_OF_TOKIMONS = 0;
    private int NUM_OF_FOKIMONS = 0;
    protected int NEW_NUMBER_OF_TOKIMON;
    protected int NEW_NUM_OF_FOKIMON;
    private int BOUND = 10;
    protected static Cell[][] cells = new Cell[NUM_ROWS][NUM_COLS];
    protected Map<Integer, Integer> indexContainer = new HashMap<Integer, Integer>();


    //Constructor to make a 10*10 Board
    public Board()
    {
       for (int i = 0; i < NUM_ROWS ; i++)
           for(int j = 0 ; j < NUM_COLS ;j++)
           {
               cells[i][j] = new Cell();
           }
    }


    public void setNum_of_Fokimons(int num_of_Fokimons) {
        NUM_OF_FOKIMONS = num_of_Fokimons;
    }

    public void setNUM_OF_TOKIMONS(int NUM_OF_TOKIMONS) {
        this.NUM_OF_TOKIMONS = NUM_OF_TOKIMONS;
    }

    public int getNum_of_Fokimons() {
        return NUM_OF_FOKIMONS;
    }

    public int getNUM_OF_TOKIMONS() {
        return NUM_OF_TOKIMONS;
    }
    //Patter described in Requirements ABCDEFGHIJ and 12345678910
    void pattern()
    {
        System.out.print("   ");
        for (int index = 1 ; index <= 10 ; index++)
        {
            System.out.print("   " +index );
        }
        System.out.println(" ");
        System.out.println();
    }

    protected void displayTokiAndFoki()
    {
        pattern();
        for (int index = 0; index < this.cells.length;index++)
        {
            int Alphabet = index+65;
            System.out.print(((char)Alphabet) + "    ");
            for (int indexj = 0 ; indexj < this.cells.length; indexj++)
            {
                System.out.print(this.cells[index][indexj].getTokimon() + " ");
                System.out.print(this.cells[index][indexj].getFokimon() + " ");
                if (this.cells[index][indexj].getFokimon() != "\uD83E\uDD85" && this.cells[index][indexj].getTokimon() != "\uD83D\uDC36") {
                   System.out.print(this.cells[index][indexj].getIcon() + "  ");
                }
                System.out.print("->"+"("+(char)Alphabet +""+ indexj+")");
            }
            System.out.println();
        }
    }

    public void display()
    {
        pattern();
        for (int index = 0; index < this.cells.length;index++)
        {
            int Alphabet = index+65;
            System.out.print(((char)Alphabet) + "    ");
            for (int indexj = 0 ; indexj < this.cells.length; indexj++)
            {
                System.out.print(this.cells[index][indexj].getIcon()+ "  ");
            }
            System.out.println();
        }
    }

    //Randomly placing Tokimon and Fokimons on the Board
    protected void randomlyPlaceTokimonsAndFokimons(int a, int b)
    {
        Random random = new Random();
        int RANDOM_ROW_NUMBER,RANDOM_COLUMN_NUMBER;
        while(a != this.NUM_OF_TOKIMONS) {
            do {
                RANDOM_ROW_NUMBER = random.nextInt(BOUND);
                RANDOM_COLUMN_NUMBER = random.nextInt(BOUND);
                if (!indexContainer.containsKey(RANDOM_ROW_NUMBER) || indexContainer.get(RANDOM_ROW_NUMBER) != RANDOM_COLUMN_NUMBER) {
                    indexContainer.put(RANDOM_ROW_NUMBER, RANDOM_COLUMN_NUMBER);
                    this.cells[RANDOM_ROW_NUMBER][RANDOM_COLUMN_NUMBER].setTokimon();
                    this.NUM_OF_TOKIMONS++;
                    break;
                }
            } while (true);
        }

        while(b != this.NUM_OF_FOKIMONS) {
            do {
                RANDOM_ROW_NUMBER = random.nextInt(BOUND);
                RANDOM_COLUMN_NUMBER = random.nextInt(BOUND);
                if (!indexContainer.containsKey(RANDOM_ROW_NUMBER) || indexContainer.get(RANDOM_ROW_NUMBER) != RANDOM_COLUMN_NUMBER || cells[RANDOM_ROW_NUMBER][RANDOM_COLUMN_NUMBER].getTokimon() != "\uD83D\uDC36") {
                    indexContainer.put(RANDOM_ROW_NUMBER, RANDOM_COLUMN_NUMBER);
                    this.cells[RANDOM_ROW_NUMBER][RANDOM_COLUMN_NUMBER].setFokimon();
                    this.NUM_OF_FOKIMONS++;
                    break;
                }
            } while (true);
        }
    }

    public void setNumOfTokiAndFoki(int numoftoki, int numoffoki, int argumentlength)
    {
        NEW_NUMBER_OF_TOKIMON = numoftoki ;
        NEW_NUM_OF_FOKIMON = numoffoki;

        Game game = new Game();
        game.Start(NEW_NUMBER_OF_TOKIMON, NEW_NUM_OF_FOKIMON,argumentlength);
    }
}

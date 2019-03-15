package ca.cmpt213.uipackage;

/**  Cell class to hold the visited, icon, current position of a tokimon and fokimon in 10*10 Matrix */
public class Cell {
    private boolean visited;
    private String icon;
    private String tokimon;
    private String fokimon;
    private String currentPosition;

    //Constructor
    Cell ()
    {
        visited =false;
        icon = "\uD83D\uDD34";
        tokimon ="";
        fokimon ="";
        currentPosition ="";
    }
    //Getters and Setters
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }
    public boolean getVisited()
    {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getTokimon() {
        return tokimon;
    }

    public void setTokimon() {
        tokimon = "\uD83D\uDC36";
    }


    public void setFokimon() {
        fokimon = "\uD83E\uDD85";
    }

    public String getFokimon() {
        return fokimon;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition() {
        currentPosition = "*️";
    }
}

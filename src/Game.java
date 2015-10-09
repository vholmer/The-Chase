import java.util.Scanner;

/**
 *  This class is the main class of the "The Chase" application. 
 *  "The Chase" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery, talk to characters, pick up items,
 *  shoot people, participate in certain action packed events and more!
 * 
 *  This main class creates and initializes all the others: it creates a
 *  player object, creates the parser and starts the game.  It also prints
 *  a welcome message at the start of the game.
 * 
 * @author  Viktor Holmér
 * @version 2013.12.18 
 */

public class Game 
{  
    private boolean quitBoolean;
    private Parser parser;
    private Player player;
    private Scanner scan;
    
    /**
     * The main method creates the game object,
     * and calls its play method.
     */
    public static void main(String[] args)
    {
    	Game spel = new Game();
    	spel.play();
    }
        
    /**
     * Create the game and initializes a player object.
     */
    public Game() 
    {
    	quitBoolean = false;
        parser = new Parser();
        player = new Player(this);
        scan = new Scanner(System.in);
    }
    
    /**
     * Simply returns the parser of the game object.
     * 
     * @return The parser of this instance of the game class.
     */
    public Parser getParser()
    {
    	return parser;
    }
    
    /**
     *  Main play routine. Loops until end of play.
     *  Or until quitGame() is called, which sets
     *  quitBoolean to false.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over. 
        boolean finished = false;
        
        while (! finished) {
            Command command = parser.getCommand();
            finished = player.processCommand(command);
            
            if(quitBoolean == true)
            	break;
            
            if(player.getPosition().isTheEnd()) {
            	if(player.getHardcore())
            		player.printLocationInfo();
            	break;
            }
            
            if(player.getHardcore() && player.getMoves() == 0) {
            	System.out.println("BOOM!"
            			       + "\nYou have taken too long to find the bomb. Clara has"
            			       + "\ndetonated an entire block and killed billions."
            			       + "\nYou have failed.");
            	break;
            }
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to The Chase!");
        System.out.println("The Chase is a new, incredibly action packed");
        System.out.println("and awesome adventure game!");
        System.out.println("Type 'help' if you need any help.");
        System.out.println();
        player.printLocationInfo();
    }
    
    /**
     * Quits the game.
     */
    public void quitGame()
    {
    	quitBoolean = !quitBoolean;
    }
    
    /**
     * Presents the player with the option to retry
     * the game, and thus start it again once they've
     * died and failed.
     */
    public void retryGame()
    {
    	System.out.println("Would you like to retry?");
    	System.out.println("1. Yes!");
    	System.out.println("2. No!");
    	
    	String selection;
    	
    	do {
        	//scan reads the chosen option for this method. (1 or 2)
    		selection = scan.next();

    		//the if statement checks if selection has a valid value.
    		if(!selection.equals("2") && !selection.equals("1")) {
        		System.out.println("Answer 1 or 2!");
        	}
    	} while(!selection.equals("2") && !selection.equals("1"));
    	
    	if(selection.equals("1")) {
    		player = new Player(this);
    		printWelcome();
    		return;
    	}
    	
    	else if(selection.equals("2")) {
    		quitGame();
    	}
    }
}
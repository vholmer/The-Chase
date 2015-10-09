/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 *
 * @author  Viktor Holmer
 * @version 2013.12.18
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "back", "take", "drop", "inventory", "talk", "show", "charge", "fire", "1", "2", "3", "shoot", "hardcore"
    };

    /**
     * Constructor - initialize the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }
    
    /**
     * Produces all valid commands and returns them
     * in the form of a string.
     */
    public String getCommandList()
    {
        String toPrint = "";
        for(String command : validCommands) {
        	if(!command.equals("1") && !command.equals("2") && !command.equals("3")) {
        		toPrint += command + " ";
        	}
        	//1, 2 or 3 are only valid commands when events are playing, therefore they are not printed here.
        }
        return toPrint;
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}

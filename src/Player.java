import java.util.ArrayList;

/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * Every game needs a player, this class defines just that. The class
 * controls the player's current position, which room he has charged
 * in his beamer, his inventory, his path through the rooms, and
 * processes every command that the player types (sent to parser).
 * Also checks whether an event is playing or not and defines which
 * commands are valid during an event.
 *
 * @author  Viktor Holmér
 * @version 2013.12.19
 */

public class Player
{
	private Parser parser;
    private Room currentRoom;
    private Room chargedRoom;
    private ArrayList<Room> roomPath;
    private ArrayList<Item> inventory;
    private boolean eventPlaying;
    private final String[] validEventCommands = {"1", "2", "3"};
    private Game game;
    private boolean hardcoreMode;
    private int moves;
    private boolean justStarted;
    
    /**
     * Create a player object in a certain game.
     * 
     * @param game The game in which the player will exist.
     */
	public Player(Game game)
	{
		this.game = game;
		moves = 14;
		//ensures that both the game and the player uses the same parser
		parser = game.getParser();
    	eventPlaying = false;
    	hardcoreMode = false;
    	justStarted = true;
        roomPath = new ArrayList<Room>();
        inventory = new ArrayList<Item>();
        Environment world = new Environment();
        //sets the default room to the returned Room of world.bootWorld()
        currentRoom = world.bootWorld();
        chargedRoom = currentRoom;
	}
	
	/**
	 * Prints information about the current location. If the current location
	 * has an event information about it will be printed instead.
	 * 
	 * @see Event#getStart
	 */
	public void printLocationInfo()
	{
    	if(!currentRoom.hasEvent())
    		System.out.println(currentRoom.getLongDescription());
    	else {
    		System.out.println(currentRoom.getEvent().getStart());
    		eventPlay();
    	}
    	if(hardcoreMode && !currentRoom.hasEvent() && !currentRoom.isTheEnd()) {
    		System.out.println("Moves left: " + moves);
    	}
    }
	
	/**
	 * Starts an event.
	 */
	public void eventPlay()
	{
    	eventPlaying = true;
    }
	
	/**
	 * Enables hardcore mode, a limited amount of moves!
	 */
	public void hardcore()
	{
		if(justStarted && !hardcoreMode) {
			hardcoreMode = true;
			System.out.println("You have entered hardcore mode, you must stop Clara"
					       + "\nbefore she detonates the bomb that she has placed"
					       + "\nsomewhere in the city!");
			System.out.println("Moves left: " + moves);
			return;
		}
		else if(hardcoreMode) {
			System.out.println("Hardcore mode is already enabled!");
		}
		else if(!justStarted && !hardcoreMode) {
			System.out.println("You must start hardcore mode at the beginning of the game.");
		}
	}
	
	/**
	 * Prints a description of the current location.
	 */
	private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    } 
    
	/**
	 * Makes the player trace its path backwards.
	 */
    private void back()
    {
        int length = roomPath.size() - 1;
        
        if(length >= 0) {
            currentRoom = roomPath.get(length);
            roomPath.remove(length);
            printLocationInfo();
        }
    }
    
    /**
     * Makes the player take an item.
     * 
     * @param command The second word of this object is the item to take.
     * @see Command#getSecondWord()
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Take which item?");
            return;
        }
        
        else if(!currentRoom.getItems().isEmpty()) {
            for(Item item : currentRoom.getItems()) {
                if(item.getItemName().equals(command.getSecondWord())) {
                    inventory.add(item);
                    System.out.println("You have taken the following item: " + item.getItemName());
                    currentRoom.getItems().remove(item);
                    return;
                }
            }
        }
        
        //if the program gets to this point, the item did not exist in the room.
        System.out.println("That item does not exist in this room.");
    }
    
    
    /**
     * Makes the player drop an item.
     * 
     * @param command The second word of this object is the item to drop.
     * @see Command#getSecondWord()
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop which item?");
            return;
        } 
        
        else if(!inventory.isEmpty()) {
            for(Item item : inventory) {
                if(item.getItemName().equals(command.getSecondWord())) {
                    System.out.println("You have dropped the following item: " + item.getItemName());
                    currentRoom.addItem(item);
                    inventory.remove(item);
                    return;
                }
            }
        }
        
        //if the program gets to this point, the item wasn't in the player's inventory.
        System.out.println("Your inventory does not contain this item.");
    }
    
    /**
     * Prints what the player has in their inventory. If the inventory
     * is empty, it prints a certain message.
     */
    private void inventoryGet()
    {
        System.out.print("Items in your inventory: ");
        if(!inventory.isEmpty()) {
            for(Item item : inventory) {
                System.out.print(item.getItemName() + " ");
            }
        }
        else {
            System.out.print("YOU HAVE NOTHING LOL");
        }
        System.out.println();
    }
     
    /**
     * Makes the player talk to a character in the room. If the character
     * has an item to give to the player, then this will be done.
     * 
     * @param command The second word of this object is the name of the
     * character to talk to.
     * @see Command#getSecondWord()
     */
    private void talk(Command command)
    {		
        if(!command.hasSecondWord()) {
            System.out.println("Talk to who?");
            return;
        }
        
        else if(!currentRoom.getChars().isEmpty()) {
            for(Character charInRoom : currentRoom.getChars()) {
                if(charInRoom.getName().equals(command.getSecondWord())) {
                    System.out.println(charInRoom.getDialogue());
                    if(charInRoom.hasItemToGive()) {
                    	inventory.add(charInRoom.getItemToGive());
                    	System.out.println("\nItems added to your inventory: " + charInRoom.getItemToGive().getItemName());
                    	charInRoom.removeItemToGive();
                    }
                    return;
                }
            }
        }
        
        //if the program gets to this point, the character wasn't in the room.
        System.out.println("That person is not in this particular place.");
    }

    /**
     * Makes the player show an item to the characters in the current
     * room. If the player doesn't have this item in their inventory
     * print an error message. If there are no characters or if there
     * are no reaction from any character, print "nothing happens".
     * 
     * @param command The second word of this object is the name of
     * the item to show.
     * @see Command#getSecondWord()
     */
    private void show(Command command)
    {
        boolean existing = false;

        if(!command.hasSecondWord()) {
            System.out.println("Show what?");
            return;
        }

        //the for loop checks whether the player has the item or not, if yes then existing = true else false
        for(Item item : inventory) {
                if(command.getSecondWord().equals(item.getItemName())) {
                    existing = true;
                    break;
                }
                else {
                    existing = false;
                }
            }
        
        //asserts that the room contains characters and that the player has the item to show
        if(!currentRoom.getChars().isEmpty() && existing == true) {
            for(Character charInRoom : currentRoom.getChars()) {
            	if(charInRoom.hasActionItem()) {
            		//if the name of the item the character will react to equals the player input
	                if(charInRoom.getActionItem().getItemName().equals(command.getSecondWord())) {
	                    System.out.println(charInRoom.getActionDialogue());
	                    return;
	                }
            	}
            }
        }
        
        //if the player doesn't have the item or if the inventory is empty
        if(existing == false || inventory.isEmpty()) {
            System.out.println("Your inventory does not contain this item.");
            return;
        }
        
        /* if the program gets here the player had the item but no character reacted or
         * there where no characters.
         */
        System.out.println("Nothing happens.");
    }
    
    /**
     * Stores the current room in the beamer. If the player fires the beamer
     * he will be transported to this stored room.
     * 
     * @see #fire()
     */
    private void charge()
    {
    	if(currentRoom.getBeam()) {
	        chargedRoom = currentRoom;
	        System.out.println("Your Beamer has charged the current room.");
    	}
    	else {
    		System.out.println("You've dropped your beamer! You can't find it!");
    	}
    }
    
    /**
     * Fires the beamer, transporting the player to the currently stored
     * Room object.
     * 
     * @see #charge()
     */
    private void fire()
    {
    	if(currentRoom.getBeam()) {
	        currentRoom = chargedRoom;
	        System.out.println("You fire the Beamer, and you're instantly transported.");
	        printLocationInfo();
    	}
    	else {
    		System.out.println("You've dropped your beamer! You can't find it!");
    	}
    }
    
    /**
     * Attempt to shoot a character. If successful the character will
     * die and will thus be removed from the game. If the character
     * is important the game will end and print appropriate text.
     * The player must have the gun item for this.
     * 
     * @param command The second word of this object is the character to shoot.
     * @see Character#setImportant()
     * @see Command#getSecondWord()
     */
    private void shoot(Command command)
    {
    	if(!command.hasSecondWord()) {
    		System.out.println("Shoot who?");
    		return;
    	}
    	
    	String secondWord = command.getSecondWord();
    	Boolean hasGun = false;
    	
    	//checks if the player has a gun
    	for(Item item : inventory) {
    		if(item.getItemName().equals("gun")) {
    			hasGun = true;
    		}
    	}
    	
    	if(hasGun) {
			for(Character charInRoom : currentRoom.getChars()) {
				if(secondWord.equals(charInRoom.getName())) {
					System.out.println("You pull out your gun and fire at " + charInRoom.getName() + ".");
					//quits the game if the character is important.
					if(charInRoom.isImportant()) {
						System.out.println("You have killed an innocent person. You will be apprehended by the police."
					                   + "\nYour mission to stop Clara has failed.");
						game.retryGame();
						return;
					}
					
					else {
						System.out.println("You have succesfully killed " + charInRoom.getName() + ".");
						currentRoom.getChars().remove(charInRoom);
						return;
					}
				}
				
				else {
					System.out.println("That person is not in this particular place. Perhaps you have the wrong name?");
					return;
				}
			}
    	}
    	
    	System.out.println("You do not have a gun.");
    }
    
    /**
     * Prints the result of picking a certain option during an event. (1, 2 or 3)
     * 
     * @param option The chosen option.
     * @see Room#getEventCase(String)
     * @see Event
     */
    public void printEventResult(String option)
    {
    	if(option == "1") {
    		System.out.println(currentRoom.getEvent().getFirstResult());
    	}
    	else if(option == "2") {
    		System.out.println(currentRoom.getEvent().getSecondResult());
    	}
    	else if(option == "3") {
    		System.out.println(currentRoom.getEvent().getThirdResult());
    	}
    	
		if(currentRoom.getEventCase(option) == true) {
			currentRoom.removeEvent();
			eventPlaying = false;
			System.out.println();
			printLocationInfo();
		}
		else {
			game.retryGame();
		}
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If an event is playing, check for the event specific
     * commands: 1, 2, 3 or an event specific help command.
     * 
     * @param command The command to be processed.
     * @return True if the command ends the game, false otherwise.
     * @see #validEventCommands
     */
    public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        
        if(eventPlaying) {
        	//below are the different commands that are usable during events. (1, 2, 3, help, quit and look)
        	if(commandWord.equals("1")) {
        		printEventResult("1");
        	}
        	else if(commandWord.equals("2")) {
        		printEventResult("2");
        	}
        	else if(commandWord.equals("3")) {
        		printEventResult("3");
        	}
        	else if(commandWord.equals("help")) {
        		System.out.println("This is a quicktime event! Enter 1, 2 or 3 to choose an option!"
        				       + "\nYou may use the look command to review the event once more.");
        	}
        	else if(commandWord.equals("quit")) {
        		game.quitGame();
        	}
        	else if(commandWord.equals("look")) {
        		System.out.println(currentRoom.getEvent().getStart());
        	}
        	//checks whether the entered command is part of the ArrayList validEventCommands.
        	else {
        		boolean checkCommand = true;
        		
        		for(int i = 0; i < validEventCommands.length; i++) {
        			if(!commandWord.equals(validEventCommands[i])) {
        				checkCommand = false;
        			}
        		}
        		
        		if(checkCommand == false) {
        			System.out.println("I don't know what you mean...");
        		}
        	}
        }
        
        else if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("inventory")) {
            inventoryGet();
        }
        else if (commandWord.equals("talk")) {
            talk(command);
        }
        else if (commandWord.equals("show")) {
            show(command);
        }
        else if (commandWord.equals("charge")) {
            charge();
        }
        else if (commandWord.equals("fire")) {
            fire();
        }
        else if (commandWord.equals("shoot")) {
        	shoot(command);
        }
        else if (commandWord.equals("hardcore")) {
        	hardcore();
        }
        else if (commandWord.equals("1")) {
        	System.out.println("I don't know what you mean...");
        }
        else if (commandWord.equals("2")) {
        	System.out.println("I don't know what you mean...");
        }
        else if (commandWord.equals("3")) {
        	System.out.println("I don't know what you mean...");
        }

        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are looking for Clara, the bounty hunter.");
        System.out.println("Search the city for clues!");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }
    
    /**
     * @return The room the player is currently in.
     */
    public Room getPosition()
    {
    	return currentRoom;
    }
    
    /**
     * @return True if hardcore mode enabled, else false.
     */
    public boolean getHardcore()
    {
    	return hardcoreMode;
    }
    
    /**
     * @return The number of moves left in hardcore mode.
     */
    public int getMoves()
    {
    	return moves;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * If the room is locked but the player has the correct
     * key, then enter the room and print an unlockString.
     * Remove the key from the inventory if the item is set
     * to be removed when used.
     * 
     * @see Room#getUnlockText()
     * @see Item#setToRemove()
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        String removedText = "";
        boolean toPrintRemoved = false;
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        Room nextLockedRoom = currentRoom.getLockedExit(direction);

        if(nextRoom == null && command.hasSecondWord()) {
            if(nextLockedRoom != null && !inventory.isEmpty()) {
                for(Item keyToRoom : inventory) {
                    if(keyToRoom.getItemName().equals(nextLockedRoom.getKey().getItemName()) && moves >= 1) {
                        roomPath.add(currentRoom);
                        currentRoom.removeLockedExit(direction, nextLockedRoom);
                        
                        if(nextLockedRoom.getKey().toRemove()) {
                        	removedText = "Items removed from your inventory: " + nextLockedRoom.getKey().getItemName();
                        	inventory.remove(nextLockedRoom.getKey());
                        	toPrintRemoved = true;
                        }
                        
                        currentRoom = nextLockedRoom;
                        nextLockedRoom.removeKey();
                        
                        if(!eventPlaying) {
                        	System.out.println(currentRoom.getUnlockText());
                        }
                        
                        if(hardcoreMode) {
                        	moves--;
                        }
                        
                        if(moves > 0)
                        	printLocationInfo();
                        
                        if(toPrintRemoved && !currentRoom.hasEvent()) {
                        	System.out.println(removedText);
                        }
                        
                        return;
                    }
                }
            }
            
            else if(nextLockedRoom == null) {
            	System.out.println("There is no available path.");
            	return;
            }
            
            System.out.println("You need a certain item to pass through!");
        }
                   
        else if(nextRoom == null) {
            System.out.println("There is no available path!");
        }
            
        if(nextRoom != null && command.hasSecondWord() && moves >= 1) {
        	justStarted = false;
            roomPath.add(currentRoom);
            currentRoom = nextRoom;
            
            if(hardcoreMode) {
            	moves--;
            }
            
            if(moves > 0)
            	printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
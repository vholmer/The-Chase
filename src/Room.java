import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Viktor Holmér
 * @version 2013.12.18
 */
public class Room 
{
    private String description, unlockString;
    private HashMap<String, Room> exits;
    private HashMap<String, Room> lockedExits;
    private HashMap<String, String> oppositeDirections;
    private HashMap<String, Boolean> eventMap;
    private ArrayList<Item> itemsInRoom;
    private ArrayList<Character> charsInRoom;
    private Item key;
    private Event event;
    private boolean canBeam;
    private boolean theEnd;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        eventMap = new HashMap<String, Boolean>();
        itemsInRoom = new ArrayList<Item>();
        charsInRoom = new ArrayList<Character>();
        lockedExits = new HashMap<String, Room>();
        oppositeDirections = new HashMap<String, String>();
        canBeam = true;
        theEnd = false;
        setUpOpposites();
    }
    
    /**
     * Adds a string to be printed when the room unlocks, if it is locked.
     * 
     * @param unlockString The string to be printed when the room is unlocked.
     */
    public void addUnlockString(String unlockString)
    {
    	this.unlockString = unlockString;
    }
    
    /**
     * @return True if possible to use beamer in room, else false.
     */
    public boolean getBeam()
    {
    	return canBeam;
    }
    
    /**
     * Makes it impossible to use the beamer in the room.
     */
    public void setNoBeam()
    {
    	canBeam = false;
    }
    
    /**
     * Adds the event object to the room.
     * 
     * @param event The event to add.
     */
    public void addEvent(Event event)
    {
    	this.event = event;
    }
    
    /**
     * Adds a key which unlocks the room.
     * 
     * @param key The key needed to unlock the room.
     */
    public void addKey(Item key)
    {
    	this.key = key;
    }
    
    /**
     * Returns the string to be printed when
     * the room is unlocked.
     * 
     * @return String to be printed when unlocked.
     */
    public String getUnlockText()
    {
    	return unlockString;
    }
    
    /**
     * Adds an item to the room.
     * 
     * @param itemToAdd The item to add.
     */
    public void addItem(Item itemToAdd)
    {
        itemsInRoom.add(itemToAdd);
    }
    
    /**
     * Maps a certain option in an event to a boolean.
     * If the boolean is true you have survived
     * the event and picked the right option.
     * 
     * @param option The option to set. (1, 2 or 3)
     * @param alive Sets the boolean alive: True if correct option, else false.
     */
    public void setEventCase(String option, boolean alive) {
    	eventMap.put(option, alive);
    }
    
    /**
     * Returns true or false based on if the chosen option
     * is the correct option or not.
     * 
     * @param option The option you chose. (1, 2 or 3)
     * @return True if correct, else false
     */
    public boolean getEventCase(String option) {
    		return eventMap.get(option);
    }
    
    /**
     * Removes the event from the room by setting its
     * value to null.
     */
    public void removeEvent() {
    	event = null;
    }
    
    /**
     * Checks whether the room has an event or not.
     * 
     * @return True if the room has an event, else false.
     */
    public boolean hasEvent()
    {
    	if(event != null) {
    		return true;
    	}
    	
    	else return false;
    }
    
    /**
     * Returns the event of the room.
     * 
     * @return The event of the room.
     */
    public Event getEvent()
    {
    	return event;
    }
    
    /**
     * Returns the key which unlocks the room.
     * 
     * @return The key to the room.
     */
    public Item getKey()
    {
    	return key;
    }
    
    /**
     * Removes the key from the room by setting its
     * value to null.
     */
    public void removeKey()
    {
    	this.key = null;
    }
    
    /**
     * Adds a character object to the room
     * 
     * @param charToAdd The character to add to the room.
     */
    public void addCharacter(Character charToAdd)
    {
        charsInRoom.add(charToAdd);
    }
    
    /**
     * Removes a character object from the room.
     * 
     * @param charToRemove The character object to remove.
     */
    public void removeCharacter(Character charToRemove)
    {
    	charsInRoom.remove(charToRemove);
    }
    
    /**
     * Removes an Item object from the room.
     * 
     * @param itemToRemove The Item object to remove.
     */
    public void removeItem(Item itemToRemove)
    {
        itemsInRoom.remove(itemToRemove);
    }
    
    /**
     * Returns the Item objects in the room.
     * 
     * @return ArrayList containing every Item object currently in the room.
     */
    public ArrayList<Item> getItems()
    {
        return itemsInRoom;
    }
    
    /**
     * Returns the Character objects in the room.
     * 
     * @return ArrayList containing every Character object currently in the room.
     */
    public ArrayList<Character> getChars()
    {
        return charsInRoom;
    }
    
    /**
     * Returns a room in a given direction.
     * 
     * @param direction The direction to which a specific room is mapped.
     * @return The room located in the given direction relative to the current room.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Returns a locked room in a given direction.
     * 
     * @param direction The direction to which a specific locked room is mapped.
     * @return The locked room located in the given direction. Null if there is no locked room in that direction.
     */
    public Room getLockedExit(String direction)
    {
        if(!lockedExits.isEmpty()) {
            return lockedExits.get(direction);
        }
        
        return null;
    }
    
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * 
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        Set<String> lockedKeys = lockedExits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        
        if(!lockedKeys.isEmpty()) {
	        for(String lockedExit : lockedKeys) {
	            returnString += " " + lockedExit;
	        }
        }
        //The if-statement adds any potential "locked directions" to the returnString.

        return returnString;
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
    	if((direction != null) && (neighbor != null)) {
        	exits.put(direction, neighbor);
    	}
    }
    
    /**
     * Define a locked exit from this room.
     * 
     * @param direction The direction of the exit.
     * @param lockedRoom The locked room in the given direction.
     */
    public void setLockedExit(String direction, Room lockedRoom)
    {
        if((direction != null) && (lockedRoom != null)) {
            lockedExits.put(direction, lockedRoom);
        }
    }
    
    /**
     * Returns the opposite direction. "north" will return "south"
     * "up" will return "down" and so on.
     * 
     * @param direction The direction input.
     * @return The opposite of the direction input.
     */
    public String getOpposite(String direction)
    {
    	return oppositeDirections.get(direction);
    }
    
    /**
     * Removes a locked exit from a room, and turns it into
     * a normal exit that isn't locked.
     * 
     * @param direction The direction of the locked exit to remove.
     * @param neighbor The room behind the locked exit.
     */
    public void removeLockedExit(String direction, Room neighbor)
    {
    	lockedExits.remove(direction);
    	exits.put(direction, neighbor);
    }
    
    /**
     * Maps "north" to "south" and so on in the HashMap called oppositeDirections.
     */
    private void setUpOpposites()
    {
    	oppositeDirections.put("north", "south");
    	oppositeDirections.put("east", "west");
    	oppositeDirections.put("south", "north");
    	oppositeDirections.put("west", "east");
    	oppositeDirections.put("up", "down");
    	oppositeDirections.put("down", "up");
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The description of the items in the room.
     */
    public String getItemDescription()
    {
        String returnString = "";
        
        if(!itemsInRoom.isEmpty()) {
            for(Item item : itemsInRoom) {
                returnString += item.getDescription() + "\n";
            }
        }
        
        return returnString;
    }
    
    /**
     * @return The description of the characters in the room.
     */
    public String getCharacterDescription()
    {
        String returnString = "";
        
        if(!charsInRoom.isEmpty()) {
            for(Character character : charsInRoom) {
                returnString += character.getDescription() + "\n";
            }
        }
        
        return returnString;
    }
    
    /**
     * @return True if room is the end, else false.
     */
    public boolean isTheEnd()
    {
    	return theEnd;
    }
    
    /**
     * Sets the room to the very final room, the end.
     */
    public void setTheEnd()
    {
    	theEnd = true;
    }
    
    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen.
     *  A knife is on the table.
     *  A guy named Charlie is next to the table.
     *  Exits: north west
     *  
     * @return A description of the room, including exits, characters and items.
     */
    public String getLongDescription()
    {
    	String returnString = "";
    	
    	if(getExitString().trim().equals("Exits:")) {
	       returnString += description + "\n" + getItemDescription()
	        + getCharacterDescription();
    	}
    	
    	else {
    		returnString += description + "\n" + getItemDescription()
    		        + getCharacterDescription() + "\n" + getExitString() + "\n";
    	}
    	
    	return returnString;
    }
}
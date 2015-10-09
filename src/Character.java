/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * This class defines the characters that one are able to interact
 * with throughout the course of the game.
 *
 * @author  Viktor Holmer
 * @version 2013.12.19
 */

public class Character
{
    private String description;
    private String dialogue;
    private String actionDialogue;
    private Item action;
    private Item toGive;
    private String name;
    private String secondDialogue;
    private boolean important;
    
    /**
     * Create a new character with a description
     * and a name.
     * 
     * @param description The description of the character.
     * @param name The name of the character.
     */
    public Character(String description, String name)
    {
        this.description = description;
        this.name = name;
        this.important = false;
    }
    
    /**
     * @return True if the character will react to an item.
     * @see #addActionItem(Item)
     */
    public Boolean hasActionItem()
    {
    	if(action != null) {
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * @return True of the character has an item to give you, else false.
     */
    public Boolean hasItemToGive()
    {
    	if(toGive != null) {
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * @return True if the character is important (not possible to kill), else false.
     */
    public Boolean isImportant()
    {
    	return important;
    }
    
    /**
     * Makes the character important, meaning it cannot be killed without consequence.
     */
    public void setImportant()
    {
    	this.important = true;
    }
    
    /**
     * Removes any item the character has to give you by setting the
     * item's value to null.
     */
    public void removeItemToGive()
    {
    	toGive = null;
    }
    
    /**
     * @return The item the character has to give you.
     */
    public Item getItemToGive()
    {
    	return toGive;
    }
    
    /**
     * Adds an item that the character will give to you
     * if you talk to it.
     * 
     * @param toGive The item to give to the player.
     */
    public void addItemToGive(Item toGive)
    {
    	this.toGive = toGive;
    }
    
    /**
     * Adds an item the character will react to if the
     * player shows it.
     * 
     * @param action The item that triggers the reaction.
     */
    public void addActionItem(Item action)
    {
    	this.action = action;
    }
    
    /**
     * Adds a reaction dialogue that will trigger if a
     * certain item is shown by the player.
     * 
     * @see #addActionItem
     * @param actionDialogue Adds reaction dialogue to the character.
     */
    public void addActionDialogue(String actionDialogue)
    {
    	this.actionDialogue = actionDialogue;
    }
    
    /**
     * Adds dialogue to the character.
     * 
     * @param dialogue The dialogue to add.
     */
    public void addDialogue(String dialogue)
    {
    	this.dialogue = dialogue;
    }
    
    /**
     * Adds more dialogue to the character.
     * If the character has been talked to once and
     * has more dialogue, only the latter will be printed.
     * 
     * @param secondDialogue The dialogue to add.
     */
    public void addSecondDialogue(String secondDialogue)
    {
    	this.secondDialogue = secondDialogue;
    }
    
    /**
     * @return The name of the character.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return The reaction dialogue of the character.
     * @see #addActionDialogue
     */
    public String getActionDialogue()
    {
        return actionDialogue;
    }
    
    /**
     * @return The dialogue of the character. Returns the second dialogue if it exists.
     * @see #addSecondDialogue
     */
    public String getDialogue()
    {
    	String temp = dialogue;
    	if(secondDialogue != null) {
    		dialogue = secondDialogue;
    	}
        return temp;
    }
    
    /**
     * @return A description of the character.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The item the character will react to.
     */
    public Item getActionItem()
    {
        return action;
    }
}
/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * This class defines the items that one are able to pick up, drop or
 * show to characters throughout the game.
 *
 * @author  Viktor Holmer
 * @version 2013.12.18
 */

public class Item
{
    private String description;
    private String itemName;
    private Boolean toRemove;
    
    /**
     * Creates an item with a description and a name.
     * 
     * @param description Describes the item.
     * @param itemName The name of the item.
     */
    public Item(String description, String itemName)
    {
        this.description = description;
        this.itemName = itemName;
        this.toRemove = false;
    }
    
    /**
     * Sets the value of the boolean toRemove to true.
     * If this is true when the item is used as a key
     * to a room, it will be removed from the inventory
     * of the player.
     */
    public void setToRemove()
    {
    	this.toRemove = true;
    }
    
    /**
     * @return The value of the boolean toRemove.
     */
    public Boolean toRemove()
    {
    	return toRemove;
    }
    
    /**
     * @return The description of the item.s
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return The name of the item.
     */
    public String getItemName()
    {
        return itemName;
    }
}
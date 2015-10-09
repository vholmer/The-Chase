public class Item
{
    private String description;
    private String itemName;
    
    /**
     * Creates an item with a weight and description
     * 
     * @param description Describes the item.
     * @param itemName The name of the item.
     */
    public Item(String description, String itemName)
    {
        this.description = description;
        this.itemName = itemName;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getItemName()
    {
        return itemName;
    }
}
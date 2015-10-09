public class Character
{
    private String description;
    private String dialogue;
    private String actionDialogue;
    private Item action;
    private String name;
    
    public Character(String description, String dialogue, String actionDialogue, Item action, String name)
    {
        this.description = description;
        this.dialogue = dialogue;
        this.actionDialogue = actionDialogue;
        this.action = action;
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getActionDialogue()
    {
        return actionDialogue;
    }
    
    public String getDialogue()
    {
        return dialogue;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Item getActionItem()
    {
        return action;
    }
}
import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Viktor Holmér
 * @version 2013.12.12
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room chargedRoom;
    private ArrayList<Room> roomPath;
    private ArrayList<Item> inventory;
    private int nicotine;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomPath = new ArrayList<Room>();
        inventory = new ArrayList<Item>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room bar, streetnorth, streetcentral, streetsouth, alley, deadend, policehq, market;
        Room residential, apartments, office, construction, rooftop, store, ending;
      
        // create the rooms
        bar = new Room("inside a crowded neon lit pub.");
        streetnorth = new Room("on the north end of the city's main street.");
        streetcentral = new Room("in the middle of the city. Huge buildings line the street.");
        alley = new Room("in a backstreet alley.");
        deadend = new Room("at the end of the alley, a dead end. The path behind you \n has been blocked by a forcefield! You're trapped!");
        policehq = new Room("inside the police headquarters.");
        streetsouth = new Room("on the south end of the city's main street.");
        market = new Room("in an outdoor fish market.");
        residential = new Room("in a residential area. Blocks of apartment buildings reach towards the sky.");
        office = new Room("inside an abandoned office building.");
        apartments = new Room("in a corridor. A few meters down, it turns to the east.");
        construction = new Room("on a construction site.");
        rooftop = new Room("on the roof!");
        store = new Room("in a very crowded store. It will be nearly impossible to locate Clara amgonst the people.");
        ending = new Room("The end.");

        // initialise room exits
        bar.setExit("east", streetnorth);
        
        streetnorth.setExit("south", streetcentral);
        streetnorth.setExit("west", bar);
        
        streetcentral.setExit("north", streetnorth);
        streetcentral.setExit("east", policehq);
        streetcentral.setExit("south", streetsouth);
        streetcentral.setExit("west", alley);
        
        alley.setExit("east", streetcentral);
        alley.setExit("west", deadend);
        
        policehq.setExit("west", streetcentral);
        
        streetsouth.setExit("north", streetcentral);
        streetsouth.setExit("east", market);
        
        market.setExit("east", residential);
        market.setExit("west", streetsouth);
        
        residential.setExit("north", office);
        residential.setExit("up", rooftop);
        residential.setExit("south", apartments);
        residential.setExit("west", market);
        
        office.setExit("south", residential);
        
        apartments.setExit("north", residential);
        apartments.setExit("east", construction);
        
        construction.setExit("west", apartments);
        
        rooftop.setExit("north", store);
        rooftop.setExit("down", residential);
        
        store.setExit("east", ending);
        store.setExit("south", rooftop);
        
        //creates all items and characters.
        Item badge = new Item("A police badge lies in front of you.", "badge");
        Item gun = new Item("A police issue firearm is lying next to you.", "firearm");
        Character birger = new Character(
        "An odd hooded fellow named Birger is standing in the corner.",
        "Don't talk to me, I don't like cops.",
        "Birger suddenly yells: 'No need to pull a gun, man! Chill out!'",
        gun, "birger");
        
        //places the items and characters in the proper rooms.
        bar.addItem(badge);
        bar.addItem(gun);
        bar.addCharacter(birger);

        currentRoom = bar;  // start game in bar
    }
    
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
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
        printLocationInfo();
    }
    
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void smoke()
    {
        System.out.println("You light a cigarette and smoke it.");
        nicotine += 1;
        if(nicotine == 10) {
            System.out.println("You are suffering a severe nicotine addiction.");
        }
    }
    
    private void back()
    {
        int length = roomPath.size() - 1;
        
        if(length >= 0) {
            currentRoom = roomPath.get(length);
            roomPath.remove(length);
            printLocationInfo();
        }
    }
    
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
        
        System.out.println("That item does not exist in this room.");
    }
    
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
        
        System.out.println("Your inventory does not contain this item.");
    }
    
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
                    return;
                }
            }
        }
        
        System.out.println("That person is not in this particular place.");
    }
    
    private void show(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Show what?");
        }
        
        else if(!currentRoom.getChars().isEmpty()) {
            for(Character charInRoom : currentRoom.getChars()) {
                if(charInRoom.getActionItem().getItemName().equals(command.getSecondWord())) {
                    System.out.println(charInRoom.getActionDialogue());
                    return;
                }
            }
        }
        
        System.out.println("Nothing happens.");
    }
    
    private void charge()
    {
        chargedRoom = currentRoom;
        System.out.println("Your Beamer has charged the current room.");
    }
    
    private void fire()
    {
        currentRoom = chargedRoom;
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
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
        else if (commandWord.equals("smoke")) {
            smoke();
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

        return wantToQuit;
    }

    // implementations of user commands:

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
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no available path!");
        }
        else {
            roomPath.add(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
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
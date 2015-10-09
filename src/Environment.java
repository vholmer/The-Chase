/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * This class is responsible of setting up the entire "map" of the game.
 * It creates every single room, event, character and item and is responsible
 * for adding relevant data to these. This means dialogue for characters,
 * keys that unlocks certain rooms, options to events or exits for every room.
 *
 * @author  Viktor Holmer
 * @version 2013.12.19
 */

public class Environment
{	
	/**
	 * Creates an Environment object.
	 */
	public Environment()
	{
		bootWorld();
	}
	
	/**
	 * @return The first room, where the player is at the start of the game.
	 */
	public Room bootWorld()
	{
		//create the events and add options and results to them
    	Event alleyAmbush = new Event("Suddenly a person jumps down from a nearby roof!\nYou have nowhere to run, what do you do?");
    	alleyAmbush.addFirstOption("1. Draw your gun and attempt to shoot the person.");
    	alleyAmbush.addFirstResult("You draw your gun and fire at the person, but your aim fails you.\nThe person shoots back and manages to hit you in the head.");
    	alleyAmbush.addSecondOption("2. Take cover behind a nearby dumpster.");
    	alleyAmbush.addSecondResult("You dash towards the dumpster and manage to take cover before the \nassailant manages to shoot you. However, you accidentaly trip and die.");
    	alleyAmbush.addThirdOption("3. Run straight towards the person and attempt to beat them down.");
    	alleyAmbush.addThirdResult("You run towards the person, and the utter stupidity of running \ntowards an armed enemy shocks the person so much that it works."
    			                   + "\nYou manage to land a few hits at the person, but the assailant \nescapes. He or she did drop something however.");
    	
    	Event fishSale = new Event("You find yourself in a huge fish market. Suddenly a huge guy \nstarts throwing swordfish at you. What do you do?");
    	fishSale.addFirstOption("1. Draw your gun and attempt to shoot the swordfish before it reaches you.");
    	fishSale.addFirstResult("You pull out your pistol and fire away at the swordfish. It doesn't do shit man. \nYou're DEAD.");
    	fishSale.addSecondOption("2. Embrace the swordfish.");
    	fishSale.addSecondResult("You open your arms and prepare to hug the oncoming swordfish."
							     + "\nAs you do this the fish ponders its life. Why would it let"
							     + "\nitself be thrown around like this? It simply wasn't fair. "
							     + "\nThe huge fish had had enough. This was finally the moment"
							     + "\nwhere the fish would say no to its master. Suddenly, and"
							     + "\nfor no apparent reason whatsoever, the fish exploded and"
							     + "\nyou are now covered in swordfish blood. Typical monday."
							     + "\nThe guy who threw the fish is nowhere to be seen.");
    	fishSale.addThirdOption("3. Wave your arms in the air like you just don't care.");
    	fishSale.addThirdResult("You wave your arms in the air like you just do-YOU'RE DEAD.");
    	
    	Event roofTopChase = new Event("You climb up the ladder. Before you lies a dark alley at the end of which you see\n...CLARA!"
    							       + "\nShe speaks: 'It was I back there in the alley. A foolish"
    							       + "\nmove you pulled, detective Case!' She reaches for her holster. What do you do?");
    	roofTopChase.addFirstOption("1. Throw yourself against the floor to avoid any incoming bullets.");
    	roofTopChase.addFirstResult("Just as Clara fires several shots towards you, you throw yourself"
    			                + "\nto the ground. A bullet grazes your ear, and you curse at the"
    			                + "\nbounty hunter as she escapes up a ladder at the end of the alley."
    			                + "\nYou follow her up towards the roof.");
    	roofTopChase.addSecondOption("2. Use your beamer and escape like the chicken you are.");
    	roofTopChase.addSecondResult("You fire your beamer, and end up back in the bar. Unfortunately, there's"
    			                 + "\nsome sort of bar fight going on. Somebody hits you in the head with a"
    			                 + "\nbottle just as you materialize. The very instant the bottle connects"
    			                 + "\nWith your head, you die. Man, you're not very good at this game.");
    	roofTopChase.addThirdOption("3. Throw everything in your inventory at Clara to distract her.");
    	roofTopChase.addThirdResult("You start chucking your entire inventory at Clara, she chucks it all back."
    			                + "\nSomething hits you in the head, and you die. That wasn't such a good move, was it?");
    	
    	Event storeGuess = new Event("As you follow Clara on the rooftops you see her jump from a building into a"
    			                 + "\ncrowd of people, all of which are heading into the same store. You follow"
    			                 + "\nthe crowd and find yourself inside the store. There are tons of people"
    			                 + "\ninside, making it very hard to discern which of them is Clara. She could be"
    			                 + "\nwearing a disguise for all you know. You observe the people for a while and"
    			                 + "\nsee three suspicious individuals. All of which could be her."
    			                 + "\nThe Question is which one?");
    	storeGuess.addFirstOption("1. A bald man wearing a fedora and a suit. This is definitely not Clara.");
    	storeGuess.addFirstResult("How could a man the game claims is innocent NOT be guilty? You pull out your"
    			              + "\ngun and fire several shots at the man, who instantly dies. As you approach"
    			              + "\nthe body, you notice his face is not an actual face. It is a mask! Before you"
    			              + "\nhave any time whatsoever to react, Clara rips of her mask and reveals her"
    			              + "\nidentity. Suddenly you hear a loud bang, and look down to see you've been"
    			              + "\nshot. Clara manages to stumble away, into the back of the store. You fall"
    			              + "\nto the ground.");
    	storeGuess.addSecondOption("2. A dog. Seriously, it's a very suspicious looking dog.");
    	storeGuess.addSecondResult("You pull out your gun and fire away at the dog, instantly killing it. The"
    			               + "\nowner happens to be a captain of a whaling ships. He impales you with his"
    			               + "\nharpoon, because every whaler carries his harpoon everywhere. Duh.");
    	storeGuess.addThirdOption("3. A puss in boots. Yeah, don't ask.");
    	storeGuess.addThirdResult("You have killed the puss in boots. Why would anybody kill him? He's adorable!"
    			              + "\nYou don't deserve to finish this game. Poof! You're dead.");
        
        //create all items and add descriptions and names to them
        Item key = new Item("Someone has dropped a key on the ground.", "key");
        Item keycard = new Item("You see a keycard on the ground. I wonder which door it unlocks?", "keycard");
        Item badge = new Item("Your police badge lies in front of you.", "badge");
        Item gun = new Item("Your police issue gun is lying next to you.", "gun");
        Item paperwork = new Item("A stack of paperwork is stuck in between a few girders. \nIt's all filled in with complicated symbols.", "paperwork");
        
        //set which items will be removed when used to unlock a room
        key.setToRemove();
        keycard.setToRemove();
        paperwork.setToRemove();
      
        //create the rooms
        Room bar, streetnorth, streetcentral, streetsouth, alley, deadend, policehq, jail, market;
        Room residential, apartments, office, construction, rooftop, store, ending;
        
        bar = new Room("You are inside a crowded neon lit pub.");
        streetnorth = new Room("You are on the north end of the city's main street.");
        streetcentral = new Room("You are in the middle of the city. Huge buildings line the street.");
        alley = new Room("You are in a backstreet alley.");
        deadend = new Room("You are at the end of the alley, a dead end.");
        policehq = new Room("You are inside the police headquarters.");
        jail = new Room("You are trapped inside a jail cell! The door behind you has locked itself! \nUse your beamer to escape! (Use the fire command)");
        streetsouth = new Room("You are on the south end of the city's main street.");
        market = new Room("You are in an outdoor fish market.");
        residential = new Room("You are in a residential area. Blocks of apartment buildings \nreach towards the sky."
        		           + "\nA locked folding ladder leads to the rooftops.");
        office = new Room("You are inside an abandoned office building.");
        apartments = new Room("You are in a corridor. A few meters down, it turns to the east.");
        construction = new Room("You are on a construction site.");
        rooftop = new Room("You are on the roof! \nA trail of footsteps lead north.");
        store = new Room("You're shot! You are bleeding heavily from the gut. However, you "
        		     + "\nstill have enough strength to crawl to the back of the store.");
        ending = new Room("You slowly crawl towards the door Clara went through. You can see a very bright light."
        		      + "\nThe light blinds you as you crawl closer in extreme pain, you reach your hand towards"
        		      + "\nit...and wake up. It was all a dream. Haters gonna hate. THE END.");
        
        //add keys to locked rooms
        deadend.addKey(gun);
        policehq.addKey(badge);
        market.addKey(gun);
        office.addKey(paperwork);
        apartments.addKey(key);
        rooftop.addKey(keycard);
        
        //add events to proper rooms
        deadend.addEvent(alleyAmbush);
        market.addEvent(fishSale);
        rooftop.addEvent(roofTopChase);
        store.addEvent(storeGuess);
        
        //makes rooms "unbeamable"
        store.setNoBeam();
        
        //sets a room to the final room
        ending.setTheEnd();
        
        //add text to be shown when locked rooms are unlocked
        deadend.addUnlockString("To pass you show the police officer your weapon.");
        policehq.addUnlockString("Now that you have your badge, you are allowed into the police station.");
        market.addUnlockString("You can now enter. It is much safer to travel with a gun.");
        office.addUnlockString("You give your paperwork to the bureaucrat, and he immediately \nstarts filing and sorting it. You may now pass.");
        apartments.addUnlockString("You unlock the door with the key.");
        rooftop.addUnlockString("The folding ladder unlocks with a click when you use the keycard. \nYou can now easily climb the ladder to the rooftops.");
        
        //create the event cases
    	deadend.setEventCase("1", false);
    	deadend.setEventCase("2", false);
    	deadend.setEventCase("3", true);
    	market.setEventCase("1", false);
    	market.setEventCase("2", true);
    	market.setEventCase("3", false);
    	rooftop.setEventCase("1", true);
    	rooftop.setEventCase("2", false);
    	rooftop.setEventCase("3", false);
    	store.setEventCase("1", true);
    	store.setEventCase("2", false);
    	store.setEventCase("3", false);

        //initialize room exits and locked exits
        bar.setExit("east", streetnorth);
        streetnorth.setExit("south", streetcentral);
        streetnorth.setExit("west", bar);
        streetcentral.setExit("north", streetnorth);
        streetcentral.setLockedExit("east", policehq);
        streetcentral.setExit("south", streetsouth);
        streetcentral.setExit("west", alley);
        alley.setExit("east", streetcentral);
        alley.setLockedExit("west", deadend);
        deadend.setExit("east", alley);
        policehq.setExit("west", streetcentral);
        policehq.setExit("east", jail);
        streetsouth.setExit("north", streetcentral);
        streetsouth.setLockedExit("east", market);
        market.setExit("east", residential);
        market.setExit("west", streetsouth);
        residential.setLockedExit("north", office);
        residential.setLockedExit("up", rooftop);
        residential.setLockedExit("south", apartments);
        residential.setExit("west", market);
        office.setExit("south", residential);
        apartments.setExit("north", residential);
        apartments.setExit("east", construction);
        construction.setExit("west", apartments);
        rooftop.setExit("north", store);
        rooftop.setExit("down", residential);
        store.setExit("east", ending);
   
        //create the characters and add dialogue, reaction dialogue and reaction items to them 
        Character birger = new Character("An odd hooded fellow named Birger is standing in the corner.", "birger");
        birger.addDialogue("'Don't talk to me, I don't like strangers.'");
        birger.addActionDialogue("Birger gets scared and starts talking: \n'Okay man, just chill out! No need to arrest me. \nMy guess is you're lookin' for some info huh?"
        + "\nYou hear about this bounty hunter, Clara? \nRumor has it she's killed several ranking policemen. \nMaybe some of your friends?'");
        birger.addActionItem(badge);
        
        Character stranger = new Character("A mysterious stranger lurks in the shadows of the bar.", "stranger");
        stranger.addDialogue("'I hear the market isn't safe for the feds nowadays. \nDon't go there unarmed.'");
        stranger.addSecondDialogue("'Get your filthy paws off me, you damn dirty ape!'");
        
        Character officer = new Character("An officer is standing right next to a crime scene of some sort.", "officer");
        officer.addDialogue("'Sorry, Case. You'll need your gun to get inside, there's been \na sighting of the bounty hunter Clara here.'");
        officer.addActionDialogue("'Oh, I see you've got your firearm right there! You may pass.'");
        officer.addActionItem(gun);
        officer.setImportant();
        
        Character bureaucrat = new Character("A bureaucrat is standing right outside the entrance to the office tower.", "bureaucrat");
        bureaucrat.addActionItem(paperwork);
        bureaucrat.addActionDialogue("'YOU MUST GIVE ME THIS! PLEASE!!11!1one!!1!eleven'");
        bureaucrat.addDialogue("'Hmpf. I have no time for a mere detective. Shoo!'");
        bureaucrat.setImportant();
        
        Character chief = new Character("The chief of police is talking to some recruits.", "chief");
        chief.addDialogue("'Hello, Case! I've been meaning to talk to you about this\n"
        + "upcoming assignment. There's all kinds of rumors about\n"
        + "Clara, the bounty hunter. I need you to go find her and\n"
        + "terminate her. Here's your gun!'");
        chief.addSecondDialogue("'Get to it, Case! I need you to find and kill Clara!'");
        chief.addItemToGive(gun);
        chief.setImportant();
        
        Character leif = new Character("A funny looking hobo named Leif is standing outside a noodle joint.", "leif");
        leif.addDialogue("'Have you seen my friend birger? That guy is really scared of cops. Try showing him your badge!'");
        leif.addSecondDialogue("'Hey man you wanna *hick* spare a coin? I could really go for a *hick* beer.'");
        
        //places the items and characters in the proper rooms.
        bar.addItem(badge);
        bar.addCharacter(birger);
        bar.addCharacter(stranger);
        office.addItem(keycard);
        alley.addCharacter(officer);
        deadend.addItem(key);
        policehq.addCharacter(chief);
        streetsouth.addCharacter(leif);
        construction.addItem(paperwork);
        residential.addCharacter(bureaucrat);
        
        //the starting room of the player
        return bar;
	}
}

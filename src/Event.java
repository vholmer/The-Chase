/**
 * This class is part of the "The Chase" application. 
 * "The Chase" is an extremely complicated, text based adventure game.  
 * 
 * This class defines the different events throughout the game where
 * the player may choose one of three options, one of which will
 * assert the survival of the player.
 *
 * @author  Viktor Holmér
 * @version 2013.12.19
 */

public class Event {
	private String startText;
	private String firstOption; 
	private String firstResult;
	private String secondOption;
	private String secondResult;
	private String thirdOption;
	private String thirdResult;
	
	/**
	 * Creates an Event type object with a description of
	 * what's happening to the player. The description
	 * may look like this:
	 * 
	 * "A bandit appears! What do you do?"
	 * 
	 * @param startText Describes what's happening at the
	 * very start of the event.
	 */
	public Event(String startText) {
		this.startText = startText;
	}
	
	/**
	 * Returns the entire event text. Presents the player
	 * with the description of what's happening as well
	 * as all three options.
	 * 
	 * @return A description of what's happening as well
	 * as all three available options.
	 */
	public String getStart() {
		return startText + firstOption + secondOption + thirdOption;
	}
	
	/**
	 * Adds the first option to the event.
	 * 
	 * @param firstOption The text that presents the
	 * very first option.
	 */
	public void addFirstOption(String firstOption) {
		this.firstOption = "\n" + firstOption;
	}
	
	/**
	 * Adds a result of picking the first option.
	 * 
	 * @param firstResult Description of what happens
	 * when the first option is picked.
	 */
	public void addFirstResult(String firstResult) {
		this.firstResult = "\n" + firstResult;
	}
	
	/**
	 * Adds the second option to the event.
	 * 
	 * @param secondOption The text that presents the
	 * second option.
	 */
	public void addSecondOption(String secondOption) {
		this.secondOption = "\n" + secondOption;
	}
	
	/**
	 * Adds a result of picking the second option.
	 * 
	 * @param secondResult Description of what happens
	 * when the second option is picked.
	 */
	public void addSecondResult(String secondResult) {
		this.secondResult = "\n" + secondResult;
	}
	
	/**
	 * Adds the third option to the event.
	 * 
	 * @param thirdOption The text that presents the
	 * third option.
	 */
	public void addThirdOption(String thirdOption) {
		this.thirdOption = "\n" + thirdOption;
	}
	
	/**
	 * Adds a result of picking the third option.
	 * 
	 * @param thirdResult Description of what happens
	 * when the third option is picked.
	 */
	public void addThirdResult(String thirdResult) {
		this.thirdResult = "\n" + thirdResult;
	}
	
	/**
	 * @return The result of picking the first option.
	 */
	public String getFirstResult() {
		return firstResult;
	}
	
	/**
	 * @return The result of picking the second option.
	 */
	public String getSecondResult() {
		return secondResult;
	}
	
	/**
	 * @return The result of picking the third option.
	 */
	public String getThirdResult() {
		return thirdResult;
	}
}
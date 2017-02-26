package deadwood;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int location, n;
	private int propertiesOwned;
	private String[] propertyNames = new String[36];
	private String playerName;
	private static int counter = 0;
	public final int id;

	public Players() {
		location = 0;
		this.id = counter;
		counter++;
		playerX = 480;
		playerY = 480;
		balance = 1500;
		playerName = "Player";
		propertiesOwned = 0;
		n = 0;
	}

	public void setName(String name) {
		playerName = name;
	}

	public String getName() {
		return playerName;
	}

	public void addBalance(int amount) {
		balance += amount;
	}

	public void deductBalance(int amount) {
		balance -= amount;
	}

	public int getBalance() {
		return balance;
	}

	public int getX() {
		return playerX;
	}

	public void changeX(int pixels) { // changes position on the board
		playerX += pixels;
	}

	public int getY() {
		return playerY;
	}

	public void changeY(int pixels) { // changes position on the board
		playerY += pixels;
	}

	public void move() { //moves player according to roll()
		int k = TurnControl.roll(this); // calls roll and passes current player through
		n = n + k;
		location = n % 40;
		Board.moveTokens(id, k); // moves current player k spaces
	}

	public void move(int k) { //moves player manually n spaces
		n = n + k;
		location = n % 40;
		Board.moveTokens(id, k);
	}

	public int getLocation(){ //returns players location
		return location;
	}

	public void propertyBought(String propName) {
		propertyNames[propertiesOwned] = propName;
		propertiesOwned++;
	}

	public String getPropertiesOwned() {
		String output = "";
		output = playerName + " owns " + propertiesOwned + " properties: " + toStringArray(propertyNames);
		return output;
	}
	
	public String toStringArray(String[] array) { // prints the propertyNames array without nullspaces
		StringBuilder builder = new StringBuilder();
		int n = array.length;
	    for(int i=0; i<n; i++) {
	        if(array[i] != null) {
	            builder.append(array[i].toString());
	            break;
	        }
	    }
	    for(int j=1; j<n; j++) {
	        if(array[j] != null) {
	            builder.append(", ");
	            builder.append(array[j].toString());
	        }
	    }
		
		return builder.toString();
	}

}

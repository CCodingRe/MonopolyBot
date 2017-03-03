package deadwood;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int assets;
	private int location, n;
	private int propertiesOwned;
	private String[] propertyNames = new String[36];
	private String playerName;
	public int id;
	private int firstRoll;

	public Players() {
		location = 0;
		playerX = 480;
		playerY = 480;
		balance = 1500;
		assets = 0;
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
		Board.moveTokens(id, k); // moves current player k spaces
	}

	public void move(int k) { //moves player manually k spaces
		Board.moveTokens(id, k);
	}

	public void changeLocation() {
		n++;
		location = n % 40;
	}

	public int getLocation(){ //returns players location
		return location;
	}

	public void propertyBought(Propertys prop) {
		propertyNames[propertiesOwned] = prop.getName();
		propertiesOwned++;
		assets += prop.getValue();
	}

	public String getPropertiesOwned() {
		String output = "";
		output = playerName + " owns " + propertiesOwned + " properties: " + toStringArray(propertyNames);
		return output;
	}

	public int calculateAssets() {
		return assets + balance;
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

	public int getFirstRoll(){
		return firstRoll;
	}

	public void setFirstRoll(int total){
		firstRoll = total;
	}

	public void setId(int index){
		id = index;
	}

}

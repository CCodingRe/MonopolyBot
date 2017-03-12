package deadwood;

import java.util.LinkedList;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int assets;
	private int location, n;
	private int propertiesOwned;
	private LinkedList<Propertys> ownedProperties = new LinkedList<Propertys>();
	private String playerName;
	public int id;
	private int firstRoll;
	private static final String[] COLOUR = {"red", "blue", "yellow", "green", "magenta", "white"};

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
		return playerName + " (" + COLOUR[id] + ")";
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
		Board.moveTokens(this, k); // moves current player k spaces
	}

	public void move(int k) { //moves player manually k spaces
		Board.moveTokens(this, k);
	}

	public void changeLocation() {
		n++;
		location = n % 40;
	}

	public int getLocation(){ //returns players location
		return location;
	}

	public void propertyBought(Propertys prop) {
		ownedProperties.add(prop);
		propertiesOwned++;
		assets += prop.getValue();
	}

	public String getPropertiesOwned() {
		String output = "";
		output = playerName + " owns " + propertiesOwned + " properties: " + toStringList(ownedProperties);
		return output;
	}

	public int calculateAssets() {
		return assets + balance;
	}

	public String toStringList(LinkedList<Propertys> propertyNames) { // puts the owned properties in a string
		StringBuilder builder = new StringBuilder();
		
		for (Propertys prop : propertyNames) {
			builder.append(prop.getName());
			if(prop.isMortgaged()) {
				builder.append("(mortgaged)");
			}
			builder.append(", ");
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
	
	public int getId() {
		return id;
	}

}

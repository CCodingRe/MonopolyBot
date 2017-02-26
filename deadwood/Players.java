package deadwood;

import java.util.concurrent.ThreadLocalRandom;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int location, n;
	private int propertiesOwned;
<<<<<<< HEAD
	private int location;
	private int n;
	private final int id;
	private static int counter = 0;
=======
>>>>>>> refs/remotes/origin/master
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

	public void buy(int amount) {
		balance -= amount;
	}

	public int getBalance() {
		return balance;
	}

	public int getX() {
		return playerX;
	}

	public void changeX(int pixels) {
		playerX += pixels;
	}

	public int getY() {
		return playerY;
	}

	public void changeY(int pixels) {
		playerY += pixels;
	}
<<<<<<< HEAD
	
	public void move() { //moves player according to roll()
=======

	public void move(){ //moves player according to roll()
>>>>>>> refs/remotes/origin/master
		int k = roll();
		n = n + k;
		location = n % 40;
		Board.moveTokens(id, k);
	}
<<<<<<< HEAD
	
	public static int roll() { //returns dice roll
	    int randomNum = ThreadLocalRandom.current().nextInt(2, 12 + 1);
	    return randomNum;
	  }
	
	public int getLocation(){ //returns players location
		return location;
}
	
=======
	public void move(int k){ //moves player manually n spaces
		n = n + k;
		location = n % 40;
		Board.moveTokens(id, k);
	}
	public int getLocation(){ //returns players location
			return location;
	}

	public static int roll(){ //returns dice roll
	    int randomNum = ThreadLocalRandom.current().nextInt(2, 12 + 1);
	    return randomNum;
	  }

>>>>>>> refs/remotes/origin/master
	public void propertyBought(String propName) {
		propertyNames[propertiesOwned] = propName;
		propertiesOwned++;
	}

	public String getPropertiesOwned() {
		String output = "";
		for(int i=0; i<36; i++){
			if(this == ((Propertys) SetUp.getLocationsList().get(i)).getOwner()) {
				propertyNames[i] = SetUp.getLocationsList().get(i).getName();
			}
		}
		output = playerName + " owns " + propertiesOwned + " properties: " + propertyNames;
		return output;
	}

}

package deadwood;

import deadwood.*;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int propertiesOwned;
	private String[] propertyNames = new String[36];
	private String playerName;
	

	public Players() {
		playerX = 480;
		playerY = 480;
		balance = 1500;
		playerName = "Player";
		propertiesOwned = 0;
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
	
	public void removeBalance(int amount) {
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
	
	public void changeY(int pixels) {
		playerY += pixels;
	}
	
	public int getY() {
		return playerY;
	}
	
	public void propertyBought() {
		propertiesOwned++;
	}
	
	public String getPropertiesOwned() {
		String output = "";
		output = playerName + " owns " + propertiesOwned + " properties";
		return output;
	}
	
}
package deadwood;

import java.util.LinkedList;

public class Players {
	private int playerX;
	private int playerY;
	private int balance;
	private int assets;
	private int location, n;
	private int getOutOfJailCard;
	private LinkedList<Propertys> ownedProperties = new LinkedList<Propertys>();
	private LinkedList<Services> ownedServices = new LinkedList<Services>();
	private String playerName;
	public int id;
	private int firstRoll;
	private int turn;
	private boolean jail = false;
	private static final String[] COLOUR = {"red", "blue", "yellow", "green", "magenta", "white"};

	public Players() {
		location = 0;
		playerX = 635;
		playerY = 635;
		balance = 1500;
		assets = 0;
		playerName = "Player";
		n = 0;
		firstRoll = 0;
		getOutOfJailCard = 0;
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

	public void setX(int x) {
		playerX = x;
	}

	public int getX() {
		return playerX;
	}

	public void changeX(int pixels) { // changes position on the board
		playerX += pixels;
	}

	public void setY(int y) {
		playerY = y;
	}

	public int getY() {
		return playerY;
	}

	public void changeY(int pixels) { // changes position on the board
		playerY += pixels;
	}

	public void move() { //moves player according to roll()
		int k = TurnControl.roll(this); // calls roll and passes current player through
		if(!jail){ //player wont move if in jail
			Board.moveTokens(this, k, 1); // moves current player k spaces
		}

		//sends player to jail if lands on go to jail
		if(location == 30){
			goToJail();
		} else if(location == 10 && jail == true){ //players spends 3 turns in jail
			turn++;
	}
	}

	public void move(int k) { //moves player manually k spaces
		Board.moveTokens(this, k, 1);
		if(!jail){ //player wont move if in jail
			Board.moveTokens(this, k, 1); // moves current player k spaces
		}

		//sends player to jail if lands on go to jail
		if(location == 30){
			goToJail();
		} else if(location == 10 && jail == true){ //players spends 3 turns in jail
			turn++;
		}
	}

	public void changeLocation(int direction) {
		n += direction;
		location = n % 40;
	}

	public void setLocation(int loc) {
		location = loc;
		n=loc;
	}

	public int getLocation(){ //returns players location
		return location;
	}

	public void propertyBought(Propertys prop) {
		ownedProperties.add(prop);
		assets += prop.getValue();
	}

	public void servicesBought(Services service) {
		ownedServices.add(service);
		assets += service.getValue();
	}

	public String getPropertiesOwned() {
		String output = "";
		output = playerName + " owns " + (ownedProperties.size()+ownedServices.size()) + " properties: " + toStringListProperties(ownedProperties) +  toStringListServices(ownedServices);
		return output;
	}

	public int calculateAssets() {
		return assets + balance;
	}

	public String toStringListProperties(LinkedList<Propertys> propertyNames) { // puts the owned properties in a string
		StringBuilder builder = new StringBuilder();

			for (Propertys prop : propertyNames) {
			builder.append(prop.getName());
			builder.append("[" + prop.getGroup() + "]");
			if(prop.isMortgaged()) {
				builder.append("(mortgaged)");
			}
			builder.append(", ");
		}
		return builder.toString();
	}

	public String toStringListServices(LinkedList<Services> serviceNames) { // puts the owned properties in a string
		StringBuilder builder1 = new StringBuilder();

			for (Services service : serviceNames) {
			builder1.append(service.getName());
			builder1.append("[" + service.getGroup() + "]");
			if(service.isMortgaged()) {
				builder1.append("(mortgaged)");
			}
			builder1.append(", ");
		}
		return builder1.toString();
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
	public int getTurn(){
		return turn;
	}

	public int getNumStationsOwned(){
		int numRailroad = 0;
		for (int t=0;t<ownedServices.size();t++){
			if(ownedServices.get(t).getGroup().equals("railroad")){
				 numRailroad++;
			}
		}
		return numRailroad;
	}

	public void addJailCard() {
		getOutOfJailCard++;
	}
	public void useJailCard() {
		getOutOfJailCard--;
	}
	public int getJailCard() {
		return getOutOfJailCard;
	}
	public void goToJail(){
		jail = true;
		turn = 0;
		setLocation(10);
		setX(45);
		setY(635);
		Board.refresh();
		TurnControl.endTurn();
  }
	public boolean getJail(){
		return jail;
	}
	public void leaveJail(){
		jail = false;
	}

}

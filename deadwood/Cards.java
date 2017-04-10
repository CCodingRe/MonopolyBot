package deadwood;

import java.util.concurrent.ThreadLocalRandom;

public class Cards {
	static boolean rentOwed;

	public static void communityChest(int card, Players player) {
		switch(card) {
		case 0 :
			advanceToGo(player);
			break;

		case 1 :
			backToMedAve(player);
			break;

		case 2 :
			Info_Panel.UserInput("Card: Go to jail. Move directly to jail. Do not pass Go. Do not collect $200");
			player.goToJail();
			break;

		case 3 :
			Info_Panel.UserInput("Card: Pay hospital $100");
			payFee(100, player);
			break;

		case 4 :
			Info_Panel.UserInput("Card: Doctor's fee. Pay $50");
			payFee(50, player);
			break;

		case 5 :
			Info_Panel.UserInput("Card: Pay your insurance premium $50");
			payFee(50, player);
			break;

		case 6 :
			Info_Panel.UserInput("Card: Bank error in your favour. Collect $200");
			recieveFee(200, player);
			break;

		case 7 :
			Info_Panel.UserInput("Card: Annuity matures. Collect $100");
			recieveFee(100, player);
			break;

		case 8 :
			Info_Panel.UserInput("Card: You inherit $100");
			recieveFee(100, player);
			break;

		case 9 :
			Info_Panel.UserInput("Card: From sale of stock you get $50");
			recieveFee(50, player);
			break;

		case 10 :
			Info_Panel.UserInput("Card: Receive interest on 7% preference shares: $25");
			recieveFee(25, player);
			break;

		case 11 :
			Info_Panel.UserInput("Card: Income tax refund. Collect $20");
			recieveFee(20, player);
			break;

		case 12 :
			Info_Panel.UserInput("Card: You have won second prize in a beauty contest. Collect $10");
			recieveFee(10, player);
			break;

		case 13 :
			birthdayMoney(player);
			break;

		case 14 :
			Info_Panel.UserInput("Get out of jail free. This card may be kept until needed or sold");
			player.addJailCard();
			break;

		case 15 :
			payOrChance(player);
			break;

		default :
			Info_Panel.UserInput("Error: Invalid card number");
		}
	}

	public static void chance(int card, Players player) {
		switch(card) {
		case 0 :
			advanceToGo(player);
			break;

		case 1 :
			Info_Panel.UserInput("Card: Go to jail. Move directly to jail. Do not pass Go. Do not collect $200");
			player.goToJail();
			break;

		case 2 :
			Info_Panel.UserInput("Card: Advance to St Charles Place. If you pass Go collect $200");
			advanceToLocation(11, 45, 560, player);
			break;

		case 3 :
			Info_Panel.UserInput("Card: Take a trip to Pennsylvania Railroad and if you pass Go collect $200.");
			advanceToLocation(15, 45, 340, player);
			break;

		case 4 :
			Info_Panel.UserInput("Card: Advance to Illinois Avenue. If you pass Go collect $200");
			advanceToLocation(24, 285, 45, player);
			break;

		case 5 :
			Info_Panel.UserInput("Card: Advance to Boardwalk");
			advanceToLocation(39, 635, 560, player);
			break;

		case 6 :
			Info_Panel.UserInput("Card: Go back three spaces");
			backSpaces(player, 3);
			break;

		case 7 :
			Info_Panel.UserInput("Card: Make general repairs on all of your houses. For each house pay $25. For each hotel pay $100");
			doRepairs(25, 100, player);
			break;

		case 8 :
			Info_Panel.UserInput("Card: You are assessed for street repairs: $40 per house, $115 per hotel");
			doRepairs(40, 115, player);
			break;

		case 9 :
			Info_Panel.UserInput("Card: Pay school fees of $150");
			payFee(150, player);
			break;

		case 10 :
			Info_Panel.UserInput("Card: Drunk in charge fine $20");
			payFee(20, player);
			break;

		case 11 :
			Info_Panel.UserInput("Card: speeding fine $15");
			payFee(15, player);
			break;

		case 12 :
			Info_Panel.UserInput("Card: Your building loan matures. Receive $150");
			recieveFee(150, player);
			break;

		case 13 :
			Info_Panel.UserInput("You have won a crossword competition. Collect $100");
			recieveFee(100, player);
			break;

		case 14 :
			Info_Panel.UserInput("Card: Bank pays you dividend of $50");
			recieveFee(50, player);
			break;

		case 15 :
			Info_Panel.UserInput("Get out of jail free. This card may be kept until needed or sold");
			player.addJailCard();
			break;

		default :
			Info_Panel.UserInput("Error: Invalid card number");
		}
	}


	private static void advanceToGo(Players player) {
		Info_Panel.UserInput("Card: Advance to Go");
		player.setLocation(0);
		player.setX(635);
		player.setY(635);
		Board.refresh();
		player.addBalance(200);
		Info_Panel.UserInput(player.getName() + " recieved $200 for passing Go");
		TurnControl.squareInfo(player);
	}

	private static void backToMedAve(Players player) {
		Info_Panel.UserInput("Card: Go back to Mediterranean Avenue");
		player.setLocation(1);
		player.setX(540);
		player.setY(635);
		Board.refresh();
		TurnControl.squareInfo(player);
		rentOwed = TurnControl.rentOwed(player);
		if(rentOwed) TurnControl.payRent(player);
	}

	private static void payFee(int fee, Players player) {
		player.deductBalance(fee);
		Info_Panel.UserInput(player.getName() + " paid $" + fee);
	}

	private static void recieveFee(int fee, Players player) {
		player.addBalance(fee);
		Info_Panel.UserInput(player.getName() + " recieved $" + fee);
	}

	private static void birthdayMoney(Players player) {
		Info_Panel.UserInput("Card: It is your birthday. Collect $10 from each player");
		for(Players otherPlayer: SetUp.getPlayers()) {
			if(otherPlayer!=player) {
				otherPlayer.deductBalance(10);
				player.addBalance(10);
				Info_Panel.UserInput(otherPlayer.getName() + " paid $10 to " + player.getName());
			}
		}
	}

	private static void payOrChance(Players player) {
		Info_Panel.UserInput("Card: Pay a $10 fine or take a Chance");
		Info_Panel.UserInput("Type 'pay' to pay the fine or 'chance' to take a Chance card");
		String command = Cmd_panel.getCommand();
		if(command.equalsIgnoreCase("pay")) {
			player.deductBalance(10);
			Info_Panel.UserInput(player.getName() + " paid $10");
		}
		else if(command.equalsIgnoreCase("chance")) {
			int cardNum = ThreadLocalRandom.current().nextInt(0, 16);
			Info_Panel.UserInput(player.getName() + " takes a Chance card");
			chance(cardNum, player);
		}
		else{
			Info_Panel.UserInput("Error: Invalid command");
			payOrChance(player);
		}
	}

	private static void advanceToLocation(int locNum, int x, int y, Players player) {
		if(player.getLocation()>locNum) {
			player.addBalance(200);
			Info_Panel.UserInput(player.getName() + " recieved $200 for passing Go");
		}
		player.setLocation(locNum);
		player.setX(x);
		player.setY(y);
		Board.refresh();
		TurnControl.squareInfo(player);
		rentOwed = TurnControl.rentOwed(player);
		if(rentOwed) TurnControl.payRent(player);
	}

	private static void doRepairs(int house, int hotel, Players player) {
		int total = 0;
		for(Locations prop : SetUp.getLocationsList()) {
			if(prop instanceof Propertys){
				if(((Propertys) prop).getOwner()==player) {
					if(((Propertys) prop).getUnits()<5){
						total += ((Propertys) prop).getUnits()*house;
					}
					else total += hotel;
				}
			}
		}
		player.deductBalance(total);
		Info_Panel.UserInput(player.getName() + " paid $" + total + " in repairs");
	}

	private static void backSpaces(Players player, int spaces) {
		Board.moveTokens(player, spaces, -1);
		TurnControl.squareInfo(player);
	}
}

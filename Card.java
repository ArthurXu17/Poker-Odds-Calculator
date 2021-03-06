import java.util.*;
public class Card {
	//variables
	public int rank, suit;
	
	public Card(int a, int b) {
		if(a == 1) {
			rank = 14;
		}
		else {
			rank = a;
		}
		suit = b;
	}
	
	public Card(char a, char b) {
		if(b == 'C') {
			suit = 0;
		}
		else if (b == 'D') {
			suit = 1;
		}
		else if(b == 'H') {
			suit = 2;
		}
		else if(b == 'S') {
			suit = 3;
		}
		
		if (a == 'T') {
			rank = 10;
		}
		else if(a == 'J') {
			rank = 11;
		}
		else if (a == 'Q') {
			rank = 12;
		}
		else if(a == 'K') {
			rank = 13;
		}
		else if(a == 'A') {
			rank = 14;
		}
		else {
			rank = ((int) a) - 48;
		}
	}
	
	public Card (int a) {//i'm gonna assume a is from 1 to 52
		
		if(a%13 == 0) {
			rank = 13;
			suit = (a-1)/13;
		}
		else if(a%13 == 1) {
			rank = 14;
			suit = a/13;
		}
		else {
			suit = a/13;
			rank = a%13;
		}
	}
	
	public void print() {
		if (suit == 0) {
			if(rank == 11) {
				System.out.print("JC ");
			}
			else if(rank == 12) {
				System.out.print("QC ");
			}
			else if(rank == 13) {
				System.out.print("KC ");
			}
			else if(rank == 14) {
				System.out.print("AC ");
			}
			else {
				System.out.print(rank + "C ");
			}
			
		}
		else if(suit == 1) {
			if(rank == 11) {
				System.out.print("JD ");
			}
			else if(rank == 12) {
				System.out.print("QD ");
			}
			else if(rank == 13) {
				System.out.print("KD ");
			}
			else if(rank == 14) {
				System.out.print("AD ");
			}
			else {
				System.out.print(rank + "D ");
			}
		}
		else if (suit == 2) {
			if(rank == 11) {
				System.out.print("JH ");
			}
			else if(rank == 12) {
				System.out.print("QH ");
			}
			else if(rank == 13) {
				System.out.print("KH ");
			}
			else if(rank == 14) {
				System.out.print("AH ");
			}
			else {
				System.out.print(rank + "H ");
			}
		}
		else if (suit == 3) {
			if(rank == 11) {
				System.out.print("JS ");
			}
			else if(rank == 12) {
				System.out.print("QS ");
			}
			else if(rank == 13) {
				System.out.print("KS ");
			}
			else if(rank == 14) {
				System.out.print("AS ");
			}
			else {
				System.out.print(rank + "S ");
			}
		}
	}

	public boolean equals(Card input) {
		if(rank == input.rank && suit == input.suit) {
			return true;
		}
		else {
			return false;
		}
	}
}

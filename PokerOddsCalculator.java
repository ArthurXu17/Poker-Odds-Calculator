import java.util.*;
public class PokerOddsCalculator {
	//************************************************troublehsooting methods********************************************************
	public static void print(int[] arr) {
		for(int i = 0; i < arr.length;i++) {
			if(arr[i] >= 10) {
				System.out.print(arr[i] + " ");
			}
			else {
				System.out.print(arr[i] + "  ");
			}
			
		}
		System.out.println();
	}
	public static void print2(int[] arr) {
		for(int i = 0; i < arr.length;i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
	public static void printType(int a) {
		if(a == 1) {
			System.out.println("High Card");
		}
		else if(a == 2) {
			System.out.println("Pair");
		}
		else if(a == 3) {
			System.out.println("2 Pair");
		}
		else if(a == 4) {
			System.out.println("Triple");
		}
		else if(a == 5) {
			System.out.println("Straight");
		}
		else if(a == 6) {
			System.out.println("Flush");
		}
		else if(a == 7) {
			System.out.println("Full House");
		}
		else if(a == 8) {
			System.out.println("Quad");
		}
		else if(a == 9) {
			System.out.println("Straight Flush");
		}
	}
	//*********************************************methods to determine winner*****************************************************
	public static int win2(Hand hand1, Hand hand2) {
		/*
		 * determines winner of 2 hands
		 * returns 1 or 2 for either winner
		 * returns 0 if tie
		 */
		if(hand1.determineType()> hand2.determineType()) {
			return 1;
		}
		else if(hand2.determineType()> hand1.determineType()) {
			return 2;
		}
		else {
			int[] kicker1 = hand1.determineKicker();
			int[] kicker2 = hand2.determineKicker();
			int counter = kicker1.length-1;
			
			while(kicker1[counter] == kicker2[counter]) {
				counter--;
				if(counter == -1) {
					break;
				}
			}
			if(counter == -1) {//gone throuh every single item in the kicker array and they are all the same
				return 0;
			}
			else if(kicker1[counter] > kicker2[counter]) {
				return 1;
			}
			else {
				return 2;
			}
		}
		
	}
	
	public static int[] win(Hand[] input) {
		/*
		 * returns the array index of winner (0 to n-1)
		 	if output.length = 1, there is only one winner
		 	otherwise there is tie
		 	
		 	method compares adjacent elements and keeps track of 
		 */
		int[] output = new int[1];
		output[0] = 0;
		for(int i = 0; i < input.length-1;i++) {
			if(win2(input[i],input[i+1]) == 1) {
				input[i+1] = input[i];
			}
			else if (win2(input[i],input[i+1]) == 2){
				output = new int[1];
				output[0] = i+1;
			}
			else {
				output = addElement(output,i+1);
			}
		}
		return output;
	}
	
	public static int[] wintest(int[] input) {
		int[] output = new int[1];
		output[0] = 0;
		for(int i = 0; i < input.length-1;i++) {
			if(input[i]>input[i+1]) {
				input[i+1] = input[i];
			}
			else if (input[i]<input[i+1]){
				output = new int[1];
				output[0] = i+1;
			}
			else {
				output = addElement(output,i+1);
			}
		}
		return output;
	}
	
	public static int[] addElement(int[] arr, int n) {
		int[] output = new int [arr.length+1];
		for(int i = 0; i < arr.length; i++) {
			output[i] = arr[i];
		}
		output[arr.length] = n;
		return output;
	}
	
	
	//************************************************************methods to determine all combinations of deck**********************************************************
	
	// Function to swap the data
    // present in the left and right indices
    public static int[] swap(int data[], int left, int right)
    {
  
        // Swap the data
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
  
        // Return the updated array
        return data;
    }
  
    // Function to reverse the sub-array
    // starting from left to the right
    // both inclusive
    public static int[] reverse(int data[], int left, int right)
    {
  
        // Reverse the sub-array
        while (left < right) {
            int temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }
  
        // Return the updated array
        return data;
    }
  
    // Function to find the next permutation
    // of the given integer array
    public static boolean findNextPermutation(int data[])
    {
  
        // If the given dataset is empty
        // or contains only one element
        // next_permutation is not possible
        if (data.length <= 1)
            return false;
  
        int last = data.length - 2;
  
        // find the longest non-increasing suffix
        // and find the pivot
        while (last >= 0) {
            if (data[last] < data[last + 1]) {
                break;
            }
            last--;
        }
  
        // If there is no increasing pair
        // there is no higher order permutation
        if (last < 0)
            return false;
  
        int nextGreater = data.length - 1;
  
        // Find the rightmost successor to the pivot
        for (int i = data.length - 1; i > last; i--) {
            if (data[i] > data[last]) {
                nextGreater = i;
                break;
            }
        }
  
        // Swap the successor and the pivot
        data = swap(data, nextGreater, last);
  
        // Reverse the suffix
        data = reverse(data, last + 1, data.length - 1);
  
        // Return true as the next_permutation is done
        return true;
    }
    
    public static int fact(int n) {
    	int ans = 1;
    	for(int i = 1; i <= n; i++) {
    		ans = ans*i;
    	}
    	return ans;
    }
    
    public static int C(int n, int r) {
    	int numerator = 1;
    	for(int i = 0; i <= r-1;i++) {
    		numerator = numerator*(n-i);
    	}
    	return numerator/fact(r);
    }
    
    public static Card[][] generateCardCombinations (Card[] arr, int k) {
    	int n = arr.length;
    	int[] permutations = new int[n];
    	for(int i = 0; i < k; i++) {
    		permutations[i] = 0;
    	}
    	for(int i = k; i <n;i++) {
    		permutations[i] = 1;
    	}

    	Card[][] result = new Card[C(n,k)][k];
    	int rowcounter = 0;
    	do {
    		int columncounter = 0;
    		for(int i = 0; i < n ;i++) {
    			if(permutations[i] == 0) {
    				result[rowcounter][columncounter] = arr[i];
    				columncounter++;
    			}
    		}
    		
    		rowcounter++;
    	}while(findNextPermutation(permutations));
    	return result;
    }
    
    //************************************8methods to help remove cards from the deck************************************************
    
    public static boolean containsElement(Card[] arr, Card n) {
    	for(int i = 0; i < arr.length;i++) {
    		if(arr[i].equals(n)) {
    			return true;
    		}
    	}
    	return false;
    }
    public static Card[] removeCard(Card[] arr, int index) {
		Card[] output = new Card[arr.length-1];
		for(int i = 0; i < index; i++) {
			output[i] = arr[i];
		}
		for(int i = index+1; i<arr.length;i++ ) {
			output[i-1] = arr[i]; 
		}
		return output;
    }
    public static Card[] removeElements(Card [] arr, Card[] remove) {
    	//this method assumes that remove is a subset of arr
    	if(remove.length==0) {
    		return arr;
    	}
    	else {
    		Card[] output = new Card[arr.length-remove.length];
        	int removedcounter = 0;
        	for(int i = 0; i < arr.length; i++) {
        		if(!containsElement(remove,arr[i])) {
        			output[i-removedcounter] = new Card(arr[i].rank,arr[i].suit);
        		}
        		else {
        			removedcounter++;
        		}
        	}
        	return output;
    	}

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//**************************************************************Code that determines probability of winning for n number of players*******************************************8
		
		//start by making deck
		Card[] deck = new Card[52];
		for(int j = 0; j < 52; j++) {
			deck[j] = new Card(j+1);
		}
		
		//make the array that stores the 2 cards that each player gets
		int playernum = 4;
		Card[][] playerCardArrays = new Card[playernum][2];//an array that contains the 2 cards that each player has
		for(int i = 0; i < playernum; i++) {
			int index0 = (int)(Math.random()*deck.length);//pick random index from deck
			playerCardArrays[i][0] = deck[index0];//take the card at that index and put in 2d array
			deck = removeCard(deck,index0);//remove that card from the deck array
			int index1 = (int)(Math.random()*deck.length);
			playerCardArrays[i][1] = deck[index1];
			deck = removeCard(deck,index1);
			
			System.out.println("Player " + i);
			playerCardArrays[i][0].print();
			playerCardArrays[i][1].print();
			System.out.println();
		}
		//make the board
		System.out.println("Board");
		int boardsize = (int) (Math.random()*3);
		if(boardsize == 1) {
			boardsize = 3;
		}
		else if(boardsize == 2) {
			boardsize = 4;
		}
		else {
			System.out.println("Empty");
		}
		
		Card[] board = new Card[boardsize];
		for(int i = 0; i < boardsize; i++) {
			int bindex = (int)(Math.random()*deck.length);
			board[i] = deck[bindex];
			deck = removeCard(deck,bindex);
		}
		
		for(int i = 0; i < boardsize; i++) {
			board[i].print();
		}
		System.out.println();
		//make the counters
		double[] wincounter = new double[playernum];
		double[] tiecounter = new double[playernum];
		double denominator = 0.0;
		for(int i = 0; i < playernum; i++) {
			wincounter[i] = 0.0;
			tiecounter[i] = 0.0;
		}
		long startTime = System.nanoTime();
		//generate all possible combinations of remaining cards in deck
		Card[][] allCombinations = generateCardCombinations(deck,5-boardsize);
		
		for(int i = 0; i < allCombinations.length; i++) {
			Hand[] playerHands = new Hand[playernum];
			for(int j = 0; j < playernum; j++) {
				playerHands[j] = new Hand(playerCardArrays[j],board,allCombinations[i]);
			}
			int[] winners = win(playerHands);
			if(winners.length==1) {
				wincounter[winners[0]]++;
			}
			else {
				for(int j = 0; j < winners.length;j++) {
					tiecounter[winners[j]]++;
				}
			}
			denominator++;
		}
		
		for(int i = 0; i < playernum; i++) {
			System.out.println("Player " + i + " Win Chance: " + wincounter[i]/denominator);
			System.out.println("Player " + i + " Split Chance: " + tiecounter[i]/denominator);
		}
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime-startTime) + " ns");
		//*******************************************************************Code that compares 2 ppl, confirmed to work*********************************************************************
		//making the hands of the 2 players
		/*int a1 = (int)(Math.random()*52+1);
		int a2 = (int)(Math.random()*52+1);
		int b1 = (int)(Math.random()*52+1);
		int b2 = (int)(Math.random()*52+1);
		System.out.println(a1 + ", "+a2 + ", "+b1 + ", "+b2);
		Card Ca1 = new Card(a1);
		Card Ca2 = new Card(a2);
		Card Cb1 = new Card(b1);
		Card Cb2 = new Card(b2);
		Ca1.print();
		Ca2.print();
		Cb1.print();
		Cb2.print();
		System.out.println();
		Card[] player1 = new Card[2];
		player1[0] = new Card(a1);
		player1[1] = new Card(a2);
		
		Card[] player2 = new Card[2];
		player2[0] = new Card(b1);
		player2[1] = new Card(b2);
		//making the deck and removing the players' cards from the deck
		Card takenCards[] = {Ca1,Ca2,Cb1,Cb2}; 
		
		Card[] deck = new Card[52];
		for(int i = 1; i <=52; i++) {
			deck[i-1] = new Card(i);
		}
		deck = removeElements(deck,takenCards);
		//making the board
		int boardsize = (int)(Math.random()*5);//board size from 0 to 4
		Card[] board = new Card[boardsize];
		for(int i = 0; i <boardsize; i++) {
			board[i] = new Card((int)(Math.random()*52+1));
		}
		System.out.print("Board: ");
		for(int i = 0; i < board.length;i++) {
			board[i].print();
		}
		System.out.println();
		deck = removeElements(deck,board);
		for(int i = 0; i < deck.length; i++) {
			deck[i].print();
		}
		System.out.println();
		long startTime = System.nanoTime();
		Card[][] allCombinations = generateCardCombinations(deck,5-boardsize);
		double numerator0 = 0.0;//draws
		double numerator1 = 0.0;//p1 wins
		double numerator2 = 0.0;//p2 wins
		double denominator = 0.0;
		for(int i = 0; i < allCombinations.length;i++) {
			Hand hand1 = new Hand(player1,board,allCombinations[i]);
			Hand hand2 = new Hand(player2,board,allCombinations[i]);
			int result = win2(hand1,hand2);
			if(result == 1) {
				numerator1++;
			}
			else if(result == 2) {
				numerator2++;
			}
			else if(result == 0){
				numerator0++;
			}
			denominator++;
		}
		System.out.println("Player 1 win: " + numerator1/denominator + " " + numerator1);
		System.out.println("Player 2 win: " + numerator2/denominator + " " + numerator2);
		System.out.println("Split: " + numerator0/denominator + " " + numerator0);
		long endTime = System.nanoTime();
		
		long time = endTime-startTime;
		System.out.println("Took "+time + " ns");*/
		
		
		//***********************************************Code to check if the win determiner works for mass cases***********************************************
		/*for(int i = 0; i < 100; i++) {
			
			Card[] deck = new Card[52];
			for(int j = 0; j < 52; j++) {
				deck[j] = new Card(j+1);
			}
			
			Card[] player1 = new Card[2];
			int p11 = (int)(Math.random()*deck.length);
			player1[0] = deck[p11];
			deck = removeCard(deck, p11);
			int p12 = (int)(Math.random()*deck.length);
			player1[1] = deck[p12];
			deck =removeCard(deck, p12);
			
			Card[] player2 = new Card[2];
			int p21 = (int)(Math.random()*deck.length);
			player2[0] = deck[p21];
			deck =removeCard(deck, p21);
			int p22 = (int)(Math.random()*deck.length);
			player2[1] = deck[p22];
			deck =removeCard(deck, p22);
			
			Card[] player3 = new Card[2];
			int p31 = (int)(Math.random()*deck.length);
			player3[0] = deck[p31];
			deck =removeCard(deck, p31);
			int p32 = (int)(Math.random()*deck.length);
			player3[1] = deck[p32];
			deck =removeCard(deck, p32);
			
			Card[] player4 = new Card[2];
			int p41 = (int)(Math.random()*deck.length);
			player4[0] = deck[p41];
			deck =removeCard(deck, p41);
			int p42 = (int)(Math.random()*deck.length);
			player4[1] = deck[p42];
			deck =removeCard(deck, p42);

			
			Card[] deal = new Card[5];
			int d0 = (int)(Math.random()*deck.length);
			deal[0] = deck[d0];
			deck =removeCard(deck,d0);
			int d1 = (int)(Math.random()*deck.length);
			deal[1] = deck[d1];
			deck =removeCard(deck,d1);
			int d2 = (int)(Math.random()*deck.length);
			deal[2] = deck[d2];
			deck =removeCard(deck,d2);
			int d3 = (int)(Math.random()*deck.length);
			deal[3] = deck[d3];
			deck =removeCard(deck,d3);
			int d4 = (int)(Math.random()*deck.length);
			deal[4] = deck[d4];
			deck =removeCard(deck,d4);
			
			
			System.out.println("Hand 1");
			Hand testHand1 = new Hand(player1,deal);
			testHand1.print();
			printType(testHand1.determineType());
			print2(testHand1.determineKicker());
			System.out.println("Hand 2");
			Hand testHand2 = new Hand(player2,deal);
			testHand2.print();
			printType(testHand2.determineType());
			print2(testHand2.determineKicker());
			System.out.println("Hand 3");
			Hand testHand3 = new Hand(player3,deal);
			testHand3.print();
			printType(testHand3.determineType());
			print2(testHand3.determineKicker());
			System.out.println("Hand 4");
			Hand testHand4 = new Hand(player4,deal);
			testHand4.print();
			printType(testHand4.determineType());
			print2(testHand4.determineKicker());
			
			Hand all4Hands[] = {testHand1,testHand2,testHand3,testHand4}; 
			print2(win(all4Hands));
			System.out.println("\n");
			
		}*/
		
		
		
		//*******************************************************Code for testing very specific cases*****************************************
		/*Card[] p1 = new Card[2];
		p1[0] = new Card(41);//2S
		p1[1] = new Card(50);//JS
		Card[] p2 = new Card[2];
		p2[0] = new Card(9);
		p2[1] = new Card(3);
		Card[] mid = new Card[5];
		mid[0] = new Card(4,2);
		mid[1] = new Card(7,2);
		mid[2] = new Card(8,2);
		mid[3] = new Card(9,2);
		mid[4] = new Card(12,2);
		
		
		Card[] test = new Card[7];
		test[0] = new Card(2,1);
		test[1] = new Card(7,2);
		test[2] = new Card(4,1);
		test[3] = new Card(5,1);
		test[4] = new Card(6,1);
		test[5] = new Card(3,1);
		test[6] = new Card(14,2);
		Card[] test2 = new Card[7];
		test2[0] = new Card(2,1);
		test2[1] = new Card(7,2);
		test2[2] = new Card(4,2);
		test2[3] = new Card(5,2);
		test2[4] = new Card(8,2);
		test2[5] = new Card(6,2);
		test2[6] = new Card(9,3);
	
		Hand testHand1 = new Hand(test);//2D,7H,4D,5D,6D,3D,14H (2nd)
		Hand testHand2 = new Hand(test2);//2D,7H,4H,5H,8H,6H,9S (1st)
		Hand testHand3 = new Hand(p1,mid);//2S,JS,4H,7H,8H,9H,12H (3rd)
		Hand testHand4 = new Hand(p2,mid);//9C,3C,4H,7H,8H,9H,12H (3rd)
		System.out.println("Hand1");
		System.out.println(testHand1.determineType());
		print2(testHand1.determineKicker());
		System.out.println("Hand2");
		System.out.println(testHand2.determineType());
		print2(testHand2.determineKicker());
		System.out.println("Hand3");
		System.out.println(testHand3.determineType());
		print2(testHand3.determineKicker());
		System.out.println("Hand4");
		System.out.println(testHand4.determineType());
		print2(testHand4.determineKicker());
		Hand hands[] = {testHand4,testHand2,testHand2,testHand1};
		int[] winners = win(hands);
		System.out.println(winners.length);
		print2(winners);*/
		
	}

}

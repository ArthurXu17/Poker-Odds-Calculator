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
		 	
		 	method compares adjacent elements and keeps track of the winning indices
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
		
		//******************************************************Code with user input***********************************************************************************************
		Scanner sc = new Scanner(System.in);
		//start by making deck
		Card[] deck = new Card[52];
		for(int i = 0; i <= 51; i++) {
			deck[i] = new Card(i+1);
		}
		
		//ask for player information
		
		System.out.println("How many players?");
		int numplayers = sc.nextInt();
		//constant which keeps track of each players' 2 cards
		Card[][] playerHands = new Card[numplayers][2];
		
		for(int i = 0; i < numplayers; i++) {
			//take in card information
			System.out.println("Enter Player " + (i+1) + "'s first card:");
			String str0 = sc.next();
			System.out.println("Enter Player " + (i+1) + "'s second card:");
			String str1 = sc.next();
			//put into playerHands constant
			playerHands[i][0] = new Card(str0.charAt(0), str0.charAt(1));
			playerHands[i][1] = new Card(str1.charAt(0), str1.charAt(1));
			
			//remove the player's cards from the deck
			deck = removeElements(deck, playerHands[i]);
		}
		
		//ask for board and dead cards information
		
		System.out.println("How many cards on board?");
		int boardsize = sc.nextInt();
		Card[] board = new Card[boardsize];
		
		for(int i = 0; i < boardsize; i++) {
			System.out.println("Enter the next card on the board");
			String str = sc.next();
			board[i] = new Card(str.charAt(0), str.charAt(1));
		}
		
		System.out.println("How many dead cards?");
		int deadCardsNum = sc.nextInt();
		Card[] deadCards = new Card[deadCardsNum];
		
		for(int i = 0; i < deadCardsNum; i++) {
			System.out.println("Enter the next card to be removed");
			String str = sc.next();
			deadCards[i] = new Card(str.charAt(0), str.charAt(1));
		}
		
		//after getting board and dead cards, remove them from deck
		deck = removeElements(deck, board);
		deck = removeElements(deck, deadCards);
		
		//Start the counting process
		//make the counters
		double[] wincounter = new double[numplayers];
		double[] tiecounter = new double[numplayers];
		double denominator = 0.0;
		for(int i = 0; i < numplayers; i++) {
			wincounter[i] = 0.0;
			tiecounter[i] = 0.0;
		}
		long startTime = System.nanoTime();
		
		//generate all possible combinations of remaining cards in deck
		Card[][] allCombinations = generateCardCombinations(deck,5-boardsize);
		
		for(int i = 0; i < allCombinations.length;i++) {
			//array to keep track of the 7 card hand that each player has with their 2 pocket cards
			//and shared board cards
			Hand[] playerBoardHands = new Hand[numplayers];
			for(int j = 0; j < numplayers;j++) {
				playerBoardHands[j] = new Hand(playerHands[j], board, allCombinations[i]);
			}
			//determine the winner(s) and tally up the results
			int[] winners = win(playerBoardHands);
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
		
		//displaying the results
		
		//player hands
		for(int i = 0; i < numplayers;i++) {
			System.out.print("Player " + (i+1) + "'s cards: ");
			playerHands[i][0].print();
			playerHands[i][1].print();
			System.out.println();
		}
		
		
		//board cards
		System.out.print("Board: ");
		if(boardsize >=1) {
			for(int i = 0; i < boardsize;i++) {
				board[i].print();
			}
			System.out.println();
		}
		else {
			System.out.println("Empty");
		}
		
		//dead cards
		System.out.print("Dead Cards: ");
		if(deadCardsNum>=1) {
			for(int i = 0; i < deadCardsNum;i++) {
				deadCards[i].print();
			}
			System.out.println();
		}
		else {
			System.out.println("None");
		}
		//win results
		for(int i = 0; i < numplayers; i++) {
			System.out.println("Player " + (i + 1) + " Win Chance: " + wincounter[i]/denominator);
			System.out.println("Player " + (i + 1) + " Split Chance: " + tiecounter[i]/denominator);
		}
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime-startTime) + " ns");
	}

}

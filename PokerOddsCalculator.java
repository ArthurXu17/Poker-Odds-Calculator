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
	
	//public static ArrayList<Integer> win (ArrayList<Hand> input){ 
		/*
		 * Returns the winning hand(s) when there are mulitple hands to look at.
		 * 
		 */
		/*int maxType=0;
		ArrayList<Integer> output = new ArrayList<Integer>(0);
	
		for(int i = 0; i < input.size();i++) {
			int currentType =input.get(i).determineType(); 
			if(currentType > maxType) {
				maxType = currentType;
			}
		}
		//remove all things that are not of the highest rank
		int winnerCounter = 1;//used for if there is only one winner
		for(int i = 0; i < input.size();i++) {
			try {
				int currentType =input.get(i).determineType(); 
				if(currentType < maxType) {
					input.remove(i);
					i--;
				}
				else {
					output.add(winnerCounter);
				}
				winnerCounter++;
				
			}
			catch(IndexOutOfBoundsException e) {
				break;
			}
			
		}
		if(input.size()==1) {//if there is only one hand left that has the highest rank, that is the only winner, and so we return that
			return output;
					
		}
		else {//have to look at kickers now
			int kickerlength = input.get(0).determineKicker().length;
			int[][] kickers = new int[output.size()][kickerlength];
			for(int i = 0; i < output.size(); i++) {//setting up an array of the kicker arrays of each hand that is still included in input
				int[] currentKicker = input.get(i).determineKicker();
				for(int j = 0; j < kickerlength;j++) {
					kickers[i][j] = currentKicker[j];
				}
			}
			for(int column = kickerlength-1; column >=0;column--) {
				//find max for each column, then remove any kickers that aren't included
				//incomplete
			}
			return output;
		}
	}*/
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*for(int i = 0; i < 1000; i++) {
			
			Card[] player1 = new Card[2];
			player1[0] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			player1[1] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			
			Card[] player2 = new Card[2];
			player2[0] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			player2[1] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			
			Card[] deal = new Card[5];
			deal[0] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			deal[1] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			deal[2] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			deal[3] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			deal[4] = new Card((int)(Math.random()*13+2),(int)(Math.random()*4));
			Hand testHand1 = new Hand(player1,deal);
			print(testHand1.rankArr);
			print(testHand1.suitArr);
			printType(testHand1.determineType());
			print2(testHand1.determineKicker());
			Hand testHand2 = new Hand(player2,deal);
			print(testHand2.rankArr);
			print(testHand2.suitArr);
			printType(testHand2.determineType());
			print2(testHand2.determineKicker());
			System.out.println(win2(testHand1,testHand2));
			System.out.println();
			
		}*/
		
		
		int a1 = (int)(Math.random()*52+1);
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
		
		Card[] deck = new Card[48];
		int arrcounter = 0;
		for(int i = 1; i <= 52; i++) {
			if(i != a1 &&i != a2 &&i != b1 &&i != b2) {
				deck[arrcounter] = new Card(i);
				arrcounter++;
			}
		}
		/*for(int i = 0; i <= 47; i++) {
			deck[i] = new Card(i+5);
		}*/
		for(int i = 0; i < deck.length; i++) {
			deck[i].print();
		}
		System.out.println();
		Card[][] allCombinations = generateCardCombinations(deck,5);
		double numerator0 = 0.0;//draws
		double numerator1 = 0.0;//p1 wins
		double numerator2 = 0.0;//p2 wins
		double denominator = 0.0;
		for(int i = 0; i < allCombinations.length;i++) {
			Hand hand1 = new Hand(player1,allCombinations[i]);
			Hand hand2 = new Hand(player2, allCombinations[i]);
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
		Hand h1 = new Hand(p1,mid);
		Hand h2 = new Hand(p2, mid);
		System.out.println(win2(h1,h2));*/
		
		/*Card[] test = new Card[7];
		test[0] = new Card(2,2);
		test[1] = new Card(7,1);
		test[2] = new Card(4,2);
		test[3] = new Card(5,1);
		test[4] = new Card(9,3);
		test[5] = new Card(11,2);
		test[6] = new Card(12,2);
		Card[] test2 = new Card[7];
		test2[0] = new Card(2,2);
		test2[1] = new Card(7,2);
		test2[2] = new Card(4,2);
		test2[3] = new Card(5,2);
		test2[4] = new Card(9,2);
		test2[5] = new Card(10,2);
		test2[6] = new Card(12,2);
	
		Hand testHand1 = new Hand(test);
		Hand testHand2 = new Hand(test2);
		System.out.println(testHand1.determineType());
		print(testHand1.determineKicker());
		System.out.println(testHand2.determineType());
		print(testHand2.determineKicker());
		System.out.println(win2(testHand1,testHand2));*/
	}

}

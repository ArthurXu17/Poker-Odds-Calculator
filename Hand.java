import java.util.*;
public class Hand {
	//Class variables**********************************************************************
	public Card[] arr;
	public int[] suitArr, rankArr;
	private int type, size;
	
	//constructor***********************************************************************************
	public Hand(Card[] input) {
		int n = input.length;
		size = n;
		arr = new Card[n];
		suitArr = new int[n];
		rankArr = new int[n];
		for(int i = 0; i < n;i++) {
			arr[i] = input[i];
			suitArr[i] = input[i].suit;
			rankArr[i] = input[i].rank;
		}
	}
	
	public Hand(Card[] input1, Card[] input2) {
		int n = input1.length+input2.length;
		size = n;
		arr = new Card[n];
		suitArr = new int[n];
		rankArr = new int[n];
		for(int i = 0; i < input1.length;i++) {
			arr[i] = input1[i];
			suitArr[i] = input1[i].suit;
			rankArr[i] = input1[i].rank;
		}
		for(int i = input1.length; i < n;i++) {
			arr[i] = input2[i-input1.length];
			suitArr[i] = input2[i-input1.length].suit;
			rankArr[i] = input2[i-input1.length].rank;
		}
	}
	
	//methods used in determineType and determineKicker*****************************************************************

	public static void print2(int[] arr) {
		for(int i = 0; i < arr.length;i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
	public static int[][] AssignedSort(int [] arr, int[] arr2) {//this is insertion sort, but modified to alter the second array accordingly
		int n = arr.length; 
		int[][] output = new int[2][n];
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int key2 = arr2[i];
            int j = i - 1; 
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j];
                arr2[j+1] = arr2[j];
                
                j--; 
            } 
            arr[j + 1] = key;
            arr2[j + 1] = key2;
        }
        output[0] = arr;
        output[1] = arr2;
        return output;
	}
	
	public static int[] removeElement(int[] arr, int index) {
		int[] output = new int[arr.length-1];
		for(int i = 0; i < index; i++) {
			output[i] = arr[i];
		}
		for(int i = index+1; i<arr.length;i++ ) {
			output[i-1] = arr[i]; 
		}
		return output;
	}
	//***************************************************************the boolean checkers that determine if a hand is of a given type****************************************************
	/*all these methods assume the input array is sorted
	 * 
	 */
	public static boolean straightFlush(int[] ranks, int[] suits) {
		
			int[][] sorted = AssignedSort(ranks,suits);
			int[] rankSorted = sorted[0];
			int[] suitSorted = sorted[1];
			//print2(rankSorted);
			//print2(suitSorted);
			//after this specfic sorting (where rank and suit remain linked), it's just the straight method
			int dupecounter= 0;//legit only used for finding a,2,3,4,5 straight
			
			int[] duperanks = new int[rankSorted.length];//new array so we don't change the original array
			int[] dupesuits = new int[rankSorted.length];
			for(int i = 0; i < rankSorted.length;i++) {
				duperanks[i] = rankSorted[i];
				dupesuits[i] = suitSorted[i];
			}
			if(has2(rankSorted)) {//if the hand has a pair, we have to remove the pair to allow us to check consecutive numbers
				for(int i = 1; i < rankSorted.length; i++) {
					if(duperanks[i] == rankSorted[i-1]) {
						duperanks[i-1] = -1;//specifically change i-1 so if dupe[i] = dupe[i+1], it will change on next iteration
						dupecounter++;
					}
				}
				AssignedSort(duperanks,dupesuits);
			
				if(duperanks[dupecounter] == 2 && duperanks[dupecounter+1] == 3 && duperanks[dupecounter+2] == 4 && duperanks[dupecounter+3] == 5 && duperanks[duperanks.length-1] == 14&& dupesuits[dupecounter] == dupesuits[dupecounter+1] &&dupesuits[dupecounter+1] == dupesuits[dupecounter+2] &&dupesuits[dupecounter+2] == dupesuits[dupecounter+3] &&dupesuits[dupecounter+3] == dupesuits[dupesuits.length-1]) {
					return true;
				}
				for(int i = 4; i < rankSorted.length; i++) {
					if(duperanks[i] == duperanks[i-1] + 1 && duperanks[i-1] == duperanks[i-2] + 1 && duperanks[i-2] == duperanks[i-3] + 1 && duperanks[i-3] == duperanks[i-4] + 1 && suitSorted[i] == suitSorted[i-1] && suitSorted[i-1] == dupesuits[i-2]&& dupesuits[i-2] == dupesuits[i-3]&&dupesuits[i-3] == dupesuits[i-4] ) {
						return true;
					}
				}
				return false;
			}
			else {
				if(rankSorted[0] == 2 && rankSorted[1] == 3 && rankSorted[2] == 4 && rankSorted[3] == 5 && rankSorted[rankSorted.length-1] == 14 && suitSorted[0] == suitSorted[1] &&suitSorted[1] == suitSorted[2] &&suitSorted[2] == suitSorted[3] &&suitSorted[3] == suitSorted[suitSorted.length-1]) {
					return true;
				}
				for(int i = 4; i < rankSorted.length; i++) {
					if(rankSorted[i] == rankSorted[i-1] + 1 && rankSorted[i-1] == rankSorted[i-2] + 1 && rankSorted[i-2] == rankSorted[i-3] + 1 && rankSorted[i-3] == rankSorted[i-4] + 1 && suitSorted[i] == suitSorted[i-1] && suitSorted[i-1] == suitSorted[i-2]&& suitSorted[i-2] == suitSorted[i-3]&& suitSorted[i-3] == suitSorted[i-4] ) {
						return true;
					}
				}
				return false;
			}
		
	}
	public static boolean has4(int[] arr) {//requires sorted array as input
		for(int i = 3; i < arr.length; i++) {
			if(arr[i] == arr[i-1] && arr[i-1] == arr[i-2] && arr[i-2] == arr[i-3]) {
				return true;
			}
		}
		return false;
	}
	public static boolean fullHouse(int[] arr) {
		int[] dupe = new int[arr.length];//new array so we don't change the original array
		for(int i = 0; i < arr.length;i++) {
			dupe[i] = arr[i];
		}	
		if(has3(dupe)) {//only bother with tryna find pair, if already has a triple
			for(int i = 2; i < arr.length; i++) {
				if(dupe[i] == dupe[i-1] && dupe[i-1] == dupe[i-2]) {//when find a triple, change those values and end the loop
					dupe[i] = -1;
					dupe[i-1] = -2;
					dupe[i-2] = -3;
					break;
				}
			}
			if(has2(dupe)) {//if has another pair after the initial triple has been removed
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public static boolean flush(int[] arr) {//literally finds duplicate of 5 in a sorted suit array
		for(int i = 4; i < arr.length; i++) {
			if(arr[i] == arr[i-1] && arr[i-1] == arr[i-2] && arr[i-2] == arr[i-3] && arr[i-3] == arr[i-4]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean straight(int[] arr) {
		int dupecounter= 0;//legit only used for finding a,2,3,4,5 straight
		
		int[] dupe = new int[arr.length];//new array so we don't change the original array
		for(int i = 0; i < arr.length;i++) {
			dupe[i] = arr[i];
		}
		if(has2(arr)) {//if the hand has a pair, we have to remove the pair to allow us to check consecutive numbers
			for(int i = 1; i < arr.length; i++) {
				if(dupe[i] == arr[i-1]) {
					dupe[i-1] = -1;//specifically change i-1 so if dupe[i] = dupe[i+1], it will change on next iteration
					dupecounter++;
				}
			}
			Arrays.sort(dupe);
			if(dupe[dupecounter] == 2 && dupe[dupecounter+1] == 3 && dupe[dupecounter+2] == 4 && dupe[dupecounter+3] == 5 && dupe[dupe.length-1] == 14) {
				return true;
			}
			for(int i = 4; i < arr.length; i++) {
				if(dupe[i] == dupe[i-1] + 1 && dupe[i-1] == dupe[i-2] + 1 && dupe[i-2] == dupe[i-3] + 1 && dupe[i-3] == dupe[i-4] + 1) {
					return true;
				}
			}
			return false;
		}
		else {
			if(dupe[0] == 2 && dupe[1] == 3 && dupe[2] == 4 && dupe[3] == 5 && dupe[dupe.length-1] == 14) {
				return true;
			}
			for(int i = 4; i < arr.length; i++) {
				if(arr[i] == arr[i-1] + 1 && arr[i-1] == arr[i-2] + 1 && arr[i-2] == arr[i-3] + 1 && arr[i-3] == arr[i-4] + 1) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static boolean has3(int[] arr) {//requires sorted array as input
		for(int i = 2; i < arr.length; i++) {
			if(arr[i] == arr[i-1] && arr[i-1] == arr[i-2]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean twoPair(int[] arr) {
		int[] dupe = new int[arr.length];//new array so we don't change the original array
		for(int i = 0; i < arr.length;i++) {
			dupe[i] = arr[i];
		}	
		if(has2(dupe)) {//only bother with tryna find a second pair, if already has a pair
			for(int i = 1; i < arr.length; i++) {
				if(dupe[i] == dupe[i-1]) {//when find a pair, change those values and end the loop
					dupe[i] = -1;
					dupe[i-1] = -2;
					break;
				}
			}
			if(has2(dupe)) {//if has another pair after the initial triple has been removed
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public static boolean has2(int[] arr) {//requires sorted array as input
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == arr[i-1]) {
				return true;
			}
		}
		return false;
	}
	

	//methods***********************************************************************************
	
	public int determineType() {
		/*
		1 = high card
		2 = pair
		3 = 2 pair
		4 = triple
		5 = straight
		6 = flush
		7 = full house
		8 = quad
		9 = straight flush
		 */
		//make duplicates because we don't want to change the actual values
		int[] rankArr2 = new int[size];
		int[] suitArr2 = new int[size];
		//these are specifically for straight flush, can't be sorted
		int[] rankArr3 = new int[size];
		int[] suitArr3 = new int[size];
		for(int i = 0; i < size;i++) {
			rankArr2[i] = rankArr[i];
			suitArr2[i] = suitArr[i];
			rankArr3[i] = rankArr[i];
			suitArr3[i] = suitArr[i];
		}
		Arrays.sort(suitArr2);
		Arrays.sort(rankArr2);
		
		if(has4(rankArr2)) {
			type = 8;
			return 8;
		}
		else if(fullHouse(rankArr2)) {
			type = 7;
			return 7;
		}
		//can't have straight flush and 4 of a kind or fh in texas hold'em
		else if(flush(suitArr2) && straight(rankArr2)) {
			if(straightFlush(rankArr3,suitArr3)) {
				type = 9;
				return 9;
			}
			else {
				type = 6;
				return 6;
			}
		}
		else if(flush(suitArr2)) {
			type = 6;
			return 6;
		}
		else if(straight(rankArr2)) {
			type = 5;
			return 5;
		}
		else if(has3(rankArr2)) {
			type = 4;
			return 4;
		}
		else if(twoPair(rankArr2)) {
			type = 3;
			return 3;
		}
		else if(has2(rankArr2)) {
			type = 2;
			return 2;
		}
		else {
			type = 1;
			return 1;
		}
	}
	public int[] determineKicker() {
		//the kicker array is used to differentiate between hands of the same type
		//all the algorithms used within this method assume that the input hand satisfies the conditions of each type
		int[] output;
		//make duplicates because i don't want to actually change/sort the arrays
		int[] rankArr2 = new int[size];
		int[] suitArr2 = new int[size];
		//these are specifically for straight flush, can't be sorted
		int[] rankArr3 = new int[size];
		int[] suitArr3 = new int[size];
		for(int i = 0; i < size;i++) {
			rankArr2[i] = rankArr[i];
			suitArr2[i] = suitArr[i];
			rankArr3[i] = rankArr[i];
			suitArr3[i] = suitArr[i];
		}
		Arrays.sort(suitArr2);
		Arrays.sort(rankArr2);
		if(type == 9) {//straight flush
			//determine the suit that has 5+ in
			//remove elements that are not of that suit, this also means that no need to check for pairs
			//perform assigned sort
			//loop from top, if it has the common suit and is one greater than one below it and two greater than the one below that, we good
			output = new int[1];
			int[] suitDeterminer = new int[4];
			int suit = 0;//suit that occurs 5 times
			for(int i = 0; i <4;i++) {
				suitDeterminer[i] = 0;
			}
			for(int i = 0; i < size;i++) {
				suitDeterminer[suitArr3[i]]++;//assumes that suits are 0,1,2,3
			}
			for(int i = 0; i < 4;i++) {
				if(suitDeterminer[i] >= 5) {
					suit = i;
					break;
				}
			}
			/*int[] rankdupe = new int[rankArr3.length];//make a duplicate so we can remove without issues
			for(int i = 0; i < rankArr3.length;i++) {
				rankdupe[i] = rankArr3[i];
			}*/
			int removecounter = 0;//counter to track how many elements have already been removed
			for(int i = 0; i < suitArr3.length;i++) {//be sure to use suitArr.length as rankArr.length is changing as we go through loop
				
				if(suitArr3[i] != suit) {
					//with each subsequent removal, the array decreases size by one
					rankArr3 = removeElement(rankArr3,i-removecounter);
					removecounter++;
					//System.out.println("removecounter: " + removecounter);
				}

			}
			Arrays.sort(rankArr3);
			boolean hasOtherStraight = false;
			//only have the suit of interest, just do the straight algorithm
			//print2(rankArr3);
			for(int i = rankArr3.length-1; i >=4; i--) {
				if(rankArr3[i] == rankArr3[i-1] + 1 && rankArr3[i-1] == rankArr3[i-2] + 1 && rankArr3[i-2] == rankArr3[i-3] + 1 && rankArr3[i-3] == rankArr3[i-4] + 1) {
					output[0] = rankArr3[i];
					hasOtherStraight = true;
					break;
				}
			}
			if(!hasOtherStraight) {
				output[0] = 5;
			}
			return output;
			
		}
		else if(type == 8) {//4 of a kind
			output = new int[2];
			for(int i = 3; i < size; i++) {//i should prob start from the top, but hands are at most size 7, so there can't be 2 quads
				if(rankArr2[i] == rankArr2[i-1] && rankArr2[i-1] == rankArr2[i-2] && rankArr2[i-2] == rankArr2[i-3]) {
					output[1] = rankArr2[i];
				}
			}
			if(rankArr2[size-1] == output[1]) {//if the highest value card is the 4 of a kind, it can no longer be the kicker
				output[0] = rankArr2[size - 5];//the top 4 in the sorted array will be part of the 4 of a kind, take the next one up
			}
			else {
				output[0] = rankArr2[size - 1];
			}
		} 
		else if(type == 7) {//full house
			output = new int[2];
			//first find triple
			for(int i = size-1; i >=2;i--) {//must start from top in case of 2 triples
				if(rankArr2[i] == rankArr2[i-1] && rankArr2[i-1] == rankArr2[i-2]) {
					output[1] = rankArr2[i];
					rankArr2[i] = -1;//change these so that can find the pair afterward
					rankArr2[i-1] = -2;
					rankArr2[i-2] = -3;
					break;
				}
			}
			Arrays.sort(rankArr2);
			//find pair, again starting from top in case of 3-2-2
			for(int i = size -1; i>= 2; i--) {
				if(rankArr2[i] == rankArr2[i-1]) {
					output[0] = rankArr2[i];
					break;
				}
			}
		}
		else if(type == 6) {//flush
			output = new int[5];
			int suit = 0;
			int[] suitDeterminer = new int[4];
			for(int i = 0; i <4;i++) {
				suitDeterminer[i] = 0;
			}
			for(int i = 0; i < size;i++) {
				suitDeterminer[suitArr3[i]]++;//assumes that suits are 0,1,2,3
			}
			for(int i = 0; i < 4;i++) {
				if(suitDeterminer[i] >= 5) {
					suit = i;
					break;
				}
			}
			int[][] sorted = AssignedSort(rankArr3,suitArr3);
			int[] rankSorted = sorted[0];
			int[] suitSorted = sorted[1];
			
			int outputIndexCounter = 4;
			for(int i = size-1; i>=0;i--) {
				if(suitSorted[i] == suit) {
					output[outputIndexCounter] = rankSorted[i];
					outputIndexCounter--;
					if(outputIndexCounter < 0) {
						break;
					}
				}
			}
			return output;
			
		}
		else if(type == 5) {//straight
			boolean hasOtherStraight = false;//used to determine if the straight is A,2,3,4,5
			/*
			 * Method is made in assumption that a straight exists. If it is not found with 5 consecutive numbers, this implies that it must be the 5 high straight
			 */
			output = new int[1];
			if(has2(rankArr2)) {//if the hand has a pair, we have to remove the pair to allow us to check consecutive numbers
				for(int i = 1; i < size; i++) {
					if(rankArr2[i] == rankArr2[i-1]) {
						rankArr2[i-1] = -1;
					}
				}
				Arrays.sort(rankArr2);
				for(int i = size-1; i >=4; i--) {
					if(rankArr2[i] == rankArr2[i-1] + 1 && rankArr2[i-1] == rankArr2[i-2] + 1 && rankArr2[i-2] == rankArr2[i-3] + 1 && rankArr2[i-3] == rankArr2[i-4] + 1) {
						output[0] = rankArr2[i];
						hasOtherStraight = true;
						break;
					}
				}
				
			}
			else {
				for(int i = size-1; i >=4; i--) {
					if(rankArr2[i] == rankArr2[i-1] + 1 && rankArr2[i-1] == rankArr2[i-2] + 1 && rankArr2[i-2] == rankArr2[i-3] + 1 && rankArr2[i-3] == rankArr2[i-4] + 1) {
						output[0] = rankArr2[i];
						hasOtherStraight = true;
						break;
					}
				}
			}
			if(!hasOtherStraight) {
				output[0] = 5;
			}
		}
		else if(type == 4) {//three of a kind
			output = new int[3];
			//first find triple
			for(int i = size-1; i >=2;i--) {//must start from top in case of 2 triples
				if(rankArr2[i] == rankArr2[i-1] && rankArr2[i-1] == rankArr2[i-2]) {
					output[2] = rankArr2[i];
					rankArr2[i] = -1;//change these so that we can find high cards afterward
					rankArr2[i-1] = -2;
					rankArr2[i-2] = -3;
					break;
				}
			}
			Arrays.sort(rankArr2);
			output[1] = rankArr2[size-1];
			output[0] = rankArr2[size-2];
		}
		else if(type == 3) {//2 pair
			output = new int[3];
			//find highest pair first
			for(int i = size-1; i >=1;i--) {//must start from top
				if(rankArr2[i] == rankArr2[i-1]) {
					output[2] = rankArr2[i];
					rankArr2[i] = -1;//change these so that we can find other cards afterward
					rankArr2[i-1] = -2;
					break;
				}
			}
			//resort
			Arrays.sort(rankArr2);
			//find next highest pair
			for(int i = size-1; i >=1;i--) {//must start from top
				if(rankArr2[i] == rankArr2[i-1]) {
					output[1] = rankArr2[i];//this is second highest pair now
					rankArr2[i] = -3;//change these so that we can find other cards afterward
					rankArr2[i-1] = -4;
					break;
				}
			}
			//resort
			Arrays.sort(rankArr2);
			
			//find the next single
			output[0] = rankArr2[size-1];
			
		}
		else if(type == 2) {//pair
			output = new int[4];
			//find highest pair first
			for(int i = size-1; i >=1;i--) {//must start from top
				if(rankArr2[i] == rankArr2[i-1]) {
					output[3] = rankArr2[i];
					rankArr2[i] = -1;//change these so that we can find other cards afterward
					rankArr2[i-1] = -2;
					break;
				}
			}
			Arrays.sort(rankArr2);
			//determine the next kickers
			output[2] = rankArr2[size-1];
			output[1] = rankArr2[size-2];
			output[0] = rankArr2[size-3];
		}
		else {
			return rankArr2;//if high card, sorted ranks is good enough
		}
		return output;
		
	}
	
	/*public void printNumbers() {
		for(int i = 0; i < 7;i++) {
			System.out.print(rankArr[i] + " ");
		}
		System.out.println();
	}
	public void printSuits() {
		for(int i = 0; i < 7;i++) {
			System.out.print(suitArr[i] + " ");
		}
		System.out.println();
	}*/
}

package home.algo.dp;

public class CombinationSum {

	public static void main(String[] args) {

		CombinationSum combinationSum = new CombinationSum();
		System.out.println(combinationSum.findCombinationsCount(15, new int[] {
				1, 6, 7 }));
		System.out.println(combinationSum.findCombinationsCount(100, new int[] {
				1, 5, 25, 50 }));
		System.out.println(combinationSum.findCombinationsCount(10, new int[] {
				1, 2, 9 }));
	}

	int findCombinationsCount(int amount, int coins[]) {
		// I am assuming amount >= 0, coins.length > 0 and all elements of coins
		// > 0.
		if (coins.length == 1) {
			return amount % coins[0] == 0 ? 1 : 0;
		} else {
			int total = 0;
			int[] subCoins = removeFirst(coins);
			for (int i = 0; i * coins[0] <= amount; ++i) {
				total += findCombinationsCount(amount - i * coins[0], subCoins);
			}
			return total;
		}
	}

	private int[] removeFirst(int[] coins) {
		if (2 <= coins.length) {
			int[] returnArray = new int[coins.length - 1];
			for (int i = 0; i < returnArray.length; i++) {
				returnArray[i] = coins[i + 1];
			}
			return returnArray;
		}
		return coins;
	}
}

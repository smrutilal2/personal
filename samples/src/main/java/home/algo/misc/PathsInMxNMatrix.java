package home.algo.misc;

public class PathsInMxNMatrix {

	private int[][] matrix;

	private int rowCount;
	private int colCount;

	public PathsInMxNMatrix(int[][] matrix) {
		this.matrix = matrix;
		rowCount = matrix.length;
		colCount = matrix[0].length;
	}

	public void printAllPaths(int currX, int currY, String path) {
		if (currX == rowCount - 1) {
			for (int j = currY; j <= colCount - 1; j++) {
				path = path + "->" + matrix[currX][j];
			}
			System.out.println("Path : " + path);
			return;
		}

		if (currY == colCount - 1) {
			for (int i = currX; i <= rowCount - 1; i++) {
				path = path + "->" + matrix[i][currY];
			}
			System.out.println("Path : " + path);
			return;
		}
		path = path + "->" + matrix[currX][currY];
		printAllPaths(currX + 1, currY, path);
		printAllPaths(currX, currY + 1, path);
	}

	public int countAllPaths(int currX, int currY) {

		return 0;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 },
				{ 10, 11, 12 } };
		PathsInMxNMatrix pathsInMxNMatrix = new PathsInMxNMatrix(matrix);
		pathsInMxNMatrix.printAllPaths(0, 0, "");
	}

}

package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		int prevScore = 0;
		int score = 0;
		Color[][] scoreBoard = board.flatten();
		boolean[][] visited = new boolean[scoreBoard.length][scoreBoard[0].length];
		for(int i = 0; i < scoreBoard.length;i++){
			for(int j = 0; j < scoreBoard[i].length;j++){
				score = undiscoveredBlobSize(i,j,scoreBoard,visited);
				if (score > prevScore){
					prevScore = score;
				}
			}
		}
		return prevScore;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal) 
		+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		return undiscoveredBlobSizeHelper(i, j, unitCells, visited, 0);
	}

	private int undiscoveredBlobSizeHelper(int i, int j, Color[][] unitCells, boolean[][] visited, int size) {
		if (unitCells[i][j] != targetGoal || visited[i][j] == true) {
			return size;
		} else {
			if (visited[i][j] == false && unitCells[i][j].equals(targetGoal)) {
				size++;
				visited[i][j] = true;

				if (i < (unitCells.length - 1)) {
					size = undiscoveredBlobSizeHelper(i + 1, j, unitCells, visited, size);
				}
				if (i > 0) {
					size = undiscoveredBlobSizeHelper(i - 1, j, unitCells, visited, size);
				}
				if (j > 0) {
					size = undiscoveredBlobSizeHelper(i, j - 1, unitCells, visited, size);
				}
				if (j < (unitCells[i].length - 1)) {
					size = undiscoveredBlobSizeHelper(i, j + 1, unitCells, visited, size);
				}
			}
			return size;
		}
	}}

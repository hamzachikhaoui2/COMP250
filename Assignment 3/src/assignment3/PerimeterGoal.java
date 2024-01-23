package assignment3;

import java.awt.Color;


public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
	int score  = 0;
	Color[][] scoreBoard = board.flatten();
		for (int i = 0; i<scoreBoard.length;i++){
			for (int j = 0; j<scoreBoard[i].length;j++){
				if ((i == 0 || j == 0 || i == (scoreBoard.length-1) || j == (scoreBoard[i].length-1))
						&& scoreBoard[i][j].equals(targetGoal)) {
					score ++;}
				if (((i == 0 && j == 0) || (i == 0 && j == (scoreBoard[i].length-1)) || (i == (scoreBoard.length-1) && j == (scoreBoard[i].length-1)) || (j == 0 && i == (scoreBoard.length-1)))
						&& (scoreBoard[i][j].equals(targetGoal))) {
					score ++; }
			}
		}
		return score;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}

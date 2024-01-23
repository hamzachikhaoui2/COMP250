package finalproject;


import java.util.ArrayList;

public class RatingDistributionByProf extends DataAnalyzer {

	private MyHashTable<String, MyHashTable<String, Integer>> myHashTable;


	public RatingDistributionByProf(Parser p) {
		super(p);
	}

	int ratingIndex = parser.fields.get("student_star");
	int nameIndex = parser.fields.get("professor_name");
	int schoolIndex = parser.fields.get("school_name");
	int commentIndex = parser.fields.get("comments");
	int genderIndex = parser.fields.get("gender");
	private MyHashTable<String, ArrayList<Integer>> bigHashmap;
	private MyHashTable<String,MyHashTable<String, ArrayList<Integer>>> biggestHashmap;
	private MyHashTable<String, Integer> finalTable;
	private MyHashTable<String, MyHashTable<String, Integer>> OutputTable;
	private String keyword;
	private Integer count1 = 0;
	private Integer count2 = 0;
	private Integer count3 = 0;
	private Integer count4 = 0;
	private Integer count5 = 0;


	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		this.keyword = keyword.toLowerCase().stripLeading().stripTrailing();;
		return this.OutputTable.get(this.keyword);

		//ADD YOUR CODE ABOVE THIS
	}

	//private ArrayList<String> profNames = ((ArrayList< LinkedList<MyPair<String,Integer>>>) parser.data).; this is the reason why I cannot use the getSetKeys()


	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		int ratingIndex = parser.fields.get("student_star");
		int nameIndex = parser.fields.get("professor_name");
		int schoolIndex = parser.fields.get("school_name");
		int commentIndex = parser.fields.get("comments");
		int genderIndex = parser.fields.get("gender");
		MyHashTable<String,MyHashTable<String, ArrayList<Integer>>> biggestHashmap = new MyHashTable<String,MyHashTable<String, ArrayList<Integer>>>();
		this.biggestHashmap = biggestHashmap;



		for (int i = 0; i < parser.data.size(); i++) {
			MyHashTable<String, ArrayList<Integer>> bigHashmap = new MyHashTable<String, ArrayList<Integer>>();
			if (this.biggestHashmap.get(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase()) == null){
			bigHashmap.put("1",new ArrayList<Integer>());
			bigHashmap.put("2",new ArrayList<Integer>());
			bigHashmap.put("3",new ArrayList<Integer>());
			bigHashmap.put("4",new ArrayList<Integer>());
			bigHashmap.put("5",new ArrayList<Integer>());}
			else{
				bigHashmap = this.biggestHashmap.get(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase());
			}
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 1 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <2){
					Integer rating = (int) Double.parseDouble(parser.data.get(i)[ratingIndex]);
					bigHashmap.get("1").add(rating);
				}

				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 2 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <3){
					Integer rating = (int) Double.parseDouble(parser.data.get(i)[ratingIndex]);
					bigHashmap.get("2").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 3 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <4){
					Integer rating = (int) Double.parseDouble(parser.data.get(i)[ratingIndex]);
					bigHashmap.get("3").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 4 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <5){
					Integer rating = (int) Double.parseDouble(parser.data.get(i)[ratingIndex]);
					bigHashmap.get("4").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 5){
					Integer rating = (int) Double.parseDouble(parser.data.get(i)[ratingIndex]);
					bigHashmap.get("5").add(rating);
				}
				this.bigHashmap = bigHashmap;
				this.biggestHashmap.put(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase(), this.bigHashmap);

			}

		ArrayList<String> listProfs = this.biggestHashmap.getKeySet();
		MyHashTable<String, MyHashTable<String, Integer>> OutputTable = new MyHashTable<String, MyHashTable<String, Integer>>();

		for (int j = 0; j<listProfs.size();j++){
			this.count1 = 0;
			this.count2 = 0;
			this.count3 = 0;
			this.count4 = 0;
			this.count5 = 0;
			this.count1 = this.biggestHashmap.get(listProfs.get(j)).get("1").size();
			this.count2 = this.biggestHashmap.get(listProfs.get(j)).get("2").size();
			this.count3= this.biggestHashmap.get(listProfs.get(j)).get("3").size();
			this.count4 = this.biggestHashmap.get(listProfs.get(j)).get("4").size();
			this.count5 = this.biggestHashmap.get(listProfs.get(j)).get("5").size();
			MyHashTable<String, Integer> finalTable = new MyHashTable<String, Integer>();
			finalTable.put("1", this.count1);
			finalTable.put("2", this.count2);
			finalTable.put("3", this.count3);
			finalTable.put("4", this.count4);
			finalTable.put("5", this.count5);
			this.finalTable = finalTable;
			OutputTable.put(listProfs.get(j).stripLeading().stripTrailing().toLowerCase(), this.finalTable);
		}
		this.OutputTable = OutputTable;


























	}

	/*int ratingIndex = parser.fields.get("student_difficult");
		int nameIndex = parser.fields.get("professor_name");

		for (int i = 0; i < parser.data.size(); i++) {
			String profName = parser.data.get(i)[nameIndex].toLowerCase().stripLeading().stripLeading();

			MyHashTable<String, Integer> myHashRatings = new MyHashTable<String, Integer>();
			Integer rating_1 = 0;
			Integer rating_2 = 0;
			Integer rating_3 = 0;
			Integer rating_4 = 0;
			Integer rating_5 = 0;
			rating_1 = myHashRatings.get("1");
			rating_2 = myHashRatings.get("2");
			rating_3 = myHashRatings.get("3");
			rating_4 = myHashRatings.get("4");
			rating_5 = myHashRatings.get("5");

			myHashRatings.put("1", rating_1);
			myHashRatings.put("2", rating_2);
			myHashRatings.put("3", rating_3);
			myHashRatings.put("4", rating_4);
			myHashRatings.put("5", rating_5);

			myHashTable.put(profName, myHashRatings);
		}



		for (int i = 0; i < parser.data.size(); i++) {
			String profName = parser.data.get(i)[nameIndex].toLowerCase().stripIndent();
			String profRating = parser.data.get(i)[ratingIndex];
			double profRatingInt = Double.parseDouble(profRating);


			MyHashTable<String, Integer> tempHashRatings = myHashTable.get(profName);
			Integer rating_1 = tempHashRatings.get("1");
			Integer rating_2 = tempHashRatings.get("2");
			Integer rating_3 = tempHashRatings.get("3");
			Integer rating_4 = tempHashRatings.get("4");
			Integer rating_5 = tempHashRatings.get("5");

			if (profRatingInt < 1) {
				rating_1++;
			}
			if (profRatingInt >= 1 && profRatingInt < 2) {
				rating_2++;
			}
			if (profRatingInt >= 2 && profRatingInt < 3) {
				rating_3++;
			}
			if (profRatingInt >= 3 && profRatingInt < 4) {
				rating_4++;
			}
			if (profRatingInt >= 4) {
				rating_5++;
			}

			tempHashRatings.put("1", rating_1);
			tempHashRatings.put("2", rating_2);
			tempHashRatings.put("3", rating_3);
			tempHashRatings.put("4", rating_4);
			tempHashRatings.put("5", rating_5);

			myHashTable.put(profName, tempHashRatings);


		}


		//ADD YOUR CODE ABOVE THIS


	}


	public static void main(String args) {


	}
}*/}

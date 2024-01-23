package finalproject;


import java.util.ArrayList;

public class RatingDistributionBySchool extends DataAnalyzer {




	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	int ratingIndex = parser.fields.get("student_star");
	int nameIndex = parser.fields.get("professor_name");
	int schoolIndex = parser.fields.get("school_name");
	int commentIndex = parser.fields.get("comments");
	int genderIndex = parser.fields.get("gender");
	private MyHashTable<String, ArrayList<Double>> bigHashmap;
	private MyHashTable<String, MyHashTable<String, ArrayList<Double>>> biggestHashmap;
	private MyHashTable<String, Integer> finalTable;
	private MyHashTable<String, MyHashTable<String, Integer>> OutputTable;
	private String keyword;

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		this.keyword = keyword.stripLeading().stripTrailing().toLowerCase(); //school name
		return this.OutputTable.get(this.keyword);

		//ADD YOUR CODE ABOVE THIS
	}

	private static Double calculateAverage(ArrayList<Double> numbers){
		double sum = 0;
		for (int i = 0; i<numbers.size(); i++){
			sum += numbers.get(i);
		}
		Double average  =  (sum/numbers.size());
		return average;
	}



	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		int ratingIndex = parser.fields.get("student_star");
		int nameIndex = parser.fields.get("professor_name");
		int schoolIndex = parser.fields.get("school_name");
		int commentIndex = parser.fields.get("comments");
		int genderIndex = parser.fields.get("gender");

		MyHashTable<String, MyHashTable<String, ArrayList<Double>>> biggestHashmap = new MyHashTable<String, MyHashTable<String, ArrayList<Double>>>();
		MyHashTable<String, ArrayList<Double>> bigHashmap = new MyHashTable<String, ArrayList<Double>>();
		this.bigHashmap = bigHashmap;
		this.biggestHashmap = biggestHashmap;
		ArrayList<Double> tempArray = new ArrayList<Double>();
		for (int i = 0; i < parser.data.size(); i++) {
			if (this.biggestHashmap.get(parser.data.get(i)[schoolIndex].stripLeading().stripTrailing().toLowerCase()) == null){
				this.biggestHashmap.put(parser.data.get(i)[schoolIndex].stripLeading().stripTrailing().toLowerCase(), new MyHashTable<String, ArrayList<Double>>());}
				if (this.bigHashmap.get(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase()) == null){
				this.bigHashmap.put(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase(), new ArrayList<Double>());}
				Double rating = (double) Double.parseDouble(parser.data.get(i)[ratingIndex]);
				tempArray = this.bigHashmap.get(parser.data.get(i)[nameIndex].toLowerCase().stripLeading().stripTrailing());
				tempArray.add(rating);
				this.bigHashmap.put(parser.data.get(i)[nameIndex].stripLeading().stripTrailing().toLowerCase(),tempArray);
				this.biggestHashmap.put(parser.data.get(i)[schoolIndex].stripTrailing().stripLeading().toLowerCase(), this.bigHashmap);
		}




		ArrayList<String> listProfs = this.bigHashmap.getKeySet();
		ArrayList<String> listOfSchools = this.biggestHashmap.getKeySet();
		MyHashTable<String, Integer> finalTable = new MyHashTable<String, Integer>();
		MyHashTable<String, MyHashTable<String, Integer>> OutputTable = new MyHashTable<String, MyHashTable<String, Integer>>();
		for (int k = 0; k<listOfSchools.size();k++){
		for (int j = 0; j<listProfs.size(); j++){
			Double average = (double) calculateAverage(bigHashmap.get(listProfs.get(j)))*100.0/100.0;
			String averageString = String.valueOf(average);
			String finalString = String.format("%.2f", average).replace(",",".");
			finalTable.put(listProfs.get(j) + "\n" + finalString, (Integer) this.bigHashmap.get(listProfs.get(j)).size());
		}
		OutputTable.put(listOfSchools.get(k), finalTable);
		}
		this.OutputTable = OutputTable;

	}
			/*
			if (tempHash.getKeySet().contains(profName)) {
				Integer tempInt = tempHash.get(profName) + 1;
				tempHash.put(profName, tempInt);
			} else {
				tempHash.put(profName, 0);
			}
			myHashTableSize.put(schoolName, tempHash);
		}

		ArrayList<String> schoolNames = myHashTableSize.getKeySet();

		for (int j = 0; j < schoolNames.size(); j++) {
			for (int i = 0; i < parser.data.size(); i++) {

				String profName = parser.data.get(i)[nameIndex].toLowerCase(Locale.ROOT).stripLeading().stripLeading();
				String schoolName = parser.data.get(i)[schoolIndex].toLowerCase(Locale.ROOT).stripLeading().stripLeading();
				String profRating = parser.data.get(i)[ratingIndex];
				Integer profRatingInt = Integer.parseInt(profRating);

				if (schoolNames.get(j).equalsIgnoreCase(schoolName) && myHashTableSize.) {
					MyHashTable<String, Integer> profRatings = new MyHashTable<String, Integer>();
					//Integer newSize = myHashTableSize.get(schoolNames.get(j));


				}

				//Not sure if that calculates the right thing as the end result is supposed to be an Integer, but the example shows doubles as the stored values.

				;


			}

			int size = 0;

			ArrayList<String> listProfs = myHashTable.getKeySet();
			//ADD YOUR CODE ABOVE THIS
		}
	}}
	private Integer getAverage(String school, String profname){
		Integer averageReview = 0;
		int size = 0;
		for (int i = 0; i < parser.data.size(); i++) {
			String profName = parser.data.get(i)[nameIndex].toLowerCase().stripLeading().stripTrailing();
			String schoolName = parser.data.get(i)[schoolIndex].toLowerCase().stripLeading().stripTrailing();
			String profRating = parser.data.get(i)[ratingIndex];

			if (profName.equalsIgnoreCase(profname) && schoolName.equalsIgnoreCase(school)){
				averageReview += (Integer) Integer.parseInt(profRating);
				size ++;
			}
		}

		averageReview = averageReview/size;
		return averageReview;
	}
	Integer average = 0;


		for (int i = 0; i < parser.data.size(); i++) {

			String profName = parser.data.get(i)[nameIndex].toLowerCase().stripLeading().stripTrailing();
			String schoolName = parser.data.get(i)[schoolIndex].toLowerCase().stripLeading().stripTrailing();
			MyHashTable<String, Integer> tempHash = new MyHashTable<String, Integer>();
			tempHash.put(profName, average);
			myHashTable.put(schoolName, tempHash);
		}

		for (int i = 0; i < myHashTable.getBuckets().size(); i++) {
			//The problem here is that when we iterate over the hashtable, the values are not indexed.
			MyHashTable<String, Integer> tempHash = new MyHashTable<String, Integer>();
			String schoolName = myHashTable.getKeySet().get(i);
			for (int j = 0; j < myHashTable.getBuckets().get(i).size(); j++) {
				String profName = myHashTable.get(schoolName).getKeySet().get(j);
				tempHash = myHashTable.get(schoolName);
				tempHash.put(profName, getAverage(schoolName, profName));
			}
		}
	}}


*/}

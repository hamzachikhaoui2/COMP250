package finalproject;

import java.util.ArrayList;

public class RatingByGender extends DataAnalyzer{


	public RatingByGender(Parser p) {
		super(p);
	}


	int difficultyIndex = parser.fields.get("student_difficult");
	int ratingIndex = parser.fields.get("student_star");
	int nameIndex = parser.fields.get("professor_name");
	int schoolIndex = parser.fields.get("school_name");
	int commentIndex = parser.fields.get("comments");
	int genderIndex = parser.fields.get("gender");

	private MyHashTable<String, ArrayList<Integer>> bigHashmapFDifficult;
	private MyHashTable<String, ArrayList<Integer>> bigHashmapMDifficult;
	private MyHashTable<String, ArrayList<Integer>> bigHashmapFStar;
	private MyHashTable<String, ArrayList<Integer>> bigHashmapMStar;
	private MyHashTable<String, ArrayList<Integer>> bigHashmap;
	private MyHashTable<String, Integer> finalTable;
	private String[] keyword;
	private String gender;
	private String ratingType;
	private Integer count1 = 0;
	private Integer count2 = 0;
	private Integer count3 = 0;
	private Integer count4 = 0;
	private Integer count5 = 0;
	ArrayList<Integer> array1 = new ArrayList<Integer>();
	ArrayList<Integer> array2 = new ArrayList<Integer>();
	ArrayList<Integer> array3 = new ArrayList<Integer>();
	ArrayList<Integer> array4 = new ArrayList<Integer>();
	ArrayList<Integer> array5 = new ArrayList<Integer>();



	private static String[] splitString(String inputString){
		String[] parts = inputString.split(",");

		if (parts.length > 2){ System.out.println("the input has more than 2 inputs");}

		for (int i = 0; i < 2; i++){
			parts[i].trim().toLowerCase().stripLeading().stripTrailing();
		}
		return parts;
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		this.keyword = splitString(keyword);
		this.gender = this.keyword[0];
		if (this.keyword[1].equalsIgnoreCase("difficulty")){this.ratingType = "student_difficult";}
		if (this.keyword[1].equalsIgnoreCase("quality")){this.ratingType = "student_star";}
		this.ratingIndex = parser.fields.get(this.ratingType);
		if (this.gender.equalsIgnoreCase("M") && this.ratingType.equals("student_difficult")){
			this.bigHashmap = this.bigHashmapMDifficult;
		}
		if (this.gender.equalsIgnoreCase("F") && this.ratingType.equals("student_difficult")){
			this.bigHashmap = this.bigHashmapFDifficult;
		}
		if (this.gender.equalsIgnoreCase("M") && this.ratingType.equals("student_star")){
			this.bigHashmap = this.bigHashmapMStar;
		}
		if (this.gender.equalsIgnoreCase("F") && this.ratingType.equals("student_star")){
			this.bigHashmap = this.bigHashmapFStar;
		}
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		ArrayList<Integer> array2  = new ArrayList<Integer>();
		ArrayList<Integer> array3  = new ArrayList<Integer>();
		ArrayList<Integer> array4  = new ArrayList<Integer>();
		ArrayList<Integer> array5  = new ArrayList<Integer>();
		array1	 = this.bigHashmap.get("1");
		array2  = this.bigHashmap.get("2");
		array3  = this.bigHashmap.get("3");
		array4  = this.bigHashmap.get("4");
		array5  = this.bigHashmap.get("5");
		this.array1 = array1;
		this.array2 = array2;
		this.array3 = array3;
		this.array4 = array4;
		this.array5 = array5;
		this.count1 = array1.size();
		this.count2= array2.size();
		this.count3= array3.size();
		this.count4 = array4.size();
		this.count5 = array5.size();
		MyHashTable<String, Integer> finalTable = new MyHashTable<String, Integer>();
		finalTable.put("1", this.count1);
		finalTable.put("2", this.count2);
		finalTable.put("3", this.count3);
		finalTable.put("4", this.count4);
		finalTable.put("5", this.count5);
		this.finalTable = finalTable;
		return this.finalTable;


		//ADD YOUR CODE ABOVE THIS
	}



	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		this.count1 = 0;
		this.count2= 0;
		this.count3= 0;
		this.count4 = 0;
		this.count5 = 0;

		int difficultyIndex = parser.fields.get("student_difficult");
		int ratingIndex = parser.fields.get("student_star");
		this.nameIndex = parser.fields.get("professor_name");
		this.schoolIndex = parser.fields.get("school_name");
		this.commentIndex = parser.fields.get("comments");
		this.genderIndex = parser.fields.get("gender");



		MyHashTable<String, ArrayList<Integer>> bigHashmapFDifficult = new MyHashTable<String, ArrayList<Integer>>();
		bigHashmapFDifficult.put("1",new ArrayList<Integer>());
		bigHashmapFDifficult.put("2",new ArrayList<Integer>());
		bigHashmapFDifficult.put("3",new ArrayList<Integer>());
		bigHashmapFDifficult.put("4",new ArrayList<Integer>());
		bigHashmapFDifficult.put("5",new ArrayList<Integer>());
		this.bigHashmapFDifficult = bigHashmapFDifficult;

		for (int i = 0; i < parser.data.size(); i++) {
			if (parser.data.get(i)[genderIndex].equalsIgnoreCase("F")){
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 1.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <2.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapFDifficult.get("1").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 2.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <3.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapFDifficult.get("2").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 3.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <4.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapFDifficult.get("3").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 4.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapFDifficult.get("4").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapFDifficult.get("5").add(rating);
				}

			}

		}
		MyHashTable<String, ArrayList<Integer>> bigHashmapMDifficult = new MyHashTable<String, ArrayList<Integer>>();
		bigHashmapMDifficult.put("1",new ArrayList<Integer>());
		bigHashmapMDifficult.put("2",new ArrayList<Integer>());
		bigHashmapMDifficult.put("3",new ArrayList<Integer>());
		bigHashmapMDifficult.put("4",new ArrayList<Integer>());
		bigHashmapMDifficult.put("5",new ArrayList<Integer>());
		this.bigHashmapMDifficult = bigHashmapMDifficult;
		for (int i = 0; i < parser.data.size(); i++) {
			if (parser.data.get(i)[genderIndex].equalsIgnoreCase("M")){
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 1.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <2.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapMDifficult.get("1").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 2.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <3.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapMDifficult.get("2").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 3.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <4.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapMDifficult.get("3").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 4.0 &&
						Double.parseDouble(parser.data.get(i)[difficultyIndex]) <5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapMDifficult.get("4").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[difficultyIndex]) >= 5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[difficultyIndex]));
					this.bigHashmapMDifficult.get("5").add(rating);
				}

			}

		}

		MyHashTable<String, ArrayList<Integer>> bigHashmapFStar = new MyHashTable<String, ArrayList<Integer>>();
		bigHashmapFStar.put("1",new ArrayList<Integer>());
		bigHashmapFStar.put("2",new ArrayList<Integer>());
		bigHashmapFStar.put("3",new ArrayList<Integer>());
		bigHashmapFStar.put("4",new ArrayList<Integer>());
		bigHashmapFStar.put("5",new ArrayList<Integer>());
		this.bigHashmapFStar = bigHashmapFStar;
		for (int i = 0; i < parser.data.size(); i++) {
			if (parser.data.get(i)[genderIndex].equalsIgnoreCase("F")){
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 1.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <2.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapFStar.get("1").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 2.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <3.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapFStar.get("2").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 3.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <4.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapFStar.get("3").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 4.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapFStar.get("4").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapFStar.get("5").add(rating);
				}

			}

		}
		MyHashTable<String, ArrayList<Integer>> bigHashmapMStar = new MyHashTable<String, ArrayList<Integer>>();
		bigHashmapMStar.put("1",new ArrayList<Integer>());
		bigHashmapMStar.put("2",new ArrayList<Integer>());
		bigHashmapMStar.put("3",new ArrayList<Integer>());
		bigHashmapMStar.put("4",new ArrayList<Integer>());
		bigHashmapMStar.put("5",new ArrayList<Integer>());
		this.bigHashmapMStar = bigHashmapMStar;
		for (int i = 0; i < parser.data.size(); i++) {
			if (parser.data.get(i)[genderIndex].equalsIgnoreCase("M")){
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 1.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <2.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapMStar.get("1").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 2.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <3.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapMStar.get("2").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 3.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <4.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapMStar.get("3").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 4.0 &&
						Double.parseDouble(parser.data.get(i)[ratingIndex]) <5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapMStar.get("4").add(rating);
				}
				if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 5.0){
					Integer rating = (int) Math.floor(Double.parseDouble(parser.data.get(i)[ratingIndex]));
					this.bigHashmapMStar.get("5").add(rating);
				}

			}

		}






		//ADD YOUR CODE ABOVE THIS
	}
}


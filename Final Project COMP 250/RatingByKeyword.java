package finalproject;

import java.util.ArrayList;



public class RatingByKeyword extends DataAnalyzer {

    public RatingByKeyword(Parser p) {
        super(p);
    }

	int ratingIndex = parser.fields.get("student_star");
	int nameIndex = parser.fields.get("professor_name");
	int schoolIndex = parser.fields.get("school_name");
	int commentIndex = parser.fields.get("comments");
	int genderIndex = parser.fields.get("gender");
	private MyHashTable<String, ArrayList<String[]>> bigHashmap;
	private MyHashTable<String, Integer> finalTable;
	private String keyword;
	private Integer count1;
	private Integer count2;
	private Integer count3;
	private Integer count4;
	private Integer count5;
	ArrayList<String[]> array1 = new ArrayList<String[]>();
	ArrayList<String[]> array2 = new ArrayList<String[]>();
	ArrayList<String[]> array3 = new ArrayList<String[]>();
	ArrayList<String[]> array4 = new ArrayList<String[]>();
	ArrayList<String[]> array5 = new ArrayList<String[]>();

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		this.keyword = keyword.toLowerCase().stripTrailing().stripLeading();
		ArrayList<String[]> array1 = new ArrayList<String[]>();
		array1 = this.bigHashmap.get("1");
		this.array1 = array1;
		ArrayList<String[]> array2 = new ArrayList<String[]>();
		array2 = this.bigHashmap.get("2");
		this.array2 = array2;
		ArrayList<String[]> array3 = new ArrayList<String[]>();
		array3 = this.bigHashmap.get("3");
		this.array3 = array3;
		ArrayList<String[]> array4 = new ArrayList<String[]>();
		array4 = this.bigHashmap.get("4");
		this.array4 = array4;
		ArrayList<String[]> array5 =  new ArrayList<String[]>();
		array5 = this.bigHashmap.get("5");
		this.array5 = array5;

		for (int i = 0; i<this.array1.size(); i++){
			if (containKeyword(this.keyword.toLowerCase().stripLeading().stripTrailing(), this.array1.get(i))){
				this.count1++;

			}
		}
		for (int i = 0; i<this.array2.size(); i++){
			if (containKeyword(this.keyword.toLowerCase().stripLeading().stripTrailing(), this.array2.get(i))){
				this.count2++;

			}
		}
		for (int i = 0; i<this.array3.size(); i++){
			if (containKeyword(this.keyword.toLowerCase().stripLeading().stripTrailing(), this.array3.get(i))){
				this.count3++;

			}
		}
		for (int i = 0; i<this.array4.size(); i++){
			if (containKeyword(this.keyword.toLowerCase().stripLeading().stripTrailing(), this.array4.get(i))){
				this.count4++;

			}
		}
		for (int i = 0; i<this.array5.size(); i++){
			if (containKeyword(this.keyword.toLowerCase().stripLeading().stripTrailing(), this.array5.get(i))){
				this.count5++;

			}
		}
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
	private static String[] extractWords(String input){
		String lowercase = input.toLowerCase();
		String lettersOnly = lowercase.replaceAll("[^a-z']"," ");
		String[] words = lettersOnly.split("\\s+");
		return words;

	}

	private static boolean containKeyword(String keyword, String[] array){
		boolean keywordFound = false;
		keyword = keyword.toLowerCase().stripTrailing().stripLeading();
		for (String str : array){
			if (str.toLowerCase().contains(keyword)){
				keywordFound = true;
				break;
			}
		}
		return keywordFound;
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		this.count1 = 0;
		this.count2= 0;
		this.count3= 0;
		this.count4 = 0;
		this.count5 = 0;
		int ratingIndex = parser.fields.get("student_star");
		int nameIndex = parser.fields.get("professor_name");
		int schoolIndex = parser.fields.get("school_name");
		int commentIndex = parser.fields.get("comments");
		int genderIndex = parser.fields.get("gender");

		MyHashTable<String, ArrayList<String[]>> bigHashmap = new MyHashTable<String, ArrayList<String[]>>();
		bigHashmap.put("1",new ArrayList<String[]>());
		bigHashmap.put("2",new ArrayList<String[]>());
		bigHashmap.put("3",new ArrayList<String[]>());
		bigHashmap.put("4",new ArrayList<String[]>());
		bigHashmap.put("5",new ArrayList<String[]>());
		this.bigHashmap = bigHashmap;

		for (int i = 0; i < parser.data.size(); i++) {
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 1 &&
					Double.parseDouble(parser.data.get(i)[ratingIndex]) <2){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("1").add(comment);
			}
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 2 &&
					Double.parseDouble(parser.data.get(i)[ratingIndex]) <3){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("2").add(comment);
			}
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 3 &&
					Double.parseDouble(parser.data.get(i)[ratingIndex]) <4){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("3").add(comment);
			}
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 4 &&
					Double.parseDouble(parser.data.get(i)[ratingIndex]) <5){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("4").add(comment);
			}
			if (Double.parseDouble(parser.data.get(i)[ratingIndex]) >= 5){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("5").add(comment);
			}
		}

		}




		//ADD YOUR CODE ABOVE THIS
	}



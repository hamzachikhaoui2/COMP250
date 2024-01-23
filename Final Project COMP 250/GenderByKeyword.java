package finalproject;

import java.util.ArrayList;


public class GenderByKeyword extends DataAnalyzer {

	private MyHashTable<String, ArrayList<String[]>> bigHashmap;
	private MyHashTable<String, Integer> finalTable;

	int ratingIndex = parser.fields.get("student_difficult");
	int nameIndex = parser.fields.get("professor_name");
	int schoolIndex = parser.fields.get("school_name");
	int commentIndex = parser.fields.get("comments");
	int genderIndex = parser.fields.get("gender");
	Integer countM = 0;
	Integer countF = 0;
	Integer countX = 0;
	ArrayList<String[]> arrayM = new ArrayList<String[]>();
	ArrayList<String[]> arrayF = new ArrayList<String[]>();
	ArrayList<String[]> arrayX = new ArrayList<String[]>();


	private String keyword = "";

	public GenderByKeyword(Parser p) {
		super(p);
	}


	private static boolean containKeyword(String keyword, String[] array){
		boolean keywordFound = false;
		keyword = keyword.toLowerCase().stripTrailing().stripLeading();
		for (String str : array){
			if (str.toLowerCase().stripTrailing().stripLeading().equalsIgnoreCase(keyword)){
				keywordFound = true;
				break;
			}
		}
		return keywordFound;
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		this.keyword = keyword.toLowerCase().stripLeading().stripTrailing();
		ArrayList<String[]> arrayM = new ArrayList<String[]>();
		arrayM = this.bigHashmap.get("M");
		this.arrayM = arrayM;
		ArrayList<String[]> arrayF = new ArrayList<String[]>();
		arrayF = this.bigHashmap.get("F");
		this.arrayF =  arrayF;
		ArrayList<String[]> arrayX = new ArrayList<String[]>();
		arrayX = this.bigHashmap.get("X");
		this.arrayX = arrayX;
		for (int i = 0; i<this.arrayM.size()-1; i++){
			if (containKeyword(this.keyword.toLowerCase().stripTrailing().stripLeading(),this.arrayM.get(i))){//To get the number of times the number is repeated, easily loop
				//over the String[] and compare the string with the keyword.
				this.countM++;
			}}
		for (int i = 0; i<this.arrayF.size()-1; i++){
			if (containKeyword(this.keyword.toLowerCase().stripTrailing().stripLeading(),this.arrayF.get(i))){
				this.countF++;
			}}
		for (int i = 0; i<this.arrayX.size()-1; i++){
			if (containKeyword(this.keyword.toLowerCase().stripTrailing().stripLeading(),this.arrayX.get(i))){
				this.countX++;
			}}
		MyHashTable<String, Integer> finalTable = new MyHashTable<String, Integer>();
		finalTable.put("M", this.countM);
		finalTable.put("F", this.countF);
		finalTable.put("X", this.countX);
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

	@Override
	public void extractInformation() {
		int ratingIndex = parser.fields.get("student_difficult");
		int nameIndex = parser.fields.get("professor_name");
		int schoolIndex = parser.fields.get("school_name");
		int commentIndex = parser.fields.get("comments");
		int genderIndex = parser.fields.get("gender");
		this.countM = 0;
		this.countF = 0;
		this.countX = 0;
		MyHashTable<String, ArrayList<String[]>> bigHashmap = new MyHashTable<String, ArrayList<String[]>>();
		bigHashmap.put("M",new ArrayList<String[]>());
		bigHashmap.put("F",new ArrayList<String[]>());
		bigHashmap.put("X",new ArrayList<String[]>());
		this.bigHashmap = bigHashmap;

		// ADD YOUR CODE BELOW THIS
		for (int i = 0; i < parser.data.size(); i++) {
			if (parser.data.get(i)[genderIndex].equals("M")){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("M").add(comment);
			}
			if (parser.data.get(i)[genderIndex].equals("F")){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("F").add(comment);
			}
			if (parser.data.get(i)[genderIndex].equals("X")){
				String[] comment = extractWords(parser.data.get(i)[commentIndex]);
				this.bigHashmap.get("X").add(comment);
			}
		}

		}




		//ADD YOUR CODE ABOVE THIS
	}




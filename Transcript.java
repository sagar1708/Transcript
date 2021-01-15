package Assignment2;
import java.util.ArrayList;



import java.util.Scanner;
import java.io.*;



/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.




READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name:Sagarkumar Patel
Student Number:217637604
Course Section: B
 */


/**
 * This class generates a transcript for each student, whose information is in the text file.
 * 
 *
 */

public class Transcript {
	private ArrayList<Object> grade = new ArrayList<Object>();
	private File inputFile;
	private String outputFile;

	/**
	 * This the the constructor for Transcript class that 
	 * initializes its instance variables and call readFie private
	 * method to read the file and construct this.grade.
	 * @param inFile is the name of the input file.
	 * @param outFile is the name of the output file.
	 */
	public Transcript(String inFile, String outFile) {
		inputFile = new File(inFile);	
		outputFile = outFile;	
		grade = new ArrayList<Object>();
		this.readFile();
	}// end of Transcript constructor

	/** 
	 * This method reads a text file and add each line as 
	 * an entry of grade ArrayList.
	 * @exception It throws FileNotFoundException if the file is not found.
	 */
	private void readFile() {
		Scanner sc = null; 
		try {
			sc = new Scanner(inputFile);	
			while(sc.hasNextLine()){
				grade.add(sc.nextLine());
			}  
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}	

	} // end of readFile
	/**
	 * This method creates and returns an ArrayList, whose element is an object of class Student. 
	 * The object at each element is created by aggregating all the information.
	 * one student in the grade ArrayList of class Transcript.
	 * @return an ArrayList with aggregated Student as element.
	 */


	public ArrayList<Student> buildStudentArray() {
		ArrayList<Student> allStudents = new ArrayList<Student>();   //an ArrayList that contains all Students 
		ArrayList<String> studentID= new ArrayList<String>();   //an ArrayList that saves all student's ID


		for(int i =0;i<grade.size();i++) {

			String firstLine = grade.get(i).toString();// getting string representation of lines of txt file.
			String[] firstLineArray = firstLine.split(",");// spliting each line with commas.

			ArrayList<Double> studMarks = new ArrayList<Double>(); //list of all grades received per each line of txt file
			ArrayList<Integer>courseWeigh = new ArrayList<Integer>();//list of all wightage  per each line of txt file
			ArrayList<Assessment> studAssess = new ArrayList<Assessment>();//list of all assessment received per each line of txt file

			String studName = firstLineArray[firstLineArray.length-1]; //student's name is stored
			String courseName = firstLineArray[0]; // course name is stored in arraylist
			Double courseCred = Double.parseDouble(firstLineArray[1]);// course credit value
			String studId = firstLineArray[2]; // student ID is stored in arraylist


			if(studentID.contains(studId)) {

				//student already registered for a course so 
				//enroll him/her in another course
				//he/she has been already registerd in the arraylist.

				data(firstLineArray,studMarks,courseWeigh,studAssess);


				int studPos = studentID.indexOf(studId);//getting an index of studentID


				Student temp = allStudents.get(studPos);
				temp.addCourse(new Course(courseName,studAssess,courseCred));//course is added to particular student.

				temp.addGrade(studMarks, courseWeigh);//grade is added to particular student.
			} else {

				//new incoming student 
				//so enroll him in the arraylist. 
				//and then add the course with the neccessary weightage of the course.

				data(firstLineArray,studMarks,courseWeigh,studAssess);

				ArrayList<Course> courseDetails = new ArrayList<Course>();//new list of courses.
				courseDetails.add(new Course(courseName,studAssess,courseCred));// new course is created.
				allStudents.add(new Student(studId,studName,courseDetails));//new student is created.

				allStudents.get(allStudents.size()-1).addGrade(studMarks, courseWeigh);// add's the grade

				studentID.add(studId);//stores the student iD

			}
		}
		return allStudents;
	}
	/**
	 * This is  helper method called data for buildArrayListStudent. 
	 * @param firstLineArray - Represents the information in input file's every row.
	 * @param studMarks - An ArrayList containing grades of course.
	 * @param courseWeigh - An ArrayList containing weightage of a part. course.
	 * @param studAssess - An ArrayList that has all the assessments for a course.
	 */


	private void data(String[] firstLineArray,ArrayList<Double> studMarks,ArrayList<Integer> courseWeigh,ArrayList<Assessment> studAssess) {
		for(int i =3;i <firstLineArray.length-1;i++) {

			int weight = Integer.parseInt(firstLineArray[i].substring(1,firstLineArray[i].indexOf('(')));// getting a substring for weightage of different types of assignment of every line.
			double marks = Double.parseDouble(firstLineArray[i].substring(firstLineArray[i].indexOf('(') + 1,firstLineArray[i].indexOf(')')));// getting a substring for marks of assignment of every line.
			// condition when assignment type is exam
			if(firstLineArray[i].contains("E")) {
				studMarks.add(marks);
				courseWeigh.add(weight);
				studAssess.add(Assessment.getInstance('E', weight));

			}
			// condition when assignment type is practical.

			if(firstLineArray[i].contains("P")) {
				studMarks.add(marks);
				courseWeigh.add(weight);
				studAssess.add(Assessment.getInstance('P', weight));

			}
		}
	}
	/**
	 * This is a method which prints the Transcript to the given file (i.e. attribute of output file).
	 * @param students is the ArrayList that contains element of type Student.
	 */
	public void printTranscript(ArrayList<Student> students) {
		String output = "";

		for (int i = 0; i < students.size(); i++) {
			output = output + students.get(i).getSName() + "\t" + students.get(i).getSID() + "\n";
			output = output + "--------------------\n";
			for (int j = 0; j < students.get(i).courseTaken.size(); j++) {

				output = output + students.get(i).courseTaken.get(j).getCode() + "\t" + students.get(i).finalGrade.get(j);
				output = output + "\n";
			}
			output = output + "--------------------\n" + "GPA: " + students.get(i).weightedGPA() + "\n" + "\n";

		}
		outputFile = outputFile + output;



	}



	/**
	 * This is the main method for the class Transcript.
	 * @param args
	 */
	public static void main(String[] args) {
		Transcript transcript = new Transcript("Input.txt", "");   //transcript for Input.txt file is created
		transcript.printTranscript(transcript.buildStudentArray());   //the printTranscript method is called which gives us the output
		System.out.println(transcript.outputFile);   //output
	}

	//------------------------------------------------------------*** Student class**---------------------------------------------------------------------------------

	class Student{
		/**
		 * Represents the student's ID.
		 */
		private String studentID;
		/**
		 * Represents the Student's name.
		 */
		private String name;
		/**
		 * An ArrayList that contains all courses enrolled by the student.
		 */
		private ArrayList<Course> courseTaken;
		/**
		 * An ArrayList that contains all the course's final grades.
		 */
		private ArrayList<Double> finalGrade;
		/**
		 * This is the constructor for this class that 
		 * initializes its instance variables.
		 */
		public Student() {
			this.studentID = "";
			this.name = "";
			this.courseTaken = new ArrayList<Course>();
			this.finalGrade = new ArrayList<Double>();
		}

		/**
		 * This is the overloaded constructor for this class.
		 * @param studentID - represents student's 4 digit ID.
		 * @param name - name
		 * @param courseTaken - an arrayList of courses taken by a particular Student.
		 * */

		public Student(String studentID, String name, ArrayList<Course> courseTaken) {
			this.studentID = studentID;
			this.name = name;
			this.courseTaken = new ArrayList<Course>();

			for(Course c:courseTaken) {
				//				deep copy to avoid privacy leak
				this.courseTaken.add(new Course(c));
			}

			this.finalGrade = new ArrayList<Double>();
		}



		//	getters


		/**
		 * This is an accessor method for name.
		 * @return - the name of this Student.
		 */
		public String getSName() {
			return this.name;

		}
		/**
		 * This is an accessor method for ID.
		 * @return - the ID of this Student.
		 */
		public String getSID() {
			return this.studentID;
		}

		/**
		 * This is an accessor method for courses taken.
		 * @return - the list of courses taken by this Student.
		 */
		public ArrayList<Course> getCourseTaken(){
			ArrayList<Course> newCourseTaken = new 	ArrayList<Course>();
			for(Course c:this.courseTaken) {
				newCourseTaken.add(new Course(c));
			}
			return newCourseTaken;
		}
		//	setters

		/**
		 * This is an mutator method for name.
		 * @param - newName
		 */
		public void setSName(String newName) {
			this.name = newName;

		}
		/**
		 * This is an mutator method for ID.
		 * @param - newstudentID
		 */
		public void setSID(String newstudentID) {
			this.studentID = newstudentID;
		}

		/**
		 * This is an mutator method for courses.
		 * @param - newCourseTaken-arraylist of courses
		 * */
		public void setCourseTaken (ArrayList<Course> newCourseTaken){
			this.courseTaken = new 	ArrayList<Course>();
			for(Course c:newCourseTaken) {
				this.courseTaken.add(new Course(c));
			}
		}
		/**
		 * This method gets an array list of the grades and their weight
		 * the true value of the grade based on its weight and add it to fg attribute.
		 * In case the sum of the weight was not 100, or the sum of grade was greater 100, it throws an InvalidTotalException.
		 * @param fg - ArrayList of grades received by a student in a particular course.
		 * @param weight- ArrayList of the weightage for a particular course.
		 * @throws InvalidTotalException This is the exception that is thrown if the total weight of the assessments 
		 * does not add up to 100, or of the total grade of a student is more than 100.
		 */
		public void addGrade(ArrayList<Double> fg, ArrayList<Integer> weight) {
			double grade =0;
			double sum = 0;

			//try catch block
			//add new exception
			for(int i=0; i<weight.size(); i++) {
				sum = sum + weight.get(i);
			}
			try{

				if(sum != 100) {
					throw new InvalidTotalException("Total Weights are not equal to 100!");// throws exception if weight is not out of 100.
				}
				else {
					for(int i=0; i<fg.size(); i++) {
						grade = grade + (fg.get(i) * weight.get(i))/100;
						grade = Math.round(grade*10)/10.0;
					}
					if(grade >100) {
						throw new InvalidTotalException("Grade can not be equal to 100!");// throws exception when grade is not equal to 100.
					}

				}

			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}

			double percent = 0.0;
			for (int i = 0; i < fg.size(); i++) {
				double temp = fg.get(i) * weight.get(i) / 100;
				percent = percent + temp;
				percent = Math.round(percent * 10) / 10.0;
			}
			this.finalGrade.add(percent);
		}
		/**
		 * It is the method that computes the GPA.
		 * @return - the computed GPA.
		 */
		public double weightedGPA() {
			double temp = 0;   //represents the sum of product of weightage and grade
			double creditSum = 0;
			double Gpa= 0;

			for (int i = 0; i < this.courseTaken.size(); i++) {
				creditSum = creditSum + this.courseTaken.get(i).getCredit();
			}

			for (int i = 0; i < this.courseTaken.size(); i++) {
				double Coursecredit = this.courseTaken.get(i).getCredit();
				if (finalGrade.get(i) >= 90 && finalGrade.get(i) <= 100) {
					temp = temp + Coursecredit * 9;
				}
				else if (finalGrade.get(i) >= 80 && finalGrade.get(i) <= 89.99) {
					temp = temp + Coursecredit * 8;
				}
				else if (finalGrade.get(i) >= 75 && finalGrade.get(i) <= 79.99) {
					temp = temp +Coursecredit * 7;
				}
				else if (finalGrade.get(i) >= 70 && finalGrade.get(i) <= 74.99) {
					temp = temp + Coursecredit * 6;
				}
				else if (finalGrade.get(i) >= 65 && finalGrade.get(i) <= 69.99) {
					temp = temp + (Coursecredit * 5);
				}
				else if (finalGrade.get(i) >= 60 && finalGrade.get(i) <= 64.99) {
					temp = temp + (Coursecredit * 4);
				}
				else if (finalGrade.get(i) >= 55 && finalGrade.get(i) <= 59.99) {
					temp = temp + Coursecredit * 3;
				}
				else if (finalGrade.get(i) >= 50 && finalGrade.get(i) <= 54.99) {
					temp = temp + Coursecredit * 2;
				}
				else if (finalGrade.get(i) >= 49 && finalGrade.get(i) <= 49.99) {
					temp = temp + Coursecredit * 1;
				}
				else {
					temp = temp + 0;
				}
			}


			Gpa = temp / creditSum;
			Gpa = Math.round(Gpa * 10) / 10.0;
			return Gpa;
		}
		/**
		 * This method gets a course object as an input and add it to courseTaken.
		 * @param c - the Course object.
		 */

		public void addCourse(Course c) {
			Course newCourse = new Course(c);
			this.courseTaken.add( newCourse);
		}
		/**
		 * 
		 * @author Sagarkumar Patel
		 *
		 */


		class InvalidTotalException extends Exception{


			private static final long serialVersionUID = 1L;

			public InvalidTotalException() {

				super();
			}
			public InvalidTotalException(String message) {
				super(message);
			}

		}



	}
	//----------------------------------------------------- **Assessment class**----------------------------------------------------------------------------------------

	/**
	 * This class defines an assessment.
	 */
	static class Assessment{

		/**
		 * Represents the assessment's type i.e. P or E
		 */
		private char type;
		/**
		 * Represents the assessment's weight out of. 
		 */
		private int weight;
		/**
		 * This is the constructor for this class that initializes its instance variables. 
		 */

		private Assessment() {
			this.type = 'X';
			this.weight = 0;
		}
		/**
		 * This is the overloaded constructor for this class.
		 * @param type - represents the type of Assessment, it is either P or E
		 * @param weight - represents the weight of a course's assessment.
		 */

		private Assessment(char type,int weight) {
			this.type = type;
			this.weight = weight;

		}
		//	 	getters

		/**
		 * This is an accessor method for type of assessment.
		 * @return - the type of this assessment.
		 */

		public char getType() {
			return this.type;
		}
		/**
		 * This is an accessor method for weight of assessment.
		 * @return - the weight of this assessment.
		 */


		public int getWeight() {
			return this.weight;
		}
		//	 	setters

		/**
		 * This is an mutator method for type of assignment.
		 * @param - newType
		 */
		public void setType(char newType) {
			this.type = newType;
		}

		/**
		 * This is an mutator method for weight of assignment.
		 * @param - newWeight
		 */
		public void setweight(int newWeight) {
			this.weight = newWeight;
		}

		//	     static factory method


		/**
		 * This is a static factory method for this class.
		 * @param t- represents the type of Assessment, it is either P (practical) or E (exam).
		 * @param w - represents the weight of a course's assessment.
		 * @return - an Assessment
		 */
		public static Assessment getInstance(char t, int w) { 
			return new Assessment(t, w);
		}


		/**
		 * This is an overridden method for Object’s equals() method that returns true
		 * if all  the instance variables of two objects have the same value.
		 */
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			if(obj == null) {
				return false;
			}
			if(this.getClass() != obj.getClass()) {
				return false;

			}

			Assessment other = (Assessment) obj;
			if((this.type)!= (other.type)) {
				return false;
			}
			if ((this.weight != other.weight)) {
				return false;
			}

			return true;
		}
	}
	//----------------------------------------------------** course class**-----------------------------------------------------------------------------------------


	class Course{
		/**
		 * Represents the course's code.
		 */
		private String code;
		/**
		 * An ArrayList that contains assessments of all the courses.
		 */	

		private ArrayList<Assessment> assignment;

		/**
		 * Represents the course's credit value.
		 */
		private double credit;
		//	 	constructor
		/**
		 * This is the constructor for this class that initializes its instance variables.
		 */
		public Course() {
			this.code = "";
			this.assignment = new ArrayList<Assessment>();
			this.credit = 0.0;
		}
		//	 	custom constructor

		/**
		 * This is the overloaded constructor for this class.
		 * @param code - represents the course code.
		 * @param assignment - an ArrayList that contains Assessments.
		 * @param credit - the course's credit count.
		 */
		public Course (String code, ArrayList<Assessment> assignment,double credit) {
			this.code = code;
			this.assignment = new ArrayList<Assessment>();
			for(Assessment a : assignment) {
				//				making deep copy to avoid privacy leak.
				char temptype = a.getType();
				int tempweight = a.getWeight();
				this.assignment.add(Assessment.getInstance(temptype, tempweight));
			}
			this.credit = credit;
		}
		//	 	copy constructor
		/**
		 * This is the copy constructor for course class.
		 * @param other - represents the object of type Course.
		 */

		public Course(Course other) {
			this(other.code,other.assignment,other.credit);
		}


		/**
		 * This is an overridden method for Object’s equals() method that returns true
		 * if all the instance variables of two objects have the same value.
		 */

		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			if(obj == null) {
				return false;
			}
			if(this.getClass() != obj.getClass()) {
				return false;

			}

			Course other = (Course) obj;

			if(!this.code.equals(other.code)) {
				return false;
			}
			if(Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit)) {
				return false;
			}
			return true;
		}


		//	 	getters
		/**
		 * This is an accessor method for code.
		 * @return - the code of this course.
		 */
		public String getCode() {
			return this.code;
		}

		/**
		 * This is an accessor method for credit.
		 * @return - the course's credit.
		 */
		public double getCredit() {
			return this.credit;
		}
		/**
		 * This is an accessor method for  arraylist of assessment.
		 * @return - the course's assignment.
		 */


		public ArrayList<Assessment> getAssignment(){
			ArrayList<Assessment> assignment = new ArrayList<Assessment>();
			for(Assessment a : this.assignment) {
				assignment.add(a);
			}

			return assignment;


		}

		//	 	setters

		/**
		 * This is an mutator method for code of course.
		 * @param - newCode
		 */
		public void setCode(String newCode) {
			this.code = newCode;
		}
		/**
		 * This is an mutator method for credit of course.
		 * @param - newCredit
		 */


		public void setCredit(int newCredit) {
			this.credit = newCredit;
		}
		/**
		 * This is an mutator method for arraylist of assessments of course.
		 * @param - assignment
		 */

		public void  setAssignment(ArrayList<Assessment> newAssessment){
			this.assignment = new ArrayList<Assessment>();
			for(Assessment a : assignment) {
				this.assignment.add(a);
			}

		}
	}







}






// end of Transcript


//package Assignment2;
//
//import java.util.*;
//
//public class Student{
//	private String studentID;
//	private String name;
//	ArrayList<Course> courseTaken;
//	ArrayList<Double> finalGrade;
//
//	public Student() {
//		this.studentID = "";
//		this.name = "";
//		this.courseTaken = new ArrayList<Course>();
//		this.finalGrade = new ArrayList<Double>();
//	}
//
//	public Student(String studentID, String name, ArrayList<Course> courseTaken) {
//		this.studentID = studentID;
//		this.name = name;
//		this.courseTaken = new ArrayList<Course>();
//
//		for(Course c:courseTaken) {
//			this.courseTaken.add(c);
//		}
//		
//		this.finalGrade = new ArrayList<Double>();
//	}
//
//
//
//	//	getters
//	public String getSName() {
//		return this.name;
//
//	}
//	public String getSID() {
//		return this.studentID;
//	}
//	public ArrayList<Course> getCourseTaken(){
//		ArrayList<Course> newCourseTaken = new 	ArrayList<Course>();
//		for(Course c:this.courseTaken) {
//			newCourseTaken.add(new Course(c));
//		}
//		return newCourseTaken;
//	}
//	//	setters
//	public void setSName(String newName) {
//		this.name = newName;
//
//	}
//	public void setSID(String newstudentID) {
//		this.studentID = newstudentID;
//	}
//	public void setCourseTaken (ArrayList<Course> newCourseTaken){
//		this.courseTaken = new 	ArrayList<Course>();
//		for(Course c:newCourseTaken) {
//			this.courseTaken.add(new Course(c));
//		}
//	}
//
//	public void addGrade(ArrayList<Double> fg, ArrayList<Integer> weight) {
//		double grade =0;
//		double sum = 0;
//
//		//try catch block
//		//add new exception
//		for(int i=0; i<weight.size(); i++) {
//			sum = sum + weight.get(i);
//		}
//		try{
//
//			if(sum != 100) {
//				throw new InvalidTotalException("Total Weights are not equal to 100!");
//			}
//			else {
//				for(int i=0; i<fg.size(); i++) {
//					grade = grade + (fg.get(i) * weight.get(i))/100;
//					grade = Math.round(grade*10)/10.0;
//				}
//				if(grade >100) {
//					throw new InvalidTotalException("Grade can not be equal to 100!");
//				}
//
//			}
//
//		}
//		catch(Exception e) {
////			System.out.println("sagar");
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
//
//		double percent = 0.0;
//		for (int i = 0; i < fg.size(); i++) {
//			double temp = fg.get(i) * weight.get(i) / 100;
//			percent = percent + temp;
//			percent = Math.round(percent * 10) / 10.0;
//		}
//		this.finalGrade.add(percent);
//	}
//
//	public double weightedGPA() {
//		double temp = 0;   //represents the sum of product of weightage and grade
//		double creditSum = 0;
//		double Gpa= 0;
//
//		for (int i = 0; i < this.courseTaken.size(); i++) {
//			creditSum = creditSum + this.courseTaken.get(i).getCredit();
//		}
//
//		for (int i = 0; i < this.courseTaken.size(); i++) {
//			double Coursecredit = this.courseTaken.get(i).getCredit();
//			if (finalGrade.get(i) >= 90 && finalGrade.get(i) <= 100) {
//				temp = temp + Coursecredit * 9;
//			}
//			else if (finalGrade.get(i) >= 80 && finalGrade.get(i) <= 89.99) {
//				temp = temp + Coursecredit * 8;
//			}
//			else if (finalGrade.get(i) >= 75 && finalGrade.get(i) <= 79.99) {
//				temp = temp +Coursecredit * 7;
//			}
//			else if (finalGrade.get(i) >= 70 && finalGrade.get(i) <= 74.99) {
//				temp = temp + Coursecredit * 6;
//			}
//			else if (finalGrade.get(i) >= 65 && finalGrade.get(i) <= 69.99) {
//				temp = temp + (Coursecredit * 5);
//			}
//			else if (finalGrade.get(i) >= 60 && finalGrade.get(i) <= 64.99) {
//				temp = temp + (Coursecredit * 4);
//			}
//			else if (finalGrade.get(i) >= 55 && finalGrade.get(i) <= 59.99) {
//				temp = temp + Coursecredit * 3;
//			}
//			else if (finalGrade.get(i) >= 50 && finalGrade.get(i) <= 54.99) {
//				temp = temp + Coursecredit * 2;
//			}
//			else if (finalGrade.get(i) >= 49 && finalGrade.get(i) <= 49.99) {
//				temp = temp + Coursecredit * 1;
//			}
//			else {
//				temp = temp + 0;
//			}
//		}
//
//
//		Gpa = temp / creditSum;
//		Gpa = Math.round(Gpa * 10) / 10.0;
//		return Gpa;
//	}
//
//	public void addCourse(Course c) {
//		Course newCourse = new Course(c);
//		this.courseTaken.add( newCourse);
//	}
//
//	public ArrayList<Double> getFinalGrade(){
//		ArrayList<Double> tempFinalGrade = new ArrayList<Double>();
//		for(Double d:this.finalGrade) {
//			tempFinalGrade.add(d);
//		}
//		return tempFinalGrade;
//	}
//
//}
//

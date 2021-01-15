//package Assignment2;
//
//import java.util.ArrayList;
//
//public class Course{
//	private String code;
//	private ArrayList<Assessment> assignment;
//	private double credit;
////	constructor
//	public Course() {
//		this.code = "";
//		this.assignment = new ArrayList<Assessment>();
//		this.credit = 0.0;
//	}
////	custom constructor
//	public Course (String code, ArrayList<Assessment> assignment,double credit) {
//		this.code = code;
//		this.assignment = new ArrayList<Assessment>();
//		for(Assessment a : assignment) {
//			this.assignment.add(a);
//		}
//		this.credit = credit;
//	}
////	copy constructor
//
//	public Course(Course other) {
//		  this(other.code,other.assignment,other.credit);
//	}
//
//	@Override
//	
////	public boolean equals(Object other) {
////		Course oth = (Course) other;
////		if(this.code.equals(oth.code)) {
////			if(this.compareLists(this.assignment, oth.assignment)) {
////				if(this.credit == oth.credit) {
////					return true;
////				}
////			}
////		}
////		return false;
////	}
//	
//	public boolean equals(Object obj) {
//		if(this == obj) {
//			return true;
//		}
//		if(obj == null) {
//			return false;
//		}
//		if(this.getClass() != obj.getClass()) {
//			return false;
//			
//		}
//		
//		Course other = (Course) obj;
//	
//		if(!this.code.equals(other.code)) {
//			return false;
//		}
//		if(Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit)) {
//			return false;
//		}
//		return true;
//	}
////	
//////	Helper Method
////	private boolean compareLists(ArrayList<Assessment> a, ArrayList<Assessment> b) {
////		boolean flag = true;
////		for(int i=0; i<a.size(); i++) {
////			if(!(a.get(i).equals(b.get(i)))) {
////				flag = false;
////			}
////		}
////		return flag;
////	}
//	
//	
////	getters
//	/**
//	 * This is an accessor method for code.
//	 * @return - the code of this course.
//	 */
//	public String getCode() {
//		return this.code;
//	}
//	
//	/**
//	 * This is an accessor method for credit.
//	 * @return - the course's credit.
//	 */
//	public double getCredit() {
//		return this.credit;
//	}
//	public ArrayList<Assessment> getAssignment(){
//		ArrayList<Assessment> assignment = new ArrayList<Assessment>();
//		for(Assessment a : this.assignment) {
//			assignment.add(a);
//		}
//		
//		return assignment;
//		
//	
//}
//	
////	setters
//	public void setCode(String newCode) {
//		this.code = newCode;
//	}
//	
//	
//	public void setCredit(int newCredit) {
//		 this.credit = newCredit;
//	}
//	public void  setAssignment(ArrayList<Assessment> newAssessment){
//		this.assignment = new ArrayList<Assessment>();
//		for(Assessment a : assignment) {
//			this.assignment.add(a);
//		}
//		
//	}
//}
//

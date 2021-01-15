//package Assignment2;
//
//public class Assessment{
//	char type;
//	int weight;
//	  
//    Assessment() {
//    	this.type = ' ';
//    	this.weight = 0;
//    }
//    	
//	private Assessment(char type,int weight) {
//		this.type = type;
//		this.weight = weight;
//		
//	}
////	getters
//	public char getType() {
//		return this.type;
//	}
//	
//	public int getWeight() {
//		return this.weight;
//	}
////	setters
//	public void setType(char newType) {
//		this.type = newType;
//	}
//	public void setweight(int newWeight) {
//		this.weight = newWeight;
//	}
//	
//    
//	public static Assessment getInstance(char t, int w) { 
//		return new Assessment(t, w);
//    }
//	
//	@Override
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
//		Assessment other = (Assessment) obj;
//		if((this.type)!= (other.type)) {
//			return false;
//		}
//			if ((this.weight != other.weight)) {
//				return false;
//			}
//		
//		return true;
//	}
//}
//
//	
//

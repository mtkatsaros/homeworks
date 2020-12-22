package hw3;

public class Term implements Comparable<Object>{
	private int exponent;
	private int coefficient;
	
	Term(int coeff, int exp){
		exponent = exp;
		coefficient = coeff;
	}
	
	Term(){
		this(1,1);
	}
	
	Term(String s){
		
		
		
		int indexOfX = s.indexOf("x");
		int carrotIndex = s.indexOf("^");
		
		/*
		 * 1)empty String
		 * 2)no exponent, with x
		 * 3)coefficient of 1 with exponent
		 * 4)just a coefficient w no x
		 * 5)base case; coefficient w exponent
		 */
		//if empty
		if(s.equals("")) {
			coefficient = 0;
			exponent = 0;
		}
		
		
		
		//if exponent is 1
		else if(s.contains("x") && !s.contains("^") && indexOfX == 1 && !s.contains("")) {
			exponent = 1;
			
			if(s.charAt(0) == '+')
				coefficient = Integer.parseInt(s.substring(1, indexOfX));
			else
				coefficient = Integer.parseInt(s.substring(0, indexOfX));
		}
		
		
		
		//if exponent does not exist
		else if (indexOfX == -1) {
			if(s.charAt(0) == '+')
				coefficient = Integer.parseInt(s.substring(1));
			coefficient = Integer.parseInt(s.substring(0));
			exponent = 0;
		}
		
		
		//if coefficient is 1
		else if (indexOfX == 1) {
			coefficient = (s.charAt(0) == '+' ? 1 : -1);
			exponent = Integer.parseInt(s.substring(carrotIndex + 1));
		}
			
		
		
		//base case
		else {
			if(s.charAt(0) == '+')
				coefficient = Integer.parseInt(s.substring(1,indexOfX));
			else
				coefficient = Integer.parseInt(s.substring(0, indexOfX));
			exponent = Integer.parseInt(s.substring(carrotIndex+1));
		}
		
		
		
		/*
	    if (s.contains("x^")) {
	        String[] parts = s.split("x^");
	        if (parts[0].equals("+") || parts[0].equals("-")) 
	           parts[0] += "1";
	        this.coefficient = Integer.parseInt(parts[0]);
	        this.exponent = Integer.parseInt(parts[1]);
	    } 
	    else if (s.contains("x")) {
	        String[] parts = s.split("x");
	        if (parts[0].equals("+") || parts[0].equals("-"))
	            parts[0] += "1";
	        this.coefficient = Integer.parseInt(parts[0]);
	        this.exponent = 1;
	    } 	
	    else {
	        this.coefficient = Integer.parseInt(s);
	        this.exponent = 0;        
	    }*/
	}
	
	Term(Object o){
		this();
		Term copy;
		if(o instanceof Term) {
			copy = (Term) o;
			coefficient = copy.getCoefficient();
			exponent = copy.getExponent();
		}
	}
	
	public Term clone() {
		return new Term(coefficient, exponent);
	}
	
	public void setAll(int coeff, int exp) {
		coefficient = coeff;
		exponent = exp;
	}
	
	public void setCoefficient(int coeff) {
		coefficient = coeff;
	}
	
	public void setExponent(int exp) {
		exponent = exp;
	}
	
	public int getExponent() {
		return exponent;
	}
	
	public int getCoefficient() {
		return coefficient;
	}
	
	public String toString() {
		String temp = "";
		if(coefficient == 0)
			return "";
		//add the coefficient value if it exists
		if (coefficient > 0)
			temp += "+";
		if (coefficient == -1)
			temp += "-";
		if(coefficient == -1 && exponent == 0)
			temp += coefficient;
		if(coefficient != 1 && coefficient != -1)
			temp += coefficient;
		
		
		//add the exponent value if it exists
		if(exponent != 0)
			temp += "x";
		if (exponent != 1 && exponent != 0)
			temp += "^" + exponent;
		return temp;
	}
	
	public boolean equals(Object o) {
		return o instanceof Term && ((Term) o).coefficient == coefficient && ((Term )o).exponent == exponent;
	}
	
	@Override
	public int compareTo(Object o) {
		
		if(o instanceof Term) {
			Term term = (Term) o;
			return term.getExponent() - exponent;
		}
		
		return -1;
	}
	
	
}

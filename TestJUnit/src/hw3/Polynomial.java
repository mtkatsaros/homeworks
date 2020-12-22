package hw3;

import java.util.Collections;
import java.util.LinkedList;

public class Polynomial {
	private LinkedList<Term> termsList;
	
	
	//constructors that instantiate the linked list of terms.
	public Polynomial(Term t) {
		termsList = new LinkedList<Term>();
		termsList.add(t);
		
	}
	public Polynomial() {
		termsList = new LinkedList<Term>();
		
	}
	public Polynomial(Polynomial p) {
		termsList = new LinkedList<>();
		Term temp;
		for(int i = 0; i < termsList.size(); i++) {
			temp = new Term(p.getTerm(i));
			termsList.add(new Term(temp));
		}
		
		
	}
	
	public LinkedList<Term> getTermsList(){
		return termsList;
	}
	
	public void addTerm(Term t) {
		//loop through each term. if no matching exponent, just add
		
		boolean flag = true;
		for(int i = 0; i < getNumTerms(); i++) {
			if(termsList.size() == 0) break;
			if(termsList.get(i).getExponent() == t.getExponent()) {
				termsList.get(i).setCoefficient(termsList.get(i).getCoefficient() + t.getCoefficient());
				
				//if after adding the resultant coefficient is zero remove
				if(termsList.get(i).getCoefficient() == 0) {
					termsList.remove(i);
				}
					
				flag = false;
				break;
			}
		}
		
		//else add coefficient to whichever term shares an exponent.
		if (flag){
			termsList.add(t);
		}
		
		//organize after you add
		Collections.sort(termsList);
	}
	
	public int getNumTerms() {
		return termsList.size();
	}
	
	public Term getTerm(int i) {
		if(termsList.isEmpty())
			return new Term(0,0);
		return termsList.get(i);
	}
	
	
	public void add(Polynomial p) {
		for(int i = 0; i < p.getNumTerms(); i++)
			addTerm(p.getTerm(i));
	}
	
	public void clear() {
		termsList.clear();
	}
	
	public String toString() {
        StringBuilder temp = new StringBuilder();

        // if empty return 0
        if (getNumTerms() == 0)
            return "0";

        //add the term in each element to the string
        for (int i = 0; i < getNumTerms(); i++) {
            temp.append(termsList.get(i));
            System.out.println(termsList.get(i));
        }
        //if there is a plus in front remove it
        if (temp.charAt(0) == '+') 
        	temp = new StringBuilder(temp.substring(1));
            

        return temp.toString();

    }
	
}

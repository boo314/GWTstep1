package ba.dum.tss.shared;

import java.io.Serializable;

public class Stats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noWords = 0;
	private int noChars = 0;
	
	public Stats(){
		noWords = 0;
		noChars = 0;
	}
	
	public void setWords(int x){
		noWords = x;
	}
	
	public void setChars(int x){
		noChars = x;
	}
	
	public int getWords(){
		return noWords;
	}
	
	public int getChars(){
		return noChars;
	}
}

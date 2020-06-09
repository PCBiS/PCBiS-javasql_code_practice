package userIf;

import java.util.regex.Pattern;

public class RegEX {
	private String regExPhone, regExEmail, regExNum, regExString, regExGrade;
	
	private static RegEX instance;
	private RegEX() {
		// Constructor
		regExPhone = "(\\d{2,3})+-(\\d{3,4})+-(\\d{4})";
		regExEmail = "(a-zA-Z0-9.-_){2,15}+@[a-zA-Z0-9.-_]{2,15}+\\.[a-zA-Z]{2,6}$";
		regExNum = "^[0-9]{1,4}$";
		regExString = "^[a-zA-Z.-_]{2,30}$";
		regExGrade = "^[1-4]{1}$";
	}
	
	public static RegEX getInstance() {
		if (instance == null) {
			instance = new RegEX();
		}
		return instance;
	}
	
	public boolean phoneNumberCheck(String phoneNum) {
		boolean flag = true;
		if (!Pattern.matches(regExPhone, phoneNum)) {
			flag = false;
		}
		return flag;
	}
	
	public boolean emailCheck(String email) {
		boolean flag = true;
		if (!Pattern.matches(regExEmail, email)) {
			flag = false;
		}
		return flag;
	}
	
	public boolean isNumber(String number) {
		boolean flag = true;
		if (!Pattern.matches(regExNum, number)) {
			flag = false;
		}
		return flag;
	}
	
	public boolean isString(String text) {
		boolean flag = true;
		if (Pattern.matches(regExString, text)) {
			flag = false;
		}
		return flag;
	}
	
	public boolean grade(String grade) {
		boolean flag = false;
		if (Pattern.matches(regExGrade, grade)) {
			flag = true;
		}
		return flag;
	}
	
}

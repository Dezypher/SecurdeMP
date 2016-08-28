package security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCard {
	
	public static final int VISA = 1;
	public static final int MASTERCARD = 2;
	public static final int AMERICANEXPRESS = 3;
	public static final int DINERSCLUB = 4;
	public static final int DISCOVER = 5;
	public static final int JCB = 6;
	
	public static final float VISA_LIMIT = 100000;
	public static final float MASTERCARD_LIMIT = 250000;
	public static final float AMERICANEXPRESS_LIMIT = 25000;
	public static final float DINERSCLUB_LIMIT = 10000;
	public static final float DISCOVER_LIMIT = 50000;
	public static final float JCB_LIMIT = 25000;
	
	public static int GetCreditCardInstitution(String ccnumber) {
		//VISA
		Pattern p = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
		Matcher m = p.matcher(ccnumber);
		boolean result = m.matches();
		
		if(result)
			return VISA;
		
		//MASTERCARD
		p = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
		m = p.matcher(ccnumber);
		result = m.matches();
		
		if(result)
			return MASTERCARD;

		//AMERICANEXPRESS
		p = Pattern.compile("^3[47][0-9]{13}$");
		m = p.matcher(ccnumber);
		result = m.matches();
		
		if(result)
			return AMERICANEXPRESS;

		//DINERSCLUB
		p = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
		m = p.matcher(ccnumber);
		result = m.matches();
		
		if(result)
			return DINERSCLUB;

		//DISCOVER
		p = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
		m = p.matcher(ccnumber);
		result = m.matches();
		
		if(result)
			return DISCOVER;

		//JCB
		p = Pattern.compile("^(?:2131|1800|35{3}){11}$");
		m = p.matcher(ccnumber);
		result = m.matches();
		
		if(result)
			return JCB;
		
		//INVALID
		return 0;
	}
	
	public static boolean CheckCreditCardValidity(String ccnumber) {
		boolean result = false;

		int cardInstitution = GetCreditCardInstitution(ccnumber); 
		
		if(cardInstitution != 0)
			result = true;
		
		return result;
		
	}
	
	public static boolean CheckCreditCardLimit(String ccnumber, float price) {
		int cardInstitution = GetCreditCardInstitution(ccnumber); 
		
		switch (cardInstitution) {
			case VISA: if(price <= VISA_LIMIT)
					    return true; break;
			case MASTERCARD: if(price <= MASTERCARD_LIMIT)
			    return true; break;
			case AMERICANEXPRESS: if(price <= AMERICANEXPRESS_LIMIT)
			    return true; break;
			case DINERSCLUB: if(price <= DINERSCLUB_LIMIT)
			    return true; break;
			case DISCOVER: if(price <= DISCOVER_LIMIT)
			    return true; break;
			case JCB: if(price <= JCB_LIMIT)
			    return true; break;
		}
		
		return false;
	}
}

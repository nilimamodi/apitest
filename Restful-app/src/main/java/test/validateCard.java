package test;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Path("/validateCreditcard")  
public class validateCard {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML(@QueryParam ("cardNumber") String cardNumber)
	{
		String response="";
		String tempStr="",cardType="";

			tempStr = cardNumber.replaceAll("\\s+", "");
			cardType="Unknown";
			if(tempStr.length() > 0)
			{
				
				if(checkAmexCard(tempStr) == true)
				{
					cardType="AMEX";
				}
				else if(checkDiscover(tempStr) == true)
				{
					cardType="Discover";
				}
				else if(checkMastercard(tempStr) == true)
				{
					cardType="MasterCard";
				}
				else if(checkVisa(tempStr) == true)
				{
					cardType="Visa";
				}											
			}
			
			if(!cardType.equals("Unknown"))
			{			
				int[] ints = new int[tempStr.length()];
				for (int i = 0; i < tempStr.length(); i++) {
					ints[i] = Integer.parseInt(tempStr.substring(i, i + 1));
				}
				
				for (int i = ints.length - 2; i >= 0; i = i - 2) {
					int j = ints[i];
					System.out.println(j);
					j = j * 2;
					if (j > 9) {
						j = j % 10 + 1;
					}							
					ints[i] = j;
				}
				int sum = 0;
				for (int i = 0; i < ints.length; i++) {				
					sum += ints[i];
				}
				if (sum % 10 == 0) {					
					response = response + cardType + " :" + tempStr +  " (valid)</br>";
				} else {					
					response = response + cardType + " :" +  tempStr +  " (invalid)</br>";
				}
			}
			else
			{				
				response = response + cardType + " :" +  tempStr +  " (invalid)</br>";
			}

		return "<html>" + "<title>" + "Credit Card" +  "</title>"
				+ "<body><h1>" + response + "</h1></body>" + "</html>";
		
	}
	
	public boolean checkAmexCard(String str)
	{
		Pattern amexRegex = Pattern.compile("^3[4-7][0-9]{13}$");
		Matcher m = amexRegex.matcher(str);						
		return m.matches();
	}
	
	public boolean checkDiscover(String str)
	{
		Pattern discoverRegex = Pattern.compile("^6011[0-9]{12}$");
		Matcher m = discoverRegex.matcher(str);						
		return m.matches();	
	}
	
	public boolean checkMastercard(String str)
	{
		Pattern masterRegex = Pattern.compile("^5[1-5][0-9]{14}$");
		Matcher m = masterRegex.matcher(str);						
		return m.matches();	
	}
	
	public boolean checkVisa(String str)
	{
		Pattern visaRegex = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
		Matcher m = visaRegex.matcher(str);						
		return m.matches();	
	}
		
}


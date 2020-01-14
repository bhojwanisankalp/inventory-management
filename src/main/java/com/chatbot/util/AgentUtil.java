package com.chatbot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chatbot.model.Agents;
import com.chatbot.model.Encryption;

/*
 * Utility class for Agents related operations.
 * */
public class AgentUtil {
	
	/*
	 * To assign parameters of Agents coming from HTTP request.
	 * @param HttpServletRequest containing the request parameter.
	 * @return Agents instance after allocation of parameters.
	 * */
	public static Agents allocateAgentParameters(HttpServletRequest request,HttpSession session,Agents user) {
		if(user == null) {
		   user = new Agents();
		}
		user.setName(request.getParameter("firstname")+" "+request.getParameter("lastname"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setContact(request.getParameter("contact"));
//		StringBuilder builder = new StringBuilder("");
//		builder.append("Street: "+request.getParameter("street"));
//		builder.append("\n City: "+request.getParameter("city"));
//		builder.append("\n State: "+request.getParameter("state"));
//		builder.append("\n Zip: "+request.getParameter("zip"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZip(request.getParameter("zip"));
		try {
			Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("dob"));
			user.setDob(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEmail(request.getParameter("email"));
		String password = AgentUtil.getRandomPassword();
		user.setUnEncriptedPassword(password);
		user.setPassword(Encryption.getMd5(password));
		user.setRole("agent");
		user.setCreatedBy((Integer)session.getAttribute("userId"));
		return user;
	}
	
	/*
	 * To assign parameters of Agents coming from HTTP request.
	 * @return String instance after allocation of parameters.
	 * */
	public static String getRandomPassword() 
    { 
		int n = 8;
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
}

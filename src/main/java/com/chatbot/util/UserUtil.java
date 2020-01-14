package com.chatbot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chatbot.model.Encryption;
import com.chatbot.model.User;

public class UserUtil {
 public static User getUser(Map<String,String> userDetails) {
	 User user = new User();
	 user.setName(userDetails.get("name"));
	 user.setEmail(userDetails.get("email"));
	 user.setRole("user");
	 user.setAddress("Street : "+userDetails.get("street")+"\n City : "+userDetails.get("city")+"\n Province : "+userDetails.get("province")+"\n Postal Code :"+userDetails.get("postalCode"));
	 
	 return user;
 }
 
 public static  Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
 
 public static  Date today() {
	    final Calendar cal = Calendar.getInstance();
	    return cal.getTime();
	}
 
public static User setUser(HttpServletRequest request,User user) {
	user.setName(request.getParameter("name"));
	user.setContact(request.getParameter("contact"));
	user.setEmail(request.getParameter("email"));
	user.setRole(request.getParameter("role"));
	user.setAddress(request.getParameter("address"));
	String password = request.getParameter("password");
	if(!password.trim().equals("")) {
	user.setPassword(Encryption.getMd5(password));
	}
	try {
		Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("dob"));
		user.setDob(dob);
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return user;
	} 
}

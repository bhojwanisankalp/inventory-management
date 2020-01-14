package com.chatbot.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chatbot.model.User;

/*
 * Transactional service interface for User.
 * */
public interface UserService {
		
		/*
		 * To add user into database.
		 * @param User Hibernate entity.
		 * */
		public void addUser(User add);
		
		/*
		 * To update user parameter in database.
		 * @param User Hibernate entity
		 * */
		public void updateUser(User update);
		
		/*
		 * To get the List of all users.
		 * @return List of type User Hibernate entity.
		 * */
		public List<User> listUsers();
		
		/*
		 * To get a single User instance against id.
		 * @param Id integer value of id associated with user in database.
		 * @return User instance for given id.
		 * */
		public User getUserById(int id);
		
		/*
		 * To delete the user records from the database for given id.
		 * @param Id integer value of id associated with user in database.
		 * */
		public void removeUser(int id);
		
		/*
		 * To check the email and password provide by user to login.
		 * @param String values for email and password.
		 * @return Boolean true for correct credential.
		 * @return Boolean false for incorrect credentials.
		 * */
		public boolean authenticate(String email, String password);
		
		/*
		 * To get user instance for provided email.
		 * @param String value for email.
		 * @return User instance associated with the email.
		 * */
		public User getUserByEmail(String email);
		
		/*
		 * To check if the email associated with any user exist in database.
		 * @param String value for email.
		 * @return Boolean true for existing email and false for not getting result.
		 * */
		public boolean isEmailExist(String email);
		
		/*
		 * To check if the contact associated with any user exists in the database.
		 * @param String value for contact.
		 * @return Boolean true if the contact exist and false for the contact do not exist
		 * */
		public boolean isContactExist(String contact);
		
		/*
		 * To get List of users for user-role 'admin' in database.
		 * @return List type of User.
		 * */
		public List<User> listUsersForAdmin();
		
		/*
		 * To get List of users provided the starting index and limit of records required.
		 * @param Integer values for start index and limit for records in database.
		 * @return List type of User.
		 * */
		public List<User> listPerPage(int start,int limit);
		
		/*
		 * To get the page count by calculating the rows in database.
		 * @return Long value for the page count after calculating the rows in database.
		 * */
		public long totalPageCount();
		
		/*
		 * To get the page count by calculating the rows in database for user-role 'admin'.
		 * @return Long value for the page count after calculating the rows in database..
		 * */
		public long totalPageCountForAdmin();
		
		/*
		 *  To get the List of users for the user-role as 'admin'.
		 *  @param Integer values of start index and limit of records.
		 *  @return List of type User.
		 * */
		public List<User> listPerPageUsersForAdmin(int start,int limit);
		
		/*
		 *  To get the List of users.
		 *  @param String values for user-name or user-email or user-contact
		 *  @return List of type User.
		 * */
		public List<User> searchFilter(String nameOrEmailOrContact);
		
		/*
		 *  To get the row count of users.
		 *  @return Long value of row count for entity type User.
		 * */
		public long newUserCount();
		
		/*
		 * To get the row count of users for the user-role as 'admin'.
		 * @return Long value of row count for entity type User.
		 * */
		public long newUserCountForAdmin();
		
		/*
		 *  To get the List of users added for user-createdAt compare to yesterday or today's date.
		 *  @return List of type User.
		 * */
		public List<User> listOfNewUsers();
		

		/*
		 *  To get the List of users added yesterday and today for the user-role admin.
		 *  @return List of type User.
		 * */
		public List<User> listOfNewUsersForAdmin();
		

		/*
		 *  To get the List of users for the user-role 'admin'.
		 *  @param String values for user-name or user-email or user-contact
		 *  @return List of type User.
		 * */
		public List<User> serachforAdmin(String nameOrEmailOrContact);
		
		/*
		 * To get the row count of orders for the orders-orderStatus as '1'(Pending).
		 * @return Long value of row count for entity type Orders.
		 * */
		public long paymentPending();
		
		/*
		 * To get the row count of orders for the orders-createdAt compare to yesterday or today's date.
		 * @return Long value of row count for entity type User.
		 * */
		public long newOrders();
		
		/*
		 * To get the row count of inactive products.
		 * @return Long value of row counts for entity Product.
		 * */
		public long productsOutOfStock();
}


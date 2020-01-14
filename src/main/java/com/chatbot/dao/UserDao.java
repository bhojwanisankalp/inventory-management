package com.chatbot.dao;

import java.util.List;

import com.chatbot.model.User;

public interface UserDao {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public boolean authenticate(String email, String password);
	public User getUserByEmail(String email);
	public boolean isEmailExist(String email);
	public boolean isContactExist(String contact);
	public List<User> listUsersForAdmin();
	public List<User> listPerPage(int start,int limit);
	public long totalPageCount();
	public long totalPageCountForAdmin();
	public List<User> listPerPageUsersForAdmin(int start,int limit);
	public List<User> searchFilter(String nameOrEmailOrContact);
	public long newUserCount();
	public long newUserCountForAdmin();
	public List<User> listOfNewUsers();
	public List<User> listOfNewUsersForAdmin();
	public List<User> serachforAdmin(String nameOrEmailOrContact);
	public long paymentPending();
	public long newOrders();
	public long productsOutOfStock();
}


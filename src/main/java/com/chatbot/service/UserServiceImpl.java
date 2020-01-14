package com.chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatbot.dao.UserDao;
import com.chatbot.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void addUser(User p) {
		this.userDao.addUser(p);
	}

	@Override
	@Transactional
	public void updateUser(User p) {
		this.userDao.updateUser(p);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDao.removeUser(id);
	}
	
	@Override
	@Transactional
	public boolean authenticate(String email, String password) {
		return this.userDao.authenticate(email,password);
	}
	
	@Override
	@Transactional	
	public User getUserByEmail(String email) {
		return this.userDao.getUserByEmail(email);
	}
	
	@Override
	@Transactional
	public boolean isEmailExist(String email) {
		return this.userDao.isEmailExist(email);
	}
	@Override
	@Transactional
	public boolean isContactExist(String contact) {
		return this.userDao.isContactExist(contact);
	}
	@Override
	@Transactional
	public List<User> listUsersForAdmin(){
		return this.userDao.listUsersForAdmin();
	}
	
	@Override
	@Transactional
	public List<User> listPerPage(int start,int limit){
		return this.userDao.listPerPage(start, limit);
	}
	
	@Override
	@Transactional
	public long totalPageCount() {
		return this.userDao.totalPageCount();
	}
	
	@Override
	@Transactional
	public long totalPageCountForAdmin() {
		return this.userDao.totalPageCountForAdmin();
	}
	@Override
	@Transactional
	public List<User> listPerPageUsersForAdmin(int start,int limit){
		return this.userDao.listPerPageUsersForAdmin(start,limit);
	}
	@Override
	@Transactional
	public List<User> searchFilter(String nameOrEmailOrContact){
	 return this.userDao.searchFilter(nameOrEmailOrContact);	
	}
	@Override
	@Transactional
	public long newUserCount() {
		return this.userDao.newUserCount();
	}
	@Override
	@Transactional
	public List<User> listOfNewUsers(){
		return this.userDao.listOfNewUsers();
	}
	@Override
	@Transactional
	public List<User> listOfNewUsersForAdmin(){
		return this.userDao.listOfNewUsersForAdmin();
	}
	@Override
	@Transactional
	public long newUserCountForAdmin() {
		return this.userDao.newUserCountForAdmin();
	}
	@Override
	@Transactional
	public List<User> serachforAdmin(String nameOrEmailOrContact){
		return this.userDao.serachforAdmin(nameOrEmailOrContact);
	}
	@Override
	@Transactional
	public long paymentPending() {
		return this.userDao.paymentPending();
	}
	@Override
	@Transactional
	public long newOrders() {
		return this.userDao.newOrders();
	}
	@Override
	@Transactional
	public long productsOutOfStock() {
		return this.userDao.productsOutOfStock();
	}
}
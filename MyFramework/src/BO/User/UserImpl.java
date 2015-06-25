package BO.User;

import java.util.ArrayList;
import java.util.List;

import Model.dao.UserDAO;

public class UserImpl implements UserBO {
	public UserDAO userDAO;
	
	

	public UserDAO getUserDAO() {
		return userDAO;
	}



	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	@Override
	public List login(String userName, String password) {
		ArrayList<String> propertyNames = new ArrayList<String>();
		propertyNames.add("username");
		propertyNames.add("password");
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(userName);
		values.add(password);
//		List user = userDAO.findAll();
     	List user = userDAO.findByProperty(propertyNames, values);
		return user;
	}

}

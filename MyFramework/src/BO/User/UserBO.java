package BO.User;

import java.util.List;

import Model.po.User;

public interface UserBO {
	public List login(String userName,String password);
}

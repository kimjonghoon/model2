package net.java_school.user;

import java.util.List;

public class UserService {
	private UserDao dao = UserDao.getInstance();

	public void addUser(User user) {
		dao.insert(user);
	}

	public User login(String email, String passwd) {
		return dao.login(email, passwd);
	}

	public void editMyAccount(User user) {
		dao.update(user);
	}

	public void changeMyPasswd(String email, String newPasswd) {
		dao.updatePasswd(email, newPasswd);
	}

	public User getUser(String email) {
		return dao.selectOne(email);
	}
	
	public List<String> getRoles(String email) {
		return dao.selectRoles(email);
	}
	
	//admin menu
	public int getTotalUser(String search) {
		return dao.selectCountOfUsers(search);
	}
	
	//admin menu
	public List<UserInfo> getUserInfos(String search, int startRecord, int endRecord) {
		return dao.selectListOfUserInfos(search, startRecord, endRecord);
	}
	
	//admin menu
	public void addAuthority(String email, String authority) {
		dao.insertAuthority(email, authority);
	}
	
	public void removeUser(String email) {
		dao.deleteAllAuthorities(email);
		dao.delete(email);
	}
	
	//admin menu
	public void removeAuthority(String email, String authority) {
		dao.deleteAuthority(email, authority);
	}
	
	//admin menu
	public void editUserAccount(User user) {
		dao.update(user);
	}

	//for admin
	public void changeUserPasswd(String email, String passwd) {
		dao.updatePasswd(email, passwd);
	}
	
	public int authentication(String email, String passwd) {
		return dao.authentication(email, passwd);
	}

}
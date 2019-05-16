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
		String email = user.getEmail();
		String passwd = user.getPasswd();
		int  check = dao.authentication(email, passwd);
		if (check == 1) {
			dao.update(user);
		}
	}

	//Change My Password
	public void changeMyPasswd(String email, String currentPasswd, String newPasswd) {
		int check = dao.authentication(email, currentPasswd);
		if (check == 1) {
			dao.updatePasswd(email, newPasswd);
		}
	}

	//Bye
	public void bye(String email, String passwd) {
		int check = dao.authentication(email, passwd);
		if (check == 1) {
			dao.deleteAllAuthorities(email);
			dao.delete(email);
		}
	}

	public User getUser(String email) {
		return dao.selectOne(email);
	}
	
	public List<String> getRoles(String email) {
		return dao.selectRoles(email);
	}
	
	//for admin
	public int getTotalUser(String search) {
		return dao.selectCountOfUsers(search);
	}
	
	//for admin
	public List<UserInfo> getUserInfos(String search, int startRecord, int endRecord) {
		return dao.selectListOfUserInfos(search, startRecord, endRecord);
	}
	
	//for admin
	public void addAuthority(String email, String authority) {
		dao.insertAuthority(email, authority);
	}
	
	//for admin
	public void deleteUser(String email) {
		dao.deleteAllAuthorities(email);
		dao.delete(email);
	}
	
	//for admin
	public void removeAuthority(String email, String authority) {
		dao.deleteAuthority(email, authority);
	}
	
	//for admin
	public void editUserAccount(User user) {
		dao.update(user);
	}

	//for admin
	public void changeUserPasswd(String email, String passwd) {
		dao.updatePasswd(email, passwd);
	}
	

}
package net.java_school.user;

import java.util.List;

import net.java_school.commons.WebContants;

public class UserInfo {
	private User user;
	private List<String> roles;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public boolean isAdmin() {
		for (String role : roles) {
			if (role.equals(WebContants.ROLE_ADMIN)) {
				return true;
			}
		}
		return false;
	}
	
}
package net.java_school.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.User;
import net.java_school.user.UserInfo;
import net.java_school.user.UserService;

public class EditUserAccountFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null || userInfo.isAdmin() == false) {
			throw new AuthenticationException("You are not an administrator.");
		}
		
		String email = req.getParameter("email");
		
		UserService service = new UserService();
		User user = service.getUser(email);
		List<String> roles = service.getRoles(email);
		
		req.setAttribute("user", user);
		req.setAttribute("roles", roles);
		
		forward.setView("/admin/editAccount.jsp");

		return forward;
	}

}

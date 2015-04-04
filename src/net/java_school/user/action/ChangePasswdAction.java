package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.User;
import net.java_school.user.UserService;

public class ChangePasswdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		if (user == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}
		
		//currentPasswd(현재 비밀번호),newPasswd(변경 비밀번호)
		String currentPasswd = req.getParameter("currentPasswd");
		String newPasswd = req.getParameter("newPasswd");
		String email = user.getEmail();
		
		UserService service = new UserService();
		int check = service.changePasswd(currentPasswd, newPasswd, email);
		
		if (check < 1) {
			session.removeAttribute(WebContants.USER_KEY);
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		
		forward.setView("changePasswd_confirm.do");
		forward.setRedirect(true);
		
		return forward;
	}

}

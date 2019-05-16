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
import net.java_school.user.UserInfo;
import net.java_school.user.UserService;

public class EditMyAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		//전달되는 파라미터 name, mobile, passwd
		String name = req.getParameter("name");
		String mobile = req.getParameter("mobile");
		String passwd = req.getParameter("passwd");

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		String email = userInfo.getUser().getEmail();

		UserService service = new UserService();

		if (service.login(email, passwd) == null) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		User user = userInfo.getUser();
		user.setPasswd(passwd);
		user.setName(name);
		user.setMobile(mobile);

		service.editMyAccount(user);
		forward.setView("/users/changePasswd.do");
		forward.setRedirect(true);

		return forward;
	}

}
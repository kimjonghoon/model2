package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.user.User;
import net.java_school.user.UserService;

public class SignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		String name = req.getParameter("name");
		String mobile = req.getParameter("mobile");

		User user = new User(email, passwd, name, mobile);

		UserService service = new UserService();
		service.addUser(user);

		forward.setView("welcome.do");
		forward.setRedirect(true);

		return forward;
	}

}

package net.java_school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.action.AddCommentAction;
import net.java_school.board.action.BoardAction;
import net.java_school.board.action.CreateBoardAction;
import net.java_school.board.action.DeleteAction;
import net.java_school.board.action.DeleteAttachFileAction;
import net.java_school.board.action.DeleteCommentAction;
import net.java_school.board.action.EditBoardAction;
import net.java_school.board.action.ListAction;
import net.java_school.board.action.ModifyAction;
import net.java_school.board.action.ModifyFormAction;
import net.java_school.board.action.UpdateCommentAction;
import net.java_school.board.action.ViewAction;
import net.java_school.board.action.WriteAction;
import net.java_school.board.action.WriteFormAction;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.action.AddAuthorityAction;
import net.java_school.user.action.ByeAction;
import net.java_school.user.action.ByeFormAction;
import net.java_school.user.action.ChangeMyPasswordAction;
import net.java_school.user.action.ChangeMyPasswordFormAction;
import net.java_school.user.action.ChangeUserPasswdAction;
import net.java_school.user.action.DelAuthorityAction;
import net.java_school.user.action.DelUserAction;
import net.java_school.user.action.EditMyAccountAction;
import net.java_school.user.action.EditMyAccountFormAction;
import net.java_school.user.action.EditUserAccountAction;
import net.java_school.user.action.EditUserAccountFormAction;
import net.java_school.user.action.LoginAction;
import net.java_school.user.action.LogoutAction;
import net.java_school.user.action.SignUpAction;
import net.java_school.user.action.UserListAction;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = -904167838225890202L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(contextPath.length());

		String view = null;
		Action action = null;
		ActionForward forward = null;

		try {
			if (command.equals("/bbs/list.do") && req.getMethod().equals("GET")) {
				action = new ListAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/view.do") && req.getMethod().equals("GET")) {
				action = new ViewAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/write.do") && req.getMethod().equals("GET")) {
				action = new WriteFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/write.do") && req.getMethod().equals("POST")) {
				action = new WriteAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/addComment.do") && req.getMethod().equals("POST")) {
				action = new AddCommentAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/updateComment.do") && req.getMethod().equals("POST")) {
				action = new UpdateCommentAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/deleteComment.do") && req.getMethod().equals("POST")) {
				action = new DeleteCommentAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/deleteAttachFile.do") && req.getMethod().equals("POST")) {
				action = new DeleteAttachFileAction();
				forward = action.execute(req, resp);                
			} else if (command.equals("/bbs/modify.do") && req.getMethod().equals("GET")) {
				action = new ModifyFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/modify.do") && req.getMethod().equals("POST")) {
				action = new ModifyAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/bbs/del.do") && req.getMethod().equals("POST")) {
				action = new DeleteAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/changePasswd.do") && req.getMethod().equals("GET")) {
				action = new ChangeMyPasswordFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/changePasswd.do") && req.getMethod().equals("POST")) {
				action = new ChangeMyPasswordAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/changePasswd_confirm.do") && req.getMethod().equals("GET")) {
				forward = new ActionForward();
				forward.setView("/users/changePasswd_confirm.jsp");             
			} else if (command.equals("/users/signUp.do") && req.getMethod().equals("GET")) {
				forward = new ActionForward();
				forward.setView("/users/signUp.jsp");
			} else if (command.equals("/users/signUp.do") && req.getMethod().equals("POST")) {
				action = new SignUpAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/welcome.do") && req.getMethod().equals("GET")) {
				forward = new ActionForward();
				forward.setView("/users/welcome.jsp");              
			} else if (command.equals("/users/editAccount.do") && req.getMethod().equals("GET")) {
				action = new EditMyAccountFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/editAccount.do") && req.getMethod().equals("POST")) {
				action = new EditMyAccountAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/bye.do") && req.getMethod().equals("GET")) {
				action = new ByeFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/bye.do") && req.getMethod().equals("POST")) {
				action = new ByeAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/login.do") && req.getMethod().equals("GET")) {
				forward = new ActionForward();
				forward.setView("/users/login.jsp");
			} else if (command.equals("/users/login.do") && req.getMethod().equals("POST")) {
				action = new LoginAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/users/logout.do") && req.getMethod().equals("GET")) {
				action = new LogoutAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/list.do") && req.getMethod().equals("GET")) {
				action = new UserListAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/delUser.do") && req.getMethod().equals("POST")) {
				action = new DelUserAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/editAccount.do") && req.getMethod().equals("GET")) {
				action = new EditUserAccountFormAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/editAccount.do") && req.getMethod().equals("POST")) {
				action = new EditUserAccountAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/delAuthority.do") && req.getMethod().equals("GET")) {
				action = new DelAuthorityAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/addAuthority.do") && req.getMethod().equals("POST")) {
				action = new AddAuthorityAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/changePasswd.do") && req.getMethod().equals("POST")) {
				action = new ChangeUserPasswdAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/boards.do") && req.getMethod().equals("GET")) {
				action = new BoardAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/editBoard.do") && req.getMethod().equals("POST")) {
				action = new EditBoardAction();
				forward = action.execute(req, resp);
			} else if (command.equals("/admin/createBoard.do") && req.getMethod().equals("POST")) {
				action = new CreateBoardAction();
				forward = action.execute(req, resp);
			}

			view = forward.getView();

			if (forward.isRedirect() == false) {
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher(view);
				rd.forward(req, resp);
			} else {
				resp.sendRedirect(view);
			}
		} catch (AuthenticationException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}

	}

}
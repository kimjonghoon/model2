package net.java_school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.action.AddCommentsAction;
import net.java_school.board.action.DeleteAction;
import net.java_school.board.action.DeleteAttachFileAction;
import net.java_school.board.action.DeleteCommentsAction;
import net.java_school.board.action.ListAction;
import net.java_school.board.action.ModifyAction;
import net.java_school.board.action.ModifyFormAction;
import net.java_school.board.action.UpdateCommentsAction;
import net.java_school.board.action.ViewAction;
import net.java_school.board.action.WriteAction;
import net.java_school.board.action.WriteFormAction;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.action.ByeAction;
import net.java_school.user.action.ByeFormAction;
import net.java_school.user.action.ChangePasswdAction;
import net.java_school.user.action.ChangePasswdFormAction;
import net.java_school.user.action.EditAccountAction;
import net.java_school.user.action.EditAccountFormAction;
import net.java_school.user.action.LoginAction;
import net.java_school.user.action.LogoutAction;
import net.java_school.user.action.SignUpAction;

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
            if (command.equals("/bbs/list.do")) {
                action = new ListAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/view.do")) {
                action = new ViewAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/write_form.do")) {
                action = new WriteFormAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/write_proc.do")) {
                action = new WriteAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/addComments_proc.do")) {
                action = new AddCommentsAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/updateComments_proc.do")) {
                action = new UpdateCommentsAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/deleteComments_proc.do")) {
                action = new DeleteCommentsAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/deleteAttachFile_proc.do")) {
                action = new DeleteAttachFileAction();
                forward = action.execute(req, resp);                
            } else if (command.equals("/bbs/modify_form.do")) {
                action = new ModifyFormAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/modify_proc.do")) {
                action = new ModifyAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/bbs/del_proc.do")) {
                action = new DeleteAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/changePasswd.do")) {
                action = new ChangePasswdFormAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/changePasswd_proc.do")) {
                action = new ChangePasswdAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/changePasswd_confirm.do")) {
                forward = new ActionForward(); //단순 포워딩
                forward.setView("/users/changePasswd_confirm.jsp");             
            } else if (command.equals("/users/signUp.do")) {
                forward = new ActionForward(); //단순 포워딩일때는 액션이 필요없다.
                forward.setView("/users/signUp.jsp");
            } else if (command.equals("/users/signUp_proc.do")) {
                action = new SignUpAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/welcome.do")) {
                forward = new ActionForward(); //단순 포워딩
                forward.setView("/users/welcome.jsp");              
            } else if (command.equals("/users/editAccount.do")) {
                action = new EditAccountFormAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/editAccount_proc.do")) {
                action = new EditAccountAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/bye.do")) {
                action = new ByeFormAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/bye_proc.do")) {
                action = new ByeAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/login.do")) {
                forward = new ActionForward();//단순 포워딩
                forward.setView("/users/login.jsp");
            } else if (command.equals("/users/login_proc.do")) {
                action = new LoginAction();
                forward = action.execute(req, resp);
            } else if (command.equals("/users/logout_proc.do")) {
                action = new LogoutAction();
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
            //컨트롤러에 인증실패 익셉션이 전달되면, 컨트롤러는 에러를 컨테이너에 전달한다.
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        

	}

}

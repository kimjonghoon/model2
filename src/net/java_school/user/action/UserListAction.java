package net.java_school.user.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.NumbersForPaging;
import net.java_school.commons.Paginator;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;
import net.java_school.user.UserService;

public class UserListAction extends Paginator implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null) {
			String url = req.getRequestURI();
			String query = req.getQueryString();
			if (query != null) url += "?" + query;
			url = URLEncoder.encode(url, "UTF-8");
			forward.setView("/users/login.do?url=" + url);
			forward.setRedirect(true);

			return forward;
		}
		
		if (userInfo.isAdmin() == false) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		int page = Integer.parseInt(req.getParameter("page"));
		String search = req.getParameter("search");

		UserService service = new UserService();

		int numPerPage = 20;
		int pagePerBlock = 10;

		int total = service.getTotalUser(search);
		NumbersForPaging numbers = this.getNumbersForPaging(total, page, numPerPage, pagePerBlock);

		int startRecord = (page - 1) * numPerPage + 1;
		int endRecord = page * numPerPage;

		List<UserInfo> list = service.getUserInfos(search, startRecord, endRecord);
		
		int listItemNo = numbers.getListItemNo();
		int prevPage = numbers.getPrevBlock();
		int firstPage = numbers.getFirstPage();
		int lastPage = numbers.getLastPage();
		int nextPage = numbers.getNextBlock();
		int totalPage = numbers.getTotalPage();

		req.setAttribute("list", list);
		req.setAttribute("listItemNo", listItemNo);
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("firstPage", firstPage);
		req.setAttribute("lastPage", lastPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("totalPage", totalPage);

		forward.setView("/admin/list.jsp");

		return forward;
	}

}
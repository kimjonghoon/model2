package net.java_school.board;

import java.util.List;

public class BoardService {
	private BoardDao dao = BoardDao.getInstance();

	public BoardService() {}

	public List<Article> getArticleList(String boardCd, String searchWord, int startRecord, int endRecord) {
		return dao.selectListOfArticles(boardCd, searchWord, startRecord, endRecord);
	}

	public int getTotalRecord(String boardCd, String searchWord) {
		return dao.selectCountOfArticles(boardCd, searchWord);
	}

	public void addArticle(Article article, AttachFile attachFile) {
		dao.insert(article, attachFile);
	}

	public void modifyArticle(Article article, AttachFile attachFile) {
		dao.update(article, attachFile);
	}

	public void removeArticle(int articleNo) {
		dao.delete(articleNo);
	}

	public void increaseHit(int articleNo, String ip, String yearMonthDayHour){
		dao.insertOneViews(articleNo, ip, yearMonthDayHour);
	}

	public int getTotalViews(int articleNo) {
		return dao.selectCountOfViews(articleNo);
	}
	
	public Article getArticle(int articleNo) {
		return dao.selectOne(articleNo);
	}

	public Article getNextArticle(int articleNo, String boardCd, String searchWord) {
		return dao.selectNextOne(articleNo, boardCd, searchWord);
	}

	public Article getPrevArticle(int articleNo, String boardCd, String searchWord) {
		return dao.selectPrevOne(articleNo, boardCd, searchWord);
	}

	public List<AttachFile> getAttachFileList(int articleNo) {
		return dao.selectListOfAttachFiles(articleNo);
	}

	public void removeAttachFile(int attachFileNo) {
		dao.deleteFile(attachFileNo);
	}

	public String getBoardNm(String boardCd) {
		return dao.selectOneBoardName(boardCd);
	}

	public void addComment(Comment comment) {
		dao.insertComment(comment);
	}

	public void modifyComment(Comment comment) {
		dao.updateComment(comment);
	}

	public void removeComment(int commentNo) {
		dao.deleteComment(commentNo);
	}

	public List<Comment> getCommentList(int articleNo) {
		return dao.selectListOfComments(articleNo);
	}

	public AttachFile getAttachFile(int attachFileNo) {
		return dao.selectOneAttachFile(attachFileNo);
	}

	public Comment getComment(int commentNo) {
		return dao.selectOneComment(commentNo);
	}
	
	public List<Board> getAllBoard() {
		return dao.selectAllBoard();
	}
	
}
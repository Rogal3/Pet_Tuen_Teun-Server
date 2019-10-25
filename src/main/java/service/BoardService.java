package service;

import java.util.ArrayList;

import manager.BoardManager;
import manager.CommentManager;
import model.Board;
import model.Comment;

public class BoardService {
	private BoardManager boardManager;
	private CommentManager commentManager;
	public BoardService(BoardManager boardManager, CommentManager commentManager) {
		super();
		this.boardManager = boardManager;
		this.commentManager = commentManager;
	}
	public BoardManager getBoardManager() {
		return boardManager;
	}
	public void setBoardManager(BoardManager boardManager) {
		this.boardManager = boardManager;
	}
	public CommentManager getCommentManager() {
		return commentManager;
	}
	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}
	public ArrayList<Board> searchBoardByType(String type){
		return this.boardManager.searchType(type);
	}
	public ArrayList<Board> searchBoardByWriter(String writer){
		return this.searchBoardByWriter(writer);
	}
	/**
	 * 타입과 작성자로 검색인데 일단 보류!
	 * @param writer 작성자
	 * @param type 타입
	 * @return
	 */
	public ArrayList<Board> searchBoardByWrterOfType(String writer,String type){
		return this.boardManager.searchBoardByWriter(writer);
	}
	public ArrayList<Board> searchBoardByTitle(String title,String type){
		ArrayList<Board> emp=new ArrayList<Board>();
		if("total".equals(type)) {
			ArrayList<String> types=searchBoardType();
			
			for(int i=0;i<types.size();++i) {
				ArrayList<Board> boards=this.boardManager.searchType(types.get(i));
				for(int j=0;j<boards.size();++j) {
					emp.add(boards.get(j));
				}
			}
		}else {
			ArrayList<Board> boards=this.boardManager.searchType(type);
			for(int i=0;i<boards.size();++i) {
				emp.add(boards.get(i));
			}
		}
		return emp;
	}
	public ArrayList<String> searchBoardType(){
		return this.boardManager.searchTypes();
	}
	public byte modifyBoard(String boardID,Board board) {
		return this.boardManager.addBoard(board);
	}
	public byte deleteBoard(String boardID) {
		return this.boardManager.deleteBoard(boardID);
	}
	public byte addBoard(Board board) {
		return this.boardManager.addBoard(board);
	}
	public byte addComment(String boardID,Comment comment) {
		return this.commentManager.addComment(boardID, comment);
	}
	public byte deleteComment(String boardID,Comment comment) {
		return this.commentManager.deleteComment(boardID,comment.getWriteTime());
	}
	public byte modifyComment(String boardID,Comment comment){
		return this.commentManager.modifyComment(boardID, comment);
	}
}

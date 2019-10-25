package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Board;

public class BoardManager {
	private HashMap<String,Board> boards;
	private HashMap<String,ArrayList<Board>> boardTypeList;
	
	public BoardManager() {
		super();
		this.boards=new HashMap<String,Board>();
		this.boardTypeList=new HashMap<String,ArrayList<Board>>();
	}
	public BoardManager(HashMap<String, Board> boards, HashMap<String, ArrayList<Board>> boardTypeList) {
		super();
		this.boards = boards;
		this.boardTypeList = boardTypeList;
	}
	public HashMap<String, Board> getBoards() {
		return boards;
	}
	public void setBoards(HashMap<String, Board> boards) {
		this.boards = boards;
	}
	public HashMap<String, ArrayList<Board>> getBoardTypeList() {
		return boardTypeList;
	}
	public void setBoardTypeList(HashMap<String, ArrayList<Board>> boardTypeList) {
		this.boardTypeList = boardTypeList;
	}
	public ArrayList<Board> searchType(String type){
		if(this.boardTypeList.get(type)==null)return null;
		return this.boardTypeList.get(type);
	}
	public Board searchBoard(String boardID) {
		return this.boards.get(boardID);
	}
	public HashMap<String,ArrayList<Board>> searchBoardByTitle(String title){
		HashMap<String,ArrayList<Board>> emp=new HashMap<String,ArrayList<Board>>();
		

		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		
		while(iter.hasNext()) {
			ArrayList<Board> temp=new ArrayList<Board>();
			String key=iter.next();
			ArrayList<Board> putting=this.boardTypeList.get(key);
			
			for(int i=0;i<putting.size();++i){
				if(putting.get(i).getTitle().contains(title)) {
					temp.add(putting.get(i));
				}
			}
			emp.put(key,temp);
		}
		return emp;
	}
	public ArrayList<Board> searchBoardByWriter(String writer){
		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		ArrayList<Board> emp=new ArrayList<Board>();
		while(iter.hasNext()) {
			Board temp=this.boards.get(iter.next());
			
				if(temp.getWriter().equals(writer)) {
					emp.add(temp);
				}
		}
		return emp;
	}
	public byte modifyBoard(String boardID,Board board) {
		if(searchBoard(boardID)==null)return 0;
		
		this.boards.put(boardID, board);
		
		return 1;
	}
	public byte deleteBoard(String boardID) {
		if(searchBoard(boardID)==null)return 0;
		this.boards.remove(boardID);
		return 1;
	}
	public byte addBoard(Board board) {
		if(searchBoard(board.getBoardID())!=null)return 0;
		this.boards.put(board.getBoardID(),board);
		this.boardTypeList.get(board.getType()).add(board);
		return 1;
	}
	
}

package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Board;

public class BoardManager {
	/**
	 * 인기 f , 최신 n, 내동물 m, 팁 t
	 */
	private HashMap<String,Board> boards;
	private HashMap<String,ArrayList<Board>> boardTypeList;
	
	public BoardManager() {
		super();
		this.boards=new HashMap<String,Board>();
		this.boardTypeList=new HashMap<String,ArrayList<Board>>();
		
		ArrayList<Board> f1=new ArrayList<Board>();
		f1.add(new Board("f1","favorite","title","aaa","asdf","19.10.30"));
		f1.add(new Board("f2","favorite","title1","bbb","asdf","19.10.30"));
		f1.add(new Board("f3","favorite","title2","ccc","asdf","19.10.30"));
		f1.add(new Board("f4","favorite","title3","ccc","asdf","19.10.30"));
		f1.add(new Board("f5","favorite","title4","aaa","asdf","19.10.30"));
		
		ArrayList<Board> n1=new ArrayList<Board>();
		f1.add(new Board("n1","new","title","aaa","asdf","19.10.30"));
		f1.add(new Board("n2","new","title","bbb","asdf","19.10.30"));
		f1.add(new Board("n3","new","title","ccc","asdf","19.10.30"));
		f1.add(new Board("n4","new","title","ccc","asdf","19.10.30"));
		f1.add(new Board("n5","new","title","aaa","asdf","19.10.30"));
		
		ArrayList<Board> m1=new ArrayList<Board>();
		f1.add(new Board("m1","my","title","aaa","asdf","19.10.30"));
		f1.add(new Board("m2","my","title","bbb","asdf","19.10.30"));
		f1.add(new Board("m3","my","title","ccc","asdf","19.10.30"));
		f1.add(new Board("m4","my","title","ccc","asdf","19.10.30"));
		f1.add(new Board("m5","my","title","aaa","asdf","19.10.30"));
		
		ArrayList<Board> t1=new ArrayList<Board>();
		f1.add(new Board("t1","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Board("t2","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Board("t3","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Board("t4","tip","title","ccc","asdf","19.10.30"));
		f1.add(new Board("t5","tip","title","aaa","asdf","19.10.30"));
		
		for(int i=0;i<5;i++) {
			this.boards.put(f1.get(i).getBoardID(),f1.get(i));
			this.boards.put(n1.get(i).getBoardID(),n1.get(i));
			this.boards.put(m1.get(i).getBoardID(),m1.get(i));
			this.boards.put(t1.get(i).getBoardID(),t1.get(i));
		}
		this.boardTypeList.put("favorite", f1);
		this.boardTypeList.put("new", n1);
		this.boardTypeList.put("my", m1);
		this.boardTypeList.put("tip", t1);
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
	public ArrayList<String> searchTypes(){
		ArrayList<String> emp=new ArrayList<String>();
		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		while(iter.hasNext()) {
			emp.add(iter.next());
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

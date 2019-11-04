package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import VO.GetPostListVO;
import manager.CommentManager;
import manager.MemberManager;
import manager.PostManager;
import model.Comment;
import model.Member;
import model.Post;

@Controller
public class PostController {
	@Autowired
	private PostManager postManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private CommentManager commentManager;
	
	@RequestMapping(value="/getPostDetail.do")
	@ResponseBody//일단 이거 넣어야 string이나 json으로 전달되네
	public JSONObject getPostDetail(@RequestParam("postID")String id) {
		System.out.println("post id : "+id);
		Post item=postManager.searchById(id);
		Member writer=memberManager.searchMemberByID(item.getWriter());
		ArrayList<Comment> comments=commentManager.searchCommentList(id);
		//System.out.println(item);
		if(comments == null) {
			comments=new ArrayList<Comment>();
		}
		JSONObject postInfo=new JSONObject();
		postInfo.put("title",item.getTitle());
		postInfo.put("writerNickname",writer.getName());
		postInfo.put("contents",item.getContent());
		postInfo.put("date",item.getWriteTime());
		postInfo.put("commentCnt",comments.size());
		JSONArray ary=new JSONArray();
		//리스트추가
		for(int i=0;i<comments.size();i++) {
			JSONObject temp=new JSONObject();
			Comment cItem=comments.get(i);
			temp.put("commentID","임의id");
			temp.put("contents",cItem.getContent());
			temp.put("writerNickName", memberManager.searchMemberByID(cItem.getWriter()));
			temp.put("date", cItem.getWriteTime());
			ary.add(temp);
		}
		postInfo.put("comments", ary);
		return postInfo;
	}
	
	@RequestMapping(value="/getPostList.do")
	@ResponseBody//일단 이거 넣어야 string이나 json으로 전달되네
	public JSONObject getPostList(GetPostListVO vo){
		System.out.println(vo.getMemberID());
		//게시글 불러오는 로직 넣자.
		JSONObject result=new JSONObject();
		JSONArray list=new JSONArray();
		List<Post> posts=postManager.getPosts();
		System.out.println(posts.size());
		for(int i=posts.size()-1;i>=0;i--) {
			Post temp=posts.get(i);
			//System.out.println(temp.toString());
			JSONObject item=new JSONObject();
			Member writer=memberManager.searchMemberByID(temp.getWriter());
			if(writer ==null) {
				System.out.println("hello?");
			}
			//System.out.println("Writer :"+writer);
			//System.out.println("temp : "+temp.toString());
			item.put("id",temp.getId());
			item.put("title",temp.getTitle() );
			item.put("writerNickname",writer.getName());
			item.put("date", temp.getWriteTime());
			item.put("viewCnt",i+10);//나중에 제대로 넣자
			ArrayList<Comment> comments=commentManager.searchCommentList(temp.getId());
			int commentCnt=0;
			if(comments!=null)
				commentCnt=comments.size();
			item.put("commentCnt",commentCnt);
			item.put("imgUrl", "temp");//나중에 고려하자..
			list.add(item);
		}
		result.put("list",list);
		return result;
	}
	
}

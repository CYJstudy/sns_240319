package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;

	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) { // model은 일반 controller에서만 사용가능
		
		// 로그인 여부 - 로그인을 하지 않은 상태여도 오류가 발생하지는 않지만, 글쓰기는 불가
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		// db insert
		commentBO.addComment(userId, postId, content);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId) {		
		
		// db delete
		commentBO.deleteComment(commentId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}

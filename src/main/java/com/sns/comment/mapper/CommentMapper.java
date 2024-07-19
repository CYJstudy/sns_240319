package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	public int insertComment( // int로 return해도 bo에서 안쓰면 상관없음
			@Param("userId") int userId, 
			@Param("postId") int postId,
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
}

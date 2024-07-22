package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// 댓글 하나를 가지고 있는 클래스
@ToString
@Data
public class CommentView {

	// 댓글 1개
	private Comment comment;
	
	// 댓글쓴이
	private UserEntity user;
}

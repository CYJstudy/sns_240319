package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// 화면에 보여지게 하기 위한 view용 객체
// 글 하나와 매핑됨
@ToString
@Data
public class CardView {
	// 글 한개 전체표시
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 댓글 N개
	private List<CommentView> commentList;
	
	// 좋아요 N개

	// 좋아요를 눌렀는지 여부
}

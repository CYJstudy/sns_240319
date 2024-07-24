package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	// post별 좋아요 갯수
	// input: postId    output: int(좋아요개수)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	// 내가 좋아요를 눌렀는지 여부
	// input: postId, userId      output: int(특정유저의 좋아요개수)
	public int getLikeCountByPostIdUserId(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
	}
	
	// 좋아요 채울지 여부
	// input:postId(필수), userId(로그인/비로그인)   output: boolean(채울지 여부)
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		// 비로그인이면 DB 조회 없이 하트 채우지 않음
		if (userId == null) {
			return false;
		}
		// 로그인이면 1. 행이있으면(1) true  2. 없으면(0) false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 1 ? true : false;
	}
	
	// input:postId, userId     output:X
	public void likeToggle(int postId, int userId) {
		// 조회
//		Like like = likeMapper.selectLikeByPostIdUserId(postId, userId);
		int count = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		
		// 값이 있는지 여부에 따라 => 삭제 or 추가
//		if (like != null) {		
//			likeMapper.deleteLike(postId, userId);
//		} else {			
//			likeMapper.insertLike(postId, userId);
//		}
		if (count > 0) {
			// 있으면 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가
			likeMapper.insertLike(postId, userId);
		}
		
	}
}

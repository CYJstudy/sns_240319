package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.domain.Like;
import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	// post별 좋아요 갯수
	public int getLikeCount(int postId) {
		return likeMapper.selectlikeCountByPostId(postId);
	}
	
	// 내가 좋아요를 눌렀는지 여부
	public int filledLike(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdUserId(postId, userId);
	}
	
	// input:postId, userId     output:X
	public void likeToggle(int postId, int userId) {
		// 조회
//		Like like = likeMapper.selectLikeByPostIdUserId(postId, userId);
		int count = likeMapper.selectLikeCountByPostIdUserId(postId, userId);
		
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

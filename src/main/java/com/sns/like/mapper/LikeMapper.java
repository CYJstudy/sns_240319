package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

//	public Like selectLikeByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	// 좋아요 개수 - 검증을 해서, 하기 합친쿼리로 바꿔 준 이후에 삭제
//	public int selectlikeCountByPostId(
//			@Param("postId") int postId);
	
	// likeToggle - 검증을 해서, 하기 합친쿼리로 바꿔 준 이후에 삭제
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId") int postId, 
//			@Param("userId") int userId);
	
	// 상기 두개의 count 쿼리를 하나로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
}

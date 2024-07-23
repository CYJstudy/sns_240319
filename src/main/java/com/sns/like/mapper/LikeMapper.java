package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {

//	public Like selectLikeByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	public int selectlikeCountByPostId(
			@Param("postId") int postId);
	
	public int selectLikeCountByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}

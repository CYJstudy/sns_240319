package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: X
	// output: List<PostEntity>
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	// input: 파라미터들
	// output: PostEntity
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		// 업로드 후, imagePath를 받아옴
		String imagePath = null;
		
		if (file != null) {
			
			imagePath = fileManagerService.uploadFile(file, userLoginId);
		}
		
		return postRepository.save(PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	@Transactional
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 기존post를 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		if (post == null) {
			log.error("[delete post] postId:{}, userId:{}", postId, userId);
			return;
		}
		// 이미지삭제
		if (post.getImagePath() != null) {
			fileManagerService.deleteFile(post.getImagePath());
		}
		
		// post 삭제
		postRepository.delete(post);
		// 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);	
		
	}
}

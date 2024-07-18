package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	// input: X
	// output: List<PostEntity>
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	// input: 파라미터들
	// output: PostEntity
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		// 업로드 후, imagePath를 받아옴
		String imagePath = 
		
		return postRepository.save(PostEntity.builder()
				.userId(userId)
				.userLoginId(userLoginId)
				.content(content)
				.file(file)
				.build());
	}
}

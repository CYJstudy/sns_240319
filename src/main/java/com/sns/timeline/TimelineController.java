package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@Controller
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		
		// db 조회 - 글목록
//		List<PostEntity> postList = postBO.getPostEntityList();
//		List<Comment>
		List<CardView> cardViewList = timelineBO.generateCardViewList();
		
		// 모델에 담기
//		model.addAttribute("postList", postList);
		model.addAttribute("cardViewList", cardViewList);
		
		return "timeline/timeline";
	}
	
}

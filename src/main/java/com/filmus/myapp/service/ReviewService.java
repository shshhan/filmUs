package com.filmus.myapp.service;

import java.util.List;

import com.filmus.myapp.domain.ReviewCommentDTO;
import com.filmus.myapp.domain.ReviewCommentVO;
import com.filmus.myapp.domain.ReviewDTO;
import com.filmus.myapp.domain.ReviewVO;

public interface ReviewService {
	
	public abstract ReviewVO reviewDetail(Integer rno, Integer filmId);
	
	public abstract int modReview(ReviewDTO dto);
	
	public abstract int delReview(Integer rno, Integer filmId);

	public abstract int rcCreate(ReviewCommentDTO dto);
	
	public abstract int rcModify(ReviewCommentDTO dto);
	
	public abstract int rcDelete(Integer rcno, Integer rno);
	
	public abstract List<ReviewCommentVO> rcList(Integer rno);

}//end interface

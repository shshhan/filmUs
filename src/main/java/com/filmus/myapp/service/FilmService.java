package com.filmus.myapp.service;

import java.util.List;
import java.util.Map;

import com.filmus.myapp.domain.Criteria;
import com.filmus.myapp.domain.FilmReviewDTO;
import com.filmus.myapp.domain.FilmReviewVO;
import com.filmus.myapp.domain.UserVO;

public interface FilmService {

	public abstract Map<String, Object> showFilmInfo(String filmId);		//영화 정보 가져오기
	
	public abstract List<FilmReviewVO> getReviewList(Map<String, Object> params);	//리뷰 목록 가져오기

	public abstract int getTotal(Map<String, Object> params);		//총 레코드 수 가져오기

	public abstract int registerReview(FilmReviewDTO dto);	//리뷰 등록하기
	
}//end interface

package com.filmus.myapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.filmus.myapp.domain.Criteria;
import com.filmus.myapp.domain.FilmDetailVO;
import com.filmus.myapp.domain.FilmPeopleVO;
import com.filmus.myapp.domain.FilmReviewDTO;
import com.filmus.myapp.domain.FilmReviewVO;

public interface FilmMapper {

	public abstract FilmDetailVO getFilmDetail(String filmId);
	
	public abstract List<FilmPeopleVO> getFilmPeople(String filmId);
	
	public abstract List<FilmReviewVO> getReviews(Map<String, Object> params);

	public abstract int getTotalCount(Map<String, Object> params);
	
	public abstract int insertReview(FilmReviewDTO dto);
	
}//end interface

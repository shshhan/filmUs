package com.filmus.myapp.mapper;

import java.util.List;

import com.filmus.myapp.domain.FilmDetailVO;
import com.filmus.myapp.domain.FilmPeopleVO;
import com.filmus.myapp.domain.FilmReviewDTO;
import com.filmus.myapp.domain.FilmReviewVO;

public interface FilmMapper {

	public abstract FilmDetailVO getFilmDetail(String filmId);
	
	public abstract List<FilmPeopleVO> getFilmPeople(String filmId);

	public abstract int insertReview(FilmReviewDTO dto);
	
	public abstract List<FilmReviewVO> getReviews(String filmId);
}

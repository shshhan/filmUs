package com.filmus.myapp.service;

import java.util.List;

import com.filmus.myapp.domain.FilmusFilmsGenreVO;
import com.filmus.myapp.domain.FilmusFilmsVO;
import com.filmus.myapp.domain.FilmusReviewsVO;


public interface MainService {
	
	public abstract List<FilmusFilmsVO> getMainFilms();
	
	public abstract List<FilmusReviewsVO> getMainReviews();
	
	public abstract List<FilmusFilmsGenreVO> getFilmsByGenre(String genre);
	
	public abstract List<FilmusFilmsGenreVO> getAllFilms();

} //end interface

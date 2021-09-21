package com.filmus.myapp.service;

import java.util.Map;

import com.filmus.myapp.domain.FilmReviewDTO;

public interface FilmService {

	public abstract Map<String, Object> showFilmInfo(String filmId);

	public abstract int registerReview(FilmReviewDTO dto);

}//end interface

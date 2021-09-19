package com.filmus.myapp.mapper;

import java.util.List;

import com.filmus.myapp.domain.FilmDetailVO;
import com.filmus.myapp.domain.FilmPeopleVO;

public interface FilmMapper {

	public abstract FilmDetailVO getFilmDetail(String filmId);
	
	public abstract List<FilmPeopleVO> getFilmPeople(String filmId);
}

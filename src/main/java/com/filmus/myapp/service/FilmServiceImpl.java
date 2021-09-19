package com.filmus.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmus.myapp.domain.FilmDetailVO;
import com.filmus.myapp.domain.FilmPeopleVO;
import com.filmus.myapp.mapper.FilmMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class FilmServiceImpl 
		implements FilmService, InitializingBean {
	
	@Setter(onMethod_=@Autowired)
	private FilmMapper mapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		Objects.requireNonNull(this.mapper);
	}//afterPropertiesSet
	
	
	@Override
	public Map<String, Object> showFilmInfo(String filmId) {
		log.debug("showFilmInfo({}) invoked", filmId);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		FilmDetailVO filmDetail = this.mapper.getFilmDetail(filmId);
		log.info(filmDetail);
		resultMap.put("filmDetail", filmDetail);
		
		List<FilmPeopleVO> filmPeople = this.mapper.getFilmPeople(filmId);
		filmPeople.forEach(log::info);
		
		List<FilmPeopleVO> director = new ArrayList<>();
		List<FilmPeopleVO> cast = new ArrayList<>();
		
		filmPeople.forEach(t -> {
			if(t.getCreditOrder() != null) {
				cast.add(t);
			} else {
				director.add(t);
			}//if-else
		});
		
//		log.info("director>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		director.forEach(log::info);
//		log.info("cast>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		cast.forEach(log::info);
		
		filmPeople.clear();
		
		resultMap.put("director", director);
		resultMap.put("cast", cast);
		
		return resultMap;
	}//showFilmInfo
	
}//end class

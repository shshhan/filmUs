package com.filmus.myapp.domain;

import lombok.Value;

@Value
public class FilmDetailVO {

	private String originalTitle;
	private String releaseDate;
	private String plot;
	private String posterPath;
	private String backdropPath;
}

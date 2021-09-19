<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FILMEE | FILM MEETING</title>
	<link rel="icon" href="/resources/img/favicon_noback.ico" type="image/x-icon">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

	<script>
  	 	$(function() {
  	 		$(window).scroll(function() {
  	 		   	 			
				var scrollTop = $(document).scrollTop();
				if (scrollTop < 490) {
				scrollTop = 490;
				}
				$("aside div").stop();
				$("aside div").animate( { "top" : scrollTop });
			});
  	 		
  	 		
		//     $("#cast_more").on('click', function(e) {				
		// 		console.log("cast_more clicked.");

		// 		$("#cast_more").attr("display", "none");
		// 		<c:forEach items="${cast}" var="cast" begin="29">
		// 			<span>
		// 				<a href="/search/people/${cast.peopleId}">${cast.enName}</a>
		// 			</span>
		// 		</c:forEach>
		// 	})//onclick cast_more_span

		});//jquery
	</script>

	<style>
		#container {
		width: 998px;
		margin: 0 auto;
		font-family:'Florencesans SC Exp'; 
		word-break: keep-all;

		background: linear-gradient(to top, white, white 60%, transparent),
					url("https://www.themoviedb.org/t/p/original${filmDetail.backdropPath}");
		background-repeat: no-repeat;
		background-size: contain;
		}
		#gap{
			height:300px;
		}

		#film_header{
			margin : 35px 0px 25px 0px;		
		}

		#film_header p{
			font-size: 17px;
		}

		#film_header strong{
			font-size : 35px;
			font-weight: bold;
		}

		#plot{
			margin-top:60px;
			width: 650px;
		}

		#people{
			margin-top:50px;
			width: 650px;
		}

		#people p{
			font-size: 23px;
		}

		#people div span{
			display: inline;
			margin-right: 15px;
			background-color: rgb(224, 224, 224);
		}

		#cast{
			margin-top: 20px;
		}

		aside{
			margin-top: 300px;
			text-align: center;
		}

		aside div{
			position : absolute;
			top: 490px;
		}
		aside img{
			margin-top: 10px;
			width: 228px;
			border: 1px solid white;
		}

		aside p{
			margin-top : 10px;
			text-align: center;
			font-size: 18px;
			font-weight: bold;
		}
	</style>

</head>

<body>
	<%@include file="/resources/html/header.jsp" %>
	<div id="container">
		<div id="gap"></div>
		<div class="row">
			<section class="col-9">
				<div id="film_header">
					<p>
						<strong>${filmDetail.originalTitle}</strong>
						[${filmDetail.releaseDate}]
					</p>
				</div>	
				<div id="plot">
					${filmDetail.plot}
				</div>
				<div id="people">
					<div id="director">
						<p>DIRECTOR</p>
						<c:forEach items="${director}" var="director">
							<span>
								<a href="/search/people/${director.peopleId}">${director.enName}</a>
							</span>
						</c:forEach>
					</div>
					<div id="cast">
						<p>CAST</p>
						<c:forEach items="${cast}" var="cast">
							<span>
								<a href="/search/people/${cast.peopleId}">${cast.enName}</a>
							</span>
						</c:forEach>
					</div>
				</div>
			</section>
			<aside class="col-3">
				<div>
					<img src="https://www.themoviedb.org/t/p/original${filmDetail.posterPath}" alt="filmPoster" id="filmPoster">
					<p>${filmDetail.originalTitle}</p>
				</div>
			</aside>
		</div>
	</div>
	<%@include file="/resources/html/footer.jsp" %>

</body>

</html>
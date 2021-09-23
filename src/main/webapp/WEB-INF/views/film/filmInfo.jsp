<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FILMUS</title>
	<link rel="icon" href="/resources/img/favicon_noback.ico" type="image/x-icon">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/star-rating.css">
	<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css'/>

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

		.film_header{
			margin : 35px 0px 25px 0px;		
		}

		.film_header p{
			font-size: 17px;
		}

		.film_header strong{
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
			font-size: 15px;
		}

		.sub_header{
			font-size: 23px;
			text-decoration: underline;
			margin-bottom: 10px;
		}

		#people div span{
			display: inline;
			margin-right: 15px;
			background-color: rgb(224, 224, 224);
		}

		#cast{
			margin-top: 20px;
		}

		#reviews{
			margin-top: 70px;
			margin-bottom : 20px;
			width: 650px;
			font-size: 14px;
		}

		#reviews strong{
			font-size : 18px;
			font-weight: bold;
		}

		#review_content{
			overflow: hidden;
        	text-overflow: ellipsis;
			word-wrap: break-word;
        	display: -webkit-box;
        	-webkit-line-clamp: 5 ;
       		-webkit-box-orient: vertical;  	
		}

		#profile_photo{
			text-align: center;
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
		#register_review_btn{
			display: none;
		}

		.inner-star::before {
            color: pink;            
        }
        
        .outer-star {
            position: relative;
            display: inline-block;
            color: rgb(177, 175, 175);
        }
        
        .inner-star {
            position: absolute;
            left: 0;
            top: 0;
            width: 0%;
            overflow: hidden;
            white-space: nowrap;
        }
        
        .outer-star::before, .inner-star::before {
            content: '\f005 \f005 \f005 \f005 \f005';
            font-family: 'Font Awesome 5 free';
            font-weight: 900;
			font-size: 150%;
        }
	</style>

</head>

<body>
	<%@include file="/resources/html/header.jsp" %>
	<div id="container">
		<div id="gap"></div>
		<div class="row">
			<section class="col-9">
				<div class="film_header">
					<p>
						<strong>${filmDetail.originalTitle}</strong>
						[${filmDetail.releaseDate}]
					</p>
					<div class='RatingStar'>
						<div class='RatingScore'>
							<div class='outer-star'>
								<div class='inner-star' style='width: ${filmDetail.width}%'></div>
							</div>
							${filmDetail.avgRate} / 5.0
						</div>
					</div>
				</div>	
				<div id="plot">
					${filmDetail.plot}
				</div>
				<div id="people">
					<div id="director">
						<p class="sub_header">DIRECTOR</p>
						<c:forEach items="${director}" var="director">
							<span>
								<a href="/search/people/${director.peopleId}">${director.enName}</a>
							</span>
						</c:forEach>
					</div>
					<div id="cast">
						<p class="sub_header">CAST</p>
						<c:forEach items="${cast}" var="cast">
							<span>
								<a href="/search/people/${cast.peopleId}">${cast.enName}</a>
							</span>
						</c:forEach>
					</div>
				</div>
				<div id="reviews">
					<p class="sub_header">REVIEW</p>
						<c:forEach items="${reviews}" var="review">
							<div class="row">
								<div class="col-2" id="profile_photo">
									<img class="rounded-circle" src="https://younghoon.s3.ap-northeast-2.amazonaws.com/${review.profilePhotoPath}" alt="profile_photo" width="50px" height="50px">
								</div>
								<div class="col-10">
									<p>reviewd by <strong><a href="http://localhost:8090/mypage/main?userid=${review.writer}">${review.nickname}</a></strong>
									<!-- <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${review.insertTs}"/>
									<c:if test="${review.updateTs!=null}">
										<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${review.updateTs}"/>
									</c:if> -->
									<img src='/resources/img/fullheart.png' style='height:25px;'>${review.likeCnt}</a>    
								  	<a href='/film/${filmDetail.filmId}/review/${review.rno}'><img src='/resources/img/reply.png' style='height:25px;'>${review.commentCnt}</a></p>								  
									<div class='RatingStar'>
										<div class='RatingScore'>
											<div class='outer-star'>
												<div class='inner-star' style='width: ${review.width}%'></div>
											</div>
											<p>${review.rate} / 5.0</p>                               
										</div>
									</div>
									<div id="review_content">
										${review.content}
									</div>
									<hr>


									
									
								</div>
							</div>
						</c:forEach>
				</div>
			</section>
			<aside class="col-3">
				<div>
					<img src="https://www.themoviedb.org/t/p/original${filmDetail.posterPath}" alt="filmPoster" id="filmPoster">
					<p>${filmDetail.originalTitle}</p>
					<button type="button" class="btn btn-outline-dark" id="register_review_btn" data-bs-toggle="modal" data-bs-target="#reg_review">Register a review</button>
				</div>
			</aside>
		</div>
	</div>

	<!-- reg_review Modal -->
	<div class="modal fade input_modal" id="reg_review" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="staticBackdropLabel"><b>Register a review</b></h2>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body row">
					<div class="col-4">
						<img src="https://www.themoviedb.org/t/p/original${filmDetail.posterPath}" width="230px" alt="filmPoster" id="filmPoster">
					</div>
					<div class="col-8 film_header">
						<p>
							<strong>${filmDetail.originalTitle}</strong>
							[${filmDetail.releaseDate}]
						</p>
						
						<form action="/film/regReview" method="POST">
							<input type="hidden" name="filmId" value="${filmDetail.filmId}">
							<input type="hidden" name="writer" value="${__LOGIN__.userId}">
							<!-- <input type="hidden" name="currPage" value="${cri.currPage}">
							<input type="hidden" name="amount" value="${cri.amount}">
							<input type="hidden" name="pagesPerPage" value="${cri.pagesPerPage}"> -->

							<div class='rating-wrap'>
								<fieldset class='rating'>
									<input type='radio' id='star5' name='rating' value='5' /><label for='star5' class='full'></label>
									<input type='radio' id='star4.5' name='rating' value='4.5' /><label for='star4.5' class='half'></label>
									<input type='radio' id='star4' name='rating' value='4' /><label for='star4' class='full'></label>
									<input type='radio' id='star3.5' name='rating' value='3.5' /><label for='star3.5' class='half'></label>
									<input type='radio' id='star3' name='rating' value='3' /><label for='star3' class='full'></label>
									<input type='radio' id='star2.5' name='rating' value='2.5' /><label for='star2.5' class='half'></label>
									<input type='radio' id='star2' name='rating' value='2' /><label for='star2' class='full'></label>
									<input type='radio' id='star1.5' name='rating' value='1.5' /><label for='star1.5' class='half'></label>
									<input type='radio' id='star1' name='rating' value='1' /><label for='star1' class='full'></label>
									<input type='radio' id='star0.5' name='rating' value='0.5' /><label for='star0.5' class='half'></label>
								</fieldset>
							</div>
							<p id='rating-value'></p>					
							<script src='/resources//js/star-rating.js'></script>
							<div class="mb-3">
								<textarea id="review_content" class="form-control" placeholder="write your review here..." name="content" maxlength="2048" style="height: 180px;"></textarea>
							</div>
							<div class="form-check">
								<label class="form-check-label" for="spoiler"><b>this review contains spoilers</b></label>
								<input class="form-check-input" type="checkbox" name="spoiler" id="spoiler">
							</div>
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">REGISTER</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/resources/html/footer.jsp" %>

	
</body>

</html>
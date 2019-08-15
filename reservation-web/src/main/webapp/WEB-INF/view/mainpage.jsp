<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="resources/css/style.css" rel="stylesheet" />

<style>

#promotion {
	margin-top: 40px;
	position: relative;
	max-width: 414px;
	height: 177px;
	overflow: hidden;
}

#images {
	position: absolute;
	height: 177px;
	width: 5000px;
	transition: transform 1s;
}

.image {
	width: 414px;
	float: left;
	display: block;
}
</style>
</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./myreservation.html" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="./bookinglogin.html" class="btn_my"> <span
					class="viewReservation" title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<hr>
		<div class="event">
			<div id="promotion">
				<div id="images">
					<img src="resources/img/2_th_3.png" class="image"> <img
						src="resources/img/4_th_11.png" class="image"> <img
						src="resources/img/20_th_50.png" class="image"> <img
						src="resources/img/21_th_53.png" class="image">
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active">
							<span>전체리스트</span>
					</a></li>
					<li class="item" data-category="1"><a class="anchor"> <span>전시</span>
					</a></li>
					<li class="item" data-category="2"><a class="anchor"> <span>뮤지컬</span>
					</a></li>
					<li class="item" data-category="3"><a class="anchor"> <span>콘서트</span>
					</a></li>
					<li class="item" data-category="4"><a class="anchor"> <span>클래식</span>
					</a></li>
					<li class="item" data-category="5"><a class="anchor"> <span>연극</span>
					</a></li>
					<!-- li class="item" data-category="7">
                        <a class="anchor"> <span>클래스</span> </a>
                    </li>
                    <li class="item" data-category="8">
                        <a class="anchor"> <span>체험</span> </a>
                    </li>
                    <li class="item" data-category="9">
                        <a class="anchor last"> <span>키즈</span> </a>
                    </li -->
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink">10개</span> 있습니다
				</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul class="lst_event_box">
						<li class="item"><a href="detail.html" class="item_book">
								<div class="item_preview">
									<img alt="뮤지컬 드림걸즈(DREAMGIRLS) 최초 내한" class="img_thumb"
										src="https://ssl.phinf.net/naverbooking/20170303_271/1488514705030TuUK4_JPEG/17%B5%E5%B8%B2%B0%C9%C1%EE_%B8%DE%C0%CE%C6%F7%BD%BA%C5%CD_%C3%D6%C1%BE.jpg?type=l591_945">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>뮤지컬 드림걸즈(DREAMGIRLS) 최초 내한</span> <small class="sm">샤롯데
											씨어터</small>
									</h4>
									<p class="event_txt_dsc">뮤지컬 드림걸즈(DREAMGIRLS) 최초 내한! 1981년
										미국 브로드웨이 초연 당시 전미(全美) 흥행 돌풍 2006년 비욘세, 제이미 폭스, 제니퍼 허드슨 등 초호화
										캐스팅 영화로 재탄생 2009년 브로드웨이와 국내 제작진이 힘을 합쳐 세계 최초로 선보인 월드 프리미어
										2017년 뮤지컬 드림걸즈 최초 내한 드림걸즈의 진가를 느낄 수 있는 최고의 무대가 온다! 오리지널 소울에 압도
										당하다! &lt;드림걸즈&gt;의 실제 주인공들인 &lt;슈프림스(Supremes)&gt;의 본 고장 최고의
										팀이 뭉쳤다 파워풀한 가창력과 최고의 기량을 지닌 미국 브로드웨이 배우들이 최초로 내한! ＇Listen＇,
										＇One Night Only＇, ＇Move＇, ＇To The Bad Side＇ 등 환상적인 뮤지컬 넘버를
										오리지널 흑인 R&amp;B, 소울, 그루브로 선보이며 관객의 눈과 귀를 사로잡는다! 당신의 놓치면 안 될 가장
										화려한 꿈의 무대 드림걸즈! ALL 아프리칸 아메리칸 캐스트가 전하는 음악의 진수! 브로드웨이의 새로운 트렌드의
										중심, 차원이 다른 사운드! 주역부터 앙상블까지 브로드웨이 아프리칸 아메리칸(African-American)
										배우로 구성! 진정한 R&amp;B 오리지널 소울을 그대로 전하는 2017년 최고의 기대작!</p>
								</div>
						</a></li>
						<li class="item"><a href="detail.html" class="item_book">
								<div class="item_preview">
									<img alt="뮤지컬 더 데빌 The DEVIL" class="img_thumb"
										src="https://ssl.phinf.net/naverbooking/20170111_195/1484112206459rGQGI_JPEG/%BB%F3%BC%BC%C6%E4%C0%CC%C1%F6_%BB%F3%B4%DC%28%B3%D7%C0%CC%B9%F6_%BF%B9%BE%E0%29.jpg?type=l591_945">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>뮤지컬 더 데빌 The DEVIL</span> <small class="sm">드림아트센터
											1관 에스비타운</small>
									</h4>
									<p class="event_txt_dsc">더욱 간결해진 이야기 구조와 선명한 주제로 변화 시도 2014
										공연계를 뒤흔든 문제적 창작 뮤지컬의 귀환! 시대와 국경을 초월하는 ‘유혹’과 ‘선택’에 관한 이야기 괴테의
										[파우스트]를 모티브로 2014년 초연 개막 직후 공연계 최대의 화두로 떠올랐던 뮤지컬 &lt;더데빌&gt;
										보다 친절하게 혹은 명확하게 관객들에게 다가가기 위해 작품의 개성은 유지하되 일부 장면 수정, 삭제 선과 악을
										넘나드는 미지의 인물이었던 ‘X’를 White와 Black으로 분리하며 캐릭터 재구성, 장면의 상징성 강화
										거부할 수 없는 매혹적인 스토리로 재탄생된 2017 &lt;더데빌&gt;을 만난다! 중독성 강한 음악으로 가득
										찬 무대 음악이 곧 이야기이다! 아름다운 클래식 선율과 강렬한 록 사운드의 만남 몽환적 느낌으로 독특한 분위기를
										자아내는 &lt;더데빌&gt;의 음악은 곧 이야기이자 캐릭터이며 인간의 욕망과 파멸이라는 주제 그 자체가 된다
										기존 넘버의 편곡 작업 및 새로운 넘버 2곡 추가 20여 곡의 넘버로 쉴 틈 없이 채워지는 100분간의 압도적인
										무대 특유의 몽환적이면서도 농밀한 공기로 무대를 가득 채운다! 더욱 강렬하고 섹시해진 캐스트 폭발적인 시너지의
										무대! 파워풀한 가창력과 탄탄한 연기력으로 무장한 초연 캐스트와 New 캐스트의 완벽한 조합 보다 깊어진
										존재감으로 만나게 될 초연 캐스트 송용진 박영수 이충주 2년여 만의 화려한 컴백 장승조 부드러운 카리스마의 배우
										임병근 대학로 무대로 만나는 최고의 뮤지컬 히로인 리사 2017 &lt;더데빌&gt;을 통해 가장 매력적인
										필모그래피를 완성할 라이징스타 고훈정 조형균 정욱진 이하나 이예은까지 각자의 캐릭터 해석을 통해 선과 악, 신과
										악마, 때로는 나약한 인간으로 관객 앞에 서게 될 매력적인 11인의 캐스트 실력과 개성을 겸비한 배우들의 진가를
										가장 가까이에서 만끽한다! 최강 크리에이티브 팀이 탄생시킨 화제의 창작 락 뮤지컬 &lt;더데빌&gt; 제 9회
										더뮤지컬 어워즈 작곡작사상 수상 음악과 작품 구성의 완벽한 조화를 이끌어 낸 초연 크리에이티브 팀이 다시 뭉쳤다
										&lt;잃어버린 얼굴 1895&gt;, &lt;곤 투모로우&gt;, &lt;도리안 그레이&gt; 등 2016년
										한 해 동안 세 편의 창작 뮤지컬을 무대에 올린 이지나 연출 미국에서 다방면으로 활동하며 주목받은 음악가
										Woody Pak 톡톡 튀는 감각으로 다수의 뮤지컬에 참여해 온 이지혜 작곡가 등 국내외 실력파 크리에이터들이
										다시 의기투합하여 한층 완성도 높아진 공연을 선보인다!</p>
								</div>
						</a></li>
					</ul>
					<ul class="lst_event_box">
						<li class="item"><a href="detail.html" class="item_book">
								<div class="item_preview">
									<img alt="뮤지컬 로미오와 줄리엣" class="img_thumb"
										src="https://ssl.phinf.net/naverbooking/20170119_135/1484789767866RPO6o_JPEG/%B7%CE%B9%CC%BF%C0%C1%D9%B8%AE%BF%A7_1242.jpg?type=l591_945">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>뮤지컬 로미오와 줄리엣</span> <small class="sm">종로구 두산아트센터
											연강홀</small>
									</h4>
									<p class="event_txt_dsc">웰메이드 창작 뮤지컬의 대표 브랜드 '김수로 프로젝트'
										최신작!</p>
								</div>
						</a></li>
						<li class="item"><a href="detail.html" class="item_book">
								<div class="item_preview">
									<img alt="뮤지컬 인 더 하이츠(IN THE HEIGHTS)" class="img_thumb"
										src="https://ssl.phinf.net/naverbooking/20170111_225/1484116579024rNkXW_JPEG/2016_%B9%C2%C1%F6%C4%C3_%C0%CE_%B4%F5_%C7%CF%C0%CC%C3%F7_%C6%F7%BD%BA%C5%CD%2820MB%29.jpg?type=l591_945">
									<span class="img_border"></span>
								</div>
								<div class="event_txt">
									<h4 class="event_txt_tit">
										<span>뮤지컬 인 더 하이츠(IN THE HEIGHTS)</span> <small class="sm">예술의전당
											CJ토월극장</small>
									</h4>
									<p class="event_txt_dsc">제62회 토니상 어워즈 4개 부문 수상! (최우수뮤지컬상,
										작곡∙작사상, 안무상, 오케스트라상) 제51회 그래미 어워즈 최우수 뮤지컬 앨범상 수상! VOLUME UP!
										랩, 힙합, 스트릿댄스! 눈을 뗄 수 없는 강렬한 퍼포먼스! 새롭고 흥겨운 다이내믹한 무대를 관객들에게 전하여,
										20 - 30대뿐만 아니라 40, 50, 60대 이상 모든 연령층이 함께 즐기는 공연! 뉴욕의 라틴 할렘이라
										불리는 맨해튼 북서부의 워싱턴 하이츠. 그곳에서 서로 인연을 맺은 이주민들의 애환이 담긴 삶과 꿈, 그리고
										희망을 긍정적인 유머로 승화하여 진한 위로와 공감대를 전하는 브로드웨이 작품이다.</p>
								</div>
						</a></li>
					</ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn">
							<span>더보기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>


	<script type="rv-template" id="promotionItem">
    <li class="item" style="background-image: ${ImageLocation}">
        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    </script>

	<script type="rv-template" id="itemList">
        <li class="item">
            <a href="detail.html?id=${id}" class="item_book">
                <div class="item_preview">
                    <img alt="${description}" class="img_thumb" src="http://211.249.62.123/productImages/${id}?type=th">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>${description}</span> <small class="sm">${placeName}</small> </h4>
                    <p class="event_txt_dsc">${content}</p>
                </div>
            </a>
        </li>
    </script>


	<!--Top버튼 누를시 맨위로 이동-->
	<script type="text/javascript">
		var topIcon = document.querySelector(".lnk_top");
		var scroll = document.querySelector("html");

		function goTop() {
			if (scroll.scrollTop === 0)
				return;
			scroll.scrollTop -= 35;
			requestAnimationFrame(goTop);
		}

		topIcon.addEventListener("click", function() {
			requestAnimationFrame(goTop);
		})
	</script>


	<script type="text/javascript">
		var index = 0;
		var width = 414;
		var images = document.querySelector("#images");
		var length = images.childElementCount;

		var moveTo = function() {
			images.style.transform = "translate(-" + (width * index) + "px,0)";
		}

		setInterval(function() {
			index = index + 1;
			index = index % length;
			moveTo();
		}, 2000)
	</script>
	
	<script>
	document.addEventListener("DOMContentLoaded", function() {	

		var categoryList = document.querySelector(".event .section_event_tab .event_tab_lst");
		
		categoryList.addEventListener("click",function(evt){
			changeCategory(evt);
			deleteItemAll(evt);
			showNewItem(evt);
		})
		
		function changeCategory(evt){
			var selectedCategory = document.querySelector(".event .section_event_tab .event_tab_lst>.item .anchor.active");
			if(evt.target.tagName == "SPAN"){
				selectedCategory.classList.remove("active");
				evt.target.parentNode.classList.add("active");
			}
			else if(evt.target.tagName === "A"){
				selectedCategory.classList.remove("active");
				evt.target.classList.add("active");
			}
		}

		function deleteItemAll(evt){
			var itemList = document.querySelectorAll(".event .wrap_event_box .lst_event_box");
			
			while ( itemList[0].hasChildNodes() ) { 
				itemList[0].removeChild( itemList[0].firstChild ); 
				}

			while (  itemList[1].hasChildNodes() ) { 
				itemList[1].removeChild( itemList[1].firstChild ); 
				}
		}
		
		function showNewItem(evt){
			var categoryName = document.querySelector(".event .section_event_tab .event_tab_lst>.item .anchor.active").childNodes[1].innerHTML;
			
			
		}
		
		
	})
	
	
	</script>
</body>

</html>

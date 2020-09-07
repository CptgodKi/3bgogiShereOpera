<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> 송장 부여 결과 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/deliv_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
	<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/jquery-barcode.js"></script>
    
    
    <script type="text/javascript">
    
    	$(function(){
    		
    		window.onbeforeunload = function(e){
    	    	opener.location.reload();
        	}
    		
    	});
    </script>
    <style>
   
   	 @media print{@page {size: landscape}}
   	 
   	 h3{
   	 	-webkit-font-smoothing: antialiased;
   	 }
    </style>
</head>
<body>
	<div class="container-fluid  dashboard-content" style="padding:0" id="testId">
		<div class="row" style="display: none;">
			<canvas id="delivTest" width="1101" height="429"></canvas>
		</div>
		
		<c:set var="topValue" value="0"/>
		<c:forEach var="orlist" items="${orList }">		
			<div id="html_deliv_test" style="position: absolute; width:1090px; height:429px; top:${topValue}px;" >
				<h3 class='deliv-styles deliv-po-nm-point'>접수국 : ${orlist.delivPoNm }</h3>
				<h3 class='deliv-styles deliv-reserv-date'>${orlist.resDate } </h3>
				
				<h3 class='deliv-styles deliv-buyer-name'>주문인 : ${orlist.orBuyerName }</h3>
				<h3 class='deliv-styles deliv-ss-name'>주문처 : ${orlist.ssName } </h3>
				<h3 class='deliv-styles deliv-order-number'>고유번호  : ${orlist.orSerialSpecialNumber } </h3>

				<h3 class='deliv-styles deliv-box-weight'>중량 : 초소</h3>
				<h3 class='deliv-style deliv-sending-price'>요금 : 계약요금</h3>
					
				<div class='deliv-styles deliv-zip-barcode barcodes' data-barcodes="${orlist.orShippingAddressNumber }"></div>
				
				<h3 class='deliv-styles deliv-message-title'>배송메세지 : </h3>
				
				
				<h3 class='deliv-styles deliv-message'> ${orlist.orDeliveryMessage } </h3>
				
				<h3 class='deliv-styles arr-cnpo-before'>${fn:substring(orlist.delivAreaCd, 0, 2) }</h3>
				<h3 class='deliv-styles arr-cnpo-nm'>${orlist.arrCnpoNm }</h3>
				<h3 class='deliv-styles arr-cnpo-nm-after'>${fn:substring(orlist.delivAreaCd, 2, 5) }</h3>
				<h3 class='deliv-styles deliv-po-nm'>${orlist.delivPoNm }</h3>
				<h3 class='deliv-styles deliv-po-nm-before'>${fn:substring(orlist.delivAreaCd, 5, 7) }</h3>
				<h3 class='deliv-styles deliv-po-nm-after'>${fn:substring(orlist.delivAreaCd, 7, 9) }</h3>
				
				
				<h3 class='deliv-styles deliv-sender-addr'>인천광역시 계양구 효서로 388 (작전동) 3층 삼형제고기</h3>
				
				<h3 class='deliv-styles deliv-sender-zip-num'> 21126 </h3>
				
				<h3 class='deliv-styles deliv-sender'>  
					<c:if test="${empty orlist.orBuyerAnotherName }">
						삼형제고기
					</c:if>
					<c:if test="${!empty orlist.orBuyerAnotherName  }">
						${orlist.orBuyerAnotherName }
					</c:if>
				</h3>
				
				<h3 class='deliv-styles deliv-sender-tel-num'> T : 0507-1312-1620 </h3>
				
				
				<h3 class='deliv-styles rec-addr'> ${orlist.orShippingAddress } ${orlist.orShippingAddressDetail } </h3>
				
				<h3 class='deliv-styles receiver'> ${orlist.orReceiverName } </h3>
				
				<h3 class='deliv-styles rec-tel-num'> T : ${orlist.orReceiverContractNumber1 } </h3>
				<c:if test="${!empty orlist.orReceiverContractNumber2 } ">
					<h3 class='deliv-styles rec-mob-num'> T : ${orlist.orReceiverContractNumber2 } </h3>				
				</c:if>
				
				
				<div class='deliv-styles deliv-invoice-num delivNum' data-deliv-num="${orlist.regiNo }"></div>
				
				
				
				<c:set var="divQty" value="1"/>
				<c:set var="qty" value="0"/>
				<c:set var="prodTop" value="80"/>
				<c:if test="${fn:length(orlist.productOptionList) > 8 }">
					<c:set var="divQty" value="${(fn:length(orlist.productOptionList) / 8) + (1-((fn:length(orlist.productOptionList) / 8)%1))%1  }"/>
						<c:forEach var="i" begin="0" step="1" end="${8 - 1 }">				
						<h3 style='top:${prodTop}px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>${orlist.productOptionList[i].anotherOptionQty }</h3>
						<h3 style='top:${prodTop-12}px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>
							${orlist.productOptionList[i].productName } [ ${orlist.productOptionList[i].optionName } , ${i }]
						</h3>
						
						<c:set var="qty" value="${qty + products.anotherOptionQty  }"/>
						<c:set var="prodTop" value="${prodTop + 40 }"/>	
					</c:forEach>
				</c:if>
				
				<c:if test="${fn:length(orlist.productOptionList) <= 8 }">
					<c:set var="divQty" value="${(fn:length(orlist.productOptionList) / 8) + (1-((fn:length(orlist.productOptionList) / 8)%1))%1  }"/>
					<c:forEach var="i" begin="0" step="1" end="${fn:length(orlist.productOptionList) - 1}">				
						<h3 style='top:${prodTop}px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>${orlist.productOptionList[i].anotherOptionQty }</h3>
						<h3 style='top:${prodTop-12}px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>
							${orlist.productOptionList[i].productName } [ ${orlist.productOptionList[i].optionName } , ${i }]
						</h3>
						
						<c:set var="qty" value="${qty + products.anotherOptionQty  }"/>
						<c:set var="prodTop" value="${prodTop + 40 }"/>	
					</c:forEach>
				</c:if>
				
				
				
				
				<h3 class='deliv-styles total-qty'>총 상품수량 : ${fn:length(orlist.productOptionList) }</h3>
				<h3 class='deliv-styles qty-title'>수량</h3>
			</div>
			
			
			<c:if test="${ divQty > 1 }">
				<c:set var="topValue" value="${topValue + 432 }"/>
				<c:set var="divs" value="${fn:length(orlist.productOptionList) % 8 }"/>
				<c:forEach var="i" begin="0" step="1" end="${divQty-1 }">
					<c:set var="divQtyCount" value="1"/>
					 <div id="html_deliv_test" style="position: absolute; width:1090px; height:429px; top:${topValue}px;" >
					 	<h3 class='deliv-styles deliv-sender-addr'>***** 해당 송장은 발송하지 않는 송장 입니다 ******</h3>
					 	<c:set var="prodTop" value="80"/>
						<c:forEach var="j" begin="${((i+1) * 8) - 1  }" step="1" end="${( ((i+1) * 8) + ((i+1) * 8) ) - 1 }">		
							<c:if test="${(fn:length(orlist.productOptionList) - 1 ) == j }">							
							 	<h3 style='top:${prodTop}px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>${orlist.productOptionList[j].anotherOptionQty }</h3>
								<h3 style='top:${prodTop-12}px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>
									${orlist.productOptionList[j].productName } [ ${orlist.productOptionList[j].optionName } ]
									
								</h3>
							</c:if>
							<c:set var="prodTop" value="${prodTop + 40 }"/>	
						</c:forEach>
						<h3 class='deliv-styles qty-title'>수량</h3>
					 </div>
				
				</c:forEach>
				
			</c:if>
			<c:set var="topValue" value="${topValue + 432 }"/>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
	/* var canvas  = document.getElementById("delivTest");
	var context = canvas.getContext("2d");
	context.font = "bold 50px '맑은 고딕'";
	context.fillText("B8", 260, 50.4);
	
	var context = canvas.getContext("2d");
	context.font = "bold 20px '맑은 고딕'";
	context.fillText("부평물", 320, 47.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 28px '맑은 고딕'";
	context.fillText("40", 390, 38.4);
	
	var context = canvas.getContext("2d");
	context.font = "bold 22px '맑은 고딕'";
	context.fillText("인천계양", 427, 47.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 40px '맑은 고딕'";
	context.fillText("55", 530, 50.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 40px '맑은 고딕'";
	context.fillText("41", 600, 50.4);

	var arrCnpoNmBefore = document.createElement("h3");
	arrCnpoNmBefore.innerHTML="B8";
	arrCnpoNmBefore.style.fontSize=55+"px";	
	arrCnpoNmBefore.style.top=8.4+"px";
	arrCnpoNmBefore.style.left=260+"px";
	arrCnpoNmBefore = epostFontStyle(arrCnpoNmBefore);
	
	
	var arrCnpoNm = document.createElement("h3");
	arrCnpoNm.innerHTML="부평물";
	arrCnpoNm.style.fontSize=15+"px";	
	arrCnpoNm.style.top=40.4+"px";
	arrCnpoNm.style.left=330+"px";
	arrCnpoNm = epostFontStyle(arrCnpoNm);
	
	var arrCnpoNmAfter = document.createElement("h3");
	arrCnpoNmAfter.innerHTML="409";
	arrCnpoNmAfter.style.fontSize=40+"px";	
	arrCnpoNmAfter.style.top=16.4+"px";
	arrCnpoNmAfter.style.left=390+"px";
	arrCnpoNmAfter = epostFontStyle(arrCnpoNmAfter); */
	
	
	var arrCnpoNmBeforeHtml = ''; 
	//접수 우체국 및 인쇄일
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-po-nm-point'>접수국 : 인천계양</h3>"
	
	+"<h3 class='deliv-styles deliv-reserv-date'>2020-09-01 </h3>";
	
	
	//주문고객 정보
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-buyer-name'>주문인 : 갓기찬</h3>"
		+"<h3 class='deliv-styles deliv-ss-name'>주문처 : 스마트스토어 </h3>"
		+"<h3 class='deliv-styles deliv-order-number'>고유번호  : 스마-33608 </h3>";
		
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-box-weight'>중량 : 초소</h3>"
		+"<h3 class='deliv-style deliv-sending-price'>요금 : 계약요금</h3>";
		
	arrCnpoNmBeforeHtml+="<div class='deliv-styles deliv-zip-barcode barcodes'></div>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-message-title'>배송메세지 : </h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-message'> 3층 사무실에 놔주세요 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles arr-cnpo-before'>B8</h3>"
	+"<h3 class='deliv-styles arr-cnpo-nm'>부평물</h3>"
	+"<h3 class='deliv-styles arr-cnpo-nm-after'>409</h3>"
	+"<h3 class='deliv-styles deliv-po-nm'>인천계양</h3>"
	+"<h3 class='deliv-styles deliv-po-nm-before'>55</h3>"
	+"<h3 class='deliv-styles deliv-po-nm-after'>41</h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-addr'> 인천광역시 계양구 효서로 388 (작전동) </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-zip-num'> 21125 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender'> 삼형제고기 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-tel-num'> T : 0507-1312-1620 </h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-addr'> 인천광역시 남동구 수현로136번길 25 (만수동, 한양빌라) 8동 102호 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles receiver'> 캡틴 갓기찬 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-tel-num'> T : 010-9350-3632 </h3>";
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-mob-num'> T : 010-1234-1234 </h3>";
	
	
	arrCnpoNmBeforeHtml+="<div class='deliv-styles deliv-invoice-num delivNum'></div>";
	
	
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles total-qty'>총 상품수량 : 3</h3>";
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles qty-title'>수량</h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 style='top:80px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:80px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:120px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:120px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:160px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:160px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:200px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:200px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:240px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:240px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:280px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:280px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:320px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:320px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:360px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:360px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	
	/* document.getElementById("htmlTest").appendChild(arrCnpoNmBefore);
	document.getElementById("htmlTest").appendChild(arrCnpoNm);
	document.getElementById("htmlTest").appendChild(arrCnpoNmAfter);
	
	document.getElementById("htmlTest2").append(arrCnpoNmBefore);
	document.getElementById("htmlTest2").append(arrCnpoNm);
	document.getElementById("htmlTest2").append(arrCnpoNmAfter); */
	
	document.getElementById("htmlTest").innerHTML=arrCnpoNmBeforeHtml;
	document.getElementById("htmlTest2").innerHTML=arrCnpoNmBeforeHtml;
	
	var delivs="";
	//<div style="position: absolute; width:1090px; height:429px;" ></div>
	
	/* for(var i = 0; i < 10; i++){
		delivs += '<div style="position: absolute; width:1090px; height:429px; top:'+(i * 432)+'px;" >'+arrCnpoNmBeforeHtml+'</div>';
		
	} */
	
	document.getElementById("testId").innerHTML=delivs;
	
	$(".barcodes").barcode('21126', 'code128',{barWidth:2, barHeight:60});
	
	$(".delivNum").barcode('60963158186731', 'code128',{barWidth:2.7, barHeight:75});
	
	function epostFontStyle(elements){
		elements.style.position="absolute";
		elements.style.color="black";
		return elements;
		
		
	}
	
	 /* html2canvas($('#htmlTest')[0]).then(function(canvas) {
		    var doc = new jsPDF('p', 'mm'); //jspdf객체 생성
		    var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
		    doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
		    doc.save('sample-file.pdf'); //pdf저장
		  }); */
</script>
 
</html>

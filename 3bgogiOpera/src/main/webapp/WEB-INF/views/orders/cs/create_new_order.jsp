<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>발송 변경</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
<script type="text/javascript">
    
    	$(function(){
    		var today = formatDate(new Date());
    		var todayAllTime = allFormatDate(new Date());
    		var todayAllSec = formatDate_all_hh_mm_ss(new Date());
    		$("#orSendingDeadline").val(today);
    		$("#orSettlementDay").val(todayAllSec);
    		$("#orCheckDay").val(today);
    		
    		
    		$('#orSendingDeadline').datetimepicker({
    			format:'Y-m-d',
    			lang:'kr',
    			timepicker:false,
    			minDate : formatDate(new Date())
    		});
    		
    		$("#searchAddressBtn").click(function(){
    			window.open("<c:url value='/orders/search/tab.do'/>", "우편 조회" , "width=700, height=800, top=100, left=300, scrollbars=no");
    			
    		});
    		
    		$("#addProductBtn").click(function(){		

    			window.open("/orders/cs/add_create_order_product.do", "상품 추가" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    			
    			/*$.ajax({
    				type       : 'GET',
    				data       : {
    					"orSerialSpecialNumberList":orSerialSpecialNumberList
    				},
    				url        : '/orders/delete/customer_order.do',
    				success    : function(data){
    					if(data == true){		
    						alert("해당 주문서 삭제 완료");
    						location.reload();
    					}else{
    						alert("삭제 실패");
    					}
    				}
    				
    			});*/
    		});
    		
    		$("#smsSend").click(function(){
    			if($(this).is(":checked") == true){

    				$("#smsSendFlag").val("1");
    				
    			}else{
    				$("#smsSendFlag").val("0");
    			}
    		});
    		
    		$("#sameReceiver").click(function(){
    			if($(this).is(":checked") == true){
    				var orBuyerName = $("#orBuyerName").val();
    				var orBuyerContractNumber1 = $("#orBuyerContractNumber1").val();
    				var orBuyerContractNumber2 = $("#orBuyerContractNumber2").val();
    				
    				$("#orReceiverName").val(orBuyerName);
    				$("#orReceiverContractNumber1").val(orBuyerContractNumber1);
    				$("#orReceiverContractNumber2").val(orBuyerContractNumber2);
    				
    			}else{
    				$("#orReceiverName").val("");
    				$("#orReceiverContractNumber1").val("");
    				$("#orReceiverContractNumber2").val("");
    			}
    		});
    		
    		$("#insertNewOrderForm").submit(function(){
    			var productExist = $("#createOrderProductList").children().length;
    			var addressNum = $("#orShippingAddressNumber").val();
    			
    			if(productExist == 0){
    				alert("상품을 추가해주세요");
	    			event.preventDefault();
	    			return false;
	    			
    			}else if(addressNum == ''){
    				alert("우편번호가 없습니다. 주소 검색을 해주세요 ");
	    			event.preventDefault();
	    			return false;
    			}
    		});
    		
    		$(document).on("click", ".deleteCreateOrderProduct", function(){
    			$(this).parent().parent().remove();
    		});
    	});
    	
    	
    </script>
</head>
<style type="text/css">
	body{
		margin-bottom : 40px;
		padding-top : 40px;
	}
	label{
		font-size: 12px;
	}
	.form-control, .btn-sm{
		font-size: 11px;
	}
</style>
<body>
	<div class="row">
		<div class="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-sm-12 col-12">
			<form class="row" id="insertNewOrderForm" action="<c:url value='/orders/create/order.do'/>" method="POST">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">
							<h4 class="mb-0"> 새 주문서 작성 </h4>
						</div>
						<div class="card-body">
							<div class="needs-validation" >
								<input type="hidden" name="orProductType" value="일반상품">
								<input type="hidden" name="orSettlementDay" id="orSettlementDay">
								<input type="hidden" name="orCheckDay" id="orCheckDay">
								<input type="hidden" name="orDeliveryType" value="택배,등기">
								<input type="hidden" name="orDeliveryChargeType" value="회사부담">
								<input type="hidden" name="orPaymentPositionType" value="전화주문">
								<input type="hidden" name="orProductPrice" value="0">
								<input type="hidden" name="orProductOptionPrice" value="0">
								<input type="hidden" name="orDiscountPrice" value="0">
								<input type="hidden" name="orSendingAddress" value="인천시 계양구 효서로 388 3층 삼형제고기">
								<input type="hidden" name="orPaymentType" value="계좌이체">
								<input type="hidden" name="orInflowRoute" value="전화">
								<input type="hidden" name="smsSendFlag" id="smsSendFlag" value="0">
								
								<div class="row">
									<div class="col-md-4 mb-1">
										<div class="form-group row">
                                            <label for="orSendingDeadline" class="col-12 col-sm-3 col-form-label text-sm-right" style="font-size: 12px;">발송일</label> 
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" class="form-control form-control-sm" id="orSendingDeadline" name="orSendingDeadline" placeholder="" required="">
                                            </div>
                                        </div>
									</div>
									<div class="col-md-5 mb-1">
										<div class="form-group row">
                                            <label for="ssFk" class="col-12 col-sm-3 col-form-label text-sm-right" style="font-size: 12px;">판매처</label> 
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control form-control-sm" name="ssFk" id="ssFk">
												    <c:forEach var="sslist" items="${ssList }">						                    	
														<option value="${sslist.ssPk }"> ${sslist.ssName }</option>
												    </c:forEach>
										        </select>
                                            </div>
                                        </div>
									</div>
									<div class="col-md-3 mb-1">
										<label for="smsSend">문자 발송</label> 
										<label class="custom-control custom-checkbox custom-control-inline">
	                                        <input type="checkbox" id="smsSend" class="custom-control-input"><span class="custom-control-label"> &nbsp; </span>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3 mb-1">
										<label for="orBuyerName" class="text-muted"> 주문자 명</label> 
										<input type="text" class="form-control form-control-sm" id="orBuyerName" name="orBuyerName" placeholder="" value="" required="">
									</div>
									
									<div class="col-md-3 mb-1">
										<label for="orBuyerContractNumber1">주문자 핸드폰</label> 
										<input type="text" class="form-control form-control-sm phone-inputmask " id="orBuyerContractNumber1" name="orBuyerContractNumber1" placeholder="">
									</div>
									
									<div class="col-md-3 mb-1"> 
										<label for="orBuyerContractNumber2">주문자 연락처</label> 
										<input type="text" class="form-control form-control-sm" id="orBuyerContractNumber2" name="orBuyerContractNumber2" placeholder="">
									</div>
									<div class="col-md-3 mb-1">
										<label for="orBuyerContractNumber1">수취인 동일</label> 
										<label class="custom-control custom-checkbox custom-control-inline">
	                                        <input type="checkbox" id="sameReceiver" class="custom-control-input"><span class="custom-control-label"> &nbsp; </span>
										</label>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-4 mb-1">
										<label for="orReceiverName"> 수취인명</label> 
										<input type="text" class="form-control form-control-sm" id="orReceiverName" name="orReceiverName" placeholder="" value="" required="">
									</div>
									
									<div class="col-md-4 mb-1">
										<label for="orReceiverContractNumber1">수취인 핸드폰</label> 
										<input type="text" class="form-control form-control-sm phone-inputmask " name="orReceiverContractNumber1" id="orReceiverContractNumber1" placeholder="">
									</div>
									
									<div class="col-md-4 mb-1"> 
										<label for="orReceiverContractNumber2">수취인 연락처</label> 
										<input type="text" class="form-control form-control-sm" id="orReceiverContractNumber2" name="orReceiverContractNumber2" placeholder="">
									</div>
								</div>
								
								
								<div class="row mb-2">
									<div class="col-md-4">									
										<label for="orShippingAddressNumber"> 우편번호 </label>
										<div class="input-group mb-1" >
		                                    <input type="text" class="form-control form-control-sm" id="orShippingAddressNumber" name="orShippingAddressNumber" readonly="readonly" required>
		                                    <div class="input-group-append">
		                                    	<button type="button" class="btn btn-primary btn-sm" id="searchAddressBtn"> 검색 </button>
		                                    </div>
	                                    </div>
									</div>
									<div class="col-md-8">
										<label for="orShippingAddress"> 주소 </label> 
										<input type="text" class="form-control form-control-sm" id="orShippingAddress" name="orShippingAddress" placeholder="" required>
									</div>
								</div>
								
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orShippingAddressDetail"> 상세 주소 </label> 
										<input type="text" class="form-control form-control-sm" id="orShippingAddressDetail" name="orShippingAddressDetail" placeholder="">
									</div>
								</div>
								
								<div class="row mb-2">
									<div class="col-md-8">
										<label for="orShippingAddressDetail"> 배송메세지 </label> 
										<input type="text" class="form-control form-control-sm" id="orDeliveryMessage" name="orDeliveryMessage" placeholder="">
									</div>
									<div class="col-md-4">
										<label for="orShippingAddressDetail"> 배송비 </label> 
										<input type="text" class="form-control form-control-sm" id="orDeliveryPrice" name="orDeliveryPrice" value="3300">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orUserColumn1"> 사용자 정의 1 </label> 
										<input type="text" class="form-control form-control-sm" id="orUserColumn1" name="orUserColumn1" placeholder="">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orUserColumn2"> 사용자 정의2 </label> 
										<input type="text" class="form-control form-control-sm" id="orUserColumn2" name="orUserColumn2" placeholder="">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orUserColumn3"> 사용자 정의 3 </label> 
										<input type="text" class="form-control form-control-sm" id="orUserColumn3" name="orUserColumn3"  placeholder="">
									</div>
								</div>
								<hr class="mb-4">
								<button class="btn btn-primary btn-lg btn-block" type="submit"> 추가하기 </button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-header">
							<h4 class="d-flex justify-content-between align-items-center mb-0">
								<span class="text-muted"> 상품 </span> <button class="btn btn-success btn-sm" id="addProductBtn" type="button"> 상품 추가 </button>
							</h4>
						</div>
						
						<div class="card-body">
							<ul class="list-group mb-3" id="createOrderProductList">
								
								
							</ul>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
<script type="text/javascript">
	$(function() {
			
		//$(".phone-inputmask").inputmask("999-9999-9999");
			
		$.datetimepicker.setLocale('kr');
			
	});	
</script>
</html>
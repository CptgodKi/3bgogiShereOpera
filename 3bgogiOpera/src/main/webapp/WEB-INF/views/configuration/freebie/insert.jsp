<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
    <style type="text/css">
    	.renewal-store-list{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#fbMinDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		
    		$('#fbMaxDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		
    		$("input[name=fbTotalQtyFlag]").change(function(){
    			var fbTotalQtyFlag = $(this).val();
    			var fbAddType = $("input[name=fbAddType]:checked").val();
    			if(fbTotalQtyFlag == 0){
    				$("#fbMinTotalQty").prop("readonly","readonly");
    				$("#fbMaxTotalQty").prop("readonly","readonly");
    			}else{
    				$("#fbMinTotalQty").prop("readonly","");
    				$("#fbMaxTotalQty").prop("readonly","");
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				
    			}
    			
    		});
    		
    		$("input[name=fbAnotherCheckFlag]").change(function(){
    			var fbAnotherCheckFlag = $(this).val();
    			
    			if(fbAnotherCheckFlag == 0){
    				$("#fbAnotherCheckList").prop("disabled","disabled");
    				$("#fbAnotherCheckWord").prop("readonly","readonly");
    				$("#fbAnotherCheckType").prop("disabled","disabled");
    				
    			}else{
    				$("#fbAnotherCheckList").prop("disabled","");
    				$("#fbAnotherCheckWord").prop("readonly","");
    				$("#fbAnotherCheckType").prop("disabled","");
    			}
    		});
    		
    		$("#freebieInsertForm").submit(function(){
    			var fbMinPrice = $("#fbMinPrice").val();
    			var fbMaxPrice = $("#fbMaxPrice").val();
    			var fbMinTotalQty = $("#fbMinTotalQty").val();
    			var fbMaxTotalQty = $("#fbMaxTotalQty").val();
    			
    			
    			if(fbMinPrice > fbMaxPrice){
    				alert("최대 금액이 최소금액보다 작을 수 없습니다");
    				$("#fbMaxPrice").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(fbMinTotalQty == '' && fbMinTotalQty == ''){
    				$("#fbMinTotalQty").val("0");
    				$("#fbMaxTotalQty").val("0");
    				event.preventDefault();
    				return false;
    			}
    			
    		});
    		
    		$("input[name=fbAddType]").change(function(){
    			var fbTotalQtyFlag = $("input[name=fbTotalQtyFlag]:checked").val();
    			var fbAddType = $(this).val();
    			
    			if($(this).val() == 3){
    				$("#totalPrice").text("배수 가격");
    				$("#totalQty").text("배수 수량");
    				$("#maxTotalPrice").hide();
    				$("#maxTotalQty").hide();
    				$("#fbMinPrice").prop("placeholder","배수 금액");
    				$("#fbMinTotalQty").prop("placeholder","배수 수량");
    				
    			}else{
    				$("#totalPrice").text("합계 금액");
    				$("#totalQty").text("합계 수량");
    				$("#maxTotalPrice").show();
    				$("#maxTotalQty").show();
    				$("#fbMinPrice").prop("placeholder","최소 금액");
    				$("#fbMinTotalQty").prop("placeholder","최소 수량");
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				$("#fbMinTotalQty").focus();
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				$("#fbMinPrice").focus();
    				
    			}
    			
    		});
    		
    		
    		
    	});
    	
    	function openProductOption(){
    		
    		window.open("<c:url value='/common/products.do'/>", "상품 검색" , "width=800, height=800, top=50, left=400, scrollbars=no");
    		
    	}
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content ">
                  <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 사은품 정책 추가 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 사은품 정책 </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <form id="freebieInsertForm" action="<c:url value='/config/freebie/insert.do'/>" method="POST">
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 사은품 기본 설정 </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사은품 정책명 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" id="fbName" name="fbName" class="form-control">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 정책 적용 판매처 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
							                        <select class="selectpicker" multiple data-actions-box="true" data-width="100%" id="ssList" name="ssList">
								                        <c:if test="${empty ssList }">
								                        	<option value="0">등록된 판매처가 없습니다</option>
								                        </c:if>
								                        <c:if test="${!empty ssList }">
								                        	<c:forEach var="sslist" items="${ssList }">
								                        		<option value="${sslist.ssPk }">${sslist.ssName }</option>
								                        	</c:forEach>
								                        </c:if>
							                        </select>
							                        
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">사은품 정책 기간</label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required id="fbMinDate" class="form-control" name="fbMinDate" value="${fbVO.fbMinDate }">
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required id="fbMaxDate" class="form-control" name="fbMaxDate" value="${fbVO.fbMaxDate }">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사은품 합포 기준 </label>
	                                            <div class="col-9 col-sm-7 col-lg-4">
	                                            	<label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="0" name="fbType" checked="checked" class="custom-control-input"><span class="custom-control-label"> 묶음정리기준 </span>
		                                            </label>
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="1" name="fbType" class="custom-control-input"><span class="custom-control-label"> 주문번호 기준 </span>
		                                            </label>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 사은품 생성 </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품명 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <div class="input-group mb-3">
		                                                <input type="hidden" class="form-control" id="productPk">
		                                                <input type="text" class="form-control" id="productName">
		                                                <div class="input-group-append">
		                                                    <button type="button" class="btn btn-primary" id="searchProduct" onclick="openProductOption()"> 검색 </button>
		                                                </div>
		                                            </div>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션명 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" class="form-control" id="optionName">
	                                                <input type="hidden" required="" class="form-control" id="optionPk" name="optionFk">
	                                                
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사은품 개수 </label>
	                                            <div class="col-9 col-sm-7 col-lg-4">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="0" name="fbAddType" checked class="custom-control-input"><span class="custom-control-label"> 주문개수 </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="1" name="fbAddType" class="custom-control-input"><span class="custom-control-label"> 임의정의 </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="2" name="fbAddType" class="custom-control-input"><span class="custom-control-label"> 주문수량X </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="3" name="fbAddType" class="custom-control-input" id="multiType"><span class="custom-control-label"> 배수 </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" required="" name="fbAddQty" placeholder="개수" class="form-control" value="0">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 사은품 합계 조건 </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalPrice"> 합계 금액 </label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="number" placeholder="최소 금액" id="fbMinPrice" name="fbMinPrice" class="form-control" value="0">
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3" id="maxTotalPrice">
	                                                <input type="number" placeholder="최대 금액" id="fbMaxPrice" name="fbMaxPrice" class="form-control" value="0">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalQty"> 합계 수량 </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="fbTotalQtyFlag" value="0" checked class="custom-control-input"><span class="custom-control-label"> 미사용 </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" name="fbTotalQtyFlag" value="1" class="custom-control-input"><span class="custom-control-label"> 사용 </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" placeholder="최소 수량" id="fbMinTotalQty" name="fbMinTotalQty" class="form-control" readonly="readonly" value="0">
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2" id="maxTotalQty">
	                                                <input type="number" placeholder="최대 수량" id="fbMaxTotalQty" name="fbMaxTotalQty" class="form-control" readonly="readonly" value="0">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 추가 조건 </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <!-- <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 주문 별 금액 </label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required="" placeholder="최소 금액" class="form-control">
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required="" placeholder="최대 금액" class="form-control">
	                                            </div>
	                                        </div> -->
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 문자열 포함 조건 </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" name=fbAnotherCheckFlag checked class="custom-control-input" value="0"><span class="custom-control-label"> 미사용 </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" name="fbAnotherCheckFlag" class="custom-control-input" value="1"><span class="custom-control-label"> 사용 </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckList" name="fbAnotherCheckList" disabled="disabled">
	                                                	<option value="0">판매처 상품명</option>
	                                                	<option value="1">판매처 옵션명</option>
	                                                	<option value="2">판매처 상품코드</option>
	                                                	<option value="3">매칭 상품명</option>
	                                                	<option value="4">매칭 옵션명</option>
	                                                	<option value="5">주소</option>
	                                                	<option value="6">사용자정의1</option>
	                                                	<option value="7">사용자정의2</option>
	                                                	<option value="8">사용자정의3</option>
	                                                </select>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="text" placeholder="문자열" id="fbAnotherCheckWord" name="fbAnotherCheckWord" class="form-control" readonly="readonly">
	                                            </div>
	                                             <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckType" name="fbAnotherCheckType" disabled="disabled">
	                                                	<option value="0">포함 되지 않음</option>
	                                                	<option value="1">포함 됨</option>
	                                                	<option value="2">같음</option>
	                                                	<option value="3">같지 않음</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                    		<div class="offset-4 col-2 col-sm-3">	                    		
			                    	<button class="btn btn-primary btn-block"> 사은품 등록하기 </button>
	                    		</div>
	                    </div>
                   </form>
           		</div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>
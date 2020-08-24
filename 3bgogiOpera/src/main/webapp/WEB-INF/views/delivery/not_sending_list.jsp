<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <style type="text/css">
    	.products_table_hover{
    		background-color: #c3e6cb;
    	}
    	.SelfdevisionOrderBtn{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    
    	$(function(){
    		
    		$("#sendingDelivButton").hide();
    		var orderFlag = false;
    		var labelFlag = false;
    		
    		$('#exSearchBar').hide();
    		
    		$(".productInfo").mouseover(function(){
    			var table_id = $(this).data("table-product-info");
    			$(this).next().addClass("products_table_hover");
    			$(this).addClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
    			
    		});
    		
    		$(".productInfo").mouseleave(function(){
    			
    			var table_id = $(this).data("table-product-info");
    			$(this).next().removeClass("products_table_hover");
    			$(this).removeClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
    			
    		});
    		
    		$(".productDetail").mouseover(function(){
    			var table_id = $(this).data("table-product-detail");
    			$(this).prev().addClass("products_table_hover");
    			$(this).addClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
    			
    		});
    		
    		$(".productDetail").mouseleave(function(){
    			
    			var table_id = $(this).data("table-product-detail");
    			$(this).prev().removeClass("products_table_hover");
    			$(this).removeClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
    			
    		});
    		
    		
    		$(".SelfdevisionOrderBtn").click(function(){
    			
    			var orPk = $(this).data("or-pk");
    			
    			window.open("/orders/config/self_devide_order.do?orPk="+orPk+"&closing="+0, "주문서 나누기" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
    		});

    		$('#showExSearchBar').click(function(){
    			
    				if($(this).is(":checked")){
    					$('#exSearchBar').show();
    					
    				}else{
    					$('#exSearchBar').hide();
    					
    				}
    		});
    		
    		$("#sendingDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(confirm(orSize+" 개의 주문서에 송장을 부여하시겠습니까?")){    				
	    			var orSerialSpecialNumberList = new Array(orSize);
	    				
	    			for(var i=0; i<orSize; i++){
	    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    			}
	    			
	    			
	    			$.ajax({
	    				type       : 'POST',
	    				async	   : false,
	    				data       : {
	    					"orSerialSpecialNumberList":orSerialSpecialNumberList
	    				},
	    				url        : '/security/epost/deliv.do',
	    				success    : function(data){		
	    					window.open("/security/deliv_result.do?delivResult="+data, "송장 부여 결과" , "width=800px, height=700px, top=50px, left=50px");
	    					
	    				}
	    			});
    			}
    			
    			
    		});
    		
    		$("#orSeiralSpecialNumberAllSelect").click(function(){
    			
    			if($(this).is(":checked")){
    				$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
    				
    			}else{
    				$("input[name=orSerialSpecialNumberList]").prop("checked","");
    				
    			}
    			
    		});
    		
    		$("input[name=orSerialSpecialNumberList]").click(function(){
    			
    			if($(this).is(":checked")){
    				
    				
    			}else{
    				
    				if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
    					$("#orSeiralSpecialNumberAllSelect").prop("checked","");
    					
    				}
    			}
    			
    			/*		if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
    				$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
    			}else{
    				$("input[name=orSerialSpecialNumberList]").prop("checked","");
    			}
    			*/
    			
    		});
    		
    		$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		$(".devideOrderButton").click(function(){
				
    			var orSerialSpecialNumber = $(this).val();
    			
    			window.open("/orders/config/devide.do?orSerialSpecialNumber="+orSerialSpecialNumber, "주문서 나누기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");

    		});
    		
    		
    		$("#orderIO").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			var orSerialSpecialNumberList = new Array(orSize);
    			var orderIOList = "";
    			
    			for(var i=0; i<orSize; i++){
    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
    			}
    			
    			if(orSize > 0){ 
    				if(confirm("주문서를 다운로드 하시겠습니까?")){
    	    			
    	    			var numListInput = document.createElement("input");
    	    			numListInput.name="orSerialSpecialNumberList";
    	    			numListInput.type="hidden";
    	    			numListInput.value=orSerialSpecialNumberList;
    	    			
    	    			var excelDownloadForm =  document.createElement("form")
    	    			excelDownloadForm.action="/security/orderIO.do";
    	    			excelDownloadForm.method="POST";
    	    			
    	    			excelDownloadForm.append(numListInput);
    	    			
    	    			$("#excelDownloadIframe").append(excelDownloadForm);
    	    			excelDownloadForm.submit();
    	    			$("#excelDownloadIframe").html("");
    	    			
    	    			orderFlag = true;
    	    			
    	    			if(orderFlag == true && labelFlag == true){
    	    				$("#sendingDelivButton").show();
    	    			}
    	    		}
    			}
    		});
    		
    		$("#labelIO").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			var orSerialSpecialNumberList = new Array(orSize);
    			var orderIOList = "";
    			
    			for(var i=0; i<orSize; i++){
    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
    			}
    			
    			if(orSize > 0){ 
    				if(confirm("라벨지를 다운로드 하시겠습니까?")){
    	    			
    	    			var numListInput = document.createElement("input");
    	    			numListInput.name="orSerialSpecialNumberList";
    	    			numListInput.type="hidden";
    	    			numListInput.value=orSerialSpecialNumberList;
    	    			
    	    			var excelDownloadForm =  document.createElement("form")
    	    			excelDownloadForm.action="/security/product_label.do";
    	    			excelDownloadForm.method="POST";
    	    			
    	    			excelDownloadForm.append(numListInput);
    	    			
    	    			$("#excelDownloadIframe").append(excelDownloadForm);
    	    			excelDownloadForm.submit();
    	    			$("#excelDownloadIframe").html("");
    	    			
    	    			labelFlag = true;
    	    			
    	    			if(orderFlag == true && labelFlag == true){
    	    				$("#sendingDelivButton").show();
    	    			}
    	    			
    	    		}
    			}
    		});
    		
    		$(".editCustomerInfoBtn").click(function(){
    			
    			var orSerialSpecialNumberList = $(this).val();
    			
    			window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 정보 변경" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    				
    		});
    		
    	});
    	
    	
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#epostPageForm").submit();
		}
		
    </script>
    <style type="text/css">
    	.selectClass{
    		background-color: #FFA2A2;
    		color:white;
    	}
    </style>
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid  dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> 송장부여 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 매칭 </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                	<div class="row">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card" style="background-color: #efeff6;">
								<div class="card-body">
									<h4> 주문서 진행 단계 </h4>
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn btn-success"> 특수 지역 체크  </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn btn-success"> 사은품 부여  </a>    
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn btn-success"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn btn-success"> 옵션 매칭 </a>
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn btn-success"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn btn-success"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn btn-success"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn btn-success blinking"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form id="epostPageForm" action="<c:url value='/security/epost.do'/>" method="get">
	                        			<input type="hidden" name="searchCondition" >
			                        	<input type="hidden" name="searchKeyword">
			                        	<input type="hidden" name="currentPage" value="${OrderSearchVO.currentPage}">	                        		
			                            <div class="email-filters-right">
				                            <div class="btn-group">
				                            	<select class="form-control" name="ssPk">
				                            		<option value="0">판매처 전체</option>
				                            		<c:forEach var="ssList" items="${storeSectionList }">
				                            			<option value="${ssList.ssPk }">${ssList.ssName }</option>
				                            		</c:forEach>
				                            	</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name=recordCountPerPage>
													<option value="50"
														<c:if test="${OrderSearchVO.recordCountPerPage == 50 }">
															selected="selected"
														</c:if>
													>50 개씩</option>
													<option value="100"
														<c:if test="${OrderSearchVO.recordCountPerPage == 100 }">
															selected="selected"
														</c:if>
													>100 개씩</option>
													<option value="200"
														<c:if test="${OrderSearchVO.recordCountPerPage == 200 }">
															selected="selected"
														</c:if>
													>200 개씩</option>
													<option value="500"
														<c:if test="${OrderSearchVO.recordCountPerPage == 500 }">
															selected="selected"
														</c:if>
													>500 개씩</option>
													<option value="2000"
														<c:if test="${OrderSearchVO.recordCountPerPage == 2000 }">
															selected="selected"
														</c:if>
													>2000 개씩</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name=totalQtyAlarm>
													<option value="12"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 12 }">
															selected="selected"
														</c:if>
													> 총 합 12개 이상 표시</option>
													<option value="15"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 15 }">
															selected="selected"
														</c:if>
													> 총 합 15개 이상 표시</option>
													<option value="20"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 20 }">
															selected="selected"
														</c:if>
													> 총 합 20개 이상 표시</option>
													<option value="8"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 8 }">
															selected="selected"
														</c:if>
													> 총 합 8개 이상 표시</option>
													<option value="-1"
														<c:if test="${OrderSearchVO.totalQtyAlarm == -1 }">
															selected="selected"
														</c:if>
													> 표시하지 않음 </option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light" id="dateStart" name="dateStart" style="width: 8em;" value="${OrderSearchVO.dateStart }"/> &nbsp; 
				                                <input type="text" class="btn btn-light" id="dateEnd" name="dateEnd" style="width: 8em;" value="${OrderSearchVO.dateEnd }"/>
				                            </div>
				                            <div class="btn-group">
				                            	<c:set var="insertingCountNum" value="1"/>
						                        <select class="form-control" name="insertingCount">
							                        <option value=""> 전체 차수 </option>
													<c:forEach var="isoList" items="${insertStoreOrderList }">
														<fmt:formatDate var='formatDate' value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>
														<option value="${formatDate }"
															<c:if test="${OrderSearchVO.insertingCount == formatDate }">
																selected="selected"
															</c:if>
														>${insertingCountNum } 차 <fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/></option>
														<c:set var="insertingCountNum" value="${insertingCountNum + 1 }"/>
													</c:forEach>
												</select>
					                        </div>
				                            <div class="btn-group" style="text-align: right;">
				                                <button class="btn btn-primary" type="submit"> 조건 검색 </button>
				                            </div>
				                            <div class="btn-group" style="text-align: right;">
				                            	<button type="button" class="btn btn-primary" id="orderIO">  주문서 출력  </button>
				                            </div>
				                            
				                            <div class="btn-group" style="text-align: right;">
				                            	<button type="button" class="btn btn-primary" id="labelIO">  라벨지 출력  </button>
				                            </div>
				                            <div class="btn-group" style="text-align: right;">
				                            	<button class="btn btn-danger" id="sendingDelivButton" style="display: none;"> 송장 부여 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<button type="button" class="btn btn-primary" data-toggle="popover" title="포함하는 조건" 
		                        			data-content="해당 조검을 포함하는 송장을 검색합니다">포함 검색</button>
				                        	<div class="btn-group">
				                            	<select class="form-control" name="storeSection">
								                    <option value="0">전체 판매처</option>
								                    <c:forEach var="sslist" items="${ssList }">						                    	
									                    <option value="${sslist.ssPk }"> ${sslist.ssName }</option>
								                    </c:forEach>
								                    <option value="-1">개인주문</option>
							                    </select>                                 
				                            </div>
				                            <div class="btn-group">
				                            	<select class="form-control" name="searchType">
				                            		<option value="orderNames" 
								                    	<c:if test="${osVO.searchType == 'orderNames' }">
															 selected="selected"
														</c:if>
								                    >구매자 / 수령자 통합</option>
								                    <option value="buyerName" 
								                    	<c:if test="${osVO.searchType == 'buyerName' }">
															 selected="selected"
														</c:if>
								                    >구매자</option>
								                    <option value="receiverName"
								                    	<c:if test="${osVO.searchType == 'receiverName' }">
															 selected="selected"
														</c:if>
								                    >수령인</option>
								                    <option value="buyerNum"
								                    	<c:if test="${osVO.searchType == 'buyerNum' }">
															 selected="selected"
														</c:if>
								                    >구매자 번호</option>
								                    <option value="receiverNum"
								                    	<c:if test="${osVO.searchType == 'receiverNum' }">
															 selected="selected"
														</c:if>
								                    >수령인 번호</option>
								                    <option value="orderNum"
								                    	<c:if test="${osVO.searchType == 'orderNum' }">
															 selected="selected"
														</c:if>
								                    >주문번호</option>
								                    <option value="receiverAddress"
								                    	<c:if test="${osVO.searchType == 'receiverAddress' }">
															 selected="selected"
														</c:if>
								                    >주소</option>
								                    <option value="orderProductNum"
								                    	<c:if test="${osVO.searchType == 'orderProductNum' }">
															 selected="selected"
														</c:if>
								                    >상품주문번호</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="matchingOption"
								                    	<c:if test="${osVO.searchType == 'matchingOption' }">
															 selected="selected"
														</c:if>
								                    >옵션 옵션명</option>
								                    <option value="storeProduct"
								                    	<c:if test="${osVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품명(옵션명)</option>
								                    <option value="invoiceNum"
								                    	<c:if test="${osVO.searchType == 'invoiceNum' }">
															 selected="selected"
														</c:if>
								                    >송장번호</option>
								                    <option value="deliveryMessage"
								                    	<c:if test="${osVO.searchType == 'deliveryMessage' }">
															 selected="selected"
														</c:if>
								                    >요청사항</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
				                           
	                                            <label class="custom-control custom-checkbox custom-control-inline" style="float:right;">
	                                                <input type="checkbox" class="custom-control-input" id="showExSearchBar"><span class="custom-control-label"> 검색 확장 </span>
	                                            </label>
	                                        
		                        		</div>
		                        	
		                        	</div>
		                        </div>
		                        
		                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="exSearchBar" style="display: none;">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="포함하는 조건" 
		                        			data-content="해당 조검을 포함하는 송장을 검색합니다">제외 검색</button>
				                        	<div class="btn-group">
				                            	<select class="form-control" name="storeSection">
								                    <option value="0">전체 판매처</option>
								                    <c:forEach var="sslist" items="${ssList }">						                    	
									                    <option value="${sslist.ssPk }"> ${sslist.ssName }</option>
								                    </c:forEach>
								                    <option value="-1">개인주문</option>
							                    </select>                                 
				                            </div>
				                            <div class="btn-group">
				                            	<select class="form-control" name="searchType">
				                            		<option value="orderNames" 
								                    	<c:if test="${osVO.searchType == 'orderNames' }">
															 selected="selected"
														</c:if>
								                    >구매자 / 수령자 통합</option>
								                    <option value="buyerName" 
								                    	<c:if test="${osVO.searchType == 'buyerName' }">
															 selected="selected"
														</c:if>
								                    >구매자</option>
								                    <option value="receiverName"
								                    	<c:if test="${osVO.searchType == 'receiverName' }">
															 selected="selected"
														</c:if>
								                    >수령인</option>
								                    <option value="buyerNum"
								                    	<c:if test="${osVO.searchType == 'buyerNum' }">
															 selected="selected"
														</c:if>
								                    >구매자 번호</option>
								                    <option value="receiverNum"
								                    	<c:if test="${osVO.searchType == 'receiverNum' }">
															 selected="selected"
														</c:if>
								                    >수령인 번호</option>
								                    <option value="orderNum"
								                    	<c:if test="${osVO.searchType == 'orderNum' }">
															 selected="selected"
														</c:if>
								                    >주문번호</option>
								                    <option value="receiverAddress"
								                    	<c:if test="${osVO.searchType == 'receiverAddress' }">
															 selected="selected"
														</c:if>
								                    >주소</option>
								                    <option value="orderProductNum"
								                    	<c:if test="${osVO.searchType == 'orderProductNum' }">
															 selected="selected"
														</c:if>
								                    >상품주문번호</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="matchingOption"
								                    	<c:if test="${osVO.searchType == 'matchingOption' }">
															 selected="selected"
														</c:if>
								                    >옵션 옵션명</option>
								                    <option value="storeProduct"
								                    	<c:if test="${osVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품명(옵션명)</option>
								                    <option value="invoiceNum"
								                    	<c:if test="${osVO.searchType == 'invoiceNum' }">
															 selected="selected"
														</c:if>
								                    >송장번호</option>
								                    <option value="deliveryMessage"
								                    	<c:if test="${osVO.searchType == 'deliveryMessage' }">
															 selected="selected"
														</c:if>
								                    >요청사항</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
		                        		</div>
		                        	
		                        	</div>
		                        </div>
                        
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 미부여송장 목록 ( <fmt:formatNumber value="${OrderSearchVO.totalRecord }" pattern="#,###" /> 개) </h5>
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr style="text-align: center;">
                                            	<th scope="col"> 순서 </th>
                                            	<th scope="col">
			                                        <label class="custom-control custom-checkbox be-select-all">
									             	   <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orSeiralSpecialNumberAllSelect"><span class="custom-control-label"></span>
									                </label>
		                                        </th>
                                                <th scope="col"> 구매자 / 수령자</th>
                                                <th scope="col"> 주소 </th>
                                                <th scope="col"> 구매 상품 </th>
                                                <th scope="col"> 매칭 상품 </th>
                                                <th scope="col"> 총 합 </th>
                                            </tr>
                                        </thead>
                                        <tbody class="dataTable">
                                        	<c:if test="${packingIrreOrderCounting != 0 }">
                                        		<td colspan="7" style="background-color: #FFA2A2; text-align: center;"><a href="<c:url value='/order/config/packing_irre_order_list.do'/>" target="_blank" style="color:white;"> 묶음 배송이 가능한 주문서가 있습니다 </a></td>
                                        	</c:if>
                                        	<c:set var="tableCountings" value="1"/>
                                        	<c:set var="numCounting" value="${OrderSearchVO.firstRecordIndex + 1 }"/>
                                        	<c:set var="rowCounting" value="1"/>
	                                        	<c:set var="backgroundBoolean" value="1"/>
	                                        	<c:forEach var="orlist" items="${orderList }">
	                                        		<c:set var="totalProductQty" value="0" />
	                                        		<c:set var="rowSpans" value="${fn:length(orlist.orVoList)}"/>
	                                        		<c:forEach var="stocklist" items="${orlist.orVoList }">
	                                        			<tr
	                                        			<c:if test="${backgroundBoolean % 2 == 0}">
			                                        		style="background-color:#edeef4;"
			                                        	</c:if>
	                                        			>
	                                        			<c:if test="${rowCounting == 1 }">
	                                        				<td rowspan="${rowSpans }" class="customerInfo" style="width:50px; text-align: center;" data-table-info="${tableCountings }">
								                            	${numCounting }
								                            </td>
	                                        				<td rowspan="${rowSpans }" style="width:20px; text-align: center;" data-table-info="${tableCountings }">
	                                        					<label class="custom-control custom-checkbox be-select-all">
												             	   <input class="custom-control-input chk_all" value="${orlist.orSerialSpecialNumber }" type="checkbox" name="orSerialSpecialNumberList"><span class="custom-control-label"></span>
												                </label>
								                            </td>
			                                        		<td rowspan="${rowSpans }" style="width:260px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orBuyerName } / ${orlist.orBuyerContractNumber1 }<br>
								                                ${orlist.orReceiverName } / ${orlist.orReceiverContractNumber1 }</p>
								                                <button class="btn btn-outline-success btn-xs devideOrderButton" value="${orlist.orSerialSpecialNumber }">주문서 분리</button>
								                            </td>
								                            <td rowspan="${rowSpans }" style="width:300px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orShippingAddress } ${orlist.orShippingAddressDetail }</p>
								                            	<button class="btn btn-outline-success btn-xs editCustomerInfoBtn" value="${orlist.orSerialSpecialNumber }">주소 변경</button>
								                            </td>
			                                        	</c:if>
			                                        	<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
			                                        		
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																		<span style="font-size: 12px;"> ${stocklist.orProduct }</span><br>
																		<span style="font-size: 12px;"> ${stocklist.orProductOption } </span><a data-or-pk="${stocklist.orPk }" class="SelfdevisionOrderBtn" style="color:red; font-size:10px"> [ 나누기 ]</a>
					                                        		</td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        			
	                                        			<c:if test="${!empty stocklist.productMatchingVOList }">
	                                        					                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
			                                        		
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productDetail" data-table-product-detail="${tableCountings }">
																		<c:if test="${!empty pmlist.optionMatchingVOList }">																		
							                                        		<c:forEach var="opmlist" items="${pmlist.optionMatchingVOList }">
								                                        		<c:forEach var="proOplist" items="${opmlist.productOptionList }">
								                                        			<c:if test="${!empty proOplist.optionName }">								                                        			
									                                        			<span style="font-size: 12px;">${proOplist.productName } [ ${proOplist.optionName } ] ${proOplist.cfFk } 개</span><br>
									                                        			<c:set var="totalProductQty" value="${totalProductQty + proOplist.cfFk }"/>
								                                        			</c:if>
								                                        		</c:forEach>

							                                        		</c:forEach>
																		</c:if>
					                                        		</td>
					                                        		<td class="customerInfo totalProductQty" style="width:90px; text-align: center;" data-table-info="${tableCountings }" data-total-qty="${totalProductQty }">
										                            	${totalProductQty } 개 
										                            </td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        		</tr>
	                                        		<c:set var="rowCounting" value="${rowCounting+1 }"/>
	                                        		</c:forEach>
		                                        	<c:set var="backgroundBoolean" value="${backgroundBoolean + 1 }"/>
		                                        	<c:set var="rowCounting" value="1"/>
		                                        	<c:set var="numCounting" value="${numCounting + 1 }"/>
		                                        	<c:set var="tableCountings" value="${tableCountings + 1 }"/>
		                                        	<c:set var="totalProductQty" value="0"/>
		                                        	
	                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation example" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${OrderSearchVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${OrderSearchVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${OrderSearchVO.lastPage}" begin="${OrderSearchVO.firstPage }">
										<li class="page-item 
											<c:if test='${OrderSearchVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${OrderSearchVO.lastPage < OrderSearchVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${OrderSearchVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
                    </div>
            </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>       
		
		<script type="text/javascript">
		 $(document).ready(function(e){
		        genRowspan("totalProductQty");
		    });
		     
		    function genRowspan(className){
		    	
		        $("." + className).each(function( i , selector) {
		        	var alarmQty = $("select[name=totalQtyAlarm]").val();
		        	
		        	var values = $(selector).data("table-info");
		            var rows = $("." + className+"[data-table-info='"+values+"']");
		            
		            if (rows.length > 1) {
		                rows.eq(0).attr("rowspan", rows.length);
		                
		                if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
		                	rows.eq(0).css("color", "red");
		                	rows.eq(0).css("font-weight", "bolder");
		                }
		                
		                rows.eq(0).text( rows.eq( (rows.length - 1) ).text() );
		                rows.not(":eq(0)").remove();
		            }else{
		            	
		            	if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
		                	rows.eq(0).css("color", "red");
		                	rows.eq(0).css("font-weight", "bolder");
		                }
		                
		            	
		            }
		        });
		    }
		</script>
        <%@ include file="../inc/bottom.jsp" %>
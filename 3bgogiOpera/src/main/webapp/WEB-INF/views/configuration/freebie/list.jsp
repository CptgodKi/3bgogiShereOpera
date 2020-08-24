<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		//판매처 감추기
    		$('.cdStoreCompanyDiv').hide();
    		
    		$('input[name=cdLossFlag]').change(function(){
    			if($(this).val()=='1'){
    				$('.cdLossRateDiv').show();
    			}else{
    				$('.cdLossRateDiv').hide();
    			}
    		});
    		
    		$('input[name=cdCompanyDiagnosis]').change(function(){
    			if($(this).val()=='1'){
    				$('.cdManufacturerDiv').show();
    				$('.cdStoreCompanyDiv').hide();
    				
    			}else{
    				$('.cdManufacturerDiv').hide();
    				$('.cdStoreCompanyDiv').show();
    			}
    		});
    		
    		//원가에 숫자가 아닌 문자가 들어갈 경우 초기화 시킴
    		$("#cdCost").keyup(function(){
    			var regex=  /^\d+$/;
    			
    			var cdCost = $(this).val();
    			
    			if(!regex.test(cdCost)){
    				alert("숫자만 입력해야 합니다.");
    				$(this).val("0");
    			}
    		});
    		
    		//손실률에 숫자가 아닌 문자가 들어갈 경우 초기화 시킴
    		$("#cdLossRate").keyup(function(){
    			var regex=  /^\d+$/;
    			
    			var cdLossRate = $(this).val();
    			
    			if(!regex.test(cdLossRate)){
    				alert("숫자만 입력해야 합니다.");
    				$(this).val("0");
    			}
    		});
    				
    	});
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#costDetailListForm").submit();
		}
    	
    	function updateFreebie(fbPk){
    		location.href="<c:url value='/config/freebie/update.do?fbPk="+fbPk+"'/>";
    	}
    </script>
    <style type="text/css">
    	.table-3bgogi-hover:hover{
    		background: #FFC6C6;
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
                            <h2 class="pageheader-title"> 데이터 관리 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 데이터 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 데이터 입력 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 원가 상세사항 입력</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                <div class="ecommerce-widget">
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">입력된 원가 목록</h5>
                                <div class="card-body">
                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
	                                	<button class="btn btn-success btn-xs mb-2" type="button" id="deleteFreebie"> 해당 사은품 정책 삭제 </button>
	                                </div>
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align: center;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="4%">
	                                                	<label class="custom-control custom-checkbox be-select-all">
								                        	<input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="fbAllSelect"><span class="custom-control-label"></span>
								                        </label>
	                                                </th>
	                                                <th width="20%">사은품 정책명</th>
	                                                <th width="20%">판매처</th>
	                                                <th width="10%">기간</th>
	                                                <th width="20%">상품명</th>
	                                                <th width="15%">합계 금액</th>
	                                                <th width="20%">등록일</th>
	                                                <th width="8%">수정</th>
	                                                
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty fbList }">
	                                        		<tr>
	                                        			<td colspan="8" style="text-align: center;"> 등록된 사은품 정책이 없습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty fbList }">
		                                        	<c:forEach var="fblist" items="${fbList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td scope="row" class="checkTd">
				                                            	<label class="custom-control custom-checkbox be-select-all">
										                        	<input class="custom-control-input chk_all" type="checkbox" name="fbPk"
										                        	value="${fblist.fbPk }"><span class="custom-control-label"></span>
										                        </label>
				                                            </td>
			                                                <td>${fblist.fbName }</td>
			                                                <td>${fblist.ssList }</td>
			                                                <td>${fblist.fbMinDate } ~ ${fblist.fbMaxDate }</td>
			                                                <td>${fblist.productName } [ ${fblist.optionName } ]</td>
			                                                <td><fmt:formatNumber value="${fblist.fbMinPrice }" pattern="#,###" />원  ~ <fmt:formatNumber value="${fblist.fbMaxPrice }" pattern="#,###" />원</td>
			                                                <td><fmt:formatDate value="${fblist.fbRegdate }" pattern="yyyy-MM-dd"/> </td>
			                                                <td><button type="button" class="btn btn-primary btn-xs" onclick="updateFreebie('${fblist.fbPk}')">수정</button></td>
			                                            </tr>
		                                        	</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${PaginationInfo.currentPage}">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${PaginationInfo.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${PaginationInfo.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${PaginationInfo.lastPage}" begin="${PaginationInfo.firstPage }">
										<li class="page-item 
											<c:if test='${PaginationInfo.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${PaginationInfo.lastPage < PaginationInfo.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${PaginationInfo.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>
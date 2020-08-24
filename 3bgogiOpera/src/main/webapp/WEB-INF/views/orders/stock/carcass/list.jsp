<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

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
    		
    				
    	});
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
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
                            <h2 class="pageheader-title"> 도체 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 재고 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 원육 관리 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 도체 목록 </li>
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
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">입력된 도체 목록</h5>
                                <div class="card-body">
                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-3">
	                                	<a id="insertCarcass" class="btn btn-primary btn-xs mb-2" href="<c:url value='/stock/carcass/insert.do'/>"> 도체 등록하기 </a>
	                               	</div>
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="17%">품목명</th>
	                                                <th width="17%">두수</th>
	                                                <th width="15%">이력번호</th>
	                                                <th width="15%">무게</th>
	                                                <th width="10%">구매처</th>
	                                                <th width="10%">구매가</th>
	                                                <th width="8%">등록자</th>
	                                                <th width="8%">명세서</th>
	                                                <th width="8%">입고일</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="costDetaillist" items="${costDetailList }">                                        	
		                                            <tr class="table-3bgogi-hover">
		                                                <td>${costDetaillist.costCodeVOList[0].ccCodeType }</td>
		                                                <td>${costDetaillist.cdName }</td>
		                                                
		                                                <td><fmt:formatNumber value="${costDetaillist.cdCost }" pattern="#,###"/>원</td>
		                                                
		                                                <c:if test="${costDetaillist.cdLossFlag == true }">
		                                                	<td>${costDetaillist.cdLossRate }%</td>
		                                                </c:if>
		                                                <c:if test="${costDetaillist.cdLossFlag == false }">
		                                                	<td style="color:red;">없음</td>
		                                                </c:if>
		                                                <td>
		                                                	<c:if test="${costDetaillist.cdLossFlag == true }">
		                                                		<c:set var="lossCal" value=""/>
		                                                		<fmt:formatNumber value="${costDetaillist.cdCost + (costDetaillist.cdCost*costDetaillist.cdLossRate/100) }" pattern="#,###"/>원
		                                                	</c:if>
		                                                	<c:if test="${costDetaillist.cdLossFlag == false }">
		                                                		<fmt:formatNumber value="${costDetaillist.cdCost }" pattern="#,###"/>원
		                                                	</c:if>
		                                                </td>
		                                                <td>${costDetaillist.cdMeasure }</td>
		                                                <td>
		                                                	<c:if test="${costDetaillist.cdCompanyDiagnosis == true }">
		                                                		제조사 : ${costDetaillist.cdManufacturer }
		                                                	</c:if>
		                                                	<c:if test="${costDetaillist.cdCompanyDiagnosis == false }">
		                                                		판매처 : ${costDetaillist.cdStoreCompany }
		                                                	</c:if>
		                                                </td>
		                                                <td></td>
		                                            </tr>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        	
                        	
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="costDetailListForm" action="<c:url value='/products/list/cost_detail.do'/>" method="get">
                        	<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
								<div class="card">
									<div class="card-body">
										<div class="input-group">
	                                        <div class="input-group-prepend">
	                                        
		                                        <select class="form-control" id="ssList" name="ssList" style="height: calc(2.25rem + 5px);">
									                <option value="cil_product">품목명</option>
									                <option value="cil_purchase_store">구매처</option>
									                <option value="cil_admin">등록자</option>
									                <option value="cil_animal_prod_trace_num">이력번호</option>
								                </select>
	                                        </div>
	                                        <input type="text" class="form-control form-control-sm">
                                        </div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 페이지당 목록 개수 </h3>
										<select class="form-control" name=recordCountPerPage>
											<option value="10"
												<c:if test="${osVO.recordCountPerPage == 10 }">
													selected="selected"
												</c:if>
											>10 개씩</option>
											<option value="20"
												<c:if test="${osVO.recordCountPerPage == 20 }">
													selected="selected"
												</c:if>
											>20 개씩</option>
											<option value="30"
												<c:if test="${osVO.recordCountPerPage == 30 }">
													selected="selected"
												</c:if>
											>30 개씩</option>
											<option value="40"
												<c:if test="${osVO.recordCountPerPage == 40 }">
													selected="selected"
												</c:if>
											>40 개씩</option>
										</select>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 입고일 </h3>
										<div class="form-row">
											<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
												<input type="text" class="form-control" name="dateStart" id="dateStart" placeholder="시작일" required value="">
											</div>
											<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
												<input type="text" class="form-control" name="dateEnd" id="dateEnd" placeholder="종료일" required value="">
											</div>
										</div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 구입금액대 </h3>
										<div class="form-row">
											<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMinPrice" id="minCost" placeholder="최소금액" required value="">
											</div>
											<div class="col-xl-2 col-lg-2 col-md-2 col-sm-12 col-12"> 
												원부터
											</div>
											<div class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMaxPrice" id="maxCost" placeholder="최대금액" required value="">
											</div>
											<div class="col-xl-1 col-lg-1 col-md-1 col-sm-12 col-12">
												원
											</div>
										</div>
									</div>
									<div class="card-body border-top">
										<button type="submit" class="btn btn-secondary btn-lg btn-block"> 검색하기 </button>
									</div>
								</div>
							</form>
						</div>
						<%-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
									<c:if test='${osVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
										<li class="page-item 
											<c:if test='${osVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${osVO.lastPage < osVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div> --%>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>
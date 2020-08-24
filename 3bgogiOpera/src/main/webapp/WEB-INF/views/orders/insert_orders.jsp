<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %> 
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
                            <h2 class="pageheader-title"> 주문 등록 페이지 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 등록 </a></li>
                                        
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
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success blinking"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn"> 특수 지역 체크  </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn"> 사은품 부여  </a>    
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn"> 옵션 매칭 </a>
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 엑셀 주문서 입력 ( 변형되지 않은 원본을 넣어야함 ) </h5>
                                <div class="card-body">
                                    <form id="form" method="post" action="<c:url value='/orders/order_page.do'/>" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <div class="col-3 col-lg-2 col-form-label text-right">
                                            	<select class="custom-select d-block w-100" id="ssFk" name="ssFk">
													<c:if test="${!empty storeList }">
														<c:forEach var="storelist" items="${storeList }">
															<option value="${storelist.ssPk }">${storelist.ssName }</option>
														</c:forEach>
													</c:if>
													<c:if test="${empty storeList }">
														<option value="0">현재 등록된 판매처가 존재 하지 않습니다.</option>
													</c:if>
												</select> 
                                            </div>
                                            <div class="col-9 col-lg-10">
                                                <input id="smartstore" type="file" name="excelfile" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row pt-2 pt-sm-5 mt-1">
                                            <div class="col-sm-6 offset-6">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-secondary"> 주문서 입력 </button>
                                                </p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 입력 된 주문서 목록</h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">판매처</th>
                                                <th scope="col">주문 수</th>
                                                <th scope="col">입력일</th>
                                                <th scope="col">삭제여부</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:if test="${empty insertStoreOrderList}">
                                        		<tr>
	                                                <th scope="row" colspan="4" style="text-align: center;"> 당일 주문서 값이 없습니다 </th>
	                                            </tr>
                                        	</c:if>
                                        	<c:if test="${!empty insertStoreOrderList}">                                        	
	                                        	<c:forEach var="isoList" items="${insertStoreOrderList }">
	                                        		<tr>
		                                                <th scope="row">${isoList.ssName }</th>
		                                                <td>${isoList.orPk } 건 </td>
		                                                <td>${isoList.orRegdate }</td>
		                                                <td>
		                                                	<button type="button" class="btn btn-danger"> 삭제 </button>
		                                                </td>
		                                            </tr>
	                                        	</c:forEach>
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
					</div>
	            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>



<!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="dashboard-ecommerce">
                <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 종합 처리 현황  </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <div class="ecommerce-widget">
                    	<c:set var="matching_fail" value="${deliveryResult[0].matching_fail == null ? 0 : deliveryResult[0].matching_fail }" />
	                    <c:set var="dont_grant_invoice_num" value="${deliveryResult[0].dont_grant_invoice_num == null ? 0 : deliveryResult[0].dont_grant_invoice_num }" />
	                    <c:set var="output_weiting_order" value="${deliveryResult[0].output_weiting_order == null ? 0 : deliveryResult[0].output_weiting_order }" />
	                    <c:set var="output_order" value="${deliveryResult[0].output_order == null ? 0 : deliveryResult[0].output_order }" />
	                    <c:set var="reserv_order" value="${deliveryResult[0].reserv_order == null ? 0 : deliveryResult[0].reserv_order }" />
                        <div class="row">
                        	 <!--  일반 통계 -->
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 재고 미할당 (상품 미매칭 ) </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1">${matching_fail } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 송장 대기중 ( 예약 건 ) </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1">${dont_grant_invoice_num } 건 ( ${reserv_order } 건 )</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 발송 대기중 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${output_weiting_order } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 오늘 발송 완료 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${output_order } 건</h1>
                                        </div>
                                        
                                        <!-- <div class="metric-label d-inline-block float-right text-danger font-weight-bold">
                                            <span class="icon-circle-small icon-box-xs text-danger bg-danger-light bg-danger-light "><i class="fa fa-fw fa-arrow-down"></i></span><span class="ml-1">4%</span>
                                        </div> -->
                                        
                                        
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="overview-item overview-item--c1">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-account-o"></i>
                                            </div>
                                            <div class="text">
                                                <span> 입력된 주문 건</span>
                                                <h4> 최근 6일 </h4>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart1"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="overview-item overview-item--c2">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-shopping-cart"></i>
                                            </div>
                                            <div class="text">
                                                <span> 판매 물량 </span>
                                                <h4> 최근 7일 </h4>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart2"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- <div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="overview-item overview-item--c3">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-calendar-note"></i>
                                            </div>
                                            <div class="text">
                                                <span> - 구축중</span>
                                                <h2>1,086</h2>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart3"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <h5 class="card-header"> 최소 보유치 이하로 내려간 상품 재고 목록 </h5>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover" style="table-layout: fixed; word-break: keep-all;">
                                                <thead class="bg-light">
                                                    <tr class="border-0">
                                                        <th class="border-0"> 상품명 </th>
                                                        <th class="border-0"> 옵션명 </th>
                                                        <th class="border-0"> 재고 개수 </th>
                                                        <th class="border-0"> 최소 보유량 </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:if test="${empty productOptionList}">
                                                		<tr>
                                                			<td colspan="5"> 최소 보유량 이하로 내려간 상품이 없습니다 </td>
                                                		</tr>
                                                	</c:if>
                                                	<c:if test="${!empty productOptionList}">
														<c:forEach var="productOptionlist" items="${productOptionList }">														
		                                                    <tr>
		                                                        <td>${productOptionlist.productName }</td>
		                                                        <td>${productOptionlist.optionName }</td>
		                                                        <td>${productOptionlist.optionStock }</td>
		                                                        <td>${productOptionlist.optionStockMaxAlarm }</td>
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
                        <div class="row">
                            <!-- ============================================================== -->
                            <!-- ============================================================== -->
                                          <!-- recent orders  -->
                            <!-- ============================================================== -->
                            <div class="col-xl-12 col-lg-12 col-md-6 col-sm-12 col-12">
                                <div class="card">
                                    <h5 class="card-header"> 입고된 상품 내역 </h5>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-hover" style="table-layout: fixed; word-break: keep-all;">
                                            	<colgroup>
													<col width="100px" />
													<col width="100px" />
													<col width="80px" />
													<col width="50px" />
													<col width="180px" />
													<col width="180px" />
													<col width="180px" />
													<col width="180px" />
													<col width="150px" />
												</colgroup>
												
                                                <thead class="bg-light">
                                                    <tr class="border-0">
                                                        <th class="border-0">상품</th>
                                                        <th class="border-0">입고가</th>
                                                        <th class="border-0">입고량</th>
                                                        <th class="border-0">등급</th>
                                                        <th class="border-0">이력번호</th>
                                                        <th class="border-0">도축장</th>
                                                        <th class="border-0">원산지</th>
                                                        <th class="border-0">품목제조번호</th>
                                                        <th class="border-0">등록일</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:if test="${empty costInputList}">
                                                		<tr>
                                                			<td colspan="9"> 내역이 없습니다 </td>
                                                		</tr>
                                                	</c:if>
                                                	<c:if test="${!empty costInputList}">
														<c:forEach var="costInputlist" items="${costInputList }">														
		                                                    <tr>
		                                                        <td>${costInputlist.cdName }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciPrice }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciWeight }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciLevel }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciAnimalProdTraceNum }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciAbattoir }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciCountryOfOrigin }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciItemsManufNum }</td>
		                                                        <td><fmt:formatDate value="${costInputlist.costIoVOList[0].ciRegdate }"/></td>
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
                    </div>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/resources/libs/test_js/popper.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/bootstrap-4.1/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/slick/slick.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/wow/wow.min.js"></script>>
            <script src="${pageContext.request.contextPath}/resources/libs/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/test_js/main.js"></script>
<script type="text/javascript">

</script>
        <%@ include file="../inc/bottom.jsp" %>
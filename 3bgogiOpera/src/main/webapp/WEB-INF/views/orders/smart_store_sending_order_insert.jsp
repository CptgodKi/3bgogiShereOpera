<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	
    </script>
    
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
                            <h2 class="pageheader-title"> 스마트스토어 배송중 주문서 데이터 삽입 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 스마 배송중 주문 등록 </a></li>
                                        
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
                            <div class="card">
                                <h5 class="card-header"> 배송중 엑셀 주문서 입력 ( 변형되지 않은 원본을 넣어야함 ) <span class="alert alert-danger"> 수수료 등 정확하지 않게 들어감</span> </h5>
                                <div class="card-body">
                                    <form id="form" method="post" action="<c:url value='/orders/smart_store_sending_order_insert.do'/>" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <div class="col-3 col-lg-2 col-form-label text-right">
                                            	<select class="custom-select d-block w-100" id="ssFk" name="ssFk">
													<option value="3">네이버 스마트스토어</option>
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
					</div>
	            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ============================================================== -->
        <!-- left sidebar -->
        <!-- ============================================================== -->
        <div class="nav-left-sidebar sidebar-dark">
            <div class="menu-list">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="d-xl-none d-lg-none" href="#"> 메뉴 열람 </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav flex-column">
                            <li class="nav-divider">
                               	 선택 메뉴
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-1" aria-controls="submenu-1"><i class="fa fa-fw fa-user-circle"></i>개인 <span class="badge badge-success">6</span></a>
                                <div id="submenu-1" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/admin/attendance/admin_attendance_status.do'/>"> 출결 관리 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/project/projects.do'/>">업무</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);">업무 진행도 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);"> 업무 파일 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-1-1" aria-controls="submenu-1-1"> 로그 </a>
                                            <div id="submenu-1-1" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="javascript void:(0)"> 업무 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="javascript void:(0)"> 메모 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#team" aria-controls="team"><i class="fas fa-fw fa-sitemap"></i> 팀  </a>
                                <div id="team" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);"> 팀 프로젝트 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);"> 팀원 정보  </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#stock_manage" aria-controls="stock_manage"><i class="fas fa-archive"></i> 재고 관리  </a>
                                <div id="stock_manage" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#product_manage" aria-controls="product_manage"> 상품 관리 </a>
                                            <div id="product_manage" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                	<li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/manage.do'/>"> 상품 입고 </a>
						                            </li>
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/stock_req_list.do'/>"> 상품 입고 내역 </a>
						                            </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                    
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#meat_manage" aria-controls="meat_manage"> 원육 관리 </a>
                                            <div id="meat_manage" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="#"> 부분 원육 입고  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="#"> 도체 원육 입고  </a>
						                            </li>
						                            
						                            <li class="nav-item">
						                            	<a class="nav-link" href="#"> 원육 입고 내역  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/carcass/list.do'/>"> 등록된 도체 목록  </a>
						                            </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                    
                                </div>
                            </li>
                            <li class="nav-divider">
                               	 관리자
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#sending" aria-controls="sending"><i class="fas fa-fw fa-shipping-fast"></i> 발송 및 출고  </a>
                                <div id="sending" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/sending.do'/>"> 상품 출고 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/store_order_sending.do'/>"> 판매처 송장부여 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/security/create_deliv_invoice.do'/>"> 송장 생성 테스트 </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            
                            
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#customer_service" aria-controls="sending"><i class="fas fa-address-book"></i> C / S  </a>
                                <div id="customer_service" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/orders/search/customer_orders.do'/>"> 고객 검색  </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link createNewOrder" href="#"> 새로운 주문 생성 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" target="_blank" href="<c:url value='/aligo_msg/view.do'/>"> Aligo 문자  </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-6" aria-controls="submenu-6"><i class="fas fa-fw fa-file"></i> 주문서 </a>
                                <div id="submenu-6" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#store_order" aria-controls="store_order"> 판매처 주문 입력 </a>
                                            <div id="store_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/order_page.do'/>"> * 주문 등록 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/order/config/search_except_addr_order.do'/>"> * 특수 지역 체크  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/apply.do'/>"> * 사은품 부여 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value="/order/matching/products_matching.do"/>"> * 상품명 매칭 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value="/order/matching/option_matching.do"/>"> * 옵션명 매칭 및 원가체크 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/delivery_msg_check.do'/>"> * 요청 사항 체크 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/stk_check.do'/>"> * 재고 할당 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/cancled_order_check.do'/>"> * 취소 주문 체크 </a>
						                            </li>
						                            <li class="nav-item">
						                             	<a class="nav-link" href="<c:url value='/security/epost.do'/>"> * 송장 생성 </a>
						                            </li>
                                                </ul>
                                            </div>
                                        </li>
                                        
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#excel_order" aria-controls="excel_order"> 특이 주문 입력 </a>
                                            <div id="excel_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/smart_store_sending_order_insert.do'/>" style="color:#ffa3a3;"> 네이버 배송중 주문 등록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="javascript:void(0);"> 네이버 아이디 등록 -계획중 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#irregular_order" aria-controls="irregular_order"> 고객 필터링 </a>
                                            <div id="irregular_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/list.do'/>"> 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/add.do'/>"> 추가하기 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/all_list.do'/>"> 전체 목록 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-7" aria-controls="submenu-7"><i class="fas fa-fw fa-inbox"></i> 데이터 관리 <span class="badge badge-secondary">New</span></a>
                                <div id="submenu-7" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-11" aria-controls="submenu-11"> 데이터 입력 </a>
                                            <div id="submenu-11" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/cost_detail.do'/>"> 원가 상세사항 입력 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/costs.do'/>"> 다중 원가 입력 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/product.do'/>"> 상품 등록 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#dataList" aria-controls="dataList"> 데이터 목록 </a>
                                            <div id="dataList" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                	<li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/cost_detail.do'/>"> 원가 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/costs.do'/>"> 다중 원가 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/product_list.do'/>"> 상품 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
			                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#classification_code" aria-controls="classification_code"> 분류 코드 </a>
			                                            <div id="classification_code" class="collapse submenu" style="">
			                                                <ul class="nav flex-column">
			                                                    <li class="nav-item">
			                                                        <a class="nav-link" href="<c:url value='/products/insert/cost_detail.do'/>"> 원가 분류 코드  </a>
			                                                    </li>
			                                                    <li class="nav-item">
			                                                        <a class="nav-link" href="javascript:void(0);"> 상품 분류 코드 </a>
			                                                    </li>
			                                                    <li class="nav-item">
			                                                        <a class="nav-link" href="javascript:void(0);"> 자재 분류 코드 </a>
			                                                    </li>
			                                                </ul>
			                                            </div>
			                                        </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#analy" aria-controls="analy"> 통계 </a>
                                            <div id="analy" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/analytics/analy_search_list.do'/>"> 통계 선택 조회 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-8" aria-controls="submenu-8"><i class="fas fa-fw fa-columns"></i> 팀원 </a>
                                <div id="submenu-8" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/admin/team/team_auth.do'/>"> 팀원 권한 </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#configuration_setting" aria-controls="configuration_setting"><i class="fas fa-cogs"></i> 설정 </a>
                                <div id="configuration_setting" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/config/store/list.do'/>"> 판매처 설정 </a>
                                        </li>
                                        <li class="nav-item">
				                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#offline_store" aria-controls="offline_store"> 매장 </a>
				                            <div id="offline_store" class="collapse submenu" style="">
					                            <ul class="nav flex-column">
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/products/insert/cost_detail.do'/>"> 주문 등록  </a>
						                            </li>
					                            </ul>
				                            </div>
			                            </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/security/test/mdb.do'/>"> 우편번호 설정 </a>
                                        </li>
                                        <li class="nav-item">
				                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#freebie" aria-controls="freebie"> 사은품 정책 </a>
				                            <div id="freebie" class="collapse submenu" style="">
					                            <ul class="nav flex-column">
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/insert.do'/>"> 사은품 정책 추가  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/list.do'/>"> 사은품 정책 목록  </a>
						                            </li>
					                            </ul>
				                            </div>
			                            </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- end left sidebar -->
        <!-- ============================================================== -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 상품 출고 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#orDeliveryInvoiceNumber").focus();
		
	});
</script>
</head>
<body>
	<div class="container-fluid  dashboard-content" style="padding: 0;">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card" style="margin-bottom: 10px;">
					<form class="card-body" id="sendingForm">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td width="50%">
										<div class="input-group">
											<input type="text" class="form-control" id="orDeliveryInvoiceNumber">
											<div class="input-group-append">
												<button class="btn btn-primary btn-xs" type="submit">입력</button>
											</div>
										</div>
									</td>
									<td width="50%">
										<div class="input-group">
											<input type="text" class="form-control" disabled="disabled" id="invoiceValue">
											<div class="input-group-append">
												<button class="btn btn-secondary btn-xs" type="button">초기화</button>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="btn btn-secondary btn-xs btn-block" type="button">강제출고</button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr style="font-size: 10px;">
									<th width="70%">상품</th>
									<th width="15%">개수</th>
									<th width="15%">출고</th>
								</tr>
							</thead>
							<tbody id="sendingProductList">
							</tbody>
						</table>
						<form id="sendingTargetOrder">
						
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr style="font-size: 10px;">
									<th width="70%"> 출 고 완 료 상 품</th>
									<th width="15%">개수</th>
									<th width="15%">출고</th>
								</tr>
							</thead>
							<tbody id="finishedOrder" style="background:gray; color:white;">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body> 
<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_sending_manage.js"></script>
</html>

jQuery(document).ready(function($) {
	
	jQuery.ajaxSettings.traditional = true;
	
	var dataChangeFlag = false;
	
	$(".orderDetails").click(function(){
		orderOrPk = 0;
		orderCancledFlag = false;
		var orSerialSpecialNumber = $(this).data("serial-num");
		
		$.ajax({
		    type       : 'GET',
		    data       : {
		    	"orSerialSpecialNumber":orSerialSpecialNumber
		    },
		    url        : '/orders/search/customer_order_detail.do',
		    success    : function(data){
		    	var buyerId = data[0].orBuyerId == null ? '등록 X' : data[0].orBuyerId;

		    	$("#orderDetailCustomerName").text(" 구매자 : "+data[0].orBuyerName+"( id : "+buyerId+")");
		    	$("#orderDetailReceiverName").text(" 수령자 : "+data[0].orReceiverName);
		    	$("#orderDetailCustomerPhone").html('<i class="fas fa-fw fa-phone mr-2"></i> 구매자 : '+data[0].orBuyerContractNumber1+" / 수령인 : "+data[0].orReceiverContractNumber1);
		    	var orAddress = "";
		    	
		    	if(data[0].orShippingAddressDetail != null){
		    		orAddress = data[0].orShippingAddress+" "+data[0].orShippingAddressDetail;
		    		
		    	}else{
		    		orAddress =  data[0].orShippingAddress;
		    		
		    	}
		    	
		    	if(data[0].orDeliveryInvoiceNumber == null){
		    		dataChangeFlag=true;
		    	}else{
		    		dataChangeFlag=false;
		    	}
		    	
		    	$("#orderDetailShippingAddress").html('<i class="fas fa-home mr-2"></i>'+orAddress);
		    	$("#orderDetailSettlementDay").text(' 결제일 : '+ allFormatDate(data[0].orSettlementDay));
		    	$("#orderDetailCheckDay").text(' 발주확인일 : '+data[0].orCheckDay);
		    	$("#orderDetailInflowRoute").text(' 유입경로 : '+data[0].orInflowRoute);
		    	
		    	if(data[0].orDeliveryPrice >= 3000){
		    		if(data[0].orDeliveryDiscountPrice >= 100){
		    			$("#orderDetailDeliveryPrice").text(' 배송비 : '+data[0].orDeliveryPrice+" , 할인금액 : "+data[0].orDeliveryDiscountPrice);
		    		}else{
		    			$("#orderDetailDeliveryPrice").text(' 배송비 : '+data[0].orDeliveryPrice+" , 할인금액 : 없음");
		    		}
		    		
		    	}else{
		    		$("#orderDetailDeliveryPrice").text(' 배송비 : 우리측에서 부담');
		    		
		    	}
		    	
		    	var productQuantity = 0;
		    	for(var i=0; i<data.length; i++){
		    		productQuantity+=data[i].orAmount;
		    	}
		    	
		    	var orderTotalPrice = 0;
		    	var orderTotalCost = 0;
		    	for(var i=0; i<data.length; i++){
		    		orderTotalPrice+=data[i].orTotalPrice;
		    		orderTotalCost+=data[i].orTotalCost;
		    	}
		    	
		    	$("#orderDetailOrderQuantity").text(data.length+" 개");
		    	$("#orderDetailProductQuantity").text(productQuantity+" 개");
		    	$("#orderDetailTotalPrice").text(comma(orderTotalPrice)+" 원");
		    	
		    	productFormView(data);
		    }
		    
		});
	});
	
	function productFormView(orderList){
		var orderProductDetailList= "";
		
		for(var i=0; i<orderList.length; i++){
			
			orderProductDetailList +='<div class="card">'
					+'<div class="card-body" style="padding-bottom: 1px;">'
						+'<div class="row">'
							+'<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">'
								+'<div class="media influencer-profile-data d-flex align-items-center p-2">'
									+'<div class="media-body ">'
										+'<div class="influencer-profile-data">';
			
			
						orderProductDetailList += '<h5 class="m-b-10">'
												+orderList[i].orProduct;
						

						orderProductDetailList +='<span class="m-l-10 text-secondary"> '+orderList[i].orProductOption;
						if(orderList[i].orRequest != ''){
							orderProductDetailList +=' [ 특별 요청 : '+orderList[i].orRequest+' ] </span>';
						}else{
							orderProductDetailList +='</span>';
						}
						

						
											//매칭 상품, 옵션 단계
						orderProductDetailList +=  '</h5>';
						
												if(orderList[i].productMatchingChecking == false){
													orderProductDetailList +='<p style="margin: 0px 0px 5px 0px;">매칭상품명 : 매칭되지 않았습니다. , 매칭옵션명 : 매칭되지 않았습니다.</p>';
													
												}else{
													for(var j=0; j<orderList[i].productMatchingVOList.length; j++){
														
														for(var pp = 0; pp<orderList[i].productMatchingVOList[0].optionMatchingVOList.length; pp++){
															
															
															if(orderList[i].productMatchingVOList[j].optionMatchingVOList.length > 0){
																for(var omCounting = 0; omCounting < orderList[i].productMatchingVOList[j].optionMatchingVOList[pp].productOptionList.length; omCounting++ ){
																	
																	orderProductDetailList +='<p style="margin: 0px 0px 5px 0px;"> '
																		
																		+'<span class="d-inline-block"> 매칭 상품 : ' 
																		+'<span class="m-l-10 text-primary"';
																	if(orderList[i].orInvFlag == false){
																		orderProductDetailList +=' style="text-decoration: line-through;"';
																	}
																		
																	orderProductDetailList+=	'> ';
																	orderProductDetailList+=orderList[i].productMatchingVOList[j].optionMatchingVOList[pp].productOptionList[omCounting].productName+' </span>'
																	+'</span> <span class="m-r-20 d-inline-block">'; 
																	
																	if(orderList[i].productMatchingVOList[j].optionMatchingVOList[pp].productOptionList[omCounting].optionName != null){
																		orderProductDetailList +='<span class="m-l-10 text-secondary"';
																		
																		if(orderList[i].orInvFlag == false){
																			orderProductDetailList +=' style="text-decoration: line-through;"';
																		}
																		
																		orderProductDetailList +='> [ '
																			orderProductDetailList +=orderList[i].productMatchingVOList[j].optionMatchingVOList[pp].productOptionList[omCounting].optionName+' ] '+(orderList[i].orAmount*orderList[i].productMatchingVOList[j].optionMatchingVOList[pp].omOptionAmount)+' 개 </span>';
																		
																		if(orderList[i].orInvFlag == false){
																			orderProductDetailList +=' 재고 할당이 이뤄지지 않았습니다 ';
																		}
																		orderProductDetailList +='</p>';
																	}else{
																		orderProductDetailList +='<span class="m-l-10 text-secondary">  [ 옵션 매칭이 되지 않았습니다  ]</span>';
																		
																		if(orderList[i].orInvFlag == false){
																			orderProductDetailList +=' 재고 할당이 이뤄지지 않았습니다 ';
																		}
																		
																		orderProductDetailList +='</p>';
																	}
																}
															}
														}
													}
													
												}
												
						orderProductDetailList +='</div>'
									+'</div>'
								+'</div>'
							+'</div>'
						+'</div>'
					+'</div>'
					+'<div class="border-top card-footer p-0">'
						+'<div class="campaign-metrics d-xl-inline-block" style="width:10px; padding: 2px 30px 8px 17px;">'
							+'<label class="custom-control custom-radio custom-control-inline" style="margin: 0 auto; padding-left: 0rem;">'
		                    	+'<input type="radio" name="orPk" class="custom-control-input"  value="'+orderList[i].orPk+'" data-cancled-flag="'+orderList[i].orCancledFlag+'"><span class="custom-control-label"></span>'
		                    +'</label>'
						+'</div>'
						+'<div class="campaign-metrics d-xl-inline-block">'
							+'<h4 class="mb-0">상품주문번호</h4>'
							+'<p>'+orderList[i].orProductOrderNumber+'</p>'
						+'</div>';
						
						if(orderList[i].orCancledFlag == true){
							
							orderProductDetailList+='<div class="campaign-metrics d-xl-inline-block">'
							+'<h4 class="mb-0" style="color: red;">현재 주문취소된 상품입니다 </h4>'
						+'</div>';
						
						}else if(orderList[i].orRefunds > 0){
							
							orderProductDetailList+='<div class="campaign-metrics d-xl-inline-block">'
							+'<h4 class="mb-0" style="color: red;"> 환불 처리가 존재하는 상품입니다 총 '+orderList[i].orAmount+' 개 중  '+orderList[i].orRefunds+' 개</h4>'
						+'</div>';
						
						}else{
							orderProductDetailList+='<div class="campaign-metrics d-xl-inline-block">'
								+'<h4 class="mb-0">총 개수</h4>'
								+'<p>'+orderList[i].orAmount+' 개</p>'
							+'</div>'
							+'<div class="campaign-metrics d-xl-inline-block">'
								+'<h4 class="mb-0">배송메세지 </h4>';
							if(orderList[i].orDeliveryMessage == '' || orderList[i].orDeliveryMessage == null){
								orderProductDetailList += '<p> 배송메세지를 적지 않았습니다. </p>';
							}else{
								orderProductDetailList +='<p>'+orderList[i].orDeliveryMessage+'</p>';
							}
						}
						
						
				
					orderProductDetailList +='</div>'
					+'</div>'
				+'</div>';
			
		}
		
		$("#orderProductDetailHTML").html(orderProductDetailList);
	}
	
	//주문 삭제 기능
	$("#deleteOrderButton").click(function(){
		if(confirm("선택된 주문서를 정말 삭제하시겠습니까? ( 복구 불가 )")){			
			/*var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
			var orSerialSpecialNumberList = new Array(orSize);
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}
			*/
			
			var csSearchFormData = csSearchFormInsertAndReturn('name', 'orSerialSpecialNumberList');
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/delete/customer_order.do',
				success    : function(data){
					if(data == true){		
						alert("주문서 삭제 완료");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				}
				
			});
		}
	});
	
	function csSearchFormInsertAndReturn(attr_type, inputName){
		
		var orSize = $("input["+attr_type+"="+inputName+"]:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		var csSearchForm =  document.createElement("form");
		csSearchForm.method="POST";
		csSearchForm.id="csSearchForm";
		
		$("#csSearchIframe").append(csSearchForm);
		
		for(var i=0; i<orSize; i++){
			var orSerialSpecialNumberInput = document.createElement("input");
			orSerialSpecialNumberInput.name=inputName;
			orSerialSpecialNumberInput.type="hidden";
			orSerialSpecialNumberInput.value=$("input["+attr_type+"="+inputName+"]:checked")[i].value;
			$("#csSearchForm").append(orSerialSpecialNumberInput);
		}
		
		console.log(orSize);
		console.log(orSerialSpecialNumberList);
		return jQuery("#csSearchForm").serialize();
	}
	
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

	// 주문서 나누기 orSerialSpecialNumberList
	$("#devideOrderButton").click(function(){

		var orderCheckCounting = $("input[name=orSerialSpecialNumberList]:checked").length;
		
		if(orderCheckCounting > 1){
			alert("한 번에 두 개 이상의 주문서를 나눌 수 없습니다. ");
			
		}else if(orderCheckCounting === 0){
			alert("나눌 주문서를 체크해주세요");
			
		}else{
			/*alert($("input[name=orSerialSpecialNumberList]:checked")[0].value);*/
			
			
			window.open("/orders/config/devide.do?orSerialSpecialNumber="+$("input[name=orSerialSpecialNumberList]:checked")[0].value, "주문서 나누기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
			
		}
	});
	
	
	//주문서 합치기
	$("#combineOrderButton").click(function(){
		var orSize = $("input[data-deliv-weiting='1']:checked").length;
		
		if(orSize == 0){
			alert("합포 혹은 배송지 변경할 수 있는 주문서가 없습니다");
		}else{
			
			if(confirm(orSize+"개의 주문서를 합포 혹은 배송지 변경을 하시겠습니까?")){				
					var orSerialSpecialNumberList = new Array(orSize);
					
					for(var i=0; i<orSize; i++){
						orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked")[i].value;
						
					}
					
					window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 합치기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
			}
		}
			
	});
	
	var orderOrPk = 0;
	var orderCancledFlag = false;
	
	$(document).on("click", "input[name=orPk]", function(){
		orderOrPk=$(this).val();
		orderCancledFlag = $(this).data("cancled-flag");
	});
	
	//환불 처리 하기 
	$("#refundOrderBtn").click(function(){
		var cancledCount = 0;
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}

		if(orderCancledFlag == true){
			alert("주문취소된 건은 환불처리가 불가능합니다");
			return false;
		}
		window.open("/orders/refund_order.do?orPk="+orderOrPk, "주문서 환불 " , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//특별 요청 사항 처리하기
	$("#specialReqBtn").click(function(){

		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}

		if(orderCancledFlag == true){
			alert("주문취소된 건은 특별 요청 처리가 불가능합니다");
			return false;
		}
		
		window.open("/orders/special_request.do?orPk="+orderOrPk, "특별 요청 사항 처리 " , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	
	//주문서 개수 나누기
	$("#SelfdevisionOrderBtn").click(function(){
		
		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 나눠주세요");
			return false;
		}
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}
		
		window.open("/orders/config/self_devide_order.do?orPk="+orderOrPk, "주문서 나누기" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//주문서 삭제하기
	$("#deleteOrderTargetingDeleteBtn").click(function(){
		
		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 삭제해주세요");
			return false;
		}
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}
		
		if(confirm("해당 건을 정말 삭제하시겠습니까?")){			
			$.ajax({
				type       : 'POST',
				data       : {
					"orPk":orderOrPk
				},
				url        : '/orders/delete/order_one.do',
				success    : function(data){
					if(data == true){		
						alert("해당 주문서 삭제 완료");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				}
				
			});
		}
		
	});
	
	$("#excelGiftSetBtn").click(function(){
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}
		
		window.open("/orders/devide/gift.do?orPk="+orderOrPk, "엑셀 주소지 입력" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//합포페이지 하나의 정보로 변경할 주문서 선택할 경우 액션
	$("input[name=changeOrderInfo]").change(function(){
		
		var orSerialSpecialNumber = $(this).data("serial-special-number");
		var orBuyerName = $(this).data("buyer-name"); 
		var orBuyerContractNumber1 = $(this).data("buyer-contract-number1");
		var orReceiverName = $(this).data("receiver-name");
		var orReceiverContractNumber1 = $(this).data("receiver-contract-number1");
		var orDeliveryMessage = $(this).data("delivery-message");
		var orShippingAddress = $(this).data("shipping-address");
		var orShippingAddressDetail = $(this).data("shipping-address-detail");
		var orShippingAddressNumber = $(this).data("shipping-address-number");
		var orSendingDeadline = $(this).data("sending-deadline");
		var orRegdate = $(this).data("regdate");
		
		$("#orSendingDeadline").val(orSendingDeadline);
		$("#orBuyerName").val(orBuyerName);
		$("#orReceiverName").val(orReceiverName);
		$("#orShippingAddress").val(orShippingAddress);
		$("#orShippingAddressDetail").val(orShippingAddressDetail);
		$("#orShippingAddressNumber").val(orShippingAddressNumber);
		$("#orReceiverContractNumber1").val(orReceiverContractNumber1);
		$("#orBuyerContractNumber1").val(orBuyerContractNumber1);
		$("#orDeliveryMessage").val(orDeliveryMessage);
		$("#orSerialSpecialNumber").val(orSerialSpecialNumber);
		$("#orRegdate").val(orRegdate);
		
	});
	
	$("#combineOrderBtn").click(function(){
		
		var params = jQuery("#combineOrderForm").serialize();
		
		$.ajax({
		    type       : 'POST',
		    url        : '/orders/config/combine_order.do',
		    data       : params,
		    success    : function(data){
		    	if(data == true){
		    		alert("주문서 합포 완료");
		    		opener.location.reload();
		    		self.close();
		    	}else{
		    		alert("주문서 합포 실패");
		    	}
		    }
		});
	});
	
	// 상품 변경 클릭
	$("#changeProductBtn").click(function(){
		
		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 변경해주세요");
			return false;
		}
		
		productMatching(orderOrPk);
		
	});
	
	//송장번호 수기 입력
	$(".insertDelivNum").click(function(){
		var orSerialSpecialNumber = $(this).val();
		
		window.open("/orders/config/edit_deliv_num.do?orSerialSpecialNumber="+orSerialSpecialNumber, "송장번호 입력" , "width=500px, height=620px, top=50px, left=50px, scrollbars=no");
	});
	
	//상품 변경 페이지 들어가기
	function productMatching(orPk){
    	
    	window.open("/orders/config/change_product_option.do?orPk="+orPk+"", "주문서 상품 변경" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    	
    	
    }
	
	//상품 추가 페이지 들어가기
	$("#addProductButton").click(function(){		
		var orSize = $("input[data-deliv-weiting='1']:checked").length;
		
		if(orSize == 0){
			alert("추가할 수 있는 주문건이 없습니다");
			
		}else{
			var orSerialSpecialNumberList = new Array(orSize);
			
			for(var i=0; i<orSize; i++){
				orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked")[i].value;
			}
			
			window.open("/orders/config/add_product_option.do?orSerialSpecialNumberList="+orSerialSpecialNumberList+"", "상품 추가" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
		}
		
	});
	
	$("#deleteDelivButton").click(function(){
		
		var orSize = $("input[data-deliv='1']:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("송장이 부여된 주문건이 없습니다"); 
			return false;
		}
		
		if(confirm(orSize+" 개의 송장을 삭제하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}

			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/security/epost/delete.do',
				success    : function(data){
					
					window.open("/security/deliv_result.do?delivResult="+data, "송장 삭제 결과" , "width=800px, height=700px, top=50px, left=50px");
					
				}
				
			});
		}
		
	});
	
	
	$("#outputBtn").click(function(){
		
		var orSize = $("input[data-deliv='1']:checked").length;
		
		if(orSize == 0){
			alert("발송할 수 있는 주문이 없습니다"); 
			return false;
		}
		
		if(confirm(orSize+" 개의 주문을 발송처리하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orVoList["+i+"].orSerialSpecialNumber";
				
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}

			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/order_output.do',
				success    : function(data){
					alert("발송완료");
					location.reload();
					
				}
				
			});
		}
		
	});
	
	// 복수 매칭된 상품 나누기
	$("#multiMatchingDivBtn").click(function(){
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다"); 
			return false;
		}
			
		$.ajax({
				type       : 'GET',
				data       : {
					"orPk":orderOrPk
				},
				url        : '/orders/multi_matching_devide.do',
				success    : function(data){
					alert(data);
					location.reload();
					
				}
				
		});

		
	});
	
	$("#outputCancledBtn").click(function(){
		var orSize = $("input[data-output='1']:checked").length;

		if(orSize == 0){
			alert("발송을 취소할 주문이 없습니다");
			return false;
		}
		
		if(confirm(orSize+" 개의 주문건을 발송 취소하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orVoList["+i+"].orSerialSpecialNumber";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-output='1']:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}

			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/order_output_canled.do',
				success    : function(data){		
					alert(data);
					
				}
				
			});
		}
		
	});
	
	$("#changeSendingDeadlineBtn").click(function(){
		var orSize = $("input[data-deliv-weiting='1']:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
			
		for(var i=0; i<orSize; i++){
			orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked")[i].value;
			
		}

		if(orSize == 0) {
			alert("변경할 주문 건이 없습니다");
			return false;
		}
		
		
		window.open('/orders/change/deadline.do?orSerialSpecialNumberList='+orSerialSpecialNumberList, "발송일 변경" , "width=430, height=500, top=200, left=1200, scrollbars=no");
	});

	$("#aligoSmsBtn").click(function(){
		
		var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("문자 발송 대상자가 선택 되지 않았습니다"); 
			return false;
		}
		
		if(confirm(orSize+" 건에 대해 문자를 발송하시겠습니까? ")){
			
			
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.target="aligoSms";
			csSearchForm.id="csSearchForm";

			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			
			$('input[name=orSerialSpecialNumberList]:checked').each(function(i, selected) {
				
				var AligoReceiver = document.createElement("input");
				AligoReceiver.name="aligoVOList["+i+"].receiver";
				AligoReceiver.type="hidden";
				AligoReceiver.value=$(selected).data("buyer-contract-number");
				
				var AligoDestination = document.createElement("input");
				AligoDestination.name="aligoVOList["+i+"].destination";
				AligoDestination.type="hidden";
				AligoDestination.value=$(selected).data("buyer-name");
				
				$("#csSearchForm").append(AligoReceiver);
				$("#csSearchForm").append(AligoDestination);
		     });
			
			window.open('', 'aligoSms', 'width=1000, height=700');
			frm = document.getElementById("csSearchForm");
			frm.action = "/aligo_msg/view.do";
			frm.target = "aligoSms";
			frm.method = "post";
			frm.submit();
		}
		
	});

	
}); // AND OF JQUERY
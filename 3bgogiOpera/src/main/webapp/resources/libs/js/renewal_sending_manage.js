jQuery(document).ready(function($) {
	
	function makeDelay(s){
		var timer = 0;
		
		return function(callback){
			clearTimeout(timer);
			time = setTimeout(callback, s);
		};
	};
	
	var oneMore = new Audio();
	var chekingProduct = new Audio();
	var emptyProduct = new Audio();
	var finished = new Audio();
	var nonExistsInvoiceNum = new Audio();
	var nonExistsProduct = new Audio();
	var productOneFinished = new Audio();
	var checkingProductOneMore = new Audio();
	var startSending = new Audio();
	var afterSending = new Audio();
	
	oneMore.src = "/resources/libs/sending_sound/one_more.wav";
	chekingProduct.src = "/resources/libs/sending_sound/cheking_product.wav";
	emptyProduct.src = "/resources/libs/sending_sound/empty_products.wav";
	finished.src = "/resources/libs/sending_sound/finished.wav";
	nonExistsInvoiceNum.src = "/resources/libs/sending_sound/non_exists_invoice_num.wav";
	nonExistsProduct.src = "/resources/libs/sending_sound/non_exists_product.wav";
	productOneFinished.src = "/resources/libs/sending_sound/product_one_finished.mp3";
	checkingProductOneMore.src = "/resources/libs/sending_sound/cheking_product_one_more.wav";
	startSending.src = "/resources/libs/sending_sound/start_sending.wav";
	afterSending.src = "/resources/libs/sending_sound/after_sending_order.wav";
	
	oneMore.volume = 1;
	chekingProduct.volume = 1;
	emptyProduct.volume = 1;
	finished.volume = 1;
	nonExistsInvoiceNum.volume = 1;
	nonExistsProduct.volume = 1;
	productOneFinished.volume = 1;
	checkingProductOneMore.volume = 1;
	startSending.volume = 1;
	afterSending.volume = 1;
	
	var totalLength;
	var numberCounting;
	var completeProduct;
	var orDeliveryInvoiceNumber; 
	
	$(document).on("submit", "#sendingForm",(function(){
		
		orDeliveryInvoiceNumber = $("#orDeliveryInvoiceNumber").val();
		orDeliveryInvoiceNumber = orDeliveryInvoiceNumber.replace(" ","");
		
		if(orDeliveryInvoiceNumber == null || orDeliveryInvoiceNumber == ""){
			alert("값이 없습니다");
			$("#orDeliveryInvoiceNumber").focus();
			
			event.preventDefault();
			return false;
		}
		
		if($("#invoiceValue").val() == ""){
			
			$.ajax({
			    type       : 'POST',
			    async	   : false,
			    url        : '/delivery/result.do',
			    data : {			    	
			    "orDeliveryInvoiceNumber" : orDeliveryInvoiceNumber
			    },
			    success    : function(data){
			    	if(data.length == 0){
			    		nonExistsInvoiceNum.play();
			    		$("#orDeliveryInvoiceNumber").val("");
						$("#orDeliveryInvoiceNumber").focus();
						
			    	}else{
			    		
			    		if(data[0].orSendingDay != null){
			    			afterSending.play();
			    			completeProduct = 0;
			    			$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
			    		}else{
			    			startSending.play();
				    		sendingProductListWrite(data);
				    		$("#invoiceValue").val(orDeliveryInvoiceNumber);
				    		totalLength = $("td[data-cancled='N']").length;
							completeProduct = 0;
							$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
							
							console.log(" 총 물품 개수 => "+totalLength);
			    		}
			    		
			    	}
			    	
			    }
			});
		}else{
			numberCounting = 1;
			var forSearch = 0;
			var searchCounting = 0;
			var noneProdCounting = 0;
			console.log(" 바코드 번호  => "+orDeliveryInvoiceNumber);
			
			$("#sendingProductList td[name=productLists]").each(function(idx){
				console.log(" 물품 순회 시작  => "+idx);
				
				
				numberCounting+=1;
				
				//var barcodeNum = $(this).data("barcodenum");
				var barcodeNum = $(this).attr("data-barcodenum");
				if(!barcodeNum){
					console.log(" 바코드 번호 ");
					
				}else{		
					if( barcodeNum == orDeliveryInvoiceNumber && $(this).data("cancled") != "Y"){
						console.log(" 바코드 같음 1  => ");
						
						if( $(this).data("finished") == "N" && $(this).data("barcodenum") != "N"){
							console.log(" 같아서 증가 ");
							
							searchCounting++;
							//$(this).text( (Number($(this).data("quantity") )+Number(1))+"개" );
							//$(this).data("quantity",(Number($(this).data("quantity") )+Number(1)));
							
							$(this).text( (Number($(this).attr("data-quantity") )+Number(1))+"개" );
							$(this).attr("data-quantity",(Number($(this).attr("data-quantity") )+Number(1)));
							
							
							$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
							
							//if($(this).data("quantity") == $(this).data("amount")){
							
							if($(this).attr("data-quantity") == $(this).attr("data-amount")){
								
								completeProduct+=1;
								productOneFinished.play();
								
								$(this).attr("data-barcodenum","N");
								$(this).attr("data-finished","Y");
								
								$("#finishedOrder").append($(this).parent().clone());
								
								$(this).parent().css("display","none");
								
								
								
								if(totalLength == completeProduct){
									
									finished.play();
									
									$("#sendingProductList").html("");
									$("#invoiceValue").val("");
									
									var params = jQuery("#sendingTargetOrder").serialize();
									
									$.ajax({
									    type       : 'POST',
									    async	   : false,
									    url        : '/delivery/sending_result.do',
									    data	   : params,
									    success    : function(data){
									    	
									    }
									});
									
									$("#sendingTargetOrder").html("");
									$("#finishedOrder").html("");
									
								}
								
							}else{
								oneMore.play();
								// return false;
							}
							
							return false;
							
						}else{
							
							checkingProductOneMore.play();
							$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
						}
						
					}else if( barcodeNum == orDeliveryInvoiceNumber && $(this).data("cancled") == "Y"){
						forSearch++;
					}else if(barcodeNum != orDeliveryInvoiceNumber && $(this).data("cancled") == "N"){

						noneProdCounting++;
					}else{

						forSearch++;
					}
					
				}
				
				
	
			});
			
			console.log(" 같지 않은 개수 "+forSearch);
			console.log(" 물품 개수 "+totalLength);
			console.log(" 검색 개수 "+searchCounting);
			console.log(" 없는 바코드 개수 "+noneProdCounting);
			
			if((forSearch == totalLength && searchCounting == 0) || noneProdCounting == totalLength){
				nonExistsProduct.play();
				$("#orDeliveryInvoiceNumber").val("");
				$("#orDeliveryInvoiceNumber").focus();
			}
			
		}
		
		event.preventDefault();
		return false;
		
	}));
	
	function sendingProductListWrite(data){
		var productList = ""; 
		
		$.each(data, function(){
			productList
				+="<tr style='font-size: 12px;'>"
					+"<td width='70%;' style='";
			if(!this.orUserColumn1){
				productList+=" color:red;";
			}
			if(this.orCancledFlag == true){
				productList+=" text-decoration:line-through; color:red;";
			}
			
			productList+="'>"+this.orProduct;
			
			if(!this.orUserColumn1){
				productList+="(바코드 없음)";
			}
			if(this.orCancledFlag == true){
				productList+=" - 취소된 상품 - ";
			}
			
			if(this.orCancledFlag == true){
				productList+="</td>"
					+"<td width='15%;'>"+this.orAmount+"개</td>"
					+"<td width='15%;' name='productLists' data-barcodenum='";
				
				if(!this.orUserColumn1){
					productList+="N";
				}else{
					productList+=this.orUserColumn1
				}
				productList+="' data-amount='"+this.orAmount+"' data-finished='N' data-cancled='Y' data-quantity='"+this.orAmount+"'> 취소 </td>"
				+"</tr>";
				
				var orPkInput = document.createElement("input");
				orPkInput.name="orPk";
				orPkInput.type="hidden";
				orPkInput.value=this.orPk;
				$("#sendingTargetOrder").append(orPkInput);
				
			}else{
				productList+="</td>"
					+"<td width='15%;'>"+this.orAmount+"개</td>"
					+"<td width='15%;' name='productLists' data-barcodenum='";
				
				if(!this.orUserColumn1){
					productList+="N";
				}else{
					productList+=this.orUserColumn1
				}
				productList+="' data-amount='"+this.orAmount+"' data-finished='N' data-cancled='N' data-quantity='0'>0개</td>"
				+"</tr>";
				
				var orPkInput = document.createElement("input");
				orPkInput.name="orPk";
				orPkInput.type="hidden";
				orPkInput.value=this.orPk;
				$("#sendingTargetOrder").append(orPkInput);
				
			}
			
		});
	
		$("#sendingProductList").html(productList);
	}
}); // AND OF JQUERY
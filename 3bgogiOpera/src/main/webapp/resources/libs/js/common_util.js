
	function regexFunction( eventTitle, eventTarget){
	
		$(document).on(eventTitle, eventTarget, function(){
			
			var regexp = /^[0-9]*$/
	
				v = $(this).val();
	
				if( !regexp.test(v) ) {
	
					alert("숫자만 입력하세요");
	
					$(this).val("");
	
				}
				
		});
	
	}
	//기타 기능
	function returnWeek(date){
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		
		var dayOfWeek = week[new Date(date).getDay()];
		
		return dayOfWeek;
	}

	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-');
	}
	
	function allFormatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	    
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-') + " "+hours+":"+minutes+"";
	}
	
	function formatDate_all_hh_mm_ss(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	    
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    var sec = ''+ d.getSeconds();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	    if(sec < 10) sec="0"+sec;
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-') + " "+hours+":"+minutes+":"+sec;
	}
	
	function formatDate_HH_MM(date) {
	    var d = new Date(date);
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	    
	    return hours+":"+minutes+"";
	}
	
	function comma(num){
		
	    var len, point, str; 
	    
	    if(num==null){
	    	str = 'No Data';
	    	return str;
	    }
	    num = num + ""; 
	    point = num.length % 3 ;
	    len = num.length; 
	   
	    str = num.substring(0, point); 
	    while (point < len) { 
	        if (str != "") str += ","; 
	        str += num.substring(point, point + 3); 
	        point += 3; 
	    } 
	     
	    return str;
	 
	}
	
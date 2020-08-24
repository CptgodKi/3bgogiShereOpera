<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/resources/libs/css/theme.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/resources/libs/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/morris-bundle/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/c3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/flag-icon-css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/multi-select/css/multi-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/%40claviska/jquery-minicolors/jquery.minicolors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
    
    <!-- Optional JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Stylish" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/renewal_page.css">
    
    <script src="${pageContext.request.contextPath}/resources/vendor/pace-master/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
    <link rel=”stylesheet” type=”text/css” href=”//cdn.rawgit.com/innks/NanumSquareRound/master/nanumsquareround.min.css”>
    <title> 3Bgogi Renewal Home Page</title>
    <style type="text/css">
    	*{
    		font-family: ‘NanumSquareRound’,sans-serif;
    	}
    	.blinking{
			-webkit-animation:blink 1s ease-in-out infinite alternate;
			-moz-animation:blink 1s ease-in-out infinite alternate;
			animation:blink 1s ease-in-out infinite alternate;
		}
		@-webkit-keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
		@-moz-keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
		@keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
			
			
    </style>
</head>
<script type="text/javascript">
	$(function(){
		$(".createNewOrder").click(function(){
			
			window.open('/orders/create/order.do', "새주문생성" , "width=1500, height=900, top=100, left=100, scrollbars=no");
		});
	});
$.ajaxSetup({
	
    beforeSend: function(xhr) {
        xhr.setRequestHeader("AJAX", true);
     },
     error: function(xhr, status, err) {
        if (xhr.status == 401) {
            alert("인증에 실패 했습니다. 로그인 페이지로 이동합니다.");
            location.href = "/loginFail.do";
         } else if (xhr.status == 403) {
            alert("세션이 만료가 되었습니다. 로그인 페이지로 이동합니다.");
              location.href = "/login.do";
         } else if (xhr.status == 500) {
             alert(" 500에러 ");
        }
     }
});
	function openProjectWhenSetupPage(){
		$.ajax({
			url:"<c:url value='/project/project_alarm.do'/>",
			method:"POST",
			async:false,
			success:function(data){
				projectTopRewrite(data);
				
				setTimeout(function(){
					openProjectWhenSetupPage();
					clearTimeout(this);
				}, 10000);
				
			}
		});
	}
	
	$.ajax({
		url:"<c:url value='/project/project_alarm.do'/>",
		method:"POST",
		success:function(data){
			projectTopRewrite(data);
			openProjectWhenSetupPage();
		}
	});
	
	
function projectTopRewrite(data){
		var alarmCount = 0;
		var projectList = "";
		var projects = new Array();
		
		$.each(data, function(idx){
			
			projectList+='<a href="#" class="list-group-item list-group-item-action showDetailProject" ';
			
			 if(this.proTopAlarmCheck === true){
	            projectList+=' style="background: beige;"';
	         }
			 
			projectList+=' id="setInterval'+this.proPk+'"   data-toggle="modal" data-target="#projectModal">'
				+'<input type="hidden" value="'+this.proPk+'" name="proPk">'
                +'<div class="notification-info">'
                    +'<div class="notification-list-user-img">';
                    
                    if(this.proThumbnailImage != null){
                    	
                    	projectList+='<img src="${pageContext.request.contextPath}/resources/images/project_image/'+this.proThumbnailImage+'" class="user-avatar-md rounded-circle">';
                    }
                    
                    projectList+='</div>'
                    +'<div class="notification-list-user-block"><span class="notification-list-user-name" style="color:'+this.proTitleColor+'"> '+this.proTitle+'</span>';
                    
                    if(this.proDetail != null){
                    	projectList+=this.proDetail;
                    }
                    
             projectList+='<div class="notification-date">'+formatDate(this.proRegdate)+'</div>'
                    +'</div>'
                +'</div>'
            +'</a>';
            
            if(this.proTopAlarmCheck === true){
            	alarmCount++;
            	var ProjectsVO = new Object();
            	ProjectsVO.proPk = this.proPk;
            	ProjectsVO.proTitle = this.proTitle;
            	
            	projects.push(JSON.stringify(ProjectsVO));
            }
            
		});
		
		if(alarmCount == 0){
			$("#alarmDiv").hide();
			$(".indicator").hide();
			
			
		}else{
			alarmDivDisplay(projects);
        	$(".indicator").show();
        	console.log(projects);
		}
		
		$(".topFixedProjectListDiv").html(projectList);
		
	}
	
	function alarmDivDisplay(data){
		/* alarmDivHTML =data;
		$("#alarmId").text(alarmDivHTML);
		$("#alarmDiv").show(); */
		
		
		window.open("<c:url value='/alarm/popup.do?projects="+encodeURI(data)+"'/>", "업무 알람" , "width=430, height=220, top=200, left=1200, scrollbars=no");
		
	}

</script>
<body>
    <!-- ============================================================== -->
    <!-- main wrapper -->
    <!-- ============================================================== -->
    <div class="dashboard-main-wrapper">
        <!-- ============================================================== -->
        <!-- navbar -->
        <!-- ============================================================== -->
        <div class="dashboard-header">
            <nav class="navbar navbar-expand-lg bg-white fixed-top">
                <a class="navbar-brand" href="<c:url value='/main/home.do'/>" style="color:#FFA2A2;">삼형제고기</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon fas fa-angle-down"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto navbar-right-top">
                        <li class="nav-item">
                            <div id="custom-search" class="top-search-bar">
                                <input class="form-control" type="text" placeholder="Search..">
                            </div>
                        </li>
                        <li class="nav-item dropdown notification">
                            <a class="nav-link nav-icons" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-fw fa-bell" id="alarmBellIcon"></i> <span class="indicator"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="notification-title"> 업무 알람 </div>
                                    <div class="notification-list">
                                        <div class="list-group topFixedProjectListDiv">
                                            <a href="#" class="list-group-item list-group-item-action active"   data-toggle="modal" data-target="#projectModal">
                                                <div class="notification-info">
                                                    <div class="notification-list-user-img">
                                                    	<img src="${pageContext.request.contextPath}/resources/images/avatar-2.jpg" alt="" class="user-avatar-md rounded-circle">
                                                    </div>
                                                    <div class="notification-list-user-block">
                                                    	<span class="notification-list-user-name"> 전기찬 - 시스템운영개발</span>
                                                    	리뉴얼 웹페이지 언젠가 제작 다 하겠지?
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="list-footer"><a href="<c:url value='/project/insert_project.do'/>"> 모든 업무 보기 </a></div>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown connection">
                            <a class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-fw fa-th"></i> </a>
                            <ul class="dropdown-menu dropdown-menu-right connection-dropdown">
                                <li class="connection-list">
                                    <div class="row">
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/github.png" alt=""> <span> 업무용 모달 테스트 </span></a>
                                        </div>
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/dribbble.png" alt="" > <span>Dribbble</span></a>
                                        </div>
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/dropbox.png" alt="" > <span>Dropbox</span></a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/bitbucket.png" alt=""> <span>Bitbucket</span></a>
                                        </div>
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/mail_chimp.png" alt="" ><span>Mail chimp</span></a>
                                        </div>
                                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                            <a href="#" class="connection-item"><img src="${pageContext.request.contextPath}/resources/images/slack.png" alt="" > <span>Slack</span></a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="conntection-footer"><a href="#">More</a></div>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown nav-user">
                            <a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/resources/images/3bgogi_icon.png" alt="" class="user-avatar-md rounded-circle"></a>
                            <div class="dropdown-menu dropdown-menu-right nav-user-dropdown" aria-labelledby="navbarDropdownMenuLink2">
                                <div class="nav-user-info">
                                    <h5 class="mb-0 text-white nav-user-name"> 전기찬  </h5>
                                    <span class="status"></span><span class="ml-2">- 개발팀 </span>
                                </div>
                                <a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i> 계정 </a>
                                <a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i> 환경설정 </a>
                                <a class="dropdown-item" href="<c:url value='/logout.do'/>"><i class="fas fa-power-off mr-2"></i> 로그아웃 </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <!-- ============================================================== -->
        <!-- end navbar -->
        <!-- ============================================================== -->
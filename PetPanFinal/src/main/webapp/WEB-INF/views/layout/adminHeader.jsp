<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
  <meta charset="utf-8" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>PetPanAdmin</title>

    <meta name="description" content="" />



    <!-- Favicon -->
    <link rel="icon" type="image/png" href='<%=request.getContextPath()%>/resources/img/logo.png' sizes="20x11">






    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com"  />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->

    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/fonts/boxicons.css" />




    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/css/demo.css" />


    <!-- Vendors CSS -->


     <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" /> 



    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/libs/apex-charts/apex-charts.css" />


    <!-- Page CSS -->

    <!-- Helpers -->


    <script src="<%=request.getContextPath() %>/resources/assets/vendor/js/helpers.js"></script>


    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
       <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->


    <script src="<%=request.getContextPath() %>/resources/assets/js/config.js"></script>



    <script type="text/javascript">
       document.addEventListener('DOMContentLoaded', function() {
        var currentUrl = window.location.href;
        
        if (currentUrl.includes("/main")) {
           document.getElementById('main').className = 'menu-item active';
        }else{
           document.getElementById('main').className = 'menu-item';
        }

        if (currentUrl.includes("/blacklist/")) {
           document.getElementById('black').className = 'menu-item active';
        }else{
           document.getElementById('black').className = 'menu-item';
        }
        
        if (currentUrl.includes("/member/")){
           document.getElementById('member').className = 'menu-item active';
        }else{
           document.getElementById('member').className = 'menu-item';
        }
        if (currentUrl.includes("/notice/")){
            document.getElementById('notice').className = 'menu-item active';
         }else{
            document.getElementById('notice').className = 'menu-item';
         }
        if (currentUrl.includes("/batch/")){
            document.getElementById('message').className = 'menu-item active';
         }else{
            document.getElementById('notice').className = 'menu-item';
         }
        if (currentUrl.includes("/reportboard/")){
           document.getElementById('reportboard').className = 'menu-item active';
        }else{
           document.getElementById('reportboard').className = 'menu-item';
        }
        if (currentUrl.includes("/reportcomment/")){
           document.getElementById('reportcomment').className = 'menu-item active';
        }else{
           document.getElementById('reportcomment').className = 'menu-item';
        }
        if (currentUrl.includes("/shop/")){
           document.getElementById('shop').className = 'menu-item active';
        }else{
           document.getElementById('shop').className = 'menu-item';
        }
        if (currentUrl.includes("/buyer/")){
            document.getElementById('buyer').className = 'menu-item active';
        }else{
            document.getElementById('buyer').className = 'menu-item';
        }
        if (currentUrl.includes("/reportshop/")){
           document.getElementById('reportshop').className = 'menu-item active';
        }else{
           document.getElementById('reportshop').className = 'menu-item';
        }
      });
    
    </script>
    <style>
		@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
	</style>
   <style type="text/css">
   body {
/* 	font-family: 'Roboto', sans-serif; */
	}
   html{
   position:relative;
   }
   .menu:not(.menu-no-animation) .menu-icon {
    transition: margin-right 0.3s ease;
   }
   
   .container2 {
     position: absolute;
     margin-left: 400px; 
     margin-top: 100px; 



/*      margin-top: 10px; */
 /* 특정 div의 위쪽 여백 설정 */


   }
	
	.logon {
  	position: absolute;
     top : 10px;
     right : 10px;
     width: 250px;
     height: 30px;
    

/*      margin-top: 10px; */
 /* 특정 div의 위쪽 여백 설정 */
   }
   .welcome{
   position: absolute;
   top : 10px;
    right : 500px;
    width: 270px;
    height: 30px;
    justify-content: center;
   }
   


   
   </style>

  </head>

</head>
<body>
<div id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme" style = "float: left; position: fixed; top:0; left:0;  display: flex; height: 1080px;">
          <div class="app-brand demo">
            <a href="<%=request.getContextPath() %>/admin/main" class="app-brand-link">
              <span class="app-brand-logo demo">
               <img src="<%=request.getContextPath()%>/resources/img/logo.png" style="width:60px;">
              </span>
              <span class="app-brand-text demo menu-text fw-bolder ms-2">PetPan</span>
            </a>

            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>
         
         
          <ul class="menu-inner py-1">
            <!-- Dashboard -->
            <li class="menu-item" id="main">
              <a href="<%=request.getContextPath() %>/admin/main" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div data-i18n="Analytics">Admin Main</div>
              </a>
            </li>
            
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">User</span>
            </li>

            <li class="menu-item" id="member">

            <li class="menu-item" id="user">

              <a href="<%=request.getContextPath() %>/admin/member/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-id-card"></i>
                <div data-i18n="Account Settings">User Manage</div>
              </a>
            </li>
            <li class="menu-item" id="black">
              <a href="<%=request.getContextPath() %>/admin/blacklist/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-user-x"></i>
                <div data-i18n="Authentications">Blacklist</div>
              </a>
<!--                    <ul class="dropdown-menu"> -->
<!--             <li><a class="dropdown-item" href="#">Action 1</a></li> -->
<!--             <li><a class="dropdown-item" href="#">Action 2</a></li> -->
<!--             <li><a class="dropdown-item" href="#">Action 3</a></li> -->
<!--                    </ul> -->
            </li>
            
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">Notice</span>
            </li>
            <li class="menu-item" id="notice">
              <a href="<%=request.getContextPath() %>/admin/notice/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-volume-full"></i>
                <div data-i18n="Account Settings">Notice Manage</div>
              </a>
            </li>
            <li class="menu-item" id="message">
              <a href="<%=request.getContextPath() %>/admin/batch/write" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-chat"></i>
                <div data-i18n="Account Settings">Notice Message</div>
              </a>
            </li>
            
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">Reports</span>
            </li>
            <li class="menu-item" id="reportboard">
              <a href="<%=request.getContextPath() %>/admin/reportboard/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-task-x"></i>
                <div data-i18n="Account Settings">Report Board</div>
              </a>
            </li>
            <li class="menu-item" id="reportcomment">
              <a href="<%=request.getContextPath() %>/admin/reportcomment/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-message-alt-x"></i>
                <div data-i18n="Authentications">Report Comment</div>
              </a>
            </li>
            
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">Shop</span>
            </li>
            <li class="menu-item" id="shop">
              <a href="<%=request.getContextPath() %>/admin/shop/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-basket"></i>
                <div data-i18n="Account Settings">Shop Manage</div>
              </a>
            </li>
            <li class="menu-item" id="buyer">
              <a href="<%=request.getContextPath() %>/admin/buyer/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-archive-out"></i>
                 <div data-i18n="Account Settings">Buyer Manage</div>
              </a>
            </li>
            <li class="menu-item" id="reportshop">
              <a href="<%=request.getContextPath() %>/admin/reportshop/list" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-phone"></i>
                <div data-i18n="Authentications">Shop Report</div>
              </a>
            </li>
         
          </ul>
        </div>
        
        <div class = welcome style="margin-right: 350px; width:300px;">
	     <i class="bi bi-person"></i><h3 class = "h3">PetPan 관리자 페이지</h3>
        </div>
        

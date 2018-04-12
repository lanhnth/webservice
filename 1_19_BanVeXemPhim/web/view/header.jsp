<%-- 
    Document   : header
    Created on : Nov 29, 2017, 3:41:30 PM
    Author     : NguyenNgoc
--%>

<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--<link rel="stylesheet" type="text/css" href="css/style.css">-->
<!--day la header-->

<%
    Client client = (Client) session.getAttribute("user");
    String dn = "Đăng Ký";
    String link = "home?controller=login_signin";
    String loginout = "Đăng Nhập";
    String link2 = "home?controller=login_signin";
    if (client != null) {
        dn = client.getUsername();
        link = "home?controller=member_account";
        loginout = "LOG OUT";
        link2 = "LogoutServlet";
    }
%>
<div class="header">
    <div class="Quangcao">
        <a href="home" target="_blank">
            <img class="banner img-responsive" src="img/banner.png" alt="Du hoc cung RMIT">
        </a>
    </div>
    <div class="menu">
        <ul>
            <li><a href=<%=link%>><%=dn%></a></li>
            <li><a href=<%=link2%>><%=loginout%></a></li>
            <li><a href="#" id="lienhe">Liên Hệ</a></li>
            <li><a href="#" id="tuyendung">Tuyển Dụng</a></li>
        </ul>
    </div>
    <div class="navbar-default">
        <div class="navbar-header">
            <a href="#" class="logo">
                <img src="img/cgvlogo.png" alt="cgv" class="large">
                <img src="img/cgvlogo-small.png" alt="cgv" class="small">
            </a>
            <div class="icon">
                <button data-target="#item3" class="navbar-toggle icon3" data-toggle="collapse" >
                    <span class="sr-only"></span>
                    <span>EN</span>
                </button>

                <button data-target="#item2" class="navbar-toggle icon2" data-toggle="collapse" >
                    <span class="sr-only"></span>
                    <span class="glyphicon glyphicon-user"></span>
                </button>

                <button data-target="#item1" class="navbar-toggle icon1" data-toggle="collapse">
                    <span class="sr-only"></span>
                    <span class="glyphicon glyphicon-search"></span>
                </button>
                <button data-target="#item" class="navbar-toggle icon0" data-toggle="collapse" >
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
        </div>
        <div class="navbar-collapse collapse menu-top" id="item">
            <ul class="nav navbar-nav">
                <li>
                    <a href="" title="">PHIM</a>
                    <ul class="menu-sub">
                        <li><a href="home?controller=now_showing" title="">Phim Đang Chiếu</a></li>
                        <li><a href="home?controller=pre_show" title="">Phim Sắp Chiếu</a></li>
                    </ul>
                </li>
                <li><a href="home?controller=schedule" title="">LỊCH CHIẾU</a></li>

                <li>
                    <a href="" title="">THÀNH VIÊN</a>
                    <ul class="menu-sub">
                        <li><a href="<%=link%>" title="Thành Viên">Thành Viên</a></li>
                        <li><a href="<%=link2%>" title="Đăng Ký">Đăng Ký</a></li>
                    </ul>
                </li>
            </ul>
            <form action="SearchServlet" method="POST" >
                <div class="search">
                    <div class="search-left">
                        <a href="#">
                            <img src="img/tin-moi-uu-dai.gif">
                        </a>
                        <div class="navbar-collapse collapse" id="">
                            <input type="text" class="search_mini" placeholder="enter the search" name="textsearch">
                        </div>
                    </div>
                    <div class="search-right">
                        <p><a href="#"><img src="img/mua-ve_ngay.png" alt="mua-ve-ngay"></a></p>
                    </div>
                </div>
            </form>
        </div>
        <div class="navbar-collapse collapse" id="item1">
            <input type="text" class="form-control" placeholder="enter the search">
        </div>
        <!-- <div class="navbar-collapse collapse" id="item2">
          <ul class="nav navbar-nav listmenu2">
            <li><a href="#">Tài khoản</a></li>
            <li><a href="#">Giỏ Hàng</a></li>
            <li><a href="#">Thanh Toán</a></li>
            <li><a href="#">Đăng Ký</a></li>
            <li><a href="#">Đăng Nhập</a></li>
          </ul>
        </div> -->
    </div>
</div>

<%-- 
    Document   : now_showing
    Created on : Nov 29, 2017, 5:29:35 PM
    Author     : NguyenNgoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/style_showing.css">
<link rel="stylesheet" type="text/css" href="css/now_showing_responsive.css">
<!-- breadcrumbs -->
<div class="breadcrumbs">

    <div class="container">
        <span class="fa fa-home"></span>
        <span class="second-level">Phim</span>
        <span class="second-level">Phim Đang Chiếu</span>
    </div>

</div>
<!-- end breadcrumbs -->

<!-- content -->
<div class="content">
    <div class="container">
        <section>
            <h2>Phim đang chiếu</h2>
            <div class="content-items">
                <!-- row -->
                <div class="row film_list">

                </div>
                <!-- end row -->
            </div>
            <ul class="pagination">
                <li><a>1</a></li>
                <li><a>2</a></li>
                <li><a>3</a></li>
                <li><a>4</a></li>
                <li><a>5</a></li>
            </ul>
        </section>
    </div>
</div>
<div id="loading-mask">
    <p class="loader" id="loading_mask_loader" >
        <img src="https://www.cgv.vn//skin/frontend/rwd/default/images/ajax-loader.gif" alt="Loading...">
</div>
<style>
    .pagination li a {
        cursor: pointer;
    }
    .loader {
        position: absolute;
        top: 45%;
        left: 45%;
    }
    #loading-mask {
        left: 0;
        width: 100vw;
        height: 100vh;
        top: 0;
        display: none;
        position: fixed;
        background: rgba(0,0,0,0.5);
    }
</style>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/now_showing.js"></script>
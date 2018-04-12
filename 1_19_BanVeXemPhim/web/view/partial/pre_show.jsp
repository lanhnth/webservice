<%-- 
    Document   : pre_show
    Created on : Dec 23, 2017, 5:32:25 PM
    Author     : NguyenNgoc
--%>
<%@page import="model.Category"%>
<%@page import="model.Film"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/style_showing.css">
<link rel="stylesheet" type="text/css" href="css/now_showing_responsive.css">
<% ArrayList<Film> listPreFilm = (ArrayList<Film>) request.getAttribute("listPreFilm");
%>
<!-- breadcrumbs -->
<div class="breadcrumbs">

    <div class="container">
        <span class="fa fa-home"></span>
        <span class="second-level">Phim</span>
        <span class="second-level">Phim Sắp Chiếu</span>
    </div>

</div>
<!-- end breadcrumbs -->
<div class="content">
    <div class="container">
        <section>
            <h2>Phim sắp chiếu</h2>
            <div class="content-items">
                <!-- row -->
                <div class="row">
                    <!-- col1 -->
                    
                    <%if(listPreFilm.size() == 0){%>
                        <h2>Chưa có phim</h2>
                    <%}else{
                        for (Film film : listPreFilm) {%>
                            <div class="col-md-3 col-res">
                                <div class="content-item-wrapper">
                                    <div class="content-item">
                                        <a href="#"></a>
                                        <img src="Image/<%=film.getPoster()%>" alt="<%=film.getName()%>">

                                        <div class="info">
                                            <h4><a href="#"><%=film.getName()%></a></h4>
                                            <p>thể loại:
                                                <%for (Category c : film.getCategories()) {%>
                                                    <span><%=c.getName()%></span>
                                                <%}%>
                                            </p>

                                            <p>thời lượng:
                                                <span><%=film.getRunningTime()%></span>
                                            </p>

                                            <p>Khởi chiếu:
                                                <span><%=film.getRealeaseDate()%></span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <%}%>
                    <%}%>
                    <!-- end col -->
                </div>
                <!-- end row -->


            </div>
        </section>
    </div>
</div>

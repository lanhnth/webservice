<%-- 
    Document   : search
    Created on : Dec 18, 2017, 4:38:51 PM
    Author     : Dell
--%>

<%@page import="model.Category"%>
<%@page import="model.Film"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/style_showing.css">
<link rel="stylesheet" type="text/css" href="css/now_showing_responsive.css">

<% ArrayList<Film> list = (ArrayList<Film>) session.getAttribute("search");
    ArrayList<Category> listCate = (ArrayList<Category>) session.getAttribute("cate");
%>

<div class="content">
    <div class="container">
        <section>

            <div class="content-items">
                <form action="SearchServlet" method="GET">
                    <select name="cate">
                        <%for (Category item : listCate) {%>
                        <option><%=item.getName()%></option>
                        <%}%>
                    </select>
                    <button type="Sumbit">Search</button>
                </form>
                <!-- row -->
                <div class="row film_list">
                    <% if ((list.size() == 0)) {%>
                    <h3>Không có kết quả tìm kiếm</h3>
                    <%}%>
                    <% for (Film item : list) {%>
                    <div class="col-md-3 col-res">
                        <div class="content-item-wrapper">
                            <div class="content-item">
                                <a href=<%="home?controller=detail_film&movies=" + item.getApi_id()%>>
                                </a><img src=<%=item.getPoster()%>>
                                <div class="info"><h4><a href=<%="home?controller=deatil_film&movies=" + item.getId()%>><%=item.getName()%></a>
                                    </h4><p>Vote: <span><%=item.getVote()%></span></p>
                                    <p>Khỏi chiếu: <span><%=item.getRealeaseDate()%></span></p></div></div>
                            <button type="button" class="btn-mua" title="Mua ve"><span>Mua ve</span></button>
                        </div>
                    </div>
                    <% }%>
                </div>
                <!-- end row -->
            </div>

        </section>
    </div>
</div>


                
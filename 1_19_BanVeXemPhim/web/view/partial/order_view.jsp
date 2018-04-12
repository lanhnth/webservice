<%-- 
    Document   : order_view
    Created on : Dec 19, 2017, 6:01:40 PM
    Author     : Dell
--%>

<%@page import="model.Ticket"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/thanhvien.css">
<%  ArrayList<Order> listSS = (ArrayList<Order>) request.getAttribute("sucess");
    //ArrayList<Order> wait = (ArrayList<Order>) session.getAttribute("wait");
%>
<div class="main-container">
    <div class="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="col-left">
                        <div class="col-left-title">
                            <strong><span>Tài Khoản</span></strong>
                        </div>
                        <div class="col-left-content">
                            <ul>
                                <li><a href="">Thông tin chung</a></li>
                                <li><a href="">Thông tin tài khoản</a></li>
                                <li><a href="OrderServlet">Đơn hàng của tôi</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <div class="col-main">
                        <div class="my-account">
                            <div class="dashboard">
                                <div class="dashboard-title">
                                    <h1>Đơn hàng của tôi</h1>
                                </div>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Ngày</th>
                                            <th>Tổng giá</th>
                                            <th>Tên phim</th>
                                            <th>Phòng chiếu</th>
                                            <th>Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Order item : listSS) {%>
                                        <tr>
                                            <td><%=item.getId()%></td>
                                            <td><%=item.getDate()%></td>
                                            <td><%=item.getGrandtotal()%></td>
                                            <td><%=item.getListTicket().get(0).getSchedule().getFilm().getName()%></td>
                                            <td><%=item.getListTicket().get(0).getSchedule().getRoom().getName()%></td>   
                                            <%if (item.getStatus() == 1) {%>
                                                <td>Đang đặt</td>
                                            <%} else if (item.getStatus() == 2) {%>
                                                <td>Đã nhận</td>
                                            <%} else {%>
                                                <td>Đã hủy</td>
                                            <%}%>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

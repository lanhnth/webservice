<%-- 
    Document   : order_buy
    Created on : Dec 23, 2017, 7:45:43 PM
    Author     : ducvu
--%>

<%@page import="model.Order"%>
<%@page import="model.Schedule"%>
<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="css/thanhvien.css">
<style>
    @media print and (orientation:landscape) {
        @page {size: landscape}
        body * {
            visibility: hidden;
        }
        #print, #print * {
            visibility: visible;
        }
        #print {
            position: absolute;
            left: 15%;
            top: 0;
        }
        div.landscape {
    transform: rotate(270deg) translate(-276mm, 0);
    transform-origin: 0 0;
  }
    }
</style>
<%
    Schedule schedule = (Schedule) request.getAttribute("schedule");
    Order order = (Order) request.getAttribute("order");
    Client client = (Client) session.getAttribute("user");
    String seat = (String) request.getAttribute("seat");
%>
<div class="main-container">
    <div class="main">
        <div class="container">
            <div class="row" id="print">
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="col-left">
                        <div class="col-left-title">
                            <strong><span>Hóa đơn</span></strong>
                        </div>
                        <div class="col-left-content">
                            <ul>
                                <li><a href="">Thông tin Hóa đơn</a></li>
                                <li><img src="<%= schedule.getFilm().getPoster()%>"></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <div class="col-main">
                        <div class="my-account" >
                            <div class="dashboard">
                                <div class="dashboard-title">
                                    <h1>Thông tin Hóa đơn</h1>
                                </div>
                                <ul>
                                    <li >
                                        <strong>Name: </strong>
                                        <span class="input-box">
                                            <%=client.getName()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Địa chỉ: </strong>
                                        <span class="input-box">
                                            <%=client.getAddress()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Email: </strong>
                                        <span class="input-box">
                                            <%=client.getMail()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Điện thoại:</strong>
                                        <span class="input-box">
                                            <%=client.getPhone()%>
                                        </span>
                                    </li>
                                    <li >
                                        <hr>  
                                    </li>
                                    <li >
                                        <strong>Film:</strong>
                                        <span class="input-box">
                                            <%= schedule.getFilm().getName()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Room:</strong>
                                        <span class="input-box">
                                            <%= schedule.getRoom().getName()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Seat:</strong>
                                        <span class="input-box">
                                            <%= seat%>
                                        </span>
                                    </li>
                                    <li >
                                        <hr>  
                                    </li>
                                    <li >
                                        <strong>Sale:</strong>
                                        <span class="input-box">
                                            <%= schedule.getSale().getName()%>
                                        </span>
                                    </li>
                                    <li >
                                        <strong>Discount:</strong>
                                        <span class="input-box">
                                            <%= order.getDiscount()%>d
                                        </span>
                                    </li>
                                    <li >
                                        <strong>GrandTotal:</strong>
                                        <span class="input-box">
                                            <%= order.getGrandtotal()%>d
                                        </span>
                                    </li>
                                    <li >
                                        <hr>  
                                    </li>
                                    <li >
                                        <strong></strong>
                                        <button type="button" onclick="window.print()">Print ticket</button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 
    Document   : member_order
    Created on : Dec 19, 2017, 12:01:17 AM
    Author     : Dell
--%>

<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/thanhvien.css">
<% Client client = (Client) session.getAttribute("user");%>
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
                                <li><a href="">Đơn hàng của tôi</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <div class="col-main">
                        <div class="my-account">
                            <div class="dashboard">
                                <div class="dashboard-title">
                                    <h1>Order History</h1>
                                </div>
                                <form action="ChangPassServlet" method="POST" accept-charset="utf-8" class="form-acc" name="formChangePass" onsubmit="return checkPass();">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>discount</th>
                                                <th>grandtotal</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>John</td>
                                                <td>Doe</td>
                                                <td>john@example.com</td>
                                            </tr>
                                            <tr>
                                                <td>Mary</td>
                                                <td>Moe</td>
                                                <td>mary@example.com</td>
                                            </tr>
                                            <tr>
                                                <td>July</td>
                                                <td>Dooley</td>
                                                <td>july@example.com</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

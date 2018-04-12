<%-- 
    Document   : member_account
    Created on : Nov 29, 2017, 7:51:34 PM
    Author     : ducvu
--%>

<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/thanhvien.css">
<% Client client = (Client) session.getAttribute("user"); %>
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
                                <li><a href="home?controller=order_view">Đơn hàng của tôi</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <div class="col-main">
                        <div class="my-account">
                            <div class="dashboard">
                                <div class="dashboard-title">
                                    <h1>Sửa thông tin tài khoản</h1>
                                </div>
                                <form action="ChangPassServlet" method="POST" accept-charset="utf-8" class="form-acc" name="formChangePass" onsubmit="return checkPass();">
                                    <ul>
                                        <li class="form-left">
                                            <label>Name</label>
                                            <div class="input-box">
                                                <%=client.getName() %>
                                                <input type="hidden" name="checkpass" value=<%=client.getPassword() %>>
                                            </div>
                                        </li>
                                        <li class="form-right">
                                            <label>Địa chỉ</label>
                                            <div class="input-box">
                                                <%=client.getAddress() %>
                                            </div>
                                        </li>
                                        <li class="form-left">
                                            <label>Email</label>
                                            <div class="input-box">
                                                <%=client.getMail() %>
                                            </div>
                                        </li>
                                        <li class="form-right">
                                            <label>Điện thoại</label>
                                            <span class="star">*</span>
                                            <div class="input-box">
                                                <%=client.getPhone() %>
                                            </div>
                                        </li>
                                        <li class="control">
                                            <input type="checkbox" name="change_password" id="change_password" onclick="setPasswordForm(this.checked)" class="checkbox">
                                            <label for="change_password">Đổi mật khẩu</label>
                                        </li>

                                        <div class="change-pass">
                                            <ul>
                                                <li class="form-left">
                                                    <label>Mật khẩu cũ:</label>
                                                    <span class="star">*</span>
                                                    <div class="input-box">
                                                        <input type="password" name="txtold_password" value="" required="">
                                                    </div>
                                                    <p id="check_old_password"> </p>
                                                </li>
                                                <li class="form-left">
                                                    <label>Mật khẩu mới</label>
                                                    <span class="star">*</span>
                                                    <div class="input-box">
                                                        <input type="password" name="txtnew_password" value="" required="">
                                                    </div>
                                                    <p id="check_password"> </p>
                                                </li>
                                                <li class="form-right">
                                                    <label>Nhập lại mật khẩu mới</label>
                                                    <span class="star">*</span>
                                                    <div class="input-box">
                                                        <input type="password" name="txtnew_password_cf" value="" required="">
                                                    </div>
                                                    <p id="check_password_cf"> </p>
                                                </li>

                                            </ul>
                                        </div>
                                        
                                        <div class="button-edit">
                                            <button type="submit" title="Lưu lại" class="button">
                                                <span>Lưu lại</span>
                                            </button>
                                        </div>

                                    </ul>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.change-pass').hide();
        $('#change_password').on('click', function () {
            // body...
            if ($('.change-pass').css('display') == 'none') {
                $('.change-pass').css('display', 'block');
            } else {
                $('.change-pass').css('display', 'none');
            }

        });
    });
    var pass = $('.change-pass');
    window.onclick = function (event) {
        if (event.target == pass) {
            pass.style.css = "none";
        }
    }

    function checkPass() {
        if( document.formChangePass.checkpass.value != document.formChangePass.txtold_password.value){
            console.log(" k bang");
            alert("document.formChangePass.checkpass.value");
            document.getElementById("check_old_password").innerHTML = "Passwords incorrect";
            document.getElementById("check_old_password").style.color="red";
            document.formChangePass.txtold_password.value="";
            document.formChangePass.txtold_password.focus();
            return false;
        } else {
            document.getElementById("check_old_password").innerHTML = "";
        }
        var pass = document.formChangePass.txtnew_password.value;
        var pattern = new RegExp("^[a-zA-Z0-9_-]{6,18}$");
        var result = pattern.test(pass);
        if(!result){
            document.getElementById("check_password_cf").innerHTML = "Weak password!";
            document.getElementById("check_password_cf").style.color="red";
            document.formChangePass.txtnew_password.focus();
            return false;
        } else {
            document.getElementById("check_password_cf").innerHTML = "";
        }
        if(document.formChangePass.txtnew_password.value != document.formChangePass.txtnew_password_cf.value){
            document.getElementById("check_password_cf").innerHTML = "Passwords do not match";
            document.getElementById("check_password_cf").style.color="red";
            document.formChangePass.txtnew_password.value="";
            document.formChangePass.txtnew_password_cf.value="";
            document.formChangePass.txtnew_password.focus();
            return false;
        } else {
            document.getElementById("check_password_cf").innerHTML = "";
        }
        
        return true;
    }
</script>
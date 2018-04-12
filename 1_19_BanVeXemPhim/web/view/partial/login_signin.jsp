<%-- 
    Document   : login_signin
    Created on : Nov 29, 2017, 7:37:20 PM
    Author     : ducvu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/login_signin.css">
<style>
    .lc-login-t {
        font-family: Verdana,Arial,sans-serif;
        font-size: 10px;
        width: 100%;
        font-style: italic;
    }
</style>

<div class="main-container">
    <div class="lc-form">
        <div class="lc-form-main">
            <div class="lc-form-main-content">
                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#login" aria-controls="login" role="tab" data-toggle="tab">Đăng nhập</a>
                        </li>
                        <li role="presentation">
                            <a href="#signin" aria-controls="signin" role="tab" data-toggle="tab">Đăng ký</a>
                        </li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="login">
                            <div class="lc-form-login-main">

                                <div class="lc-form-login-content">
                                    <%
                                        String mes = "";
                                        if (request.getParameter("ok") != null) {
                                            mes = request.getParameter("ok");
                                    %>
                                    <div class="alert alert-danger" role="alert">
                                        Username or Password incorrect!
                                    </div>
                                    <%} %>
                                    <p class="lc-login-title">Vui lòng nhập tên người dùng (email) và mật khẩu của bạn...</p>
                                    <p class="lc-login-t">Account Demo( Username: demo_d3m0 | Password: demo_d3m0)</p>

                                    <div class="lc-login-content">
                                        <form action="Loginservlet" method="POST">
                                            <ul class="lc-form-list">
                                                <li class="lc-form-input-email">
                                                    <div class="input-box">
                                                        <input type="text" name="username" id="email"  title="Địa chỉ email">
                                                    </div>
                                                </li>
                                                <li class="lc-form-input-pass">
                                                    <div class="input-box">
                                                        <input type="password" name="password" id="password"  title="Password">
                                                    </div>
                                                </li>
                                            </ul>	
                                            <div class="lc-form-button">
                                                <button class="button" type="submit" title="Đăng nhập" name="send">
                                                    <span>
                                                        <span>Đăng nhập</span>
                                                    </span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="signin">
                            <div class="lc-form-signin-main">
                                <div class="lc-form-signin-content">
                                    <div class="lc-form-signin-form">
                                        <div class="row">
                                            <form action="SigninServlet" method="POST" role="form" name="formSignin" onsubmit="return checkform_signin();">
                                                <div class="col-md-6 form-group">
                                                    <label for="name">Họ và tên <span class="important">*</span></label>
                                                    <input type="text" class="input-sign form-control" name="hoten" id="name" placeholder="Họ và tên" required="">
                                                    <p></p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="name">Username  <span class="important">*</span></label>
                                                    <input type="text" class="input-sign form-control" name="username" id="username" placeholder="Username" required="">
                                                    <p id="checkuser" style="color: #737373; size: 10px;">Example: my-us3r_n4m3</p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="address">Địa chỉ<span class="important">*</span></label>
                                                    <input type="text" class="input-sign form-control" name="address" id="address" placeholder="Địa chỉ" required="">
                                                    <p></p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="password">Mật khẩu<span class="important">*</span></label>
                                                    <input type="password" class="input-sign form-control" name="pass" id="password" placeholder="Mật khẩu" required="">
                                                    <p></p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="phone">Số điện thoại<span class="important">*</span></label>
                                                    <input type="number" class="input-sign form-control" name="phone" id="phone" placeholder="Số điện thoại" min="0" required="">
                                                    <p id="checksdt" style="color: #737373; size: 10px;"> Example:0123xxxxxx</p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="cf-password">Nhập lại Mật khẩu<span class="important">*</span></label>
                                                    <input type="password" class="input-sign form-control" name="cfpass" id="cf-password" placeholder="Nhập lại Mật khẩu" required="">
                                                    <p id="checkpass" style="color: #737373; size: 10px;">Example: myp4ssw0rd </p>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="email">Email <span class="important">*</span></label>
                                                    <input type="email" class="input-sign form-control" name="email" id="email" placeholder="Email" required="">
                                                    <p></p>
                                                </div>
                                                <div class="col-md-12 form-group">
                                                    <button type="submit" class="">
                                                        <span>
                                                            <span>Đăng ký</span>
                                                        </span>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" >
    function checkform_signin() {
        var str = document.formSignin.phone.value;
        var patt = new RegExp("(\\+84|0)\\d{9,10}");
        var res = patt.test(str);
        if (!res) {
            document.getElementById("checksdt").innerHTML = document.formSignin.phone.value + ' isn\'t a phone number with area code!';
            document.getElementById("checksdt").style.color = "red";
            document.formSignin.phone.value = "";
            document.formSignin.pass.value = "";
            document.formSignin.cfpass.value = "";
            document.formSignin.phone.focus();
            return false;
        } else {
            document.getElementById("checksdt").innerHTML = "";
        }
        if (document.formSignin.pass.value != document.formSignin.cfpass.value) {
            document.getElementById("checkpass").innerHTML = "Passwords do not match";
            document.getElementById("checkpass").style.color = "red";
            document.formSignin.pass.value = "";
            document.formSignin.cfpass.value = "";
            document.formSignin.pass.focus();
            return false;
        }
        var pass = document.formSignin.pass.value;
        var pattern = new RegExp("^[a-zA-Z0-9_-]{6,18}$");
        var result = pattern.test(pass);
        if (!result) {
            document.getElementById("checkpass").innerHTML = "Weak password!";
            document.getElementById("checkpass").style.color = "red";
            document.formSignin.pass.focus();
            return false;
        }
        var user = document.formSignin.username.value;
        var patternUsername = new RegExp("^[a-zA-Z0-9_-]{3,16}$");
        var resu = patternUsername.test(user);
        if (!resu) {
            document.getElementById("checkuser").innerHTML = "Invalid Username! ";
            document.getElementById("checkuser").style.color = "red";
            document.formSignin.username.focus();
            return false;
        } else {
            document.getElementById("checkuser").innerHTML = "";
        }

        return true;
    }
</script>
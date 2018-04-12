<%-- 
    Document   : login
    Created on : Dec 3, 2017, 12:40:13 AM
    Author     : ducvu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container mt-2">
    <div class="row">
        <!-- col-6  login-->
        <div class="col-md-4 offset-md-4">
            <form method="post" action="admin">
                <div class="form-group">
                    <label class="col-form-label" for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <label class="col-form-label" for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <input class="btn btn-success" type="submit" value="Login" />
                <a href="home" class="btn btn-default">Back to home</a>
            </form>
        </div>
    </div>
</div>
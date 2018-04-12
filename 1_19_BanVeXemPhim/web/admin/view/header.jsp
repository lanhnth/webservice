<%-- 
    Document   : header
    Created on : Dec 3, 2017, 12:45:19 AM
    Author     : ducvu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header id="header" class="">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="admin">CGV DNN</a>
            <%
                if (session.getAttribute("admin_login") != null) {
            %>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="admin?controller=home">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=sale">Sale</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=schedule">Schedule</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=film">Film</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=import_film">Import Film Api</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=category">Category</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=room">Room & Cinema</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin?controller=seat">Seat</a>
                </li>
            </ul>
            <%
                }
            %>
            <!-- <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form> -->
        </div>
    </nav>
</header><!-- /header -->

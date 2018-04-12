<%-- 
    Document   : schedule
    Created on : Dec 3, 2017, 1:04:50 AM
    Author     : ducvu
--%>

<%@page import="model.Sale"%>
<%@page import="model.Film"%>
<%@page import="model.Room"%>
<%@page import="model.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- /main -->
<%
    ArrayList<Schedule> listSchedule = (ArrayList<Schedule>) request.getAttribute("listSchedule");
    ArrayList<Film> listFilm = (ArrayList<Film>) request.getAttribute("listFilm");
    ArrayList<Room> listRoom = (ArrayList<Room>) request.getAttribute("listRoom");
    ArrayList<Sale> listSale = (ArrayList<Sale>) request.getAttribute("listSale");
%>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Schedule</li>
        </ol>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <!-- col-3  add list-->
            <div class="col-md-2">
                <ul class="list-group">
                    <li class="list-group-item" >
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">List Schedule</a>
                    </li>
                    <li class="list-group-item">
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">Add Schedule</a>
                    </li>
                </ul>
            </div>

            <!-- col-9 -->
            <div class="col-md-10">
                <div class="card">
                    <div class="card-body">
                        <!-- list sale-->
                        <div class="collapse multi-collapse show" id="list">
                            <h5>List Sale</h5>
                            <hr>
                            <table id="example" class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Film</th>
                                        <th>Datetime</th>
                                        <th>Room</th>
                                        <th>Sale</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (Schedule row : listSchedule) {
                                    %>
                                    <tr>
                                        <td><%= row.getId()%></td>
                                        <td><%= row.getFilm().getName()%></td>
                                        <td><%= row.getDate() + " " + row.getTime()%></td>
                                        <td><%= row.getRoom().getName()%></td>
                                        <td><%= row.getSale().getName()%></td>
                                        <td>
                                            <a href="" data-toggle="modal" data-target="#edit<%= row.getId()%>">Edit</a> | 
                                            <a href="#" onclick="return window.confirm('Are you sure!')">Delete</a>
                                        </td>
                                        <!-- Modal edit-->
                                <div class="modal fade" id="edit<%= row.getId()%>" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h5>Edit Schedule</h5>
                                                <hr>

                                                <form class="container" action="addEditSchedule?action=edit&id=<%= row.getId()%>" method="post" id="needs-validation1">
                                                    <div class="film row" data-spy="scroll" data-offset="0" style="height: 275px; overflow-x: hidden; overflow-y: scroll;">
                                                        <!-- /film  -->
                                                        <%
                                                            for (Film film : listFilm) {
                                                                String check = "";
                                                                if (film.getId() == row.getFilm().getId()) {
                                                                    check = "checked";
                                                                }
                                                        %>
                                                        <div class="col-md-3">
                                                            <div class="card" style="height: 50px;">
                                                                <!--<img class="card-img-top" src="admin/download.svg" alt="Card image cap">-->
                                                                <div class="card-block text-left mt-2">
                                                                    <label class="custom-control custom-radio">
                                                                        <input id="film" name="film_id" type="radio" <%= check%> value="<%= film.getId()%>" class="custom-control-input" required>
                                                                        <span class="custom-control-indicator"></span>
                                                                        <span class="custom-control-description"><%= film.getName()%></span>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <% }%>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6 mb-3">
                                                            <label for="date">Date</label>
                                                            <input type="date" class="form-control" id="date" value="<%= row.getDate()%>" name="date" placeholder="Time end" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Date
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="time">Time</label>
                                                            <input type="time" class="form-control" id="time" name="time" value="<%= row.getTime()%>" placeholder="Time" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Time.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6 mb-3">
                                                            <label for="price">Price</label>
                                                            <div class="input-group">
                                                                <input type="number" class="form-control" id="price" name="price" value="<%= row.getPrice()%>" placeholder="number" aria-label="Amount (to the nearest VND)" required>
                                                                <span class="input-group-addon">VND</span>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid price.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <select class="custom-select d-block my-3" name="room_id" required>
                                                        <option value="">Choose Room</option>
                                                        <%
                                                            for (Room room : listRoom) {
                                                                String check = "";
                                                                if (room.getId() == row.getRoom().getId()) {
                                                                    check = "selected";
                                                                }
                                                        %>
                                                        <option value="<%= room.getId()%>" <%= check%> ><%= room.getName()%></option>
                                                        <% } %>
                                                    </select>
                                                    <select class="custom-select d-block my-3" name="sale_id" required>
                                                        <option value="">Choose Sale</option>
                                                        <%
                                                            for (Sale sale : listSale) {
                                                                String check = "";
                                                                if (sale.getId() == row.getSale().getId()) {
                                                                    check = "selected";
                                                                }
                                                        %>
                                                        <option value="<%= sale.getId()%>" <%= check%> ><%= sale.getName()%></option>
                                                        <% } %>
                                                    </select>
                                                    <button class="btn btn-primary" type="submit">Submit form</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <!-- add sale-->
                        <div class="collapse multi-collapse" id="add">
                            <h5>Add Schedule</h5>
                            <hr>
                            <div class="container">
                                <div class="row">
                                    <ul class="nav nav-tabs" role="tablist">

                                        <li role="presentation" class="nav-item">
                                            <a href="#step1" class="nav-link btn-outline-primary rounded-circle active" data-toggle="tab" aria-controls="step1" role="tab" title="Step 1">
                                                1
                                            </a>
                                        </li>

                                        <li role="presentation" class="nav-item disabled">
                                            <a href="#step2" class="nav-link btn-outline-primary rounded-circle " data-toggle="tab" aria-controls="step2" role="tab" title="Step 2">
                                                2
                                            </a>
                                        </li>
                                        <li role="presentation" class="nav-item disabled">
                                            <a href="#step3" class="nav-link btn-outline-primary rounded-circle" data-toggle="tab" aria-controls="step3" role="tab" title="Step 3">
                                                3
                                            </a>
                                        </li>
                                    </ul>

                                    <form class="container-fluid mt-3" method="post" action="addEditSchedule?action=add" id="needs-validation" novalidate>
                                        <div class="tab-content">
                                            <div class="tab-pane active" role="tabpanel" id="step1">
                                                <!-- step 1 select film -->
                                                <h5>Step 1: Select a moive to schedule</h5>
                                                <div class="container">
                                                    <div class="film row" data-spy="scroll" data-offset="0" style="height: 350px; overflow-x: hidden; overflow-y: scroll;">
                                                        <%
                                                            for (Film film : listFilm) {
                                                        %>
                                                        <div class="col-md-3">
                                                            <div class="card" style="height: 50px;">
                                                                <!--<img class="card-img-top" src="admin/download.svg" alt="Card image cap">-->
                                                                <div class="card-block text-left mt-2">
                                                                    <label class="custom-control custom-radio">
                                                                        <input id="film" name="film_id" type="radio" value="<%= film.getId()%>" class="custom-control-input" required>
                                                                        <span class="custom-control-indicator"></span>
                                                                        <span class="custom-control-description"><%= film.getName()%></span>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <% }%>
                                                    </div>
                                                </div>
                                                <button type="button" class="btn btn-xs btn-primary mt-3 next-step">Save and continue</button>
                                            </div>
                                            <div class="tab-pane" role="tabpanel" id="step2">
                                                <!-- step 2 choose time-->
                                                <h5>Step 2: Select room, time, price </h5>
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label for="date">Date</label>
                                                        <input type="date" class="form-control" id="date" name="date" placeholder="Time end" required>
                                                        <div class="invalid-feedback">
                                                            Please provide a valid Date
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label for="time">Time</label>
                                                        <input type="time" class="form-control" id="time" name="time" placeholder="Time" required>
                                                        <div class="invalid-feedback">
                                                            Please provide a valid Time.
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label for="price">Price</label>
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" id="price" name="price" placeholder="number" aria-label="Amount (to the nearest VND)" required>
                                                            <span class="input-group-addon">VND</span>
                                                        </div>
                                                        <div class="invalid-feedback">
                                                            Please provide a valid price.
                                                        </div>
                                                    </div>
                                                </div>
                                                <select class="custom-select d-block my-3" name="room_id" required>
                                                    <option value="">Choose Room</option>
                                                    <%
                                                        for (Room room : listRoom) {
                                                    %>
                                                    <option value="<%= room.getId()%>" ><%= room.getName()%></option>
                                                    <% } %>
                                                </select>
                                                <select class="custom-select d-block my-3" name="sale_id" required>
                                                    <option value="">Choose Sale</option>
                                                    <%
                                                        for (Sale sale : listSale) {
                                                    %>
                                                    <option value="<%= sale.getId()%>" ><%= sale.getName()%></option>
                                                    <% }%>
                                                </select>            
                                                <button type="button" class="btn btn-default prev-step">Previous</button>
                                                <button type="button" class="btn btn-primary next-step">Save and continue</button>
                                            </div>
                                            <div class="tab-pane" role="tabpanel" id="step3">
                                                <h5>Step 3: Submit</h5>
                                                <p>You have successfully completed all steps.</p>
                                                <button type="button" class="btn btn-default prev-step">Previous</button>
                                                <button type="submit" class="btn btn-success">Submit</button>
                                            </div>
                                            <div class="clearfix"></div>
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

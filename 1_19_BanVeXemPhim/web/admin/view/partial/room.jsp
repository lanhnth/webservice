<%-- 
    Document   : sale
    Created on : Dec 3, 2017, 12:59:48 AM
    Author     : ducvu
--%>

<%@page import="model.Cinema"%>
<%@page import="model.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- /main -->
<%ArrayList<Room> listRoom = (ArrayList<Room>) request.getAttribute("listRoom");%>
<%ArrayList<Cinema> listCinema = (ArrayList<Cinema>) request.getAttribute("listCinema");%>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Room & Cinema</li>
        </ol>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <!--room-->
            <div class="col-md-6">
                <div class="container-fluid">
                    <div class="row">
                        <!-- col-2  add list-->
                        <div class="col-xs-2">
                            <ul class="list-group">
                                <li class="list-group-item" >
                                    <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">List Room</a>
                                </li>
                                <!-- <li class="list-group-item" >
                                        <a data-toggle="collapse"  href="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Add</a>
                                </li> -->
                                <li class="list-group-item">
                                    <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">Add Room</a>
                                </li>
                            </ul>
                        </div>

                        <!-- col-10 -->
                        <div class="col-xs-10" style="width: 83%">
                            <div class="card">
                                <div class="card-body">
                                    <!-- list sale-->
                                    <div class="collapse multi-collapse show" id="list">
                                        <h5>List Room</h5>
                                        <hr>
                                        <!--<form action="edit_sale" method="post">-->


                                        <table id="example" class="table table-striped table-bordered" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>#ID</th>
                                                    <th>Name</th>
                                                    <th>Type</th>
                                                    <th>Cinema_id</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <%for (Room room : listRoom) {%>
                                                <tr>
                                                    <td><%=room.getId()%></td>
                                                    <td><%=room.getName()%></td>
                                                    <th><%=room.getType()%></th>
                                                    <td><%=room.getCinema_id()%></td>
                                                    <td>
                                                        <a href="#" data-toggle="modal" data-target="#edit<%=room.getId()%>">Edit</a> 
                                                        <a href="#" onclick="return window.confirm('Are you sure!')">Delete</a>

                                                    </td>

                                                    <!-- Modal edit-->


                                            <div class="modal fade" id="edit<%=room.getId()%>" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-body">

                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                            <h5>Edit Room</h5>
                                                            <hr>

                                                            <form class="container" id="needs-validation" novalidate action="addEditRoom?action=edit&id=<%=room.getId()%>" method="post">
                                                                <div class="row">
                                                                    <div class="col-md-12 mb-3">
                                                                        <label for="nameRoom">Name Room</label>
                                                                        <input type="text" class="form-control" id="nameRoom" name="name" value="<%=room.getName()%>" placeholder="name room" required>
                                                                        <div class="invalid-feedback">
                                                                            Please provide a valid name room.
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <select class="custom-select d-block my-3" name="type" required>
                                                                    <%
                                                                        String[] type = {"VIP", "Standard", "Normal"};
                                                                        for (String t : type) {
                                                                            String check = "";
                                                                            System.out.println(t);
                                                                            if (room.getType().equals(t)) {
                                                                                check = "selected";
                                                                            }
                                                                    %>
                                                                    <option value="<%= t%>" <%= check%> ><%= t%></option>
                                                                    <% } %>
                                                                </select>    
                                                                <select class="custom-select d-block my-3" name="cinema_id" required>
                                                                    <option value="">Choose Room</option>
                                                                    <%
                                                                        for (Cinema cinema : listCinema) {
                                                                            String check = "";
                                                                            if (room.getCinema_id() == cinema.getId()) {
                                                                                check = "selected";
                                                                            }
                                                                    %>
                                                                    <option value="<%= cinema.getId()%>" <%= check%> ><%= cinema.getName()%></option>
                                                                    <% } %>
                                                                </select>
                                                                <button class="btn btn-primary" type="submit">Submit form</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </tr>
                                            <%}%> 

                                            </tbody>
                                        </table>
                                        <!--</form>-->
                                    </div>
                                    <!-- add sale-->
                                    <div class="collapse multi-collapse" id="add">
                                        <h5>Add Room</h5>
                                        <hr>
                                        <form class="container" id="needs-validation" novalidate action="addEditRoom?action=add" method="post">
                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="nameRoom">Name Room</label>
                                                    <input type="text" class="form-control" id="nameRoom" name="name" value="" placeholder="name room" required>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid name room.
                                                    </div>
                                                </div>
                                            </div>
                                            <select class="custom-select d-block my-3" name="type" required>
                                                <option value="">Choose Type</option>
                                                <option value="VIP">VIP</option>
                                                <option value="Standard" >Standard</option>
                                                <option value="Normal" >Normal</option>
                                            </select>    
                                            <select class="custom-select d-block my-3" name="cinema_id" required>
                                                <option value="">Choose Room</option>
                                                <%
                                                    for (Cinema cinema : listCinema) {
                                                %>
                                                <option value="<%= cinema.getId()%>" ><%= cinema.getName()%></option>
                                                <% } %>
                                            </select>
                                            <button class="btn btn-primary" type="submit">Submit form</button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!--cinema-->
            <div class="col-md-6">
                <div class="container-fluid">
                    <div class="row">
                        <!-- col-2  add list-->
                        <div class="col-xs-2">
                            <ul class="list-group">
                                <li class="list-group-item" >
                                    <a data-toggle="collapse" href=".multi-collapse1" aria-expanded="false" aria-controls="list_cinema add_cinema">List Cinema</a>
                                </li>
                                <!-- <li class="list-group-item" >
                                        <a data-toggle="collapse"  href="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Add</a>
                                </li> -->
                                <li class="list-group-item">
                                    <a data-toggle="collapse" href=".multi-collapse1" aria-expanded="false" aria-controls="list_cinema add_cinema">Add Cinema</a>
                                </li>
                            </ul>
                        </div>

                        <!-- col-10 -->
                        <div class="col-xs-10">
                            <div class="card">
                                <div class="card-body">
                                    <!-- list sale-->
                                    <div class="collapse multi-collapse1 show" id="list_cinema">
                                        <h5>List Cinema</h5>
                                        <hr>
                                        <!--<form action="edit_sale" method="post">-->


                                        <table id="example_cinema" class="table table-striped table-bordered" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>#ID</th>
                                                    <th>Name</th>
                                                    <th>Address</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <%for (Cinema cinema : listCinema) {%>
                                                <tr>
                                                    <td><%=cinema.getId()%></td>
                                                    <td><%=cinema.getName()%></td>
                                                    <td><%=cinema.getAddress()%></td>
                                                    <td>
                                                        <a href="#" data-toggle="modal" data-target="#edit_cinema<%=cinema.getId()%>">Edit</a> 
                                                        <a href="#" onclick="return window.confirm('Are you sure!')">Delete</a>

                                                    </td>

                                                    <!-- Modal edit-->


                                            <div class="modal fade" id="edit_cinema<%=cinema.getId()%>" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-body">

                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                            <h5>Edit Cinema</h5>
                                                            <hr>

                                                            <form class="container" id="needs-validation" novalidate action="addEditCinema?action=edit&id=<%=cinema.getId()%>" method="post">
                                                                <div class="row">
                                                                    <div class="col-md-6 mb-3">
                                                                        <label for="nameCinema">Name Cinema</label>
                                                                        <input type="text" class="form-control" id="nameCinema" name="name" value="<%=cinema.getName()%>" placeholder="name room" required>
                                                                        <div class="invalid-feedback">
                                                                            Please provide a valid name Cinema.
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-6 mb-3">
                                                                        <label for="addressCinema">Address</label>
                                                                        <input type="text" class="form-control" id="addressCinema" name="address" value="<%=cinema.getAddress()%>" placeholder="name room" required>
                                                                        <div class="invalid-feedback">
                                                                            Please provide a valid Address Cinema.
                                                                        </div>
                                                                    </div>

                                                                </div>   
                                                                <button class="btn btn-primary" type="submit">Submit form</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </tr>
                                            <%}%> 

                                            </tbody>
                                        </table>
                                        <!--</form>-->
                                    </div>
                                    <!-- add sale-->
                                    <div class="collapse multi-collapse1" id="add_cinema">
                                        <h5>Add Cinema</h5>
                                        <hr>
                                        <form class="container" id="needs-validation" novalidate action="addEditCinema?action=add" method="post">
                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="nameCinema">Name Cinema</label>
                                                    <input type="text" class="form-control" id="nameCinema" name="name" value="" placeholder="Name Cinema" required>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid name Cinema.
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="addressCinema">Address</label>
                                                    <input type="text" class="form-control" id="addressCinema" name="address" value="" placeholder="Address Cinema" required>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Address Cinema.
                                                    </div>
                                                </div>

                                            </div>   
                                            <button class="btn btn-primary" type="submit">Submit form</button>
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

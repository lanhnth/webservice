<%-- 
    Document   : seat
    Created on : Dec 11, 2017, 11:06:44 PM
    Author     : ducvu
--%>

<%@page import="model.Room"%>
<%@page import="model.Seat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%ArrayList<Seat> listSeat = (ArrayList<Seat>) request.getAttribute("listSeat");%>
<%ArrayList<Room> listRoom = (ArrayList<Room>) request.getAttribute("listRoom");%>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Seat</li>
        </ol>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <!-- col-3  add list-->
            <div class="col-md-2">
                <ul class="list-group">
                    <li class="list-group-item" >
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">List Seat</a>
                    </li>
                    <!-- <li class="list-group-item" >
                            <a data-toggle="collapse"  href="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Add</a>
                    </li> -->
                    <li class="list-group-item">
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">Add Seat</a>
                    </li>
                </ul>
            </div>

            <!-- col-9 -->
            <div class="col-md-10">
                <div class="card">
                    <div class="card-body">
                        <!-- list sale-->
                        <div class="collapse multi-collapse show" id="list">
                            <h5>List Seat</h5>
                            <hr>
                            <!--<form action="edit_sale" method="post">-->


                            <table id="example" class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Col</th>
                                        <th>Row</th>
                                        <th>Type</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%for (Seat seat : listSeat) {%>
                                    <tr>
                                        <td><%=seat.getId()%></td>
                                        <td><%=seat.getCol()%></td>
                                        <td><%=seat.getRow()%></td>
                                        <td><%=seat.getType()%></td>
                                        <td>
                                            <a href="#" data-toggle="modal" data-target="#edit<%=seat.getId()%>">Edit |</a> 
                                            <a href="#" onclick="return window.confirm('Are you sure!')">Delete</a>

                                        </td>

                                        <!-- Modal edit-->


                                <div class="modal fade" id="edit<%=seat.getId()%>" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">

                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h5>Edit Seat</h5>
                                                <hr>

                                                <form class="container" id="needs-validation" novalidate action="addEditSeat?action=edit&id=<%=seat.getId()%>" method="post">

                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <select class="custom-select d-block my-3" name="col" required>
                                                                <option value="0">Choose Col</option>
                                                                <%
                                                                    int i;
                                                                    for (i = 0; i <= 12; i++) {
                                                                        String check = "";
                                                                        if (seat.getCol() == i) {
                                                                            check = "selected";
                                                                        }
                                                                %>
                                                                <option value="<%= i%>" <%= check%> ><%= i%></option>
                                                                <% } %>
                                                            </select>   
                                                            <div class="invalid-feedback">
                                                                Please provide a valid col.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <select class="custom-select d-block my-3" name="row" required>
                                                                <option value="0">Choose Row</option>
                                                                <%
                                                                    String[] type = {"A", "B", "C", "D", "E", "F", "G", "H"};
                                                                    i = 0;
                                                                    for (String t : type) {
                                                                        String check = "";
                                                                        i++;
                                                                        if (seat.getRow() == i) {
                                                                            check = "selected";
                                                                        }
                                                                %>
                                                                <option value="<%=i%>" <%= check%> ><%= t%></option>
                                                                <% } %>
                                                            </select>   
                                                            <div class="invalid-feedback">
                                                                Please provide a valid row.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <select class="custom-select d-block my-3" name="type" required>
                                                                <option value="0">Choose Type</option>
                                                                <%
                                                                    String[] type1 = {"VIP", "Standard", "Couple"};
                                                                    for (String t : type1) {
                                                                        String check = "";
                                                                        System.out.println(t);
                                                                        if (seat.getType().equals(t)) {
                                                                            check = "selected";
                                                                        }
                                                                %>
                                                                <option value="<%= t%>" <%= check%> ><%= t%></option>
                                                                <% } %>
                                                            </select>   
                                                        </div>
                                                        <div class="col-md-12">
                                                            <select class="custom-select d-block my-3" name="id_room" required>
                                                                <option value="0">Choose Room</option>
                                                                <%
                                                                    for (Room room : listRoom) {
                                                                        String check = "";
                                                                        if (room.getId()== seat.getRoom().getId()) {
                                                                            check = "selected";
                                                                        }
                                                                %>
                                                                <option value="<%= room.getId()%>" <%= check%> ><%= room.getName()%></option>
                                                                <% } %>
                                                            </select>   
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
                        <div class="collapse multi-collapse" id="add">
                            <h5>Add Seat</h5>
                            <hr>
                            <form class="container" id="needs-validation" novalidate action="addEditSeat?action=add" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <select class="custom-select d-block my-3" name="col" required>
                                            <option value="0">Choose Col</option>
                                            <option value="1" >1</option>
                                            <option value="2" >2</option>
                                            <option value="3" >3</option>
                                            <option value="4" >4</option>
                                            <option value="5" >5</option>
                                            <option value="6" >6</option>
                                            <option value="7" >7</option>
                                            <option value="8" >8</option>
                                            <option value="9" >9</option>
                                            <option value="10" >10</option>
                                            <option value="11" >11</option>
                                            <option value="12" >12</option>
                                        </select>   
                                        <div class="invalid-feedback">
                                            Please provide a valid col.
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <select class="custom-select d-block my-3" name="row" required>
                                            <option value="0">Choose Row</option>
                                            <option value="1" >A</option>
                                            <option value="2" >B</option>
                                            <option value="3" >C</option>
                                            <option value="4" >D</option>
                                            <option value="5" >E</option>
                                            <option value="6" >F</option>
                                            <option value="7" >G</option>
                                            <option value="8" >H</option>
                                        </select>   
                                        <div class="invalid-feedback">
                                            Please provide a valid row.
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <select class="custom-select d-block my-3" name="type" required>
                                            <option value="0">Choose Type</option>
                                            <option value="VIP" >VIP</option>
                                            <option value="Standard" >Standard</option>
                                            <option value="Couple" >Couple</option>
                                        </select>   
                                    </div>
                                    <div class="col-md-12">
                                        <select class="custom-select d-block my-3" name="id_room" required>
                                            <option value="0">Choose Room</option>
                                            <%
                                                for (Room room : listRoom) {
                                            %>
                                            <option value="<%= room.getId()%>" ><%= room.getName()%></option>
                                            <% }%>
                                        </select>   
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



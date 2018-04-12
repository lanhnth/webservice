<%-- 
    Document   : sale
    Created on : Dec 3, 2017, 12:59:48 AM
    Author     : ducvu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Sale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- /main -->
<%ArrayList<Sale> listSale = (ArrayList<Sale>) request.getAttribute("listSale");%>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Sale</li>
        </ol>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <!-- col-3  add list-->
            <div class="col-md-2">
                <ul class="list-group">
                    <li class="list-group-item" >
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">List Sale</a>
                    </li>
                    <!-- <li class="list-group-item" >
                            <a data-toggle="collapse"  href="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Add</a>
                    </li> -->
                    <li class="list-group-item">
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add">Add Sale</a>
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
                            <!--<form action="edit_sale" method="post">-->


                            <table id="example" class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Name</th>
                                        <th>Number</th>
                                        <th>Time Start</th>
                                        <th>Time End</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%for (int i = 0; i < listSale.size(); i++) {%>
                                    <tr>
                                        <td><%=listSale.get(i).getId()%></td>
                                        <td><%=listSale.get(i).getName()%></td>
                                        <td><%=listSale.get(i).getNumber()%></td>
                                        <td><%=listSale.get(i).getSdate()%></td>
                                        <td><%=listSale.get(i).getEdate()%></td>
                                        <td>
                                            <a href="#" data-toggle="modal" data-target="#edit<%=i%>">Edit</a> 
                                            <!--<a href="#" onclick="return window.confirm('Are you sure!')">Delete</a>-->

                                        </td>

                                        <!-- Modal edit-->


                                <div class="modal fade" id="edit<%=i%>" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">

                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h5>Edit Sale</h5>
                                                <hr>

                                                <form class="container" id="needs-validation" novalidate action="add_edit_sale?action=edit&idEdit=<%=listSale.get(i).getId()%>" method="post">
                                                    <div class="row">
                                                        <div class="col-md-6 mb-3">
                                                            <label for="nameSale">Name sale</label>
                                                            <input type="text" class="form-control" id="nameSale" name="nameEdit" value="<%=listSale.get(i).getName()%>" placeholder="name sale" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid name sale.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="number">Number</label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" id="number" name="numberEdit" placeholder="number" value="<%=listSale.get(i).getNumber()%>" aria-label="Amount % (to the nearest VND)" required>
                                                                <span class="input-group-addon">%</span>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid number.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12 mb-3">
                                                            <label for="des">Description</label>
                                                            <textarea class="form-control" id="des" name="desEdit" rows="6" required><%=listSale.get(i).getDes()%></textarea>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid des.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="sdate">Time start</label>
                                                            <input type="date" class="form-control" id="sdate" name="sdateEdit" value="<%=listSale.get(i).getSdate()%>" placeholder="Time start" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Time start.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="edate">Time end</label>
                                                            <input type="date" class="form-control" id="edate" name="edateEdit" value="<%=listSale.get(i).getEdate()%>" placeholder="Time end" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Time end.
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
                        <div class="collapse multi-collapse" id="add">
                            <h5>Add Sale</h5>
                            <hr>
                            <form class="container" id="needs-validation" novalidate action="add_edit_sale?action=add" method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="nameSale">Name sale</label>
                                        <input type="text" class="form-control" id="nameSale" name="name" placeholder="name sale" required>
                                        <div class="invalid-feedback">
                                            Please provide a valid name sale.
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="number">Number</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="number" name="number" placeholder="number" aria-label="Amount % (to the nearest VND)" required>
                                            <span class="input-group-addon">%</span>
                                        </div>
                                        <div class="invalid-feedback">
                                            Please provide a valid number.
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="des">Description</label>
                                        <textarea class="form-control" name="des" rows="6" required></textarea>
                                        <div class="invalid-feedback">
                                            Please provide a valid des.
                                        </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="sdate">Time start</label>
                                        <input type="date" class="form-control" id="sdate" name="sdate" placeholder="Time start" required>
                                        <div class="invalid-feedback">
                                            Please provide a valid Time start.
                                        </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="edate">Time end</label>
                                        <input type="date" class="form-control" id="edate" name="edate" placeholder="Time end" required>
                                        <div class="invalid-feedback">
                                            Please provide a valid Time end.
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

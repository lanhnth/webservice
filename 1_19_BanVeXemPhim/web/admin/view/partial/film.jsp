<%-- 
    Document   : film
    Created on : Dec 3, 2017, 1:05:55 AM
    Author     : ducvu
--%>

<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Film"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- /main -->
<%ArrayList<Film> listFilm = (ArrayList<Film>) request.getAttribute("listFilm");
    ArrayList<Category> listCategory = (ArrayList<Category>) request.getAttribute("listCategory");
    session.setAttribute("listCategory", listCategory);
%>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Film</li>
        </ol>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <!-- col-3  add list-->
            <div class="col-md-2">
                <ul class="list-group">
                    <li class="list-group-item" >
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add import">List Film</a>
                    </li>
                    <!-- <li class="list-group-item" >
                            <a data-toggle="collapse"  href="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Add</a>
                    </li> -->
                    <li class="list-group-item">
                        <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add import">Add Film</a>
                    </li>
                    <!-- <li class="list-group-item">
                            <a data-toggle="collapse" href=".multi-collapse" aria-expanded="false" aria-controls="list add import">Import Film to API</a>
                    </li> -->
                </ul>
            </div>

            <!-- col-9 -->
            <div class="col-md-10">
                <div class="card">
                    <div class="card-body">
                        <!-- list film-->
                        <div class="collapse multi-collapse show" id="list">
                            <h5>List Film</h5>
                            <hr>
                            <table id="example" class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Image</th>
                                        <th>Name</th>
                                        <th>ReleaseDate</th>
                                        <th>Vote</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (int i = 0; i < listFilm.size(); i++) {%>
                                    <tr>
                                        <td><%=listFilm.get(i).getId()%></td>
                                        <td><img src="<%=listFilm.get(i).getPoster()%>" style="width: 50px;"></td>
                                        <td><%=listFilm.get(i).getName()%></td>
                                        <td><%=listFilm.get(i).getRealeaseDate()%></td>
                                        <td><%=listFilm.get(i).getVote()%></td>
                                        <td>
                                            <a href="" data-toggle="modal" data-target="#edit<%=i%>">Edit</a> 

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

                                                <form class="container" id="needs-validation1" method="post" action="add_edit_film?action=edit" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="col-md-6 mb-3">
                                                            <input type="hidden" name="idEdit" value="<%=listFilm.get(i).getId()%>">
                                                            <label for="name">Name Film</label>
                                                            <input type="text" class="form-control" id="name" name="nameEdit" value="<%=listFilm.get(i).getName()%>" placeholder="name film" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid name film.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="country">Country</label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" id="country" name="countryEdit" value="<%=listFilm.get(i).getProductCountry()%>" placeholder="country" required>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid country.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <label for="des">Description</label>
                                                            <textarea class="form-control" id="des" name="desEdit" rows="6" required><%=listFilm.get(i).getDes()%></textarea>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid des.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="releaseDate">Release Date</label>
                                                            <input type="date" class="form-control" id="releaseDate" name="releaseDateEdit" value="<%=listFilm.get(i).getRealeaseDate()%>" placeholder="Release Date" required>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Release Date.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3 mb-3">
                                                            <label for="vote">Vote</label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" id="vote" name="voteEdit" value="<%=listFilm.get(i).getVote()%>" placeholder="vote" required>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid vote.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3 mb-3">
                                                            <label for="runningTime">Running Time</label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" id="runningTime" name="runningTimeEdit" value="<%=listFilm.get(i).getRunningTime()%>" placeholder="Running Time" required>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid Running Time.
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <label for="trailer">Link trailer</label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control" id="trailer" name="trailerEdit" value="<%=listFilm.get(i).getTrailer()%>" placeholder="trailer" required>
                                                            </div>
                                                            <div class="invalid-feedback">
                                                                Please provide a valid trailer.
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="poster">File input</label>
                                                        <input type="file" class="form-control-file" id="poster" name="posterEdit"  aria-describedby="fileHelp">
                                                        <small id="fileHelp" class="form-text text-muted">Select file poster.</small>
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
                        </div>
                        <!-- add film-->
                        <div class="collapse multi-collapse" id="add">
                            <h5>Add Film</h5>
                            <hr>
                            <form class="container" id="needs-validation" novalidate action="add_edit_film?action=add" method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="name">Name Film</label>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="name film" required>
                                        <div class="invalid-feedback">
                                            Please provide a valid name film.
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="country">Country</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="country" name="country" placeholder="country" required>
                                        </div>
                                        <div class="invalid-feedback">
                                            Please provide a valid country.
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="des">Description</label>
                                        <textarea class="form-control" id="des" rows="6" name="des" required></textarea>
                                        <div class="invalid-feedback">
                                            Please provide a valid des.
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="releaseDate">Release Date</label>
                                        <input type="date" class="form-control" id="releaseDate" name="releaseDate" placeholder="Release Date" required>
                                        <div class="invalid-feedback">
                                            Please provide a valid Release Date.
                                        </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="vote">Vote</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="vote" name="vote" placeholder="vote" required>
                                        </div>
                                        <div class="invalid-feedback">
                                            Please provide a valid vote.
                                        </div>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="runningTime">Running Time</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="runningTime" name="runningTime" placeholder="Running Time" required>
                                        </div>
                                        <div class="invalid-feedback">
                                            Please provide a valid Running Time.
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="trailer">Link trailer</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="trailer" name="trailer" placeholder="trailer" required>
                                        </div>
                                        <div class="invalid-feedback">
                                            Please provide a valid trailer.
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Category">Category</label>
                                    <select multiple class="form-control" id="Category" name="category" required>
                                        <%for (Category c : listCategory) {%>
                                            <option value="<%=c.getId()%>"><%=c.getName()%></option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="poster">File input</label>
                                    <input type="file" class="form-control-file" id="poster" name="poster"  aria-describedby="fileHelp">
                                    <small id="fileHelp" class="form-text text-muted">Select file poster.</small>
                                </div>
                                <input class="btn btn-primary" type="submit" value="Submit form">
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
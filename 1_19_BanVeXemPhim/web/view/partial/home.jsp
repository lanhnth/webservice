<%-- 
    Document   : home
    Created on : Nov 29, 2017, 3:58:52 PM
    Author     : NguyenNgoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <script src="js/home.js"></script>
<link rel="stylesheet" type="text/css" href="css/home.css">
<div class="main-container">
    <div class="top-container">
        <!-- // dành cho trang chủ -->
        <div class="sect-person">
            <ul>
                <li><a class="theater" href="" title=""></a></li>
                <li><a class="now-sh" href="" title=""></a></li>
                <li><a class="special" href="" title=""></a></li>
                <li><a class="arthouse" href="" title=""></a></li>
                <li><a class="contact" href="" title=""></a></li>
                <li><a class="new" href="" title=""></a></li>
                <li><a class="login" href="" title=""></a></li>
            </ul>
        </div>
    </div>
    <div class="breadcrumbs">
        <!-- // dành cho phim và rạp -->
    </div>
    <div class="banner-left">
        <a href="" title=""><img src="img/baner-left-vn.jpg" alt=""></a>
    </div>
    <div class="banner-right">
        <div class="content-banner-right">
            <a href="" title=""><img src="img/en-banner-right-1.png" alt=""></a>
            <a href="" title=""><img src="img/en-banner-right-2.png" alt=""></a>
            <a href="" title=""><img src="img/vn-banner-right-3.png" alt=""></a>
            <a href="" title=""><img src="img/vn-banner-right-4.png" alt=""></a>
        </div>
    </div>
    <div class="main-container-content">
        <div class="main">
            <div class="slideshow-container">
                <div class="container">
                    <div id="slide-show" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#slide-show" data-slide-to="0" class="active"></li>
                            <li data-target="#slide-show" data-slide-to="1"></li>
                            <li data-target="#slide-show" data-slide-to="2"></li>
                            <li data-target="#slide-show" data-slide-to="3"></li>
                            <li data-target="#slide-show" data-slide-to="4"></li>
                        </ol>

                        <div class="carousel-inner">
                            <div class="item active">
                                <a href="" title=""><img src="img/slideshow1.jpg"></a>
                                <div class="carousel-caption">
                                    <!-- ... -->
                                </div>
                            </div>
                            <div class="item">
                                <a href="" title=""><img src="img/slideshow2.jpg"></a>
                                <div class="carousel-caption">
                                    <!-- ... -->
                                </div>
                            </div>
                            <div class="item">
                                <a href="" title=""><img src="img/slideshow3.png"></a>
                                <div class="carousel-caption">
                                    <!-- ... -->
                                </div>
                            </div>
                            <div class="item">
                                <a href="" title=""><img src="img/slideshow4.jpg"></a>
                                <div class="carousel-caption">
                                    <!-- ... -->
                                </div>
                            </div>
                            <div class="item">
                                <a href="" title=""><img src="img/slideshow5.jpg"></a>
                                <div class="carousel-caption">
                                    <!-- ... -->
                                </div>
                            </div>
                            <a class="left carousel-control" href="#slide-show" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#slide-show" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                    <script>
                        $('#slide-show').carousel({
                            interval: 4000
                        });
                    </script>
                </div>
            </div>
            <div class="moive-selection">
                <div class="title">
                    <img src="img/h3_movie_selection.gif" alt="" height="60px" >
                </div>
                <!-- Item slider-->
                <div class="container-fluid">

                    <div class="row selection-item">

                    </div>

                </div>
                <!-- Item slider end-->
                <!-- <div class="modal fade" id="modal1">
        <div class="modal-dialog">
                <div class="modal-content">
                        <div class="modal-body">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <iframe id="moivetrailer1" width="560" height="315" src="https://www.youtube.com/embed/_affkHceSj4?autoplay=1" frameborder="0" allowfullscreen></iframe>	
                        </div>
                </div>
        </div>
    </div> -->
                <!-- <script type="text/javascript">
                        $(document).ready(function(){
                            /* Get iframe src attribute value i.e. YouTube video url
                            and store it in a variable */
                            var url = $("#moivetrailer1").attr('src');
                            
                            /* Assign empty url value to the iframe src attribute when
                            modal hide, which stop the video playing */
                            $("#modal1").on('hide.bs.modal', function(){
                                $("#moivetrailer1").attr('src', '');
                            });
                            
                            /* Assign the initially stored url back to the iframe src
                            attribute when modal is displayed again */
                            $("#modal1").on('show.bs.modal', function(){
                                $("#moivetrailer1").attr('src', url);
                            });
                        });
                </script> -->
            </div>
            <div class="event">
                <div class="title">
                    <img src="img/h3_event.gif" alt="">
                </div>
                <!-- Item slider-->
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="carousel carousel-showmanymoveone slide" id="eventslider">
                                <div class="carousel-inner">

                                    <div class="item active">
                                        <div class="col-xs-12 col-sm-6 col-md-3">
                                            <a href="#"><img src="img/footer1.jpg" class="img-responsive"></a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="col-xs-12 col-sm-6 col-md-3">
                                            <a href="#"><img src="img/footer2.jpg" class="img-responsive"></a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="col-xs-12 col-sm-6 col-md-3">
                                            <a href="#"><img src="img/footer3.jpg" class="img-responsive"></a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="col-xs-12 col-sm-6 col-md-3">
                                            <a href="#"><img src="img/footer4.jpg" class="img-responsive"></a>
                                        </div>
                                    </div>
                                </div>

                                <div id="slider-event-control">
                                    <a class="left carousel-control" href="#eventslider" data-slide="prev"><img src="img/arow-movie-selection-left.png" alt="Left" class="img-responsive"></a>
                                    <a class="right carousel-control" href="#eventslider" data-slide="next"><img src="img/arow-movie-selection-right.png" alt="Right" class="img-responsive"></a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- Item slider end-->
            </div>
            <script src="js/slider.js"></script>
        </div>
    </div>

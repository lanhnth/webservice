<%-- 
    Document   : import_film
    Created on : Dec 15, 2017, 5:54:32 PM
    Author     : ducvu
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!DOCTYPE html>
<div class="container">
    <!-- /breadcrumb -->
    <nav aria-label="breadcrumb" role="navigation">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Import Film Api</li>
        </ol>
    </nav>

    <div class="container">
        <div class="col-md-12 col-process">
            <button type="button" class="btn btn-info start_api">Click to Import</button>
            <br> <br>
            <h5>Process: <span> <strong class="film_count">0</strong> / <span class="film_total">0</span> Film</span> <i class="fa fa-spinner fa-pulse fa-3x fa-fw loading_api" aria-hidden="true" style="font-size: 17px; display: none;"></i></h5>
            <span class="sr-only">Loading...</span>
            <div class="progress">
                <div class="progress-bar progress-bar-striped progress-bar-animated process_api" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%; display: none;"></div>
            </div>
            <br>
            <div class="alert alert-success" role="alert" style="display: none;">
                <h4 class="alert-heading">Well done!</h4>
                <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
                <hr>
                <p class="mb-0">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>
            </div>
            <div class="alert alert-warning" role="alert" style="display: none;">
                <h4 class="alert-heading">Please don't turn off or redirect...!</h4>
            </div>
            <div class="alert alert-danger" role="alert" style="display: none;">
                <h4 class="alert-heading">Please check network...! Request api fail! :(</h4>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        var baseUrl = "https://api.themoviedb.org/3/movie/";
        var api_key = "931e54c126cfca6e31b0c905d36d6614";
        var baseImg = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";

        var page = 0;

        function getListIdApi() {
            console.log("Running");
            var urlGetList = baseUrl + "now_playing?page=35&api_key=" + api_key;
            fetch(urlGetList).then((resp) => resp.json()).then(function (data) {
                let all = data;
                var run = 0;
                console.log(all.results);
                $(".film_total").html(all.total_results);
                for (var i = 1; i <= all.total_pages; i++) {
                    var urlFilmPage = baseUrl + "now_playing?page=" + i + "&api_key=" + api_key;
//                    setTimeout(function () {
                    fetch(urlFilmPage).then((resp) => resp.json()).then(function (res) {
                        let allData = res;
                        $.each(allData.results, function (key, value) {


                            setTimeout(function () {
                                run++;
                                $(".film_count").html(run);
                                $(".process_api").css("width", (run / all.total_results) * 100 + "%");

                                var urlFilm = baseUrl + value.id + "?api_key=" + api_key;
                                fetch(urlFilm).then((resp) => resp.json()).then(function (respond) {
                                    let film = respond;
                                    let rerelease_date = "01-01-2000";
                                    if (film.release_date !== "" && film.release_date === undefined) {
                                        rerelease_date = film.release_date;
                                    }
                                    let data = {
                                        api_id: film.id === null ? 0 : film.id,
                                        name: film.original_title !== "" ? film.original_title : " ",
                                        release_date: film.release_date !== "" ? film.release_date : "01-01-2000",
                                        runtime: film.runtime === null ? 0 : film.runtime,
                                        vote: film.vote_average === null ? 0 : film.vote_average,
                                        des: film.overview !== "" ? film.overview : "No thing!",
                                        trailler: " ",
                                        poster: film.poster_path === undefined ? " " : baseImg + film.poster_path,
                                        category: film.genres ? film.genres : "[]"
                                    };

                                    console.log(data);

                                    if (film.id !== undefined) {
                                        fetch("filmApi", {
                                            method: 'POST',
                                            headers: new Headers(),
                                            body: JSON.stringify(data)
                                        }).then((resp) => resp.json()).then(function (respond) {
                                            $(".film_count").html(run);
                                            $(".process_api").css("width", (run / all.total_results) * 100 + "%");
                                            console.log(value.id + " done ");
                                            if ($(".process_api").css("width") === $(".progress").css("width")) {
                                                setTimeout($(".alert-success").show(), 2000);
                                                $(".loading_api").hide();
                                                $(".alert-warning").hide();
                                                $(".start_api").html("Done");
                                            }
                                        }).catch(function (error) {
                                            $(".alert-danger").show();
                                            console.log(error);
                                        });
                                    }



                                }).catch(function (error) {
                                    $(".alert-danger").show();
                                    console.log(error);
                                });
                            }, 5000);
                        });
                    }).catch(function (error) {
                        $(".alert-danger").show();
                        console.log(error);
                    });

//                    }, 3000);
                }

            }).catch(function (error) {
                $(".alert-danger").show();
                console.log(error);
            });
            console.log("End");
        }

        $(".start_api").on("click", function () {
            $(".loading_api").show();
            $(".alert-warning").show();
            $(".process_api").show();

            $(this).html("Starting....");
            getListIdApi();
        });
    });
</script>

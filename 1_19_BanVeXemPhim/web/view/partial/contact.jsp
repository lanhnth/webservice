<%-- 
    Document   : contact
    Created on : Apr 29, 2018, 6:00:54 PM
    Author     : Lanh
--%>

<%@page import="java.util.List"%>
<%@page import="control.DBConnection"%>
<%@page import="model.Cinema"%>
<%@page import="modelDAO.CinemaDAOImpl"%>
<%@page import="modelDAO.CinemaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/view_lichchieu_main.css">
<style>
    /* Always set the map height explicitly to define the size of the div
 * element that contains the map. */
    #map {
        height: 100%;
    }
    /* Optional: Makes the sample page fill the window. */
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    .controls {
        margin-top: 10px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
    }

    #pac-input {
        background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 300px;
    }

    #pac-input:focus {
        border-color: #4d90fe;
    }

    .pac-container {
        font-family: Roboto;
    }

    #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
    }

    #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
    }
    #target {
        width: 345px;
    }
</style>
<%
    CinemaDAO dao = new CinemaDAOImpl();
    DBConnection db = new DBConnection();
    List<Cinema> listCi = dao.getListCinema(db.getConnection());
%>
<div class="main-container">
    <div class="lc-main">
        <div class="row">
            <div class="lc-col-main">
                <!-- main-container -->
                <div class="lc-main-container">
                    <div class="lc-main-theater-header">
                        <div class="home-title">
                            <h3>Rạp chiếu phim</h3>
                        </div>
                        <div class="page-title theater-title">
                            <h3>CGV Hồ Gươm Plaza</h3>
                        </div>
                    </div>
                    <!-- slide  -->
                    <div class="lc-main-slide">
                        <div id="carousel-id" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="item">
                                    <img alt="CGV Hồ Gươm Plaza" src="img/slide1.jpg">
                                </div>
                                <div class="item">
                                    <img  alt="CGV Hồ Gươm Plaza" src="img/slide2.jpg">
                                </div>
                                <div class="item active">
                                    <img alt="CGV Hồ Gươm Plaza" src="img/slide3.jpg">
                                </div>
                            </div>
                            <div class="carousel-caption-text">
                                <div class="carousel-caption-left">
                                    <div class="carousel-caption-left-content">
                                        <h6>Tầng 3, TTTM Hồ Gươm Plaza, 102 Trần Phú, Phường Mỗ Lao, Quận Hà Đông, Hà Nội</h6>
                                        <h6><strong>Fax: </strong>+84 4 6 275 5240</h6>
                                        <h6><strong>Hotline: </strong> 1900 6017</h6>
                                    </div>
                                </div>
                                <div class="carousel-caption-right">
                                    <ul class="carousel-caption-right-nav">
                                        <li><a href="#map" class="btn-carousel">XEM BẢN ĐỒ</a></li>
                                        <li><a href="#" class="btn-carousel">LIÊN HỆ CGV</a></li>
                                    </ul>
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="prev-left"></span></a>
                            <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="next-right"></span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input id="pac-input" class="controls" type="text" placeholder="Search Box" style="margin: 0 250px;">
<div id="map" style="margin: 0 200px;"></div>
<!-- Replace the value of the key parameter with your own API key. -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAygJYL4zqe0iOYUr-7J8zpCo6fUnGyv3g&libraries=places&language=vi&region=VI&callback=initMap"
async defer></script>
<script>
    var map, marker, infowindow;
    var markers = [];
    var address_infos = {};
    var cinemas = [];
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 13,
            center: {lat: 20.979019, lng: 105.785642},
            streetViewControl: false,
            mapTypeControl: false
        });

//        placeMarker({lat: 21.003054, lng: 105.815493});
//        geocodeAddress(marker.position);
//        map.panTo(marker.position);
//        markers.push(marker);
    <% for (int i = 0; i < listCi.size(); i++) {%>
        var southWest = new google.maps.LatLng(<%= listCi.get(i).getId_x()%>, <%= listCi.get(i).getId_y()%>);
        // Thuộc tính khởi tạo Marker 
        var marker2 = new google.maps.Marker({
            position: southWest,
            map: map,
            title: 'Click to zoom'
        });
//        var mess[i] = <%= listCi.get(i).getName().toString() %>;
        var message = ['CGV Hồ Gươm Plaza', 'CGV Vincom Center Bà Triệu'];
        attachSecretMessage(marker2, message[<%=i%>]);
//        attachSecretMessage(marker2, <%= listCi.get(i).getName() %>);
//        var name = <%= listCi.get(i).getName().toString()%>;
//        geocodeAddress(marker2.position);
//        map.panTo(marker2.position);
//        markers.push(marker2);
    <% }%>
//        map.addListener("click", function (e) {
//            clearMarkers();
//            placeMarker(e.latLng);
//            geocodeAddress(e.latLng);
//            map.panTo(marker.position);
//            markers.push(marker);
//        });
//        createInfoWindow();
//        attachSecretMessage(marker2, 0);
// 
        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        searchBox.addListener("places_changed", function () {
            searchBox.set("map", null);
            clearMarkers();

            var places = searchBox.getPlaces();
            if (places.length == 0) {
                console.log("Returned no place");
                return;
            }

            var bounds = new google.maps.LatLngBounds();

            if (places.length > 1) {
                places.forEach(function (place) {
                    if (!place.geometry) {
                        console.log("Returned place contains no geometry");
                        return;
                    }

                    var icon = {
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(25, 25)
                    };

                    marker = new google.maps.Marker({
                        map: map,
                        icon: icon,
                        title: place.name,
                        label: place.name,
                        position: place.geometry.location
                    });

                    marker.bindTo("map", searchBox, "map");
                    marker.addListener("map_changed", function () {
                        if (!this.getMap()) {
                            this.unbindAll();
                        }
                    });

                    if (place.geometry.viewport) {
                        // Only geocodes have viewport.
                        bounds.union(place.geometry.viewport);
                    } else {
                        bounds.extend(place.geometry.location);
                    }
                });

                map.fitBounds(bounds);
                searchBox.set("map", map);
                map.setZoom(Math.min(map.getZoom(), 15));
                // searchBox.setBounds(map.getBounds());
            } else {
                infowindow.close();
                var place = places[0];
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }

                placeMarker(place.geometry.location);

                marker.bindTo("map", searchBox, "map");
                marker.addListener("map_changed", function () {
                    if (!this.getMap()) {
                        this.unbindAll();
                    }
                });

                create_address_infos(place);

                infowindow.setContent(
                        "<div>" +
                        "<b>Name: </b>" + place.name + "<br>" +
                        "<b>Address :</b> " + place.formatted_address + "<br>" +
                        "<b>Latitude :</b> " + place.geometry.location.lat() + "<br>" +
                        "<b>Longitude :</b> " + place.geometry.location.lng() +
                        "</div>"
                        );

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }

                map.fitBounds(bounds);
                searchBox.set("map", map);
                map.setZoom(Math.min(map.getZoom(), 15));
                infowindow.open(map, marker);
                markers.push(marker);
            }
        });

    }

    function placeMarker(latLng) {
        marker = new google.maps.Marker({
            position: latLng,
            map: map
        });
    }

    function createInfoWindow() {
        if (infowindow) {
            infowindow.close();
        }
        infowindow = new google.maps.InfoWindow();
    }

    function geocodeAddress(latLng) {
        var geocoder = new google.maps.Geocoder;
        createInfoWindow();

        geocoder.geocode(
                {"location": latLng},
                function (results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        if (results[0]) {
                            create_address_infos(results[0]);

                            infowindow.setContent(
                                    "<div>" +
                                    "<b>Name: </b>" + place.name + "<br>" +
                                    "<b>Address :</b> " + address_infos["name"] + "<br>" +
                                    "<b>Latitude :</b> " + address_infos["latitude"] + "<br>" +
                                    "<b>Longitude :</b> " + address_infos["longitude"] +
                                    "</div>"
                                    );
                            infowindow.open(map, marker);
                        } else {
                            console.log("No results found");
                        }
                    } else {
                        console.log("Geocoder failed due to: " + status);
                    }
                }
        );
    }

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null)
        }
        markers = [];
    }

    function create_address_infos(address) {
        address_infos = {
            name: address.formatted_address.toString(),
            latitude: address.geometry.location.lat(),
            longitude: address.geometry.location.lng(),
            prefecture: "",
            city: "",
            town: "",
            choume: "",
            banchi: "",
            gou: ""
        }
    }

    // Thêm message thông báo khi click vào marker
    // tham số là marker đang click và số thứ tự của message
    // như vậy nó sử dụng closure để thiết lập message cho  từng marker
    function attachSecretMessage(marker, message)
    {
        // Danh sách message
//        var message = ['CGV Hồ Gươm Plaza', 'CGV Vincom Center Bà Triệu'];
        // Khởi tạo của sổ message
        var infowindow = new google.maps.InfoWindow({
            content: message
        });
        // Gắn của sổ vào sự kiện clic vào marker
        google.maps.event.addListener(marker, 'click', function () {
            // Hàm open có hai tham số đó là map nó đang được gắn vào và marker đó 
            infowindow.open(marker.get('map'), marker);
        });
//        markers[i].addListener('click', function () {
//            infowindow.open(markers[i].get('map'), markers[i]);
//        });
    }
</script>

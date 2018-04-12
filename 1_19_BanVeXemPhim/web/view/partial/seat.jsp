<%-- 
    Document   : seat
    Created on : Nov 29, 2017, 5:49:19 PM
    Author     : NguyenNgoc
--%>

<%@page import="model.Schedule"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/view_seats_main.css">
<%
    Schedule schedule = (Schedule) request.getAttribute("schedule");
    int numberSeated = (Integer) request.getAttribute("numberSeated");
    int numberSeat = (Integer) request.getAttribute("numberSeat");

%>
<div class="main-container">
    <div class="lc-main">
        <div class="row">
            <div class="lc-col-main">
                <!-- main-container -->
                <div class="lc-main-container">

                    <div class="lc-main-book-ticket">
                        <!-- header -->
                        <div class="lc-main-book-ticket-header">
                            <h3>Người / Ghế</h3>
                        </div>
                        <!-- end header -->
                        <!-- site  -->
                        <div class="lc-main-book-ticket-site">
                            <div class="book-ticket-item">
                                <div class="lc-main-book-ticket-site-left">
                                    <p>CGV Ho Guom Plaza | <%= schedule.getRoom().getName()%> | Số ghế <span>(<%= numberSeat - numberSeated%>/<%= numberSeat%>)</span></p>
                                    <p class="book-time"><%= schedule.getDate()%> | <%= schedule.getTime()%></p>
                                </div>
                                <div class="lc-main-book-ticket-site-right">
                                    <!-- site book combo  -->
                                    <div class="book-ticket-combo">
                                        <div class="book-ticket-combo-left">
                                            CGV COMBO(ONLINE)
                                        </div>
                                        <div class="book-ticket-combo-right">
                                            <span class="icon-book"></span>
                                            <ul>
                                                <li class="active-combo-book"><span>0</span></li>
                                                <li><span>1</span></li>
                                                <li><span>2</span></li>
                                                <li><span>3</span></li>
                                                <li><span>4</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="clear-both"></div>
                                    <div class="space"></div>
                                    <div class="book-ticket-combo">
                                        <div class="book-ticket-combo-left">
                                            MY COMBO(ONLINE)
                                        </div>
                                        <div class="book-ticket-combo-right">
                                            <span class="icon-book"></span>
                                            <ul>
                                                <li class="active-combo-book-online"><span>0</span></li>
                                                <li><span>1</span></li>
                                                <li><span>2</span></li>
                                                <li><span>3</span></li>
                                                <li><span>4</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- end site combo book -->
                                </div>
                            </div>
                        </div>
                        <!-- end site  -->
                        <!-- seatmap -->
                        <div class="lc-main-book-ticket-seatmap">
                            <!-- left seatmap -->
                            <div class="seatmap-left">
                                <div class="seatmap-title">
                                    <span class="screen"></span>
                                </div>
                                <div class="seatmap-row">
                                </div>
                            </div>
                            <!-- end left seatmap  -->
                            <!-- right seatmap  -->
                            <div class="seatmap-right">
                                <div class="seatmap-right-content">
                                    <div class="select-row">
                                        <p>
                                            <span class="seat-select seat-choose">&nbsp;</span>Đang chọn
                                        </p>
                                    </div>
                                    <div class="select-row">
                                        <p>
                                            <span class="seat-select seat-choosed">&nbsp;</span>Đã chọn
                                        </p>
                                    </div>
                                    <div class="seatmap-space"></div>
                                    <div class="select-row">
                                        <p>
                                            <span class="seat-select seat-standard">&nbsp;</span>Thường
                                        </p>
                                    </div>
                                    <div class="select-row">
                                        <p>
                                            <span class="seat-select seat-vip">&nbsp;</span>VIP
                                        </p>
                                    </div>
                                    <div class="select-row">
                                        <p>
                                            <span class="seat-select seat-couple">&nbsp;</span>Ghế đôi
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <!-- end right seatmap -->
                        </div>
                        <!-- end seatmap -->
                        <!-- bottom -->
                        <div class="lc-main-book-ticket-bottom">
                            <div class="lc-main-book-ticket-bottom-center">
                                <div class="lc-main-book-ticket-bottom-prev">
                                    <a href="home?controller=schedule" title="">Previous</a>
                                </div>

                                <div class="lc-main-book-ticket-bottom-main">
                                    <div class="lc-main-book-ticket-bottom-col col-film-first">
                                        <div class="bottom-img">
                                            <img src="<%= schedule.getFilm().getPoster()%>" alt="">
                                        </div>
                                        <div class="bottom-content-film">
                                            <p><%= schedule.getFilm().getName()%></p>
                                            <p><strong>2D</strong></p>
                                        </div>
                                    </div>
                                    <div class="lc-main-book-ticket-bottom-col col-film-secound">
                                        <div class="bottom-content-session">
                                            <p>
                                                <span>Rạp chiếu phim:</span>
                                                <strong>CGV Ho Guom Plaza</strong>
                                            </p>
                                        </div>
                                        <div class="bottom-content-session">
                                            <p>
                                                <span>Ngày:</span>
                                                <strong><%= schedule.getDate()%></strong>
                                            </p>
                                        </div>
                                        <div class="bottom-content-session">
                                            <p>
                                                <span>Phòng chiếu:</span>
                                                <strong><%= schedule.getRoom().getName()%></strong>
                                            </p>
                                        </div>
                                        <div class="bottom-content-session">
                                            <p>
                                                <span>Ghế:</span>
                                            </p>
                                            <strong class="prime_seat"></strong>
                                        </div>
                                    </div>
                                    <div class="lc-main-book-ticket-bottom-col col-film-third">
                                        <div class="bottom-content-price">
                                            <p>
                                                <span>Phim:</span>
                                                <strong class="subtotal"></strong>đ
                                            </p>
                                        </div>
                                        <div class="bottom-content-price">
                                            <p>
                                                <span class="total">Tổng:</span>
                                                <strong class="subtotal"></strong>đ
                                            </p>
                                        </div>

                                    </div>
                                </div>

                                <div class="lc-main-book-ticket-bottom-next">
                                    <a id="save" title="Submit" style="cursor: pointer;">Next</a>
                                </div>
                            </div>

                        </div>
                        <!-- end bottom -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="change_screen">
    Change Screen
</div>
<style type="text/css" media="screen">
    .change_screen {
        position: fixed;
        top: 45%;
        left: 15px;
        padding: 5px 10px;
        border: 1px solid #000;
        cursor: pointer;
    }
</style>
<script src="js/jquery-1.10.2.min.js"></script>
<script>
    $(document).ready(function () {
        var getAllData = [];
        var mode = "defaul";
        var list_seat = {};
        var prime = {};
        var allData = {};
        var row = 0;
        function createDiv(code, className) {
            return $(document.createElement(code)).attr('class', className);
        }

        function setClassText(clasName, text) {
            $("." + clasName + "").text(text);
        }

        function setPrimeSeat() {
            var prime = "";
            var t = $('.selected');
            $.each(t, function (key, value) {
                if (key != 0)
                    prime += ",";
                prime += $(value).text();

            });
            return prime;
        }

        function setSubtotal() {
            var total = 0;
            var t = $('.selected');
            $.each(t, function (key, value) {
                total += 50000;
            });
            return total;
        }


        function setSeat(array, prime) {
            $.each(array, function (key, value) {
                var seat_row = createDiv("div", "seat-row " + key);
                if (mode == 'defaul') {
                    $('.seatmap-row').append(seat_row);
                } else {
                    $('.seatmap-row').prepend(seat_row);
                }
                var t = value.value.split(",");
                $.each(t, function (p, k) {
                    var r = createDiv("div", "box-seat " + value.category).text(k);
                    r.on('click', function (e) {
                        var t = $('.selected').length;
                        if ($(this).hasClass('selected')) {
                            $(e.target).toggleClass("selected");
                            setClassText('prime_seat', setPrimeSeat());
                            setClassText('subtotal', setSubtotal());
                        } else {
                            if ($(this).hasClass('disabled')) {
                                exit();
                            }
                            if (t >= 8) {
                                alert("Please select no more than 8 seats");
                            } else {
                                $(e.target).toggleClass("selected");
                                setClassText('prime_seat', setPrimeSeat());
                                setClassText('subtotal', setSubtotal());
                            }
                        }

                    });
                    $.each(prime.split(","), function (u, v) {
                        if (v == k)
                            r.addClass("disabled");
                    });
                    seat_row.append(r);
                });
            });
        }
        $('.box-seat').on('click', function (e) {
            var t = $('.selected').length;
            if ($(this).hasClass('selected')) {
                $(e.target).toggleClass("selected");
                setClassText('prime_seat', setPrimeSeat());
                setClassText('subtotal', setSubtotal());
            } else {
                if ($(this).hasClass('disabled')) {
                    exit();
                }
                if (t >= 8) {
                    alert("Please select no more than 8 seats");
                } else {
                    $(e.target).toggleClass("selected");
                    setClassText('prime_seat', setPrimeSeat());
                    setClassText('subtotal', setSubtotal());
                }
            }

        });

        $('.change_screen').on('click', function (e) {
            if (mode == 'defaul')
                mode = "rotate180";
            else {
                mode = "defaul";
            }
            $('.seatmap-row').empty();
            setSeat(list_seat, prime);
            setClassText('prime_seat', setPrimeSeat());
        });

        function getListSeat(seatRow) {
            var list_seat = {};
            for (var i = 1; i <= seatRow; i++) {
                var prime = "";
                var category = "";
                $.each($('.seat-row.row' + i).children(), function (key, value) {
                    if ($(value).hasClass('standard'))
                        category = "standard";
                    if ($(value).hasClass('vip'))
                        category = "vip";
                    if ($(value).hasClass('couple'))
                        category = "couple";
                    if (key != 0)
                        prime += ",";
                    prime += $(value).text();
                });
                var row = {
                    'category': category,
                    'value': prime
                }
                list_seat['row' + i] = row;
            }
            return list_seat;
        }

        var request = $.ajax({
            url: "seatRoom",
            method: "GET",
            dataType: "text",
            data: {
                schedule: <%= schedule.getId()%>,
                film: <%= schedule.getFilm().getId()%>,
                date: '<%= schedule.getDate()%>',
                time: '<%= schedule.getTime()%>'
            }
        });

        request.done(function (msg) {
            var data = $.parseJSON(msg);
            var list_seat1 = {};
            var i = 1;
            $.each(data.map.list_seat.myArrayList, function (key, value) {
                var row = {
                    'category': value.map.category.toLowerCase(),
                    'value': value.map.value
                };
                list_seat1['row' + i] = row;
                i++;
            });
            setSeat(list_seat1, data.map.seat_prime);
            setClassText('prime_seat', setPrimeSeat());
            setClassText('subtotal', setSubtotal());
            list_seat = list_seat1;
            prime = data.map.seat_prime;
            row = data.map.seat_row;
            allData = {
                "seat_number": data.map.seat_number,
                "seat_prime": setPrimeSeat(),
                "list_seat": getListSeat(row)
            };
        });

        request.fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });

        $("#save").on('click', function () {
            var request = $.ajax({
                url: "bookTicket",
                method: "GET",
                dataType: "text",
                data: {
                    schedule: <%= schedule.getId()%>,
                    film: <%= schedule.getFilm().getId()%>,
                    date: '<%= schedule.getDate()%>',
                    time: '<%= schedule.getTime()%>',
                    prime: $('.prime_seat').text(),
                    subtotal: setSubtotal()
                }
            });
            request.done(function (msg) {
                var data = $.parseJSON(msg);
                var mapurl =" ";
                window.open(window.location.origin+window.location.pathname+"?controller=order_buy&schedule="+data.map.schedule+"&order="+data.map.order);
                window.open(location.reload( true ));
            });
        });

    });
</script>

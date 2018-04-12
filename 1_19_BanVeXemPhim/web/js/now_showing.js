

$(document).ready(function () {
    var baseUrlImg = 'https://image.tmdb.org/t/p/w185_and_h278_bestv2';
    var baseUrl = 'https://api.themoviedb.org/3/movie/';
    var api_key = '931e54c126cfca6e31b0c905d36d6614';


    function createDiv(code, className) {
        return $(document.createElement(code)).attr('class', className);
    }
    function createDivAtrr(code, attrName, attrValue) {
        return $(document.createElement(code)).attr(attrName, attrValue);
    }
    function createPSpan(p, span) {
        return $(document.createElement('p')).text(p).append("<span>" + span + "</span>");
    }
    function createElementFilm(nameFilm, vote, releasedate, urlImg, urlFilm) {

        var col = createDiv('div', 'col-md-3 col-res');
        var wrapper = createDiv('div', 'content-item-wrapper');
        var content = createDiv('div', 'content-item');
        var btn = $(document.createElement('button')).attr({
            type: 'button',
            class: 'btn-mua',
            title: 'Mua vé',
            href: 'home?controller=schedule'
        });
        var span_btn = $(document.createElement('span')).text("Mua vé");
        var a = createDivAtrr('a', 'href', urlFilm);
        var img = createDivAtrr('img', 'src', baseUrlImg + urlImg);

        var info = createDiv('div', 'info');
        var h4 = $(document.createElement('h4'));
        var ah4 = createDivAtrr('a', 'href', urlFilm).text(nameFilm);
        var p1 = createPSpan("Vote: ", vote);
        var p3 = createPSpan("Khỏi chiếu: ", releasedate);

        h4.append(ah4);
        info.append(h4, p1, p3);
        content.append(a, img, info);
        btn.append(span_btn);
        wrapper.append(content, btn);
        col.append(wrapper);
        return col;
    }
    function createUrl(category, page) {
        return baseUrl + category + "?api_key=" + api_key + "&page=" + page;
    }
    function createpPathUrl(url) {
        for (var i = url.length - 1; i > 0; i--) {
            if (url[i] == '/') {
                return url.substr(0, i + 1);
            }
        }
        return null;
    }
    $(window).load(function () {
        var page = 1;

        fetch(createUrl('popular', page))
                .then((resp) => resp.json())
                .then(function (data) {
                    let all = data.results;
                    $.each(all, function (key, value) {
                        $('.film_list').append(createElementFilm(
                                value.title,
                                value.vote_average,
                                value.release_date,
                                value.poster_path,
                                createpPathUrl(window.location.href) + "home?controller=detail_film&movies=" + value.id
                                ));
                    });
                })
                .catch(function (error) {
                    console.log(error);
                });

        console.log(window.location.search.substr(1).split('&'));
        var t = window.location.href;
        console.log(createpPathUrl(t));
    });

    function paginationFetch(page) {
        fetch(createUrl('popular', page))
                .then((resp) => resp.json())
                .then(function (data) {
                    let all = data.results;
                    $.each(all, function (key, value) {
                        $('.film_list').append(createElementFilm(value.title, value.vote_average, value.release_date, value.poster_path, createpPathUrl(window.location.href) + "cgv.html?movies=" + value.id));
                    });
                    $('#loading-mask').hide();

                })
                .catch(function (error) {
                    console.log(error);
                });
    }
    $('.pagination li a').on('click', function () {
        $('#loading-mask').show();
        $('.film_list').empty();
        paginationFetch($(this).text());
    });
});
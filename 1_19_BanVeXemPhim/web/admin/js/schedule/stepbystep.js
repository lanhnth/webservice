$(document).ready(function () {

    $('.nav-tabs > li a[title]').tooltip();
    

    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.nav-tabs li>a.active');
        $active.parent().next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.nav-tabs li>a.active');
        // $active.parent().prev().removeClass('disabled');
        prevTab($active);

    });
});

function nextTab(elem) {
    $(elem).parent().next().find('a[data-toggle="tab"]').click();
    // $(elem).parent().addClass('disabled');
}
function prevTab(elem) {
    $(elem).parent().prev().find('a[data-toggle="tab"]').click();
    // $(elem).parent().addClass('disabled');
}
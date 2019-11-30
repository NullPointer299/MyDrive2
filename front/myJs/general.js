$(function () {

    $('#resize').funcResizeBox({
        minWidth: 0,
        isWidthResize: true,
        isHeightResize: false,
        mouseRange: 10
    });

    history.pushState(null, null, null);
    $(window).on("popstate", function () {
        history.pushState(null, null, null);
    });

    //drawerに関するJS

    $('#nav-input').change(function () {
        const target = $('#nav-input');
        target.attr('disabled', true);
        setTimeout(function () {
            target.attr('disabled', false);
        }, 300);
        if ($(this).prop('checked')) {
            $('.drawer').addClass('active');
            $('#side-nav').addClass('active');
            $('.side-item').removeClass('nav-unshown');
        } else {
            $('.drawer').removeClass('active');
            $('#side-nav').removeClass('active');
            setTimeout(function () {
                $('.side-item').addClass('nav-unshown');
            }, 280);
        }
    });

    //settings-bar

    //sidebar
    $(document).on('click', '#home,#favorites,#search,#trash', function () {
        $('.' + $('#nav-content .current').attr('id')).attr('hidden', true);
        $('#nav-content .current').removeClass('current');
        $(this).addClass('current');
        const id = $(this).attr('id');
        if (id == 'home') {
            $('.home').attr('hidden', false);
        } else if (id == 'favorites') {
            $('.favorites').attr('hidden', false);
        } else if (id == 'search') {
            $('.search').attr('hidden', false);
        } else if (id == 'trash') {
            $('.trash').attr('hidden', false);
        }
    });
    $(document).on('click', '#nav-content i,.side-item', function () {
        $('.' + $('#nav-content .current').attr('id')).attr('hidden', true);
        $('#nav-content .current').removeClass('current');
        $(this).parent().addClass('current');
        const id = $(this).parent().attr('id');
        if (id == 'home') {
            $('.home').attr('hidden', false);
        } else if (id == 'favorites') {
            $('.favorites').attr('hidden', false);
        } else if (id == 'search') {
            $('.search').attr('hidden', false);
        } else if (id == 'trash') {
            $('.trash').attr('hidden', false);
        }
    });
});
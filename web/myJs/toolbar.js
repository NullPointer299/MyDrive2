$(function () {
    $(document).on('mouseenter', '.parent>li:not(:animated)', function () {
        $('ul.dropdwn_menu').slideUp(150);
        $(this).children("ul.dropdwn_menu").slideDown("fast");
    });

    $(document).on('mouseleave', ".parent>li:not(:animated)", function () {
        $(this).children("ul.dropdwn_menu").slideUp(150);
    });

    $(document).on('mouseleave', "ul.dropdwn_menu:not(:animated)", function () {
        $("ul.dropdwn_menu", $(this).parent()).slideUp(150);
    });
});
$(function () {
    $(document).on('contextmenu', '#files-area .dir', function (e) {
        e.preventDefault();
        e.stopPropagation();
        console.log('clicked');
        $('#context-menu').css('display', 'none');
    });

    $(document).on('contextmenu', '#files-area', function (e) {
        e.preventDefault();
        console.log('in');
        const context_menu = $('#context-menu');
        context_menu.css('display', 'block');
        context_menu.css('left', event.clientX);
        context_menu.css('top', event.clientY);
    });

    $(document).on('click', ':not(.context-item)', function (e) {
        $('#context-menu').css('display', 'none');
        e.stopPropagation();
    });
    $('#button').on('contextmenu', '#button', function (e) {
        e.preventDefault();
        console.log('button');
    });
});
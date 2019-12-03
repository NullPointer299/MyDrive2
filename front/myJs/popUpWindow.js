$(function(){
    $(document).on('click','#new-directory-button',function () {
        $('#cover').attr('hidden',false);
        $('#new-directory').attr('hidden',false);
    });

    $(document).on('click','.close-button, :not(.not-close-action)',function (event) {
        event.stopPropagation();
        $('#cover').attr('hidden',true);
        $('#new-directory').attr('hidden',true);
        $('#new-directory input[type=text]').val("");
    });
});
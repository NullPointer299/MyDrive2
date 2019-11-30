$(function(){
    $(document).on('click','#new-directory-button',function () {
        console.log('IN');
        $('#cover').removeAttr('hidden');
        $('#new-directory').removeAttr('hidden');
    });

    $(document).on('click','.close-button, :not(#new-directory)',function () {
        $('#cover').attr('hidden',true);
        $('#new-directory').attr('hidden',true);
    });
});
$(function () {
    $(document).on('click', '#files-area .dir', function () {
        /*const checkbox = $(this).children('input[type=checkbox]');
        if(checkbox.prop('checked')){
            checkbox.prop('checked',false);
        }else {
            checkbox.prop('checked', true);
        }*/
    });

    $(document).on('dragstart', '#files-area .dir', function (e) {
        const dragImage = document.createElement('img');
        dragImage.src = '../picture/file.png';
        dragImage.style.backgroundColor = 'white';
        e.originalEvent.dataTransfer.setDragImage(dragImage, 0, 16);
        $(this).css('opacity', '0.5');
    });

    $(document).on('dragend', '#files-area .dir', function () {
        $(this).css('opacity', '1');
    });
});
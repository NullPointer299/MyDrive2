$(function () {
    //drag&dropに関するJS

    $(document).on("dragover", '#files-area', function (event) {
        event.preventDefault();
        event.stopPropagation();
    });

    $(document).on("dragleave", '#files-area', function (event) {
        event.preventDefault();
        event.stopPropagation();
    });

    $(document).on('drop', '#files-area', function (event) {
        event.preventDefault();
        event.stopPropagation();
        let files = event.dataTransfer.files;
        for (let file of files) {
            ajax_upload(file);
        }
    });
});

function ajax_upload(file) {
    let form_data = new FormData();
    form_data.append('file', file);
    $.ajax({
        type: 'POST',
        url: '（アップロード処理を行うサーバー側のURL）',
        data: {
            "file":form_data,
            "name":"aaa"
        }
    }).done(function () {
        console.log('success');
    }).fail(function () {
        alert('failed');
    });
}
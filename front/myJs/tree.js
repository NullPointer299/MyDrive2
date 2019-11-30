$(function () {

    $(document).on('click', "#resize a.dir", function () {
        if ($(this).hasClass('open')) {
            let result = $(this).next('ul').find('.open');
            result.each(function (index, element) {
                $(element).removeClass('open');
            });
            $(this).next('ul').removeClass('open');
            $(this).removeClass('open');
        } else {
            if (!opened.has(this)) {
                createTree($(this), treeMap.get(this));
                opened.add(this);
            }
            $(this).next('ul').addClass('open');
            $(this).addClass('open');
        }
    });

    $(document).on('dblclick', "#resize .dir", function () {
        let path = "";
        const parents = $(this).parentsUntil($('#parent'), 'ul').get().reverse();
        for (let o of parents) {
            path += "/" + $(o).prev('a').html();
        }
        path += "/" + $(this).html();
    });

    let tree_comment;
    let dragging;

    $(document).on('dragstart', '#resize a', function () {
        dragging = this;
    });

    $(document).on('dragend', '#resize a', function () {
        dragging = null;
    });

    $(document).on('dragenter', "#resize .dir", function (event) {
        event.preventDefault();
        event.stopPropagation();
        const pos = $(this).offset();
        tree_comment = $('</p>');
        tree_comment.html("Move to " + $(this).html());
        tree_comment.css('top', pos.top + 3);
        tree_comment.css('left', pos.left + $(this).width() + 40);
        tree_comment.attr('id', "tree-comment");
        $('#resize').append(tree_comment);
    });

    $(document).on("dragover", "#resize .dir", function (event) {
        event.preventDefault();
        event.stopPropagation();
    });

    $(document).on('dragleave', "#parent .dir", function (event) {
        event.preventDefault();
        event.stopPropagation();
        $('#tree-comment').remove();
    });

    $(document).on('drop', "#resize .dir", function (event) {
        $('#tree-comment').remove();
        event.preventDefault();
        event.stopPropagation();
        if (dragging != null) {
            if (!$(this).next('ul').is($(dragging).parent().parent())) {
                if (move_file(treeMap.get(dragging), treeMap.get(this)))
                    $(this).next('ul').append($(dragging).parent('li'));
            }
        } else {
            if (event.originalEvent) {
                event = event.originalEvent;
            }
            let files = event.dataTransfer.files;
            for (let file of files) {
                $.getScript("../myJs/upload.js", function () {
                    ajax_upload(file);
                });
            }
        }
    });

    const treeMap = new WeakMap();
    const opened = new WeakSet();
    tree_init();

    function tree_init() {
        const home = document.createElement("a");
        home.classList.add('dir');
        home.innerHTML = "Home";
        $('#parent').append($(home));
        treeMap.set(home, rootId);
        createTree($(home), treeMap.get(home));
        opened.add(home);
        $(home).next('ul').addClass('open');
        $(home).addClass('open');
    }

    function createTree(clicked, parent_id) {
        const elements = origin_tree[parent_id];
        if (elements) {
            const ul = $('<ul></ul>');
            clicked.after(ul);
            for (let i = 0; i < Object.keys(elements).length; i++) {
                let o = elements[i];
                let element = document.createElement("li");
                let a = document.createElement("a");
                if (o.isDirectory == "true") {
                    a.classList.add('dir');
                } else {
                    a.classList.add('file');
                }
                a.innerHTML = decodeURI(o.name);
                a.setAttribute('name', decodeURI(o.id));
                a.setAttribute('draggable', "true");
                element.appendChild(a);
                $(element).appendTo(ul);
                treeMap.set(a, decodeURI(o.id));
            }
        }
    }

    function move_file(src_id, des_id) {
        $.ajax({
            url: "",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "src": src_id,
                "des": des_id
            })
        }).done(function (data) {
            if (data._status)
                return true;
            else
                return false;
        }).fail(function (data) {
            alert('ネットワークエラー');
        })

    }
});
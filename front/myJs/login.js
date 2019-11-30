$(function () {
    $(document).on('focusin', 'input', function () {
        $(this).parent().find('label').addClass('active');/*カーソルが合った時にlabelを動かすclassを追加*/
    });

    $(document).on('focusout', 'input', function () {
        if (!this.value) {
            $(this).parent().find('label').removeClass('active');/*カースルが外れた時にlabelを動かしていたclassを削除*/
        }
    });

    $(document).on('click', 'i', function () {
        const icon = $(this);/*material iconsの文字列を取得*/
        const passEl = icon.parent().parent().parent().find('input');/* inputのelementを取得*/
        if (icon.html() == ("visibility")) {/*現在の値が不可視だった場合 true*/
            icon.text("visibility_off");
            passEl.get(0).type = 'text'; /*input typeをtextに変更*/
        } else {
            icon.text("visibility");
            passEl.get(0).type = 'password';/*input typeをpasswordに変更*/
        }
    });
});

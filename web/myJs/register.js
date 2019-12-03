$(function () {
    let mail;
    let last;
    let first;
    let user_id;
    let password;
    let password_confirm;
    let openness;
    let select;
    let answer;

    $(window).on('popstate', function (e) {
        history.pushState(null, null, null);
        e.preventDefault();
    });

    $(document).on('click', '#Submit', function () {
        const content = $('#content');
        const submit = $('#Submit');
        const description = $('#Description');
        switch ($('#Current').children('p').html()) {//　現在フォーカスしているprogress barを取得
            case "Email"://フォーカス中がEmailの場合
                mail = $('#Mail').val();
                last = $('#Last').val();
                first = $('#First').val();
                const json_data = {"emailAddress": mail, "lastName": last, "firstName": first,"token":token};
                if (mail.match('\\w+@\\w+') != null && last && first) { //フォームの中身が空では無かった且つメールアドレスの正しい構成で合った場合
                    const loader = $('.loader-wrap');
                    loader.css('display', 'flex');
                    $.ajax({
                        url: "/MyDrive2/AuthMail/",//ajaxの送信先
                        type: "post",
                        dataType: "json",
                        data: JSON.stringify(json_data)
                    }).done(function (data) {
                        if (data._valid) { //メアドが使えるものかを判定
                            token = decodeURI(data.token)
                            forward_progress_form();
                            const email = $('#Email');
                            const next = email.next();
                            move_step(
                                function () {
                                    email.attr('hidden', true);
                                    next.removeAttr('hidden');
                                    content.fadeIn(800);
                                }, function () {
                                    submit.val('Certificate');
                                    submit.attr('disabled', true);
                                    submit.fadeIn(800);
                                },
                                function () {
                                    description.html('<a href="">Resend Secret Code</a>');
                                    description.fadeIn(800);
                                });
                        } else {
                            show_feedback();
                        }
                        loader.css('display', 'none');
                    }).fail(function (data) {
                        console.log(data);
                        console.log($.parseJSON(data));
                        loader.css('display', 'none');
                        alert("通信に失敗しました。");
                    });
                }
                break;

            case "Certification" :
                let codes = "";
                $('.code').each(function (i, val) {
                    codes += $(val).val();
                });
                if (codes.match('\\d{4}')) {
                    const loader = $('.loader-wrap');
                    loader.css('display', 'flex');
                    $.ajax({
                        url: "/MyDrive2/AuthCode/",//ajaxの送信先
                        type: "post",
                        dataType: "json",
                        data: JSON.stringify({
                            "code": codes,
                            "token":token
                        })
                    }).done(function (data) {
                        const certification = $('#Certification');
                        const next = certification.next();
                        token = decodeURI(data.token);
                        if (data._valid) {
                            forward_progress_form();
                            move_step(
                                function () {
                                    certification.attr('hidden', 'true');
                                    next.removeAttr('hidden');
                                    content.fadeIn(800);
                                }, function () {
                                    submit.val('Apply');
                                    submit.attr('disabled', true);
                                    submit.fadeIn(800);
                                }, function () {
                                    description.html('You can change these option later');
                                    description.fadeIn(800);
                                });
                        } else {
                            alert("一致しません");
                        }
                        loader.css('display', 'none');
                    }).fail(function () {
                        loader.css('display', 'none');
                        alert("通信に失敗しました。");
                    });
                }
                break;
            case "Settings" :
                user_id = $('#Id').val();
                password = $('#Password').val();
                password_confirm = $('#Pass-confirm').val();
                openness = $('#openness').prop('checked');
                if (user_id && password_confirm && password) {
                    $.ajax({
                        url: "/MyDrive2/GetQuestions/",
                        type: "post",
                        dataType: "json",
                        data:JSON.stringify({
                            "token":token
                        })
                    }).done(function (data) {
                        const secret_question = data.questions;
                        forward_progress_form();
                        token = decodeURI(data.token);
                        const settings = $('#Settings');
                        const next = settings.next();
                        move_step(
                            function () {
                                const select_menu = $('#select-menu');
                                for (let i = 1; i <= secret_question.length; i++) {
                                    select_menu.append($('<option></option>').val(i).text(decodeURI(secret_question[i - 1])));
                                }
                                settings.attr('hidden', 'true');
                                next.removeAttr('hidden');
                                content.fadeIn(800);
                            }, function () {
                                submit.val('Submit');
                                submit.attr('disabled', true);
                                submit.fadeIn(800);
                            }, function () {
                                description.html('');
                                description.fadeIn(800);
                            });
                    });
                }
                break;
            case "Secret<br>Question" :
                select = $('#select-menu').val();
                answer = $('#answer').val();
                forward_progress_form();
                const secret_question = $('#Secret-question');
                const next = secret_question.next();
                move_step(
                    function () {
                        secret_question.attr('hidden', 'true');
                        next.removeAttr('hidden');
                        $('#confirm-id').val(user_id);
                        $('#confirm-pass').val(password);
                        $('#confirm-openness').prop('checked', openness);
                        $('#confirm-question').val($('#select-menu option[value=' + select + ']').text());
                        $('#confirm-answer').val(answer);
                        content.fadeIn(800);
                        setTimeout(function () {
                            next.addClass('loaded');
                        }, 50);
                    }, function () {
                        submit.val('Create');
                        submit.fadeIn(800);
                    }, function () {
                        description.html('');
                        description.fadeIn(800);
                    });
                break;
            case "Confirm" :
                $('<form/>', {action: "/MyDrive2/Register/", method: 'post'}) //action先書いてね
                    .append($('<input/>', {type: 'hidden', name: 'email-address', value: mail}))
                    .append($('<input/>', {type: 'hidden', name: 'last-name', value: last}))
                    .append($('<input/>', {type: 'hidden', name: 'first-name', value: first}))
                    .append($('<input/>', {type: 'hidden', name: 'user-id', value: user_id}))
                    .append($('<input/>', {type: 'hidden', name: 'password', value: password}))
                    .append($('<input/>', {type: 'hidden', name: 'question', value: select}))
                    .append($('<input/>', {type: 'hidden', name: 'answer', value: answer}))
                    .append($('<input/>', {type: 'hidden', name: 'openness', value: openness}))
                    .append($('<input/>', {type: 'hidden', name: 'token', value: token}))
                    .appendTo(document.body)
                    .submit();
        }
    });

    /*Email*/
    $('#Mail').on('keyup', function () {
        const submit = $('#Submit');
        if ($(this).val() && $('#Last').val() && $('#First').val()) {
            submit.attr('disabled', false);
        } else {
            submit.attr('disabled', true);
        }
    });

    $('#Last').on('keyup', function () {
        const submit = $('#Submit');
        if ($(this).val() && $('#Mail').val() && $('#First').val()) {
            submit.attr('disabled', false);
        } else {
            submit.attr('disabled', true);
        }
    });
    $('#First').on('keyup', function () {
        const submit = $('#Submit');
        if ($(this).val() && $('#Last').val() && $('#Mail').val()) {
            submit.attr('disabled', false);
        } else {
            submit.attr('disabled', true);
        }
    });
    /*Certification*/

    $('.code').on('keyup', function (e) { //Certificationにて入力されたコードが半角数字だった場合、次の入力欄にフォーカスを移動
        const target = $(this);
        const keycode = e.which;
        let flag = false;
        if (target.val().match(/[0-9]/) && (keycode >= 48 && keycode <= 57)) {
            target.next().focus();
            target.removeClass('invalid');
        } else {
            if (target.val().match(/[^0-9]/)) {
                target.addClass('invalid');
            }
        }
        $('.code').each(function (i, o) {
            if (!$(o).val().match(/[0-9]/)) {
                flag = true;
            }
        })
        if (!flag) {
            $('#Submit').attr('disabled', false);
        } else {
            $('#Submit').attr('disabled', true);
        }
    });


    /*Settings*/
    let timer = false;
    let isMatch = false;
    let isValid = false;
    $('#Id').on('keyup', function () {
        if ($('#Id').val()) {
            const status = $('#id-status');
            status.removeClass('loading valid invalid');
            status.addClass('loading');
            $('#Submit').attr('disabled', true);
            if ($('#Password').val() != $('#Pass-confirm').val()) {
                $('#Pass-confirm').addClass('invalid');
                isMatch = true;
            } else {
                $('#Pass-confirm').removeClass('invalid');
            }
            if (timer != false) clearTimeout(timer);
            timer = setTimeout(function () {
                $.ajax({
                    url: "/MyDrive2/CheckID/",
                    type: "post",
                    dataType: "json",
                    data: JSON.stringify({
                        "userId": $('#Id').val()
                    })
                }).done(function (data) {
                    status.removeClass('loading');
                    if (data._valid) { //IDが使えるものかを判定
                        isValid = true;
                        status.addClass('valid');
                        if ($('#Id').parent().hasClass('invalid')) {
                            $('#Id').parent().removeClass('invalid');
                        }
                        if (isMatch) {
                            $('#Submit').attr('disabled', false);
                        }
                    } else {
                        if (!$('#Id').parent().hasClass('invalid')) {
                            $('#Id').parent().addClass('invalid');
                            $('#Submit').attr('disabled', true);
                        }
                        if (!status.hasClass('invalid')) {
                            status.addClass('invalid');
                        }
                        isValid = false;
                    }
                }).fail(function () {
                    alert("通信に失敗しました。");
                });
                timer = false;
            }, 1000);
        } else {
            $(this).parent('.form-container').removeClass('invalid');
            clearTimeout(timer);
            $('#id-status').removeClass('loading valid invalid');

        }
    });

    $('#Password').on('keyup', function () {
        if ($(this).val() != $('#Pass-confirm').val()) {
            $('#Pass-confirm').parent().addClass('invalid');
            $('#Submit').attr('disabled', true);
            isMatch = false;
        } else {
            isMatch = true;
            $('#Pass-confirm').parent().removeClass('invalid');
            const submit = $('#Submit');
            if ($(this).val() && $('#Id').val() && $('#Password').val() && isValid) {
                submit.attr('disabled', false);
            } else {
                submit.attr('disabled', true);
            }
        }
    });

    $('#Pass-confirm').on('keyup', function () {
        if ($(this).val() != $('#Password').val()) {
            $('#Pass-confirm').parent().addClass('invalid');
            $('#Submit').attr('disabled', true);
            isMatch = false;
        } else {
            isMatch = true;
            $('#Pass-confirm').parent().removeClass('invalid');
            const submit = $('#Submit');
            if ($(this).val() && $('#Id').val() && $('#Password').val() && isValid) {
                submit.attr('disabled', false);
            } else {
                submit.attr('disabled', true);
            }
        }
    });

    $('#Circle').on('click', function () {
        const openness = $('#openness');
        if (openness.prop('checked')) {
            openness.prop('checked', false);
        } else {
            openness.prop('checked', true);
        }
    });

    /*Secret Question*/
    $('#select-menu').on('change', function () {
        const submit = $('#Submit');
        if ($(this).val() != 0 && $('#answer').val()) {
            submit.attr('disabled', false);
        } else {
            submit.attr('disabled', true);
        }
    });

    $('#answer').on('keyup', function () {
        const submit = $('#Submit');
        if ($('#select-menu').val() != null && $(this).val()) {
            submit.attr('disabled', false);
        } else {
            submit.attr('disabled', true);
        }
    });

    /*Confirm*/
});

function show_feedback() { //Email addressが無効だった場合　メールフォームにアニメーション追加
    let mail_form = $('#Mail');
    mail_form.addClass('invalid');
}

function forward_progress_form() { //progress-barを進める
    let current = $('#Current');
    let next = current.next();
    current.removeAttr('id');
    current.addClass('visited');
    next.attr('id', 'Current');
}

function move_step(func_content, func_submit, func_description) { //遷移する際の画面の視覚的動作
    $('#content').fadeOut(800, func_content);
    $('#Submit').fadeOut(800, func_submit);
    $('#Description').fadeOut(800, func_description);
}

$(function () {
    $('#Pass').on('keyup', function () {
        const pass = $('#Pass');
        const passCon = $('#Pass-confirm');
        const passText = pass.val();
        const passConText = passCon.val();
        const button = $('#submit');
        if (passText == '' || passConText == '') {
            if (passCon.hasClass('is-valid')) {
                passCon.removeClass('is-valid');
            }
            if (!passCon.hasClass('is-invalid')) {
                passCon.addClass("is-invalid");
                button.attr('disabled', true);
                button.addClass('unavailable');
            }
        } else {
            if (passText == passConText) {
                if (passCon.hasClass('is-invalid')) {
                    passCon.removeClass('is-invalid');
                }
                if (!passCon.hasClass('is-valid')) {
                    passCon.addClass("is-valid");
                    button.attr('disabled', false);
                    button.removeClass('unavailable');
                }
            } else {
                if (passCon.hasClass('is-valid')) {
                    passCon.removeClass('is-valid');
                }
                if (!passCon.hasClass('is-invalid')) {
                    passCon.addClass("is-invalid");
                    button.attr('disabled', true);
                    button.addClass('unavailable');
                }
            }
        }
    });

    $('#Pass-confirm').on('keyup', function () {
        const pass = $('#Pass');
        const passCon = $('#Pass-confirm');
        const passText = pass.val();
        const passConText = passCon.val();
        const button = $('#submit');
        if (passText == '' || passConText == '') {
            if (passCon.hasClass('is-valid')) {
                passCon.removeClass('is-valid');
            }
            if (!passCon.hasClass('is-invalid')) {
                passCon.addClass("is-invalid");
                button.attr('disabled', true);
                button.addClass('unavailable');
            }
        } else {
            if (passText == passConText) {
                if (passCon.hasClass('is-invalid')) {
                    passCon.removeClass('is-invalid');
                }
                if (!passCon.hasClass('is-valid')) {
                    passCon.addClass("is-valid");
                    button.attr('disabled', false);
                    button.removeClass('unavailable');
                }
            } else {
                if (passCon.hasClass('is-valid')) {
                    passCon.removeClass('is-valid');
                }
                if (!passCon.hasClass('is-invalid')) {
                    passCon.addClass("is-invalid");
                    button.attr('disabled', true);
                    button.addClass('unavailable');
                }
            }
        }
    });


});

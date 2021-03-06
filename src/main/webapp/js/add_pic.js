$(document).ready(function() {

    $("#profileForm").validate({
        rules: {
            websiteLink: {
                url: true
            },
            aboutText: {
                maxlength: 1000
            }
        }
    });

    var uploadBtn = $('#uploadAvatar');
    var removeBtn = $('#removeAvatar');
    var pb = $('#avatarUploadProgress').find('.progress-bar');
    var avatarImg = $('#avatarImg');
    var avatarErrorLabel = $('#avatarError');
    var avatarSuccessLabel = $('#avatarSuccess');

    $('#avatarFileUploadInput').fileupload({
        url: window.avatarUploadUrl,
        dataType: "json",
        send: function (e, data) {
            pb.css('width', '0');
            pb.switchClass('progress-bar-danger', 'progress-bar-success', 0);
            pb.parent().show();

            avatarErrorLabel.hide();
            avatarSuccessLabel.hide();

            uploadBtn.addClass('disabled');
            removeBtn.addClass('disabled');
        },
        done: function (e, data) {
            if (data.result.status == 'ok') {
                avatarImg.attr('src', window.imgBaseUrl + data.result.link);

                removeBtn.show();

                avatarSuccessLabel.show();
            }
            else {
                pb.switchClass('progress-bar-success', 'progress-bar-danger');

                var errMsg = 'Yükleme başarısız, ' + data.result.status;

                if (data.result.status == 'invalid_format') {
                    errMsg = "Sadece JPG ve PNG'ye izin veriliyor.";
                }

                avatarErrorLabel.text(errMsg);
                avatarErrorLabel.show();
            }
        },
        fail: function (e, data) {
            pb.switchClass('progress-bar-success', 'progress-bar-danger');

            avatarErrorLabel.text("Resim yükleme başarısız. Lütfrn resmin PNG veya JPG olduğundan ve and 1 MB'ı geçmediğinden emin olunuz");
            avatarErrorLabel.show();
        },
        always: function (e, data) {
            uploadBtn.removeClass('disabled');
            removeBtn.removeClass('disabled');
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            pb.css('width', progress + '%');
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');

});

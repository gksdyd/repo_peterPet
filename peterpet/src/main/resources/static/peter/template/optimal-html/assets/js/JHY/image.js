var num = 1;
var count = 1;
$(document).on("change", ".vd-image-upload", function() {
    var input = this;
    if(input.files.length == 1){
        var reader = new FileReader();
        let ext = $(input).val().split('.').pop().toLowerCase();

        if ($.inArray(ext, ['jpg','jpeg','png', 'mp4']) == -1) {
            return;
        }

        reader.onload = function(e){
            if (ext == "mp4") {
                $(input).parent().prev().prev().addClass("d-none");
                $(input).parent().prev().removeClass("d-none");
                $(input).parent().prev().attr("src", e.target.result);
            } else if ($.inArray(ext, ['jpg','jpeg','png']) != -1) {
                $(input).parent().prev().prev().attr("src", e.target.result);
            }

            if ($(input).attr("id").slice(-1) == num) {
                $(input).prev().prev().removeClass("d-none");
                if (count < 5) {
                    num++;
                    count++;
                    let text = "<div class='col-2 col-sm-2 col-md-2 col-lg-2 thumb-upload'>" +
                                    "<div class='thumb-preview position-relative border p-1'>" +
                                        "<img class='thumb-preview' src='/peter/template/optimal-html/assets/images/vendor/vender-upload-preview.jpg' alt='edit'>" +
                                        "<video class='thumb-preview d-none' src=''></video>" +
                                        "<div class='thumb-edit'>" +
                                            "<label class='removeImage d-flex-center justify-content-center position-absolute top-0 start-0 p-2 border-0 rounded-circle shadow btn d-none'><i class='icon an an-times-r an-1x'></i></label>" +
                                            "<label for='thumbUpload" + num + "' class='d-flex-center justify-content-center position-absolute top-0 end-0 p-2 border-0 rounded-circle shadow btn'><i class='icon an an-pencil-l an-1x'></i></label>" +
                                            "<input type='file' id='thumbUpload" + num + "' class='vd-image-upload d-none' accept='.png, .jpg, .jpeg, .mp4'>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>";
                    $(".thumb-upload-set").append(text);
                }
            }
        }
    }
    reader.readAsDataURL(input.files[0]);
});

$(document).on("click", ".removeImage", function() {
    $(this).parent().parent().parent().remove();
    count--;
});
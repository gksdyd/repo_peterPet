/* ===============================================-->
                      이미지 JS
   ===============================================--> */

function removeImage(e) {
  $(e).parent().remove();
}

let myDropzone = null;
window.Dropzone ? window.Dropzone.autoDiscover = false : '';
var dropzoneInit = function dropzoneInit() {
  var merge = window._.merge;
  var Selector = {
    DROPZONE: '[data-dropzone]',
    DZ_ERROR_MESSAGE: '.dz-error-message',
    DZ_PREVIEW: '.dz-preview',
    DZ_PROGRESS: '.dz-preview .dz-preview-cover .dz-progress',
    DZ_PREVIEW_COVER: '.dz-preview .dz-preview-cover'
  };
  var ClassName = {
    DZ_FILE_PROCESSING: 'dz-file-processing',
    DZ_FILE_COMPLETE: 'dz-file-complete',
    DZ_COMPLETE: 'dz-complete',
    DZ_PROCESSING: 'dz-processing'
  };
  var DATA_KEY = {
    OPTIONS: 'options'
  };
  var Events = {
    ADDED_FILE: 'addedfile',
    REMOVED_FILE: 'removedfile',
    COMPLETE: 'complete'
  };
  var dropzones = document.querySelectorAll(Selector.DROPZONE);
  !!dropzones.length && dropzones.forEach(function (item) {
    var userOptions = utils.getData(item, DATA_KEY.OPTIONS);
    userOptions = userOptions || {};
    var data = userOptions.data ? userOptions.data : {};
    var options = merge({
      url: '/xdm/product/ProductXdmUpdt',
      paramName: 'uploadImg1',
      addRemoveLinks: false,
      previewsContainer: item.querySelector(Selector.DZ_PREVIEW),
      previewTemplate: item.querySelector(Selector.DZ_PREVIEW).innerHTML,
      thumbnailWidth: null,
      thumbnailHeight: null,
      maxFilesize: 20,
      autoProcessQueue: false,
      uploadMultiple: true,
      filesizeBase: 1000,
      init: function init() {
        var thisDropzone = this;
        if (data.length) {
          data.forEach(function (v) {
            var mockFile = {
              name: v.name,
              size: v.size
            };
            thisDropzone.options.addedfile.call(thisDropzone, mockFile);
            thisDropzone.options.thumbnail.call(thisDropzone, mockFile, "".concat(v.url, "/").concat(v.name));
          });
        }
        thisDropzone.on(Events.ADDED_FILE, function addedfile() {
          if ('maxFiles' in userOptions) {
            if (userOptions.maxFiles === 1 && item.querySelectorAll(Selector.DZ_PREVIEW_COVER).length > 1) {
              item.querySelector(Selector.DZ_PREVIEW_COVER).remove();
            }
            if (userOptions.maxFiles === 1 && this.files.length > 1) {
              this.removeFile(this.files[0]);
            }
          }
        });
        document.getElementById("submitBtn").addEventListener("click", function (e) {
          e.preventDefault();
        
          const form = document.getElementById("product-form"); // form ID 맞게 수정
          validationInit();
          if (!validation()) return;
        
          let imageSeq = document.querySelectorAll(".imageSeq");
          let imageArray = []
          imageSeq.forEach(element => {
            imageArray.push(element.getAttribute("data-seq"));
          });
          $("#removeSeq").val(imageArray);

          // 파일이 있으면 Dropzone 전송
          if (myDropzone && myDropzone.getAcceptedFiles().length > 0) {
            myDropzone.on("sendingmultiple", function (files, xhr, formDataDZ) {
              const formData = new FormData(form);
              for (const [key, value] of formData.entries()) {
                formDataDZ.append(key, value);
              }
            });
        
            myDropzone.processQueue();
          } else {
            // 파일이 없으면 기존 form submit
            if (document.getElementById("registerOrModifyFlag").value == "1") {
              form.action = goUrlXdmInst;
            } else {
              form.action = goUrlXdmUpdt;
            }
        
            form.method = "post";
            form.submit();
          }
        });

        thisDropzone.on("successmultiple", function (files, response) {
          // console.log("업로드 완료", response);
          // alert("저장 완료!");
          window.location.href = "/xdm/product/ProductXdmList"; // 원하는 경로로 이동
        });
      },
      error: function error(file, message) {
        if (file.previewElement) {
          file.previewElement.classList.add('dz-error');
          if (typeof message !== 'string' && message.error) {
            message = message.error;
          }
          var errorNodes = Array.from(file.previewElement.querySelectorAll('[data-dz-errormessage]'));
          errorNodes.forEach(function (node) {
            node.textContent = message;
          });
        }
      }
    }, userOptions);
    item.querySelector(Selector.DZ_PREVIEW).innerHTML = '';
    var dropzone = new window.Dropzone(item, options);
    if (!myDropzone)  {
      myDropzone = dropzone;
    }
    dropzone.on(Events.ADDED_FILE, function () {
      if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
        item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_FILE_COMPLETE);
      }
      item.classList.add(ClassName.DZ_FILE_PROCESSING);
    });
    dropzone.on(Events.REMOVED_FILE, function () {
      if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
        item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_PROCESSING);
      }
      item.classList.add(ClassName.DZ_FILE_COMPLETE);
    });
    dropzone.on(Events.COMPLETE, function () {
      if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
        item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_PROCESSING);
      }
      item.classList.add(ClassName.DZ_FILE_COMPLETE);
    });
  });
};

dropzoneInit();
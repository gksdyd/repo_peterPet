/* ===============================================-->
                      이미지 JS
   ===============================================--> */

function removeImage(e) {
  $(e).parent().remove();
}

let myDropzones = [];

window.Dropzone ? window.Dropzone.autoDiscover = false : '';

var dropzoneInit = function () {
  const merge = window._.merge;
  const Selector = {
    DROPZONE: '[data-dropzone]',
    DZ_ERROR_MESSAGE: '.dz-error-message',
    DZ_PREVIEW: '.dz-preview',
    DZ_PROGRESS: '.dz-preview .dz-preview-cover .dz-progress',
    DZ_PREVIEW_COVER: '.dz-preview .dz-preview-cover'
  };
  const ClassName = {
    DZ_FILE_PROCESSING: 'dz-file-processing',
    DZ_FILE_COMPLETE: 'dz-file-complete',
    DZ_COMPLETE: 'dz-complete',
    DZ_PROCESSING: 'dz-processing'
  };
  const DATA_KEY = { OPTIONS: 'options' };
  const Events = {
    ADDED_FILE: 'addedfile',
    REMOVED_FILE: 'removedfile',
    COMPLETE: 'complete'
  };

  const dropzones = document.querySelectorAll(Selector.DROPZONE);

  if (dropzones.length) {
    dropzones.forEach((item) => {
      const userOptions = utils.getData(item, DATA_KEY.OPTIONS) || {};
      const data = userOptions.data || {};
      const previewsContainer = item.querySelector(Selector.DZ_PREVIEW);
      const previewTemplateHTML = previewsContainer.innerHTML;

      // Dropzone 생성 전에 템플릿 비워주기
      previewsContainer.innerHTML = '';

      const options = merge({
        url: '/xdm/product/ProductXdmImageInst',
        paramName: 'uploadImg1',
        addRemoveLinks: false,
        previewsContainer: previewsContainer,
        previewTemplate: previewTemplateHTML,
        thumbnailWidth: null,
        thumbnailHeight: null,
        maxFilesize: 20,
        autoProcessQueue: false,
        uploadMultiple: true,
        filesizeBase: 1000,
        init: function () {
          const dz = this;

          // 기존 이미지 mock으로 등록
          if (data.length) {
            data.forEach((v) => {
              const mockFile = { name: v.name, size: v.size };
              dz.emit("addedfile", mockFile);
              dz.emit("thumbnail", mockFile, `${v.url}/${v.name}`);
              dz.emit("complete", mockFile);
            });
          }

          // 단일 파일 제한 처리
          dz.on(Events.ADDED_FILE, function () {
            if (userOptions.maxFiles === 1) {
              // Dropzone이 1개만 허용될 때 기존 preview 삭제
              if (previewsContainer.querySelectorAll(Selector.DZ_PREVIEW_COVER).length > 1) {
                previewsContainer.querySelector(Selector.DZ_PREVIEW_COVER).remove();
              }
              if (dz.files.length > 1) {
                dz.removeFile(dz.files[0]);
              }
            }
          });
        },
        error: function (file, message) {
          if (file.previewElement) {
            file.previewElement.classList.add('dz-error');
            if (typeof message !== 'string' && message.error) {
              message = message.error;
            }
            const errorNodes = file.previewElement.querySelectorAll('[data-dz-errormessage]');
            errorNodes.forEach((node) => {
              node.textContent = message;
            });
          }
        }
      }, userOptions);

      const dropzone = new Dropzone(item, options);

      // 썸네일 애니메이션용 class 제어
      dropzone.on(Events.ADDED_FILE, () => {
        if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
          item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_FILE_COMPLETE);
        }
        item.classList.add(ClassName.DZ_FILE_PROCESSING);
      });

      dropzone.on(Events.REMOVED_FILE, () => {
        if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
          item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_PROCESSING);
        }
        item.classList.add(ClassName.DZ_FILE_COMPLETE);
      });

      dropzone.on(Events.COMPLETE, () => {
        if (item.querySelector(Selector.DZ_PREVIEW_COVER)) {
          item.querySelector(Selector.DZ_PREVIEW_COVER).classList.remove(ClassName.DZ_PROCESSING);
        }
        item.classList.add(ClassName.DZ_FILE_COMPLETE);
      });

      // 업로드 완료 후 체크
      dropzone.on("successmultiple", function () {
        dropzone._uploaded = true;
        if (myDropzones.every(dz => dz.getAcceptedFiles().length === 0 || dz._uploaded)) {
          const form = document.getElementById("product-form");
          form.action = document.getElementById("registerOrModifyFlag").value == "1" ? goUrlXdmInst : goUrlXdmUpdt;
          form.method = "post";
          form.submit();
        }
      });

      myDropzones.push(dropzone);
    });
  }

  // Submit 버튼 이벤트
  document.getElementById("submitBtn").addEventListener("click", function (e) {
    e.preventDefault();

    const pForm = document.getElementById("product-form");
    const dForm = document.getElementById("dropzone-form");

    validationInit();
    if (!validation()) return;

    const dropzonesToUpload = myDropzones.filter(dz => dz.getAcceptedFiles().length > 0);
    myDropzones.forEach(dz => dz._uploaded = false);

    if (dropzonesToUpload.length > 0) {
      dropzonesToUpload.forEach((dz) => {
        const type = dz.element.getAttribute("data-upload-type");
        document.getElementById("uploadImg1Type").value = type;

        dz.on("sendingmultiple", function (files, xhr, formDataDZ) {
          const formData = new FormData(dForm);
          for (const [key, value] of formData.entries()) {
            formDataDZ.append(key, value);
          }
        });

        dz.processQueue();
      });
    } else {
      pForm.action = document.getElementById("registerOrModifyFlag").value == "1" ? goUrlXdmInst : goUrlXdmUpdt;
      pForm.method = "post";
      pForm.submit();
    }
  });
};


dropzoneInit();
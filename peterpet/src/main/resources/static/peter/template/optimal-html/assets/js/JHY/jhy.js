// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
        }

        form.classList.add('was-validated')
        }, false)
    })
    })()

    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });

    $(function(){
        $("#file-area").hide();  
        $("#presentimg").click(function(){
            $("#presentimg1").click();	
        })
    })

    function loadImg(inputFile,num){

        if(inputFile.files.length == 1){
        
            var reader = new FileReader();
        
            reader.readAsDataURL(inputFile.files[0]);
            reader.onload = function(e){
                $("#presentimg").attr("src",e.target.result);
            }
        
            } else {
                $("#presentimg").attr("src",null);
            }
        
    }

    // 페이지 진입 시 기본 값
window.addEventListener('DOMContentLoaded', function() {
    showContent('list');
  });
  
  function showContent(content) {
    var contentA = document.getElementById("pet-list");
    var contentB = document.getElementById("pet-register");
    var contentC = document.getElementById("pet-detail");
  
    // 내용 숨김
    contentA.style.display = "none";
    contentB.style.display = "none";
    contentC.style.display = "none";
  
    // 선택한 내용 보이기
    if (content === "list") {
      contentA.style.display = "block";
    } else if (content === "register") {
      contentB.style.display = "block";
    } else if (content === "detail") {
        contentC.style.display = "block";
      }
  }

  function showModal(modal) {  
    // 내용 숨김
    $('#select-destination').modal('hide');
    $('#modify-destination').modal('hide');
  
    // 선택한 내용 보이기
    if (modal === "select") {
        $('#select-destination').modal('show');
    } else if (modal === "modify") {
        $('#modify-destination').modal('show');
    }
  }

  function paymentPage() {
    if (document.getElementById("account-transfer").checked) {
        $('#account-check').modal('show');
    } else if (document.getElementById("credit-check-card").checked) {
        $('#card-register').modal('show');
    } else if (document.getElementById("bank-transfer").checked) {
        location.href='http://127.0.0.1:5500/Workspace/optimal-html/checkout-success.html';
    }
  }
  
  $(function () {
      var $pswp = $('.pswp')[0],
              image = [],
              getItems = function () {
                  var items = [];
                  $('.lightboximages a').each(function () {
                      var $href = $(this).attr('href'),
                              $size = $(this).data('size').split('x'),
                              item = {
                                  src: $href,
                                  w: $size[0],
                                  h: $size[1]
                              };
                      items.push(item);
                  });
                  return items;
              };
      var items = getItems();

      $.each(items, function (index, value) {
          image[index] = new Image();
          image[index].src = value['src'];
      });
      $('.prlightbox').on('click', function (event) {
          event.preventDefault();

          var $index = $(".active-thumb").parent().attr('data-slick-index');
          $index++;
          $index = $index - 1;

          var options = {
              index: $index,
              bgOpacity: 0.7,
              showHideOpacity: true
          };
          var lightBox = new PhotoSwipe($pswp, PhotoSwipeUI_Default, items, options);
          lightBox.init();
      });
  });
  $(function () {
      var $pswp = $('.pswp')[0],
              image = [],
              getItems = function () {
                  var items = [];
                  $('.reviewimages a').each(function () {
                      var $href = $(this).attr('href'),
                              $size = $(this).data('size').split('x'),
                              item = {
                                  src: $href,
                                  w: $size[0],
                                  h: $size[1]
                              };
                      items.push(item);
                  });
                  return items;
              };
      var items = getItems();

      $.each(items, function (index, value) {
          image[index] = new Image();
          image[index].src = value['src'];
      });
      $('.reviewbox').on('click', function (event) {
          event.preventDefault();

          var $index = $(".active-thumb").parent().attr('data-slick-index');
          $index++;
          $index = $index - 1;

          var options = {
              index: $index,
              bgOpacity: 0.7,
              showHideOpacity: true
          };
          var lightBox = new PhotoSwipe($pswp, PhotoSwipeUI_Default, items, options);
          lightBox.init();
      });
  });
  
  $(function () {
      var $pswp = $('.pswp')[0],
              image = [],
              getItems = function () {
                  var items = [];
                  $('a.zoom').each(function () {
                      var $href = $(this).attr('href'),
                              $size = $(this).data('size').split('x'),
                              item = {src: $href, w: $size[0], h: $size[1]};
                      items.push(item);
                  });
                  return items;
              };
      var items = getItems();
      $('.zoom').click(function (event) {
          event.preventDefault();
          var $index = $(this).parents(".grid-lookbook").index();
          $index = $index;
          var options = {
              index: $index,
              bgOpacity: .6,
              showHideOpacity: true
          };
          var lightBox = new PhotoSwipe($pswp, PhotoSwipeUI_Default, items, options);
          lightBox.init();
      });
  });
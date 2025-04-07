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
    })

    function daumPostcode() {
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
                document.getElementById('userPostCode').value = data.zonecode;
                document.getElementById("userAddr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("userDetailAddr").focus();
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

var contentA = document.getElementById("pet-list");
var contentB = document.getElementById("pet-register");
var contentC = document.getElementById("pet-detail");
    // 페이지 진입 시 기본 값
window.addEventListener('DOMContentLoaded', function() {
    contentA.style.display = "block";
    contentB.style.display = "none";
    contentC.style.display = "none";
});
  
$('.detail').click(function(){
    // 내용 숨김
    contentA.style.display = "none";
    contentB.style.display = "none";
    contentC.style.display = "block";
    
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: goUrlPeterProc
        ,data: { "petSeq" : $(this).val() }
        ,success: function(response) {
            $("#userPetSeq").val(response.dto.petSeq);
            $("#userPetName").text(response.dto.petName);
            $("#userPetVarieties").text(response.variety);
            $("#userPetGender").text(response.gender);
            $("#userPetBirth").text(response.dto.petBirth);
            $("#userPetWeight").text(response.dto.petWeight + "kg");

            if (response.dto.petVaccinationFlag == 1) {
                $("#userPetVaccin").text("예");
            } else {
                $("#userPetVaccin").text("아니오");
            }

            if (response.dto.petNeuteringFlag == 1) {
                $("#userPetNeuter").text("예");
            } else {
                $("#userPetNeuter").text("아니오");
            }
            
            let text = "";
            for(let i = 0; i < response.personal.length; i++) {
                text += response.personal[i] + '<br>';
            }
            $("#userPetPersonal").html(text);

            text = "";
            for(let i = 0; i < response.disease.length; i++) {
                text += response.disease[i] + '<br>';
            }
            $("#userPetDisease").html(text);
        }
        ,error : function(jqXHR) {
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    })
});

$('.register').click(function(){
    // 내용 숨김
    contentA.style.display = "none";
    contentC.style.display = "none";
    contentB.style.display = "block";
    
    if ($(this).attr("id") == "petModifyBtn") {
        $.ajax({
            async: true 
            ,cache: false
            ,type: "post"
            ,url: goUrlPeterProc
            ,data: { "petSeq" : $("#userPetSeq").val() }
            ,success: function(response) {
                if (response.dto.petType == 239) {
                    dog.checked = true;
                } else if (response.dto.petType == 240) {
                    cat.checked = true;
                }

                if (response.dto.petGender == 150) {
                    male.checked = true;
                } else if (response.dto.petGender == 151) {
                    female.checked = true;
                }

                $("#petVarieties").val(response.dto.petVarieties);
                $("#petVariety").val(response.variety);
                $("#petName").val(response.dto.petName);

                for (let i = 0; i < document.querySelectorAll("#petNickname option").length; i++) {
                    if (document.querySelectorAll("#petNickname option")[i].value == response.dto.petNickname) {
                        document.querySelectorAll("#petNickname option")[i].setAttribute('selected', 'selected');
                    }
                }

                $("#petBirth").val(response.dto.petBirth);
                $("#petWeight").val(response.dto.petWeight);
    
                if (response.dto.petVaccinationFlag == 1) {
                    vaccinationYes.checked = true;
                } else {
                    vaccinationNo.checked = true;
                }
    
                if (response.dto.petNeuteringFlag == 1) {
                    neuteringYes.checked = true;
                } else {
                    neuteringNo.checked = true;
                }
                
                for(let i = 0; i < response.personal.length; i++) {
                    let personalBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badgeStyle");
                    let personalCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
                    $("#createPersonal").append(personalBadge.text(response.personal[i]));
                    $("#createPersonal").append(personalCloseBtn.attr('value', response.personalDto[i].persDiscription));
                    for (let j = 0; j < document.querySelectorAll(".selPersonal a").length; j++) {
                        if (document.querySelectorAll(".selPersonal a")[j].getAttribute("value") == response.personalDto[i].persDiscription) {
                            document.querySelectorAll(".selPersonal")[j].style.borderBottom = '';
                            document.querySelectorAll(".selPersonal a")[j].style.display = 'none';
                            break;
                        }
                    }
                }

                for(let i = 0; i < response.disease.length; i++) {
                    let diseaseBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badgeStyle");
                    let diseaseCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
                    $("#createDisease").append(diseaseBadge.text(response.disease[i]));
                    $("#createDisease").append(diseaseCloseBtn.attr('value', response.diseaseDto[i].diseDiscription));
                    for (let j = 0; j < document.querySelectorAll(".selDisease a").length; j++) {
                        if (document.querySelectorAll(".selDisease a")[j].getAttribute("value") == response.diseaseDto[i].diseDiscription) {
                            document.querySelectorAll(".selDisease")[j].style.borderBottom = '';
                            document.querySelectorAll(".selDisease a")[j].style.display = 'none';
                        }
                    }
                }
            }
            ,error : function(jqXHR) {
                alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
            }
        })
    }
});

$('.list').click(function(){
    $("#createPersonal").empty();
    $("#createDisease").empty();

    for (let j = 0; j < document.querySelectorAll(".selPersonal a").length; j++) {
        document.querySelectorAll(".selPersonal")[j].style = 'border-bottom : 1px solid gray';
        document.querySelectorAll(".selPersonal a")[j].style.display = 'block';
    }

    for (let j = 0; j < document.querySelectorAll(".selDisease a").length; j++) {
        document.querySelectorAll(".selDisease")[j].style = 'border-bottom : 1px solid gray';
        document.querySelectorAll(".selDisease a")[j].style.display = 'block';
    }

    // 내용 숨김
    contentB.style.display = "none";
    contentC.style.display = "none";
    contentA.style.display = "block";
});

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
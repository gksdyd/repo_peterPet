/* ===============================================-->
                      상품 관련 JS
   ===============================================--> */


/* ===============================================-->
                          공통
   ===============================================--> */

// 상품 종류에 따른 필터 및 등록 폼 변경 함수
changeProductType = function (formId, address) {
  let form = document.querySelector("form[name=" + formId + "]");
  form.action = address;
  form.submit();
}

// 상품의 기능을 여러개 설정하는 함수
var funcArray = [];
var funcNameArray = [];

$("#prodFunction").change(function(){
  $.ajax({
    async: true 
    ,cache: false
    ,type: "post"
    ,url: URL_PRODUCT_FUNCTION_XDM
    ,data: { "prodFunction" : $(this).val() }
    ,success: function(response) {
      let funcBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badge-subtle-primary");
      let funcCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
      $("#createFunction").append(funcBadge.text(response.funcName));
      $("#createFunction").append(funcCloseBtn.attr('value', response.funcSeq));

      for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
        if (document.querySelectorAll("#prodFunction option")[j].value == response.funcSeq) {
          document.querySelectorAll("#prodFunction option")[j].setAttribute("disabled", "true");
          funcArray.push(response.funcSeq);
          funcNameArray.push(response.funcName);
          break;
        }
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
});

// 상품 기능 뱃지 제거 버튼
$(document).on('click', '.removeFunc', function(){
  for (let i = 0; i < funcArray.length; i++) {
    if (funcArray[i] == $(this).val()) {
      document.querySelectorAll(".removeFunc")[i].remove();
      document.querySelectorAll(".badge-subtle-primary")[i].remove();
      funcArray.splice(i, 1);
      funcNameArray.splice(i, 1);
      break;
    }
  }
  
  for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
    if (document.querySelectorAll("#prodFunction option")[j].value == $(this).val()) {
      document.querySelectorAll("#prodFunction option")[j].removeAttribute("disabled");
      break;
    }
  }
});


/* ===============================================-->
                          List
   ===============================================--> */

// 서치할 때 submin 버튼
submitFunc = function() {
  $("#prodFuncArray").val(funcArray);
  $("#prodFuncNameArray").val(funcNameArray);
}

// 서치 버튼
$("#btnSearch").on('click', function() {
  submitFunc();
  search();
});

/* ===============================================-->
                          Form
   ===============================================--> */

let createFeedInfo = document.getElementById("createFeedInfo");
let createDelBtn = document.getElementById("createDelBtn");

let feedPriceArray = document.getElementById("feedPriceArray");
let feedWeightArray = document.getElementById("feedWeightArray");
let feedDiscountArray = document.getElementById("feedDiscountArray");

var num = 1;

// 사료 가격, 무게, 할인율 추가 버튼
$("#feedInfoSaveBtn").on('click', function() {
  validationInit();
  if (!infoValidation()) {
      return false;
  }

  let inputGroup = document.createElement('div');
  inputGroup.setAttribute("class", "input-group mb-2");

  let createNum = document.createElement('span');
  createNum.setAttribute("class", "input-group-text fs-11");
  createNum.innerHTML = num;

  let createPrice = document.createElement('input');
  createPrice.setAttribute("type", "text");
  createPrice.setAttribute("class", "form-control fs-11 feedPrice");
  createPrice.setAttribute("style", "text-align: right");
  createPrice.setAttribute("value", feedPrice.value);

  let createWon = document.createElement('span');
  createWon.setAttribute("class", "input-group-text fs-11");
  createWon.innerHTML = "원";

  let createWeight = document.createElement('input');
  createWeight.setAttribute("type", "text");
  createWeight.setAttribute("class", "form-control fs-11 text-right feedWeight");
  createWeight.setAttribute("style", "text-align: right");
  createWeight.setAttribute("value", feedWeight.value);

  let createKg = document.createElement('span');
  createKg.setAttribute("class", "input-group-text fs-11");
  createKg.innerHTML = "Kg";

  let createDiscount = document.createElement('input');
  createDiscount.setAttribute("type", "text");
  createDiscount.setAttribute("class", "form-control fs-11 feedDiscount");
  createDiscount.setAttribute("style", "text-align: right");
  createDiscount.setAttribute("value", feedDiscount.value);

  let createPer = document.createElement('span');
  createPer.setAttribute("class", "input-group-text fs-11");
  createPer.innerHTML = "%";

  let createDel = document.createElement('button');
  createDel.setAttribute("type", "button");
  createDel.setAttribute("class", "btn btn-outline-danger mb-1 fs-11 ms-2 removeBtn");
  createDel.setAttribute("value", num++);
  createDel.innerHTML = "삭제";

  inputGroup.appendChild(createNum);
  inputGroup.appendChild(createPrice);
  inputGroup.appendChild(createWon);
  inputGroup.appendChild(createWeight);
  inputGroup.appendChild(createKg);
  inputGroup.appendChild(createDiscount);
  inputGroup.appendChild(createPer);
  inputGroup.appendChild(createDel);

  createFeedInfo.appendChild(inputGroup);

  feedPrice.value = null;
  feedWeight.value = null;
  feedDiscount.value = null;
});

// 사료 가격, 무게, 할인율 제거 버튼
$(document).on('click', '.removeBtn', function(){
  let parent = $(this).parent();
  parent.remove();
  num--;

  for (let i = 0; i < document.querySelectorAll(".removeBtn").length; i++) {
    document.querySelectorAll(".removeBtn")[i].parentNode.firstChild.innerHTML = i + 1;
  }
});

// 등록 시, 가격, 무게, 할인율 전달하는 함수
feedInfoSave = function() {
  let priceArray = [];
  let weightArray = [];
  let discountArray = [];

  let feedPrice = document.querySelectorAll(".feedPrice");
  let feedWeight = document.querySelectorAll(".feedWeight");
  let feedDiscount = document.querySelectorAll(".feedDiscount");
  
  for (let i = 0; i < feedPrice.length; i++) {
    priceArray.push(feedPrice[i].value);
    weightArray.push(feedWeight[i].value);
    discountArray.push(feedDiscount[i].value);
  }
  
  feedPriceArray.value = priceArray;
  feedWeightArray.value = weightArray;
  feedDiscountArray.value = discountArray;
}

// 등록 시, 기능율 전달하는 함수
feedFuncSave = function() {
  $("#prodFuncArray").val(funcArray);
}

// 수정시 초기화하는 함수
initFuncBadge = function() {
  funcArray = $("#prodFuncArray").val().replace(/\[|\]/g, "").split(',');
  funcNameArray = $("#prodFuncNameArray").val().replace(/\[|\]/g, "").split(',');  
  $("#prodFuncSeqArray").val($("#prodFuncSeqArray").val().replace(/\[|\]/g, "").split(','));
  
  if (funcArray != "") {
    for (let i = 0; i < funcArray.length; i++) {
      funcArray[i] = Number(funcArray[i]);
      let funcBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badge-subtle-primary");
      let funcCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
      $("#createFunction").append(funcBadge.text(funcNameArray[i]));
      $("#createFunction").append(funcCloseBtn.attr('value', funcArray[i]));
    }
  } else {
    funcArray = [];
    funcNameArray = [];
  }
}

// 수정시 초기화하는 함수
initInfo = function() {
  let priceArray = feedPriceArray.value.replace(/\[|\]/g, "").split(',');
  let weightArray = feedWeightArray.value.replace(/\[|\]/g, "").split(',');
  let discountArray = feedDiscountArray.value.replace(/\[|\]/g, "").split(',');
  feedPriceArray.value = priceArray;
  feedWeightArray.value = weightArray;
  feedDiscountArray.value = discountArray;
  $("#feedInfoSeqArray").val($("#feedInfoSeqArray").val().replace(/\[|\]/g, "").split(','));

  if (priceArray != "") {
    for (let i = 0; i < priceArray.length; i++) {
      let inputGroup = document.createElement('div');
      inputGroup.setAttribute("class", "input-group mb-2");

      let createNum = document.createElement('span');
      createNum.setAttribute("class", "input-group-text fs-11");
      createNum.innerHTML = num;

      let createPrice = document.createElement('input');
      createPrice.setAttribute("type", "text");
      createPrice.setAttribute("class", "form-control fs-11 feedPrice");
      createPrice.setAttribute("style", "text-align: right");
      createPrice.setAttribute("value", priceArray[i]);

      let createWon = document.createElement('span');
      createWon.setAttribute("class", "input-group-text fs-11");
      createWon.innerHTML = "원";

      let createWeight = document.createElement('input');
      createWeight.setAttribute("type", "text");
      createWeight.setAttribute("class", "form-control fs-11 text-right feedWeight");
      createWeight.setAttribute("style", "text-align: right");
      createWeight.setAttribute("value", weightArray[i]);

      let createKg = document.createElement('span');
      createKg.setAttribute("class", "input-group-text fs-11");
      createKg.innerHTML = "Kg";

      let createDiscount = document.createElement('input');
      createDiscount.setAttribute("type", "text");
      createDiscount.setAttribute("class", "form-control fs-11 feedDiscount");
      createDiscount.setAttribute("style", "text-align: right");
      createDiscount.setAttribute("value", discountArray[i]);

      let createPer = document.createElement('span');
      createPer.setAttribute("class", "input-group-text fs-11");
      createPer.innerHTML = "%";

      let createDel = document.createElement('button');
      createDel.setAttribute("type", "button");
      createDel.setAttribute("class", "btn btn-outline-danger mb-1 fs-11 ms-2 removeBtn");
      createDel.setAttribute("value", num++);
      createDel.innerHTML = "삭제";

      inputGroup.appendChild(createNum);
      inputGroup.appendChild(createPrice);
      inputGroup.appendChild(createWon);
      inputGroup.appendChild(createWeight);
      inputGroup.appendChild(createKg);
      inputGroup.appendChild(createDiscount);
      inputGroup.appendChild(createPer);
      inputGroup.appendChild(createDel);

      createFeedInfo.appendChild(inputGroup);
    }
  } 
}

initFuncBadge();
initInfo();
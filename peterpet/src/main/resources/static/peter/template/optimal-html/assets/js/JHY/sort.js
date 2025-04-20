let prodSeq = [];
let funcName = [];
let infoWeight = [];
let feedBrand = [];
let prodName = [];
let infoDiscount = [];
let infoPrice = [];
let prodScore = [];

let badgeArray = [];
let prodFuncArray = [];

let feedSalaryAge;
let feedType;
let feedEtc;

let start;
let end;
let page;
let total;

function noEvent() { // 새로 고침 방지
    if (event.keyCode == 116) {
        form.action = goUrlProductList;
        form.submit();
        return false;
    } else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
        form.action = goUrlProductList;
        form.submit();
        return false;
    }
}
document.onkeydown=noEvent;

$("#shSortBy").on("change", function () {
    checkBox();
    create();
});

$(".form-check-input").change(function() {
    checkBox();
    create();
    pagination();
});

checkBox = function() {
    let productType = document.querySelectorAll(".product-type");
    let badge = document.querySelectorAll("#searchBadge a");
    allRemoveBadge();
    for (let j = 0; j < productType.length; j++) {
        let inputType = productType[j].querySelectorAll(".form-check-input");
        for (let i = 0; i < inputType.length; i++) {
            if (inputType[i].checked) {
                if (j == 0) {
                    $("#shFeedSalaryAge").val(inputType[i].value);
                    feedSalaryAge = inputType[i].value;
                } else if (j == 1) {
                    $("#shFeedType").val(inputType[i].value);
                    feedType = inputType[i].value;
                } else if (j == 2) {
                    if (!prodFuncArray.includes(inputType[i].value)) {
                        prodFuncArray.push(inputType[i].value);
                    }
                } else if (j == 3) {
                    $("#shFeedEtc").val(inputType[i].value);
                    feedEtc = inputType[i].value;
                }
                badgeArray.push(inputType[i].value);
                addBadge(inputType[i].value);
            } else {
                for (let k = 1; k < badge.length; k++) {
                    if (badge[k].getAttribute("data-value") == inputType[i].value) {
                        badge[k].parentNode.remove();
                    }
                }
                if (prodFuncArray.includes(inputType[i].value)) {
                    prodFuncArray.forEach(function(func, index) {
                        if (func == inputType[i].value) {
                            prodFuncArray.splice(index, 1);
                        }
                    });
                }
            }
        }
    }
    $("#prodFuncArray").val(prodFuncArray);
    $("#badgeArray").val(badgeArray);
}

create = function() {
    let createProduct = $(".productInfo");
    createProduct.empty();
    getSettingValue();
    for (let i = 0; i < prodSeq.length; i++) {
        let body = document.createElement('div');
        body.setAttribute("class", "col-6 col-sm-6 col-md-4 col-lg-4 item");
        body.append(initImage(prodSeq[i]));
        body.append(initDetail(prodSeq[i], funcName[i], infoWeight[i], feedBrand[i], prodName[i], infoDiscount[i], infoPrice[i], prodScore[i]));
        createProduct.append(body);
    }
    initArray();
}

getSettingValue = function() {
    $.ajax({
        async: false 
        ,cache: false
        ,type: "post"
        ,url: URL_PETER_INFO
        ,data: { "shSortBy" : $("#shSortBy").val(), "thisPage" : $("#thisPage").val(), "shFeedSalaryAge" : feedSalaryAge, "shFeedType" : feedType,
            "prodFuncArray" : prodFuncArray, "shFeedEtc" : feedEtc }
        ,success: function(response) {
            $("#productNum").text(response.vo.totalRows);

            start = response.vo.startPage;
            end = response.vo.endPage;
            page = response.vo.thisPage;
            total = response.vo.totalPages;

            for (let i = 0; i < response.dtos.length; i++) {
                prodSeq.push(response.dtos[i].prodSeq);
                funcName.push(response.dtos[i].funcName);
                infoWeight.push(response.dtos[i].infoWeight);
                feedBrand.push(response.dtos[i].feedBrand);
                prodName.push(response.dtos[i].prodName);
                infoDiscount.push(response.dtos[i].infoDiscount);
                infoPrice.push(response.dtos[i].infoPrice);
                prodScore.push(response.dtos[i].prodScore);
            }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

initArray = function() {
    prodSeq = [];
    funcName = [];
    infoWeight = [];
    feedBrand = [];
    prodName = [];
    infoDiscount = [];
    infoPrice = [];
    prodScore = [];
}

initImage = function(seq) {
    let imageGroup = document.createElement('div');
    imageGroup.setAttribute("class", "product-image");
    
    let createAddr = document.createElement('a');
    createAddr.setAttribute("href", window.href.replace('value', seq));

    let createImage = document.createElement('img');
    createImage.setAttribute("class", "blur-up lazyload");
    createImage.setAttribute("src", "/peter/template/optimal-html/assets/images/사료/loyalcanin-dog-indore-puppy-3kg-immunity.jpeg");

    let createLabel = document.createElement("div");
    createLabel.setAttribute("class", "product-labels");

    let createLabelText = document.createElement("span");
    createLabelText.setAttribute("class", "lbl on-sale rounded");
    createLabelText.innerHTML = "최저가도전!";

    createLabel.append(createLabelText);
    createAddr.append(createImage);
    createAddr.append(createLabel);
    imageGroup.append(createAddr);
    
    return imageGroup;
}

initDetail = function(seq, fName, weight, brand, name, discount, price, score) {
    let detailGroup = document.createElement('div');
    detailGroup.setAttribute("class", "product-details text-center");

    let detailName = document.createElement('div');
    detailName.setAttribute("class", "product-name text-uppercase");

    let text = "";
    let detailNameText = document.createElement('a');
    detailNameText.setAttribute("href", window.href.replace('value', seq));
    text = window.cache[brand - 1].codeName + ' ' + name;
    if (weight != null) {
        text += ' ' + weight + 'kg';
    }
    if (fName != null) {
        text += ' ' + window.cache[fName - 1].codeName;
    }
    detailNameText.innerHTML = text;

    detailName.append(detailNameText);
    detailGroup.append(detailName);

    let detailPrice = document.createElement('div');
    detailPrice.setAttribute("class", "product-price");

    let detailCurrPrice = document.createElement('span');
    detailCurrPrice.setAttribute("class", "price");
    if (discount == 0) {
        detailCurrPrice.innerHTML = price;
    } else {
        let detailOldPrice = document.createElement('div');
        detailOldPrice.setAttribute("class", "old-price");
        detailOldPrice.innerHTML = price;
        detailPrice.append(detailOldPrice);

        detailCurrPrice.innerHTML = discount + '% ' +  (price * (100 - discount) / 100);
    }
    detailPrice.append(detailCurrPrice);
    detailGroup.append(detailPrice);

    let detailReview = document.createElement('div');
    detailReview.setAttribute("class", "product-review d-flex align-items-center justify-content-center");

    let detailStar = document.createElement('span');
    for (let i = 1; i <= 5; i++) {
        let star = document.createElement('i');
        if (score >= i) {
            star.setAttribute("class", "an an-star");
        } else {
            star.setAttribute("class", "an an-star-o");
        }
        detailStar.append(star);
    }
    detailReview.append(detailStar);
    detailGroup.append(detailReview);

    let detailDelivery = document.createElement('div');
    detailDelivery.setAttribute("class", "product-review d-flex align-items-center justify-content-center");
    detailDelivery.style.color = "rgb(229, 135, 151)";

    let deliveryImage = document.createElement('i');
    deliveryImage.setAttribute("class", "an an-ship-fast fs-4");
    
    detailDelivery.append(deliveryImage);
    detailDelivery.innerHTML += "&emsp;빠른 배송";
    detailGroup.append(detailDelivery);
    return detailGroup;
}

pagination = function() {
    let pagination = $(".pagination > ul");
    pagination.empty();

    if (start > 1) {
        let li = document.createElement('li');
        li.setAttribute("class", "prev page-item");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${start - 1})`);

        let i = document.createElement('i');
        i.setAttribute("class", "icon align-middle an an-caret-left");
        i.setAttribute("aria-hidden", "true")
        
        a.append(i);
        li.append(a);
        pagination.append(li);
    }

    for (let i = start; i <= end; i++) {
        let li = document.createElement('li');
        li.setAttribute("class", "page-item");
        if (i === page) li.classList.add("active");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${i})`);
        a.innerHTML = i;
        
        li.append(a);
        pagination.append(li);
    }

    if (end < total) {
        let li = document.createElement('li');
        li.setAttribute("class", "next page-item");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${end + 1})`);

        let i = document.createElement('i');
        i.setAttribute("class", "icon align-middle an an-caret-right");
        i.setAttribute("aria-hidden", "true")
        
        a.append(i);
        li.append(a);
        pagination.append(li);
    }
}

addBadge = function(code) {
    let li = document.createElement('li');
    let a = document.createElement('a');
    a.setAttribute("href", "javascript:void(0)");
    a.setAttribute("onclick", 'removeBadge(this)');
    a.setAttribute("data-value", code);
    a.innerHTML = window.cache[code - 1].codeName;
    let i = document.createElement('i');
    i.setAttribute("class", "an an-times-l");

    a.append(i);
    li.append(a);

    $("#searchBadge").append(li);
}

removeBadge = function(e) {
    let productType = document.querySelectorAll(".product-type");
    let flag = false;

    for (let i = 0; i < productType.length; i++) {
        let inputType = productType[i].querySelectorAll(".form-check-input");
        for (let j = 0; j < inputType.length; j++) {
            if (inputType[j].value == $(e).data("value")) {
                inputType[j].checked = false;
                if (i == 0) {
                    $("#shFeedSalaryAge").val(null);
                    feedSalaryAge = null;
                } else if (i == 1) {
                    $("#shFeedType").val(null);
                    feedType = null;
                } else if (i == 2) {
                    prodFuncArray.forEach(function(func ,index) {
                        if (func == $(e).data("value")) {
                            prodFuncArray.splice(index, 1);
                        }
                    });
                    $("#prodFuncArray").val(prodFuncArray);
                } else if (i == 3) {
                    $("#shFeedEtc").val(null);
                    feedEtc = null;
                }
                flag = true;
                break;
            }
            if (flag) {
                break;
            }
        }
    }
    badgeArray.forEach(function(badge, index) {
        if (badge == $(e).data("value")) {
            badgeArray.splice(index, 1);
        }
    });
    $("#badgeArray").val(badgeArray);
    $(e).parent().remove();
    create();
    pagination();
}

allRemoveBadge = function() {
    let badge = document.querySelectorAll("#searchBadge li");
    badgeArray = [];
    for (let i = 1; i < badge.length; i++) {
        badge[i].remove();
    }
}

$("#clearBtn").on("click", function() {
    allRemoveBadge();
    let productType = document.querySelectorAll(".product-type");
    
    for (let i = 0; i < productType.length; i++) {
        let inputType = productType[i].querySelectorAll(".form-check-input");
        for (let j = 0; j < inputType.length; j++) {
            inputType[j].checked = false;
        }
    }
    $("#shFeedSalaryAge").val(null);
    feedSalaryAge = null;
    $("#shFeedType").val(null);
    feedType = null;
    prodFuncArray = [];
    $("#prodFuncArray").val(null);
    badgeArray = [];
    $("#badgeArray").val(null);
    $("#shFeedEtc").val(null);
    feedEtc = null;
    create();
    pagination();
});

initBadge = function() {
    badgeArray = $("#badgeArray").val().replace(/\s+/g, '').replace(/\[|\]/g, "").split(',');
    prodFuncArray = $("#prodFuncArray").val().replace(/\s+/g, '').replace(/\[|\]/g, "").split(',');
    for (let i = 0; i < badgeArray.length; i++) {
        addBadge(badgeArray[i]);
    }
}
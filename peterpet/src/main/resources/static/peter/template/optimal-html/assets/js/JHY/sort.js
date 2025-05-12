let badgeArray = [];
let prodFuncArray = [];
let feedBrandArray = [];
let shIngredientArray = [];

let feedSalaryAge;
let feedType;
let feedEtc;
let feedWeight;
let feedSize;
let prodPetType;
let snckFeature;

function noEvent(event) {
    let form = document.getElementById("productPagination");

    $("#badgeArray").val(badgeArray);
    $("#prodFuncArray").val(prodFuncArray);
    $("#feedBrandArray").val(feedBrandArray);
    $("#shIngredientArray").val(shIngredientArray);
    $("#shFeedSalaryAge").val(feedSalaryAge);
    $("#shFeedType").val(feedType);
    $("#shFeedEtc").val(feedEtc);
    $("#shFeedWeight").val(feedWeight);
    $("#shFeedSize").val(feedSize);
    $("#prodPetType").val(prodPetType);
    $("#shSnckFeature").val(snckFeature);

    if (event.keyCode === 116 || (event.ctrlKey && (event.keyCode === 78 || event.keyCode === 82))) {
        event.preventDefault(); // 기본 동작 막기
        form.method = "post";
        form.action = "/peter/shop/ShopPeterList"; // form이 전역에 없을 경우
        form.submit();
    }
}
document.addEventListener('keydown', noEvent);

function sort() {
    shop();
}

function radio(e) {
    let flag = false;
    let current;
    let name = $(e).attr("name");
    
    if (name === "petType") {
        prodPetType = $(e).val();
        allRemoveBadge();
        filter();
        shop();
        return;
    }

    for (let i = 0; i < $("input[name=" + name + "]").length; i++) {
        if (badgeArray.includes($("input[name=" + name + "]").eq(i).val())) {
            for (let j = 0; badgeArray.length; j++) {
                if (badgeArray[j] == $("input[name=" + name + "]").eq(i).val()) {
                    current = badgeArray[j];
                    badgeArray.splice(j, 1);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    if (current == $(e).val()) {
        $(e).prop("checked", false);
        if (name === "feedSalaryAge") {
            feedSalaryAge = null;
        } else if (name === "feedType") {
            feedType = null;
        } else if (name === "feedEtc") {
            feedEtc = null;
        } else if (name === "feedWeight") {
            feedWeight = null;
        } else if (name === "feedSize") {
            feedSize = null;
        } else if (name === "feature") {
            snckFeature = null;
        }
    } else {
        badgeArray.push($(e).val());
        if (name === "feedSalaryAge") {
            feedSalaryAge = $(e).val();
        } else if (name === "feedType") {
            feedType = $(e).val();
        } else if (name === "feedEtc") {
            feedEtc = $(e).val();
        } else if (name === "feedWeight") {
            feedWeight = $(e).val();
        } else if (name === "feedSize") {
            feedSize = $(e).val();
        } else if (name === "feature") {
            snckFeature = $(e).val();
        }
    }
    shop();
}

function check(e) {    
    let name = $(e).attr("name");
    if (badgeArray.includes($(e).val())) {
        for (let j = 0; badgeArray.length; j++) {
            if (badgeArray[j] == $(e).val()) {
                badgeArray.splice(j, 1);
                break;
            }
        }
        if (name === "feedBrand") {
            for (let j = 0; feedBrandArray.length; j++) {
                if (feedBrandArray[j] == $(e).val()) {
                    feedBrandArray.splice(j, 1);
                    break;
                }
            }
        } else if (name === "feedIngredient") {
            for (let j = 0; shIngredientArray.length; j++) {
                if (shIngredientArray[j] == $(e).val()) {
                    shIngredientArray.splice(j, 1);
                    break;
                }
            }
        } else if (name === "funcName") {
            for (let j = 0; prodFuncArray.length; j++) {
                if (prodFuncArray[j] == $(e).val()) {
                    prodFuncArray.splice(j, 1);
                    break;
                }
            }
        }
    } else {
        badgeArray.push($(e).val());
        if (name === "feedBrand") {
            feedBrandArray.push($(e).val());
        } else if (name === "feedIngredient") {
            shIngredientArray.push($(e).val());
        } else if (name === "funcName") {
            prodFuncArray.push($(e).val());
        }
    }
    shop();
}

function shop() { 
    let value;   
    const params = new URLSearchParams();
    params.append('badgeArray', badgeArray);
    params.append('prodType', $("#prodType").val());
    params.append('shMinPrice', $("#shMinPrice").val());
    params.append('shMaxPrice', $("#shMaxPrice").val());
    value = parseInt(feedSalaryAge);
    params.append('shFeedSalaryAge', isNaN(value) ? '' : value);
    value = parseInt(feedType);
    params.append('shFeedType', isNaN(value) ? '' : value);
    params.append('prodFuncArray', prodFuncArray);
    value = parseInt(feedEtc);
    params.append('shFeedEtc', isNaN(value) ? '' : value);
    value = parseInt(feedWeight);
    params.append('shFeedWeight', isNaN(value) ? '' : value);
    params.append('feedBrandArray', feedBrandArray);
    value = parseInt(shIngredientArray);
    params.append('shIngredientArray', isNaN(value) ? '' : value);
    value = parseInt(feedSize);
    params.append('shFeedSize', isNaN(value) ? '' : value);
    value = parseInt($("#shSortBy").val());
    params.append('shSortBy', isNaN(value) ? '' : value);
    params.append('thisPage', $("#thisPage").val());
    value = parseInt(prodPetType);
    params.append('prodPetType', isNaN(value) ? '' : value);
    value = parseInt(snckFeature);
    params.append('shSnckFeature', isNaN(value) ? '' : value);

    fetch('/peter/shop/ShopPeterSearch', {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: params
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('main').innerHTML = "";
        document.getElementById('main').innerHTML = html;
        gridSetting();
    });
}

function gridSetting() {
    if ($("#gridMethod").val() == 0) {
        $('.grid--view-items').removeClass('prd-list').addClass('prd-grid');
    }else if ($("#gridMethod").val() == 1) {
        $('.grid--view-items').removeClass('prd-grid').addClass('prd-list');
    }
}

removeBadge = function(e) {
    let value = $(e).data("value") + "";

    if (badgeArray.includes(value)) {
        for (let j = 0; badgeArray.length; j++) {
            if (badgeArray[j] == value) {
                badgeArray.splice(j, 1);
                break;
            }
        }
        if (feedBrandArray.includes(value)) {
            for (let j = 0; feedBrandArray.length; j++) {
                if (feedBrandArray[j] == value) {
                    feedBrandArray.splice(j, 1);
                    break;
                }
            }
            for (let i = 0; i < $("input[name=feedBrand]").length; i++) {
                if ($("input[name=feedBrand]").eq(i).val() == value) {
                    $("input[name=feedBrand]").eq(i).prop("checked", false);
                }
            }
        } else if (shIngredientArray.includes(value)) {
            for (let j = 0; shIngredientArray.length; j++) {
                if (shIngredientArray[j] == value) {
                    shIngredientArray.splice(j, 1);
                    break;
                }
            }
            for (let i = 0; i < $("input[name=feedIngredient]").length; i++) {
                if ($("input[name=feedIngredient]").eq(i).val() == value) {
                    $("input[name=feedIngredient]").eq(i).prop("checked", false);
                }
            }
        } else if (prodFuncArray.includes(value)) {
            for (let j = 0; prodFuncArray.length; j++) {
                if (prodFuncArray[j] == value) {
                    prodFuncArray.splice(j, 1);
                    break;
                }
            }
            for (let i = 0; i < $("input[name=funcName]").length; i++) {
                if ($("input[name=funcName]").eq(i).val() == value) {
                    $("input[name=funcName]").eq(i).prop("checked", false);
                }
            }
        } else if (feedSalaryAge == value) {
            feedSalaryAge = null;
            for (let i = 0; i < $("input[name=feedSalaryAge]").length; i++) {
                if ($("input[name=feedSalaryAge]").eq(i).val() == value) {
                    $("input[name=feedSalaryAge]").eq(i).prop("checked", false);
                }
            }
        } else if (feedType == value) {
            feedType = null;
            for (let i = 0; i < $("input[name=feedType]").length; i++) {
                if ($("input[name=feedType]").eq(i).val() == value) {
                    $("input[name=feedType]").eq(i).prop("checked", false);
                }
            }
        } else if (feedEtc == value) {
            feedEtc = null;
            for (let i = 0; i < $("input[name=feedEtc]").length; i++) {
                if ($("input[name=feedEtc]").eq(i).val() == value) {
                    $("input[name=feedEtc]").eq(i).prop("checked", false);
                }
            }
        } else if (feedWeight == value) {
            feedWeight = null;
            for (let i = 0; i < $("input[name=feedWeight]").length; i++) {
                if ($("input[name=feedWeight]").eq(i).val() == value) {
                    $("input[name=feedWeight]").eq(i).prop("checked", false);
                }
            }
        } else if (feedSize == value) {
            feedSize = null;
            for (let i = 0; i < $("input[name=feedSize]").length; i++) {
                if ($("input[name=feedSize]").eq(i).val() == value) {
                    $("input[name=feedSize]").eq(i).prop("checked", false);
                }
            }
        } else if (snckFeature == value) {
            snckFeature = null;
            for (let i = 0; i < $("input[name=feature]").length; i++) {
                if ($("input[name=feature]").eq(i).val() == value) {
                    $("input[name=feature]").eq(i).prop("checked", false);
                }
            }
        }
    }
    shop();
}

allRemoveBadge = function() {
    let min = 0;
    let max = 400000;
    $("#shMinPrice").val("");
    $("#shMaxPrice").val("");
    let temp = "￦" + min.toLocaleString() + " - ￦" + max.toLocaleString();
    $("#amount").val(temp);

    let badge = $("#searchBadge li a");
    for (let i = 1; i < badge.length; i++) {
        removeBadge(badge[i]);
    }

    price_slider();
}

function goShop(page) {
    $("#thisPage").val(page);
    shop();
}

initBadge = function() {
    let array = $("#badgeArray").val().replace(/\[|\]/g, "").replace(/\s+/g, '').split(',');
    
    if (array[0] === "") {
        array = [];
    }

    for (let i = 0; i < array.length; i++) {
        badgeArray.push(array[i]);
    }

    array = $("#prodFuncArray").val().replace(/\[|\]/g, "").replace(/\s+/g, '').split(',');
    
    if (array[0] === "") {
        array = [];
    }

    for (let i = 0; i < array.length; i++) {
        prodFuncArray.push(array[i]);
    }

    array = $("#feedBrandArray").val().replace(/\[|\]/g, "").replace(/\s+/g, '').split(',');
    
    if (array[0] === "") {
        array = [];
    }

    for (let i = 0; i < array.length; i++) {
        feedBrandArray.push(array[i]);
    }

    array = $("#shIngredientArray").val().replace(/\[|\]/g, "").replace(/\s+/g, '').split(',');
    
    if (array[0] === "") {
        array = [];
    }

    for (let i = 0; i < array.length; i++) {
        shIngredientArray.push(array[i]);
    }

    feedSalaryAge = $("#shFeedSalaryAge").val();
    feedType = $("#shFeedType").val();
    feedEtc = $("#shFeedEtc").val();
    feedWeight = $("#shFeedWeight").val();
    feedSize = $("#shFeedSize").val();
    prodPetType = $("#prodPetType").val();
    snckFeature = $("#shSnckFeature").val();
}

function filter() {
    let value;
    const params = new URLSearchParams();
    value = parseInt(prodPetType);
    params.append('prodPetType', isNaN(value) ? '' : value);
    params.append('prodType', $("#prodType").val());

    fetch('/peter/shop/ShopPeterFilter', {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: params
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('filter').innerHTML = "";
        document.getElementById('filter').innerHTML = html;
        price_slider();
    });
}
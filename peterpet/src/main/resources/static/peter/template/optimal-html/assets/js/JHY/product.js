periordTime = function() {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: URL_PETER_TIME
        ,data: { }
        ,success: function(response) {
            if (response.day != 0) {
                $("#day").text(response.day + "일 ");
            } else {
                $("#day").text("");
            }
            $("#hour").text(response.hour + "시간 ");
            $("#minute").text(response.minute + "분 ");
            $("#second").text(response.second + "초 ");
            $("#fromDate").text(response.delivery);
        }
        ,error : function(jqXHR) {
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    })
}

setInterval(() => {periordTime();}, 950);
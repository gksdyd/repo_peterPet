let chunks = [];
let media = null;
let recorder = null;

if(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia) {
    recodeInit();
}
else {
    alert("Mic not available");
}

function recodeInit() {
    let mediaOptions = {
        audio: {
        tag: 'audio',
        type: 'audio/wav',
        ext: '.wav',
        gUM: {audio: true}
        }
    };

    media = mediaOptions.audio;
    navigator.mediaDevices.getUserMedia(media.gUM).then(_stream => {
        let stream = _stream;
        recorder = new MediaRecorder(stream);
        recorder.ondataavailable = e => {
          chunks.push(e.data);
          if(recorder.state == 'inactive') {
            makeLink();
          }
        };
        console.log('got media successfully');
      }).catch(err => console.log(err));
}

function makeLink(){
    let blob = new Blob(chunks, {type: media.type });

    const formData = new FormData();
    formData.append('uploadImg1', blob, `recordFile${media.ext}`);

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/speech/peter/SpeechPeterInsert"
        ,data: formData
        ,processData: false
        ,contentType: false
        ,success: function(response) {
            $(".col").append("<p>" + response + "</p>");
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
  }

  function startRecode() {
    $("#micON").removeClass("d-none");
    $("#micOff").addClass("d-none");
    $("#btnStart").addClass("disabled");
    $("#btnStop").removeClass("disabled");
    chunks=[];
    recorder.start();
  }

  function stopRecode() {
    $("#micON").addClass("d-none");
    $("#micOff").removeClass("d-none");
    $("#btnStart").removeClass("disabled");
    $("#btnStop").addClass("disabled");
    recorder.stop();
  }
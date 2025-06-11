let chunks = [];
let media = null;
let recorder = null;
let initFlag = 1;
let flag = 0;
let counter = 1;

function searchMic() {
    //마이크 지원여부
    if (initFlag == 1) {
        if(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia) {
            recodeInit();
            initFlag = 0;
            flag = 1;
        }
        else {
            alert("Mic not available");
        }
    } else {
        if (flag == 0) {
            startRecode();
        } else {
            stopRecode();
        }
    }
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
        startRecode();
        console.log('got media successfully');
      }).catch(err => console.log(err));
}

function makeLink(){
    let blob = new Blob(chunks, {type: media.type })
      , url = URL.createObjectURL(blob)
      , hf = document.createElement('a')
    ;

    hf.href = url;
    hf.download = `${counter++}${media.ext}`;
    hf.style.display = 'none';
    document.body.appendChild(hf);
    hf.click();

    document.body.removeChild(hf);
    URL.revokeObjectURL(url);
  }

  function startRecode() {
    $("#searchMic i").attr('style', "color:red");
    flag = 1;
    chunks=[];
    recorder.start();
  }

  function stopRecode() {
    $("#searchMic i").attr('style', "");
    flag = 0;
    recorder.stop();
  }
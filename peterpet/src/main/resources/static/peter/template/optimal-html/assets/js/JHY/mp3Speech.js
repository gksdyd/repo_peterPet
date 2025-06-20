let chunks = [];
let audioContext;
let source;
let processor;
let mp3Encoder;

navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
  audioContext = new AudioContext();
  source = audioContext.createMediaStreamSource(stream);
  processor = audioContext.createScriptProcessor(4096, 1, 1);
});

function makeLink() {
  const blob = new Blob(chunks, { type: 'audio/mp3' });

  const url = URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = 'recording.mp3';
  a.click();

  const formData = new FormData();
  formData.append('uploadImg1', blob, 'recording.mp3');

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

    mp3Encoder = new lamejs.Mp3Encoder(1, 16000, 128);

    source.connect(processor);
    processor.connect(audioContext.destination);

    processor.onaudioprocess = function (e) {
      let input = e.inputBuffer.getChannelData(0);
      let resampled = resampleBuffer(input, audioContext.sampleRate, 16000);
      let samples16 = convertFloat32ToInt16(resampled);

      let mp3buf = mp3Encoder.encodeBuffer(samples16);
      if (mp3buf.length > 0) {
        chunks.push(mp3buf);
      }
    };
  }

  function stopRecode() {
    $("#micON").addClass("d-none");
    $("#micOff").removeClass("d-none");
    $("#btnStart").removeClass("disabled");
    $("#btnStop").addClass("disabled");

    processor.disconnect();
    source.disconnect();
    let mp3buf = mp3Encoder.flush();
    if (mp3buf.length > 0) {
      chunks.push(mp3buf);
    }
    
    makeLink();
  }

  function convertFloat32ToInt16(buffer) {
    let l = buffer.length;
    let buf = new Int16Array(l);
    for (let i = 0; i < l; i++) {
        let s = Math.max(-1, Math.min(1, buffer[i]));
        buf[i] = s < 0 ? s * 0x8000 : s * 0x7FFF;
    }
    return buf;
}

function resampleBuffer(buffer, fromSampleRate, toSampleRate) {
  const sampleRateRatio = fromSampleRate / toSampleRate;
  const newLength = Math.round(buffer.length / sampleRateRatio);
  const resampled = new Float32Array(newLength);

  for (let i = 0; i < newLength; i++) {
      const idx = i * sampleRateRatio;
      const before = Math.floor(idx);
      const after = Math.min(before + 1, buffer.length - 1);
      const at = idx - before;
      resampled[i] = buffer[before] + (buffer[after] - buffer[before]) * at;
  }

  return resampled;
}
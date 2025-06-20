let floatChunks = [];
let audioContext;
let source;
let processor;

navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
  audioContext = new AudioContext(); // 기본은 44100Hz
  source = audioContext.createMediaStreamSource(stream);
  processor = audioContext.createScriptProcessor(4096, 1, 1);
});

function startRecode() {
  $("#micON").removeClass("d-none");
  $("#micOff").addClass("d-none");
  $("#btnStart").addClass("disabled");
  $("#btnStop").removeClass("disabled");
  floatChunks = [];

  source.connect(processor);
  processor.connect(audioContext.destination);

  processor.onaudioprocess = function (e) {
    const input = e.inputBuffer.getChannelData(0);
    floatChunks.push(new Float32Array(input));
  };
}

function stopRecode() {
  $("#micON").addClass("d-none");
  $("#micOff").removeClass("d-none");
  $("#btnStart").removeClass("disabled");
  $("#btnStop").addClass("disabled");

  processor.disconnect();
  source.disconnect();

  const merged = mergeBuffers(floatChunks);
  const resampled = resampleBuffer(merged, audioContext.sampleRate, 16000);
  const wavBlob = encodeWAV(resampled, 16000);

  makeLink(wavBlob, 'recording.wav');
}

function mergeBuffers(chunks) {
  const length = chunks.reduce((sum, buf) => sum + buf.length, 0);
  const result = new Float32Array(length);
  let offset = 0;
  for (const buf of chunks) {
    result.set(buf, offset);
    offset += buf.length;
  }
  return result;
}

function resampleBuffer(buffer, fromRate, toRate) {
  if (fromRate === toRate) return buffer;

  const ratio = fromRate / toRate;
  const newLength = Math.round(buffer.length / ratio);
  const resampled = new Float32Array(newLength);

  for (let i = 0; i < newLength; i++) {
    const idx = i * ratio;
    const before = Math.floor(idx);
    const after = Math.min(before + 1, buffer.length - 1);
    const at = idx - before;
    resampled[i] = buffer[before] + (buffer[after] - buffer[before]) * at;
  }
  return resampled;
}

function encodeWAV(buffer, sampleRate) {
  const length = buffer.length;
  const wavBuffer = new ArrayBuffer(44 + length * 2);
  const view = new DataView(wavBuffer);

  // WAV header
  writeString(view, 0, 'RIFF');
  view.setUint32(4, 36 + length * 2, true);
  writeString(view, 8, 'WAVE');
  writeString(view, 12, 'fmt ');
  view.setUint32(16, 16, true);       // Subchunk1Size
  view.setUint16(20, 1, true);        // PCM
  view.setUint16(22, 1, true);        // Mono
  view.setUint32(24, sampleRate, true); // SampleRate
  view.setUint32(28, sampleRate * 2, true); // ByteRate
  view.setUint16(32, 2, true);        // BlockAlign
  view.setUint16(34, 16, true);       // BitsPerSample
  writeString(view, 36, 'data');
  view.setUint32(40, length * 2, true);

  // PCM samples
  floatTo16BitPCM(view, 44, buffer);

  return new Blob([view], { type: 'audio/wav' });
}

function writeString(view, offset, str) {
  for (let i = 0; i < str.length; i++) {
    view.setUint8(offset + i, str.charCodeAt(i));
  }
}

function floatTo16BitPCM(view, offset, input) {
  for (let i = 0; i < input.length; i++, offset += 2) {
    let s = Math.max(-1, Math.min(1, input[i]));
    view.setInt16(offset, s < 0 ? s * 0x8000 : s * 0x7FFF, true);
  }
}

function makeLink(blob, filename) {
  const formData = new FormData();
  formData.append('uploadImg1', blob, filename);

  $.ajax({
    async: true,
    cache: false,
    type: "post",
    url: "/speech/peter/SpeechPeterInsert",
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
      $(".col").append("<p>" + response + "</p>");
    },
    error: function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
}
function ai() {
  const requestBody = {
      contents: [{
          "parts": [
              {"text": "판매하는 사료 이미지를 완제품으로 랜덤하게 그려줘"}
          ]
      }],
      "generationConfig":{"responseModalities":["TEXT","IMAGE"]}
  };
  fetch("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-preview-image-generation:generateContent?key=" + imageAiKey, {
  method: "POST",
  headers: {
      "Content-Type": "application/json"
  },
  body: JSON.stringify(requestBody)
  })
  .then(res => res.json())
  .then(data => { console.log(data);
      const image = "data:image/png;base64," + data.candidates[0].content.parts[1].inlineData.data;
      $("#zoompro").attr("src", image);
  });
}
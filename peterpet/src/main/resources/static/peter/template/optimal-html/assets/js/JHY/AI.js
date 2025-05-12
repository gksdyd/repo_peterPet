function ai(prodType, petType) {
    let text = "완제품 ";
    if (petType == 239) {
        text += "애완견 ";
    } else {
        text += "애완묘 ";
    }

    if (prodType == 1) {
        text += "사료 ";
    } else if (prodType == 2) {
        text += "간식 ";
    } else {
        text += "장난감 ";
    }
    text += "이미지를 랜덤하게 그려줘";
    
  const requestBody = {
      contents: [{
          "parts": [
              {"text": text}
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
  .then(data => {
      const image = "data:image/png;base64," + data.candidates[0].content.parts[1].inlineData.data;
      $("#zoompro").attr("src", image);
  });
}
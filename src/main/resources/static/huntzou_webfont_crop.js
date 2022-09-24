function cropFont(eleId, fontFamily, outputFormat) {
	var ele = document.getElementById(eleId)
	var cropText = [... new Set(ele.innerText)].join("")

	if (cropText == null || cropText === '' || cropText.trim() === '') return;
	fontFamily = fontFamily ? fontFamily : "MaShanZheng-Regular.ttf";
	outputFormat = outputFormat ? outputFormat : "woff";

	var fontface = new FontFace("huntzou_webfont", `url('https://webfont.woyou.cool/crop?crop_text=${cropText}&font_family=${fontFamily}&output_font_type=${outputFormat}')`, {})

	fontface.load().then(function(loadfont){
		document.fonts.add(loadfont);
		ele.style.fontFamily = "huntzou_webfont";
	})
}
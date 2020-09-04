$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});2

$(".medcine-info-list li a").on("click", function(e){
	e.preventDefault();
	
	var id = $(this).attr("id");
	console.log(id);
	var code = id.substring(6, id.length);
	console.log(code);
	ToptipAjax(code);
	$("#image1").ekkoLightbox();
	
});

function ToptipAjax(code){
	var tt_code = "tt_code="+code;
	$.ajax({
		url: "/beepPro/information/toptip_attach.ajax",
		data: tt_code,
		success: function(data){
			console.log("success");
			console.log(data);
			toptipModal(data);
		},
		error: function(data){
			console.log("꿀팁사진 error");
		},
		complete:function(){
			console.log("꿀팁사진 완료 후");
		}
	});
	
}

/*function toptipModal(data){
	let list = "";
	let content = "";
	$.each(data.list, function(index, item){
		console.log(index);
		console.log(item);
		if(index==0){
			list += "<li data-target='#carousel-example-generic' id="image1" data-slide-to='1' class='active'></li>";
		}
		else {
			list +="<li data-target='#carousel-example-generic' data-slide-to='"+(index+1)+"'></li>";
		}
		content += "<img src='"+item+"'>";
		
	});
	
	$(".carousel-indicators").empty();
	$(".carousel-indicators").html(list);   
	
	$(".item").empty();
	$(".item").html(content);
}*/
function toptipModal(data){
	let content = "";
	$.each(data.dto.list, function(index, item){
		if(index==0){
			content += "<a href='"+item+"' data-toggle='lightbox' id='image1' data-gallery='example-gallery' class='col-sm-4' data-title='> "+data.dto.title+"' </a>";
		}else{
			content += "<a href='"+item+"' data-toggle='lightbox' data-gallery='example-gallery' class='col-sm-4'></a>";
		}
	})
	$("#image-md").empty();
	$("#image-md").html(content);
}
























// 필터 적용시키기

var tool_code = "";
var category_id = "";
// 라디오 버튼이 바뀌면


$("select[name='category_id']").change(function(){
	$('#tool_code').val("");
	filter();
});	


function filter(){
	category_id = $("select[name='category_id']").val();
	tool_code = $('#tool_code').val();
	
	console.log(category_id);
	console.log(tool_code);
	
	
	$.ajax({
		url: '/admin/tool/list/filter',
		method: 'get',
		contentType: "application/json",
	    data: {
			"category_id": category_id,
			"tool_code": tool_code
		},    
		success : function(data) {
			// 원래 있던 item 삭제
			$(".item").remove();
			$("#total-num").text(data.length);
		
			console.log(data.length);
			console.log(data);
			if(data.length < 1){
				let str = '<div class="item no-data"><h2>일치하는 장비가 없습니다.</h2></div>'
				$(".list-con").append(str);	
			} else {
				data.forEach(function(tool, index){
					let str='<li class="item"><div class="info">';
					str += '<p class="item1">' + (index+1) + '</p>';
					str += '<div class="item2 imgbox"><img src="'+tool.tool_image+'" alt=""></div>';
					str += '<p class="item3">'+tool.category_name+'</p>';
					str += '<p class="item4">'+tool.tool_code+'</p>';
					str += '<p class="item5">'+tool.tool_name+'</p>';
					str += '<p class="item6">'+tool.tool_comment+'</p></div>';					
			        str += '<div class="btns">';
			        str += '<button class="btn" onclick="location.href=\'/admin/tool/form/' + tool.tool_id+ '\'">수정</button>';
			        str += '<button class="btn" onclick="deleteTool('+tool.tool_id+')">삭제</button>';
			        str += '</div></li>';          
          
            
					$(".list-con").append(str);				
				});
			}
		},
		error: function (data, status, err) {			
		},
	});

}
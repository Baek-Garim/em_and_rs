// 필터 적용시키기

var listType = "";
var listType = "";
var email = "";
var tool_code = "";
// 라디오 버튼이 바뀌면


$("input[name='listType']").click(function(){
	filter();
});	
$("select[name='sortType']").change(function(){
	filter();
});	


function filter(){
	listType = $("input[name='listType']:checked").val();
	sortType = $("select[name='sortType']").val();
	email = $('#email').val();
	tool_code = $('#tool_code').val();
	
	$.ajax({
		url: '/admin/rental/list/filter',
		method: 'get',
		contentType: "application/json",
	    data: {
			"listType": listType,
			"sortType": sortType,			
			"email": email,
			"tool_code": tool_code
		},    
		success : function(data) {
			// 원래 있던 item 삭제
			$(".item").remove();
			$("#total-num").text(data.length);
		
			console.log(data.length);
			/*console.log(data);*/
			if(data.length < 1){
				let str = '<tr class="item no-data"><td>결과가 없습니다.</td></tr>'
				$(".list-con").append(str);	
			} else {
				data.forEach(function(rental, index){
					let str='<tr class="item">';
					str += '<td>'+ (index+1) +'</td>';
					str += '<td>'+ rental.user_name +'</td>';
					str += '<td>'+ rental.email +'</td>';
					str += '<td>'+ rental.phone +'</td>';
					str += '<td>'+ rental.tool_code +'</td>';
					
					day = new Date(rental.rental_date);
					let rental_date = day.getFullYear() + '/' + (day.getMonth()+1) + '/' + day.getDate();
					str += '<td>'+rental_date+'</td>';
					
					day = new Date(rental.expected_return_date);
					rental_date = day.getFullYear() + '/' + (day.getMonth()+1) + '/' + day.getDate();
					str += '<td>'+rental_date+'('+ rental.d_day +'일)</td>';

					day = new Date(rental.return_date);
					rental_date = day.getFullYear() + '/' + (day.getMonth()+1) + '/' + day.getDate();
					str += '<td>'+rental_date+'</td>';

					
					
					str += '<td>'+ rental.renew +'회</td>';
					str += '<td>'+ rental.rental_state +'</td>';
					str += '</tr>';
            
					$(".list-con").append(str);				
				});
			}
		},
		error: function (data, status, err) {
			console.log(data);
			console.log(status);
			console.log(err);
		},
	});

}
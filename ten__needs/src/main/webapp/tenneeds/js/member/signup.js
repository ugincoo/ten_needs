// 1. 회원가입
function signup(){
	
	let signupForm = document.querySelectorAll('.signupForm')[0];
	let signupFormData = new FormData( signupForm );
	
	$.ajax({
		url : "/ten__needs/memberinfo" ,
		method : "post" ,
		data : signupFormData ,
		contentType : false ,			
		processData : false ,
		success : (r)=>{
			console.log('통신')
		}
		
	})
}


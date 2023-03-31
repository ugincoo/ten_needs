let memberInfo = null;
getlogin();


function getlogin(){

	$.ajax({
		url : "/ten__needs/login" ,
		method : "get" ,
		async : false,
		success :(r)=>{
			console.log('통신')
			console.log(r)
			memberInfo = r;
			
			console.log("memberInfo : " + memberInfo)
		}
	})
}

console.log(memberInfo);
let memberInfo = null;

getlogin();
function getlogin(){

	$.ajax({
		url : "/ten__needs/login" ,
		method : "get" ,
		success :(r)=>{
			console.log('통신')
			
			
		}
	})
}
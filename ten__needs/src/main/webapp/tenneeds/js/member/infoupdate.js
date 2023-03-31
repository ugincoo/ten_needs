
console.log(memberInfo)

if( memberInfo.mid == null ){
	alert('로그인이 필요합니다.')
	location.href = "/ten__needs/tenneeds/jsp/member/login.jsp"
}

document.querySelector('.mid').innerHTML = memberInfo.mid;
document.querySelector('.mphone').value = memberInfo.mphone;
document.querySelector('.memail').value = memberInfo.memail;
document.querySelector('.premimg').src = `/ten__needs/tenneeds/jsp/member/mimg/${memberInfo.mimg == null ? 'default.webp' : memberInfo.mimg }`;

function onchecked(){
	let check = document.querySelector('.defaultimg').checked;
	
	
	if(check){
      document.querySelector('.premimg').src = `/ten__needs/tenneeds/jsp/member/mimg/default.webp`;
    
   }else{
      document.querySelector('.premimg').src = `/ten__needs/tenneeds/jsp/member/mimg/` + memberInfo.mimg;
    
   }
			
		
}



function infoupdate(){
	
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData( updateForm );
	let defaultimg = document.querySelector('.defaultimg').checked
	updateFormData.set( "defaultimg" , defaultimg );
	
	$.ajax({
		url : "/ten__needs/member/info" ,
		method : "put" ,
		data : updateFormData ,
		contentType : false ,			
		processData : false ,
		success : (r)=>{
			if( r == 'true'){
				alert('수정이 완료되었습니다.');
				location.href = "/ten__needs/tenneeds/jsp/member/login.jsp";
			}else{
				alert('수정 실패 : 비밀번호를 확인해주세요.')
			}
		}
	})
}
// 2. 유효성 검사
let checkconfirm = document.querySelectorAll('.checkconfirm');
function mpwcheck(){
	let mpw = document.querySelector('.newmpw').value;
	let mpwj = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
	
	if( mpwj.test(mpw)){
		checkconfirm[0].innerHTML = '✓'
	}else{
		checkconfirm[0].innerHTML = '사용불가능한 비밀번호입니다.</br>[8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합]'
	}
}
function mphonecheck(){
	let mphone = document.querySelector('.mphone').value;
	let mphonej = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
	
	if( mphonej.test(mphone)){
		checkconfirm[1].innerHTML = '✓'
	}else{
		checkconfirm[1].innerHTML = '휴대폰 형식으로 입력해주세요.'
	}
}
function memailcheck(){
	let memail = document.querySelector('.memail').value;
	let memailj = /^[a-zA-Z0-9+-_-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/
	
	if( memailj.test(memail)){
		checkconfirm[2].innerHTML = '인증해주세요.'
		document.querySelector('.authbtn').disabled = false;
	}else{
		checkconfirm[2].innerHTML = '이메일 형식으로 입력해주세요.'
		document.querySelector('.authbtn').disabled = true;	
	}
}
// 이메일 인증
function getauth(){
	
	/* 실제 이메일 전송
	$.ajax({
		url : "/ten__needs/email" ,
		method : "post" ,
		data : { "memail" : document.querySelector('.memail').value } ,
		success : (r)=>{
			let html = `
				<div class="timebox"> 03 : 00 </div>
				<input type="text" class="authinput" placeholder="인증코드">
				<button onclick="authconfirm()" class="authconfirmbtn" type="button">확인</button>
				`	
			document.querySelector('.authbox').innerHTML = html;
			auth = r;
			timer = 180;
			settimer();
		}
	})
	*/
	
	let html = `
				<div class="timebox"> 03 : 00 </div>
				<input type="text" class="authinput" placeholder="인증코드">
				<button onclick="authconfirm()" class="authconfirmbtn" type="button">확인</button>
				`	
	document.querySelector('.authbox').innerHTML = html;
	auth = 1234;
	timer = 180;
	settimer();
}
let auth = 0;
let timer = 0;
let timerInter;
// 타이머
function settimer(){
	
	timerInter = setInterval( ()=>{
		let minutes = parseInt(timer/60);
		let seconds = parseInt(timer%60);
		
		minutes = minutes < 10 ? "0"+minutes : minutes;
		seconds = seconds < 10 ? "0"+seconds : seconds;
		
		let timehtml = minutes+" : "+seconds;
		
		document.querySelector('.timebox').innerHTML = timehtml;
		timer--;
		
		if( timer < 0 ){ 
			clearInterval(timerInter) 
			checkconfirm[2].innerHTML = '인증실패'
			document.querySelector('.authbox').innerHTML = '';
		}
	} , 1000 )
}
// 인증코드 
function authconfirm(){
	let authinput = document.querySelector('.authinput').value;
	
	if( auth == authinput ){
		clearInterval( timerInter );
		document.querySelector('.authbox').innerHTML = '';
		document.querySelector('.authbtn').innerHTML = '완료';
		document.querySelector('.authbtn').disabled = false;
		checkconfirm[2].innerHTML = '✓'
	}else{
		checkconfirm[2].innerHTML = '인증코드가 일치하지않습니다.'
	}
}

// 첨부파일 미리보기
function premimg( object ){
	
	let file = new FileReader();
	file.readAsDataURL( object.files[0] )
	file.onload = (e)=>{
		
		document.querySelector('.premimg').src = e.target.result;
	}
}

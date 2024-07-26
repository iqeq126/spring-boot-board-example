$().ready(function () {
    let uid;
    $("#JoinAlert").click(function () {
        let id = document.getElementById('username').value;
        let uid = document.getElementById('username').style.borderColor;
        let email = document.getElementById('email').style.borderColor;
        let pw1 = document.getElementById('password_origin').style.borderColor;
        // let pw2 = document.getElementById('password_copy').style.borderColor;
        let t1 = false;
        if(uid === 'blue') {
            if(uid === pw1 ){ t1 = true; }
        }
        if(t1 === false){
            Swal.fire({
                icon: 'error',
                title: '입력값이 조건에 맞지 않습니다.',
                text: '붉은 입력창을 제거해주세요',
            }).then((value)=>{
                location.href="join";
            });
        }
        else{
            Swal.fire({
                icon: 'success',
                title: id + '님의 회원가입이 완료되었습니다.',
                text: '환영합니다',
            }).then((value)=>{
                location.href="/user";
            });
        }
    });
    $( "#username" ).change( function() {
        let uid = document.idForm.username;
        let id_length = document.idForm.username.value.length;
        if (id_length === 0){
            uid.style.borderColor = 'lightgray';
        }
        else if (id_length >= 6 &&  id_length <= 20){
            uid.style.borderColor = 'blue';
        }
        else{
            uid.style.borderColor = 'red';
        }
    } );
    $( "#email" ).change( function() {
        let uid = document.idForm.email;
        let email_length = document.idForm.email.value.length;
        if (email_length === 0){
            uid.style.borderColor = 'lightgray';
        }
        else if (email_length >= 10 &&  email_length <= 40){
            uid.style.borderColor = 'blue';
        }
        else{
            uid.style.borderColor = 'red';
        }
    } );

    $( "#password_origin" ).change( function() {
        let upw = document.idForm.password_origin;
        let pw_length = document.idForm.password_origin.value.length;
        if (pw_length === 0){
            upw.style.borderColor = 'lightgray';
        }
        else if (pw_length >= 8 &&  pw_length <= 20){
            upw.style.borderColor = 'blue';
        }
        else{
            upw.style.borderColor = 'red';
        }
    } );
    /*
    $( "#password_copy" ).change( function() {
        let pw1 = document.idForm.password_origin.value;
        let pw2 = document.idForm.password_copy.value;
        if (pw1 === pw2){
            upw.style.borderColor = 'blue';
        }
        else{
            upw.style.borderColor = 'red';
        }
    } );
*/
});


// 뒤로 가기를 막는 함수
window.history.forward(); function noBack(){
    window.history.forward();
}

// 화면 전환 효과 : fade
document.addEventListener('DOMContentLoaded', () => {
    window.setTimeout(() => {
        document.body.classList.remove('fade');
    });
});
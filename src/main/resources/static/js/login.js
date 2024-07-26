$().ready(function () {
    let uid;
    $("#loginAlert").click(function () {
        uid = document.idForm.username.value;
        let id_length = document.idForm.username.value.length;
        let pw_length = document.pwForm.password.value.length;
        let t1 = false, t2 = false;
        if (id_length >= 6 && id_length <= 20){ t1 = true; } else { t1 = false; }
        if (pw_length >= 8 && pw_length <= 20){ t2 = true; } else { t2 = false; }
        if(t1 === false || t2 === false){
            Swal.fire({
                icon: 'error',
                title: '입력값이 조건에 맞지 않습니다.',
                text: '아이디/패스워드를 다시 입력해주세요',
            }).then((value)=>{
                location.href="login";
            });
        }
        else{
            Swal.fire({
                icon: 'success',
                title: uid + '님의 로그인이 완료되었습니다.',
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

    $( "#password" ).change( function() {
        let upw = document.idForm.password;
        let pw_length = document.idForm.password.value.length;
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
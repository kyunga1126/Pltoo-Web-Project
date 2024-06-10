document.addEventListener('DOMContentLoaded', function() {
    var idInput = document.getElementById('pswd1');
    var alertTxt = document.getElementById('alertTxt');
    var errorBox = document.querySelector('.error_next_box');
    var btnJoin = document.getElementById('btnJoin');

    // 입력 필드의 내용이 변경될 때마다 유효성 검사 수행
    idInput.addEventListener('input', function() {
        checkId(idInput.value);
    });

    // 확인 버튼 클릭 이벤트 처리
    document.addEventListener('DOMContentLoaded', function() {
        var nicknameForm = document.getElementById('nicknameForm');
        var idInput = document.getElementById('idInput');

        function checkId(nickname) {
            // 닉네임 유효성을 검사하는 로직을 여기에 추가하세요
            return nickname.length >= 2 && nickname.length <= 6;
        }

        nicknameForm.addEventListener('submit', function(event) {
            if (checkId(idInput.value)) {
                alert("입력한 닉네임은 사용 가능합니다: " + idInput.value);
                nicknameForm.submit(); // 입력이 유효하면 폼 제출
            } else {
                alert("닉네임이 유효하지 않습니다. 다시 입력해 주세요.");
                event.preventDefault();
            }
        });
    });

    function checkId(value) {
        var idPattern = /^[a-zA-Z0-9가-힣-_]{2,8}$/;
        if(value === "") {
            alertTxt.innerHTML = "필수 정보입니다.";
            alertTxt.style.display = "block";
            errorBox.style.display = "none";
            return false;
        } else if(!idPattern.test(value)) {
            alertTxt.innerHTML = "2~8자의 영문 소문자, 숫자만 사용 가능합니다.";
            alertTxt.style.display = "block";
            errorBox.style.display = "none";
            return false;
        } else {
            alertTxt.innerHTML = "멋진 아이디네요!";
            alertTxt.style.color = "#08A600";
            alertTxt.style.display = "block";
            errorBox.style.display = "none";
            return true;
        }
    }
});

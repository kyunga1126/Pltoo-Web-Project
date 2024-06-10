function toggleSearch() {
    var searchInput = document.getElementById('searchInput');
    if (searchInput.style.display === 'none') {
        searchInput.style.display = 'block';
    } else {
        searchInput.style.display = 'none';
    }
}

function toggleDropdown() {
    var dropdown = document.getElementById("dropdownMenu");
    if (dropdown.style.display === "none" || dropdown.style.display === "") {
        dropdown.style.display = "block";
    } else {
        dropdown.style.display = "none";
    }
}



/* 백엔드 구현 후 백엔드의 API를 프론트엔드로 가져요기? */


    // 데이터를 HTML에 적용하는 부분

    
  
    document.addEventListener('DOMContentLoaded', function() {
    var idInput = document.getElementById('nickname');
    var alertTxt = document.getElementById('alertTxt');
    var errorBox = document.querySelector('.error_next_box');
    var phoneInput = document.getElementById('phone');
    var phoneAlertTxt = document.getElementById('phoneAlertTxt');
    var phoneForm = document.getElementById('phoneForm');
    
        // 입력 필드의 내용이 변경될 때마다 유효성 검사 수행
        idInput.addEventListener('input', function() {
            checkId(idInput.value);
        });
    
        // 확인 버튼 클릭 이벤트 처리
        var nicknameForm = document.getElementById('nicknameForm');
    
        function checkId(nickname) {
            var idPattern = /^[a-zA-Z0-9가-힣-_]{2,8}$/;
            if (nickname === "") {
                alertTxt.innerHTML = "필수 정보입니다.";
                alertTxt.style.display = "block";
                alertTxt.style.color = "red";
                alertTxt.style.position = "absolute";
                alertTxt.style.zIndex = "100";
                alertTxt.style.top = (idInput.offsetTop + idInput.offsetHeight + 5) + "px";
                alertTxt.style.left = idInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return false;
            } else if (!idPattern.test(nickname)) {
                alertTxt.innerHTML = "2~8자의 영문 소문자, 숫자만 사용 가능합니다.";
                alertTxt.style.display = "block";
                alertTxt.style.color = "red";
                alertTxt.style.position = "absolute";
                alertTxt.style.zIndex = "100";
                alertTxt.style.top = (idInput.offsetTop + idInput.offsetHeight + 5) + "px";
                alertTxt.style.left = idInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return false;
            } else {
                alertTxt.innerHTML = "멋진 아이디네요!";
                alertTxt.style.color = "#08A600";
                alertTxt.style.display = "block";
                alertTxt.style.position = "absolute";
                alertTxt.style.zIndex = "100";
                alertTxt.style.top = (idInput.offsetTop + idInput.offsetHeight + 5) + "px";
                alertTxt.style.left = idInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return true;
            }
        }
    
        function checkPhone(phone) {
            var phonePattern = /^[0-9]{10,15}$/;
            if (phone === "") {
                phoneAlertTxt.innerHTML = "필수 정보입니다.";
                phoneAlertTxt.style.display = "block";
                phoneAlertTxt.style.color = "red";
                phoneAlertTxt.style.position = "absolute";
                phoneAlertTxt.style.zIndex = "100";
                phoneAlertTxt.style.top = (phoneInput.offsetTop + phoneInput.offsetHeight + 5) + "px";
                phoneAlertTxt.style.left = phoneInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return false;
            } else if (!phonePattern.test(phone)) {
                phoneAlertTxt.innerHTML = "10~15자의 숫자만 사용 가능합니다.";
                phoneAlertTxt.style.display = "block";
                phoneAlertTxt.style.color = "red";
                phoneAlertTxt.style.position = "absolute";
                phoneAlertTxt.style.zIndex = "100";
                phoneAlertTxt.style.top = (phoneInput.offsetTop + phoneInput.offsetHeight + 5) + "px";
                phoneAlertTxt.style.left = phoneInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return false;
            } else {
                phoneAlertTxt.innerHTML = "유효한 전화번호입니다!";
                phoneAlertTxt.style.color = "#08A600";
                phoneAlertTxt.style.display = "block";
                phoneAlertTxt.style.position = "absolute";
                phoneAlertTxt.style.zIndex = "100";
                phoneAlertTxt.style.top = (phoneInput.offsetTop + phoneInput.offsetHeight + 5) + "px";
                phoneAlertTxt.style.left = phoneInput.offsetLeft + "px";
                errorBox.style.display = "none";
                return true;
            }
        }
    
        // 닉네임 입력 필드의 내용이 변경될 때마다 유효성 검사 수행
        idInput.addEventListener('input', function() {
            checkId(idInput.value);
        });
    
        // 전화번호 입력 필드의 내용이 변경될 때마다 유효성 검사 수행
        phoneInput.addEventListener('input', function() {
            checkPhone(phoneInput.value);
        });
    
        // 닉네임 폼 제출 이벤트 처리
        document.getElementById('nicknameForm').addEventListener('submit', function(event) {
            event.preventDefault(); // 폼 제출을 막고 유효성 검사 수행
            if (checkId(idInput.value)) {
                alert("입력한 닉네임은 사용 가능합니다: " + idInput.value);
                nicknameForm.submit(); // 입력이 유효하면 폼 제출
            } else {
                alert("닉네임이 유효하지 않습니다. 다시 입력해 주세요.");
            }
        });
    
        // 전화번호 폼 제출 이벤트 처리
        phoneForm.addEventListener('submit', function(event) {
            event.preventDefault(); // 폼 제출을 막고 유효성 검사 수행
            if (checkPhone(phoneInput.value)) {
                alert("입력한 전화번호는 사용 가능합니다: " + phoneInput.value);
                phoneForm.submit(); // 입력이 유효하면 폼 제출
            } else {
                alert("전화번호가 유효하지 않습니다. 다시 입력해 주세요.");
            }
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        var nicknameElement = document.getElementById('original_nickname');
    
        // 서버에서 닉네임 데이터를 가져오는 함수
        function fetchNickname() {
            // AJAX 요청 (예: Fetch API 사용)
            fetch('/api/getNickname') // 서버 API 엔드포인트를 설정하세요
                .then(response => response.json())
                .then(data => {
                    // 서버로부터 데이터를 성공적으로 가져왔을 때 닉네임 업데이트
                    nicknameElement.textContent = data.nickname;
                })
                .catch(error => {
                    console.error('Error fetching nickname:', error);
                });
        }
    
        // 페이지 로드 시 닉네임 가져오기
        fetchNickname();
    });

    document.addEventListener('DOMContentLoaded', function() {
        var tierDisplay = document.getElementById('tierDisplay');
    
        // 서버에서 티어 이미지를 가져오는 함수
        function fetchTierImages() {
            // AJAX 요청 (예: Fetch API 사용)
            fetch('/api/getTierImages') // 서버 API 엔드포인트를 설정하세요
                .then(response => response.json())
                .then(data => {
                    // 서버로부터 데이터를 성공적으로 가져왔을 때 이미지를 업데이트
                    data.tierImages.forEach(imageUrl => {
                        var img = document.createElement('img');
                        img.src = imageUrl;
                        img.alt = "Tier Image";
                        img.style.width = "100px"; // 이미지 크기를 적절히 조절하세요
                        img.style.margin = "10px"; // 이미지 간의 간격을 조절하세요
                        tierDisplay.appendChild(img);
                    });
                })
                .catch(error => {
                    console.error('Error fetching tier images:', error);
                });
        }
    
        // 페이지 로드 시 티어 이미지 가져오기
        fetchTierImages();
    });
document.addEventListener('DOMContentLoaded', function() {
    const addFriendButton = document.getElementById('addFriendButton');
    const friendId = addFriendButton.getAttribute('data-friend-id'); // 프로필의 멤버 ID
    const userId = /*[[${#request.remoteUser}]]*/ 0; // 현재 로그인한 사용자의 ID

    addFriendButton.addEventListener('click', function() {
        fetch(`/api/friends/add?userId=${userId}&friendId=${friendId}`, {
            method: 'POST'
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('친구 추가 성공!'); // 팝업창
                } else {
                    alert('친구 추가 실패: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('친구 추가 중 오류가 발생했습니다.');
            });
    });
    // 친구 목록. 사용자id를 매개변수로 받아서 해당 사용자의 친구목록 반환.
    function loadFriendList(userId) {
        fetch(`/api/friends/list?userId=${userId}`)
            .then(response => response.json())
            .then(friends => {
                const friendList = document.getElementById('friendList');
                friendList.innerHTML = '';
                friends.forEach(friend => {
                    const li = document.createElement('li');
                    li.textContent = friend.nickname;
                    friendList.appendChild(li);
                });
            })
            .catch(error => {
                console.error('Error loading friends:', error);
            });
    }
});
// 현재 사용자의 이메일 가져오기.
function getCurrentMemberEmail() {
    // 서버에서 세션을 통해 현재 사용자 이메일을 가져오는 방법
    return fetch('http://localhost:8080/api/members/current')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to get current member email');
            }
            return response.json();
        })
        .then(data => data.memberEmail)
        .catch(error => {
            console.error('Error:', error);
            return null;
        });
}

//URL에서 멤버 이메일을 가져오는 함수
function getMemberEmailFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('memberEmail');
}

// 멤버 프로필
function showMemberProfile(memberEmail) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://localhost:8080/api/members/${memberEmail}`, true);

    xhr.onload = function() {
            const data = JSON.parse(xhr.responseText);

            document.getElementById('profile_img').src = data.profileImage;
            document.getElementById('nickname').textContent = data.nickname;
            document.getElementById('age').textContent = data.age;
            document.getElementById('profile_description').textContent = data.profile;

            loadMemberGames(memberEmail);
            loadMemberFriends(memberEmail);
    };

    xhr.send();
}

// 하는 게임 목록 가져오기
function loadMemberGames(memberEmail) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://localhost:8080/api/members/${memberEmail}/games`, true);

    xhr.onload = function() {
            const games = JSON.parse(xhr.responseText);
            const gameListDiv = document.getElementById('gameList');
            gameListDiv.innerHTML = '';
            games.forEach(game => {
                const img = document.createElement('img');
                img.src = game.imageUrl;
                img.alt = game.gameName;
                gameListDiv.appendChild(img);
            })
    };

    xhr.send();
}


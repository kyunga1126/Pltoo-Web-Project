document.addEventListener('DOMContentLoaded', function() {
    const userId = getUserIdFromURL();
    showUserProfile(userId);

    // "친구 할래?" 버튼 클릭 시 친구 추가
    document.querySelector('.btnText2').addEventListener('click', addFriend);

    function addFriend() {
        getCurrentUserId()
            .then(userId => {
                const friendId = getUserIdFromURL(); // URL에서 친구 ID를 가져옴

                // API를 호출하여 친구 추가 요청
                return fetch(`http://localhost:8080/api/friends/add?userId=${userId}&friendId=${friendId}`, {
                    method: 'POST',
                });
            })
            .then(response => {
                if (response.ok) {
                    alert("친구 추가 완료!");
                    return getCurrentUserId();
                } else {
                    throw new Error('친구 추가 실패');
                }
            })
            .then(userId => loadUserFriends(userId)) // 친구 목록 갱신
            .then(userId => loadUserFriends(userId)) // 친구 목록 갱신
            .catch(error => {
                console.error('Error adding friend:', error);
                alert("친구 추가에 실패했습니다.");
            });
    }
});

function getCurrentUserId() {
    // 서버에서 세션을 통해 현재 사용자 ID를 가져오는 방법
    return fetch('http://localhost:8080/api/users/current')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to get current user ID');
            }
            return response.json();
        })
        .then(data => data)
        .catch(error => {
            console.error('Error:', error);
            return null;
        });
}

function getUserIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('userId');
}

function showUserProfile(userId) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://localhost:8080/api/users/${userId}`, true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);

            document.getElementById('profile_img').src = data.profileImage;
            document.getElementById('nickname').textContent = data.nickname;
            document.getElementById('age').textContent = data.age;
            document.querySelector('.profile').textContent = data.profile;

            loadUserGames(userId);
            loadUserFriends(userId);
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };

    xhr.send();
}

function loadUserGames(userId) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://localhost:8080/api/users/${userId}/games`, true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            const games = JSON.parse(xhr.responseText);
            const gamePage = document.querySelector('.gamePage');
            gamePage.innerHTML = games.map(game => `<img src="${game.imageUrl}" alt="${game.gameName}">`).join('');
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };

    xhr.send();
}

function loadUserFriends(userId) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://localhost:8080/api/users/${userId}/friends`, true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            const friends = JSON.parse(xhr.responseText);
            const friendList = document.querySelector('.friend_list');
            friendList.innerHTML = friends.map(friend => `<div>${friend.friend.nickname}</div>`).join('');
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };

    xhr.send();
}

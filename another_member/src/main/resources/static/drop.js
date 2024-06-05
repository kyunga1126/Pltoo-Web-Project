document.addEventListener('DOMContentLoaded', function() {
    const userId = getUserIdFromURL();
    showUserProfile(userId);

    // Add event listener to "친구 할래?" button
    document.querySelector('.button .btnText').addEventListener('click', function() {
        const friendId = prompt('추가할 친구의 ID를 입력하세요:');
        if (friendId) {
            addFriend(userId, friendId);
        }
    });
});

function showUserProfile(userId) {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `http://your-backend-api.com/api/users/${userId}`, true);

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

    xhr.open('GET', `http://your-backend-api.com/api/users/${userId}/games`, true);

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

    xhr.open('GET', `http://your-backend-api.com/api/users/${userId}/friends`, true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            const friends = JSON.parse(xhr.responseText);
            const friendList = document.querySelector('.friend_list');
            friendList.innerHTML = friends.map(friend => `<div>Friend ID: ${friend.friendId}</div>`).join('');
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };

    xhr.send();
}

function addFriend(userId, friendId) {
    const xhr = new XMLHttpRequest();

    xhr.open('POST', `http://your-backend-api.com/api/users/${userId}/friends`, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        if (xhr.status === 200) {
            loadUserFriends(userId);
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };

    xhr.send(`friendId=${friendId}`);
}

function getUserIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('userId');
}
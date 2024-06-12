document.addEventListener('DOMContentLoaded', () => {
  const postForm = document.getElementById('postForm');

  postForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;
    const attachment = document.getElementById('attachment').files[0]; // 파일 첨부

    // 여기서 게시글을 서버로 전송하는 로직을 작성합니다.
    // 이 예제에서는 간단히 콘솔에 출력하는 것으로 대체합니다.
    console.log('Title:', title);
    console.log('Content:', content);
    console.log('Attachment:', attachment);

    // 게시글 등록 후 필요한 작업을 수행합니다.
    // 예를 들어, 등록이 성공하면 다른 페이지로 리디렉션할 수 있습니다.
  });
});

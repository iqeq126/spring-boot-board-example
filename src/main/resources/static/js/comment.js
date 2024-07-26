// 입력 기능
/*const commentButton = document.getElementById('comment-btn');

if (commentButton) {
    commentButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/articles/${id}/comment`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('입력이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}*/

function editComment(button) {
    const commentId = button.getAttribute('data-id');
    const commentElement = document.getElementById('comment-' + commentId);
    const commentContent = commentElement.textContent;

    const newContent = prompt('Edit your comment:', commentContent);
    if (newContent) {
        $.post(`/articles/${id}/comment/${commentId}/update`, {content: newContent}, function(data) {
            window.location.href = data;
        });
    }
}
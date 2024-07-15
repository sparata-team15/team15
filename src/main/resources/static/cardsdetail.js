document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const cardId = urlParams.get('cardId');
    const userId = document.getElementById('userId').value;

    if (!cardId) {
        alert('카드 ID를 찾을 수 없습니다.');
        return;
    }

    function fetchComments(page = 0) {
        fetch(`/comments?cardId=${cardId}&page=${page}`)
            .then(response => response.json())
            .then(data => {
                const commentList = document.getElementById('commentList');
                commentList.innerHTML = '';
                data.content.forEach(comment => {
                    const commentDiv = document.createElement('div');
                    commentDiv.classList.add('comment');
                    commentDiv.innerHTML = `
                        <p class="mb-2">${comment.content}</p>
                        <p class="text-gray-600 text-sm">${comment.author}</p>
                        <button class="delete-comment text-red-500" data-id="${comment.commentId}">삭제</button>
                    `;
                    commentList.appendChild(commentDiv);
                });

                document.querySelectorAll('.delete-comment').forEach(button => {
                    button.addEventListener('click', function() {
                        const commentId = this.getAttribute('data-id');
                        deleteComment(commentId);
                    });
                });
            });
    }

    function submitComment() {
        const content = document.getElementById('commentContent').value;
        fetch('/comments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                content: content,
                userId: userId,
                cardId: cardId
            })
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('commentContent').value = '';
                fetchComments();
            });
    }

    function deleteComment(commentId) {
        fetch(`/comments/${commentId}?cardId=${cardId}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                fetchComments();
            });
    }

    document.getElementById('submitComment').addEventListener('click', submitComment);

    fetchComments();
});
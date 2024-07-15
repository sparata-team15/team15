document.addEventListener("DOMContentLoaded", function() {
    const cardId = 1;

    // 댓글 조회
    function fetchComments(page = 0) {
        fetch(`/comments?cardId=${cardId}&page=${page}`)
            .then(response => response.json())
            .then(data => {
                const commentList = document.getElementById('commentList');
                commentList.innerHTML = '';
                data.content.forEach(comment => {
                    const commentDiv = document.createElement('div');
                    commentDiv.classList.add('mb-4', 'p-4', 'border', 'border-gray-300', 'rounded');
                    commentDiv.innerHTML = `
            <p class="mb-2">${comment.content}</p>
            <p class="text-gray-600 text-sm">${comment.author}</p>
            <button class="delete-comment text-red-500" data-id="${comment.id}">삭제</button>
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

    // 댓글 생성
    function submitComment() {
        const content = document.getElementById('commentContent').value;
        fetch('/comments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                content: content,
                cardId: cardId
            })
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('commentContent').value = '';
                fetchComments();
            });
    }

    // 댓글 삭제
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
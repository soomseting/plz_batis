$(document).ready(function () {
    // 첫 번째 행 클릭 시 상세 페이지로 이동
    document.querySelector('.question').addEventListener('click', function () {
        window.location.href = 'qna_details.html'; // 상세 페이지로 이동
    });

});
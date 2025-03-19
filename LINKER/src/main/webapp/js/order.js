$(document).ready(function(){
function updateOrderStatus(selectElement) {
    var newStatus = selectElement.value;
    var confirmation = confirm("주문 상태를 '" + newStatus + "'로 변경하시겠습니까?");
    
    if (confirmation) {
        // 상태 변경 로직 처리
        alert("주문 상태가 " + newStatus + "로 변경되었습니다.");
    } else {
        // 취소 로직 (기존 상태 유지)
        selectElement.value = previousStatus; // 이전 상태로 복원
    }
}

var currentPage = 1;
var totalPages = 10; // 예시로 총 10페이지가 있다고 가정

function goToFirstPage() {
    currentPage = 1;
    updatePage();
}

function goToPreviousPage() {
    if (currentPage > 1) {
        currentPage--;
        updatePage();
    } else {
        alert("이전 페이지가 없습니다.");
    }
}

function goToNextPage() {
    if (currentPage < totalPages) {
        currentPage++;
        updatePage();
    }
}

function goToLastPage() {
    currentPage = totalPages;
    updatePage();
}

function updatePage() {
    document.getElementById('currentPage').textContent = currentPage;
    // 페이지 이동에 따른 추가 로직을 여기에 추가할 수 있습니다 (예: 서버로 요청 보내기).
}

});
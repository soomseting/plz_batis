$(document).ready(function(){
document.addEventListener("DOMContentLoaded", function () {
    var statusSelect = document.querySelector(".product-status");

statusSelect.addEventListener("change", function (event) {
    var selectedValue = this.value; // 변경된 값 저장
    var previousValue = this.getAttribute("data-prev-value") || "판매중"; // 이전 값 (초기값: 제작중)

    var isConfirmed = confirm("상품 상태를 " + selectedValue + "으로 변경하시겠습니까?");

    if (isConfirmed) {
        this.setAttribute("data-prev-value", selectedValue); // 변경 확정 시 저장
        alert("상품 상태가 " + selectedValue + "으로 변경되었습니다.");
    } else {
        this.value = previousValue; // 취소 시 원래 값으로 복원
        }
});

// 초기값 저장
document.querySelectorAll(".order-status").forEach(select => {
    select.setAttribute("data-prev-value", select.value);
    });
});

// 전체 선택 체크박스
function toggleSelectAll() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll(".product-checkbox");
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
}

// 선택된 상품 삭제 기능
function deleteSelectedProducts() {
    var selectedCheckboxes = document.querySelectorAll(".product-checkbox:checked");
    if (selectedCheckboxes.length == 0) {
        alert("삭제할 상품을 선택해 주세요.");
        return;
    }

    var productIdsToDelete = [];
    selectedCheckboxes.forEach(checkbox => {
        productIdsToDelete.push(checkbox.value); // 체크된 상품 ID 저장
    });

    var isConfirmed = confirm("선택된 상품을 삭제하시겠습니까?");
    if (isConfirmed) {
        // 삭제 요청을 서버로 보낼 수 있습니다 (예: AJAX 요청)
        alert("상품 ID " + productIdsToDelete.join(", ") + "가 삭제되었습니다.");
        // 서버에서 삭제 작업 후 페이지 새로고침 또는 목록 갱신
        // 예시: location.reload();  // 페이지 새로고침
    }
}

let currentPage = 1;
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
        alert("이전 페이지가 없습니다.")
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
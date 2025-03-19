$(document).ready(function () {
document.addEventListener("DOMContentLoaded", function() {
    loadCart();
});

// 장바구니 상태 로드 함수
function loadCart() {
    var cartItems = JSON.parse(localStorage.getItem("cartItems"));
    var emptyCart = document.getElementById("empty-cart");
    var filledCart = document.getElementById("filled-cart");

    if (cartItems && cartItems.length > 0) {
        emptyCart.classList.add("hidden");
        filledCart.classList.remove("hidden");
        updateCart(cartItems);
    } else {
        emptyCart.classList.remove("hidden");
        filledCart.classList.add("hidden");
    }
}

/*main 이동함수*/
function goToMainPage() {
    window.location.href = "/main.html"; // 메인 페이지로 이동
}

// 장바구니 업데이트 함수
function updateCart(cartItems) {
    var table = document.querySelector(".cart-list");
    var totalPrice = 0;
    var rowCount = table.rows.length;

    // 기존 상품 삭제
    for (var i = rowCount - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    // 장바구니 상품 표시
    for (var i = 0; i < cartItems.length; i++) {
        var item = cartItems[i];
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);

        cell1.innerHTML = '<input type="checkbox" class="item-check" onclick="updateTotal()"> ' + item.name;
        cell2.innerText = item.quantity;
        cell3.innerText = item.price + "원";
        cell4.innerHTML = '<button class="delete-btn" onclick="deleteItem(this)">삭제</button>';
        totalPrice += item.price;
    }

    // 총액 갱신
    document.getElementById("total-price").innerText = "0원";
}

// 전체 선택 체크박스
function toggleSelectAll() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll(".item-check");
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
    updateTotal(); // 전체 선택 시에도 총액을 업데이트
}

// 상품 선택 시 총액 업데이트
function updateTotal() {
    var checkboxes = document.querySelectorAll(".item-check:checked");
    var totalPrice = 0;

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        var row = checkbox.closest("tr");
        var priceText = row.querySelector(".item-price").innerText.replace("원", "").replace(",", "");
        totalPrice += parseInt(priceText);
    }

    // 총액 갱신
    document.getElementById("total-price").innerText = totalPrice.toLocaleString() + "원";
}

// 상품 삭제 함수
function deleteItem(button) {
    var confirmDelete = confirm("상품을 삭제하시겠습니까?");
    if (confirmDelete) {
        var row = button.closest("tr");
        var itemPrice = row.querySelector(".item-price").innerText.replace("원", "").replace(",", "");
        var itemName = row.querySelector(".item-check").nextSibling.textContent.trim();

        // 로컬스토리지에서 상품 삭제
        var cartItems = JSON.parse(localStorage.getItem("cartItems"));
        cartItems = cartItems.filter(function(item) {
            return item.name !== itemName;
        });
        localStorage.setItem("cartItems", JSON.stringify(cartItems));

        // 장바구니 업데이트
        loadCart();
        updateTotal(); // 상품 삭제 후 총액 업데이트
    }
}

// 장바구니에 상품 추가하는 함수 예시
function addItemToCart(item) {
    var cartItems = JSON.parse(localStorage.getItem("cartItems")) || [];
    cartItems.push(item);
    localStorage.setItem("cartItems", JSON.stringify(cartItems));
    loadCart();
}

});
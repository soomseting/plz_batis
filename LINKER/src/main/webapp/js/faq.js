$(document).ready(function () {
var buttons = document.querySelectorAll('.faq-question');

for(var i = 0; i < buttons.length; i++){
    buttons[i].addEventListener('click', function() {
        var answer = this.nextElementSibling;
        answer.classList.toggle('open');
    });
}

});
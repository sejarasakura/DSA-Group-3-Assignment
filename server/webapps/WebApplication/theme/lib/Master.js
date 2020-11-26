// A $( document ).ready() block.
$(document).ready(function () {
    $(".art-list").click(function () {
        $('.art-list').each(function (i, obj) {
            $(this).removeClass("active");
        });
        $(this).addClass("active");
    });
});

$.validator.addMethod("pwcheck", function(value) {
   return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
       && /[a-z]/.test(value) // has a lowercase letter
       && /[A-Z]/.test(value) // has a lowercase letter
       && /\d/.test(value); // has a digit
});
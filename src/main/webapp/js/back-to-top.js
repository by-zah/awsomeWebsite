$(function() {
  $(window).scroll(function() {
    if($(this).scrollTop() >= 1000) {
      $('#back-to-top-button').fadeIn();
    } else {
      $('#back-to-top-button').fadeOut();
    }
  }
);
$('#back-to-top-button').click(function() {
  $('body,html').animate({scrollTop:0},700);
});
});
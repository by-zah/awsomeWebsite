$(document).ready(function () {
    $("tr td input").mouseleave(
        function () {
            alert($(this).attr("productId"));
            $.get('cart', {
                productId: $(this).attr("productId"),
                amount: $(this).val()
            }, function (responseJSON) {
                var sum = '#sum' + dataId;
                $(sum).html(responseJSON.sum);
                $('#cartSum').html(responseJSON.total);
                $("#cartCount").text(responseJSON.dataCount);
            });
        });
    $(".btn i").on('click',
        function () {
            var dataId = $(this).attr("productId");
            $.get('cart', {
                amount: 0,
                productId: $(this).attr("productId")
            }, function (responseJSON) {
                if (dataId > 0) {
                    $('#' + dataId).remove();
                } else {
                    $('tbody').remove();
                }
                $('#cartSum').html(responseJSON.total);
                $("#cartCount").text(responseJSON.dataCount);
            });
        });
})
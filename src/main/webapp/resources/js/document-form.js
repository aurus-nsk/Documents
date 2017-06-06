$(document).ready(function () {
    $("#document_form").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
	var arr = document.forms["document_form"].getElementsByTagName("input");
	
	var result = [];
	Array.from(arr).forEach(function(item, i, arr) {
		var val = 'empty';
		if(item.value != false) {
			val = item.value;
		}
		result.push(item.id+':'+val);
	});

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/upload",
        data: JSON.stringify(result),
        dataType: 'text',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = '<div class="alert alert-success" role="alert">' + data + '<a id="link-index" href="/"><h1>Заполнить новый документ</h1></a></div>';
            
            $('#result_msg').html(json);
            window.scrollTo(0,document.body.scrollHeight);
            console.log("SUCCESS : ", data);
        },
        error: function (data) {
        	var json = '<div class="alert alert-danger" role="alert">' + data + '</div>';
            $('#result_msg').html(json);
            console.log("ERROR : ", data);
        }
    });
}
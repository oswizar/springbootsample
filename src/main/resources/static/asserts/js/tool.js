function login() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "submit" ,//url
        data: $('#form1').serialize(),
        success: function (data) {
            console.log(data);//打印服务端返回的数据(调试用)
            if (data.resultCode == 200) {
                alert("SUCCESS");
            };
        },
        error : function() {
            alert("异常！");
        }
    });
}

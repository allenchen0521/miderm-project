let requestUrl = "/miderm-project/";
let clickArray = new Array();
let pageRow = 10;
let pageRowStart = 0;
let pageRowEnd = 10;
let tododata;
let now = 1;

function init() {
    let txt = "<thead><tr><th>會員編號<th>會員帳號<th>會員名稱<th>會員等級<th>編輯會員資料<th>刪除會員資料</tr></thead>";
    members = tododata;
    
    // 每頁最多筆數 ex: 第一頁最大筆數為10
    let maxRow = now * pageRow;

    // 每頁開始筆數的索引 ex: 第一頁開始筆數的索引為 0
    pageRowStart = (now - 1) * pageRow;
    
    // 計算每頁結束的筆數
    pageRowEnd = (members.length < maxRow) ? members.length : maxRow;
    console.log("Start:" + pageRowStart + " End:" + pageRowEnd);

    for (let i = pageRowStart; i < pageRowEnd; i++) {
        txt += "<tr><td>" + members[i].mem_id;
        txt += "<td class='searchText'>" + members[i].mem_username;
        txt += "<td>" + members[i].mem_name;
        txt += "<td>" + members[i].mem_level;
        txt += "<td><a class='modifyData' href='" + requestUrl + "admin/UpdateMemberServlet.do?mem_id=" + members[i].mem_id + "'>編輯</a>";
        txt += "<td><a class='deleteData' href='" + requestUrl + "admin/DeleteMemberServlet.do?mem_id=" + members[i].mem_id + "'>刪除</a>";
    }
    $("#demo").html(txt);

    $(".modifyData, .deleteData").fancybox({
        type:'ajax'
        ,overlayColor:'#000'
        ,overlayOpacity:0.8
    });

    // 取得頁數
    let pageNum = Math.ceil(members.length / pageRow);
    for (var i = 0; i < pageNum; i++) {
        let ulElement = $("#show");
        ulElement.append("<li><a href='javascript:;'>" + (i + 1) + "</a></li>");
    }

    $("#show li").click(function () {
        // 頁數起始筆數 結束筆數
        console.log($(this).index());
        now = $(this).index() + 1;
        console.log("目前頁數:" + now);
        $("#demo").html("");
        $("#show").html("");
        init(); 
    });
}

$.ajax({
    url: "http://localhost:8080/" + requestUrl + "/admin/query_member.jsp",
    method: "post",
    dataType: "JSON",
    success: function (data) {
    	tododata = data;
        init();
    }
});

let intervalId = setInterval(function() {
	$.ajax({
	    url: "http://localhost:8080/" + requestUrl + "/admin/query_member.jsp",
	    method: "post",
	    dataType: "JSON",
	    success: function (res) {
	    	tododata = res;
	    	$("#demo").html("");
            $("#show").html("");
            init();
	    }
	});
}, 3000);
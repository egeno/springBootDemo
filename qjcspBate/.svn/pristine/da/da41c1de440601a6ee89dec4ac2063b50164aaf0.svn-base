$(function(){
		province("");
		setTimeout(function(){
			var cityCode = $('#cityCode').combobox('getValue');
			/*if(cityCode != null){
				area(cityCode, $('#cityName').val(), $('#areaCode').combobox('getValue'), $('#areaName').val());
			}*/
			var provCode = $('#provCode').combobox('getValue');
			
			$('#companyName').combobox('setValue', localStorage.getItem("companyName"));
			localStorage.removeItem("companyName");
			/*$("#companyName").combobox(localStorage.getItem("companyName"));*/
			if(provCode != null){
				city(provCode, $('#province').val(), $('#cityCode').combobox('getValue'), $('#cityName').val());
			}
		},300);
	});
	
/*var btsloader = function(param,success,error){
		  
		  //获取输入的值
		  var q = param.q || "";
		  
		  //此处q的length代表输入多少个字符后开始查询
		  if(q.length <= 0) return false;
		  $.ajax({
		  url:"merchant/fuzzyQuery",
			type : "post",
			data : {
				//传值，还是JSON数据
				searchName : q
			},

			//重要，如果写jsonp会报转换错误，此处不写都可以
			dataType : "json",
			success : function(data) {

				//关键步骤，遍历一个MAP对象
				var items = $.map(data.rows, function(item) {
					return {
						id : item.id,
						name : item.text
					};
				});

				//执行loader的success方法
				success(items);
			},

			//异常处理
			error : function(xml, text, msg) {
				error.apply(this, arguments);
			}
		});
	};*/


//android专用
var time ; //time用于显示图片时用 
//var task_upload_url = "http://weiweizhe.com/updata.do";//上传的url  定义在了外面
//android专用


var img_index = 1 ; //这个index是id=""  删除的时候要用到


function showDlg(){
	mui('#picture').popover('toggle');
}


//拍照
function makePhone(){
	var cmr = plus.camera.getCamera();  
    cmr.captureImage(function(p) {  
        plus.io.resolveLocalFileSystemURL(p, function(entry) {
        	compressImage(entry.toLocalURL(),entry.name);//压缩图片
        }, function(e) {
           alert("读取拍照文件错误：" + e.message);  
        });  
    }, function(e) {  
    }, {  
        filename: "_doc/camera/",  
        index: 1  
    });  
}

//选择相册
function selectPhone(){
	  plus.gallery.pick( function(p){  
          plus.io.resolveLocalFileSystemURL(p, function(entry) {  
        	  compressImage(entry.toLocalURL(),entry.name);//压缩图片
     }, function(e) {  
         plus.nativeUI.toast("读取拍照文件错误：" + e.message);  
     });  
      }, function ( e ) {  
      }, {  
     filename: "_doc/camera/",  
     filter:"image"  
      } );  
}



//压缩图片  
function compressImage(url,filename){
	 //显示正在上传附件  进度条
	  layer.open({
	    type: 2
	    ,content: '正在上传附件'
	    ,shadeClose:false
	  });
	 
	getTime('yyyyMMdd');
	mui('#picture').popover('toggle');
    var name="_doc/upload/"+filename;//_doc/upload/1467602809090.jpg  
    plus.zip.compressImage({  
            src:url,//src: (String 类型 )压缩转换原始图片的路径  
            dst:name,//压缩转换目标图片的路径  
            quality:100,//quality: (Number 类型 )压缩图片的质量.取值范围为1-100  
            overwrite:true//overwrite: (Boolean 类型 )覆盖生成新文件  
        },  
        function(event) {
            //uploadf(event.target,divid);  
            var path = name;//压缩转换目标图片的路径  
            uploadimge(filename,path,'comment');
        },function(error) {  
            layer.closeAll();
            
            //提示
            layer.open({
              content: '压缩图片失败，请稍候再试'
              ,skin: 'msg'
              ,time: 2 //2秒后自动关闭
            });
            
    });  
}



//上传图片，fileName图片名称 123.jpg    
//path路径_doc/upload/1467602809090.jpg       folder上传到哪个文件夹
function uploadimge(fileName,path,folder){
	
	var task = plus.uploader.createUpload(task_upload_url,{method: "POST"},
    function(t,status){
        if (status == 200){
        	layer.closeAll();
        	
        	//信息框
            layer.open({
              content: '附件上传成功'
              ,btn: '我知道了'
            });
        	
            urlToImg($.trim('/static/images/comment/'+time+'/'+fileName));
            
        }else{
        	layer.closeAll();
        	
        	//提示
            layer.open({
              content: '上传失败'
              ,skin: 'msg'
              ,time: 2 //2秒后自动关闭
            });
            
        }
    });
	task.addData('folder',time);
	task.addFile(path,{key:path});
	task.start();
}



function getTime(format){
	$.post("/public/getTime",{format:format},function(result){
		time =  result.msg;
	},'json');
}
//android专用



function uploadFile(file){
	//显示正在上传附件  进度条
	  layer.open({
	    type: 2
	    ,content: '正在上传附件'
	    ,shadeClose:false
	  });
	
    $.ajaxFileUpload({
        url : '/admin/renwu/addImage.do', //用于文件上传的服务器端请求地址  
        secureuri : false, //一般设置为false
        fileElementId : 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />  
        type : 'post',  
        dataType : 'text', //返回值类型 一般设置为json
        data:{test1:"1",test2:"2"},//附加参数，json格式  
        success : function(result) //服务器成功响应处理函数  
        {  
            var result=eval('('+result+')');
            if(result.success){
            	layer.closeAll();
                
                //信息框
                layer.open({
                  content: '附件上传成功'
                  ,btn: '我知道了'
                });
                
                
                urlToImg(result.url);
            }
        },
        error : function(result)//服务器响应失败处理函数  
        {  
        }
    });
    return false;
}



function urlToImg(url){
	 $("#imgList").prepend('<div id="'+img_index+'" class="item_img_div" style="overflow: visible;">'+
     		 '<img  src="'+url+'" origin="'+url+'" />'+
     			 '<div onclick="delImg('+img_index+',\''+url+'\')" style="position: absolute; width: 18px; height: 18px; top: -10px; right:-12px;  ">'+
     			 	'<img  src="/static/images/base/black_close.png"  />'+
     			 '</div>'+
     		'</div>');
  img_index++;
}


function delImg(img_index,url){
	  //显示正在上传附件  进度条
	  layer.open({
	    type: 2
	    ,content: '正在删除'
	    ,shadeClose:false
	  });
	 
	$.post("/admin/renwu/delImage",{url:url},function(result){
		if(result.success){
			//提交成功 设置按钮不可用 disabled="disabled"
			 
			//移除 url匹配的图片
			$("#"+img_index+"").remove();
			layer.closeAll();
			
			 //提示
			  layer.open({
			    content: '已删除'
			    ,skin: 'msg'
			    ,time: 2 //2秒后自动关闭
			  });
			 
			 
		}else{
			  //信息框
			  layer.open({
			    content: result.msg
			    ,btn: '我知道了'
			  });
		}
	},'json');
}



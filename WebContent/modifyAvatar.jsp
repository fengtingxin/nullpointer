<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en-gb" dir="ltr" class="uk-notouch"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 头像上传  -->
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div>
                                <div class="demo">
                                <div class="bheader"><h2>上传头像</h2></div>	
                                <div class="bbody">
                                <!-- upload form -->
                                 <form id="upload_form" enctype="multipart/form-data" method="post"
                                  action="${ctx}/userimg/upload" target="_parent" onsubmit="return checkForm()">
                                 <!-- hidden crop params -->
                                 <input type="hidden" id="x1" name="x1" />
                                 <input type="hidden" id="y1" name="y1" />
                                 <input type="hidden" id="x2" name="x2" />
                                 <input type="hidden" id="y2" name="y2" />
                                 <h3>请选择要上传的头像</h3>
                                 <div><input type="file" name="image_file" id="image_file" onchange="fileSelectHandler()" /></div>

                                 <div class="error"></div>

                                 <div class="step2" >
                                 <h2>编辑您的头像</h2>
                                 <img id="preview" />
 
                                 <div class="info">
                            
                                       <label>类型</label> <input type="text" id="filetype" name="filetype" />
                                       <label>长</label> <input type="text" id="w" name="w" />
                                       <label>高</label> <input type="text" id="h" name="h" />
                                 </div>

                                 <input type="submit" value="上传" />
                                 </div>
                                  </form>
                                 </div>
                                 </div> 
                      <div style="text-align:center;clear:both"><br><br>
            </div>                     
        </div>
</body>

         <script src="js/jquery.min.js"></script>
         <script src="js/jquery.Jcrop.min.js"></script>
         <script src="js/script.js"></script>
</html>
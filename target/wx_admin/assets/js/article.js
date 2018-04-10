/**
 * Created by Chy on 2018/1/29.
 */
var validateAndSubmit =  function(){
    //表单验证
    var flag = false;
    $("#articleForm").validate({
        rules:{
            articleTitle: {
                required: true,
                maxlength: 50
            },
            articleTagIds: {
                required: true,
                maxlength: 50
            }
        },
        messages : {
            articleTitle : {
                required : '文章标题不能为空！',
                maxlength : '标题最长不能超过50个字符！',
            },
            articleTagIds : {
                required : '文章类别不能为空！',
                maxlength : '标签最长不能超过50个字符',
            },
        }
    });

    //获取文章正文的字符数
    var articleContent = $(".editormd-preview").text();
    $("input[name=wordCount]").val(articleContent.length);
    if(flag){
        $("#articleForm").submit();
    }
}
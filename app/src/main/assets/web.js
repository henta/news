function()
{  
    // 获取"img"标签的对象的集合  
    var imgs = document.getElementsByTagName("img");  
    for(var i = 0; i < imgs.length; i++)  
    {  
        // 添加点击事件  
        imgs[i].onclick = function()
        {  
        // "itArticleActivity"是在上面Java代码中创建的
            itArticleActivity.startWebPhotoActivity(this.src);
        }  
    }  
}  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Wolf-Eagle</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link href="css/font-awesome.min.css" rel="/stylesheet">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <link rel="stylesheet" type="text/css" href="/css/owl.carousel.css">
<#--<script src="jquery.quovolver.min.js"></script>-->
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script src="/js/owl.carousel.js"></script>
    <script src="/js/jquery.mixitup.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/jquery.quovolver.js"></script>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/jquery.simplePagination.js"></script>
    <!--[endif]-->
</head>

<script type="text/javascript">
$(function () {

    /*调整下拉框选定状态*/
    $("#searchTypeParam option").each(function () {
        console.log('${Type!}')
        if($(this).val() == '${Type!}'){
            $(this).attr("selected",true);
        }

    })

    /*进行ajax第一页更新加载的函数方法*/
    ajaxUpdate(1)

})

function ajaxUpdate(pageIndex) {
    console.log($("#searchInput").val())
    $.ajax({
        url:"/Search/AjaxPage.html?Type=" + $("#searchTypeParam option:selected").val() + "&ParamQ=" + $("#searchInput").val() + '&pageIndex=' + pageIndex,
        type:"GET",
        success:function (result) {
            $("#ajaxUpdate").html(result)
        }
    })
}
</script>

<body>
<!--header starts-->
<header class="main-header">
    <div class="backbg-color">
        <div class="navigation-bar">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <!--navigation starts-->
                        <nav class="navbar navbar-default ">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="/"><span class="grey">Wolf</span>Eagle</a>
                            </div>
                            <div class="collapse navbar-collapse navbar-right" id="myNavbar">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="/"><h3>我</h3></a></li>
                                    <li><a href="/"><h3>知</h3></a></li>
                                    <li><a href="/"><h3>道</h3></a></li>
                                    <li><a href="/"><h3>你</h3></a></li>
                                    <li><a href="/"><h3>想</h3></a></li>
                                    <li><a href="/"><h3>要</h3></a></li>
                                </ul>
                            </div>
                        </nav>
                        <!--navigation ends-->
                    </div>
                </div>
            </div>
        </div>
        <!--banner starts-->
        <div class="banner-text">
            <div class="container">
                <div class="row">
                    <div class="banner-info text-center">
                    </div>
                    <div class="banner-heading text-center">
                    </div>

                    <div class="banner-search col-md-offset-1 col-md-10 col-md-offset-1">
                        <form action="/Search/ParamPage.html" type="POST">
                            <div class="col-md-3">
                                <select class="form-control sellone" name="type" id="searchTypeParam">
                                    <option value="News">新闻</option>
                                    <option value="Db">书籍</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control selltwo" name="ParamQ" id="searchInput" placeholder="现在，把你想要的告诉我" value="${ParamQ!}">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-btn">
                                    <button type="submit" id="IndexSearchSubmit">现在搜索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="ajaxUpdate">
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<section class="appoin-sec">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="footer-sec text-center">
                    <h2>爱上我们 ?</h2>
                    <p>马上推荐小伙伴们一起使用狼鹰！</p>
                    <a href="/"><span>现在就开始！</span></a>
                </div>
            </div>
        </div>
    </div>
</section>
<br>
<section class="footer-line">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h5>Copyright &copy; 2017.Company name All rights reserved.More Templates <a href="" target="_blank" title="WolfEagle">WolfEagle</a> - Collect from <a href="" title="WolfEagle" target="_blank">WolfEagle</a></h5>
            </div>
        </div>
    </div>
</section>
</body>
</html>

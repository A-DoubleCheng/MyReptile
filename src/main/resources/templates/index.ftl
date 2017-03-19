<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="铝业地图">
    <meta name="author" content="Buyal">

    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">

</head>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>

<script type="text/javascript">
    $(function(){
        $(".deleteEnt").on("click",function () {
            var v = $(this).val()
            layer.open({
                content: '确定要将该企业从云图删除？'
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //按钮【按钮一】的回调
                    $.ajax({
                        url:"/deleteEnt?id="+v,
                        success: function(json) {

                            });
                        }
                    });
                }
                ,cancel: function(){
                    //右上角关闭回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
/*            */
        })

    })

</script>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">铝业地图</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <#--<li><a href="#">按本地方式搜索</a></li>-->
                <#--<li><a href="#">按周边方式搜索</a></li>-->
                <#--<li><a href="#">按多边形方式搜索</a></li>-->
                <#--<li><a href="#">按Id方式搜索</a></li>-->
                <#--<li><a href="#">按条件方式搜索</a></li>-->
                <#--<li><a href="#">按数据分布方式搜索</a></li>-->
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="搜索">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">地图控制<span class="sr-only">(current)</span></a></li>
                <li><a href="#">添加单个企业</a></li>
                <li><a href="#">添加多个企业</a></li>
                <li><a href="#">添加多个企业进度</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">地图信息</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>云地图Id</th>
                        <th>公司名称</th>
                        <th>所在省份</th>
                        <th>所在城市</th>
                        <th>所在地区</th>
                        <th>图片信息</th>
                        <th>经纬度</th>
                        <th>收录地址</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>相关操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list yunMapList as yM>
                    <tr>
                        <td>${yM?counter}</td>
                        <td>${yM._name!}</td>
                        <td>${yM._province!}</td>
                        <td>${yM._city!}</td>
                        <td>${yM._district!}</td>
                        <td>
                            <#if yM._images??>
                                <#list yM._images as ymi>
                                    ${ymi!}
                                </#list>
                            </#if>
                        </td>
                        <td>${yM._location!}</td>
                        <td>${yM._address!}</td>
                        <td>${yM._createtime!}</td>
                        <td>${yM._updatetime!}</td>
                        <td><button class="updateEnt" data-id="${yM._id!}">更新企业</button><em>-</em><button class="deleteEnt" value="${yM._id!}" data-toggle="modal">删除企业</button></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>

<script type="text/javascript">
    //分页//hrefTextPrefix参数会有容错率问题，分页使用了jquery插件pagination
    $('.pagination').pagination({
        items: ${PageTotal!},
        itemOnPage: ${pageSize!},
        currentPage: ${pageIndex!},
        cssStyle: '',
        prevText: '<span aria-hidden="true">&laquo;</span>',
        nextText: '<span aria-hidden="true">&raquo;</span>',
        hrefTextPrefix: 'javascript:void(',
        hrefTextSuffix:')',
        selectOnClick: true,
        onPageClick: function (page, event) {
            ajaxUpdate(page)
        }
    });
</script>

<div class="col-md-offset-1 col-md-10 col-md-offset-1">
    <p>${tip!}</p>
    <ul>
    <#list list as l>
        <li>
            <h2><a href="${l.url!}">${l.title!}</a></h2>
            <p>${l.content!}</p>
            <p>${l.time!}</p>
        </li>
    </#list>
    </ul>
</div>
<span><nav><ul class="pagination"></ul></nav></span>

$(".categoryExplorer-page:not(:first-child)").addClass("page--shrinked"),$(".carousel-control-link").on("click",function(a){a.preventDefault();var b=$(this).data("translate");$(this).closest(".categoryExplorer-list-category").find(".categoryExplorer-page").each(function(){var a=$(this).data("translate");b>a?$(this).addClass("page--slided"):($(this).removeClass("page--slided"),$(this).hasClass("page--shrinked")||$(this).addClass("page--shrinked")),b==a&&($(this).removeClass("page--slided"),$(this).removeClass("page--shrinked"))}),$(this).siblings().removeClass("control-link-active"),$(this).addClass("control-link-active")});
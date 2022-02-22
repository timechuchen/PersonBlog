//当前模块是对所有的 api 接口进行统一的管理
import requests from "@/api/request";
import mockRequest from "@/api/mockAjax";
//`https://api.github.com/search/users?q=${this.keyWord}`
//先调用一下其他的接口的接口测试一下
export const reqGitHubList = ()=> requests({url: '/search/users?q=chuchen',method:'get'})

//获取博客列表的接口
export const reqGetBlogList = ()=> mockRequest({url:'/blogList'});
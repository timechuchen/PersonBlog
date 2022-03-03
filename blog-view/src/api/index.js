//当前模块是对所有的 api 接口进行统一的管理
import requests from "@/api/request";
import mockRequest from "@/api/mockAjax";
//`https://api.github.com/search/users?q=${this.keyWord}`
//先调用一下其他的接口的接口测试一下
export const reqGitHubList = ()=> requests({url: '/search/users?q=chuchen',method:'get'})

//获取博客列表的接口
export const reqGetBlogList = ()=> mockRequest({url:'/blogList',method:'get'});
//获取轮播图数据接口
export const reqGetBanner = ()=> mockRequest({url:'/banner',method:'get'});
//获取“我”的个人信息
export const reqPersonInfo = ()=> mockRequest({url:'/personInfo'});
//获取热点推荐信息
export const reqHot = ()=> mockRequest({url: '/hot'});
//获取日志信息
export const reqRecode = ()=> mockRequest({url:'/diary'});
//获取留言信息
export const reqWords = ()=> mockRequest({url:'/words'});
//获取博客详细信息
export const reqBlog = ()=> mockRequest({url:'/blog',method: 'get'});
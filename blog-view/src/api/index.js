//当前模块是对所有的 api 接口进行统一的管理
import requests from "@/api/request";
import mockRequest from "@/api/mockAjax";

export const reqBlogBySearch = (search)=>requests({
    url: `/blogSearch`,
    method: 'GET',
    params: {
        search
    }
});

//`https://api.github.com/search/users?q=${this.keyWord}`
//先调用一下其他的接口的接口测试一下
export const reqGitHubList = ()=> requests({url: '/search/users?q=chuchen',method:'get'})

//获取博客列表的接口
// export const reqGetBlogList = ()=> mockRequest({url:'/blogList',method:'get'});
export const reqGetBlogList = ()=> requests({url:'/blogList',method:'get'});
//获取轮播图数据接口
export const reqGetBanner = ()=> mockRequest({url:'/banner',method:'get'});
// export const reqGetBanner = ()=> requests({url:'/banner',method:'get'});
//获取“我”的个人信息
export const reqPersonInfo = ()=> mockRequest({url:'/personInfo'});
//获取热点推荐信息
// export const reqHot = ()=> mockRequest({url: '/hot'});
export const reqHot = ()=> requests({url: '/loadHotTag'});
//获取动态信息
// export const reqRecode = ()=> mockRequest({url:'/diary'});
export const reqRecode = ()=> requests({url:'/recordsByDesc'});
//获取留言信息
// export const reqWords = ()=> mockRequest({url:'/words'});
export const reqWords = ()=>requests({url:'/words',method:'get'})
//发布留言
export const reqWord = (comment)=> requests({
    url: `/word`,
    method: 'POST',
    data: {
        ...comment
    }
});
//获取博客详细信息
// export const reqBlog = (blogId)=> mockRequest({url:'/blog',method: 'get',params: {blogId: blogId}});
export const reqBlog = (blogId)=> requests({url:'/blog',method: 'get',params: {blogId: blogId}});
//获取验证码
export const reqGetCode = (phone)=>requests({url:`/user/getCode/${phone}`,method:'get'});
//注册接口  /user/sigin
export const reqUserRegister = (data)=>requests({url:`/user/sigin`,data,method:'post'});
//登陆
export const reqLogo = (data)=>requests({url:`/user/login?email=${data.email}&password=${data.password}`,method:'post'});
//获取用户的信息（需要带着用户的 token 像服务器要信息）
export const reqUserInfo = ()=> requests({url:`/user/getUserLogin`,method:'get'});
//退出登陆
export const reqLogout = ()=> requests({url: `/user/logout`,method: 'get'});
//发布评论
export const reqComment = (comment)=> requests({
    url: `/comment`,
    method: 'POST',
    data: {
        ...comment
    }
});
//获取博客评论
export const reqCommentByBlogId = (blogId)=>requests({
    url: `/blogComment`,
    method: 'GET',
    params: {
        blogId
    }
});

//获取博客评论
export const reqBlogPassword = (blogId)=>requests({
    url: `/getPassword`,
    method: 'GET',
    params: {
        blogId
    }
});

//获得分类的博客
export const reqCommentByBlogTag = (category)=>requests({
    url: `/blogByTag`,
    method: 'GET',
    params: {
        category
    }
});

//获取推荐文章信息
export const reqGetRecommendBlogs = ()=>requests({
    url: `/recommendBlogs`,
    method: 'GET',
});

export const getLikesOfRecord = ()=>requests({
    url: `/likesOfRecord`,
    method: 'GET',
})

export const addLike = (recordId)=>requests({
    url: `/addLikes`,
    method: 'GET',
    params: {
        recordId
    }
})

export const getAllTagCloud = ()=>requests({
    url: `/tagClouds`,
    method: 'GET',
})
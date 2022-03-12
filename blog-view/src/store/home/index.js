//home模块的小仓库
//引入请求函数，但是我还没写后台，这里就先不用，用mock模拟数据
import {reqGetBlogList,reqGetBanner,reqPersonInfo,reqHot} from '@/api';

//仓库存储数据的地方
const state = {
    //state中的默认初始数据不要瞎写，一般要和接口返回的数据类型要一致
    blogList: [],
    bannerList: [],
    personInfo: {},
    hotTag: []
};
//修改state的唯一手段
const mutations = {
    BLOGLIST(state,blogList){
        state.blogList = blogList;
    },
    GETBANNERLIST(state,bannerList){
        state.bannerList = bannerList;
    },
    GETPERSONINFO(state,personalInfo){
        state.personInfo = personalInfo;
    },
    GETHOTTAG(state,hotTag){
        state.hotTag = hotTag
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //通过API中的接口函数调用，模拟像后台发送请求
    async getBlogList({commit}) {
        let result = await reqGetBlogList();
        if(result.code === 200) {
            commit('BLOGLIST',result.data);
        }
    },
    //获取轮播图数据
    async getBannerList({commit}){
        let result = await reqGetBanner();
        if(result.code === 200){
            commit('GETBANNERLIST',result.data);
        }
    },
    //获取个人信息
    async getPersonInfo({commit}){
        let result = await reqPersonInfo();
        if(result.code === 200){
            commit('GETPERSONINFO',result.data);
        }
    },
    //获取个人信息
    async getHotTag({commit}){
        let result = await reqHot();
        if(result.code === 200){
            commit('GETHOTTAG',result.data);
            return result.msg;
        }else {
            return new Error("加载失败")
        }
    }
};
//可以理解为计算属性，用于简化仓库数据，让数组获取仓库的数据更加方便
const getters = {};

export default {
    state,
    mutations,
    actions,
    getters
}
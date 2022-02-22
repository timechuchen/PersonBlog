//home模块的小仓库
//引入请求函数，但是我还没写后台，这里就先不用，用mock模拟数据
import {reqGetBlogList} from '@/api';

//仓库存储数据的地方
const state = {
    //state中的默认初始数据不要瞎写，一般要和接口返回的数据类型要一致
    blogList: [],
};
//修改state的唯一手段
const mutations = {
    BLOGLIST(state,blogList){
        state.blogList = blogList;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //通过API中的接口函数调用，模拟像后台发送请求
    async blogList({commit}) {
        let blogs = await reqGetBlogList();
        if(blogs.code === 200) {
            commit('BLOGLIST',blogs.data);
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
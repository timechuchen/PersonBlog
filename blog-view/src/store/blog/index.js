//blog模块的小仓库

//仓库存储数据的地方
import {reqBlog} from "@/api";

const state = {
    blog: {}
};
//修改state的唯一手段
const mutations = {
    GETBLOG(state,blog) {
        state.blog = blog;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //获取轮播图数据
    async getBlog({commit}){
        let result = await reqBlog();
        if(result.code === 200){
            commit('GETBLOG',result.data);
        }
    },
};
//可以理解为计算属性，用于简化仓库数据，让数组获取仓库的数据更加方便
const getters = {

};

export default {
    state,
    mutations,
    actions,
    getters
}
import {getAllSite} from '@/api/page';

const state = {
    hotTag: []
};
//修改state的唯一手段
const mutations = {
    GETALLHOTTAG(state,data) {
        state.hotTag = data;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //通过 token 请求用户信息
    async getHotTagInfo({commit}) {
        let result = await getAllSite();
        if(result.code === 200){
            commit('GETALLHOTTAG',result.data);
            // console.log("122",result.data[0])
            return result.msg;
        }else {
            return Promise.reject(new Error('服务器异常'));
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
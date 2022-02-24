//diary模块的小仓库

//仓库存储数据的地方
import {reqRecode} from "@/api";

const state = {
    recode: []
};
//修改state的唯一手段
const mutations = {
    GETRECODE(state,recode) {
        state.recode = recode;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //获取轮播图数据
    async getDiary({commit}){
        let result = await reqRecode();
        if(result.code === 200){
            commit('GETRECODE',result.data);
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
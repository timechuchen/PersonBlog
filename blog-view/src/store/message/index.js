//messgae模块的小仓库
//引入请求函数，但是我还没写后台，这里就先不用，用mock模拟数据
import {reqWords} from '@/api';

//仓库存储数据的地方
const state = {
    //state中的默认初始数据不要瞎写，一般要和接口返回的数据类型要一致
    words:[]
};
//修改state的唯一手段
const mutations = {
    GETHOTTAG(state,date) {
        state.words = date
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //获取留言信息
    async getWords({commit}){
        let result = await reqWords();
        if(result.code === 200){
            commit('GETHOTTAG',result.data);
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
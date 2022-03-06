//登陆预注册
import {reqGetCode, reqLogo, reqUserInfo, reqUserRegister} from "@/api";
import {getToken, setToken} from "@/utils/token";
//仓库存储数据的地方
const state = {
    token: getToken(),
    code: '',
    userInfo: {}
};
//修改state的唯一手段
const mutations = {
    LOGINSUCCESS(state,data) {
        state.token = data;
    },
    GETCODE(state,data) {
        state.code = data
    },
    GETUSERINFO(state,data){
        state.userInfo = data;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //获取验证码
    async getCode({commit},phone) {
        //获取验证码
        let result = await reqGetCode(phone);
        if(result.code === 200) {
            commit("GETCODE",result.data);
            return "ok";
        }else {
            return Promise.reject(new Error('faile'));
        }
    },
    //用户注册
    async userRegister({commit},user) {
       let result = await reqUserRegister(user);
       if(result.code === 200) {
           return 'ok';
       }else {
           return Promise.reject(new Error('注册失败'));
       }
    },
    //登陆业务
    async isLogo({commit},data) {
        let result = await reqLogo(data);
        if(result.code === 200) {
            commit('LOGINSUCCESS',result.data);
            //对token进行本地持久化存储
            setToken(result.data);
            return 'ok'
        }else {
            return Promise.reject(new Error('登陆失败'));
        }
    },
    //通过 token 请求用户信息
    async getUserInfo({commit}) {
        let result = await reqUserInfo();
        if(result.code === 200){
            commit('GETUSERINFO',result.data);
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
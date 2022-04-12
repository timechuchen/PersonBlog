//登陆预注册
import {reqGetCode, reqLogo, reqLogout, reqUserInfo, reqUserRegister} from "@/api";
import {getToken, setToken, removeToken} from "@/utils/token";
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
        let user = {id: data.id,username: data.username,avatar:data.avatar}
        localStorage.setItem('user',JSON.stringify(user));
    },
    CLEAR(state) {
        //把仓库和本地存储的所有用户信息清空
        state.token = '';
        state.userInfo = {};
        removeToken();
        localStorage.removeItem('user')
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
       }else if(result.code === 70001){
           return Promise.reject(new Error('验证码错误'));
       }else if(result.code === 20005){
           return Promise.reject(new Error('用户已存在'));
       }else if(result.code === 500){
           return Promise.reject(new Error('服务器错误'));
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
        }else if(result.code === 20003){
            return Promise.reject(new Error('密码错误'));
        }else {
            return Promise.reject(new Error('未注册'));
        }
    },
    //通过 token 请求用户信息
    async getUserInfo({commit}) {
        let result = await reqUserInfo();
        if(result.code === 200){
            commit('GETUSERINFO',result.data);
            return 'ok';
        }
    },
    //退出登陆
    async userLogout({commit}) {
        let result = await reqLogout();
        //注意，action 中不能操作 state，需要提交 mutation
        if (result.code === 200){
            commit('CLEAR');
            return 'ok';
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
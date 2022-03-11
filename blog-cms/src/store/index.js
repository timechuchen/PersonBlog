import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters'

//需要使用插件一次
Vue.use(Vuex);

//引入小仓库
import navbar from './navbar'
import settings from "@/store/settings";
import app from './app'
import page from "@/store/page";

//对外暴露Store的一个实例
export default new Vuex.Store({
    //实现Vuex仓库模块化开发存储
    modules: {
        navbar,
        settings,
        app,
        page
    },
    getters
});

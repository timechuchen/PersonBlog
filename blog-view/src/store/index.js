import Vue from 'vue';
import Vuex from 'vuex';

//需要使用插件一次
Vue.use(Vuex);

//引入小仓库
import home from './home'
import search from './search'
import diary from './diary'
import message from './message'

//对外暴露Store的一个实例
export default new Vuex.Store({
    //实现Vuex仓库模块化开发存储
    modules: {
        home,
        search,
        diary,
        message
    }
});

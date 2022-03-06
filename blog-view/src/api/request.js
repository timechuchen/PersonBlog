//这里需要对 axios 二次封装
import axios from "axios";
//在当前模块中引入store
import store from '@/store'
//引入进度条
import nprogress from 'nprogress';
//引入进度条的样式
import "nprogress/nprogress.css";

//1、利用 Axios 对象的 create 去创建一个 axios 实例
const requests = axios.create({
    //配置对象
    //基础路径，在发送请求的时候路径会出现 api 来区分前端的路由路径，这样就不用每次自己手写
    baseURL:'/api',
    //代表请求超时时间为 5s
    timeout:5000
});
//请求拦截器，在请求发送之前做的一些事
requests.interceptors.request.use((config)=>{
    //config 配置对象，对象里面有一个属性很重要，就是请 headers 求头
    //需要将token携带给服务器
    if(store.state.user.token) {
        config.headers.token = store.state.user.token;
    }
    //进度条开始动
    nprogress.start();
    return config;
})
//响应拦截器
requests.interceptors.response.use((res)=>{
    //成功的回调函数，服务器响应数据回来之后，响应拦截器可以检测到
    //进度条结束
    nprogress.done();
    return res.data;
},(error)=>{
    //响应失败的回调函数
    return Promise.reject(new Error('faile')+''+error);
})

//对外暴露
export default requests;
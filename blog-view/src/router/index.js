//配置路由
import Vue from 'vue';
import VueRouter from 'vue-router';
//使用插件
Vue.use(VueRouter);

//解决 Vue路由的一个bug报错问题
const originalPush = VueRouter.prototype.push
const originalReplace = VueRouter.prototype.replace

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
VueRouter.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err)
}

//引入路由组件
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import About from '@/pages/About'
import Daily from '@/pages/Daily'
import Message from '@/pages/Message'
import Knowledge from '@/pages/Knowledge'
import Search from '@/pages/Search'
import BlogList from "@/pages/Home/Main/BlogList";
import ShowBlog from "@/pages/common/ShowBlog";

//具体的路由配置
export default new VueRouter({
    routes: [
        {
            path: '/home',
            component: Home,
            meta: {isShowFooter: true}
        },
        {
            path: '/login',
            component: Login,
            meta: {isShowFooter: true}
        },
        {
            path: '/about',
            component: About,
            meta: {isShowFooter: true}
        },
        {
            path: '/daily',
            component: Daily,
            meta: {isShowFooter: true}
        },
        {
            path: '/message',
            component: Message,
            meta: {isShowFooter: true}
        },
        {
            path: '/search/:keyword?',
            component: Search,
            meta: {isShowFooter: false},
            name: 'search'
        },
        {
            path: '/knowledge',
            component: Knowledge,
            meta: {isShowFooter: true}
        },
        {
            path: '/showblog',
            component: ShowBlog,
            name: 'ShowBlog',
            meta: {isShowFooter: false}
        },
        {
            //重定向，在项目跑起来的时候，也就是访问根路径就要访问首页
            path: '/',
            redirect: '/home'
        }
    ]
})
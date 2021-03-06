//配置路由
import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store'
//使用插件
Vue.use(VueRouter);

//引入路由组件
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import About from '@/pages/About'
import Daily from '@/pages/Daily'
import Friends from '@/pages/Friends'
import Knowledge from '@/pages/Knowledge'
import Search from '@/pages/Search'
import Blog from '@/pages/Blog'
import File from '@/pages/404'
import getPageTitle from "@/utils/get-page-title";

//解决 Vue路由的一个bug报错问题
const originalPush = VueRouter.prototype.push
const originalReplace = VueRouter.prototype.replace

//VueRouter.prototype 中方法的重写
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
VueRouter.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err)
}

//具体的路由配置
let router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/404',
            component: File,
            meta: {title: '404 NOT FOUND'},
            hidden: true
        },
        {
            path: '/home',
            component: Home,
            meta: {isShowFooter: true,title: '首页'},
        },
        {
            path: '/login',
            component: Login,
            meta: {isShowFooter: false,title: '登陆'},
        },
        {
            path: '/about',
            component: About,
            meta: {isShowFooter: true,title: '关于我'}
        },
        {
            path: '/daily',
            component: Daily,
            meta: {isShowFooter: true,title: '动态'}
        },
        {
            path: '/friends',
            component: Friends,
            meta: {isShowFooter: true,title: '友链'}
        },
        {
            path: '/search/:keyword?',
            component: Search,
            meta: {isShowFooter: false,title: '搜索'},
            name: 'search'
        },
        {
            path: '/knowledge/:keyword?',
            component: Knowledge,
            meta: {isShowFooter: true,title: '学无止境'},
            name: 'knowledge'
        },
        {
            path: '/blog',
            component: Blog,
            name: 'Blog',
            meta: {isShowFooter: false,title: '博客'}
        },
        {
            //重定向，在项目跑起来的时候，也就是访问根路径就要访问首页
            path: '/',
            redirect: '/home'
        },
        // 404 page must be placed at the end !!!
        {path: '*', redirect: '/404'}
    ]
})

//配置路由守卫
router.beforeEach(async (to,from,next)=>{
    //获取仓库中的token为了确定用户是否已经登陆
    let token = store.state.user.token;
    let username = store.state.user.username;
    if(token){
        //已经登陆而且要想去登陆或者页面就要拦截下来
        if(to.path === '/login') {
            document.title = getPageTitle(to.meta.title)
            next('/')
        }else {
            //已经登陆而且访问的是非登陆页面
            if(username){
                document.title = getPageTitle(to.meta.title)
                next()
            }else {
                //登陆了而且没有用户信息要先获取信息再放行
                try {
                    await store.dispatch('getUserInfo');
                    document.title = getPageTitle(to.meta.title)
                    next();
                }catch (err) {
                    //token失效要重新登陆
                    await store.dispatch('userLogout');
                    document.title = getPageTitle(to.meta.title)
                    next('/login')
                }
            }
        }
    }else {
        if(to.path === '/about') {
            alert('登陆后才能查看此页面')
            next('/login')
        }else {
            document.title = getPageTitle(to.meta.title)
            next();
        }
    }
})

export default router
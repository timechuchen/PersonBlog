import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import File from '@/page/404'
import Login from '@/page/login'
import Home from '@/page/home'

const routes = [
    {
        path: '/404',
        component: File,
        meta: {title: '404 NOT FOUND'},
        hidden: true
    },
    {
        path: '/login',
        component: Login,
        meta: {title: '后台管理登录'},
        hidden: true
    },
    {
        path: '/home',
        component: Home,
        hidden: true
    },
    {
        //重定向，在项目跑起来的时候，也就是访问根路径就要访问首页
        path: '/',
        redirect: '/home'
    },
    // 404 page must be placed at the end !!!
    {path: '*', redirect: '/404', hidden: true}
]


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//挂载路由守卫
router.beforeEach((to, from, next) => {
    if (to.path !== '/login') {
        // //获取token
        const tokenStr = window.localStorage.getItem('token')
        if (!tokenStr) return next("/login")
    }
    next()
})

export default router
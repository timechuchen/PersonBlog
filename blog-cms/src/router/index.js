import Vue from 'vue'
import VueRouter from 'vue-router'
import getPageTitle from '@/utils/get-page-title'

Vue.use(VueRouter)

import File from '@/page/404'
import Login from '@/page/login'
import Home from '@/page/home'
import Layout from '@/components/Layout'

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
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [
            {
                path: 'dashboard',
                name: 'Dashboard',
                component: () => import('@/page/dashboard'),
                meta: {title: 'Dashboard', icon: 'el-icon-view'}
            }
        ]
    },
    {
        path: '/blog',
        name: 'Blog',
        redirect: '/blog/write',
        component: Layout,
        meta: {title: '博客管理', icon: 'el-icon-menu'},
        children: [
            {
                path: 'write',
                name: 'WriteBlog',
                component: () => import('@/page/blog/blog/WriteBlog'),
                meta: {title: '写文章', icon: 'el-icon-edit'}
            },
            {
                path: 'record/write',
                name: 'WriteRecord',
                component: () => import('@/page/blog/record/WriteRecord'),
                meta: {title: '写动态', icon: 'el-icon-edit'}
            },
            {
                path: 'edit/:id',
                name: 'EditBlog',
                component: () => import('@/page/blog/blog/WriteBlog'),
                meta: {title: '编辑文章', icon: 'el-icon-edit'},
                hidden: true
            },
            {
                path: 'record/edit/:id',
                name: 'EditRecord',
                component: () => import('@/page/blog/record/WriteRecord'),
                meta: {title: '编辑动态', icon: 'el-icon-edit'},
                hidden: true
            },
            {
                path: 'list',
                name: 'BlogList',
                component: () => import('@/page/blog/blog/BlogList'),
                meta: {title: '文章管理', icon: 'el-icon-s-order'}
            },
            {
                path: 'record/list',
                name: 'RecordList',
                component: () => import('@/page/blog/record/RecordList'),
                meta: {title: '动态管理', icon: 'el-icon-chat-dot-round'}
            },
            {
                path: 'category/list',
                name: 'CategoryList',
                component: () => import('@/page/blog/category/CategoryList'),
                meta: {title: '分类管理', icon: 'el-icon-s-opportunity'}
            },
            {
                path: 'tag/list',
                name: 'TagList',
                component: () => import('@/page/blog/tag/TagList'),
                meta: {title: '标签管理', icon: 'el-icon-price-tag'}
            },
            {
                path: 'comment/list',
                name: 'CommentList',
                component: () => import('@/page/blog/comment/CommentList'),
                meta: {title: '评论管理', icon: 'el-icon-s-comment'}
            },
        ]
    },
    {
        path: '/page',
        name: 'Page',
        redirect: '/page/site',
        component: Layout,
        meta: {title: '页面管理', icon: 'el-icon-document-copy'},
        children: [
            {
                path: 'site',
                name: 'SiteSetting',
                component: () => import('@/page/page/SiteSetting'),
                meta: {title: '热点设置', icon: 'el-icon-guide'}
            },
            {
                path: 'tag',
                name: 'TagCloud',
                component: () => import('@/page/page/TagCloud'),
                meta: {title: '标签云', icon: 'el-icon-paperclip'}
            }
        ]
    },
    {
        path: '/system',
        name: 'System',
        redirect: '/system/job',
        component: Layout,
        meta: {title: '系统管理', icon: 'el-icon-s-tools'},
        children: [
            {
                path: 'job',
                name: 'JobList',
                component: () => import('@/page/system/ScheduleJobList'),
                meta: {title: '定时任务', icon: 'el-icon-alarm-clock'}
            },
        ]
    },
    {
        path: '/log',
        name: 'Log',
        redirect: '/log/job',
        component: Layout,
        meta: {title: '日志管理', icon: 'el-icon-document'},
        children: [
            {
                path: 'login',
                name: 'LoginLog',
                component: () => import('@/page/log/LoginLog'),
                meta: {title: '登录日志', icon: 'el-icon-finished'}
            },
            {
                path: 'operation',
                name: 'OperationLog',
                component: () => import('@/page/log/OperationLog'),
                meta: {title: '操作日志', icon: 'el-icon-document-checked'}
            },
            {
                path: 'exception',
                name: 'ExceptionLog',
                component: () => import('@/page/log/ExceptionLog'),
                meta: {title: '异常日志', icon: 'el-icon-document-delete'}
            },
            {
                path: 'visit',
                name: 'VisitLog',
                component: () => import('@/page/log/VisitLog'),
                meta: {title: '访问日志', icon: 'el-icon-data-line'}
            },
        ]
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
        //获取token
        const tokenStr = window.localStorage.getItem('token')
        if (!tokenStr) return next("/login")
    }
    document.title = getPageTitle(to.meta.title)
    next()
})

export default router
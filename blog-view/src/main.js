import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false
//引入路由
import router from '@/router'
//引入仓库
import store from '@/store'
//引入懒加载插件
import VueLazyload from "vue-lazyload";
//引入mock虚拟数据
import '@/mock/mockServe'
//element-ui
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

//将热门标签注册为全局组件
import HotTag from "@/pages/Common/HotTag";
//引入懒加载图片
import pic from '@/assets/default.gif'
Vue.component(HotTag.name,HotTag);

Vue.use(VueLazyload,{
  //懒加载默认的图片
  loading:pic
})
Vue.use(Element)

new Vue({
  render: h => h(App),
  //注册路由
  router,
  //注册仓库：组件实例的身上对多一个$store属性
  store,
  beforeCreate() {
    Vue.prototype.$bus = this //安装全局事件总线
  },
}).$mount('#app')

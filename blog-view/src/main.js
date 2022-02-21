import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false
//引入路由
import router from '@/router'

//将云标签和热门标签注册为全局组件
import TagCloud from "@/pages/Home/RightBar/TagCloud";
import HotTag from "@/pages/Home/RightBar/HotTag";
Vue.component(TagCloud.name,TagCloud)
Vue.component(HotTag.name,HotTag);

// //测试接口
// import {reqGitHubList} from "@/api";
// reqGitHubList();

new Vue({
  render: h => h(App),
  //注册路由
  router
}).$mount('#app')

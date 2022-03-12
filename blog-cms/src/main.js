import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//element-ui
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/styles/index.scss'
//icon
import '@/icons'
//mavonEditor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
//v-viewer
import 'viewerjs/dist/viewer.css'
import Viewer from 'v-viewer'
// directive
import './utils/directive'
//moment
import './utils/dateTimeFormatUtils.js'

Vue.prototype.msgSuccess = function (msg) {
  this.$message.success(msg)
}

Vue.prototype.msgError = function (msg) {
  this.$message.error(msg)
}

Vue.config.productionTip = false

Vue.use(Element)
Vue.use(Viewer)
Vue.use(mavonEditor)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

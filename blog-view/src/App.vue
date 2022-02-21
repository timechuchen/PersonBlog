<template>
  <div id="app">
    <Hander/>
    <!--  路由组件出口的地方  -->
    <router-view></router-view>
    <!-- Footer组件在 search 中要影藏起来   -->
    <Footer v-show="$route.meta.isShowFooter"></Footer>
  </div>
</template>

<script>
//引入组件
import Hander from './components/Header/index'
import Footer from  './components/Footer/index'
export default {
  name: 'App',
  components: {
    Hander,
    Footer
  }
}
</script>

<style>
</style>

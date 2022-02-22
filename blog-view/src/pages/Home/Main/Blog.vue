<template>
  <ul class="index_arc">
    <li class="index_arc_item" v-for="(blog,index) in blogList" :key="blog.blogId" :class="blog.blogPic === null ? 'no_pic' : null">
      <a href="#" class="pic" v-if="blog.blogPic !== null">
        <img class="lazyload" src="./images/temp/art.jpg" alt="logo"/>
      </a>
      <h4 class="title"><a href="#">{{blog.blogTitle}}</a></h4>
      <div class="date_hits">
        <span>{{blog.author}}</span>
        <span>{{blog.createTime}}</span>
        <span><a href="#">{{blog.blogTage}}</a></span>
        <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> {{blog.hot}} </p>
        <p class="commonts"><i class="Hui-iconfont" title="点击量">&#xe622;</i>
        <span class="cy_cmt_count">{{blog.hits}}</span></p>
      </div>
      <div class="desc">{{blog.synopsis}}</div>
    </li>
  </ul>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: "Blog",
  //组件挂载完毕就可以向服务器发送请求获取数据
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('blogList')
  },
  computed: {
    ...mapState({
      blogList:state=> state.home.blogList
    })
  }
}
</script>

<style scoped>

</style>
<template>
<div>
  <ul class="index_arc" @click="goBlog">
    <li class="index_arc_item" v-for="(blog,index) in blogList" :key="blog.blogId" v-if="index < count" :class="blog.blogPic === null ? 'no_pic' : null">
      <a class="pic" v-if="blog.blogPic !== null">
        <img :data-blogOne="blog.blogId" class="lazyload" :src="blog.blogPic" alt="logo"/>
      </a>
      <h4 class="title">
        <a :data-blogOne="blog.blogId">{{blog.blogTitle}}</a>
      </h4>
      <div class="date_hits">
        <span class="blog_author">{{blog.author}}</span>
        <span class="blog_createTime">{{blog.createTime}}</span>
        <span class="blog_blogTage">{{blog.blogTage}}</span>
        <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> {{blog.hot}} </p>
        <p class="commonts"><i class="Hui-iconfont" title="点击量">&#xe622;</i>
          <span class="cy_cmt_count">{{blog.hits}}</span></p>
      </div>
      <div class="desc">{{blog.synopsis}}</div>
    </li>
  </ul>
  <div class="text-c mb-20" id="moreBlog">
    <a class="btn  radius btn-block" @click="showMore(blogList.length)">{{msg}}</a>
<!--    <a class="btn  radius btn-block hidden" href="javascript:">加载中……</a>-->
  </div>
</div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "BlogList",
  data() {
    return {
      count: 5,
      msg: "点击加载更多"
    }
  },
  methods: {
    goBlog(event) {
      let element = event.target;
      let {blogone} = element.dataset;
      // //如果标签身上有blog_one属性那它一定是a标签
      if(blogone){
        let location = {name: 'ShowBlog'};
        location.query = {blogId: blogone};
        this.$router.push(location);
      }
    },
    showMore(number) {
      if(this.count < number){
        this.count += 5;
      }else {
        this.msg = "没有更多了哦！！！"
      }
    }
  },
  //组件挂载完毕就可以向服务器发送请求获取数据
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getBlogList')
  },
  computed: {
    ...mapState({
      blogList:state=> state.home.blogList
    })
  }
}
</script>

<style scoped>
  .blog_author {
    color: black;
  }
  .blog_blogTage {
    color: #62c462;
  }
  .blog_createTime {
    color: #8ad21c;
  }
</style>
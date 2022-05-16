<template>
<div>
  <ul class="index_arc" @click="goBlog">
    <li class="index_arc_item" v-for="(blog,index) in blogList" :key="blog.blogId" v-if="index < count" :class="blog.blogPic === null ? 'no_pic' : null" v-show="blog.published">
      <a class="pic" v-if="blog.blogPic !== null">
        <img :data-blogOne="blog.blogId" class="lazyload" v-lazy="blog.blogPic" alt="logo"/>
      </a>
      <h4 class="title">
        <a :data-blogOne="blog.blogId">{{blog.blogTitle}}</a>
        <i class="Hui-iconfont Hui-iconfont-key" style="font-size: 15px; color: #b94a48" v-show="blog.password !== ''"></i>
      </h4>
      <div class="date_hits">
        <span class="blog_author" style="color: #e5db18">{{blog.author}}</span>
        <span class="blog_createTime" style="color: #e7572c">{{blog.updateTime | timeFormat}}</span>
        <span class="blog_blogTage" v-for="(tag,i) in blog.blogTage" :key="tag.id">
            <span :style="{'background-color':tag.color}">{{tag.tagName}}</span>
        </span>
        <p class="commonts"><i class="Hui-iconfont" title="评论量">&#xe622;</i>
          <span class="cy_cmt_count">{{blog.comment}}</span>
        </p>
      </div>
      <div class="desc">{{blog.description | text}}</div>
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
import dayjs from '@/utils/dayjs.min'

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
        let location = {name: 'Blog'};
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
  },
  filters: {
    text(value) {
      if(value.length > 145){
        return value.slice(0,140) + ' . . .';
      }else {
        return value
      }
    },
    timeFormat(val,str='YYYY-MM-DD HH:mm:ss'){
      return dayjs(val).format(str);
    },
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
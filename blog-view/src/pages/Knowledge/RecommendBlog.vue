<template>
  <!--博主信息-->
  <div class="bg-fff box-shadow radius mb-20">
    <div class="tab-category">
      <a href=""><strong>推荐文章</strong></a>
    </div>
    <div class="tab-category-item">
      <el-carousel :interval="4000" type="card" height="80px">
        <el-carousel-item v-for="(blog,index) in recommendBlogList" :key="blog.blogId">
          <img :src="blog.blogPic" :alt="blog.blogTitle" width="100%" @click="goBlog(blog.blogId)">
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import {reqGetRecommendBlogs} from '@/api'
export default {
  name: "RecommendBlog",
  data() {
    return {
      recommendBlogList: []
    }
  },
  mounted() {
    this.getRecommendBlogList()
  },
  methods: {
    getRecommendBlogList() {
      reqGetRecommendBlogs().then((res)=>{
        if(res.code === 200) {
          this.recommendBlogList = res.data;
        }else {
          this.msgError(res.msg)
        }
      })
    },
    goBlog(blogId) {
      let location = {name: 'Blog'};
      location.query = {blogId: blogId};
      this.$router.push(location);
    }
  }
}
</script>

<style scoped>
.border {
  width: 100%;
  margin: 3px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
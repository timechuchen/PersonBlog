<template>
  <header>
    <h1>页面还未写</h1>
      <div class="container cl">
        我是搜索页面 传过来的参数是：{{$route.query.keyword}}
        <ul>
          <li v-for="(item,index) in blogList" :key="index">
            <el-image
                style="width: 100px; height: 100px"
                :src="item.blogPic"></el-image>
            {{item.blogTitle}}
          </li>
        </ul>
      </div>
  </header>
</template>

<script>
import {reqBlogBySearch} from "@/api";

export default {
  name: "index",
  data() {
    return {
      blogList: [],
      fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      reqBlogBySearch(this.$route.query.keyword).then((res)=>{
        this.blogList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
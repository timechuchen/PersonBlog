<template>
  <div class="container pt-20">
    <h1 class="title">{{ title }}</h1>
    <!--横线-->
    <el-divider></el-divider>
    <ShowBlog @getTitle="setTitle" :blogId="$route.query.blogId"/>
    <Comment/>
  </div>
</template>

<script>
import ShowBlog from "@/pages/Blog/ShowBlog";
import Comment from "@/pages/Blog/Comment";
export default {
  name: "Blog",
  components: {ShowBlog,Comment},
  data() {
    return {
      title: ''
    }
  },
  methods: {
    setTitle(data) {
      this.title = data
    }
  }
}
</script>

<style scoped>
.title {
  font-weight: 800;
  font-size: 50px;
  text-align: center;
  background-color: rgba(9, 80, 102, 0.51);
  margin: 0 auto;
  width: 90%;
  border-radius: 10%;
}
</style>
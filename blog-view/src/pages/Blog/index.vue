<template>
  <div class="container pt-20">
    <h1 style="text-align: center">{{ title }}</h1>
    <hr>
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

</style>
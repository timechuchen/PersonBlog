<template>
  <div>
    <!--导航条-->
    <nav class="breadcrumb">
      <div class="container"><i class="Hui-iconfont" style="font-size: 15px">&#xe64b;</i>
        <span class="c-gray font-style">学无止境：追求卓越，成功就会在不经意间追上你</span>
      </div>
    </nav>
    <section class="container pt-20">
      <div class="col-sm-9 col-md-9">
        <div class="art_content">
          <BlogList/>
        </div>
      </div>
      <div class="col-sm-3 col-md-3">
        <HotTag/>
      </div>
    </section>
  </div>
</template>

<script>
import BlogList from "@/pages/Knowledge/BlogList";
import HotTag from "@/pages/Common/HotTag";
export default {
  name: "index",
  components: {BlogList,HotTag}
}
</script>

<style scoped>
.font-style {
  font-style: italic;
  font-size: larger;
  font-weight: 700;
  color: #e30a47;
}
</style>
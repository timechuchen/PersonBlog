<template>
  <div>
    <section class="container pt-20">
      <div class="col-sm-9 col-md-9">
        <!--  轮播图  -->
        <SlideShow/>
        <div class="mt-20 bg-fff box-shadow radius mb-5">
          <div class="tab-category">
            <a href=""><strong class="current">最新发布</strong></a>
          </div>
        </div>
        <div class="art_content">
          <BlogList/>
        </div>
        <div class="text-c mb-20" id="moreBlog">
          <a class="btn  radius btn-block " href="javascript:;">点击加载更多</a>
          <a class="btn  radius btn-block hidden" href="javascript:;">加载中……</a>
        </div>
      </div>
      <div class="col-sm-3 col-md-3">
        <!-- 热门标签，这已经是全局组件了，所以不用再次注册了 -->
        <Statement/>
        <Personal/>
        <HotTag/>
        <Seniority/>
        <Visitors/>
        <SiteShare/>
      </div>
    </section>
  </div>
</template>

<script>
//引入其子组件
import SlideShow from "@/pages/Home/Main/SlideShow";
import BlogList from "@/pages/Home/Main/BlogList"
import Statement from "@/pages/Home/RightBar/Statement";
import Personal from "@/pages/Home/RightBar/Personal";
import Seniority from "@/pages/Home/RightBar/Seniority";
import FriendlyLink from "@/pages/Home/RightBar/FriendlyLink";
import Visitors from "@/pages/Home/RightBar/Visitors";
import SiteShare from "@/pages/Home/RightBar/images/SiteShare";

export default {
  name: "index",
  components: {SlideShow,BlogList,Statement,Personal,Seniority,FriendlyLink,Visitors,SiteShare},
}
</script>

<style scoped>

</style>
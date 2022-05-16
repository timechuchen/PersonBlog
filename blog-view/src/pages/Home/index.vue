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
      </div>
      <div class="col-sm-3 col-md-3">
        <!-- 热门标签，这已经是全局组件了，所以不用再次注册了 -->
        <Statement/>
        <Personal/>
        <HotTag/>
        <Visitors/>
        <FriendlyLink/>
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
import FriendlyLink from "@/pages/Home/RightBar/FriendlyLink";
import Visitors from "@/pages/Home/RightBar/Visitors";

export default {
  name: "index",
  components: {SlideShow,BlogList,Statement,Personal,FriendlyLink,Visitors},
  mounted() {
    //获取用户信息进行展示
    if(this.$store.state.user.token){
      this.$store.dispatch('getUserInfo');
    }
  }
}
</script>

<style scoped>

</style>
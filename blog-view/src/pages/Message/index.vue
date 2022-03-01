<template>
  <div>
    <!--导航条-->
    <nav class="breadcrumb">
      <div class="container"><i class="Hui-iconfont" style="font-size: 15px">&#xe64b;</i>
        <span class="c-gray font-style">留言板：吐槽大会，认识自我</span>
      </div>
    </nav>
    <section class="container">
      <div class="col-xs-12 col-md-10 col-md-offset-1 mt-20">
        <InputText/>
        <div class="line"></div>
        <Words/>
      </div>
    </section>
  </div>
</template>

<script>
import InputText from "@/pages/Message/InputText";
import Words from "@/pages/Message/Words";
export default {
  name: "index",
  components: {InputText,Words}
}
</script>

<style scoped>
.font-style {
  font-style: italic;
  font-size: larger;
  font-weight: 700;
  color: #19bde7;
}
</style>
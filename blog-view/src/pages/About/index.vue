<template>
  <div>
    <h1 class="guanyuwo">关于我，现在还没有</h1>
    <h1 class="guanyuwo2">About me, not yet</h1>
<!--    <div class="center">-->
<!--      <img class="img" src="http://rbx53i4an.hd-bkt.clouddn.com/files/head/714mhe4rvk.png" alt="">-->
<!--    </div>-->
    <img class="img" src="http://rbx53i4an.hd-bkt.clouddn.com/files/head/714mhe4rvk.png" alt="">

<!--      <nav class="breadcrumb">-->
<!--        <div class="container">-->
<!--          <el-button type="primary" icon="el-icon-message-solid">留言</el-button>-->
<!--        </div>-->
<!--      </nav>-->
    <div>
<!--      <span class="liuyan">留言</span>-->
      <el-divider><i class="el-icon-s-promotion"></i></el-divider>
    </div>
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
import InputText from "@/pages/About/InputText";
import Words from "@/pages/About/Words";

export default {
  name: "index",
  components: {InputText,Words}
}
</script>

<style scoped>
.guanyuwo {
  text-align: center;
  line-height: 100%;
  color: #1395d7;
  font-weight: 700;
}
.guanyuwo2 {
  text-align: center;
  line-height: 100%;
  color: #7e10de;
}
.center {
  width: 32%;
  margin: auto;
  border: 1px solid red;
  background-color: #d1d1e3;
}
.img {
  display: block;
  width: 31%;
  margin: 0 auto;
}
</style>
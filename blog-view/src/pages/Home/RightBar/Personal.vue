<template>
  <!--博主信息-->
  <div class="bg-fff box-shadow radius mb-20">
    <div class="tab-category">
      <a href=""><strong>博主信息</strong></a>
    </div>
    <div class="tab-category-item">
      <ul class="index_recd">
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe62d;</i>姓名：{{personInfo.name}}</li>
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe67b;</i>QQ：{{personInfo.qq}}</li>
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe6d1;</i>GitHub：<a :href="personInfo.github">timechuchen</a></li>
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe70d;</i>职业：{{personInfo.occupation}}</li>
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe63b;</i>邮箱：<a :href="personInfo.email_address">{{personInfo.email}}</a></li>
        <li class="index_recd_item"><i class="Hui-iconfont">&#xe671;</i>定位：{{personInfo.address}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: "Personal",
  mounted() {
    this.$store.dispatch('getPersonInfo');
  },
  computed: {
    ...mapState({
      personInfo:state=> state.home.personInfo
    })
  }
}
</script>

<style scoped>
.Hui-iconfont {
  font-size: 20px;
}
</style>
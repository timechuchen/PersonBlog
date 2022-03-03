<template>
  <!--热门推荐-->
  <div class="bg-fff box-shadow radius mb-20">
    <div class="tab-category">
      <a href="#"><strong>热门推荐</strong></a>
    </div>
    <div class="tab-category-item">
      <ul class="index_recd">
        <li v-for="(hot,index) in hotTag" :key="hot.id">
          <a :href="hot.address">{{hot.name}}</a>
          <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe622;</i> 276 </p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex';
export default {
  name: "HotTag",
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getHotTag');
  },
  computed: {
    ...mapState({
      hotTag: state => state.home.hotTag
    })
  },
}
</script>

<style scoped>

</style>
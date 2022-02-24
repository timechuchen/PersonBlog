<template>
  <div>
    <div class="cd-timeline-block"  v-for="(recode,index) in recodes" :key="recode.id">
      <div class="cd-timeline-img cd-picture">
        <i class="Hui-iconfont font-local">&#xe690;</i>
      </div>
      <div class="cd-timeline-content">
        <h4>{{ recode.title }}</h4>
        <p class="font-style">{{ recode.content }}</p>
        <div class="f-r">
          <a @click="isLike(index)">
            <i v-if="!flag[index]" class="Hui-iconfont" style="font-size: 20px">&#xe649;</i>
            <i v-if="flag[index]" class="Hui-iconfont" style="font-size: 20px; color: red">&#xe648;</i>
          </a>
          <span class="font-style">{{ recode.likes }}</span>
        </div>
        <span class="cd-date">{{recode.create_time}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Notes",
  data() {
    return {
      flag: [],
    }
  },
  //组件挂载完毕就可以向服务器发送请求获取数据
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getDiary');
    this.flag.length = 100;
  },
  methods: {
    isLike(index){
      this.flag.splice(index,1,!this.flag[index]);
    }
  },
  computed: {
    ...mapState({
      recodes:state=> state.diary.recode
    })
  }
}
</script>

<style scoped>
.font-style {
  font-size: 14px;
}
.font-local {
  font-size: 30px;
  line-height: 105%;
  font-weight: 900;
  color: white;
}
</style>
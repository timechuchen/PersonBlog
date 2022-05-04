<template>
  <div>
    <div class="cd-timeline-block"  v-for="(recode,index) in recodes" :key="recode.id" v-if="recode.published">
      <div class="cd-timeline-img cd-picture">
        <i class="Hui-iconfont font-local">&#xe690;</i>
      </div>
      <div class="cd-timeline-content">
        <h4>{{ recode.title }}</h4>
        <p class="font-style">{{ recode.content }}</p>
        <div class="f-r">
          <a @click="isLike(index,recode)">
            <i v-if="!flag[index]" class="Hui-iconfont" style="font-size: 20px">&#xe649;</i>
            <i v-if="flag[index]" class="Hui-iconfont" style="font-size: 20px; color: red">&#xe648;</i>
          </a>
          <span class="font-style">{{ recode.likes }}</span>
        </div>
<!--        <button class="btn btn-success size-S location" v-if="recode.content.length>70">{{msg}}</button>-->
        <span class="cd-date">{{recode.createTime | timeFormat}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import dayjs from '@/utils/dayjs.min'

export default {
  name: "Notes",
  data() {
    return {
      flag: [],
      // msg: '展开'
    }
  },
  //组件挂载完毕就可以向服务器发送请求获取数据
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getDiary');
    this.flag.length = 100;
  },
  methods: {
    isLike(index,recode){
      //TODO 点赞功能的实现
      // console.log(index+'@@@'+recode.id+'@@@'+recode.likes)
      this.flag.splice(index,1,!this.flag[index]);
    },
  },
  computed: {
    ...mapState({
      recodes:state=> state.diary.recode,
    }),
  },
  filters: {
    text(value) {
      if(value.length > 70){
        return value.slice(0,70) + ' . . .';
      }else {
        return value
      }
    },
    timeFormat(val,str='YYYY-MM-DD HH:mm:ss'){
      return dayjs(val).format(str);
    },
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
.location {
  float: left;
}
</style>
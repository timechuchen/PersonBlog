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
          <a @click="isLike(index,recode.id)">
            <i class="Hui-iconfont" style="font-size: 20px">&#xe697;</i>
<!--            <i v-if="flag[index]" class="Hui-iconfont" style="font-size: 20px; color: red">&#xe697;</i>-->
          </a>
          <span class="font-style">{{ likes[recode.id] }}</span>
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
import {getLikesOfRecord,addLike} from '@/api'

export default {
  name: "Notes",
  data() {
    return {
      flag: [],
      // msg: '展开'
      likes: {}
    }
  },
  //组件挂载完毕就可以向服务器发送请求获取数据
  mounted() {
    this.$store.dispatch('getDiary');
    this.getLikes()
    this.flag.length = 100;
  },
  methods: {
    isLike(index,recodeId){
      addLike(recodeId)
      setTimeout(()=>{
        this.getLikes()
      },20)
    },
    getLikes() {
      getLikesOfRecord().then((res)=>{
        this.likes = res.data
      })
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
  },
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
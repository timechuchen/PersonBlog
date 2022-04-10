<template>
  <div>
    <ul class="commentList mt-50">
      <li class="item cl" v-for="(word,index) in words" :key="word.id" v-if="index < count">
        <a href="#"><i class="avatar size-L radius">
          <img alt="" :src="word.img"></i>
        </a>
        <div class="comment-main">
          <header class="comment-header">
            <div class="comment-meta"><a class="comment-author" href="#">{{ word.author }}</a>
              <time class="f-r">{{ word.time }}</time>
            </div>
          </header>
          <div class="comment-body">
            {{word.content}}
          </div>
        </div>
      </li>
    </ul>
    <div class="text-c mb-20" id="moreBlog" style="margin-top: 15px">
      <a class="btn  radius btn-block" @click="showMore(words.length)">{{msg}}</a>
      <!--    <a class="btn  radius btn-block hidden" href="javascript:">加载中……</a>-->
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "CommentList",
  data() {
    return {
      count: 5,
      msg:"点击加载更多",
    }
  },
  methods: {
    showMore(number) {
      if(this.count < number){
        this.count += 5;
      }else {
        this.msg = "没有更多了哦！！！"
      }
    }
  },
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getWords');
  },
  computed: {
    ...mapState({
      words:state=> state.message.words
    }),
  },
}
</script>

<style scoped>

</style>
<template>
  <div>
    <ul class="commentList mt-50">
      <li class="item cl" v-for="(comment,index) in comments" :key="comment.id" v-if="index < count">
        <a href="#"><i class="avatar size-L radius">
          <img alt="" :src="comment.img"></i>
        </a>
        <div class="comment-main">
          <header class="comment-header">
            <div class="comment-meta"><a class="comment-author" href="#">{{ comment.author }}</a>
              <time class="f-r">{{ comment.time | timeFormat}}</time>
            </div>
          </header>
          <div class="comment-body">
            {{comment.content}}
          </div>
        </div>
<!--        <button class="hf f-r btn btn-primary size-S mt-10" name="2">回复</button>-->
      </li>
    </ul>
    <div class="text-c mb-20" id="moreBlog" style="margin-top: 15px">
      <a class="btn  radius btn-block" @click="showMore(comments.length)">{{msg}}</a>
      <!--    <a class="btn  radius btn-block hidden" href="javascript:">加载中……</a>-->
    </div>
  </div>
</template>

<script>

import {reqCommentByBlogId} from '@/api'
import dayjs from "@/utils/dayjs.min";

export default {
  name: "CommentList",
  data() {
    return {
      count: 5,
      msg:"点击加载更多",
      comments: [],
    }
  },
  mounted() {
    this.getComment();
    this.$bus.$on('updateComment',this.getComment)
  },
  methods: {
    getComment() {
      reqCommentByBlogId(this.$route.query.blogId).then(res => {
        this.comments = res.data
      })
    },
    showMore(number) {
      if(this.count < number){
        this.count += 5;
      }else {
        this.msg = "没有更多了哦！！！"
      }
    },
  },
  beforeDestroy() {
    this.$bus.$off('updateComment')
  },
  filters: {
    timeFormat(val, str = 'YYYY-MM-DD HH:mm:ss') {
      return dayjs(val).format(str);
    },
  }
}
</script>

<style scoped>

</style>
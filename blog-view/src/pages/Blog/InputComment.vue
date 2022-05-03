<template>
  <div class="edit_container">
    <el-input
        type="textarea"
        placeholder="请输入内容"
        v-model="comment.context"
        maxlength="100"
        show-word-limit
        clearable
        resize="none"
    >
    </el-input>
    <button @click="publish" class="btn btn-primary radius location">发布</button>
  </div>
</template>

<script>

import {reqComment} from '@/api'

export default {
  name: "InputComment",
  data() {
    return {
      comment: {
        blogId: this.$route.query.blogId,
        authorId: this.$store.state.user.userInfo.id,
        parentCommentId: -1,
        page: 0,
        context: ''
      }
    }
  },
  methods: {
    publish() {
      if(!this.$store.state.user.token){
        this.msgError('未登录，请先登录')
        return
      }
      reqComment(this.comment).then(res=>{
        if(res.code === 200) {
          this.msgSuccess("发表成功")
          this.$bus.$emit('updateComment')
          this.comment.context = ''
        }else {
          alert(res.data)
        }
      })
    }
  }
}
</script>

<style scoped>
.location {
  float: right;
  margin: 10px;
}
</style>
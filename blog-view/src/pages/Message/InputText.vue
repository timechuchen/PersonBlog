<template>
  <div class="edit_container">
    <el-input
        type="textarea"
        placeholder="请输入内容"
        v-model="word.context"
        maxlength="300"
        :autosize="{ minRows: 5, maxRows: 15 }"
        resize="none"
        show-word-limit>
    </el-input>
    <button @click="publish" class="btn btn-primary radius location">发布</button>
  </div>
</template>

<script>
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
import {addQuillTitle} from "@/utils/quill-title";
import {reqWord} from '@/api'

export default {
  name: "InputText",
  data() {
    return {
      word: {
        authorId: this.$store.state.user.userInfo.id,
        parentCommentId: -1,
        page: 1,
        context: ''
      },
    }
  },
  methods: {
    //提交
    publish() {
      if(this.content === '') {
        alert('发布内容不能为空')
        return
      }
      if(!this.$store.state.user.token){
        this.msgError('未登录，请先登录')
        return
      }
      reqWord(this.word).then((res)=>{
        if(res.code === 200) {
          this.msgSuccess('发布成功')
        }else {
          this.msgError(res.msg)
        }
      })
    },
  },
}
</script>

<style scoped>
.location {
  float: right;
  margin: 10px;
}
</style>
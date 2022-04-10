<template>
  <div class="edit_container">
    <el-input
        type="textarea"
        placeholder="请输入内容"
        v-model="textarea"
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
import {reqComment} from "@/api";

export default {
  name: "InputComment",
  data() {
    return {
      textarea: ''
    }
  },
  methods: {
    publish() {
      let user = 1
      alert('我来了')
      console.log(this.$store.state.user.userInfo)
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
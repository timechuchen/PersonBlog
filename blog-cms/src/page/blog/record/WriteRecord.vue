<template>
  <div>
    <el-form :model="form" label-position="top">
      <el-form-item label="动态标题" prop="title" style="width: 70%">
        <el-input v-model="form.title" type="text" placeholder="输入标题"></el-input>
      </el-form-item>

      <el-form-item label="动态内容" prop="content">
        <mavon-editor v-model="form.content"/>
      </el-form-item>

      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker :disabled="true" type="datetime" placeholder="现在禁止自己设置" :editable="false"></el-date-picker>
      </el-form-item>

      <el-form-item style="text-align: right;">
        <el-button type="info" @click="submit(false)">仅自己可见</el-button>
        <el-button type="primary" @click="submit(true)">发布动态</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getRecordById, saveRecord, updateRecord} from "@/api/record";

export default {
  name: "WriteRecord",
  data() {
    return {
      form: {
        title: '',
        content: '',
        published: false
      },
    }
  },
  components: {getRecordById, saveRecord, updateRecord},
  created() {
    if (this.$route.params.id) {
      this.getMoment(this.$route.params.id)
    }
  },
  methods: {
    getMoment(id) {
      getRecordById(id).then(res => {
        this.form = res.data
      })
    },
    submit(published) {
      this.form.published = published
      if (this.$route.params.id) {
        updateRecord(this.form).then(res => {
          this.msgSuccess(res.msg)
          this.$router.push('/blog/record/list')
        })
      } else {
        saveRecord(this.form).then(res => {
          this.msgSuccess(res.msg)
          this.$router.push('/blog/record/list')
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
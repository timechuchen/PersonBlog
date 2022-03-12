<template>
  <div>
    <el-form :model="form" label-position="top">
      <el-form-item label="动态内容" prop="content">
        <mavon-editor v-model="form.content"/>
      </el-form-item>

      <el-form-item label="点赞数" prop="likes" style="width: 50%">
        <el-input v-model="form.likes" type="number" placeholder="可选，默认为 0"></el-input>
      </el-form-item>

      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="form.createTime" type="datetime" placeholder="可选，默认此刻" :editable="false"></el-date-picker>
      </el-form-item>

      <el-form-item style="text-align: right;">
        <el-button type="info" @click="submit(false)">仅自己可见</el-button>
        <el-button type="primary" @click="submit(true)">发布动态</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "WriteMoment",
  data() {
    return {
      form: {
        content: '',
        createTime: null,
        likes: 0,
        published: false
      },
    }
  },
  methods: {
    getMoment(id) {

    },
    submit(published) {

    }
  }
}
</script>

<style scoped>

</style>
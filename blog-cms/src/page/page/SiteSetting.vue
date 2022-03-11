<template>
  <div>
    <el-row style="margin-top: 20px">
      <el-card>
        <div slot="header">
          <span class="title">热点设置</span>
        </div>
        <el-form :inline="true" v-for="(badge,index) in typeMap" :key="badge.id">
          <el-form-item label="title">
            <el-input v-model="badge.value.title" size="mini"></el-input>
          </el-form-item>
          <el-form-item label="url">
            <el-input v-model="badge.value.url" size="mini"></el-input>
          </el-form-item>
          <el-form-item label="subject">
            <el-input v-model="badge.value.subject" size="mini"></el-input>
          </el-form-item>
          <el-form-item label="value">
            <el-input v-model="badge.value.value" size="mini"></el-input>
          </el-form-item>
          <el-form-item label="color">
            <el-input v-model="badge.value.color" size="mini"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteBadge(index)">删除</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" size="mini" icon="el-icon-plus" @click="addBadge">添加 badge</el-button>
      </el-card>
    </el-row>

    <div style="text-align: right;margin-top: 30px">
      <el-button type="primary" icon="el-icon-check" @click="submit">保存</el-button>
    </div>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";

export default {
  name: "SiteSetting",
  components: {Breadcrumb},
  data() {
    return {
      typeMap: [],
    }
  },
  created() {
    //从后台拿到数据
  },
  methods: {
    addBadge() {
      this.typeMap.push(
          {
            createTime: new Date().getTime(),
            value: {
              title: '',
              url: '',
              subject: '',
              value: '',
              color: ''
            }
          },
      );
    },
    deleteBadge(badge) {
      this.typeMap.splice(badge,1)
    },
    submit() {
      let data = this.typeMap;
      let jsonObject = JSON.parse(JSON.stringify(data));
      // console.log(jsonObject)
      //提交数据到后台

    }
  }
}
</script>

<style scoped>
.title {
  font-size: 20px;
  font-weight: 700;
  color: #20a0ff;
}
</style>
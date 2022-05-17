<template>
  <div>
    <el-row style="margin-top: 20px">
      <el-card>
        <div slot="header">
          <span class="title">标签云</span>
        </div>
        <el-form :inline="true" v-for="(tagCloud,index) in tagCloudList" :key="tagCloud.id">
          <el-form-item label="title">
            <el-input v-model="tagCloud.title" size="mini" placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item label="url">
            <el-input v-model="tagCloud.url" size="mini" placeholder="请输入地址链接"></el-input>
          </el-form-item>
          <el-form-item label="size">
            <el-input type="number" v-model="tagCloud.size" size="mini" placeholder="请输入字体大小（px）"></el-input>
          </el-form-item>
          <el-form-item label="color">
            <el-select v-model="tagCloud.color" placeholder="请选择颜色" size="mini">
              <el-option v-for="item in colors" :key="item.value" :label="item.label" :value="item.value">
                <span style="float: left; width: 100px;">{{ item.label }}</span>
                <span style="float: left; width: 100px; height: inherit" :class="`me-${item.value}`"></span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" icon="el-icon-delete" @click="updateTagCloud(tagCloud)">修改</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteTagCloud(tagCloud.id)">删除</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" size="mini" icon="el-icon-plus" @click="addTagCloud">添加 badge</el-button>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import {addTagCloud, deleteTagCloud, getAllTagCloud, updateTagCloud} from "@/api/tagCloud";

export default {
  name: "TagCloud",
  data() {
    return {
      tagCloud: {
        title: '标题',
        url: 'www.chuchen.ltd',
        size: '2',
        color: 'red'
      },
      tagCloudList: [],
      colors: [
        {label: '红色', value: 'red'},
        {label: '橘黄', value: 'orange'},
        {label: '黄色', value: 'yellow'},
        {label: '橄榄绿', value: 'olive'},
        {label: '纯绿', value: 'green'},
        {label: '水鸭蓝', value: 'teal'},
        {label: '纯蓝', value: 'blue'},
        {label: '紫罗兰', value: 'violet'},
        {label: '紫色', value: 'purple'},
        {label: '粉红', value: 'pink'},
        {label: '棕色', value: 'brown'},
        {label: '灰色', value: 'grey'},
        {label: '黑色', value: 'black'},
      ],
    }
  },
  created() {
    this.getDate()
  },
  methods: {
    getDate() {
      getAllTagCloud().then(res=>{
        if(res.code !== 200) {
          this.msgError(res.msg)
        }
        this.tagCloudList = res.data
      })
    },
    deleteTagCloud(id) {
      deleteTagCloud(id).then(res=>{
        if(res.code === 200) {
          this.msgSuccess(res.msg)
        }else {
          this.msgError(res.msg)
        }
      })
      setTimeout(()=>{
        this.getDate()
      },500)
    },
    updateTagCloud(tagCloud) {
      updateTagCloud(tagCloud).then(res=>{
        if(res.code === 200) {
          this.msgSuccess(res.msg)
        }else {
          this.msgError(res.msg)
        }
      })
      setTimeout(()=>{
        this.getDate()
      },500)
    },
    addTagCloud() {
      addTagCloud(this.tagCloud).then(res=>{
        if(res.code === 200) {
          this.msgSuccess(res.msg)
        }else {
          this.msgError(res.msg)
        }
      })
      setTimeout(()=>{
        this.getDate()
      },500)
    },
  }
}
</script>

<style scoped>
.title {
  font-size: 20px;
  font-weight: 700;
  color: #20a0ff;
}
.el-form--inline .el-form-item {
  margin-right: 7px;
}
</style>
<template>
  <div>
    <el-row style="margin-top: 20px">
      <el-card>
        <div slot="header">
          <span class="title">热点设置</span>
        </div>
        <el-form :inline="true" v-for="(badge,index) in typeMap" :key="badge.id">
          <el-form-item label="title">
            <el-input v-model="badge.title" size="mini" placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item label="url">
            <el-input v-model="badge.url" size="mini" placeholder="请输入地址链接"></el-input>
          </el-form-item>
          <el-form-item label="hits">
            <el-input v-model="badge.hits" size="mini" placeholder="请输入热度"></el-input>
          </el-form-item>
          <el-form-item label="color">
            <el-select v-model="badge.color" placeholder="请选择颜色" size="mini">
              <el-option v-for="item in colors" :key="item.value" :label="item.label" :value="item.value">
                <span style="float: left; width: 100px;">{{ item.label }}</span>
                <span style="float: left; width: 100px; height: inherit" :class="`me-${item.value}`"></span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteBadge(index)">删除</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" size="mini" icon="el-icon-plus" @click="addBadge">添加 badge</el-button>
      </el-card>
    </el-row>

    <div style="text-align: right;margin-top: 30px">
      <el-button type="primary" icon="el-icon-check" size="mini" round @click="submit">保存</el-button>
    </div>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import {updateSite} from '@/api/page';
import {mapState} from "vuex";

export default {
  name: "SiteSetting",
  components: {Breadcrumb},
  data() {
    return {
      typeMap: [],
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
  methods: {
    addBadge() {
      this.typeMap.push(
          {
            title: '',
            url: '',
            subject: '默认',
            htis: '',
            color: ''
          },
      );
    },
    deleteBadge(badge) {
      this.typeMap.splice(badge,1)
      this.submit()
    },
    submit() {
      let data = this.typeMap;
      let jsonObject = JSON.parse(JSON.stringify(data));
      //提交数据到后台
      updateSite(jsonObject).then((res)=>{
        if(res.code === 200) {
          this.msgSuccess(res.msg);
        }else {
          this.msgError(res.msg);
        }
      })
    }
  },
  created() {
    //从后台拿到数据
    this.$store.dispatch('getHotTagInfo')
  },
  computed: {
    ...mapState({
      hotTag:state=> state.page.hotTag
    }),
  },
  watch: {
    hotTag() {
      this.typeMap = this.hotTag;
    }
  },
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
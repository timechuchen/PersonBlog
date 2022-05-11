<template>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="list"
        label="序号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="name"
        label="任务名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="direct"
        label="任务介绍">
    </el-table-column>
    <el-table-column prop="btn" label="执行一次">
      <template slot-scope="scope">
        <el-button size="mini" type="primary" @click="runTask(scope.row.list)">执行</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import {updateVisitLogToMySql, delVisitInfoToDatabase, emptyExceptionLog, updateLikesOfDB} from "@/api/schedule";

export default {
  name: "ScheduleJobList",
  components: {Breadcrumb},
  data() {
    return {
      tableData: [{
                    list: '1',
                    name: '持久化日志到',
                    direct: '拿到 redis 中的所有访问信息存储到数据库中，并且删除redis中的标识',
                    },
                    {
                      list: '2',
                      name: '删除所有浏览日志信息',
                      direct: '删除数据库中所有的浏览日志',
                    },
                    {
                      list: '3',
                      name: '清空异常日志日志信息',
                      direct: '清空数据库中所有的异常日志',
                    },
                    {
                      list: '4',
                      name: '更新点赞数',
                      direct: '更新动态点赞数到数据库',
                    }
                  ]
    }
  },
  methods: {
    runTask(n) {
      if(n === '1') {
        updateVisitLogToMySql().then((res)=>{
          if(res.code === 200) {
            this.msgSuccess(res.msg)
          }else {
            this.msgError(res.msg)
          }
        })
      }else if(n === '2') {
        // TODO 删除数据库所有的浏览日志
        delVisitInfoToDatabase().then((res)=>{
          if(res.code === 200) {
            this.msgSuccess(res.msg)
          }else {
            this.msgError(res.msg)
          }
        })
      }else if(n === '3') {
        emptyExceptionLog().then((res)=>{
          if(res.code === 200) {
            this.msgSuccess(res.msg)
          }else {
            this.msgError(res.msg)
          }
        })
      }else if(n === '4') {
        updateLikesOfDB().then((res)=>{
          if(res.code === 200) {
            this.msgSuccess(res.msg)
          }else {
            this.msgError(res.msg)
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.el-button + span {
  margin-left: 10px;
}

.el-form--inline .el-form-item {
  margin-bottom: 0;
}
</style>
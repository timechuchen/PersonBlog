<template>
  <div>
    <!--搜索-->
    <el-form inline>
      <el-form-item label="操作时间">
        <DateTimeRangePicker :date="queryInfo.date" :setDate="setDate"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-search" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="pageList">
      <el-table-column label="序号" type="index" width="50"></el-table-column>
      <el-table-column label="操作者" prop="username"></el-table-column>
      <el-table-column label="请求方式" prop="method" width="80"></el-table-column>
      <el-table-column label="请求接口" prop="uri"></el-table-column>
      <el-table-column label="描述" prop="content" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作耗时" width="110">
        <template v-slot="scope">
          <el-tag size="small">{{ scope.row.times }}ms</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="170">
        <template v-slot="scope">{{ scope.row.createTime | dateFormat }}</template>
      </el-table-column>
      <el-table-column label="操作" width="120">
        <template v-slot="scope">
          <el-popconfirm title="确定删除吗？" icon="el-icon-delete" iconColor="red" @onConfirm="deleteLogById(scope.row.id)">
            <el-button size="mini" type="danger" icon="el-icon-delete" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum"
                   :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" background>
    </el-pagination>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import {getOperationLogList, deleteOperationLogById} from "@/api/operationLog";
import DateTimeRangePicker from "@/components/DateTimeRangePicker";
import {deleteVisitLogById, getVisitLogList} from "@/api/visitLog";

export default {
  name: "OperationLog",
  components: {DateTimeRangePicker, Breadcrumb},
  data() {
    return {
      queryInfo: {
        date: [],
        pageNum: 1,
        pageSize: 10
      },
      logList: [],
      pageList: [],
      total: 0,
    }
  },
  created() {
    this.getOperationLogList()
  },
  methods: {
    getData() {
      let start = (this.queryInfo.pageNum-1) * this.queryInfo.pageSize;
      let end = start + this.queryInfo.pageSize
      this.pageList = this.logList.slice(start,end);
    },
    getOperationLogList() {
      getOperationLogList().then(res => {
        this.logList = res.data
        this.total = res.data.length
        this.getData()
        if(res.code !== 200) {
          this.msgSuccess(res.msg)
        }
      })
    },
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getData()
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getData()
    },
    deleteLogById(id) {
      deleteOperationLogById(id).then(res => {
        if(res.code === 200) {
          this.msgSuccess(res.msg)
        }else {
          this.msgError(res.msg)
        }
        this.getData()
      })
      this.getOperationLogList()
    },
    search() {
      //TODO 按时间查找
      // let temp = []
      // this.pageList.forEach((e)=>{
      //   if(((this.queryInfo.date[0] < e.createTime) || (this.queryInfo.date[1] > e.createTime) || (this.queryInfo.date[0] === e.createTime)|| (this.queryInfo.date[1] === e.createTime))) {
      //     temp.unshift(e)
      //   }
      // })
      // this.pageList = temp
      // this.total = temp.length
    },
    setDate(value) {
      this.queryInfo.date = value
    },
  }
}
</script>

<style scoped>
.el-form--inline .el-form-item {
  margin-bottom: 0;
}
</style>
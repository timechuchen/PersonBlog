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
      <el-table-column label="序号" type="index" width="100"></el-table-column>
      <el-table-column label="请求接口" prop="uri" width="180"></el-table-column>
      <el-table-column label="请求方式" prop="method"  width="100"></el-table-column>
      <el-table-column label="描述" prop="description"></el-table-column>
      <el-table-column label="操作时间" width="170">
        <template v-slot="scope">{{ scope.row.createTime | dateFormat }}</template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template v-slot="scope">
          <el-button type="warning" icon="el-icon-view" size="mini" @click="showDetail(scope.row.error)">查看详情</el-button>
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

    <!-- 异常信息 -->
    <el-dialog title="异常信息" append-to-body top="20px" width="80%" :visible.sync="detailDialogVisible" destroy-on-close>
      <div class="match-braces rainbow-braces">
				<pre>
					<code class="language-java">{{ detail }}</code>
				</pre>
      </div>
      <span slot="footer">
				<el-button @click="detailDialogVisible=false">关 闭</el-button>
			</span>
    </el-dialog>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import {getExceptionLogList, deleteExceptionLogById} from "@/api/exceptionLog";
import DateTimeRangePicker from "@/components/DateTimeRangePicker";

export default {
  name: "ExceptionLog",
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
      detailDialogVisible: false,
      detail: ''
    }
  },
  created() {
    this.getExceptionLogList()
  },
  methods: {
    getData() {
      let start = (this.queryInfo.pageNum-1) * this.queryInfo.pageSize;
      let end = start + this.queryInfo.pageSize
      this.pageList = this.logList.slice(start,end);
    },
    getExceptionLogList() {
      getExceptionLogList().then(res => {
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
      deleteExceptionLogById(id).then(res => {
        if(res.code === 200) {
          this.msgSuccess(res.msg)
        }else {
          this.msgError(res.msg)
        }
        this.getData()
      })
      this.getExceptionLogList()
    },
    showDetail(error) {
      this.detail = '\n' + error
      this.detailDialogVisible = true
    },
    search() {
      //TODO 按时间查找
      // let temp = []
      // this.pageList.forEach((e)=>{
      //   if(e.uuid === this.queryInfo.uuid) {
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
.el-button + span {
  margin-left: 10px;
}

.el-form--inline .el-form-item {
  margin-bottom: 0;
}
</style>
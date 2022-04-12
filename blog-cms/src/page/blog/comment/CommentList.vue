<template>
  <div>
    <el-table :data="pageList">
      <el-table-column label="头像" width="80">
        <template v-slot="scope">
          <el-avatar shape="square" :size="60" fit="contain" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="昵称" prop="nickname">
        <template v-slot="scope">
          {{ scope.row.nickname }}
<!--          <el-tag v-if="scope.row.adminComment" size="mini" effect="dark" style="margin-left: 5px">我</el-tag>-->
        </template>
      </el-table-column>
      <el-table-column label="邮箱" prop="email" show-overflow-tooltip></el-table-column>
      <el-table-column label="评论内容" prop="content" show-overflow-tooltip></el-table-column>
      <el-table-column label="所在页面" show-overflow-tooltip>
        <template v-slot="scope">
          <el-link type="success" href="#" target="_blank" v-if="scope.row.page===0">{{ scope.row.blogTitle }}</el-link>
          <el-link type="success" href="#" target="_blank" v-else-if="scope.row.page===1">关于我</el-link>
        </template>
      </el-table-column>
      <el-table-column label="发表时间" width="170">
        <template v-slot="scope">{{ scope.row.createTime | dateFormat }}</template>
      </el-table-column>
      <el-table-column label="是否公开" width="80">
        <template v-slot="scope">
          <el-switch v-model="scope.row.isPublished" @change="commentPublishedChanged(scope.row.id,scope.row.isPublished)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template v-slot="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row)">编辑</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteCommentById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum"
                   :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" background>
    </el-pagination>

    <!--编辑分类对话框-->
    <el-dialog title="编辑评论" width="50%" :visible.sync="editDialogVisible" :close-on-click-modal="false" @close="editDialogClosed">
      <!--内容主体-->
      <el-form :model="editForm" :rules="formRules" ref="editFormRef" label-width="80px">
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <el-input v-model="editForm.avatar"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="评论内容" prop="content">
          <el-input v-model="editForm.content" type="textarea" maxlength="250" :rows="5" show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <!--底部-->
      <span slot="footer">
				<el-button @click="editDialogVisible=false">取 消</el-button>
				<el-button type="primary" @click="editComment">确 定</el-button>
			</span>
    </el-dialog>
  </div>
</template>

<script>
import {getCommentListByQuery, deleteCommentById, updatePublished, editComment} from '@/api/comment'
import {checkEmail, checkIpv4} from '@/utils/reg';

export default {
  name: "CommentList",
  data() {
    return {
      pageId: null,
      queryInfo: {
        pageNum: 1,
        pageSize: 10
      },
      total: 0,
      commentList: [],
      blogList: [],
      pageList:[],
      editDialogVisible: false,
      editForm: {
        id: null,
        nickname: '',
        avatar: '',
        email: '',
        content: ''
      },
      formRules: {
        nickname: [{required: true, message: '请输入评论昵称', trigger: 'blur'}],
        avatar: [{required: true, message: '请输入评论头像', trigger: 'blur'}],
        email: [
          // {required: true, message: '请输入评论邮箱', trigger: 'blur'},
          // {validator: checkEmail, trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请输入评论内容', trigger: 'blur'},
          {max: 255, message: '评论内容不可多于255个字符', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.getCommentList()
  },
  methods: {
    getData() {
      let start = (this.queryInfo.pageNum - 1) * this.queryInfo.pageSize;
      let end = start + this.queryInfo.pageSize
      this.pageList = this.commentList.slice(start, end);
    },
    getCommentList() {
      getCommentListByQuery().then(res => {
        this.commentList = res.data
        this.total = res.data.length
        this.getData()
        if (res.code !== 200) {
          this.msgSuccess(res.msg)
        }
      })
    },
    //监听 pageSize 改变事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getData()
    },
    //监听页码改变事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getData()
    },
    commentPublishedChanged(id,isPublished) {
      updatePublished(id,isPublished).then((res)=>{
        if(res.code === 200) {
          this.msgSuccess(res.msg);
          this.getCommentList();
        }else{
          this.msgError(res.msg);
        }
      })
    },
    showEditDialog(row) {
      this.editForm = { ...row };
      this.editDialogVisible = true;
    },
    deleteCommentById(id) {
      this.$confirm(
          '此操作将永久删除该评论<strong style="color: red">及其所有子评论</strong>，是否删除?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            dangerouslyUseHTMLString: true,
          }
      )
          .then(() => {
            deleteCommentById(id).then((res) => {
              if(res.code === 200) {
                this.msgSuccess(res.msg);
                this.getCommentList();
              }else {
                this.msgError(res.msg);
              }
            });
          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除',
            });
          });
    },
    editComment() {
      this.$refs.editFormRef.validate((valid) => {
        if (valid) {
          const form = {
            id: this.editForm.id,
            nickname: this.editForm.nickname,
            avatar: this.editForm.avatar,
            email: this.editForm.email,
            content: this.editForm.content,
          };
          console.log(form)
          editComment(form).then((res) => {
            if(res.code === 200) {
              this.msgSuccess(res.msg);
              this.editDialogVisible = false;
              this.getCommentList();
            }else {
              this.msgError(res.msg);
            }
          });
        }
      });
    },
    editDialogClosed(){

    }
  }
}
</script>

<style scoped>

</style>
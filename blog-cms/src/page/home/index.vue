<template>
  <div>
    <h1>我是Home页</h1>
    <div>
      <el-form label-position="top">
        <el-form-item label="标题" prop="title" style="width: 50%">
          <el-input placeholder="请输入标题"></el-input>
        </el-form-item>

        <el-row :gutter="20" style="width: 50%">
          <el-col :span="12">
            <el-form-item label="网易云歌曲ID" prop="musicId">
              <el-input type="number" placeholder="请输入网易云歌曲ID（可选）"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="评论开关">
              <el-switch active-text="评论"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="text-align: right;">
          <el-button type="primary" icon="el-icon-check">保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>

</style>
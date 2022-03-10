<template>
  <div>
    <el-row class="panel-group" :gutter="50">
      <el-col :span="6">
        <h1>我是首页</h1>
      </el-col>
    </el-row>
 </div>
</template>

<script>
import SvgIcon from "@/components/SvgIcon";
export default {
  name: "Dashboard",
  components: {SvgIcon},
}
</script>

<style scoped>
.panel-group {
  margin-bottom: 30px;
}

.panel-group .card-panel {
  height: 108px;
  position: relative;
  overflow: hidden;
}

.panel-group .card-panel .card-panel-icon-wrapper {
  float: left;
  margin: 14px 0 0 14px;
  padding: 16px;
}

.panel-group .card-panel .card-panel-icon {
  float: left;
  font-size: 48px;
}

.panel-group .card-panel .card-panel-description {
  float: right;
  font-weight: 700;
  margin: 26px 26px 26px 0;
}

.panel-group .card-panel .card-panel-description .card-panel-text {
  color: rgba(0, 0, 0, 0.45);
  font-size: 16px;
  margin-bottom: 12px;
}

.panel-group .card-panel .card-panel-description .card-panel-num {
  font-size: 20px;
}
</style>
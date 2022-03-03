<template>
  <div class="size">
    <mavonEditor v-model="content"
                 fontSize="30px"
                 defaultOpen="preview"
                 :toolbarsFlag="false"
                 :subfield="false"
                 :ishljs="true"/>
    <div class="radius border font_style">
      <i class="Hui-iconfont">&#xe667;</i> Java
      <i class="Hui-iconfont">&#xe64b;</i> MyBatis
      <i class="Hui-iconfont" style="margin-left: 20px">&#xe64b;</i> MyBatis
    </div>
  </div>
</template>

<script>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {mapState} from "vuex/dist/vuex.mjs";

export default {
  name: "ShowBlog",
  components: {mavonEditor},
  data() {
    return {
      content: '',
    }
  },
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getBlog');
  },
  computed: {
    ...mapState({
      blog:state=> state.blog.blog
    }),
  },
  watch: {
    blog() {
      this.content = this.blog.content;
      this.$emit('getTitle',this.blog.title)
    }
  }
}
</script>

<style scoped>
.v-note-wrapper {
  position: inherit;
}
.size {
  margin: 0 auto;
  width: 80%;
}
.border {
  margin-top: 20px;
  height: 40px;
  background-color: #67c78a;
}
.font_style {
  font-size: 20px;
  line-height: 40px;
}
</style>
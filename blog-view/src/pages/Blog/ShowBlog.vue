<template>
  <div class="size">
    <mavonEditor v-model="content"
                 fontSize="30px"
                 defaultOpen="preview"
                 :toolbarsFlag="false"
                 :subfield="false"
                 :ishljs="true"/>
    <div class="radius border font_style">
      <i class="Hui-iconfont">&#xe667;</i>
      <i class="Hui-iconfont" v-for="tag in blog.tags" :key="tag.id">&#xe64b;
        {{tag.tagName}}
      </i>
    </div>
  </div>
</template>

<script>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {mapState} from "vuex/dist/vuex.mjs";
import {reqBlogPassword} from '@/api'

export default {
  name: "ShowBlog",
  components: {mavonEditor},
  data() {
    return {
      content: '',
      password: ''
    }
  },
  props: ['blogId'],
  created() {
    if(this.blog.password !== '') {
      reqBlogPassword(this.blogId).then(res => {
        this.password = res.data + '';
      })
    }
  },
  mounted() {
    this.$store.dispatch('getBlog',this.blogId);
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
      if(this.blog.password !== ''){
        let pas = prompt('请输入密码：')
        if((this.password === '')||(this.password !== pas)){
          this.msgError('密码错误')
          this.$router.go(-1);
        }
      }
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
  width: 90%;
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
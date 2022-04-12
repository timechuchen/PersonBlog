<template>
  <div class="size">
    <InputComment/>
    <CommentList/>
  </div>
</template>

<script>
import InputComment from "@/pages/Blog/InputComment";
import CommentList from "@/pages/Blog/CommentList";

export default {
  name: "Comment",
  components: {InputComment,CommentList},
  props: ['blogId'],
}
</script>

<style scoped>
.size {
  width: 1000px;
  margin: 20px auto;
}
</style>
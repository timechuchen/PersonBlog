<template>
  <div class="size">
    <InputText/>
    <Words/>
  </div>
</template>

<script>
import InputText from '@/pages/Message/InputText'
import Words from '@/pages/Message/Words'
export default {
  name: "Comment",
  components: {InputText,Words}
}
</script>

<style scoped>
.size {
  width: 1000px;
  margin: 20px auto;
}
</style>
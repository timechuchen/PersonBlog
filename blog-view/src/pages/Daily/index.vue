<template>
  <div>
    <!--导航条-->
    <nav class="breadcrumb">
      <div class="container"><i class="Hui-iconfont" style="font-size: 15px">&#xe64b;</i>
        <span class="c-gray font-style">碎言碎语：随心而录，随手而写</span>
      </div>
    </nav>
    <section class="container mt-20">
      <div class="container-fluid">
        <div class="timeline">
          <Notes/>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Notes from "@/pages/Daily/Notes";
export default {
  name: "index",
  components: {Notes}
}
</script>

<style scoped>
.font-style {
  font-style: italic;
  font-size: larger;
  font-weight: 700;
  color: #62c462;
}
</style>
<template>
  <!--标签云-->
  <div class="bg-fff box-shadow radius mb-20">
    <div class="tab-category">
      <a href=""><strong>标签云</strong></a>
    </div>
    <div class="tab-category-item">
      <div class="tags">
        <span class="point" @click="push(item.url)" v-for="(item,index) in tagCloud"
              :key="item.id"
              :style="{'color': item.color,'font-size':item.size+'px'}">
          {{item.title}}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import {getAllTagCloud} from "@/api";

export default {
  name: "TagCloud",
  data() {
    return {
      tagCloud: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getAllTagCloud().then(res=>{
        this.tagCloud = res.data
      })
    },
    push(url) {
      window.open(url, "_blank");
    }
  }
}
</script>

<style scoped>
.tags {
  font-weight: 700;
}
.point {
  cursor : pointer;
  margin: 0 5px;
}
</style>
<template>
  <div class="container">
    <div class="box-size center-block">
      <button class="btn size-XL btn-set" :class="{'active':isActive === 1}" @click="loginFun">登陆</button>
      <button class="btn size-XL btn-set" :class="{'active':isActive === 2}" @click="siginFun">注册</button>
      <Login v-if="isActive === 1"/>
      <Sigin v-if="isActive === 2"/>
    </div>
  </div>
</template>

<script>
import Login from "@/pages/Login/Login";
import Sigin from "@/pages/Login/Sigin";
export default {
  name: "index",
  components: {Login,Sigin},
  data() {
    return {
      isActive: 1
    }
  },
  methods: {
    loginFun() {
      this.isActive = 1;
    },
    siginFun() {
      this.isActive = 2;
    }
  },
}
</script>

<style scoped>
.box-size {
  background-color: rgba(184, 196, 184, 0.39);
}
.center-block {
  width: 30%;
  margin: 10% auto;
  overflow: hidden;
}
.size-XL {
  width: 50%;
}
.btn-set {
  background-color: rgba(147, 152, 154, 0.58);
  color: #4e4c4c;
  font-weight: 600;
}
.active {
  background: #666666;
}
</style>
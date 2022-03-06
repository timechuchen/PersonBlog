<template>
  <div>
    <h2 class="title-size">登陆</h2>
    <form>
      <div class="input-group partition">
        <label for="username">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe62c;</i></button>
          <input type="text" placeholder="电话" id="username" class="input-text size-L size" v-model="phone">
        </label>
      </div>
      <div class="input-group partition">
        <label for="password">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe602;</i></button>
          <input type="password" placeholder="密码" id="password" class="input-text size-L size" v-model="password">
        </label>
      </div>
      <div class="check-box" style="display: block; text-align: center">
        <input type="checkbox" id="checkbox-1">
        <label for="checkbox-1">记住我</label>
      </div>
      <button type="submit" class="	btn btn-success-outline radius marge-set" @click.prevent="isLogin">登陆</button>
      <button type="reset" class="btn btn-secondary-outline radius marge-set">重置</button>
    </form>
    <div class="btn btn-link link-set">
      <a href="#" style="color: #b94a48">忘记密码</a>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Login",
  data() {
    return {
      phone: '',
      password: ''
    }
  },
  methods: {
    async isLogin() {
      try {
        //登陆成功
        const {phone,password} = this;
        (phone&&password)&& (await this.$store.dispatch('isLogo', {phone, password}));
        //跳转到 home 首页
        await this.$router.push('/home');
      }catch (error){
        alert(error.message);
      }
    }
  },
  computed: {
    ...mapState({
      token:state=> state.user.token
    })
  }
}
</script>

<style scoped>
.title-size {
  text-align: center;
  font-weight: 700;
  color: #358be8;
}
.size {
  width: 85%;
}
.partition {
  margin: 20px;
}
.marge-set {
  margin: 10px 60px;
}
.link-set {
  margin-left: 70%;
}
</style>
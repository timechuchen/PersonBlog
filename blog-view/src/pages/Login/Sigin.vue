<template>
  <div>
    <h2 class="title-size">注册</h2>
      <div class="input-group partition">
        <label for="phone">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe602;</i></button>
          <input type="text" placeholder="手机号" id="phone" class="input-text size-L size" v-model="phone">
        </label>
      </div>
      <div class="input-group partition">
        <label for="username">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe62c;</i></button>
          <input type="text" placeholder="用户名" id="username" class="input-text size-L size" v-model="username">
        </label>
      </div>
      <div class="input-group partition">
        <label for="password">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe602;</i></button>
          <input type="password" placeholder="密码" id="password" class="input-text size-L size" v-model="password">
        </label>
      </div>
      <div class="input-group partition">
        <label for="twoPassword">
          <button class="btn btn-primary size-L"><i class="Hui-iconfont">&#xe602;</i></button>
          <input type="password" placeholder="确定密码" id="twoPassword" class="input-text size-L size" v-model="twoPassword">
        </label>
      </div>
      <div class="input-group partition">
        <label for="yzm">
          <input type="text" placeholder="输入验证码" id="yzm" class="input-text size-L yzm-size" v-model="code">
          <button class="btn btn-primary size-L" @click="getCode">获取验证码</button>
        </label>
      </div>
      <button type="submit" class="btn btn-primary radius marge-set btn-length size-L" @click="isSigin">注册</button>
  </div>
</template>

<script>
export default {
  name: "Sigin",
  data() {
    return {
      //收集表单数据
      username: '',
      phone: '',
      code: '',
      password: '',
      twoPassword: ''
    }
  },
  methods: {
    async getCode() {
      try{
        //如果获取到验证码
        const {phone} = this
        phone && await this.$store.dispatch('getCode',this.phone);
        console.log(this.$store.state.user.code);
      }catch (error) {
        alert('验证码获取失败！');
      }
    },
    async isSigin() {
      if(this.password !== this.twoPassword){
        alert('两次密码不一致');
        return;
      }
      try {
        const {phone,code,password,username} = this;
        (phone&&code&&password)&& await this.$store.dispatch('userRegister',{username,password,phone,code});
        //注册成功 ==> 路由跳转到登陆
        this.$parent.loginFun();
      }catch (error) {
        alert(error.message);
      }
    }
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
  margin: 10px 5px;
}
.yzm-size {
  width: 65%;
}
.btn-length {
  width: 97%;

}
.link-set {
  margin-left: 70%;
}
</style>
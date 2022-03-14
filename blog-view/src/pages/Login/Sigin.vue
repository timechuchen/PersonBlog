<template>
  <el-form status-icon label-width="100px" class="demo-ruleForm">
    <el-form-item label="头像" prop="admin">
      <el-upload :action="action" :on-success="filesUploadSuccess">
        <el-col :span="12">
          <div class="demo-basic--circle">
            <div class="block"><el-avatar :size="50" :src="user.imageUrl"></el-avatar></div>
          </div>
        </el-col>
      </el-upload>
    </el-form-item>
    <el-form-item label="昵称" prop="username">
      <el-input type="text" v-model="user.username" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="phone">
      <el-input v-model.number="user.phone"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="user.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="user.twoPassword" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="验证码" prop="code">
      <el-input v-model="user.code"></el-input><el-button @click.prevent="getCode">获取验证码</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Sigin",
  data() {
    return {
      action: 'http://localhost:8080/api/util/files/head/' + Math.random().toString(36).substr(2),
      user: {
        username: '',
        phone: '',
        code: '',
        password: '',
        twoPassword: '',
        imageUrl: 'http://localhost:8089/api/util/files/head/mm',
      }
    };
  },
  methods: {
    filesUploadSuccess(res) {
      this.user.imageUrl = res.data
    },
    async submitForm() {
      if(!(this.user.username && this.user.password && this.user.phone && this.user.code)){
        return alert('信息不完整')
      }
      if(this.user.password !== this.user.twoPassword) {
        return alert('密码不一致')
      }
      try {
        const {phone,code,password,username} = this.user;
        (phone&&code&&password&&username) && (await this.$store.dispatch('userRegister',this.user));
        //注册成功 ==> 路由跳转到登陆
        this.$parent.loginFun();
      }catch (error) {
        alert(error.message);
      }
    },
    async getCode() {
      try{
        //如果获取到验证码
        const {phone} = this.user
        phone && await this.$store.dispatch('getCode',phone);
        console.log(this.$store.state.user.code);
      }catch (error) {
        alert('验证码获取失败！');
      }
    },
  },
}
</script>

<style scoped>
</style>
<template>
  <el-form :model="user" status-icon :rules="rules" label-width="100px" class="demo-ruleForm"  >
<!--    <el-form-item label="头像">-->
<!--      <el-upload :action="action" :on-success="filesUploadSuccess" :show-file-list="false">-->
<!--        <el-col :span="12">-->
<!--          <div class="demo-basic&#45;&#45;circle">-->
<!--            <div class="block"><el-avatar :size="50" :src="user.imageUrl"></el-avatar></div>-->
<!--          </div>-->
<!--        </el-col>-->
<!--      </el-upload>-->
<!--    </el-form-item>-->
    <el-form-item label="昵称" prop="username" style="margin-top: 20px">
      <el-input type="text" v-model="user.username" autocomplete="off" class="input" placeholder="请输入昵称"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input type="text" v-model="user.email" class="input" placeholder="请输入邮箱"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="user.password" autocomplete="off" class="input" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="password">
      <el-input type="password" v-model="user.twoPassword" autocomplete="off" class="input" placeholder="请再次输入密码"></el-input>
    </el-form-item>
    <el-form-item label="验证码" prop="code">
      <el-input v-model="user.code" class="code" placeholder="请输入验证码"></el-input>
      <el-button @click.prevent="getCode" size="small" :disabled="isDisabled">{{ code }}</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm" class="input">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Sigin",
  data() {
    return {
      // action: 'http://localhost:8080/api/util/files/head/' + Math.random().toString(36).substr(2),
      user: {
        user: '',
        email: '',
        code: '',
        password: '',
        twoPassword: '',
        imageUrl: 'http://localhost:8089/api/util/files/head/mm',
      },
      rules: {
        username: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }
        ],
      },
      code: '获取验证码',
      isDisabled: false
    };
  },
  methods: {
    // filesUploadSuccess(res) {
    //   this.user.imageUrl = res.data
    // },
    async submitForm() {
      if(!(this.user.username && this.user.password && this.user.email && this.user.code)){
        return alert('信息不完整')
      }
      if(this.user.password !== this.user.twoPassword) {
        return alert('密码不一致')
      }
      try {
        const {email,code,password,username,imageUrl} = this.user;
        let res = (email&&code&&password&&username) && (await this.$store.dispatch('userRegister',{email,code,password,username,imageUrl}));
        //注册成功 ==> 路由跳转到登陆
        if(res === '注册成功') this.msgSuccess(res)
        this.$parent.loginFun();
      }catch (error) {
        alert(error.message);
      }
    },
    async getCode() {
      //这样验证不严谨，具体的 正则表达式 验证以后再写
      if(this.user.email === '') {
        alert('先填写邮箱')
        return
      }
      let count = 120;
      this.isDisabled = true
      let interval = setInterval(()=>{
        this.code = count--
        this.code = this.code+ '秒'
        if(count === 0) {
          clearInterval(interval)
          this.code = '获取验证码'
          this.isDisabled = false
        }
      },1000)
      try{
        //如果获取到验证码
        const {email} = this.user
        email && await this.$store.dispatch('getCode',email);
      }catch (error) {
        alert('验证码获取失败！');
      }
    },
  },
}
</script>

<style scoped>
.input {
  width: 70%;
}
.code {
  width: 50%;
}
</style>
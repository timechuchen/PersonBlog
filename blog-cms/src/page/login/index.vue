<template>
  <div class="login-box">
    <h1>博客后台登录</h1>
    <form method="post" action="">
      <div class="name">
        <label for="user">账  &nbsp;&nbsp;  号：</label>
        <input type="text" name="" id="user" tabindex="1" autocomplete="off" />
      </div>
      <div class="password">
        <label for="password">密  &nbsp;&nbsp;  码：</label>
        <input type="password" maxlength="16" id="password" tabindex="2" />
      </div>
      <div class="login">
        <button type="submit" tabindex="5" @click.prevent="handleLogin">登录</button>
      </div>
    </form>
    <div class="background">
      <img src="../../assets/images/0.jpg" alt="background">
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  methods: {
    handleLogin() {
      window.localStorage.setItem('token', '53531525234531243');
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
ul,
li {
  list-style: none
}

h1 {
  font-size: 30px;
  font-weight: 700;
  color: black;
  text-shadow: 0 1px 4px rgba(23, 45, 29, 0.2)
}

label {
  font-size: 20px;
}

.login-box {
  width: 30%;
  margin: 120px auto 0 auto;
  text-align: center;
  padding: 30px;
  background-color: rgba(50, 49, 49, 0.39);
  border-radius: 10px;
}

.login-box .remember input {
  box-shadow: none;
  width: 15px;
  height: 15px;
  margin-top: 25px
}

.login-box .remember label {
  display: inline-block;
  height: 42px;
  width: 70px;
  line-height: 34px;
  text-align: left
}

.login-box label {
  display: inline-block;
  width: 100px;
  text-align: right;
  vertical-align: middle
}

.login-box img {
  width: 148px;
  height: 42px;
  border: none
}

input[type=text],
input[type=password] {
  width: 70%;
  height: 42px;
  margin-top: 25px;
  padding: 0 15px;
  border: 1px solid rgba(255, 255, 255, .15);
  border-radius: 6px;
  color: #fff;
  letter-spacing: 2px;
  font-size: 16px;
  background: transparent;
}

button {
  cursor: pointer;
  width: 100%;
  height: 44px;
  padding: 0;
  margin-top: 20px;
  background: #1499e0;
  border: 1px solid #281fa0;
  border-radius: 6px;
  font-weight: 700;
  color: #fff;
  font-size: 24px;
  letter-spacing: 15px;
  text-shadow: 0 1px 2px rgba(0, 0, 0, .1)
}

input:focus {
  outline: none;
  box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset, 0 2px 7px 0 rgba(0, 0, 0, .2)
}

button:hover {
  box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px 0 rgba(0, 0, 0, .2)
}

.background img {
  vertical-align: middle;
  border: none;
  display: block;
  list-style: none;
  position: fixed;
  overflow: hidden;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1000;
  float: right;
}

.bottom a {
  color: #FFF;
  text-decoration: none;
}

.bottom a:hover {
  text-decoration: underline;
}
</style>

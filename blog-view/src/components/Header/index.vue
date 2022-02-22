<template>
  <header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
      <div class="container cl">
        <router-link class="navbar-logo hidden-xs" to="/home">
          <img class="logo" src="./images/logo.png" alt="初晨博客" />
        </router-link>
        <a class="logo navbar-logo-m visible-xs" href="#">初晨博客</a>
        <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);">&#xe667;</a>
        <nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
          <ul class="cl">
            <li class="active"> <a href="#" data-hover="首页">首页</a> </li>
            <li> <a href="#" data-hover="学无止境">学无止境</a> </li>
            <li> <a href="#" data-hover="碎言碎语">碎言碎语</a> </li>
            <li> <a href="#" data-hover="关于我">关于我</a> </li>
            <li> <a href="#" data-hover="留言板">留言板</a> </li>
            <li><input type="text" id="search" class="input-text input-size" v-model="keyword"><button class="btn btn-default" @click="goSearch">搜索</button></li>
          </ul>
        </nav>
        <nav class="navbar-nav navbar-userbar hidden-xs hidden-sm " style="top: 0;">
          <ul class="cl">
            <li class="userInfo dropDown dropDown_hover">
              <router-link to="/login"><img class="avatar size-S" src="./images/qq.jpg" title="登入" alt="QQ登陆">登陆</router-link>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      keyword: ''
    }
  },
  methods: {
    //搜索按钮的回调函数：需要想search路由进行跳转
    goSearch() {
      //路由传递参数
      //第一种，字符串形式
      // this.$router.push('/search/' + this.keyword);
      //第二种，模板字符串的方式
      // this.$router.push(`/search/${this.keyword}`);
      //第三种，对象的写法，一般真正的项目开发也都用这种
      this.$router.push({name:'search',params: {keyword:this.keyword !== ''?this.keyword:undefined}});
    }
  }
}
</script>

<style scoped>
/*导航条*/
.navbar.navbar-fixed-top {
  border-bottom: 1px #222 solid;
}
.input-size {
  width: 60%;
}
.navbar .container .navbar-userbar {
  top: 8px;
  right: 32px;
}
.nav ul li.active a {
  color:#000;
}
.nav>ul>li>a {
  padding: 0 30px;/*导航宽度*/
  outline: none;
  color: #838383;
  text-decoration: none;
  letter-spacing: 5px;
  text-shadow: 0 0 1px rgba(255,255,255,0.3);
  font-size: 1.25em;
}
.nav>ul>li>a::before {
  color: #000;
  content: attr(data-hover);
  position: absolute;
  opacity: 0;
  text-shadow: 0 0 1px rgba(255,255,255,0.3);
  -webkit-transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
  -moz-transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
  transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
  -webkit-transition: -webkit-transform 0.3s, opacity 0.3s;
  -moz-transition: -moz-transform 0.3s, opacity 0.3s;
  transition: transform 0.3s, opacity 0.3s;
  pointer-events: none;
}
.nav>ul>li>a:hover::before,
.nav>ul>li>a:focus::before {
  -webkit-transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
  -moz-transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
  transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
  opacity: 1;
}
/*user login*/
.userInfo i {
  font-size: 28px;
  color: #838383;
  position: absolute;
  right: 33px;
}
</style>
<template>
  <header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
      <div class="container cl">
        <router-link class="navbar-logo hidden-xs" to="/home">
          <img class="logo" src="./images/logo.png" alt="初晨博客" />
        </router-link>
        <a class="logo navbar-logo-m visible-xs" href="#">初晨博客</a>
        <nav class="nav navbar-nav nav-collapse font-size" role="navigation" id="Hui-navbar">
          <ul class="cl">
            <li class="active"> <router-link to="/home" data-hover="首页">首页</router-link> </li>
            <li class="dropDown dropDown_hover" @mouseenter="current = true" @mouseleave="current = false">
              <router-link to="/knowledge" class="dropDown_A" data-hover="学无止境">学无止境<i class="Hui-iconfont">&#xe6d5;</i></router-link>
              <transition>
                <ul class="dropDown-menu menu radius box-shadow" v-show="current" style="display: block">
                  <li><a @click="goSearch2('Java')">Java</a></li>
                  <li><a @click="goSearch2('力扣算法')">力扣算法</a> </li>
                  <li><a @click="goSearch2('C++')">C++</a></li>
                </ul>
              </transition>
            </li>
            <li> <router-link to="/daily" data-hover="碎言碎语">碎言碎语</router-link> </li>
            <li> <router-link to="/about" data-hover="关于我">关于我</router-link> </li>
            <li> <router-link to="/message" data-hover="留言板">留言板</router-link> </li>
            <li>
              <input type="text" class="input-text input-size" v-model="keyword" placeholder="搜索博客">
              <button class="btn btn-default" @click="goSearch">搜索</button>
            </li>
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
      keyword: '',
      current: false
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
    },
    goSearch2(value){
      this.$router.push({name:'search',params: {keyword:value}});
    }
  }
}
</script>

<style scoped>
/*!*导航条*!*/
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
.navbar-wrapper{ height: 45px}
.navbar{ position:relative; z-index:1030}
.navbar-fixed-top{ position:fixed; top:0;left: 0; right: 0; z-index:1030}

/*logo*/
.logo{ display:inline-block; text-decoration:none; cursor:pointer}
a.logo:hover{ text-decoration:none}
.navbar .logo{height: 44px;line-height: 44px;margin-right: 10px;float: left}
.navbar-logo,.navbar-logo-m{font-size: 16px}
.navbar .container{ position:relative}
.navbar-userbar{position:absolute;top:0; right:15px}
.navbar .container .navbar-userbar{ right:0
}
/*导航*/
.nav{ z-index:1}
.nav > ul{ font-size:0; line-height:0}
.nav > ul > li{ position:relative}
.nav > ul > li,.nav > ul > li > a{ display:inline-block; height:44px; line-height:44px;text-align:center;font-size:18px}
.nav > ul > li > a{ padding:0 20px}
.nav > ul > li > a:hover,.nav > ul > li.current > a{background-color:rgba(255,255,255,0.2); text-decoration:none;
  -webkit-transition: background-color 0.3s ease 0s;
  -moz-transition: background-color 0.3s ease 0s;
  -o-transition: background-color 0.3s ease 0s;
  -ms-transition: background-color 0.3s ease 0s;
  transition: background-color 0.3s ease 0s
}
.v-enter-active {
  animation: isShow 0.5s linear;
}
.v-leave-active {
  animation: isShow 0.5s linear reverse;
}
/*定义动画*/
@keyframes isShow {
  from{
    transform: translateY(-100%);
  }
  to {
    transform: translateY(0);
  }
}
</style>
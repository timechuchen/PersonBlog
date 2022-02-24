<template>
  <!--滚动图-->
  <div class="slider_main">
    <div class="slider">
      <div class="bd">
        <div class="swiper">
          <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="(carouse,index) in bannerList" :key="carouse.id">
              <img :src="carouse.imgUrl" alt="banner">
            </div>
          </div>
          <!-- 如果需要分页器 -->
          <div class="swiper-pagination"></div>
          <!-- 如果需要导航按钮 -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex';
import Swiper from 'swiper';
import 'swiper/css/swiper.min.css'
export default {
  name: "SlideShow",
  mounted() {
    //从Vuex发送请求获取数据（这里先拿到模拟数据）
    this.$store.dispatch('getBannerList');
  },
  computed: {
    ...mapState({
      bannerList: state => state.home.bannerList
    })
  },
  watch: {
    //监听bannerList数据的变化，这条数据发生变化就加载轮播图的js脚本
    bannerList: {
      handler() {
        //必须保证在下次的DOM更新后执行延迟回调
        this.$nextTick(()=>{
          //轮播图
          const mySwiper = new Swiper('.swiper', {
            loop: true, // 循环模式选项
            autoplay:true,//自动循环
            // 如果需要分页器
            pagination: {
              el: '.swiper-pagination',
              clickable: true
            },
            // 如果需要前进后退按钮
            navigation: {
              nextEl: '.swiper-button-next',
              prevEl: '.swiper-button-prev',
            },
          });
          //鼠标移出隐藏按钮，移入显示按钮
          mySwiper.el.onmouseenter=function(){
            mySwiper.navigation.$nextEl.removeClass('hide');
            mySwiper.navigation.$prevEl.removeClass('hide');
          }
          mySwiper.el.onmouseleave=function(){
            mySwiper.navigation.$nextEl.addClass('hide');
            mySwiper.navigation.$prevEl.addClass('hide');
          }
          //鼠标移动到轮播图上禁止自动切换
          mySwiper.el.onmouseover = function(){
            mySwiper.autoplay.stop();
          }
          mySwiper.el.onmouseout = function(){
            mySwiper.autoplay.start();
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.swiper .hide{
  opacity:0;
}
.swiper-button-next,.swiper-button-prev{
  color: #666666;
  transition:opacity 0.5s;
}
.swiper-button-next:after .swiper-button-prev:after {
  font-size: 10px;
}
</style>
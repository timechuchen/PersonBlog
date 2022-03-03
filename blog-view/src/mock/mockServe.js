//先引入mockjs模块
import Mock from 'mockjs';
//把那些JSON类型的数据格式引入进来
import banner from './banner.json';
import blogList from './blogList.json';
import personInfo from './personInfo.json';
import hot from './hot.json';
import recode from './recode.json';
import words from './words.json';
import blogDetails from './blogDetails.json';

//mock数据：第一个参数是请求的地址，第二个参数是请求的数据
Mock.mock('/mock/banner',{code:200,data:banner});  //模拟轮播图的数据
Mock.mock('/mock/blogList',{code:200,data:blogList});  //模拟的是博客列表数据
Mock.mock('/mock/personInfo',{code:200,data:personInfo});  //模拟的是个人信息
Mock.mock('/mock/hot',{code:200,data:hot});  //模拟的是个人信息
Mock.mock('/mock/diary',{code:200,data:recode}); //模拟日志信息
Mock.mock('/mock/words',{code:200,data:words}); //模拟用户留言信息
Mock.mock('/mock/blog',{code:200,data:blogDetails});
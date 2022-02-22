//先引入mockjs模块
import Mock from 'mockjs';
//把那些JSON类型的数据格式引入进来
import banner from './banner.json';
import blogList from './blogList.json';

//mock数据：第一个参数是请求的地址，第二个参数是请求的数据
Mock.mock('/mock/banner',{code:200,data:banner});  //模拟轮播图的数据
Mock.mock('/mock/blogList',{code:200,data:blogList});  //模拟的是博客列表数据
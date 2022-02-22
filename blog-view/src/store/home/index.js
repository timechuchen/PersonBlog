//home模块的小仓库
// import {reqGitHubList} from '@/api'; 引入请求函数，但是我还没写后台，这里就先不用，用，模拟数据

//仓库存储数据的地方
const state = {
    //state中的默认初始数据不要瞎写，一般要和接口返回的数据类型要一致
    blogList: [],
};
//修改state的唯一手段
const mutations = {
    BLOGLIST(state,blogList){
        state.blogList = blogList;
    }
};
//处理action，可以书写自己的业务逻辑，也可以处理异步
const actions = {
    //通过API中的接口函数调用，模拟像后台发送请求
    blogList({commit}) {
        // let blogs = reqBlogList();
        //模拟的数据
        let blogs = {
            code: 200,
            message: '成功',
            data: [
                {
                    blogId: 1001,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                                '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1002,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: null,
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1003,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1004,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1005,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1006,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1007,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1008,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: null,
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1009,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: null,
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                },
                {
                    blogId: 1010,
                    blogTitle: 'Java程序员很卷吗？',
                    author: '初晨',
                    blogPic: './images/temp/art.jpg',
                    blogTage: '程序人生',
                    createTime: '2022-02-22',
                    hot: 255,
                    hits: 12,
                    synopsis: '不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。' +
                        '每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp;\n' +
                        '&nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是'
                }
            ]
        }
        if(blogs.code === 200) {
            commit('BLOGLIST',blogs.data);
        }
    }
};
//可以理解为计算属性，用于简化仓库数据，让数组获取仓库的数据更加方便
const getters = {};

export default {
    state,
    mutations,
    actions,
    getters
}
module.exports = {
    //关闭 eslint 语法检查
    lintOnSave: false,
    devServer: {
        //自启动浏览器
        open: true,
        //代理服务器
        proxy: {
            '/api': {
                target: 'http://localhost:8089',
            },
        },
        port: 8081
    }
}
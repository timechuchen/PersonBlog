module.exports = {
    //关闭 eslint 语法检查
    lintOnSave: false,
    //开启代理服务器（方式一）
    /* devServer: {
      proxy: 'http://localhost:5000'
    }, */
    //开启代理服务器（方式二）
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8089',
            },
        },
    }
}
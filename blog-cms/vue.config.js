'use strict'
const path = require('path')
require('./src/settings.js');
function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {
    //关闭 eslint 语法检查
    lintOnSave: false,
    devServer: {
        //自启动浏览器
        open: true,
        //代理服务器
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8089',
            },
        },
        port: 8081,
    },

    chainWebpack(config) {
        // it can improve the speed of the first screen, it is recommended to turn on preload
        config.plugin('preload').tap(() => [
            {
                rel: 'preload',
                fileBlacklist: [/\.map$/, /hot-update\.js$/, /runtime\..*\.js$/],
                include: 'initial'
            }
        ])

        // when there are many pages, it will cause too many meaningless requests
        config.plugins.delete('prefetch')

        // set svg-sprite-loader
        config.module
            .rule('svg')
            .exclude.add(resolve('src/icons'))
            .end()
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()
    }
}
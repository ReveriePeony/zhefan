module.exports = {
    parallel: false, // 关闭thread-loader
    devServer: {
        open: true,
        port: 8099,
        proxy: {
            '/': {
                // 目标 API 地址
                // target: "http://192.168.1.145:8020/",
                target: 'http://192.168.1.232:9080/',
                // 如果要代理 websockets
                ws: false,
                // 将主机标头的原点更改为目标URL
                changeOrigin: false,
                pathRewrite: {
                    '^/': ''
                }
            }
        },
        disableHostCheck: true
    },
    configureWebpack: {
        // 配置项目别名
        resolve: {
            alias: {
                assets: '@/assets',
                commom: '@/commom',
                components: '@/components',
                views: '@/views',
                utils: '@/utils'
            }
        }
    }
}

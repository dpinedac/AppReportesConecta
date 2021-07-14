// vue.config.js
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8183',
                ws: true,
                changeOrigin: true,
                pathRewrite: { '^/api': '/api' },
            }
        }
    }
}
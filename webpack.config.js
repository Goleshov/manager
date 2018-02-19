module.exports = {
    context: __dirname,
    devtool: "source-map",
    entry: "./src/main/resources/js/index.jsx",
    output: {
        path: __dirname,
        filename: "./src/main/resources/static/built/bundle.js"
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                query: {
                    presets: ['react', 'es2015']

                }
            }

        ]
    },

};

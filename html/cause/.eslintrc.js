module.exports = {
    root: true,
    env: {
        node: true
    },
    extends: ['plugin:vue/essential', '@vue/prettier'],
    rules: {
        'generator-star-spacing': 'off',
        indent: [
            'warn',
            4,
            {
                SwitchCase: 1
            }
        ],
        'eol-last': 'off',
        'space-before-function-paren': 'off',
        'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'prettier/prettier': [
            'warn',
            {
                tabWidth: 4,
                printWidth: 120,
                semi: false,
                singleQuote: true
            }
        ]
    },
    parserOptions: {
        parser: 'babel-eslint'
    }
}

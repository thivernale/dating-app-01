import js from '@eslint/js';
import globals from 'globals';
import react from 'eslint-plugin-react';
import reactHooks from 'eslint-plugin-react-hooks';
import reactRefresh from 'eslint-plugin-react-refresh';
import eslintPluginPrettier from 'eslint-plugin-prettier/recommended';
import tseslint from 'typescript-eslint';

export default tseslint
  .config(
    { ignores: ['dist', 'src/generated'] },
    {
      extends: [js.configs.recommended, ...tseslint.configs.recommended],
      files: ['**/*.{ts,tsx}'],
      languageOptions: {
        ecmaVersion: 2020,
        globals: globals.browser,
        parserOptions: {
          project: ['./tsconfig.app.json', './tsconfig.node.json'],
          tsconfigRootDir: import.meta.dirName,
        },
      },
      settings: { react: { version: '19.0' } },
      plugins: {
        react,
        'react-hooks': reactHooks,
        'react-refresh': reactRefresh,
      },
      rules: {
        ...reactHooks.configs.recommended.rules,
        ...react.configs.recommended.rules,
        ...react.configs['jsx-runtime'].rules,
        'react-refresh/only-export-components': [
          'warn',
          { allowConstantExport: true },
        ],
        '@typescript-eslint/prefer-nullish-coalescing': 'error',
      },
    },
  )
  .concat(eslintPluginPrettier);

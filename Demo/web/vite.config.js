import { resolve } from 'path';
import { defineConfig } from 'vite';

const page = (relativePath) => resolve(__dirname, relativePath);

export default defineConfig({
  root: __dirname,
  server: {
    port: 5173,
    open: '/pages/login/loginPage.html',
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  },
  build: {
    rollupOptions: {
      input: {
        login: page('pages/login/loginPage.html'),
        list: page('pages/book-list/bookListPage.html'),
        detail: page('pages/book-detail/bookDetailPage.html'),
        create: page('pages/book-create/bookCreatePage.html'),
        edit: page('pages/book-edit/bookEditPage.html'),
        delete: page('pages/book-delete/bookDeletePage.html')
      }
    }
  }
});


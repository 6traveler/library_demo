## 数据库初始化

1. 确保本地安装并启动 MySQL，创建名为 `booklist` 的数据库。
2. 运行 `db/booklist.sql`，会自动创建 `booklist` 表并导入四本示例数据。
3. 根据本地数据库账号修改 `src/main/java/config/DatabaseConfig.java` 中的 `JDBC_URL` / `JDBC_USERNAME` / `JDBC_PASSWORD`。
4. 项目使用 `com.mysql.cj.jdbc.Driver`，请确保 IDEA 运行环境中已引入 `mysql-connector-j` 对应版本。

## 后端（IDEA / bookcrud）

- 目录：`src/main/java`
- 架构：标准 Servlet + Service + DAO 分层，端口固定暴露 `http://127.0.0.1:8080/api`。
- 依赖：`WEB-INF/lib/` 已提供 `gson-2.8.6.jar`、`mysql-connector-j-8.0.31.jar` 等，如 IDEA 报红按需导入。
- 主要接口：
  - `GET /api/books` / `GET /api/books/{id}`
  - `POST /api/books`
  - `PUT /api/books/{id}`
  - `DELETE /api/books/{id}`
  - 兼容老页面的 `/getBookList`、`/getBookDetails`

## 前端（VS Code / web）

- 目录：`web/`，使用 **Vite 多页面** 架构，不再依赖 Tomcat 直接渲染前端。
- 登录入口：`pages/login/loginPage.html`（默认账号：`admin` / `123456`）。
- 业务页面：
  - `pages/book-list/bookListPage.html`
  - `pages/book-detail/bookDetailPage.html?id=1`
  - `pages/book-create/bookCreatePage.html`
  - `pages/book-edit/bookEditPage.html?id=1`
  - `pages/book-delete/bookDeletePage.html?id=1`
- API 统一通过 `window.__BOOKLIST_API__`（默认 `http://127.0.0.1:8080/api`）访问后端。

### 启动前端
```bash
cd web
npm install
npm run dev
```
开发服务器默认打开 `http://localhost:5173/pages/login/loginPage.html`。登录成功后即可访问其它 CRUD 页面，如需切换后端地址，可在浏览器执行 `window.__BOOKLIST_API__='http://your-host:port/api'`。

构建发布：
```bash
npm run build
```
产物会输出至 `web/dist/`，可由任意静态服务器托管。

## 后端默认页

Tomcat 启动后访问 `http://127.0.0.1:8080/` 会显示 `backend-info.html`，提示“后端仅提供 API，请在 VS Code 里运行前端”。这样可以避免每次启动都弹出 404 页面，后端仅需保持运行即可。

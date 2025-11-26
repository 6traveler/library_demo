/**
 * API 客户端，封装所有与后端的 HTTP 调用。
 */
const API_BASE_URL = window.__BOOKLIST_API__ || '/api';
const TOKEN_KEY = 'booklist_token';
const USERNAME_KEY = 'booklist_username';

const defaultHeaders = {
    'Content-Type': 'application/json'
};

/**
 * 构建请求头，自动附带 Authorization。
 */
function buildHeaders(extraHeaders = {}, token) {
    const headers = { ...defaultHeaders, ...extraHeaders };
    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }
    return headers;
}

function getStoredToken() {
    try {
        return window.localStorage.getItem(TOKEN_KEY);
    } catch (error) {
        console.warn('无法读取本地 token', error);
        return undefined;
    }
}

function clearStoredAuth() {
    try {
        window.localStorage.removeItem(TOKEN_KEY);
        window.localStorage.removeItem(USERNAME_KEY);
    } catch (error) {
        console.warn('清理本地登录状态失败', error);
    }
}

function goLogin() {
    if (typeof window !== 'undefined') {
        if (!window.location.pathname.endsWith('/pages/login/loginPage.html')) {
            window.location.href = '/pages/login/loginPage.html';
        }
    }
}

/**
 * 统一的 fetch 包装器，处理 JSON 解析与错误抛出。
 */
async function request(path, { method = 'GET', data, headers = {}, token } = {}) {
    const resolvedToken = token ?? getStoredToken();
    const options = {
        method,
        headers: buildHeaders(headers, resolvedToken)
    };

    if (data !== undefined) {
        options.body = typeof data === 'string' ? data : JSON.stringify(data);
    }

    let response;
    try {
        response = await fetch(`${API_BASE_URL}${path}`, options);
    } catch (error) {
        throw new Error('无法连接服务器，请稍后再试。');
    }

    const isJson = response.headers.get('content-type')?.includes('application/json');
    const payload = isJson ? await response.json() : await response.text();

    if (!response.ok) {
        if (response.status === 401) {
            clearStoredAuth();
            goLogin();
        }
        const message = payload?.message || payload?.content?.message || response.statusText;
        throw new Error(message || '服务器错误');
    }

    if (payload?.statusCode && payload?.statusCode !== '200') {
        const message = payload?.content?.message || '服务器处理失败';
        throw new Error(message);
    }

    if (payload?.content?.message) {
        return payload.content.message;
    }

    return payload;
}

/**
 * 导出具体的业务接口函数。
 */
export const api = {
    login: (credentials) => request('/login', { method: 'POST', data: credentials }),
    getBooks: ({ query } = {}, token) => request(`/books${query ? `?${query}` : ''}`, { token }),
    getBook: (id, token) => request(`/books/${id}`, { token }),
    createBook: (payload, token) => request('/books', { method: 'POST', data: payload, token }),
    updateBook: (id, payload, token) => request(`/books/${id}`, { method: 'PUT', data: payload, token }),
    deleteBook: (id, token) => request(`/books/${id}`, { method: 'DELETE', token })
};


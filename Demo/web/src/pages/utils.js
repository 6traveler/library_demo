/**
 * 解析当前 URL 中的查询参数。
 */
const LOGIN_PAGE = '/pages/login/loginPage.html';
const HOME_PAGE = '/pages/book-list/bookListPage.html';
const REMEMBER_CHOICE_KEY = 'booklist_remember_choice';

export function getQueryParam(key) {
    const params = new URLSearchParams(window.location.search);
    return params.get(key);
}

/**
 * 将表单元素转换为普通对象。
 */
export function formToObject(form) {
    const formData = new FormData(form);
    return Object.fromEntries(formData.entries());
}

/**
 * 将图书对象渲染到指定 DOM 字段中。
 */
export function fillFields(map, data) {
    Object.entries(map).forEach(([selector, key]) => {
        const el = document.querySelector(selector);
        if (el) {
            el.textContent = data[key] ?? '';
        }
    });
}

export function getToken() {
    return window.localStorage.getItem('booklist_token');
}

export function getUsername() {
    return window.localStorage.getItem('booklist_username');
}

export function redirectToLogin(reason) {
    if (reason) {
        console.warn('未登录或登录已失效：', reason);
    }
    window.location.href = LOGIN_PAGE;
}

export function redirectToLoginTarget() {
    window.location.href = HOME_PAGE;
}

export function requireAuth() {
    if (!getToken()) {
        redirectToLogin();
        throw new Error('需要登录');
    }
}

export function logout() {
    window.localStorage.removeItem('booklist_token');
    window.localStorage.removeItem('booklist_username');
    redirectToLogin();
}

export function setupAuthUI() {
    const usernameEl = document.getElementById('usernameText');
    if (usernameEl) {
        usernameEl.textContent = getUsername() || '未登录';
    }
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', logout);
    }
}

export function rememberChoiceAccepted() {
    try {
        return window.localStorage.getItem(REMEMBER_CHOICE_KEY) === 'accepted';
    } catch (error) {
        console.warn('读取免登录偏好失败', error);
        return false;
    }
}

export function setRememberChoice(accepted) {
    try {
        if (accepted) {
            window.localStorage.setItem(REMEMBER_CHOICE_KEY, 'accepted');
        } else {
            window.localStorage.removeItem(REMEMBER_CHOICE_KEY);
        }
    } catch (error) {
        console.warn('写入免登录偏好失败', error);
    }
}


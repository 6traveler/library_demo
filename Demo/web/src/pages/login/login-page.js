import { api } from '../../api.js';
import {
    formToObject,
    getToken,
    redirectToLoginTarget,
    rememberChoiceAccepted,
    setRememberChoice
} from '../utils.js';

const form = document.getElementById('loginForm');
const statusText = document.getElementById('statusText');
const rememberDialog = document.getElementById('rememberDialog');
const rememberCheckbox = document.getElementById('rememberCheckbox');
const rememberConfirmBtn = document.getElementById('rememberConfirmBtn');
const rememberCancelBtn = document.getElementById('rememberCancelBtn');

let pendingPayload = null;

if (rememberChoiceAccepted() && getToken()) {
    redirectToLoginTarget();
}

form.addEventListener('submit', (event) => {
    event.preventDefault();
    pendingPayload = formToObject(form);
    openRememberDialog();
});

rememberCancelBtn.addEventListener('click', () => {
    pendingPayload = null;
    statusText.textContent = '已取消登录';
    closeRememberDialog();
});

rememberConfirmBtn.addEventListener('click', () => {
    if (!pendingPayload) {
        closeRememberDialog();
        return;
    }
    const payload = { ...pendingPayload };
    const rememberSelection = rememberCheckbox.checked;
    closeRememberDialog();
    attemptLogin(payload, rememberSelection);
});

function openRememberDialog() {
    rememberDialog.removeAttribute('hidden');
}

function closeRememberDialog() {
    rememberDialog.setAttribute('hidden', 'true');
    rememberCheckbox.checked = false;
}

async function attemptLogin(payload, rememberSelection) {
    statusText.textContent = '正在登录...';
    try {
        const { token, username } = await api.login({ ...payload, rememberMe: rememberSelection });
        localStorage.setItem('booklist_token', token);
        localStorage.setItem('booklist_username', username);
        setRememberChoice(rememberSelection);
        statusText.textContent = rememberSelection
            ? '已开启免登录，正在跳转...'
            : '登录成功，正在跳转...';
        redirectToLoginTarget();
    } catch (error) {
        statusText.textContent = error.message || '登录失败';
    } finally {
        pendingPayload = null;
    }
}


import { api } from '../../api.js';
import { formToObject, requireAuth, setupAuthUI } from '../utils.js';

requireAuth();
setupAuthUI();

const form = document.getElementById('createForm');
const statusText = document.getElementById('statusText');

/**
 * 处理新增表单提交。
 */
async function handleSubmit(event) {
    event.preventDefault();
    const payload = formToObject(form);
    statusText.textContent = '正在新增...';
    payload.count = Number(payload.count);
    payload.price = Number(payload.price).toFixed(2);
    try {
        await api.createBook(payload);
        statusText.textContent = '新增成功，即将返回列表';
        setTimeout(() => {
            window.location.href = '../book-list/bookListPage.html';
        }, 800);
    } catch (error) {
        statusText.textContent = error.message || '新增失败';
    }
}

form.addEventListener('submit', handleSubmit);


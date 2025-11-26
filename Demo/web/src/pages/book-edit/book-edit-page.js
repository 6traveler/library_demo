import { api } from '../../api.js';
import { getQueryParam, formToObject, requireAuth, setupAuthUI } from '../utils.js';

requireAuth();
setupAuthUI();

const form = document.getElementById('editForm');
const statusText = document.getElementById('statusText');
const reloadBtn = document.getElementById('reloadBtn');
const backBtn = document.getElementById('backBtn');
const bookId = getQueryParam('id');

if (!bookId) {
    statusText.textContent = '缺少图书编号';
}

/**
 * 将服务器返回的数据填充到表单。
 */
function fillForm(book) {
    Object.entries(book).forEach(([key, value]) => {
        if (form.elements[key]) {
            form.elements[key].value = value ?? '';
        }
    });
}

/**
 * 重新拉取数据。
 */
async function loadBook() {
    if (!bookId) return;
    statusText.textContent = '正在加载图书...';
    try {
        const book = await api.getBook(bookId);
        fillForm(book);
        statusText.textContent = '已加载';
    } catch (error) {
        statusText.textContent = error.message || '加载失败';
    }
}

/**
 * 保存编辑结果。
 */
async function handleSubmit(event) {
    event.preventDefault();
    const payload = formToObject(form);
    payload.count = Number(payload.count);
    payload.price = Number(payload.price).toFixed(2);
    statusText.textContent = '正在保存...';
    try {
        await api.updateBook(bookId, payload);
        statusText.textContent = '保存成功，正在返回列表';
        setTimeout(() => window.location.href = '../book-list/bookListPage.html', 800);
    } catch (error) {
        statusText.textContent = error.message || '保存失败';
    }
}

form.addEventListener('submit', handleSubmit);
reloadBtn.addEventListener('click', loadBook);
backBtn.addEventListener('click', () => window.location.href = '../book-list/bookListPage.html');
loadBook();


import { api } from '../../api.js';
import { getQueryParam, fillFields, requireAuth, setupAuthUI } from '../utils.js';

requireAuth();
setupAuthUI();

const statusText = document.getElementById('statusText');
const detailsPanel = document.getElementById('detailsPanel');
const coverImg = document.getElementById('coverImg');
const confirmBtn = document.getElementById('confirmBtn');
const bookId = getQueryParam('id');

/**
 * 加载待删除的图书信息。
 */
async function loadBook() {
    if (!bookId) {
        statusText.textContent = '缺少图书编号';
        confirmBtn.disabled = true;
        return;
    }
    statusText.textContent = '正在加载图书信息...';
    try {
        const book = await api.getBook(bookId);
        fillFields({
            '#bookId': 'id',
            '#bookName': 'bookName',
            '#author': 'author',
            '#price': 'price',
            '#count': 'count'
        }, book);
        coverImg.src = book.img || '/img/红楼梦.jpg';
        coverImg.alt = book.bookName || '图书封面';
        statusText.textContent = '请确认是否删除';
        detailsPanel.hidden = false;
    } catch (error) {
        statusText.textContent = error.message || '加载失败';
        confirmBtn.disabled = true;
    }
}

/**
 * 调用删除接口。
 */
async function handleDelete() {
    statusText.textContent = '正在删除...';
    confirmBtn.disabled = true;
    try {
        await api.deleteBook(bookId);
        statusText.textContent = '删除成功，正在返回列表';
        setTimeout(() => window.location.href = '../book-list/bookListPage.html', 800);
    } catch (error) {
        confirmBtn.disabled = false;
        statusText.textContent = error.message || '删除失败';
    }
}

confirmBtn.addEventListener('click', handleDelete);
loadBook();


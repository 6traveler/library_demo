import { api } from '../../api.js';
import { getQueryParam, fillFields, requireAuth, setupAuthUI } from '../utils.js';

requireAuth();
setupAuthUI();

const statusText = document.getElementById('statusText');
const detailsPanel = document.getElementById('detailsPanel');
const coverImg = document.getElementById('coverImg');

/**
 * 加载并展示图书详情。
 */
async function loadDetails() {
    const id = getQueryParam('id');
    if (!id) {
        statusText.textContent = '缺少图书编号';
        return;
    }
    statusText.textContent = '正在加载图书详情...';
    detailsPanel.hidden = true;
    try {
        const book = await api.getBook(id);
        fillFields({
            '#bookId': 'id',
            '#bookName': 'bookName',
            '#author': 'author',
            '#isbn': 'ISBN',
            '#price': 'price',
            '#count': 'count',
            '#content': 'content'
        }, book);
        coverImg.src = book.img || '/img/红楼梦.jpg';
        coverImg.alt = book.bookName || '图书封面';
        detailsPanel.hidden = false;
        statusText.textContent = '';
    } catch (error) {
        statusText.textContent = error.message || '加载失败';
    }
}

loadDetails();


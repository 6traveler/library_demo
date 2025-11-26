import { api } from '../../api.js';
import { requireAuth, setupAuthUI } from '../utils.js';

requireAuth();
setupAuthUI();

const statusText = document.getElementById('statusText');
const tbody = document.getElementById('listBody');
const refreshBtn = document.getElementById('refreshBtn');

/**
 * 渲染单行数据。
 */
function renderRow(book) {
    const tr = document.createElement('tr');
    tr.innerHTML = `
        <td>${book.id}</td>
        <td>${book.bookName}</td>
        <td>${book.author}</td>
        <td>${book.count}</td>
        <td>¥${book.price}</td>
        <td>
            <a class="btn btn-light" href="../book-detail/bookDetailPage.html?id=${book.id}">详情</a>
            <a class="btn btn-outline" href="../book-edit/bookEditPage.html?id=${book.id}">编辑</a>
            <a class="btn btn-danger" href="../book-delete/bookDeletePage.html?id=${book.id}">删除</a>
        </td>
    `;
    return tr;
}

/**
 * 拉取列表并渲染。
 */
async function loadBooks() {
    statusText.textContent = '正在加载图书数据...';
    tbody.replaceChildren();
    try {
        const books = await api.getBooks();
        if (!books.length) {
            statusText.textContent = '暂无数据';
            return;
        }
        books.forEach((book) => tbody.appendChild(renderRow(book)));
        statusText.textContent = `共 ${books.length} 本图书`;
    } catch (error) {
        statusText.textContent = error.message || '加载失败';
    }
}

refreshBtn.addEventListener('click', loadBooks);
loadBooks();


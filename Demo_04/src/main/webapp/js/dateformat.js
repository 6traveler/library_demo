//时间戳转换成标准时间格式yy-mm-ss
function formatDate(datetime) {
    let date = new Date(datetime); //datetime时间戳为13位毫秒级别,如为10位需乘1000
    let month = ("0" + (date.getMonth() + 1)).slice(-2),	// getMonth是从1-11,所以需要加1
        day = ("0" + date.getDate()).slice(-2);	// -2表示从倒数第二个元素开始截取
    let thatDate = date.getFullYear() + "-" + month + "-" + day;
    // 返回
    return thatDate;
}
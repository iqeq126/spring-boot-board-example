 function adjustHeader() {
    var width = window.innerWidth;
    if (width < 768) {
        document.getElementById('sidebar-menu').style.display = 'block';
        document.getElementById('normal-menu').style.display = 'none';
    } else {
        document.getElementById('normal-menu').style.display = 'none';
        document.getElementById('sidebar-menu').style.display = 'block';
}
}
window.onload = adjustHeader;
window.onresize = adjustHeader;
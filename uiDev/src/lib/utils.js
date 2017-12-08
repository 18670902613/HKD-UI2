let Utils = {
    domClass: {
        addClass: (obj, cls) => {
            let obj_class = obj.className, //获取 class 内容.
                blank = (obj_class != '') ? ' ' : '', //判断获取到的 class 是否为空, 如果不为空在前面加个'空格'.
                added = obj_class + blank + cls; //组合原来的 class 和需要添加的 class.
            obj.className = added; //替换原来的 class.
        },
        removeClass: (obj, cls) => {
            let obj_class = ' ' + obj.className + ' '; //获取 class 内容, 并在首尾各加一个空格. ex) 'abc    bcd' -> ' abc    bcd '
            obj_class = obj_class.replace(/(\s+)/gi, ' '); //将多余的空字符替换成一个空格. ex) ' abc    bcd ' -> ' abc bcd '
            let removed = obj_class.replace(' ' + cls + ' ', ' '); //在原来的 class 替换掉首尾加了空格的 class. ex) ' abc bcd ' -> 'bcd '
            removed = removed.replace(/(^\s+)|(\s+$)/g, ''); //去掉首尾空格. ex) 'bcd ' -> 'bcd'
            obj.className = removed; //替换原来的 class.
        },
        hasClass: (obj, cls) => {
            let obj_class = obj.className, //获取 class 内容.
                obj_class_lst = obj_class.split(/\s+/), //通过split空字符将cls转换成数组.
                x = 0;
            for (x of obj_class_lst) {
                if (x == cls) { //循环数组, 判断是否包含cls
                    return true;
                }
            }
            return false;
        }
    },
    // 鼠标经过溢出文字是显示tooltip
    // ellipsis:() => {
    //     let eles = document.getElementsByClassName('ellipsis');
    //     for (let ele of eles) {
    //         ele.onmouseenter = function(){
    //             let tooltipPoper = document.createElement('div'),
    //             tooltipContent = document.createElement('div'),
    //             tooltipArrow = document.createElement('div'),
    //             tooltipInner = document.createElement('div'),
    //             tooltipText= document.createTextNode(ele.innerText);

    //             tooltipPoper.className="ivu-tooltip-popper";
    //             tooltipContent.className="ivu-tooltip-content";
    //             tooltipArrow.className="ivu-tooltip-arrow";
    //             tooltipInner.className="ivu-tooltip-inner";

    //             tooltipInner.appendChild(tooltipText);
    //             tooltipContent.appendChild(tooltipArrow);
    //             tooltipContent.appendChild(tooltipInner);
    //             tooltipPoper.appendChild(tooltipContent);

    //             ele.parentNode.appendChild(tooltipPoper);
    //         }
    //     }
    // }

}

export {
    Utils
}

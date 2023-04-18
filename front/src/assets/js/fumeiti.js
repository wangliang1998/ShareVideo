var browser = {
    ie6: function() {
        return ((window.XMLHttpRequest == undefined) && (ActiveXObject != undefined))
    },
    getWindow: function() {
        var myHeight = 0;
        var myWidth = 0;
        if (typeof(window.innerWidth) == 'number') {
            myHeight = window.innerHeight;
            myWidth = window.innerWidth
        } else if (document.documentElement) {
            myHeight = document.documentElement.clientHeight;
            myWidth = document.documentElement.clientWidth
        } else if (document.body) {
            myHeight = document.body.clientHeight;
            myWidth = document.body.clientWidth
        }
        return {
            'height': myHeight,
            'width': myWidth
        }
    },
    getScroll: function() {
        var myHeight = 0;
        var myWidth = 0;
        if (typeof(window.pageYOffset) == 'number') {
            myHeight = window.pageYOffset;
            myWidth = window.pageXOffset
        } else if (document.documentElement) {
            myHeight = document.documentElement.scrollTop;
            myWidth = document.documentElement.scrollLeft
        } else if (document.body) {
            myHeight = document.body.scrollTop;
            myWidth = document.body.scrollLeft
        }
        return {
            'height': myHeight,
            'width': myWidth
        }
    },
    getDocWidth: function(D) {
        if (!D) var D = document;
        return Math.max(Math.max(D.body.scrollWidth, D.documentElement.scrollWidth), Math.max(D.body.offsetWidth, D.documentElement.offsetWidth), Math.max(D.body.clientWidth, D.documentElement.clientWidth))
    },
    getDocHeight: function(D) {
        if (!D) var D = document;
        return Math.max(Math.max(D.body.scrollHeight, D.documentElement.scrollHeight), Math.max(D.body.offsetHeight, D.documentElement.offsetHeight), Math.max(D.body.clientHeight, D.documentElement.clientHeight))
    }
};
var dom = {
    ID: function(id) {
        var type = typeof(id);
        if (type == 'object') return id;
        if (type == 'string') return document.getElementById(id);
        return null
    },
    insertHtml: function(html) {
        var frag = document.createDocumentFragment();
        var div = document.createElement("div");
        div.innerHTML = html;
        for (var i = 0,
        ii = div.childNodes.length; i < ii; i++) {
            frag.appendChild(div.childNodes[i])
        }
        document.body.insertBefore(frag, document.body.firstChild)
    }
};
var myEvent = {
    add: function(element, type, handler) {
        var ele = dom.ID(element);
        if (!ele) return;
        if (ele.addEventListener) ele.addEventListener(type, handler, false);
        else if (ele.attachEvent) ele.attachEvent("on" + type, handler);
        else ele["on" + type] = handler
    },
    remove: function(element, type, handler) {
        var ele = dom.ID(element);
        if (!ele) return;
        if (ele.removeEventListener) ele.removeEventListener(type, handler, false);
        else if (ele.detachEvent) ele.detachEvent("on" + type, handler);
        else ele["on" + type] = null
    }
};
var positionRightBottom = {
    Center: function(id) {
        var id = dom.ID(id);
        var ie6 = browser.ie6();
        var win = browser.getWindow();
        var ele = {
            'height': id.clientHeight,
            'width': id.clientWidth
        };
        if (ie6) {
            var scrollBar = browser.getScroll()
        } else {
            var scrollBar = {
                'height': 0,
                'width': 0
            };
            id.style.position = 'fixed'
        }
        id.style.right = '0px';
        id.style.bottom = '0px';
		//id.style.right = '3px'
    },
    floatCenter: function(id) {
        positionRightBottom.Center(id);
        var fun = function() {
            positionRightBottom.Center(id)
        };
        if (browser.ie6()) {
            myEvent.add(window, 'scroll', fun);
            myEvent.add(window, 'resize', fun)
        } else {
            myEvent.add(window, 'resize', fun)
        }
    }
};
function ad_rightBottom(){
	var html="<div id=\"ad_rightBottom\" style=\"position: fixed; z-index: 10001; bottom: 0;right:0;\"><a style=\"position:absolute;top:-15px;left:0;\" href=\"javascript:void(0);\" onclick=\"document.getElementById(\'ad_rightBottom\').style.display=\'none\'\">关闭</a><a href=\""+fUrl+"\" target=\"_blank\" style=\"display:block;\"><img src=\""+fSrc+"\" width=\"300\" height=\"250\"></a></div>";
	dom.insertHtml(html);positionRightBottom.floatCenter('ad_rightBottom');
}
myEvent.add(window,'load',ad_rightBottom);
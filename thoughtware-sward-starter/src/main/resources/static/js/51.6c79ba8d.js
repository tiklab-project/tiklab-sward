(window.webpackJsonp=window.webpackJsonp||[]).push([[51],{1014:function(e,t,n){"use strict";n.r(t),n.d(t,"default",(function(){return y}));n(35);var r=n(36),o=(n(37),n(38)),i=n(0),a=n.n(i),c=n(26);Object(c.a)(".setting-home-row {\n  height: calc(100vh - 49px);\n  overflow: auto;\n}\n\n.setting-home {\n  padding: var(--thoughtware-padding);\n}\n.setting-home .setting-home-block {\n  margin-bottom: 20px;\n}\n.setting-home .setting-home-block .setting-home-block-title {\n  height: 30px;\n  line-height: 30px;\n  margin-bottom: 10px;\n  font-weight: 600;\n  font-size: 16px;\n}\n.setting-home .setting-home-block .setting-home-block-content {\n  display: grid;\n  grid-template-columns: repeat(5, 1fr);\n  grid-gap: 15px;\n  line-height: 30px;\n}\n.setting-home .setting-home-block .setting-home-block-content .setting-home-block-content-item {\n  background-color: var(--thoughtware-gray-700);\n  border-radius: 5px;\n  padding: 15px;\n  text-align: center;\n  height: 90px;\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  flex-direction: column;\n}\n.setting-home .setting-home-block .setting-home-block-content .setting-home-block-content-item .module-title {\n  font-size: 12px;\n  font-weight: 600;\n  color: #888;\n  display: flex;\n  align-items: center;\n  gap: 5px;\n}\n.setting-home .setting-home-block .setting-home-block-content .setting-home-block-content-item:hover {\n  color: var(--thoughtware-blue);\n  cursor: pointer;\n}\n.setting-home .setting-home-block .setting-home-block-content .setting-home-block-content-item:hover .module-title {\n  color: var(--thoughtware-blue);\n}");var l=n(290),u=n(22),s=n(15),h=n(163),f="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/setting/home/components/SettingHome.js";function m(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==n)return;var r,o,i=[],a=!0,c=!1;try{for(n=n.call(e);!(a=(r=n.next()).done)&&(i.push(r.value),!t||i.length!==t);a=!0);}catch(e){c=!0,o=e}finally{try{a||null==n.return||n.return()}finally{if(c)throw o}}return i}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return p(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);"Object"===n&&e.constructor&&(n=e.constructor.name);if("Map"===n||"Set"===n)return Array.from(e);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return p(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function p(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var y=Object(u.observer)((function(e){var t,n=l.a.findOrgaNum;l.a.selectKey;var c=l.a.setSelectKey,u=m(Object(i.useState)({}),2),p=u[0],y=u[1],g=null===(t=JSON.parse(localStorage.getItem("authConfig")))||void 0===t?void 0:t.authType,d=Object(s.getVersionInfo)(),v=m(Object(i.useState)(!1),2),b=v[0],w=v[1];Object(i.useEffect)((function(){n().then((function(e){0===e.code&&y(e.data)}))}),[]);var k=function(t){if(t.islink&&!g){var n=JSON.parse(localStorage.getItem("authConfig")).authServiceUrl+"#"+t.path;window.open(n,"_blank")}else t.isEnhance&&d.expired?w(!0):(e.history.push(t.path),c(t.path))};return a.a.createElement(r.default,{className:"setting-home-row",__source:{fileName:f,lineNumber:207,columnNumber:9}},a.a.createElement(o.default,{xl:{span:18,offset:3},lg:{span:18,offset:3},md:{span:20,offset:2},__source:{fileName:f,lineNumber:208,columnNumber:13}},a.a.createElement("div",{className:"setting-home",__source:{fileName:f,lineNumber:209,columnNumber:17}},[{title:"用户与权限",key:"user",cloudShow:!1,eeShow:!0,children:[{title:"部门",key:"orga",islink:!0,path:"/setting/orga"},{title:"用户",key:"user",islink:!0,path:"/setting/user"},{title:"用户组",key:"userGroup",islink:!0,path:"/setting/userGroup"},{title:"用户目录",key:"userDir",islink:!0,path:"/setting/dir"},{title:"权限",key:"role",path:"/setting/systemRole"}]},{title:"权限",key:"user",cloudShow:!0,eeShow:!1,children:[{title:"权限",key:"role",path:"/setting/systemRole"}]},{title:"消息",key:"message",cloudShow:!0,eeShow:!0,children:[{title:"消息通知方案",key:"messageNotice",path:"/setting/messageNotice"},{title:"消息发送方式",key:"sendType",path:"/setting/messageSendType"}]},{title:"系统集成",key:"systemIntergrtion",cloudShow:!0,eeShow:!0,children:[{title:"地址配置",key:"systemUrl",path:"/setting/urlData"}]},{title:"安全",key:"security",cloudShow:!0,eeShow:!0,children:[{title:"操作日志",key:"logList",noShowNum:!0,path:"/setting/logList"},{title:"备份与恢复",key:"backups",noShowNum:!0,path:"/setting/backup"}]},{title:"应用",key:"systemversion",cloudShow:!1,eeShow:!0,children:[{title:"版本与许可证",key:"version",noShowNum:!0,path:"/setting/version"},{title:"应用访问权限",key:"applyAuth",path:"/setting/productAuth"}]},{title:"归档知识库",key:"archived",cloudShow:!0,eeShow:!0,children:[{title:"归档知识库",key:"archived",path:"/setting/archived",isEnhance:!0}]},{title:"回收站",key:"recycle",cloudShow:!0,eeShow:!0,children:[{title:"回收站",key:"recycle",path:"/setting/recycle",isEnhance:!0}]}].map((function(e){if(e.cloudShow,e.eeShow)return a.a.createElement("div",{className:"setting-home-block",key:e.key,__source:{fileName:f,lineNumber:241,columnNumber:40}},a.a.createElement("div",{className:"setting-home-block-title",__source:{fileName:f,lineNumber:242,columnNumber:37}},e.title),a.a.createElement("div",{className:"setting-home-block-content",__source:{fileName:f,lineNumber:243,columnNumber:37}},e.children.map((function(e){return a.a.createElement("div",{className:"setting-home-block-content-item",key:e.key,onClick:function(){return k(e)},__source:{fileName:f,lineNumber:246,columnNumber:56}},e.noShowNum?a.a.createElement("div",{className:"module-num",__source:{fileName:f,lineNumber:249,columnNumber:61}}):a.a.createElement("div",{className:"module-num",__source:{fileName:f,lineNumber:251,columnNumber:61}}," ",p[e.key]?p[e.key]:0),a.a.createElement("div",{className:"module-title",__source:{fileName:f,lineNumber:255,columnNumber:53}},e.title,d.expired&&e.isEnhance&&a.a.createElement("svg",{className:"img-icon-16","aria-hidden":"true",__source:{fileName:f,lineNumber:258,columnNumber:108}},a.a.createElement("use",{xlinkHref:"#icon-member",__source:{fileName:f,lineNumber:259,columnNumber:65}}))))}))))}))),a.a.createElement(h.a,{archivedFreeVisable:b,setArchivedFreeVisable:w,__source:{fileName:f,lineNumber:273,columnNumber:17}})))}))},290:function(e,t,n){"use strict";n.d(t,"a",(function(){return d}));var r,o,i,a,c=n(28),l=n(66);function u(e){return(u="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function s(){/*! regenerator-runtime -- Copyright (c) 2014-present, Facebook, Inc. -- license (MIT): https://github.com/facebook/regenerator/blob/main/LICENSE */s=function(){return e};var e={},t=Object.prototype,n=t.hasOwnProperty,r="function"==typeof Symbol?Symbol:{},o=r.iterator||"@@iterator",i=r.asyncIterator||"@@asyncIterator",a=r.toStringTag||"@@toStringTag";function c(e,t,n){return Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{c({},"")}catch(e){c=function(e,t,n){return e[t]=n}}function l(e,t,n,r){var o=t&&t.prototype instanceof m?t:m,i=Object.create(o.prototype),a=new E(r||[]);return i._invoke=function(e,t,n){var r="suspendedStart";return function(o,i){if("executing"===r)throw new Error("Generator is already running");if("completed"===r){if("throw"===o)throw i;return O()}for(n.method=o,n.arg=i;;){var a=n.delegate;if(a){var c=N(a,n);if(c){if(c===f)continue;return c}}if("next"===n.method)n.sent=n._sent=n.arg;else if("throw"===n.method){if("suspendedStart"===r)throw r="completed",n.arg;n.dispatchException(n.arg)}else"return"===n.method&&n.abrupt("return",n.arg);r="executing";var l=h(e,t,n);if("normal"===l.type){if(r=n.done?"completed":"suspendedYield",l.arg===f)continue;return{value:l.arg,done:n.done}}"throw"===l.type&&(r="completed",n.method="throw",n.arg=l.arg)}}}(e,n,a),i}function h(e,t,n){try{return{type:"normal",arg:e.call(t,n)}}catch(e){return{type:"throw",arg:e}}}e.wrap=l;var f={};function m(){}function p(){}function y(){}var g={};c(g,o,(function(){return this}));var d=Object.getPrototypeOf,v=d&&d(d(_([])));v&&v!==t&&n.call(v,o)&&(g=v);var b=y.prototype=m.prototype=Object.create(g);function w(e){["next","throw","return"].forEach((function(t){c(e,t,(function(e){return this._invoke(t,e)}))}))}function k(e,t){var r;this._invoke=function(o,i){function a(){return new t((function(r,a){!function r(o,i,a,c){var l=h(e[o],e,i);if("throw"!==l.type){var s=l.arg,f=s.value;return f&&"object"==u(f)&&n.call(f,"__await")?t.resolve(f.__await).then((function(e){r("next",e,a,c)}),(function(e){r("throw",e,a,c)})):t.resolve(f).then((function(e){s.value=e,a(s)}),(function(e){return r("throw",e,a,c)}))}c(l.arg)}(o,i,r,a)}))}return r=r?r.then(a,a):a()}}function N(e,t){var n=e.iterator[t.method];if(void 0===n){if(t.delegate=null,"throw"===t.method){if(e.iterator.return&&(t.method="return",t.arg=void 0,N(e,t),"throw"===t.method))return f;t.method="throw",t.arg=new TypeError("The iterator does not provide a 'throw' method")}return f}var r=h(n,e.iterator,t.arg);if("throw"===r.type)return t.method="throw",t.arg=r.arg,t.delegate=null,f;var o=r.arg;return o?o.done?(t[e.resultName]=o.value,t.next=e.nextLoc,"return"!==t.method&&(t.method="next",t.arg=void 0),t.delegate=null,f):o:(t.method="throw",t.arg=new TypeError("iterator result is not an object"),t.delegate=null,f)}function x(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function S(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function E(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(x,this),this.reset(!0)}function _(e){if(e){var t=e[o];if(t)return t.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length)){var r=-1,i=function t(){for(;++r<e.length;)if(n.call(e,r))return t.value=e[r],t.done=!1,t;return t.value=void 0,t.done=!0,t};return i.next=i}}return{next:O}}function O(){return{value:void 0,done:!0}}return p.prototype=y,c(b,"constructor",y),c(y,"constructor",p),p.displayName=c(y,a,"GeneratorFunction"),e.isGeneratorFunction=function(e){var t="function"==typeof e&&e.constructor;return!!t&&(t===p||"GeneratorFunction"===(t.displayName||t.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,y):(e.__proto__=y,c(e,a,"GeneratorFunction")),e.prototype=Object.create(b),e},e.awrap=function(e){return{__await:e}},w(k.prototype),c(k.prototype,i,(function(){return this})),e.AsyncIterator=k,e.async=function(t,n,r,o,i){void 0===i&&(i=Promise);var a=new k(l(t,n,r,o),i);return e.isGeneratorFunction(n)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},w(b),c(b,a,"Generator"),c(b,o,(function(){return this})),c(b,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t=[];for(var n in e)t.push(n);return t.reverse(),function n(){for(;t.length;){var r=t.pop();if(r in e)return n.value=r,n.done=!1,n}return n.done=!0,n}},e.values=_,E.prototype={constructor:E,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(S),!e)for(var t in this)"t"===t.charAt(0)&&n.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function r(n,r){return a.type="throw",a.arg=e,t.next=n,r&&(t.method="next",t.arg=void 0),!!r}for(var o=this.tryEntries.length-1;o>=0;--o){var i=this.tryEntries[o],a=i.completion;if("root"===i.tryLoc)return r("end");if(i.tryLoc<=this.prev){var c=n.call(i,"catchLoc"),l=n.call(i,"finallyLoc");if(c&&l){if(this.prev<i.catchLoc)return r(i.catchLoc,!0);if(this.prev<i.finallyLoc)return r(i.finallyLoc)}else if(c){if(this.prev<i.catchLoc)return r(i.catchLoc,!0)}else{if(!l)throw new Error("try statement without catch or finally");if(this.prev<i.finallyLoc)return r(i.finallyLoc)}}}},abrupt:function(e,t){for(var r=this.tryEntries.length-1;r>=0;--r){var o=this.tryEntries[r];if(o.tryLoc<=this.prev&&n.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var i=o;break}}i&&("break"===e||"continue"===e)&&i.tryLoc<=t&&t<=i.finallyLoc&&(i=null);var a=i?i.completion:{};return a.type=e,a.arg=t,i?(this.method="next",this.next=i.finallyLoc,f):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),f},finish:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.finallyLoc===e)return this.complete(n.completion,n.afterLoc),S(n),f}},catch:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.tryLoc===e){var r=n.completion;if("throw"===r.type){var o=r.arg;S(n)}return o}}throw new Error("illegal catch attempt")},delegateYield:function(e,t,n){return this.delegate={iterator:_(e),resultName:t,nextLoc:n},"next"===this.method&&(this.arg=void 0),f}},e}function h(e,t,n,r,o,i,a){try{var c=e[i](a),l=c.value}catch(e){return void n(e)}c.done?t(l):Promise.resolve(l).then(r,o)}function f(e){return function(){var t=this,n=arguments;return new Promise((function(r,o){var i=e.apply(t,n);function a(e){h(i,r,o,a,c,"next",e)}function c(e){h(i,r,o,a,c,"throw",e)}a(void 0)}))}}function m(e,t,n,r){n&&Object.defineProperty(e,t,{enumerable:n.enumerable,configurable:n.configurable,writable:n.writable,value:n.initializer?n.initializer.call(r):void 0})}function p(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function y(e,t,n){return t&&p(e.prototype,t),n&&p(e,n),Object.defineProperty(e,"prototype",{writable:!1}),e}function g(e,t,n,r,o){var i={};return Object.keys(r).forEach((function(e){i[e]=r[e]})),i.enumerable=!!i.enumerable,i.configurable=!!i.configurable,("value"in i||i.initializer)&&(i.writable=!0),i=n.slice().reverse().reduce((function(n,r){return r(e,t,n)||n}),i),o&&void 0!==i.initializer&&(i.value=i.initializer?i.initializer.call(o):void 0,i.initializer=void 0),void 0===i.initializer&&(Object.defineProperty(e,t,i),i=null),i}var d=new(o=g((r=y((function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),m(this,"selectKey",o,this),m(this,"setSelectKey",i,this),m(this,"findOrgaNum",a,this)}))).prototype,"selectKey",[c.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return"/setting/orga"}}),i=g(r.prototype,"setSelectKey",[c.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.selectKey=t}}}),a=g(r.prototype,"findOrgaNum",[c.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return f(s().mark((function e(){var t;return s().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(l.a)("/setting/findOrgaNum");case 2:return t=e.sent,e.abrupt("return",t);case 4:case"end":return e.stop()}}),e)})))}}),r)}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([[50],{290:function(e,t,r){"use strict";r.d(t,"a",(function(){return y}));var n,i,o,a,c=r(28),l=r(66);function s(e){return(s="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function u(){/*! regenerator-runtime -- Copyright (c) 2014-present, Facebook, Inc. -- license (MIT): https://github.com/facebook/regenerator/blob/main/LICENSE */u=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n="function"==typeof Symbol?Symbol:{},i=n.iterator||"@@iterator",o=n.asyncIterator||"@@asyncIterator",a=n.toStringTag||"@@toStringTag";function c(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{c({},"")}catch(e){c=function(e,t,r){return e[t]=r}}function l(e,t,r,n){var i=t&&t.prototype instanceof g?t:g,o=Object.create(i.prototype),a=new x(n||[]);return o._invoke=function(e,t,r){var n="suspendedStart";return function(i,o){if("executing"===n)throw new Error("Generator is already running");if("completed"===n){if("throw"===i)throw o;return k()}for(r.method=i,r.arg=o;;){var a=r.delegate;if(a){var c=_(a,r);if(c){if(c===m)continue;return c}}if("next"===r.method)r.sent=r._sent=r.arg;else if("throw"===r.method){if("suspendedStart"===n)throw n="completed",r.arg;r.dispatchException(r.arg)}else"return"===r.method&&r.abrupt("return",r.arg);n="executing";var l=d(e,t,r);if("normal"===l.type){if(n=r.done?"completed":"suspendedYield",l.arg===m)continue;return{value:l.arg,done:r.done}}"throw"===l.type&&(n="completed",r.method="throw",r.arg=l.arg)}}}(e,r,a),o}function d(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=l;var m={};function g(){}function f(){}function h(){}var p={};c(p,i,(function(){return this}));var y=Object.getPrototypeOf,v=y&&y(y(j([])));v&&v!==t&&r.call(v,i)&&(p=v);var b=h.prototype=g.prototype=Object.create(p);function N(e){["next","throw","return"].forEach((function(t){c(e,t,(function(e){return this._invoke(t,e)}))}))}function w(e,t){var n;this._invoke=function(i,o){function a(){return new t((function(n,a){!function n(i,o,a,c){var l=d(e[i],e,o);if("throw"!==l.type){var u=l.arg,m=u.value;return m&&"object"==s(m)&&r.call(m,"__await")?t.resolve(m.__await).then((function(e){n("next",e,a,c)}),(function(e){n("throw",e,a,c)})):t.resolve(m).then((function(e){u.value=e,a(u)}),(function(e){return n("throw",e,a,c)}))}c(l.arg)}(i,o,n,a)}))}return n=n?n.then(a,a):a()}}function _(e,t){var r=e.iterator[t.method];if(void 0===r){if(t.delegate=null,"throw"===t.method){if(e.iterator.return&&(t.method="return",t.arg=void 0,_(e,t),"throw"===t.method))return m;t.method="throw",t.arg=new TypeError("The iterator does not provide a 'throw' method")}return m}var n=d(r,e.iterator,t.arg);if("throw"===n.type)return t.method="throw",t.arg=n.arg,t.delegate=null,m;var i=n.arg;return i?i.done?(t[e.resultName]=i.value,t.next=e.nextLoc,"return"!==t.method&&(t.method="next",t.arg=void 0),t.delegate=null,m):i:(t.method="throw",t.arg=new TypeError("iterator result is not an object"),t.delegate=null,m)}function S(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function E(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function x(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(S,this),this.reset(!0)}function j(e){if(e){var t=e[i];if(t)return t.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length)){var n=-1,o=function t(){for(;++n<e.length;)if(r.call(e,n))return t.value=e[n],t.done=!1,t;return t.value=void 0,t.done=!0,t};return o.next=o}}return{next:k}}function k(){return{value:void 0,done:!0}}return f.prototype=h,c(b,"constructor",h),c(h,"constructor",f),f.displayName=c(h,a,"GeneratorFunction"),e.isGeneratorFunction=function(e){var t="function"==typeof e&&e.constructor;return!!t&&(t===f||"GeneratorFunction"===(t.displayName||t.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,h):(e.__proto__=h,c(e,a,"GeneratorFunction")),e.prototype=Object.create(b),e},e.awrap=function(e){return{__await:e}},N(w.prototype),c(w.prototype,o,(function(){return this})),e.AsyncIterator=w,e.async=function(t,r,n,i,o){void 0===o&&(o=Promise);var a=new w(l(t,r,n,i),o);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},N(b),c(b,a,"Generator"),c(b,i,(function(){return this})),c(b,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t=[];for(var r in e)t.push(r);return t.reverse(),function r(){for(;t.length;){var n=t.pop();if(n in e)return r.value=n,r.done=!1,r}return r.done=!0,r}},e.values=j,x.prototype={constructor:x,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(E),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var i=this.tryEntries.length-1;i>=0;--i){var o=this.tryEntries[i],a=o.completion;if("root"===o.tryLoc)return n("end");if(o.tryLoc<=this.prev){var c=r.call(o,"catchLoc"),l=r.call(o,"finallyLoc");if(c&&l){if(this.prev<o.catchLoc)return n(o.catchLoc,!0);if(this.prev<o.finallyLoc)return n(o.finallyLoc)}else if(c){if(this.prev<o.catchLoc)return n(o.catchLoc,!0)}else{if(!l)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return n(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;n>=0;--n){var i=this.tryEntries[n];if(i.tryLoc<=this.prev&&r.call(i,"finallyLoc")&&this.prev<i.finallyLoc){var o=i;break}}o&&("break"===e||"continue"===e)&&o.tryLoc<=t&&t<=o.finallyLoc&&(o=null);var a=o?o.completion:{};return a.type=e,a.arg=t,o?(this.method="next",this.next=o.finallyLoc,m):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),m},finish:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),E(r),m}},catch:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var r=this.tryEntries[t];if(r.tryLoc===e){var n=r.completion;if("throw"===n.type){var i=n.arg;E(r)}return i}}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:j(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),m}},e}function d(e,t,r,n,i,o,a){try{var c=e[o](a),l=c.value}catch(e){return void r(e)}c.done?t(l):Promise.resolve(l).then(n,i)}function m(e){return function(){var t=this,r=arguments;return new Promise((function(n,i){var o=e.apply(t,r);function a(e){d(o,n,i,a,c,"next",e)}function c(e){d(o,n,i,a,c,"throw",e)}a(void 0)}))}}function g(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function f(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function h(e,t,r){return t&&f(e.prototype,t),r&&f(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}function p(e,t,r,n,i){var o={};return Object.keys(n).forEach((function(e){o[e]=n[e]})),o.enumerable=!!o.enumerable,o.configurable=!!o.configurable,("value"in o||o.initializer)&&(o.writable=!0),o=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),o),i&&void 0!==o.initializer&&(o.value=o.initializer?o.initializer.call(i):void 0,o.initializer=void 0),void 0===o.initializer&&(Object.defineProperty(e,t,o),o=null),o}var y=new(i=p((n=h((function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),g(this,"selectKey",i,this),g(this,"setSelectKey",o,this),g(this,"findOrgaNum",a,this)}))).prototype,"selectKey",[c.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return"/setting/orga"}}),o=p(n.prototype,"setSelectKey",[c.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.selectKey=t}}}),a=p(n.prototype,"findOrgaNum",[c.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return m(u().mark((function e(){var t;return u().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(l.a)("/setting/findOrgaNum");case 2:return t=e.sent,e.abrupt("return",t);case 4:case"end":return e.stop()}}),e)})))}}),n)},984:function(e,t,r){"use strict";r.r(t),r.d(t,"default",(function(){return P}));r(121);var n,i,o=r(118),a=r(0),c=r.n(a),l=r(929),s=r(1020),u=r(45);function d(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}var m=[{title:"用户与权限",icon:"systemuser",id:"/setting/orga",purviewCode:"orga",code:1,children:[{title:"部门",id:"/setting/orga",purviewCode:"orga",islink:!0,code:0},{title:"用户",id:"/setting/user",purviewCode:"user",islink:!0,code:-1},{title:"用户组",id:"/setting/userGroup",purviewCode:"user_group",islink:!0,code:-2},{title:"用户目录",id:"/setting/dir",purviewCode:"user_dir",islink:!0,code:-3},{title:"权限",id:"/setting/systemRole",purviewCode:"SysPermission",code:2}]},{title:"消息",icon:"systemmessage",id:"/setting/messageNotice",purviewCode:"SysMessage",code:4,children:[{title:"消息通知方案",id:"/setting/messageNotice",purviewCode:"SysMessageNotice",code:3},{title:"消息发送方式",id:"/setting/messageSendType",purviewCode:"SysMessageSendType",code:2}]},{title:"系统集成",icon:"systemIntergrtion",id:"/setting/loadData",code:9,children:[{title:"地址配置",id:"/setting/urlData",code:8}]},{title:"安全",icon:"systemlog",id:"/setting/log",code:10,children:[{title:"操作日志",id:"/setting/logList",code:9},{title:"备份与恢复",id:"/setting/backup",code:8}]},(n={title:"应用",icon:"systemversion",id:"/setting/version"},d(n,"icon","systemversion"),d(n,"code",11),d(n,"children",[{title:"版本与许可证",id:"/setting/version",code:10},{title:"应用访问权限",id:"/setting/productAuth",code:9}]),n),{title:"归档知识库",icon:"systemreset",id:"/setting/archived",code:"recycle",iseEnhance:!0},{title:"回收站",icon:"systemdelete",id:"/setting/recycle",code:"recycle",iseEnhance:!0},{title:"基础数据",icon:"systemcenter",id:"/setting/systemFeature",code:12,children:[{title:"模板",id:"/setting/template",code:11},{title:"用户组",id:"/setting/usersystemgroup",purviewCode:"user_group",code:-6},{title:"虚拟角色",id:"/setting/virtual",code:"virtual"},{title:"系统功能",id:"/setting/systemFeature",purviewCode:"SysFeatrueSys",code:9},{title:"系统角色",id:"/setting/systemRoleBuilt",purviewCode:"SysRoleSys",code:8},{title:"项目功能",id:"/setting/projectFeature",purviewCode:"SysFeatrueProject",code:7},{title:"项目角色",id:"/setting/projectRole",purviewCode:"SysRoleProject",code:6},{title:"消息通知方式",id:"/setting/messageNoticeSystem",purviewCode:"SysMessageNotice",code:5},{title:"项目消息通知方式",id:"/setting/projectMessageNotice",purviewCode:"SysMessageType",code:4},{title:"消息类型",id:"/setting/messageType",purviewCode:"SysMessageType",code:"messageType"},{title:"日志模板",id:"/setting/myLogTemplateList",code:2},{title:"日志类型",id:"/setting/projectLogTypeList",code:1},{title:"待办模板",id:"/setting/todoTempList",purviewCode:"SysSetting",code:0},{title:"待办类型",id:"/setting/todoTypeTask",purviewCode:"SysSetting",code:-1}]}],g=[{title:"用户与权限",icon:"systemuser",id:"/setting/orga",purviewCode:"orga",code:1,children:[{title:"部门",id:"/setting/orga",purviewCode:"orga",islink:!0,code:0},{title:"用户",id:"/setting/user",purviewCode:"user",islink:!0,code:-1},{title:"用户组",id:"/setting/userGroup",purviewCode:"user_group",islink:!0,code:-2},{title:"用户目录",id:"/setting/dir",purviewCode:"user_dir",islink:!0,code:-3},{title:"权限",id:"/setting/systemRole",purviewCode:"SysPermission",code:2}]},{title:"消息",icon:"systemmessage",id:"/setting/messageNotice",purviewCode:"SysMessage",code:4,children:[{title:"消息通知方案",id:"/setting/messageNotice",purviewCode:"SysMessageNotice",code:3},{title:"消息发送方式",id:"/setting/messageSendType",purviewCode:"SysMessageSendType",code:2}]},{title:"系统集成",icon:"systemIntergrtion",id:"/setting/loadData",code:9,children:[{title:"地址配置",id:"/setting/urlData",code:8}]},{title:"安全",icon:"systemlog",id:"/setting/log",code:10,children:[{title:"操作日志",id:"/setting/logList",code:9},{title:"备份与恢复",id:"/setting/backup",code:8}]},(i={title:"应用",icon:"systemversion",id:"/setting/version"},d(i,"icon","systemversion"),d(i,"code",11),d(i,"children",[{title:"版本与许可证",id:"/setting/version",code:10},{title:"应用访问权限",id:"/setting/productAuth",code:9}]),i),{title:"归档知识库",icon:"systemreset",id:"/setting/archived",code:"recycle",iseEnhance:!0},{title:"回收站",icon:"systemdelete",id:"/setting/recycle",code:"recycle",iseEnhance:!0}],f=r(77),h=r(290),p=r(22),y=r(15),v=r(163),b="/Users/yuanjiexuan/Desktop/bate/project-web/tiklab-sward-ui/src/setting/common/components/SetAside.js";function N(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==r)return;var n,i,o=[],a=!0,c=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(o.push(n.value),!t||o.length!==t);a=!0);}catch(e){c=!0,i=e}finally{try{a||null==r.return||r.return()}finally{if(c)throw i}}return o}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return w(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);"Object"===r&&e.constructor&&(r=e.constructor.name);if("Map"===r||"Set"===r)return Array.from(e);if("Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r))return w(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function w(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var _=Object(u.h)(Object(p.observer)((function(e){var t,r=h.a.selectKey,n=h.a.setSelectKey,i=N(Object(a.useState)(m),2),o=i[0],u=i[1],d=null===(t=JSON.parse(localStorage.getItem("authConfig")))||void 0===t?void 0:t.authType,p=Object(y.getVersionInfo)(),w=N(Object(a.useState)(!1),2),_=w[0],S=w[1];Object(a.useEffect)((function(){u(g)}),[]);var E=function(t,i,o){return c.a.createElement(f.PrivilegeButton,{code:t.purviewCode,__source:{fileName:b,lineNumber:64,columnNumber:13}},c.a.createElement("li",{style:{cursor:"pointer",paddingLeft:"".concat(20*i+20)},className:"orga-aside-item ".concat(t.id===r?"orga-aside-select":""),onClick:function(){return function(t){var r=t.id,i=t.iseEnhance;if(t.islink&&d){var o=JSON.parse(localStorage.getItem("authConfig")).authServiceUrl+"#"+r;window.open(o,"_blank")}else!1===p.expired?(e.history.push(r),n(r)):i?S(!0):(e.history.push(r),n(r))}(t)},key:t.code,code:t.encoded,__source:{fileName:b,lineNumber:65,columnNumber:17}},c.a.createElement("span",{className:"orga-aside-item-left",__source:{fileName:b,lineNumber:72,columnNumber:21}},c.a.createElement("span",{__source:{fileName:b,lineNumber:78,columnNumber:25}},t.title)),t.islink&&!d&&c.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:b,lineNumber:82,columnNumber:55}},c.a.createElement("svg",{className:"img-icon-16","aria-hidden":"true",__source:{fileName:b,lineNumber:83,columnNumber:29}},c.a.createElement("use",{xlinkHref:"#icon-outside",__source:{fileName:b,lineNumber:84,columnNumber:33}}))),t.iseEnhance&&!0===p.expired&&c.a.createElement("svg",{className:"img-icon-16","aria-hidden":"true",__source:{fileName:b,lineNumber:89,columnNumber:76}},c.a.createElement("use",{xlinkHref:"#icon-member",__source:{fileName:b,lineNumber:90,columnNumber:29}}))))},x=N(Object(a.useState)(["/organ/organ"]),2),j=x[0],k=x[1],O=function(e){return j.some((function(t){return t===e}))},C=function e(t,r,n){return c.a.createElement(f.PrivilegeButton,{code:t.purviewCode,__source:{fileName:b,lineNumber:118,columnNumber:13}},c.a.createElement("li",{key:t.code,title:t.title,className:"orga-aside-li",__source:{fileName:b,lineNumber:119,columnNumber:17}},c.a.createElement("div",{className:"orga-aside-item orga-aside-first",style:{paddingLeft:"".concat(20*r+20)},onClick:function(){return e=t.id,void(O(e)?k(j.filter((function(t){return t!==e}))):k(j.concat(e)));var e},__source:{fileName:b,lineNumber:120,columnNumber:21}},t.icon&&c.a.createElement("span",{to:t.id,className:"orga-aside-item-left",__source:{fileName:b,lineNumber:123,columnNumber:42}},c.a.createElement("span",{className:"orga-aside-title",__source:{fileName:b,lineNumber:127,columnNumber:33}},t.title)),c.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:b,lineNumber:130,columnNumber:25}},t.children?O(t.id)?c.a.createElement(l.a,{style:{fontSize:"10px"},__source:{fileName:b,lineNumber:134,columnNumber:41}}):c.a.createElement(s.a,{style:{fontSize:"10px"},__source:{fileName:b,lineNumber:135,columnNumber:41}}):"")),c.a.createElement("ul",{title:t.title,className:"orga-aside-ul ".concat(O(t.id)?null:"orga-aside-hidden"),__source:{fileName:b,lineNumber:141,columnNumber:21}},t.children&&t.children.map((function(t){var n=r+1;return t.children&&t.children.length?e(t,n):E(t,n)})))))};return c.a.createElement(a.Fragment,{__source:{fileName:b,lineNumber:157,columnNumber:9}},c.a.createElement("div",{className:"orga-aside",__source:{fileName:b,lineNumber:158,columnNumber:13}},c.a.createElement("ul",{style:{padding:0},key:"0",className:"orga-aside-top",__source:{fileName:b,lineNumber:159,columnNumber:17}},c.a.createElement("div",{className:"orga-aside-name",__source:{fileName:b,lineNumber:160,columnNumber:21}},"设置"),o&&o.map((function(e,t){return e.children&&e.children.length>0?C(e,0):E(e,0)}))),c.a.createElement(v.a,{archivedFreeVisable:_,setArchivedFreeVisable:S,__source:{fileName:b,lineNumber:168,columnNumber:17}})))}))),S=r(26);Object(S.a)(".orga {\n  height: 100%;\n}\n.orga .orga-background {\n  background-color: #fff;\n  height: calc(100vh - var(--tiklab-head-height));\n}\n.orga .orga-col {\n  background-color: #fff;\n  padding: 20px;\n}\n.orga .orga-aside {\n  height: 100%;\n  background-color: var(--tiklab-gray-600);\n}\n.orga .orga-aside .orga-aside-name {\n  height: 40px;\n  line-height: 40px;\n  padding: 0 20px;\n  border-bottom: 1px solid #f0f0f0;\n}\n.orga .orga-aside .orga-aside-top {\n  height: calc(100vh - 48px);\n  overflow: auto;\n}\n.orga .orga-aside ul,\n.orga .orga-aside li {\n  list-style: none;\n}\n.orga .orga-aside .orga-aside-hidden {\n  display: none;\n}\n.orga .orga-aside .orga-aside-item {\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  height: 40px;\n  line-height: 40px;\n  padding: 0 20px;\n  cursor: pointer;\n}\n.orga .orga-aside .orga-aside-item-left {\n  display: inline-flex;\n  align-items: center;\n}\n.orga .orga-aside .orga-aside-li {\n  line-height: 40px;\n}\n.orga .orga-aside .orga-aside-ul {\n  padding: 0;\n}\n.orga .orga-aside .orga-aside-item:hover {\n  background-color: var(--tiklab-gray-400);\n}\n.orga .orga-aside .orga-aside-select {\n  background-color: var(--tiklab-gray-400);\n  border-left: 3px solid var(--tiklab-gray-400);\n}\n.orga .orga-change {\n  width: 100%;\n  height: 50px;\n  line-height: 50px;\n  background-color: var(--tiklab-gray);\n  text-align: center;\n  color: var(--tiklab-blue);\n  cursor: pointer;\n}\n\n.ant-modal-close-x {\n  display: flex !important;\n  justify-content: center;\n  align-items: center;\n}");var E=r(178),x="/Users/yuanjiexuan/Desktop/bate/project-web/tiklab-sward-ui/src/setting/common/components/Setting.js";function j(){return(j=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e}).apply(this,arguments)}function k(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==r)return;var n,i,o=[],a=!0,c=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(o.push(n.value),!t||o.length!==t);a=!0);}catch(e){c=!0,i=e}finally{try{a||null==r.return||r.return()}finally{if(c)throw i}}return o}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return O(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);"Object"===r&&e.constructor&&(r=e.constructor.name);if("Map"===r||"Set"===r)return Array.from(e);if("Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r))return O(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function O(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var C=o.a.Sider,L=o.a.Content,P=function(e){var t=e.route,r=k(Object(a.useState)(m),2),n=r[0],i=r[1];return Object(a.useEffect)((function(){i(g)}),[]),c.a.createElement(a.Fragment,{__source:{fileName:x,lineNumber:30,columnNumber:9}},c.a.createElement(f.SystemNav,j({},e,{applicationRouters:n,outerPath:"/setting",noAccessPath:"/noaccess",__source:{fileName:x,lineNumber:31,columnNumber:13}}),c.a.createElement(o.a,{className:"orga",__source:{fileName:x,lineNumber:37,columnNumber:17}},c.a.createElement(C,{width:200,className:"site-layout-background",__source:{fileName:x,lineNumber:38,columnNumber:21}},c.a.createElement(_,{__source:{fileName:x,lineNumber:39,columnNumber:25}})),c.a.createElement(L,{className:"orga-background",__source:{fileName:x,lineNumber:42,columnNumber:21}},Object(E.a)(t.routes)))))}}}]);
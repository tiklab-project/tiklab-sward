(window.webpackJsonp=window.webpackJsonp||[]).push([[18],{134:function(e,t,n){"use strict";n.d(t,"a",(function(){return r}));var r="/images/weekly.png"},135:function(e,t,n){"use strict";n.d(t,"a",(function(){return r}));var r="/images/weeklyNomal.png"},136:function(e,t,n){"use strict";n.d(t,"a",(function(){return r}));var r="/images/todoWork.png"},137:function(e,t,n){"use strict";n.d(t,"a",(function(){return r}));var r="/images/projectPlan.png"},138:function(e,t,n){"use strict";n.d(t,"a",(function(){return r}));var r="/images/projectOperation.png"},180:function(e,t,n){"use strict";n.d(t,"a",(function(){return z}));var r,i,a,o,u,c,l,s,m,f,p,d,b,h,g,y,v,x=n(66),w=n(28);function N(e){return(N="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function j(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function _(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?j(Object(n),!0).forEach((function(t){T(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):j(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function E(){/*! regenerator-runtime -- Copyright (c) 2014-present, Facebook, Inc. -- license (MIT): https://github.com/facebook/regenerator/blob/main/LICENSE */E=function(){return e};var e={},t=Object.prototype,n=t.hasOwnProperty,r="function"==typeof Symbol?Symbol:{},i=r.iterator||"@@iterator",a=r.asyncIterator||"@@asyncIterator",o=r.toStringTag||"@@toStringTag";function u(e,t,n){return Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{u({},"")}catch(e){u=function(e,t,n){return e[t]=n}}function c(e,t,n,r){var i=t&&t.prototype instanceof m?t:m,a=Object.create(i.prototype),o=new _(r||[]);return a._invoke=function(e,t,n){var r="suspendedStart";return function(i,a){if("executing"===r)throw new Error("Generator is already running");if("completed"===r){if("throw"===i)throw a;return k()}for(n.method=i,n.arg=a;;){var o=n.delegate;if(o){var u=x(o,n);if(u){if(u===s)continue;return u}}if("next"===n.method)n.sent=n._sent=n.arg;else if("throw"===n.method){if("suspendedStart"===r)throw r="completed",n.arg;n.dispatchException(n.arg)}else"return"===n.method&&n.abrupt("return",n.arg);r="executing";var c=l(e,t,n);if("normal"===c.type){if(r=n.done?"completed":"suspendedYield",c.arg===s)continue;return{value:c.arg,done:n.done}}"throw"===c.type&&(r="completed",n.method="throw",n.arg=c.arg)}}}(e,n,o),a}function l(e,t,n){try{return{type:"normal",arg:e.call(t,n)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var s={};function m(){}function f(){}function p(){}var d={};u(d,i,(function(){return this}));var b=Object.getPrototypeOf,h=b&&b(b(O([])));h&&h!==t&&n.call(h,i)&&(d=h);var g=p.prototype=m.prototype=Object.create(d);function y(e){["next","throw","return"].forEach((function(t){u(e,t,(function(e){return this._invoke(t,e)}))}))}function v(e,t){var r;this._invoke=function(i,a){function o(){return new t((function(r,o){!function r(i,a,o,u){var c=l(e[i],e,a);if("throw"!==c.type){var s=c.arg,m=s.value;return m&&"object"==N(m)&&n.call(m,"__await")?t.resolve(m.__await).then((function(e){r("next",e,o,u)}),(function(e){r("throw",e,o,u)})):t.resolve(m).then((function(e){s.value=e,o(s)}),(function(e){return r("throw",e,o,u)}))}u(c.arg)}(i,a,r,o)}))}return r=r?r.then(o,o):o()}}function x(e,t){var n=e.iterator[t.method];if(void 0===n){if(t.delegate=null,"throw"===t.method){if(e.iterator.return&&(t.method="return",t.arg=void 0,x(e,t),"throw"===t.method))return s;t.method="throw",t.arg=new TypeError("The iterator does not provide a 'throw' method")}return s}var r=l(n,e.iterator,t.arg);if("throw"===r.type)return t.method="throw",t.arg=r.arg,t.delegate=null,s;var i=r.arg;return i?i.done?(t[e.resultName]=i.value,t.next=e.nextLoc,"return"!==t.method&&(t.method="next",t.arg=void 0),t.delegate=null,s):i:(t.method="throw",t.arg=new TypeError("iterator result is not an object"),t.delegate=null,s)}function w(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function j(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function _(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(w,this),this.reset(!0)}function O(e){if(e){var t=e[i];if(t)return t.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length)){var r=-1,a=function t(){for(;++r<e.length;)if(n.call(e,r))return t.value=e[r],t.done=!1,t;return t.value=void 0,t.done=!0,t};return a.next=a}}return{next:k}}function k(){return{value:void 0,done:!0}}return f.prototype=p,u(g,"constructor",p),u(p,"constructor",f),f.displayName=u(p,o,"GeneratorFunction"),e.isGeneratorFunction=function(e){var t="function"==typeof e&&e.constructor;return!!t&&(t===f||"GeneratorFunction"===(t.displayName||t.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,p):(e.__proto__=p,u(e,o,"GeneratorFunction")),e.prototype=Object.create(g),e},e.awrap=function(e){return{__await:e}},y(v.prototype),u(v.prototype,a,(function(){return this})),e.AsyncIterator=v,e.async=function(t,n,r,i,a){void 0===a&&(a=Promise);var o=new v(c(t,n,r,i),a);return e.isGeneratorFunction(n)?o:o.next().then((function(e){return e.done?e.value:o.next()}))},y(g),u(g,o,"Generator"),u(g,i,(function(){return this})),u(g,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t=[];for(var n in e)t.push(n);return t.reverse(),function n(){for(;t.length;){var r=t.pop();if(r in e)return n.value=r,n.done=!1,n}return n.done=!0,n}},e.values=O,_.prototype={constructor:_,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(j),!e)for(var t in this)"t"===t.charAt(0)&&n.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function r(n,r){return o.type="throw",o.arg=e,t.next=n,r&&(t.method="next",t.arg=void 0),!!r}for(var i=this.tryEntries.length-1;i>=0;--i){var a=this.tryEntries[i],o=a.completion;if("root"===a.tryLoc)return r("end");if(a.tryLoc<=this.prev){var u=n.call(a,"catchLoc"),c=n.call(a,"finallyLoc");if(u&&c){if(this.prev<a.catchLoc)return r(a.catchLoc,!0);if(this.prev<a.finallyLoc)return r(a.finallyLoc)}else if(u){if(this.prev<a.catchLoc)return r(a.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<a.finallyLoc)return r(a.finallyLoc)}}}},abrupt:function(e,t){for(var r=this.tryEntries.length-1;r>=0;--r){var i=this.tryEntries[r];if(i.tryLoc<=this.prev&&n.call(i,"finallyLoc")&&this.prev<i.finallyLoc){var a=i;break}}a&&("break"===e||"continue"===e)&&a.tryLoc<=t&&t<=a.finallyLoc&&(a=null);var o=a?a.completion:{};return o.type=e,o.arg=t,a?(this.method="next",this.next=a.finallyLoc,s):this.complete(o)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),s},finish:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.finallyLoc===e)return this.complete(n.completion,n.afterLoc),j(n),s}},catch:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.tryLoc===e){var r=n.completion;if("throw"===r.type){var i=r.arg;j(n)}return i}}throw new Error("illegal catch attempt")},delegateYield:function(e,t,n){return this.delegate={iterator:O(e),resultName:t,nextLoc:n},"next"===this.method&&(this.arg=void 0),s}},e}function O(e,t,n,r,i,a,o){try{var u=e[a](o),c=u.value}catch(e){return void n(e)}u.done?t(c):Promise.resolve(c).then(r,i)}function k(e){return function(){var t=this,n=arguments;return new Promise((function(r,i){var a=e.apply(t,n);function o(e){O(a,r,i,o,u,"next",e)}function u(e){O(a,r,i,o,u,"throw",e)}o(void 0)}))}}function L(e,t,n,r){n&&Object.defineProperty(e,t,{enumerable:n.enumerable,configurable:n.configurable,writable:n.writable,value:n.initializer?n.initializer.call(r):void 0})}function D(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function S(e,t,n){return t&&D(e.prototype,t),n&&D(e,n),Object.defineProperty(e,"prototype",{writable:!1}),e}function T(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function P(e,t,n,r,i){var a={};return Object.keys(r).forEach((function(e){a[e]=r[e]})),a.enumerable=!!a.enumerable,a.configurable=!!a.configurable,("value"in a||a.initializer)&&(a.writable=!0),a=n.slice().reverse().reduce((function(n,r){return r(e,t,n)||n}),a),i&&void 0!==a.initializer&&(a.value=a.initializer?a.initializer.call(i):void 0,a.initializer=void 0),void 0===a.initializer&&(Object.defineProperty(e,t,a),a=null),a}var z=new(i=P((r=S((function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),L(this,"repositoryCatalogue",i,this),L(this,"expandedTree",a,this),L(this,"repositoryCatalogueList",o,this),L(this,"docDetail",u,this),L(this,"templatePageParams",c,this),L(this,"setExpandedTree",l,this),L(this,"setRepositoryCatalogueList",s,this),L(this,"createDocument",m,this),L(this,"updateDocument",f,this),L(this,"findDocument",p,this),L(this,"deleteDocument",d,this),L(this,"findCategoryDocument",b,this),L(this,"createRecent",h,this),L(this,"findDocumentTemplateList",g,this),L(this,"createDocumentFocus",y,this),L(this,"deleteDocumentFocusByCondition",v,this)}))).prototype,"repositoryCatalogue",[w.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),a=P(r.prototype,"expandedTree",[w.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[0]}}),o=P(r.prototype,"repositoryCatalogueList",[w.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),u=P(r.prototype,"docDetail",[w.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[{title:"",type:"",content:""}]}}),c=P(r.prototype,"templatePageParams",[w.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return{current:1,pageSize:10,total:1}}}),l=P(r.prototype,"setExpandedTree",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.expandedTree=t}}}),s=P(r.prototype,"setRepositoryCatalogueList",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.repositoryCatalogueList=t}}}),m=P(r.prototype,"createDocument",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(x.a)("/document/createDocument",t);case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),f=P(r.prototype,"updateDocument",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(x.a)("/document/updateDocument",t);case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),p=P(r.prototype,"findDocument",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n,r;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(n=new FormData).append("id",t),e.next=4,Object(x.a)("/document/findDocument",n);case 4:return r=e.sent,e.abrupt("return",r);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),d=P(r.prototype,"deleteDocument",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n,r;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(n=new FormData).append("id",t),e.next=4,Object(x.a)("/document/deleteDocument",n);case 4:return r=e.sent,e.abrupt("return",r);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),b=P(r.prototype,"findCategoryDocument",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n,r;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(n=new FormData).append("id",t),e.next=4,Object(x.a)("/category/findCategoryDocument",n);case 4:return r=e.sent,e.abrupt("return",r);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),h=P(r.prototype,"createRecent",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(x.a)("/recent/createRecent",t);case 2:return n=e.sent,e.abrupt("return",n.data);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),g=P(r.prototype,"findDocumentTemplateList",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=k(E().mark((function t(n){var r,i;return E().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return Object.assign(e.templatePageParams,_({},n)),r={name:e.templatePageParams.name,orderParams:[{name:"name",orderType:"asc"}]},t.next=4,Object(x.a)("/documentTemplate/findDocumentTemplateList",r);case 4:return 0===(i=t.sent).code&&(e.templateList=i.data.dataList),t.abrupt("return",i);case 7:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),y=P(r.prototype,"createDocumentFocus",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(x.a)("/documentFocus/createDocumentFocus",t);case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),v=P(r.prototype,"deleteDocumentFocusByCondition",[w.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=k(E().mark((function e(t){var n;return E().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(x.a)("/documentFocus/deleteDocumentFocusByCondition",t);case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),r)},219:function(e,t,n){"use strict";n.d(t,"a",(function(){return w}));n(43);var r=n(42),i=(n(217),n(164)),a=(n(121),n(118)),o=n(0),u=n.n(o),c=n(22),l=n(1019),s=n(26);Object(s.a)(".template-modal .ant-modal-body {\n  padding: 0;\n}\n.template-modal .template-list {\n  display: flex;\n  gap: 10px;\n  flex-wrap: wrap;\n  padding: 10px;\n}\n.template-modal .template-list .template-box {\n  padding: 10px;\n  border: 1px solid rgba(126, 134, 142, 0.16);\n  box-sizing: border-box;\n  border-radius: 6px;\n  position: relative;\n  background-color: #fff;\n}\n.template-modal .template-list .template-box .template-image {\n  width: 120px;\n}\n.template-modal .template-list .template-box .template-name {\n  height: 27px;\n  background: rgb(249, 250, 252);\n  width: 100%;\n  border-radius: 0px 0px 6px 6px;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  padding: 0 10px;\n  line-height: 27px;\n  color: #999;\n}");n(107);var m=n(134),f=n(135),p=n(136),d=n(137),b=n(138),h="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/document/document/components/SelectTemplateList.js";function g(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==n)return;var r,i,a=[],o=!0,u=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){u=!0,i=e}finally{try{o||null==n.return||n.return()}finally{if(u)throw i}}return a}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return y(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);"Object"===n&&e.constructor&&(n=e.constructor.name);if("Map"===n||"Set"===n)return Array.from(e);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return y(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function y(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var v=a.a.Content,x=a.a.Sider,w=Object(c.inject)("documentStore")(Object(c.observer)((function(e){var t=e.setTemplateVisible,n=e.templateVisible;e.documentId;var c=e.documentStore,s=e.selectTemplate;c.updateDocument;var y=c.findDocumentTemplateList;e.match.params.repositoryId;var w=g(Object(o.useState)(),2),N=w[0],j=w[1],_=[m.a,f.a,p.a,d.a,b.a];Object(o.useEffect)((function(){y().then((function(e){0===e.code&&j(e.data)}))}),[]);return u.a.createElement("div",{__source:{fileName:h,lineNumber:47,columnNumber:9}},u.a.createElement(r.default,{className:"template-modal",title:"选择模板",visible:n,width:"70vw",onCancel:function(){return t(!1)},destroyOnClose:!0,okText:"下一步",cancelText:"取消",footer:null,__source:{fileName:h,lineNumber:48,columnNumber:13}},u.a.createElement(a.a,{style:{position:"relative",height:"calc(100vh - 200px)"},__source:{fileName:h,lineNumber:60,columnNumber:17}},u.a.createElement(x,{style:{overflow:"auto",position:"absolute",left:0,top:0,height:"100%",background:"#fff"},__source:{fileName:h,lineNumber:64,columnNumber:21}},u.a.createElement(i.a,{theme:"light",mode:"inline",defaultSelectedKeys:["entry"],__source:{fileName:h,lineNumber:74,columnNumber:25}},u.a.createElement(i.a.Item,{key:"entry",icon:u.a.createElement(l.a,{__source:{fileName:h,lineNumber:75,columnNumber:58}}),__source:{fileName:h,lineNumber:75,columnNumber:29}},"推荐模版"))),u.a.createElement(a.a,{className:"site-layout",style:{marginLeft:200},__source:{fileName:h,lineNumber:80,columnNumber:21}},u.a.createElement(v,{__source:{fileName:h,lineNumber:81,columnNumber:25}},u.a.createElement("div",{className:"template-list",__source:{fileName:h,lineNumber:82,columnNumber:29}},N&&N.map((function(e,n){return u.a.createElement("div",{className:"template-box",key:n,onClick:function(){return function(e){s(e),t(!1)}(e)},__source:{fileName:h,lineNumber:85,columnNumber:48}},u.a.createElement("img",{src:_[n],alt:"",className:"template-image",__source:{fileName:h,lineNumber:86,columnNumber:45}}),u.a.createElement("div",{className:"template-name",__source:{fileName:h,lineNumber:93,columnNumber:45}},e.name))}))))))))})))},80:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var r=n(0),i=n.n(r),a=n(26);Object(a.a)(".project-botton {\n  padding: 5px 15px;\n  border-radius: 4px;\n  display: flex;\n  align-items: center;\n  cursor: pointer;\n  height: 32px;\n  line-height: 32px;\n}\n\n.project-primary {\n  background-color: var(--thoughtware-blue);\n  color: #ffffff;\n  font-weight: 500;\n}\n\n.project-primary:hover {\n  background-color: var(--thoughtware-blue);\n}\n\n.project-dashed {\n  color: #42526E;\n  font-weight: 500;\n  background-color: var(--thoughtware-gray);\n}\n\n.project-dashed:hover {\n  background-color: var(--thoughtware-gray-400);\n}");var o=function(e){var t=e.buttonText,n=e.children,r=e.onClick,a=e.type,o=e.style;return i.a.createElement("div",{onClick:r,style:o,className:"project-botton ".concat("primary"===a?"project-primary":"project-dashed"),__source:{fileName:"/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/common/button/button.js",lineNumber:8,columnNumber:3}},n,t)}},988:function(e,t,n){"use strict";n.r(t),n.d(t,"default",(function(){return D}));n(35);var r=n(36),i=(n(37),n(38)),a=(n(32),n(31)),o=(n(41),n(39)),u=n(0),c=n.n(u),l=n(22),s=n(45),m=n(26);Object(m.a)(".documnet-addList .title {\n  font-size: 40px;\n  font-weight: 600;\n}\n.documnet-addList .document-list {\n  width: 300px;\n  display: flex;\n  justify-content: center;\n  flex-direction: column;\n  margin: 20px auto;\n}\n.documnet-addList .documnet-type {\n  display: flex;\n  align-items: center;\n  margin: 20px 0;\n}\n.documnet-addList .documnet-type .icon-svg {\n  width: 30px;\n  height: 30px;\n  margin-right: 10px;\n}\n\n.documnet-edit {\n  background-color: #fff;\n  height: calc(100vh - 48px);\n}\n.documnet-edit .edit-top {\n  line-height: 50px;\n  height: 50px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  padding: 0 20px;\n  border-bottom: 1px solid #EFF1F7;\n  background-color: #F7F9FE;\n}\n.documnet-edit .edit-top .edit-title {\n  font-size: 18px;\n  font-weight: 600;\n}\n.documnet-edit .edit-top .document-edit {\n  display: flex;\n  align-items: center;\n}\n.documnet-edit .edit-top .inline {\n  border-left: 2px solid #f0f0f0;\n  height: 20px;\n  width: 1px;\n  margin-right: 20px;\n}\n.documnet-edit .edit-big {\n  background-color: #F7F9FE;\n  padding-left: 13px;\n}\n.documnet-edit .edit-big .edit-big-toolbar {\n  border-bottom: 0;\n  justify-content: flex-start;\n}\n.documnet-edit .edit-right {\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  gap: 10px;\n}\n.documnet-edit .edit-right .icon-svg {\n  width: 20px;\n  height: 20px;\n  margin-right: 20px;\n}\n.documnet-edit .edit-right .right-icon {\n  width: 20px;\n  height: 20px;\n}\n.documnet-edit .document-examine-content {\n  margin-top: 10px;\n  height: calc(100% - 120px);\n}\n.documnet-edit .document-title-input {\n  margin: 20px 0;\n  height: 50px;\n  line-height: 50px;\n  padding: 0;\n  font-size: 30px;\n  font-weight: 600;\n}\n.documnet-edit .template-title {\n  height: 40px;\n  line-height: 40px;\n  font-weight: 600;\n  color: #999;\n}\n.documnet-edit .template-list {\n  display: flex;\n  gap: 10px;\n}\n.documnet-edit .template-list .template-box {\n  padding: 10px;\n  border: 1px solid rgba(126, 134, 142, 0.16);\n  box-sizing: border-box;\n  border-radius: 6px;\n  position: relative;\n}\n.documnet-edit .template-list .template-box .template-image {\n  width: 120px;\n}\n.documnet-edit .template-list .template-box .template-name {\n  height: 27px;\n  background: rgb(249, 250, 252);\n  width: 100%;\n  border-radius: 0px 0px 6px 6px;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  padding: 0 10px;\n  line-height: 27px;\n  color: #999;\n}");var f=n(204),p=n(80),d=n(180),b=n(128),h=(n(205),n(15)),g=n(223),y=n(154),v=(n(107),n(219)),x=n(134),w=n(135),N=n(136),j=n(137),_=n(138),E="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/document/document/components/DocumentEdit.js";function O(){return(O=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}function k(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==n)return;var r,i,a=[],o=!0,u=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){u=!0,i=e}finally{try{o||null==n.return||n.return()}finally{if(u)throw i}}return a}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return L(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);"Object"===n&&e.constructor&&(n=e.constructor.name);if("Map"===n||"Set"===n)return Array.from(e);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return L(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function L(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var D=Object(s.h)(Object(l.inject)("relationWorkStore")(Object(l.observer)((function(e){var t=e.relationWorkStore;e.documentStore;var n=d.a.findDocument,l=d.a.updateDocument,s=d.a.findDocumentTemplateList,m=b.a.documentTitle,L=b.a.setDocumentTitle,D=b.a.repositoryCatalogueList,S=e.match.params.id,T=e.match.params.repositoryId,P=k(Object(u.useState)({name:"",likenumInt:"",commentNumber:"",master:{name:""}}),2),z=P[0],C=P[1],F=k(Object(u.useState)(),2),I=F[0],A=F[1],B=k(Object(u.useState)(!1),2),G=B[0],U=B[1],R=Object(u.useRef)(),W=Object(h.getUser)().ticket,M=Object(h.getUser)().tenant,V=k(Object(u.useState)(!1),2),J=V[0],H=V[1],Y=k(Object(u.useState)(),2),$=Y[0],K=Y[1],q=[x.a,w.a,N.a,j.a,_.a];Object(u.useEffect)((function(){s().then((function(e){0===e.code&&K(e.data.slice(0,3))}))}),[]),Object(u.useEffect)((function(){A(),n(S).then((function(e){if(0===e.code){var t,n,r,i=null==e||null===(t=e.data)||void 0===t?void 0:t.node;if(L(i.name),null!=e&&null!==(n=e.data)&&void 0!==n&&n.details)A(null==e||null===(r=e.data)||void 0===r?void 0:r.details);else A('[{"type":"paragraph","children":[{"text":""}]}]');C(i)}}))}),[S]),Object(u.useEffect)((function(){I&&U(ee(I))}),[I]);var Q=function(e){A(e);var t={id:S,details:e,detailText:R.current.innerText};l(t).then((function(e){0===e.code&&o.default.success("保存成功")}))},X=Object(g.a)((function(e){A(e);var t={id:S,details:e,detailText:R.current.innerText};l(t)}),[500]),Z=function(e){L(e.target.value);var t={id:S,node:{id:S,name:e.target.value}};l(t).then((function(t){0===t.code&&(document.getElementById("examine-title").innerHTML=e.target.value,document.getElementById("file-"+S).innerHTML=e.target.value,Object(y.d)(D,S,e.target.value))}))},ee=function(e){var t=!0,n=JSON.parse(e);return n.length>1&&(t=!1),1===n.length&&(t="paragraph"===n[0].type&&""===n[0].children[0].text),t},te=function(e){var t={id:S,details:e.details,detailText:e.detailText};A(null),l(t).then((function(t){0===t.code&&A(e.details)}))};return c.a.createElement("div",{className:"documnet-edit",__source:{fileName:E,lineNumber:173,columnNumber:9}},c.a.createElement("div",{className:"edit-top",__source:{fileName:E,lineNumber:174,columnNumber:13}},c.a.createElement("div",{className:"edit-title",id:"examine-title",__source:{fileName:E,lineNumber:175,columnNumber:17}},z.name),c.a.createElement("div",{className:"edit-right",__source:{fileName:E,lineNumber:176,columnNumber:17}},c.a.createElement(p.a,{type:"primary",onClick:function(){Q(I)},__source:{fileName:E,lineNumber:177,columnNumber:21}},"保存"),c.a.createElement(p.a,{onClick:function(){e.history.push("/repositorydetail/".concat(T,"/doc/").concat(S))},__source:{fileName:E,lineNumber:178,columnNumber:21}},"退出编辑"))),c.a.createElement("div",{style:{height:"calc(100% - 60px)"},__source:{fileName:E,lineNumber:181,columnNumber:13}},I&&c.a.createElement(f.EditorBig,{value:I,onChange:function(e){return X(e)},relationWorkStore:t,base_url:"",ticket:W,tenant:M,__source:{fileName:E,lineNumber:183,columnNumber:26}},c.a.createElement(c.a.Fragment,null,c.a.createElement(r.default,{className:"document-examine-content",__source:{fileName:E,lineNumber:192,columnNumber:25}},c.a.createElement(i.default,{xl:{span:18,offset:3},lg:{span:18,offset:3},md:{span:20,offset:2},__source:{fileName:E,lineNumber:193,columnNumber:29}},c.a.createElement("div",{className:"document-title",__source:{fileName:E,lineNumber:194,columnNumber:33}},c.a.createElement(a.default,{className:"document-title-input",bordered:!1,onChange:function(e){return L(e.target.value)},value:m,onPressEnter:function(e){return Z(e)},onBlur:function(e){return Z(e)},__source:{fileName:E,lineNumber:195,columnNumber:37}})),c.a.createElement("div",{ref:R,__source:{fileName:E,lineNumber:204,columnNumber:33}},c.a.createElement(f.EditorBigContent,{value:I,relationWorkStore:t,__source:{fileName:E,lineNumber:205,columnNumber:37}})),G&&c.a.createElement("div",{className:"template-select",__source:{fileName:E,lineNumber:213,columnNumber:37}},c.a.createElement("div",{className:"template-title",__source:{fileName:E,lineNumber:214,columnNumber:41}},"推荐模版"),c.a.createElement("div",{className:"template-list",__source:{fileName:E,lineNumber:215,columnNumber:41}},$&&$.map((function(e,t){return c.a.createElement("div",{className:"template-box",key:t,onClick:function(){return te(e)},__source:{fileName:E,lineNumber:218,columnNumber:60}},c.a.createElement("img",{src:q[t],alt:"",className:"template-image",__source:{fileName:E,lineNumber:219,columnNumber:57}}),c.a.createElement("div",{className:"template-name",__source:{fileName:E,lineNumber:225,columnNumber:57}},e.name))})),c.a.createElement("div",{className:"template-box",key:0,onClick:function(){return H(!0)},__source:{fileName:E,lineNumber:229,columnNumber:45}},c.a.createElement("img",{src:"/images/template.png",alt:"",className:"template-image",__source:{fileName:E,lineNumber:230,columnNumber:49}}),c.a.createElement("div",{className:"template-name",__source:{fileName:E,lineNumber:236,columnNumber:49}},"更多模版"))))))))),c.a.createElement(v.a,O({documentId:S,setTemplateVisible:H,templateVisible:J,documentStore:d.a,selectTemplate:te},e,{__source:{fileName:E,lineNumber:249,columnNumber:13}})))}))))}}]);
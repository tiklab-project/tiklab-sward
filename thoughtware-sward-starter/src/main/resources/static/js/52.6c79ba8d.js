(window.webpackJsonp=window.webpackJsonp||[]).push([[52],{1009:function(e,t,r){"use strict";r.r(t),r.d(t,"default",(function(){return d}));r(35);var n=r(36),i=(r(37),r(38)),o=(r(48),r(47)),a=r(0),u=r.n(a),c=r(22),l=r(26);Object(l.a)(".template-view-top {\n  padding: 10px 0;\n  font-weight: 600;\n  align-items: center;\n}\n.template-view-top .template-view-breadcrumb {\n  font-size: var(--thoughtware-font-16);\n  font-weight: var(--thoughtware-font-weight-bold);\n  display: flex;\n  align-items: center;\n}\n.template-view-top .template-view-breadcrumb .template-back {\n  cursor: pointer;\n}\n.template-view-top .template-view-title {\n  background: #fff;\n  position: -webkit-sticky;\n  position: sticky;\n  top: 0;\n  z-index: 3;\n}");var s=r(204),f=(r(205),r(181)),p=r(15),m="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/setting/template/components/TemplatePreview.js";function h(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==r)return;var n,i,o=[],a=!0,u=!1;try{for(r=r.call(e);!(a=(n=r.next()).done)&&(o.push(n.value),!t||o.length!==t);a=!0);}catch(e){u=!0,i=e}finally{try{a||null==r.return||r.return()}finally{if(u)throw i}}return o}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return b(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);"Object"===r&&e.constructor&&(r=e.constructor.name);if("Map"===r||"Set"===r)return Array.from(e);if("Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r))return b(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function b(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var d=Object(c.inject)("relationWorkStore")(Object(c.observer)((function(e){h(o.default.useForm(),1)[0];var t=e.relationWorkStore,r=e.match.params.templateId,c=f.a.findDocumentTemplate,l=h(Object(a.useState)(),2),b=l[0],d=l[1];Object(p.getUser)().ticket;var v=Object(p.getUser)().tenant,y=h(Object(a.useState)(),2),w=y[0],g=y[1];return Object(a.useEffect)((function(){r&&(d(),c(r).then((function(e){var t=e.data;0===e.code&&(d(t.details),g(t.name))})))}),[r]),u.a.createElement(n.default,{className:"template-add",__source:{fileName:m,lineNumber:40,columnNumber:9}},u.a.createElement(i.default,{xl:{span:18,offset:3},lg:{span:18,offset:3},md:{span:20,offset:2},__source:{fileName:m,lineNumber:41,columnNumber:13}},u.a.createElement("div",{__source:{fileName:m,lineNumber:43,columnNumber:17}},u.a.createElement("div",{className:"template-view-top",__source:{fileName:m,lineNumber:44,columnNumber:21}},u.a.createElement("div",{className:"template-view-breadcrumb",__source:{fileName:m,lineNumber:45,columnNumber:25}},u.a.createElement("span",{onClick:function(){return e.history.goBack()},className:"template-back",__source:{fileName:m,lineNumber:46,columnNumber:29}},"模板"),u.a.createElement("svg",{className:"svg-icon","aria-hidden":"true",__source:{fileName:m,lineNumber:47,columnNumber:29}},u.a.createElement("use",{xlinkHref:"#icon-rightBlue",__source:{fileName:m,lineNumber:48,columnNumber:33}})),u.a.createElement("span",{__source:{fileName:m,lineNumber:50,columnNumber:29}},w))),b&&u.a.createElement(s.PreviewEditor,{value:b,relationWorkStore:t,base_url:"",tenant:v,__source:{fileName:m,lineNumber:54,columnNumber:34}}))))})))},181:function(e,t,r){"use strict";r.d(t,"a",(function(){return k}));var n,i,o,a,u,c,l,s,f,p,m,h,b=r(28),d=r(66);function v(e){return(v="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function y(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function w(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?y(Object(r),!0).forEach((function(t){E(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):y(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function g(){/*! regenerator-runtime -- Copyright (c) 2014-present, Facebook, Inc. -- license (MIT): https://github.com/facebook/regenerator/blob/main/LICENSE */g=function(){return e};var e={},t=Object.prototype,r=t.hasOwnProperty,n="function"==typeof Symbol?Symbol:{},i=n.iterator||"@@iterator",o=n.asyncIterator||"@@asyncIterator",a=n.toStringTag||"@@toStringTag";function u(e,t,r){return Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{u({},"")}catch(e){u=function(e,t,r){return e[t]=r}}function c(e,t,r,n){var i=t&&t.prototype instanceof f?t:f,o=Object.create(i.prototype),a=new L(n||[]);return o._invoke=function(e,t,r){var n="suspendedStart";return function(i,o){if("executing"===n)throw new Error("Generator is already running");if("completed"===n){if("throw"===i)throw o;return _()}for(r.method=i,r.arg=o;;){var a=r.delegate;if(a){var u=j(a,r);if(u){if(u===s)continue;return u}}if("next"===r.method)r.sent=r._sent=r.arg;else if("throw"===r.method){if("suspendedStart"===n)throw n="completed",r.arg;r.dispatchException(r.arg)}else"return"===r.method&&r.abrupt("return",r.arg);n="executing";var c=l(e,t,r);if("normal"===c.type){if(n=r.done?"completed":"suspendedYield",c.arg===s)continue;return{value:c.arg,done:r.done}}"throw"===c.type&&(n="completed",r.method="throw",r.arg=c.arg)}}}(e,r,a),o}function l(e,t,r){try{return{type:"normal",arg:e.call(t,r)}}catch(e){return{type:"throw",arg:e}}}e.wrap=c;var s={};function f(){}function p(){}function m(){}var h={};u(h,i,(function(){return this}));var b=Object.getPrototypeOf,d=b&&b(b(E([])));d&&d!==t&&r.call(d,i)&&(h=d);var y=m.prototype=f.prototype=Object.create(h);function w(e){["next","throw","return"].forEach((function(t){u(e,t,(function(e){return this._invoke(t,e)}))}))}function O(e,t){var n;this._invoke=function(i,o){function a(){return new t((function(n,a){!function n(i,o,a,u){var c=l(e[i],e,o);if("throw"!==c.type){var s=c.arg,f=s.value;return f&&"object"==v(f)&&r.call(f,"__await")?t.resolve(f.__await).then((function(e){n("next",e,a,u)}),(function(e){n("throw",e,a,u)})):t.resolve(f).then((function(e){s.value=e,a(s)}),(function(e){return n("throw",e,a,u)}))}u(c.arg)}(i,o,n,a)}))}return n=n?n.then(a,a):a()}}function j(e,t){var r=e.iterator[t.method];if(void 0===r){if(t.delegate=null,"throw"===t.method){if(e.iterator.return&&(t.method="return",t.arg=void 0,j(e,t),"throw"===t.method))return s;t.method="throw",t.arg=new TypeError("The iterator does not provide a 'throw' method")}return s}var n=l(r,e.iterator,t.arg);if("throw"===n.type)return t.method="throw",t.arg=n.arg,t.delegate=null,s;var i=n.arg;return i?i.done?(t[e.resultName]=i.value,t.next=e.nextLoc,"return"!==t.method&&(t.method="next",t.arg=void 0),t.delegate=null,s):i:(t.method="throw",t.arg=new TypeError("iterator result is not an object"),t.delegate=null,s)}function x(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function N(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function L(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(x,this),this.reset(!0)}function E(e){if(e){var t=e[i];if(t)return t.call(e);if("function"==typeof e.next)return e;if(!isNaN(e.length)){var n=-1,o=function t(){for(;++n<e.length;)if(r.call(e,n))return t.value=e[n],t.done=!1,t;return t.value=void 0,t.done=!0,t};return o.next=o}}return{next:_}}function _(){return{value:void 0,done:!0}}return p.prototype=m,u(y,"constructor",m),u(m,"constructor",p),p.displayName=u(m,a,"GeneratorFunction"),e.isGeneratorFunction=function(e){var t="function"==typeof e&&e.constructor;return!!t&&(t===p||"GeneratorFunction"===(t.displayName||t.name))},e.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,m):(e.__proto__=m,u(e,a,"GeneratorFunction")),e.prototype=Object.create(y),e},e.awrap=function(e){return{__await:e}},w(O.prototype),u(O.prototype,o,(function(){return this})),e.AsyncIterator=O,e.async=function(t,r,n,i,o){void 0===o&&(o=Promise);var a=new O(c(t,r,n,i),o);return e.isGeneratorFunction(r)?a:a.next().then((function(e){return e.done?e.value:a.next()}))},w(y),u(y,a,"Generator"),u(y,i,(function(){return this})),u(y,"toString",(function(){return"[object Generator]"})),e.keys=function(e){var t=[];for(var r in e)t.push(r);return t.reverse(),function r(){for(;t.length;){var n=t.pop();if(n in e)return r.value=n,r.done=!1,r}return r.done=!0,r}},e.values=E,L.prototype={constructor:L,reset:function(e){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(N),!e)for(var t in this)"t"===t.charAt(0)&&r.call(this,t)&&!isNaN(+t.slice(1))&&(this[t]=void 0)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(e){if(this.done)throw e;var t=this;function n(r,n){return a.type="throw",a.arg=e,t.next=r,n&&(t.method="next",t.arg=void 0),!!n}for(var i=this.tryEntries.length-1;i>=0;--i){var o=this.tryEntries[i],a=o.completion;if("root"===o.tryLoc)return n("end");if(o.tryLoc<=this.prev){var u=r.call(o,"catchLoc"),c=r.call(o,"finallyLoc");if(u&&c){if(this.prev<o.catchLoc)return n(o.catchLoc,!0);if(this.prev<o.finallyLoc)return n(o.finallyLoc)}else if(u){if(this.prev<o.catchLoc)return n(o.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return n(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;n>=0;--n){var i=this.tryEntries[n];if(i.tryLoc<=this.prev&&r.call(i,"finallyLoc")&&this.prev<i.finallyLoc){var o=i;break}}o&&("break"===e||"continue"===e)&&o.tryLoc<=t&&t<=o.finallyLoc&&(o=null);var a=o?o.completion:{};return a.type=e,a.arg=t,o?(this.method="next",this.next=o.finallyLoc,s):this.complete(a)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),s},finish:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var r=this.tryEntries[t];if(r.finallyLoc===e)return this.complete(r.completion,r.afterLoc),N(r),s}},catch:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var r=this.tryEntries[t];if(r.tryLoc===e){var n=r.completion;if("throw"===n.type){var i=n.arg;N(r)}return i}}throw new Error("illegal catch attempt")},delegateYield:function(e,t,r){return this.delegate={iterator:E(e),resultName:t,nextLoc:r},"next"===this.method&&(this.arg=void 0),s}},e}function O(e,t,r,n,i,o,a){try{var u=e[o](a),c=u.value}catch(e){return void r(e)}u.done?t(c):Promise.resolve(c).then(n,i)}function j(e){return function(){var t=this,r=arguments;return new Promise((function(n,i){var o=e.apply(t,r);function a(e){O(o,n,i,a,u,"next",e)}function u(e){O(o,n,i,a,u,"throw",e)}a(void 0)}))}}function x(e,t,r,n){r&&Object.defineProperty(e,t,{enumerable:r.enumerable,configurable:r.configurable,writable:r.writable,value:r.initializer?r.initializer.call(n):void 0})}function N(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function L(e,t,r){return t&&N(e.prototype,t),r&&N(e,r),Object.defineProperty(e,"prototype",{writable:!1}),e}function E(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function _(e,t,r,n,i){var o={};return Object.keys(n).forEach((function(e){o[e]=n[e]})),o.enumerable=!!o.enumerable,o.configurable=!!o.configurable,("value"in o||o.initializer)&&(o.writable=!0),o=r.slice().reverse().reduce((function(r,n){return n(e,t,r)||r}),o),i&&void 0!==o.initializer&&(o.value=o.initializer?o.initializer.call(i):void 0,o.initializer=void 0),void 0===o.initializer&&(Object.defineProperty(e,t,o),o=null),o}var k=new(i=_((n=L((function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),x(this,"templateList",i,this),x(this,"templatePageParams",o,this),x(this,"setTemPlateList",a,this),x(this,"createDocumentTemplate",u,this),x(this,"findDocumentTemplateList",c,this),x(this,"findDocumentTemplate",l,this),x(this,"updateDocumentTemplate",s,this),x(this,"deleteDocumentTemplate",f,this),x(this,"findIconList",p,this),x(this,"creatIcon",m,this),x(this,"upload",h,this)}))).prototype,"templateList",[b.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return[]}}),o=_(n.prototype,"templatePageParams",[b.observable],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return{current:1,pageSize:10,total:1}}}),a=_(n.prototype,"setTemPlateList",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(t){e.templateList=t}}}),u=_(n.prototype,"createDocumentTemplate",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(d.a)("/documentTemplate/createDocumentTemplate",t);case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),c=_(n.prototype,"findDocumentTemplateList",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){var e=this;return function(){var t=j(g().mark((function t(r){var n,i;return g().wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return Object.assign(e.templatePageParams,w({},r)),n={name:e.templatePageParams.name,orderParams:[{name:"name",orderType:"asc"}]},t.next=4,Object(d.a)("/documentTemplate/findDocumentTemplateList",n);case 4:return 0===(i=t.sent).code&&(e.templateList=i.data),t.abrupt("return",i);case 7:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}}),l=_(n.prototype,"findDocumentTemplate",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r,n;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(r=new FormData).append("id",t),e.next=4,Object(d.a)("/documentTemplate/findDocumentTemplate",r);case 4:return n=e.sent,e.abrupt("return",n);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),s=_(n.prototype,"updateDocumentTemplate",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(d.a)("/documentTemplate/updateDocumentTemplate",t);case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),f=_(n.prototype,"deleteDocumentTemplate",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r,n;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(r=new FormData).append("id",t),e.next=4,Object(d.a)("/documentTemplate/deleteDocumentTemplate",r);case 4:return n=e.sent,e.abrupt("return",n);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),p=_(n.prototype,"findIconList",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(d.a)("/icon/findIconList",t);case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),m=_(n.prototype,"creatIcon",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(d.a)("/icon/createIcon",t);case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),h=_(n.prototype,"upload",[b.action],{configurable:!0,enumerable:!0,writable:!0,initializer:function(){return function(){var e=j(g().mark((function e(t){var r;return g().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(d.a)("/dfs/upload",t);case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()}}),n)}}]);
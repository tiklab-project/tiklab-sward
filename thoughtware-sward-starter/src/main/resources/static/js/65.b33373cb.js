(window.webpackJsonp=window.webpackJsonp||[]).push([[65],{1100:function(e,t,n){"use strict";n.r(t);n(426);var r=n(427),a=(n(429),n(430)),m=n(0),c=n.n(m),u=n(65),l=n(837),o=(n(838),n(866)),i=n(14),s="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/document/share/components/ShareMarkdown.js";function N(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==n)return;var r,a,m=[],c=!0,u=!1;try{for(n=n.call(e);!(c=(r=n.next()).done)&&(m.push(r.value),!t||m.length!==t);c=!0);}catch(e){u=!0,a=e}finally{try{c||null==n.return||n.return()}finally{if(u)throw a}}return m}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return b(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);"Object"===n&&e.constructor&&(n=e.constructor.name);if("Map"===n||"Set"===n)return Array.from(e);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return b(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function b(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var f=[{type:"code",children:[{type:"paragraph",children:[{text:""}]}]}];t.default=Object(u.inject)("shareStore")(Object(u.observer)(Object(i.h)((function(e){var t=e.shareStore,n=t.documentView,u=t.commentView,i=(t.judgeAuthCode,N(Object(m.useState)(!1),2)),b=i[0],d=i[1],_=N(Object(m.useState)(f),2),p=_[0],v=_[1],h=N(Object(m.useState)({name:"",likenumInt:"",commentNumber:""}),2),y=h[0],E=h[1];return Object(m.useEffect)((function(){u({documentId:e.match.params.id}).then((function(e){})),n({documentId:e.match.params.id}).then((function(e){if(0===e.code){var t,n;if(null!==(t=e.data)&&void 0!==t&&t.details){var r,a=null===(r=e.data)||void 0===r?void 0:r.details;v(JSON.parse(a))}else v(f);E(null===(n=e.data)||void 0===n?void 0:n.node)}}))}),[e.match.params.id]),c.a.createElement("div",{className:"markdown-share-examine",__source:{fileName:s,lineNumber:66,columnNumber:9}},c.a.createElement("div",{className:"examine-title",__source:{fileName:s,lineNumber:67,columnNumber:13}},c.a.createElement("span",{className:"examine-name",__source:{fileName:s,lineNumber:68,columnNumber:17}},y.name)),c.a.createElement("div",{className:"examine-content",__source:{fileName:s,lineNumber:70,columnNumber:13}},c.a.createElement(r.default,{style:{flex:1,overflow:"auto"},__source:{fileName:s,lineNumber:71,columnNumber:17}},c.a.createElement(a.default,{className:"repositorydetail-content-col",xl:{span:18,offset:3},lg:{span:20,offset:2},__source:{fileName:s,lineNumber:72,columnNumber:21}},c.a.createElement("div",{style:{paddingTop:"10px"},__source:{fileName:s,lineNumber:73,columnNumber:25}},c.a.createElement(l.MarkdownView,{value:p,base_url:"",__source:{fileName:s,lineNumber:74,columnNumber:29}})))),b&&c.a.createElement(o.a,{documentId:e.match.params.id,setShowComment:d,__source:{fileName:s,lineNumber:80,columnNumber:36}})),c.a.createElement("div",{className:"comment-box",__source:{fileName:s,lineNumber:85,columnNumber:13}},c.a.createElement("div",{className:"comment-box-item",__source:{fileName:s,lineNumber:86,columnNumber:17}},c.a.createElement("svg",{className:"midden-icon","aria-hidden":"true",onClick:function(){return d(!b)},__source:{fileName:s,lineNumber:87,columnNumber:21}},c.a.createElement("use",{xlinkHref:"#icon-comment",__source:{fileName:s,lineNumber:88,columnNumber:25}})),c.a.createElement("div",{className:"commnet-num",__source:{fileName:s,lineNumber:90,columnNumber:21}},y.commentNumber))))}))))},801:function(e,t,n){"use strict";var r=n(0),a=n.n(r);t.a=function(e){var t=e.name,n=e.size,r=t?t.charAt(0):"A";return a.a.createElement("div",{className:"big"===n?"user-big-icon":"user-icon",__source:{fileName:"/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/common/UserIcon/UserIcon.js",lineNumber:7,columnNumber:9}},r)}},866:function(e,t,n){"use strict";n(777);var r=n(52),a=n(0),m=n.n(a),c=n(65),u=n(801),l="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/document/share/components/CommentShare.js";function o(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==n)return;var r,a,m=[],c=!0,u=!1;try{for(n=n.call(e);!(c=(r=n.next()).done)&&(m.push(r.value),!t||m.length!==t);c=!0);}catch(e){u=!0,a=e}finally{try{c||null==n.return||n.return()}finally{if(u)throw a}}return m}(e,t)||function(e,t){if(!e)return;if("string"==typeof e)return i(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);"Object"===n&&e.constructor&&(n=e.constructor.name);if("Map"===n||"Set"===n)return Array.from(e);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return i(e,t)}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function i(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}t.a=Object(c.inject)("commentStore","shareStore")(Object(c.observer)((function(e){var t=e.commentStore,n=e.documentId,c=e.setShowComment,i=e.shareStore,s=t.findCommentPage,N=i.commentView,b=o(Object(a.useState)(),2),f=b[0],d=b[1],_=o(Object(a.useState)(10),2),p=_[0],v=_[1],h=o(Object(a.useState)(0),2),y=h[0],E=h[1];Object(a.useEffect)((function(){N({documentId:n,pageParam:{pageSize:1,currentPage:p}}).then((function(e){0===e.code&&(d(e.data),E(e.data.totalPage))}))}),[n]);return m.a.createElement("div",{className:"share-comment",__source:{fileName:l,lineNumber:54,columnNumber:9}},m.a.createElement("div",{className:"comment-top",__source:{fileName:l,lineNumber:55,columnNumber:13}},m.a.createElement("span",{className:"comment-title",__source:{fileName:l,lineNumber:56,columnNumber:17}},"评论"),m.a.createElement("svg",{className:"svg-icon","aria-hidden":"true",onClick:function(){return c(!1)},__source:{fileName:l,lineNumber:57,columnNumber:17}},m.a.createElement("use",{xlinkHref:"#icon-close",__source:{fileName:l,lineNumber:58,columnNumber:21}}))),m.a.createElement("div",{className:"comment-list",__source:{fileName:l,lineNumber:61,columnNumber:13}},f&&f.length>0?m.a.createElement(m.a.Fragment,null,f&&f.map((function(e){return m.a.createElement("div",{className:"comment-item",key:e.id,__source:{fileName:l,lineNumber:66,columnNumber:40}},m.a.createElement("div",{className:"comment-list-top",__source:{fileName:l,lineNumber:67,columnNumber:37}},m.a.createElement("div",{className:"comment-user",__source:{fileName:l,lineNumber:68,columnNumber:41}},m.a.createElement(u.a,{size:"big",name:e.user.nickname,__source:{fileName:l,lineNumber:69,columnNumber:45}}),m.a.createElement("span",{className:"user-name",__source:{fileName:l,lineNumber:70,columnNumber:45}},e.user.name)),m.a.createElement("div",{className:"comment-time",__source:{fileName:l,lineNumber:72,columnNumber:41}},e.createTime.slice(5,16))),m.a.createElement("div",{className:"comment-content",__source:{fileName:l,lineNumber:77,columnNumber:37}},e.details),e.commentList&&e.commentList.map((function(e){return m.a.createElement("div",{className:"comment-item commnet-children-item",key:e.id,__source:{fileName:l,lineNumber:85,columnNumber:52}},m.a.createElement("div",{className:"comment-list-top",__source:{fileName:l,lineNumber:90,columnNumber:49}},m.a.createElement("div",{className:"comment-user",__source:{fileName:l,lineNumber:91,columnNumber:53}},m.a.createElement(u.a,{size:"big",name:e.user.nickname,__source:{fileName:l,lineNumber:92,columnNumber:57}}),m.a.createElement("span",{className:"user-name",__source:{fileName:l,lineNumber:93,columnNumber:57}},e.user.name,"回复了：",e.aimAtUser.name)),m.a.createElement("div",{className:"comment-time",__source:{fileName:l,lineNumber:95,columnNumber:53}},e.createTime.slice(5,16))),m.a.createElement("div",{className:"comment-content",__source:{fileName:l,lineNumber:99,columnNumber:49}},e.details))})))})),y>1&&p<y&&m.a.createElement("div",{className:"comment-more-botton",onClick:function(){return v(e=p+1),void s({documentId:n,pageParam:{pageSize:1,currentPage:e}}).then((function(e){if(0===e.code){var t=f.concat(e.data.dataList);d(t),E(e.data.totalPage)}}));var e},__source:{fileName:l,lineNumber:111,columnNumber:73}},"查看更多...")):m.a.createElement(r.default,{image:"/images/nodata.png",description:"暂时没有评价~",__source:{fileName:l,lineNumber:115,columnNumber:25}})))})))}}]);
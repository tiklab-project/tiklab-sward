(window.webpackJsonp=window.webpackJsonp||[]).push([[40],{1012:function(e,n,t){"use strict";t.r(n),t.d(n,"default",(function(){return N}));t(35);var m=t(36),r=(t(37),t(38)),a=t(0),o=t.n(a),c=t(22),i=t(286),l=(t(287),t(26));Object(l.a)(".markdown-share-examine {\n  height: calc(100vh - 48px);\n  background-color: #fff;\n}\n.markdown-share-examine .examine-title {\n  line-height: 50px;\n  height: 50px;\n  font-size: 18px;\n  font-weight: 600;\n  border: 1px solid #f7f8fa;\n  padding: 0 20px;\n}\n.markdown-share-examine .examine-content {\n  display: flex;\n  justify-content: space-between;\n  height: calc(100% - 60px);\n  overflow: hidden;\n}\n.markdown-share-examine .preview-editor {\n  min-height: 300px;\n}\n.markdown-share-examine .comment-box {\n  box-shadow: rgba(112, 144, 176, 0.24) 0px 0px 1px 0px, rgba(112, 144, 176, 0.12) 0px 6px 24px 4px;\n  padding: 6px;\n  border-radius: 6px;\n  background-color: rgb(255, 255, 255);\n  position: absolute;\n  bottom: 150;\n  right: 20px;\n}\n.markdown-share-examine .comment-box .comment-box-item {\n  padding: 5px;\n}\n.markdown-share-examine .comment-box .comment-item {\n  cursor: pointer;\n}\n.markdown-share-examine .comment-box .commnet-num {\n  font-size: 11px;\n  transform: scale(0.8);\n  position: absolute;\n  top: 0;\n  right: 4px;\n}");var s=t(314),u=t(45),d="/Users/yuanjiexuan/Desktop/bate/project-web/tiklab-sward-ui/src/document/share/components/ShareMarkdown.js";function p(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var t=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==t)return;var m,r,a=[],o=!0,c=!1;try{for(t=t.call(e);!(o=(m=t.next()).done)&&(a.push(m.value),!n||a.length!==n);o=!0);}catch(e){c=!0,r=e}finally{try{o||null==t.return||t.return()}finally{if(c)throw r}}return a}(e,n)||function(e,n){if(!e)return;if("string"==typeof e)return f(e,n);var t=Object.prototype.toString.call(e).slice(8,-1);"Object"===t&&e.constructor&&(t=e.constructor.name);if("Map"===t||"Set"===t)return Array.from(e);if("Arguments"===t||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t))return f(e,n)}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function f(e,n){(null==n||n>e.length)&&(n=e.length);for(var t=0,m=new Array(n);t<n;t++)m[t]=e[t];return m}var b=[{type:"code",children:[{type:"paragraph",children:[{text:""}]}]}],N=Object(c.inject)("shareStore")(Object(c.observer)(Object(u.h)((function(e){var n=e.shareStore,t=n.documentView,c=n.commentView;n.judgeAuthCode;var l=p(Object(a.useState)(!1),2),u=l[0],f=l[1],N=p(Object(a.useState)(b),2),h=N[0],x=N[1],g=p(Object(a.useState)({name:"",likenumInt:"",commentNumber:""}),2),_=g[0],v=g[1];return Object(a.useEffect)((function(){c({documentId:e.match.params.id}).then((function(e){})),t({documentId:e.match.params.id}).then((function(e){if(0===e.code){var n,t;if(null!==(n=e.data)&&void 0!==n&&n.details){var m,r=null===(m=e.data)||void 0===m?void 0:m.details;x(JSON.parse(r))}else x(b);v(null===(t=e.data)||void 0===t?void 0:t.node)}}))}),[e.match.params.id]),o.a.createElement("div",{className:"markdown-share-examine",__source:{fileName:d,lineNumber:66,columnNumber:9}},o.a.createElement("div",{className:"examine-title",__source:{fileName:d,lineNumber:67,columnNumber:13}},o.a.createElement("span",{className:"examine-name",__source:{fileName:d,lineNumber:68,columnNumber:17}},_.name)),o.a.createElement("div",{className:"examine-content",__source:{fileName:d,lineNumber:70,columnNumber:13}},o.a.createElement(m.default,{style:{flex:1,overflow:"auto"},__source:{fileName:d,lineNumber:71,columnNumber:17}},o.a.createElement(r.default,{className:"repositorydetail-content-col",xl:{span:18,offset:3},lg:{span:20,offset:2},__source:{fileName:d,lineNumber:72,columnNumber:21}},o.a.createElement("div",{style:{paddingTop:"10px"},__source:{fileName:d,lineNumber:73,columnNumber:25}},o.a.createElement(i.MarkdownView,{value:h,base_url:"",__source:{fileName:d,lineNumber:74,columnNumber:29}})))),u&&o.a.createElement(s.a,{documentId:e.match.params.id,setShowComment:f,__source:{fileName:d,lineNumber:80,columnNumber:36}})),o.a.createElement("div",{className:"comment-box",__source:{fileName:d,lineNumber:85,columnNumber:13}},o.a.createElement("div",{className:"comment-box-item",__source:{fileName:d,lineNumber:86,columnNumber:17}},o.a.createElement("svg",{className:"midden-icon","aria-hidden":"true",onClick:function(){return f(!u)},__source:{fileName:d,lineNumber:87,columnNumber:21}},o.a.createElement("use",{xlinkHref:"#icon-comment",__source:{fileName:d,lineNumber:88,columnNumber:25}})),o.a.createElement("div",{className:"commnet-num",__source:{fileName:d,lineNumber:90,columnNumber:21}},_.commentNumber))))}))))},114:function(e,n,t){"use strict";t.d(n,"a",(function(){return o}));var m=t(0),r=t.n(m),a=t(26);Object(a.a)(".user-icon {\n  width: 16px;\n  height: 16px;\n  border-radius: 16px;\n  background-color: #ddd;\n  font-size: 12px;\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  color: #fff;\n}\n\n.user-big-icon {\n  width: 20px;\n  height: 20px;\n  border-radius: 20px;\n  background-color: #ddd;\n  font-size: 12px;\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  color: #fff;\n}");var o=function(e){var n=e.name,t=e.size,m=n?n.charAt(0):"A";return r.a.createElement("div",{className:"big"===t?"user-big-icon":"user-icon",__source:{fileName:"/Users/yuanjiexuan/Desktop/bate/project-web/tiklab-sward-ui/src/common/UserIcon/UserIcon.js",lineNumber:7,columnNumber:9}},m)}},314:function(e,n,t){"use strict";t.d(n,"a",(function(){return d}));t(73);var m=t(78),r=t(0),a=t.n(r),o=t(22),c=t(26);Object(c.a)(".share-comment {\n  display: block;\n  padding: 10px;\n  border-radius: 4px;\n  background: #fff;\n  box-shadow: 0 3px 6px -4px rgba(0, 0, 0, 0.1215686275), 0 6px 16px rgba(0, 0, 0, 0.0784313725), 0 9px 28px 8px rgba(0, 0, 0, 0.0509803922);\n  transition: all 5s linear;\n  height: 100%;\n  overflow: auto;\n  width: 400px;\n  position: absolute;\n  right: 0;\n  z-index: 10;\n}\n.share-comment .comment-top {\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n}\n.share-comment .comment-top .comment-title {\n  font-size: 15px;\n  font-weight: 600;\n}\n.share-comment .hidden-comment {\n  display: none;\n}\n.share-comment .comment-list {\n  margin: 20px 0;\n  border-radius: 10px;\n}\n.share-comment .comment-list .title {\n  font-size: 18px;\n  font-weight: 600;\n}\n.share-comment .comment-list .edit-comment {\n  display: flex;\n  align-items: center;\n  justify-content: flex-start;\n  margin: 10px 0;\n}\n.share-comment .comment-list .edit-comment .icon-svg {\n  width: 30px;\n  height: 30px;\n  border-radius: 15px;\n  margin-right: 15px;\n}\n.share-comment .comment-list .edit-comment .ant-input {\n  border-radius: 15px;\n  margin-right: 10px;\n  border: 0;\n  flex: 1;\n  background: #f7f7f7;\n}\n.share-comment .comment-list .comment-item .comment-list-top {\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  margin: 20px 0;\n}\n.share-comment .comment-list .comment-item .comment-list-top .comment-user {\n  display: flex;\n  align-items: center;\n  gap: 10px;\n}\n.share-comment .comment-list .comment-item .comment-list-top .comment-user .icon-svg {\n  width: 30px;\n  height: 30px;\n  border-radius: 15px;\n  margin-right: 10px;\n}\n.share-comment .comment-list .comment-item .comment-list-top .comment-user .user-name {\n  font-size: 14px;\n  font-weight: 600;\n  color: #0053ca;\n}\n.share-comment .comment-list .comment-item .comment-list-top .comment-time {\n  font-size: 12px;\n  color: #6b778c;\n}\n.share-comment .comment-list .comment-item .comment-content {\n  margin-left: 40px;\n  margin-bottom: 20px;\n}\n.share-comment .comment-list .comment-item .comment-operate {\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  margin-left: 40px;\n  font-size: 12px;\n  color: #6b778c;\n}\n.share-comment .comment-list .comment-item .comment-operate .comment-edit, .share-comment .comment-list .comment-item .comment-operate .comment-delete, .share-comment .comment-list .comment-item .comment-operate .comment-reply, .share-comment .comment-list .comment-item .comment-operate .comment-like {\n  cursor: pointer;\n}\n.share-comment .comment-list .comment-item .comment-operate span {\n  margin-right: 10px;\n}\n.share-comment .comment-list .comment-more-botton {\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  cursor: pointer;\n  color: #0053ca;\n}\n.share-comment .comment-list .edit-comment-show {\n  display: flex;\n  margin: 10px 0 10px 30px;\n}\n.share-comment .comment-list .edit-comment-hidden {\n  display: none;\n}\n.share-comment .comment-list .commnet-children-item {\n  margin-left: 30px;\n}");var i=t(114),l="/Users/yuanjiexuan/Desktop/bate/project-web/tiklab-sward-ui/src/document/share/components/CommentShare.js";function s(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var t=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==t)return;var m,r,a=[],o=!0,c=!1;try{for(t=t.call(e);!(o=(m=t.next()).done)&&(a.push(m.value),!n||a.length!==n);o=!0);}catch(e){c=!0,r=e}finally{try{o||null==t.return||t.return()}finally{if(c)throw r}}return a}(e,n)||function(e,n){if(!e)return;if("string"==typeof e)return u(e,n);var t=Object.prototype.toString.call(e).slice(8,-1);"Object"===t&&e.constructor&&(t=e.constructor.name);if("Map"===t||"Set"===t)return Array.from(e);if("Arguments"===t||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t))return u(e,n)}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function u(e,n){(null==n||n>e.length)&&(n=e.length);for(var t=0,m=new Array(n);t<n;t++)m[t]=e[t];return m}var d=Object(o.inject)("commentStore","shareStore")(Object(o.observer)((function(e){var n=e.commentStore,t=e.documentId,o=e.setShowComment,c=e.shareStore,u=n.findCommentPage,d=c.commentView,p=s(Object(r.useState)(),2),f=p[0],b=p[1],N=s(Object(r.useState)(10),2),h=N[0],x=N[1],g=s(Object(r.useState)(0),2),_=g[0],v=g[1];Object(r.useEffect)((function(){d({documentId:t,pageParam:{pageSize:1,currentPage:h}}).then((function(e){0===e.code&&(b(e.data),v(e.data.totalPage))}))}),[t]);return a.a.createElement("div",{className:"share-comment",__source:{fileName:l,lineNumber:54,columnNumber:9}},a.a.createElement("div",{className:"comment-top",__source:{fileName:l,lineNumber:55,columnNumber:13}},a.a.createElement("span",{className:"comment-title",__source:{fileName:l,lineNumber:56,columnNumber:17}},"评论"),a.a.createElement("svg",{className:"svg-icon","aria-hidden":"true",onClick:function(){return o(!1)},__source:{fileName:l,lineNumber:57,columnNumber:17}},a.a.createElement("use",{xlinkHref:"#icon-close",__source:{fileName:l,lineNumber:58,columnNumber:21}}))),a.a.createElement("div",{className:"comment-list",__source:{fileName:l,lineNumber:61,columnNumber:13}},f&&f.length>0?a.a.createElement(a.a.Fragment,null,f&&f.map((function(e){return a.a.createElement("div",{className:"comment-item",key:e.id,__source:{fileName:l,lineNumber:66,columnNumber:40}},a.a.createElement("div",{className:"comment-list-top",__source:{fileName:l,lineNumber:67,columnNumber:37}},a.a.createElement("div",{className:"comment-user",__source:{fileName:l,lineNumber:68,columnNumber:41}},a.a.createElement(i.a,{size:"big",name:e.user.nickname,__source:{fileName:l,lineNumber:69,columnNumber:45}}),a.a.createElement("span",{className:"user-name",__source:{fileName:l,lineNumber:70,columnNumber:45}},e.user.name)),a.a.createElement("div",{className:"comment-time",__source:{fileName:l,lineNumber:72,columnNumber:41}},e.createTime.slice(5,16))),a.a.createElement("div",{className:"comment-content",__source:{fileName:l,lineNumber:77,columnNumber:37}},e.details),e.commentList&&e.commentList.map((function(e){return a.a.createElement("div",{className:"comment-item commnet-children-item",key:e.id,__source:{fileName:l,lineNumber:85,columnNumber:52}},a.a.createElement("div",{className:"comment-list-top",__source:{fileName:l,lineNumber:90,columnNumber:49}},a.a.createElement("div",{className:"comment-user",__source:{fileName:l,lineNumber:91,columnNumber:53}},a.a.createElement(i.a,{size:"big",name:e.user.nickname,__source:{fileName:l,lineNumber:92,columnNumber:57}}),a.a.createElement("span",{className:"user-name",__source:{fileName:l,lineNumber:93,columnNumber:57}},e.user.name,"回复了：",e.aimAtUser.name)),a.a.createElement("div",{className:"comment-time",__source:{fileName:l,lineNumber:95,columnNumber:53}},e.createTime.slice(5,16))),a.a.createElement("div",{className:"comment-content",__source:{fileName:l,lineNumber:99,columnNumber:49}},e.details))})))})),_>1&&h<_&&a.a.createElement("div",{className:"comment-more-botton",onClick:function(){return x(e=h+1),void u({documentId:t,pageParam:{pageSize:1,currentPage:e}}).then((function(e){if(0===e.code){var n=f.concat(e.data.dataList);b(n),v(e.data.totalPage)}}));var e},__source:{fileName:l,lineNumber:111,columnNumber:73}},"查看更多...")):a.a.createElement(m.default,{image:"/images/nodata.png",description:"暂时没有评价~",__source:{fileName:l,lineNumber:115,columnNumber:25}})))})))}}]);
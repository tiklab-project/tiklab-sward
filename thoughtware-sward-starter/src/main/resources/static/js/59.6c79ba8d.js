(window.webpackJsonp=window.webpackJsonp||[]).push([[59],{1007:function(e,n,t){"use strict";t.r(n),t.d(n,"default",(function(){return b}));t(41);var i=t(39),o=t(0),r=t.n(o),a=t(45),d=t(26);Object(d.a)(".document-markdown-edit {\n  background-color: #fff;\n  height: calc(100vh - 48px);\n}\n.document-markdown-edit .edit-top {\n  line-height: 50px;\n  height: 50px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  padding: 0 20px;\n  border-bottom: 1px solid #EFF1F7;\n  background-color: #F7F9FE;\n}\n.document-markdown-edit .edit-top .edit-title {\n  font-size: 18px;\n  font-weight: 600;\n  height: 40px;\n  outline: none;\n  line-height: 40px;\n  border-radius: 5px;\n  box-sizing: border-box;\n  padding: 0 10px;\n}\n.document-markdown-edit .edit-top .edit-title:hover {\n  background-color: #fff;\n}\n.document-markdown-edit .edit-top .edit-title-focus {\n  background-color: #fff;\n}\n.document-markdown-edit .edit-top .document-edit {\n  display: flex;\n  align-items: center;\n}\n.document-markdown-edit .edit-top .inline {\n  border-left: 2px solid #f0f0f0;\n  height: 20px;\n  width: 1px;\n  margin-right: 20px;\n}\n.document-markdown-edit .edit-markdown {\n  height: calc(100% - 50px);\n}\n.document-markdown-edit .edit-markdown .markdown-content {\n  height: calc(100% - 50px);\n}\n.document-markdown-edit .edit-markdown .markdown-edit {\n  overflow: auto;\n}\n.document-markdown-edit .edit-markdown .markdown-view {\n  overflow: auto;\n}\n.document-markdown-edit .edit-big {\n  background-color: #F7F9FE;\n  padding-left: 13px;\n}\n.document-markdown-edit .edit-big .edit-big-toolbar {\n  border-bottom: 0;\n  justify-content: flex-start;\n}\n.document-markdown-edit .edit-right {\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  gap: 10px;\n}\n.document-markdown-edit .edit-right .icon-svg {\n  width: 20px;\n  height: 20px;\n  margin-right: 20px;\n}\n.document-markdown-edit .edit-right .right-icon {\n  width: 20px;\n  height: 20px;\n}\n.document-markdown-edit .document-examine-content {\n  margin-top: 10px;\n  height: calc(100% - 120px);\n  overflow: auto;\n}\n.document-markdown-edit .document-title-input {\n  margin: 20px 0;\n  height: 50px;\n  line-height: 50px;\n  padding: 0;\n  font-size: 30px;\n  font-weight: 600;\n}");var c=t(286),u=t(80),m=t(385),l=(t(287),t(128)),s=t(285),f=t(223),p="/Users/yuanjiexuan/Desktop/bate/project-web/thoughtware-sward-ui/src/document/markdown/components/MarkdownEdit.js";function g(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var t=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null==t)return;var i,o,r=[],a=!0,d=!1;try{for(t=t.call(e);!(a=(i=t.next()).done)&&(r.push(i.value),!n||r.length!==n);a=!0);}catch(e){d=!0,o=e}finally{try{a||null==t.return||t.return()}finally{if(d)throw o}}return r}(e,n)||function(e,n){if(!e)return;if("string"==typeof e)return h(e,n);var t=Object.prototype.toString.call(e).slice(8,-1);"Object"===t&&e.constructor&&(t=e.constructor.name);if("Map"===t||"Set"===t)return Array.from(e);if("Arguments"===t||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t))return h(e,n)}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function h(e,n){(null==n||n>e.length)&&(n=e.length);for(var t=0,i=new Array(n);t<n;t++)i[t]=e[t];return i}var b=Object(a.h)((function(e){var n=m.a.findDocument,t=m.a.updateDocument,a=l.a.documentTitle,d=l.a.setDocumentTitle,h=e.match.params.id,b=e.match.params.repositoryId,w=g(Object(o.useState)(),2),x=w[0],k=w[1];Object(o.useEffect)((function(){k(),n(h).then((function(e){if(0===e.code){if(e.data.details){var n=e.data.details;k(JSON.parse(n))}else k([{type:"paragraph",children:[{text:"**make** **decorations** to  it _dead_ simple ."}]}]);var t=e.data.node;d(t.name)}}))}),[h]);var y=function(e,n){k(e);var o=e[0].children.map((function(e){return s.Node.string(e)})).join("\n"),r={id:h,details:JSON.stringify(e),detailText:o};t(r).then((function(e){0===e.code&&"click"===n&&i.default.success("保存成功")}))},N=Object(f.a)((function(e){y(e,"auto")}),[500]),v=function(e){var n={id:h,node:{id:h,name:e.target.innerText}};t(n).then((function(n){0===n.code&&(document.getElementById("examine-title").innerHTML=e.target.innerText,document.getElementById("file-"+h).innerHTML=e.target.innerText)})),j(!1),document.getElementById("examine-title").blur()},E=g(Object(o.useState)(),2),_=E[0],j=E[1];return r.a.createElement("div",{className:"document-markdown-edit",__source:{fileName:p,lineNumber:121,columnNumber:9}},r.a.createElement("div",{className:"edit-top",__source:{fileName:p,lineNumber:122,columnNumber:13}},r.a.createElement("div",{id:"examine-title",contentEditable:!0,suppressContentEditableWarning:!0,className:"edit-title ".concat(_?"edit-title-focus":""),onBlur:function(e){return v(e)},onKeyDown:function(e){return function(e){13===e.keyCode&&(e.stopPropagation(),e.preventDefault(),v(e))}(e)},onClick:function(){return document.getElementById("examine-title").focus(),void j(!0)},__source:{fileName:p,lineNumber:123,columnNumber:17}},a),r.a.createElement("div",{className:"edit-right",__source:{fileName:p,lineNumber:132,columnNumber:17}},r.a.createElement(u.a,{type:"primary",onClick:function(){y(x,"click")},__source:{fileName:p,lineNumber:133,columnNumber:21}},"保存"),r.a.createElement(u.a,{onClick:function(){return e.history.replace("/repositorydetail/".concat(b,"/markdownView/").concat(h))},__source:{fileName:p,lineNumber:134,columnNumber:21}},"退出编辑"))),r.a.createElement("div",{className:"edit-markdown",style:{height:"calc(100% - 50px)"},__source:{fileName:p,lineNumber:138,columnNumber:13}},x&&r.a.createElement(c.Markdown,{value:x,setValue:k,onChange:function(e){return N(e)},__source:{fileName:p,lineNumber:140,columnNumber:26}})))}))}}]);
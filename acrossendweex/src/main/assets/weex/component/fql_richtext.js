// { "framework": "Vue"}

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("\n\nvar _fql_richtext = __webpack_require__(1);\n\nvar _fql_richtext2 = _interopRequireDefault(_fql_richtext);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n_fql_richtext2.default.el = '#root';\nnew Vue(_fql_richtext2.default);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi8udGVtcC9mcWxfcmljaHRleHQuanM/YzE1MiJdLCJuYW1lcyI6WyJBcHAiLCJlbCIsIlZ1ZSJdLCJtYXBwaW5ncyI6Ijs7QUFBQTs7Ozs7O0FBQ0FBLHVCQUFJQyxFQUFKLEdBQVMsT0FBVDtBQUNBLElBQUlDLEdBQUosQ0FBUUYsc0JBQVIiLCJmaWxlIjoiMC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBBcHAgZnJvbSAnLi5cXFxcZnFsX3JpY2h0ZXh0LnZ1ZSdcbkFwcC5lbCA9ICcjcm9vdCdcbm5ldyBWdWUoQXBwKVxuICAgIFxuXG5cbi8vIFdFQlBBQ0sgRk9PVEVSIC8vXG4vLyAuLy50ZW1wL2ZxbF9yaWNodGV4dC5qcyJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///0\n");

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

eval("var __vue_exports__, __vue_options__\nvar __vue_styles__ = []\n\n/* styles */\n__vue_styles__.push(__webpack_require__(2)\n)\n\n/* script */\n__vue_exports__ = __webpack_require__(3)\n\n/* template */\nvar __vue_template__ = __webpack_require__(4)\n__vue_options__ = __vue_exports__ = __vue_exports__ || {}\nif (\n  typeof __vue_exports__.default === \"object\" ||\n  typeof __vue_exports__.default === \"function\"\n) {\nif (Object.keys(__vue_exports__).some(function (key) { return key !== \"default\" && key !== \"__esModule\" })) {console.error(\"named exports are not supported in *.vue files.\")}\n__vue_options__ = __vue_exports__ = __vue_exports__.default\n}\nif (typeof __vue_options__ === \"function\") {\n  __vue_options__ = __vue_options__.options\n}\n__vue_options__.__file = \"E:\\\\000WeexCode\\\\weex-demo\\\\src\\\\fql_richtext.vue\"\n__vue_options__.render = __vue_template__.render\n__vue_options__.staticRenderFns = __vue_template__.staticRenderFns\n__vue_options__._scopeId = \"data-v-8863e6d6\"\n__vue_options__.style = __vue_options__.style || {}\n__vue_styles__.forEach(function (module) {\n  for (var name in module) {\n    __vue_options__.style[name] = module[name]\n  }\n})\nif (typeof __register_static_styles__ === \"function\") {\n  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)\n}\n\nmodule.exports = __vue_exports__\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9mcWxfcmljaHRleHQudnVlP2EzYjciXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7QUFDQTs7QUFFQTtBQUNBLG9CQUFvQixtQkFBTyxDQUFDLENBQTRYO0FBQ3haOztBQUVBO0FBQ0Esa0JBQWtCLG1CQUFPLENBQUMsQ0FBMFE7O0FBRXBTO0FBQ0EsdUJBQXVCLG1CQUFPLENBQUMsQ0FBc1I7QUFDclQ7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLHNEQUFzRCxtREFBbUQsSUFBSTtBQUM3RztBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsQ0FBQztBQUNEO0FBQ0E7QUFDQTs7QUFFQSIsImZpbGUiOiIxLmpzIiwic291cmNlc0NvbnRlbnQiOlsidmFyIF9fdnVlX2V4cG9ydHNfXywgX192dWVfb3B0aW9uc19fXG52YXIgX192dWVfc3R5bGVzX18gPSBbXVxuXG4vKiBzdHlsZXMgKi9cbl9fdnVlX3N0eWxlc19fLnB1c2gocmVxdWlyZShcIiEhRDpcXFxcVXNlcnNcXFxcYnJpZ2h0bGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzdHlsZS1sb2FkZXIhRDpcXFxcVXNlcnNcXFxcYnJpZ2h0bGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzdHlsZS1yZXdyaXRlcj9pZD1kYXRhLXYtODg2M2U2ZDYhRDpcXFxcVXNlcnNcXFxcYnJpZ2h0bGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzZWxlY3Rvcj90eXBlPXN0eWxlcyZpbmRleD0wIS4vZnFsX3JpY2h0ZXh0LnZ1ZVwiKVxuKVxuXG4vKiBzY3JpcHQgKi9cbl9fdnVlX2V4cG9ydHNfXyA9IHJlcXVpcmUoXCIhIUQ6XFxcXFVzZXJzXFxcXGJyaWdodGxpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc2NyaXB0LWxvYWRlciFiYWJlbC1sb2FkZXIhRDpcXFxcVXNlcnNcXFxcYnJpZ2h0bGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzZWxlY3Rvcj90eXBlPXNjcmlwdCZpbmRleD0wIS4vZnFsX3JpY2h0ZXh0LnZ1ZVwiKVxuXG4vKiB0ZW1wbGF0ZSAqL1xudmFyIF9fdnVlX3RlbXBsYXRlX18gPSByZXF1aXJlKFwiISFEOlxcXFxVc2Vyc1xcXFxicmlnaHRsaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHRlbXBsYXRlLWNvbXBpbGVyP2lkPWRhdGEtdi04ODYzZTZkNiFEOlxcXFxVc2Vyc1xcXFxicmlnaHRsaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHNlbGVjdG9yP3R5cGU9dGVtcGxhdGUmaW5kZXg9MCEuL2ZxbF9yaWNodGV4dC52dWVcIilcbl9fdnVlX29wdGlvbnNfXyA9IF9fdnVlX2V4cG9ydHNfXyA9IF9fdnVlX2V4cG9ydHNfXyB8fCB7fVxuaWYgKFxuICB0eXBlb2YgX192dWVfZXhwb3J0c19fLmRlZmF1bHQgPT09IFwib2JqZWN0XCIgfHxcbiAgdHlwZW9mIF9fdnVlX2V4cG9ydHNfXy5kZWZhdWx0ID09PSBcImZ1bmN0aW9uXCJcbikge1xuaWYgKE9iamVjdC5rZXlzKF9fdnVlX2V4cG9ydHNfXykuc29tZShmdW5jdGlvbiAoa2V5KSB7IHJldHVybiBrZXkgIT09IFwiZGVmYXVsdFwiICYmIGtleSAhPT0gXCJfX2VzTW9kdWxlXCIgfSkpIHtjb25zb2xlLmVycm9yKFwibmFtZWQgZXhwb3J0cyBhcmUgbm90IHN1cHBvcnRlZCBpbiAqLnZ1ZSBmaWxlcy5cIil9XG5fX3Z1ZV9vcHRpb25zX18gPSBfX3Z1ZV9leHBvcnRzX18gPSBfX3Z1ZV9leHBvcnRzX18uZGVmYXVsdFxufVxuaWYgKHR5cGVvZiBfX3Z1ZV9vcHRpb25zX18gPT09IFwiZnVuY3Rpb25cIikge1xuICBfX3Z1ZV9vcHRpb25zX18gPSBfX3Z1ZV9vcHRpb25zX18ub3B0aW9uc1xufVxuX192dWVfb3B0aW9uc19fLl9fZmlsZSA9IFwiRTpcXFxcMDAwV2VleENvZGVcXFxcd2VleC1kZW1vXFxcXHNyY1xcXFxmcWxfcmljaHRleHQudnVlXCJcbl9fdnVlX29wdGlvbnNfXy5yZW5kZXIgPSBfX3Z1ZV90ZW1wbGF0ZV9fLnJlbmRlclxuX192dWVfb3B0aW9uc19fLnN0YXRpY1JlbmRlckZucyA9IF9fdnVlX3RlbXBsYXRlX18uc3RhdGljUmVuZGVyRm5zXG5fX3Z1ZV9vcHRpb25zX18uX3Njb3BlSWQgPSBcImRhdGEtdi04ODYzZTZkNlwiXG5fX3Z1ZV9vcHRpb25zX18uc3R5bGUgPSBfX3Z1ZV9vcHRpb25zX18uc3R5bGUgfHwge31cbl9fdnVlX3N0eWxlc19fLmZvckVhY2goZnVuY3Rpb24gKG1vZHVsZSkge1xuICBmb3IgKHZhciBuYW1lIGluIG1vZHVsZSkge1xuICAgIF9fdnVlX29wdGlvbnNfXy5zdHlsZVtuYW1lXSA9IG1vZHVsZVtuYW1lXVxuICB9XG59KVxuaWYgKHR5cGVvZiBfX3JlZ2lzdGVyX3N0YXRpY19zdHlsZXNfXyA9PT0gXCJmdW5jdGlvblwiKSB7XG4gIF9fcmVnaXN0ZXJfc3RhdGljX3N0eWxlc19fKF9fdnVlX29wdGlvbnNfXy5fc2NvcGVJZCwgX192dWVfc3R5bGVzX18pXG59XG5cbm1vZHVsZS5leHBvcnRzID0gX192dWVfZXhwb3J0c19fXG5cblxuXG4vLy8vLy8vLy8vLy8vLy8vLy9cbi8vIFdFQlBBQ0sgRk9PVEVSXG4vLyAuL2ZxbF9yaWNodGV4dC52dWVcbi8vIG1vZHVsZSBpZCA9IDFcbi8vIG1vZHVsZSBjaHVua3MgPSAwIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///1\n");

/***/ }),
/* 2 */
/***/ (function(module, exports) {

eval("module.exports = {\n  \"content-text-wrap\": {\n    \"width\": \"750\",\n    \"paddingTop\": \"24\",\n    \"paddingBottom\": \"30\",\n    \"paddingLeft\": \"32\",\n    \"paddingRight\": \"32\",\n    \"position\": \"relative\"\n  },\n  \"content-text\": {\n    \"lineHeight\": \"52\",\n    \"fontFamily\": \"PingFangSC-Regular\",\n    \"fontSize\": \"28\",\n    \"color\": \"#000000\"\n  },\n  \"content-text-expansion\": {\n    \"lineHeight\": \"52\",\n    \"fontFamily\": \"PingFangSC-Medium\",\n    \"fontWeight\": \"500\",\n    \"fontSize\": \"28\",\n    \"color\": \"#95999F\"\n  },\n  \"space-block\": {\n    \"display\": \"inline-block\",\n    \"width\": \"10\",\n    \"height\": 0\n  }\n}//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9mcWxfcmljaHRleHQudnVlP2E5OWIiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiMi5qcyIsInNvdXJjZXNDb250ZW50IjpbIm1vZHVsZS5leHBvcnRzID0ge1xuICBcImNvbnRlbnQtdGV4dC13cmFwXCI6IHtcbiAgICBcIndpZHRoXCI6IFwiNzUwXCIsXG4gICAgXCJwYWRkaW5nVG9wXCI6IFwiMjRcIixcbiAgICBcInBhZGRpbmdCb3R0b21cIjogXCIzMFwiLFxuICAgIFwicGFkZGluZ0xlZnRcIjogXCIzMlwiLFxuICAgIFwicGFkZGluZ1JpZ2h0XCI6IFwiMzJcIixcbiAgICBcInBvc2l0aW9uXCI6IFwicmVsYXRpdmVcIlxuICB9LFxuICBcImNvbnRlbnQtdGV4dFwiOiB7XG4gICAgXCJsaW5lSGVpZ2h0XCI6IFwiNTJcIixcbiAgICBcImZvbnRGYW1pbHlcIjogXCJQaW5nRmFuZ1NDLVJlZ3VsYXJcIixcbiAgICBcImZvbnRTaXplXCI6IFwiMjhcIixcbiAgICBcImNvbG9yXCI6IFwiIzAwMDAwMFwiXG4gIH0sXG4gIFwiY29udGVudC10ZXh0LWV4cGFuc2lvblwiOiB7XG4gICAgXCJsaW5lSGVpZ2h0XCI6IFwiNTJcIixcbiAgICBcImZvbnRGYW1pbHlcIjogXCJQaW5nRmFuZ1NDLU1lZGl1bVwiLFxuICAgIFwiZm9udFdlaWdodFwiOiBcIjUwMFwiLFxuICAgIFwiZm9udFNpemVcIjogXCIyOFwiLFxuICAgIFwiY29sb3JcIjogXCIjOTU5OTlGXCJcbiAgfSxcbiAgXCJzcGFjZS1ibG9ja1wiOiB7XG4gICAgXCJkaXNwbGF5XCI6IFwiaW5saW5lLWJsb2NrXCIsXG4gICAgXCJ3aWR0aFwiOiBcIjEwXCIsXG4gICAgXCJoZWlnaHRcIjogMFxuICB9XG59XG5cblxuLy8vLy8vLy8vLy8vLy8vLy8vXG4vLyBXRUJQQUNLIEZPT1RFUlxuLy8gRDovVXNlcnMvYnJpZ2h0bGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zdHlsZS1sb2FkZXIuanMhRDovVXNlcnMvYnJpZ2h0bGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zdHlsZS1yZXdyaXRlci5qcz9pZD1kYXRhLXYtODg2M2U2ZDYhRDovVXNlcnMvYnJpZ2h0bGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zZWxlY3Rvci5qcz90eXBlPXN0eWxlcyZpbmRleD0wIS4vZnFsX3JpY2h0ZXh0LnZ1ZVxuLy8gbW9kdWxlIGlkID0gMlxuLy8gbW9kdWxlIGNodW5rcyA9IDAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///2\n");

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("\n\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nmodule.exports = {\n    data: function data() {\n        return {\n            isShowDetail: true,\n            richtext: '是真的香啊！！！冲鸭朋友们！\\n已经弃了我的xsmax\\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得金色也不错，实物拿到手之后发现没选错！是很淡的金色，侧面有点像香槟金，很高级的感觉。根本就不是好多人说的黄金那种颜色啊！\\n又配了一个橙色的手机壳和充电头。。\\n自己买了一个ideal的手机壳，绿色流沙这个真的太美了！！',\n            richtext1: '是真的香啊！！！冲鸭朋友们！\\n已经弃了我的xsmax\\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得金色也不错，实物拿到手之后发现没选错！是很淡的金色，侧面有点像香槟金，很高级的感觉。根本就不是好多人说的黄金那种颜色啊！\\n又配了一个橙色的手机壳和充电头。。\\n自己买了一个ideal的手机壳，绿色流沙这个真的太美了！！',\n            richtext2: '是真的香啊！！！冲鸭朋友们！\\n已经弃了我的xsmax\\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得'\n        };\n    },\n\n\n    methods: {\n        showContentDetail: function showContentDetail() {\n            if (this.isShowDetail) {\n                this.richtext = this.richtext2;\n            } else {\n                this.richtext = this.richtext1;\n            }\n\n            this.isShowDetail = !this.isShowDetail;\n        }\n    }\n};//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9mcWxfcmljaHRleHQudnVlPzNlNzIiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQWE7O0FBRWI7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsS0FBSzs7O0FBR0w7QUFDQTtBQUNBO0FBQ0E7QUFDQSxhQUFhO0FBQ2I7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQSIsImZpbGUiOiIzLmpzIiwic291cmNlc0NvbnRlbnQiOlsiJ3VzZSBzdHJpY3QnO1xuXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cblxubW9kdWxlLmV4cG9ydHMgPSB7XG4gICAgZGF0YTogZnVuY3Rpb24gZGF0YSgpIHtcbiAgICAgICAgcmV0dXJuIHtcbiAgICAgICAgICAgIGlzU2hvd0RldGFpbDogdHJ1ZSxcbiAgICAgICAgICAgIHJpY2h0ZXh0OiAn5piv55yf55qE6aaZ5ZWK77yB77yB77yB5Yay6bit5pyL5Y+L5Lus77yBXFxu5bey57uP5byD5LqG5oiR55qEeHNtYXhcXG7ku6XliY3nlKjov4fnmoTmiYDmnInnmoRpUGhvbmUg6YO95piv6ZO26Imy77yM6L+Z5qyh5pys5p2l5oOz5Lmw5rW36JOd6Imy77yM5ZCO5p2l6KeJ5b6X6YeR6Imy5Lmf5LiN6ZSZ77yM5a6e54mp5ou/5Yiw5omL5LmL5ZCO5Y+R546w5rKh6YCJ6ZSZ77yB5piv5b6I5reh55qE6YeR6Imy77yM5L6n6Z2i5pyJ54K55YOP6aaZ5qef6YeR77yM5b6I6auY57qn55qE5oSf6KeJ44CC5qC55pys5bCx5LiN5piv5aW95aSa5Lq66K+055qE6buE6YeR6YKj56eN6aKc6Imy5ZWK77yBXFxu5Y+I6YWN5LqG5LiA5Liq5qmZ6Imy55qE5omL5py65aOz5ZKM5YWF55S15aS044CC44CCXFxu6Ieq5bex5Lmw5LqG5LiA5LiqaWRlYWznmoTmiYvmnLrlo7PvvIznu7/oibLmtYHmspnov5nkuKrnnJ/nmoTlpKrnvo7kuobvvIHvvIEnLFxuICAgICAgICAgICAgcmljaHRleHQxOiAn5piv55yf55qE6aaZ5ZWK77yB77yB77yB5Yay6bit5pyL5Y+L5Lus77yBXFxu5bey57uP5byD5LqG5oiR55qEeHNtYXhcXG7ku6XliY3nlKjov4fnmoTmiYDmnInnmoRpUGhvbmUg6YO95piv6ZO26Imy77yM6L+Z5qyh5pys5p2l5oOz5Lmw5rW36JOd6Imy77yM5ZCO5p2l6KeJ5b6X6YeR6Imy5Lmf5LiN6ZSZ77yM5a6e54mp5ou/5Yiw5omL5LmL5ZCO5Y+R546w5rKh6YCJ6ZSZ77yB5piv5b6I5reh55qE6YeR6Imy77yM5L6n6Z2i5pyJ54K55YOP6aaZ5qef6YeR77yM5b6I6auY57qn55qE5oSf6KeJ44CC5qC55pys5bCx5LiN5piv5aW95aSa5Lq66K+055qE6buE6YeR6YKj56eN6aKc6Imy5ZWK77yBXFxu5Y+I6YWN5LqG5LiA5Liq5qmZ6Imy55qE5omL5py65aOz5ZKM5YWF55S15aS044CC44CCXFxu6Ieq5bex5Lmw5LqG5LiA5LiqaWRlYWznmoTmiYvmnLrlo7PvvIznu7/oibLmtYHmspnov5nkuKrnnJ/nmoTlpKrnvo7kuobvvIHvvIEnLFxuICAgICAgICAgICAgcmljaHRleHQyOiAn5piv55yf55qE6aaZ5ZWK77yB77yB77yB5Yay6bit5pyL5Y+L5Lus77yBXFxu5bey57uP5byD5LqG5oiR55qEeHNtYXhcXG7ku6XliY3nlKjov4fnmoTmiYDmnInnmoRpUGhvbmUg6YO95piv6ZO26Imy77yM6L+Z5qyh5pys5p2l5oOz5Lmw5rW36JOd6Imy77yM5ZCO5p2l6KeJ5b6XJ1xuICAgICAgICB9O1xuICAgIH0sXG5cblxuICAgIG1ldGhvZHM6IHtcbiAgICAgICAgc2hvd0NvbnRlbnREZXRhaWw6IGZ1bmN0aW9uIHNob3dDb250ZW50RGV0YWlsKCkge1xuICAgICAgICAgICAgaWYgKHRoaXMuaXNTaG93RGV0YWlsKSB7XG4gICAgICAgICAgICAgICAgdGhpcy5yaWNodGV4dCA9IHRoaXMucmljaHRleHQyO1xuICAgICAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICAgICAgICB0aGlzLnJpY2h0ZXh0ID0gdGhpcy5yaWNodGV4dDE7XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIHRoaXMuaXNTaG93RGV0YWlsID0gIXRoaXMuaXNTaG93RGV0YWlsO1xuICAgICAgICB9XG4gICAgfVxufTtcblxuXG4vLy8vLy8vLy8vLy8vLy8vLy9cbi8vIFdFQlBBQ0sgRk9PVEVSXG4vLyBEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3NjcmlwdC1sb2FkZXIuanMhRDovVXNlcnMvYnJpZ2h0bGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL19iYWJlbC1sb2FkZXJANy4xLjVAYmFiZWwtbG9hZGVyL2xpYiFEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3NlbGVjdG9yLmpzP3R5cGU9c2NyaXB0JmluZGV4PTAhLi9mcWxfcmljaHRleHQudnVlXG4vLyBtb2R1bGUgaWQgPSAzXG4vLyBtb2R1bGUgY2h1bmtzID0gMCJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///3\n");

/***/ }),
/* 4 */
/***/ (function(module, exports) {

eval("module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;\n  return _c('div', {\n    staticClass: [\"content-text-wrap\"]\n  }, [_c('richtext', {\n    ref: \"richtext\",\n    staticClass: [\"content-text\"],\n    on: {\n      \"click\": _vm.showContentDetail\n    }\n  }, [_c('span', {\n    staticClass: [\"content-text\"]\n  }, [_vm._v(_vm._s(_vm.richtext))]), _c('span', [_vm._v(\"...\")]), _c('image', {\n    staticClass: [\"space-block\"]\n  }), _c('span', {\n    staticClass: [\"content-text-expansion\"]\n  }, [_vm._v(\"展开\")])])], 1)\n},staticRenderFns: []}\nmodule.exports.render._withStripped = true//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9mcWxfcmljaHRleHQudnVlPzE3YjIiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsZ0JBQWdCLG1CQUFtQixhQUFhLDBCQUEwQjtBQUMxRTtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0g7QUFDQSxHQUFHO0FBQ0g7QUFDQSxHQUFHO0FBQ0g7QUFDQSxHQUFHO0FBQ0gsQ0FBQztBQUNEIiwiZmlsZSI6IjQuanMiLCJzb3VyY2VzQ29udGVudCI6WyJtb2R1bGUuZXhwb3J0cz17cmVuZGVyOmZ1bmN0aW9uICgpe3ZhciBfdm09dGhpczt2YXIgX2g9X3ZtLiRjcmVhdGVFbGVtZW50O3ZhciBfYz1fdm0uX3NlbGYuX2N8fF9oO1xuICByZXR1cm4gX2MoJ2RpdicsIHtcbiAgICBzdGF0aWNDbGFzczogW1wiY29udGVudC10ZXh0LXdyYXBcIl1cbiAgfSwgW19jKCdyaWNodGV4dCcsIHtcbiAgICByZWY6IFwicmljaHRleHRcIixcbiAgICBzdGF0aWNDbGFzczogW1wiY29udGVudC10ZXh0XCJdLFxuICAgIG9uOiB7XG4gICAgICBcImNsaWNrXCI6IF92bS5zaG93Q29udGVudERldGFpbFxuICAgIH1cbiAgfSwgW19jKCdzcGFuJywge1xuICAgIHN0YXRpY0NsYXNzOiBbXCJjb250ZW50LXRleHRcIl1cbiAgfSwgW192bS5fdihfdm0uX3MoX3ZtLnJpY2h0ZXh0KSldKSwgX2MoJ3NwYW4nLCBbX3ZtLl92KFwiLi4uXCIpXSksIF9jKCdpbWFnZScsIHtcbiAgICBzdGF0aWNDbGFzczogW1wic3BhY2UtYmxvY2tcIl1cbiAgfSksIF9jKCdzcGFuJywge1xuICAgIHN0YXRpY0NsYXNzOiBbXCJjb250ZW50LXRleHQtZXhwYW5zaW9uXCJdXG4gIH0sIFtfdm0uX3YoXCLlsZXlvIBcIildKV0pXSwgMSlcbn0sc3RhdGljUmVuZGVyRm5zOiBbXX1cbm1vZHVsZS5leHBvcnRzLnJlbmRlci5fd2l0aFN0cmlwcGVkID0gdHJ1ZVxuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIEQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvdGVtcGxhdGUtY29tcGlsZXIuanM/aWQ9ZGF0YS12LTg4NjNlNmQ2IUQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc2VsZWN0b3IuanM/dHlwZT10ZW1wbGF0ZSZpbmRleD0wIS4vZnFsX3JpY2h0ZXh0LnZ1ZVxuLy8gbW9kdWxlIGlkID0gNFxuLy8gbW9kdWxlIGNodW5rcyA9IDAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///4\n");

/***/ })
/******/ ]);
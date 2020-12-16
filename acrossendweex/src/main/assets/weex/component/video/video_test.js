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
eval("\n\nvar _video_test = __webpack_require__(1);\n\nvar _video_test2 = _interopRequireDefault(_video_test);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n_video_test2.default.el = '#root';\nnew Vue(_video_test2.default);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi8udGVtcC92aWRlb190ZXN0LmpzP2QzNWMiXSwibmFtZXMiOlsiQXBwIiwiZWwiLCJWdWUiXSwibWFwcGluZ3MiOiI7O0FBQUE7Ozs7OztBQUNBQSxxQkFBSUMsRUFBSixHQUFTLE9BQVQ7QUFDQSxJQUFJQyxHQUFKLENBQVFGLG9CQUFSIiwiZmlsZSI6IjAuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgQXBwIGZyb20gJy4uXFxcXHZpZGVvX3Rlc3QudnVlJ1xuQXBwLmVsID0gJyNyb290J1xubmV3IFZ1ZShBcHApXG4gICAgXG5cblxuLy8gV0VCUEFDSyBGT09URVIgLy9cbi8vIC4vLnRlbXAvdmlkZW9fdGVzdC5qcyJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///0\n");

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

eval("var __vue_exports__, __vue_options__\nvar __vue_styles__ = []\n\n/* styles */\n__vue_styles__.push(__webpack_require__(2)\n)\n\n/* script */\n__vue_exports__ = __webpack_require__(3)\n\n/* template */\nvar __vue_template__ = __webpack_require__(4)\n__vue_options__ = __vue_exports__ = __vue_exports__ || {}\nif (\n  typeof __vue_exports__.default === \"object\" ||\n  typeof __vue_exports__.default === \"function\"\n) {\nif (Object.keys(__vue_exports__).some(function (key) { return key !== \"default\" && key !== \"__esModule\" })) {console.error(\"named exports are not supported in *.vue files.\")}\n__vue_options__ = __vue_exports__ = __vue_exports__.default\n}\nif (typeof __vue_options__ === \"function\") {\n  __vue_options__ = __vue_options__.options\n}\n__vue_options__.__file = \"E:\\\\000WeexCode\\\\weex-demo\\\\src\\\\video_test.vue\"\n__vue_options__.render = __vue_template__.render\n__vue_options__.staticRenderFns = __vue_template__.staticRenderFns\n__vue_options__._scopeId = \"data-v-3918bae4\"\n__vue_options__.style = __vue_options__.style || {}\n__vue_styles__.forEach(function (module) {\n  for (var name in module) {\n    __vue_options__.style[name] = module[name]\n  }\n})\nif (typeof __register_static_styles__ === \"function\") {\n  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)\n}\n\nmodule.exports = __vue_exports__\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi92aWRlb190ZXN0LnZ1ZT9mNmQyIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7O0FBRUE7QUFDQSxvQkFBb0IsbUJBQU8sQ0FBQyxDQUEwWDtBQUN0Wjs7QUFFQTtBQUNBLGtCQUFrQixtQkFBTyxDQUFDLENBQXdROztBQUVsUztBQUNBLHVCQUF1QixtQkFBTyxDQUFDLENBQW9SO0FBQ25UO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxzREFBc0QsbURBQW1ELElBQUk7QUFDN0c7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLENBQUM7QUFDRDtBQUNBO0FBQ0E7O0FBRUEiLCJmaWxlIjoiMS5qcyIsInNvdXJjZXNDb250ZW50IjpbInZhciBfX3Z1ZV9leHBvcnRzX18sIF9fdnVlX29wdGlvbnNfX1xudmFyIF9fdnVlX3N0eWxlc19fID0gW11cblxuLyogc3R5bGVzICovXG5fX3Z1ZV9zdHlsZXNfXy5wdXNoKHJlcXVpcmUoXCIhIUQ6XFxcXFVzZXJzXFxcXGJyaWdodGxpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc3R5bGUtbG9hZGVyIUQ6XFxcXFVzZXJzXFxcXGJyaWdodGxpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc3R5bGUtcmV3cml0ZXI/aWQ9ZGF0YS12LTM5MThiYWU0IUQ6XFxcXFVzZXJzXFxcXGJyaWdodGxpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc2VsZWN0b3I/dHlwZT1zdHlsZXMmaW5kZXg9MCEuL3ZpZGVvX3Rlc3QudnVlXCIpXG4pXG5cbi8qIHNjcmlwdCAqL1xuX192dWVfZXhwb3J0c19fID0gcmVxdWlyZShcIiEhRDpcXFxcVXNlcnNcXFxcYnJpZ2h0bGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzY3JpcHQtbG9hZGVyIWJhYmVsLWxvYWRlciFEOlxcXFxVc2Vyc1xcXFxicmlnaHRsaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHNlbGVjdG9yP3R5cGU9c2NyaXB0JmluZGV4PTAhLi92aWRlb190ZXN0LnZ1ZVwiKVxuXG4vKiB0ZW1wbGF0ZSAqL1xudmFyIF9fdnVlX3RlbXBsYXRlX18gPSByZXF1aXJlKFwiISFEOlxcXFxVc2Vyc1xcXFxicmlnaHRsaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHRlbXBsYXRlLWNvbXBpbGVyP2lkPWRhdGEtdi0zOTE4YmFlNCFEOlxcXFxVc2Vyc1xcXFxicmlnaHRsaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHNlbGVjdG9yP3R5cGU9dGVtcGxhdGUmaW5kZXg9MCEuL3ZpZGVvX3Rlc3QudnVlXCIpXG5fX3Z1ZV9vcHRpb25zX18gPSBfX3Z1ZV9leHBvcnRzX18gPSBfX3Z1ZV9leHBvcnRzX18gfHwge31cbmlmIChcbiAgdHlwZW9mIF9fdnVlX2V4cG9ydHNfXy5kZWZhdWx0ID09PSBcIm9iamVjdFwiIHx8XG4gIHR5cGVvZiBfX3Z1ZV9leHBvcnRzX18uZGVmYXVsdCA9PT0gXCJmdW5jdGlvblwiXG4pIHtcbmlmIChPYmplY3Qua2V5cyhfX3Z1ZV9leHBvcnRzX18pLnNvbWUoZnVuY3Rpb24gKGtleSkgeyByZXR1cm4ga2V5ICE9PSBcImRlZmF1bHRcIiAmJiBrZXkgIT09IFwiX19lc01vZHVsZVwiIH0pKSB7Y29uc29sZS5lcnJvcihcIm5hbWVkIGV4cG9ydHMgYXJlIG5vdCBzdXBwb3J0ZWQgaW4gKi52dWUgZmlsZXMuXCIpfVxuX192dWVfb3B0aW9uc19fID0gX192dWVfZXhwb3J0c19fID0gX192dWVfZXhwb3J0c19fLmRlZmF1bHRcbn1cbmlmICh0eXBlb2YgX192dWVfb3B0aW9uc19fID09PSBcImZ1bmN0aW9uXCIpIHtcbiAgX192dWVfb3B0aW9uc19fID0gX192dWVfb3B0aW9uc19fLm9wdGlvbnNcbn1cbl9fdnVlX29wdGlvbnNfXy5fX2ZpbGUgPSBcIkU6XFxcXDAwMFdlZXhDb2RlXFxcXHdlZXgtZGVtb1xcXFxzcmNcXFxcdmlkZW9fdGVzdC52dWVcIlxuX192dWVfb3B0aW9uc19fLnJlbmRlciA9IF9fdnVlX3RlbXBsYXRlX18ucmVuZGVyXG5fX3Z1ZV9vcHRpb25zX18uc3RhdGljUmVuZGVyRm5zID0gX192dWVfdGVtcGxhdGVfXy5zdGF0aWNSZW5kZXJGbnNcbl9fdnVlX29wdGlvbnNfXy5fc2NvcGVJZCA9IFwiZGF0YS12LTM5MThiYWU0XCJcbl9fdnVlX29wdGlvbnNfXy5zdHlsZSA9IF9fdnVlX29wdGlvbnNfXy5zdHlsZSB8fCB7fVxuX192dWVfc3R5bGVzX18uZm9yRWFjaChmdW5jdGlvbiAobW9kdWxlKSB7XG4gIGZvciAodmFyIG5hbWUgaW4gbW9kdWxlKSB7XG4gICAgX192dWVfb3B0aW9uc19fLnN0eWxlW25hbWVdID0gbW9kdWxlW25hbWVdXG4gIH1cbn0pXG5pZiAodHlwZW9mIF9fcmVnaXN0ZXJfc3RhdGljX3N0eWxlc19fID09PSBcImZ1bmN0aW9uXCIpIHtcbiAgX19yZWdpc3Rlcl9zdGF0aWNfc3R5bGVzX18oX192dWVfb3B0aW9uc19fLl9zY29wZUlkLCBfX3Z1ZV9zdHlsZXNfXylcbn1cblxubW9kdWxlLmV4cG9ydHMgPSBfX3Z1ZV9leHBvcnRzX19cblxuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIC4vdmlkZW9fdGVzdC52dWVcbi8vIG1vZHVsZSBpZCA9IDFcbi8vIG1vZHVsZSBjaHVua3MgPSAwIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///1\n");

/***/ }),
/* 2 */
/***/ (function(module, exports) {

eval("module.exports = {\n  \"video\": {\n    \"width\": \"630\",\n    \"height\": \"350\",\n    \"marginTop\": \"60\",\n    \"marginLeft\": \"60\"\n  },\n  \"info\": {\n    \"marginTop\": \"40\",\n    \"fontSize\": \"40\",\n    \"textAlign\": \"center\"\n  }\n}//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi92aWRlb190ZXN0LnZ1ZT85YTIyIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiMi5qcyIsInNvdXJjZXNDb250ZW50IjpbIm1vZHVsZS5leHBvcnRzID0ge1xuICBcInZpZGVvXCI6IHtcbiAgICBcIndpZHRoXCI6IFwiNjMwXCIsXG4gICAgXCJoZWlnaHRcIjogXCIzNTBcIixcbiAgICBcIm1hcmdpblRvcFwiOiBcIjYwXCIsXG4gICAgXCJtYXJnaW5MZWZ0XCI6IFwiNjBcIlxuICB9LFxuICBcImluZm9cIjoge1xuICAgIFwibWFyZ2luVG9wXCI6IFwiNDBcIixcbiAgICBcImZvbnRTaXplXCI6IFwiNDBcIixcbiAgICBcInRleHRBbGlnblwiOiBcImNlbnRlclwiXG4gIH1cbn1cblxuXG4vLy8vLy8vLy8vLy8vLy8vLy9cbi8vIFdFQlBBQ0sgRk9PVEVSXG4vLyBEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3N0eWxlLWxvYWRlci5qcyFEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3N0eWxlLXJld3JpdGVyLmpzP2lkPWRhdGEtdi0zOTE4YmFlNCFEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3NlbGVjdG9yLmpzP3R5cGU9c3R5bGVzJmluZGV4PTAhLi92aWRlb190ZXN0LnZ1ZVxuLy8gbW9kdWxlIGlkID0gMlxuLy8gbW9kdWxlIGNodW5rcyA9IDAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///2\n");

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nexports.default = {\n  data: function data() {\n    return {\n      state: '----',\n      src: 'http://flv2.bn.netease.com/videolib3/1611/01/XGqSL5981/SD/XGqSL5981-mobile.mp4'\n    };\n  },\n\n  methods: {\n    onstart: function onstart(event) {\n      this.state = 'onstart';\n    },\n    onpause: function onpause(event) {\n      this.state = 'onpause';\n    },\n    onfinish: function onfinish(event) {\n      this.state = 'onfinish';\n    },\n    onfail: function onfail(event) {\n      this.state = 'onfinish';\n    }\n  }\n};//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi92aWRlb190ZXN0LnZ1ZT84MzMzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFhOztBQUViO0FBQ0E7QUFDQSxDQUFDO0FBQ0Q7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsR0FBRzs7QUFFSDtBQUNBO0FBQ0E7QUFDQSxLQUFLO0FBQ0w7QUFDQTtBQUNBLEtBQUs7QUFDTDtBQUNBO0FBQ0EsS0FBSztBQUNMO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiMy5qcyIsInNvdXJjZXNDb250ZW50IjpbIid1c2Ugc3RyaWN0JztcblxuT2JqZWN0LmRlZmluZVByb3BlcnR5KGV4cG9ydHMsIFwiX19lc01vZHVsZVwiLCB7XG4gIHZhbHVlOiB0cnVlXG59KTtcbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG5cbmV4cG9ydHMuZGVmYXVsdCA9IHtcbiAgZGF0YTogZnVuY3Rpb24gZGF0YSgpIHtcbiAgICByZXR1cm4ge1xuICAgICAgc3RhdGU6ICctLS0tJyxcbiAgICAgIHNyYzogJ2h0dHA6Ly9mbHYyLmJuLm5ldGVhc2UuY29tL3ZpZGVvbGliMy8xNjExLzAxL1hHcVNMNTk4MS9TRC9YR3FTTDU5ODEtbW9iaWxlLm1wNCdcbiAgICB9O1xuICB9LFxuXG4gIG1ldGhvZHM6IHtcbiAgICBvbnN0YXJ0OiBmdW5jdGlvbiBvbnN0YXJ0KGV2ZW50KSB7XG4gICAgICB0aGlzLnN0YXRlID0gJ29uc3RhcnQnO1xuICAgIH0sXG4gICAgb25wYXVzZTogZnVuY3Rpb24gb25wYXVzZShldmVudCkge1xuICAgICAgdGhpcy5zdGF0ZSA9ICdvbnBhdXNlJztcbiAgICB9LFxuICAgIG9uZmluaXNoOiBmdW5jdGlvbiBvbmZpbmlzaChldmVudCkge1xuICAgICAgdGhpcy5zdGF0ZSA9ICdvbmZpbmlzaCc7XG4gICAgfSxcbiAgICBvbmZhaWw6IGZ1bmN0aW9uIG9uZmFpbChldmVudCkge1xuICAgICAgdGhpcy5zdGF0ZSA9ICdvbmZpbmlzaCc7XG4gICAgfVxuICB9XG59O1xuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIEQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc2NyaXB0LWxvYWRlci5qcyFEOi9Vc2Vycy9icmlnaHRsaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX2JhYmVsLWxvYWRlckA3LjEuNUBiYWJlbC1sb2FkZXIvbGliIUQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc2VsZWN0b3IuanM/dHlwZT1zY3JpcHQmaW5kZXg9MCEuL3ZpZGVvX3Rlc3QudnVlXG4vLyBtb2R1bGUgaWQgPSAzXG4vLyBtb2R1bGUgY2h1bmtzID0gMCJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///3\n");

/***/ }),
/* 4 */
/***/ (function(module, exports) {

eval("module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;\n  return _c('div', [_c('video', {\n    staticClass: [\"video\"],\n    attrs: {\n      \"src\": _vm.src,\n      \"autoplay\": \"\",\n      \"controls\": \"\"\n    },\n    on: {\n      \"start\": _vm.onstart,\n      \"pause\": _vm.onpause,\n      \"finish\": _vm.onfinish,\n      \"fail\": _vm.onfail\n    }\n  }), _c('text', {\n    staticClass: [\"info\"]\n  }, [_vm._v(\"state: \" + _vm._s(_vm.state))])])\n},staticRenderFns: []}\nmodule.exports.render._withStripped = true//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi92aWRlb190ZXN0LnZ1ZT82NWY0Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLGdCQUFnQixtQkFBbUIsYUFBYSwwQkFBMEI7QUFDMUU7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0EsS0FBSztBQUNMO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBLEdBQUc7QUFDSCxDQUFDO0FBQ0QiLCJmaWxlIjoiNC5qcyIsInNvdXJjZXNDb250ZW50IjpbIm1vZHVsZS5leHBvcnRzPXtyZW5kZXI6ZnVuY3Rpb24gKCl7dmFyIF92bT10aGlzO3ZhciBfaD1fdm0uJGNyZWF0ZUVsZW1lbnQ7dmFyIF9jPV92bS5fc2VsZi5fY3x8X2g7XG4gIHJldHVybiBfYygnZGl2JywgW19jKCd2aWRlbycsIHtcbiAgICBzdGF0aWNDbGFzczogW1widmlkZW9cIl0sXG4gICAgYXR0cnM6IHtcbiAgICAgIFwic3JjXCI6IF92bS5zcmMsXG4gICAgICBcImF1dG9wbGF5XCI6IFwiXCIsXG4gICAgICBcImNvbnRyb2xzXCI6IFwiXCJcbiAgICB9LFxuICAgIG9uOiB7XG4gICAgICBcInN0YXJ0XCI6IF92bS5vbnN0YXJ0LFxuICAgICAgXCJwYXVzZVwiOiBfdm0ub25wYXVzZSxcbiAgICAgIFwiZmluaXNoXCI6IF92bS5vbmZpbmlzaCxcbiAgICAgIFwiZmFpbFwiOiBfdm0ub25mYWlsXG4gICAgfVxuICB9KSwgX2MoJ3RleHQnLCB7XG4gICAgc3RhdGljQ2xhc3M6IFtcImluZm9cIl1cbiAgfSwgW192bS5fdihcInN0YXRlOiBcIiArIF92bS5fcyhfdm0uc3RhdGUpKV0pXSlcbn0sc3RhdGljUmVuZGVyRm5zOiBbXX1cbm1vZHVsZS5leHBvcnRzLnJlbmRlci5fd2l0aFN0cmlwcGVkID0gdHJ1ZVxuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIEQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvdGVtcGxhdGUtY29tcGlsZXIuanM/aWQ9ZGF0YS12LTM5MThiYWU0IUQ6L1VzZXJzL2JyaWdodGxpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc2VsZWN0b3IuanM/dHlwZT10ZW1wbGF0ZSZpbmRleD0wIS4vdmlkZW9fdGVzdC52dWVcbi8vIG1vZHVsZSBpZCA9IDRcbi8vIG1vZHVsZSBjaHVua3MgPSAwIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///4\n");

/***/ })
/******/ ]);
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
eval("\n\nvar _richtext = __webpack_require__(1);\n\nvar _richtext2 = _interopRequireDefault(_richtext);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n_richtext2.default.el = '#root';\nnew Vue(_richtext2.default);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi8udGVtcC9yaWNodGV4dDQuanM/Mjc4NyJdLCJuYW1lcyI6WyJBcHAiLCJlbCIsIlZ1ZSJdLCJtYXBwaW5ncyI6Ijs7QUFBQTs7Ozs7O0FBQ0FBLG1CQUFJQyxFQUFKLEdBQVMsT0FBVDtBQUNBLElBQUlDLEdBQUosQ0FBUUYsa0JBQVIiLCJmaWxlIjoiMC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBBcHAgZnJvbSAnLi5cXFxccmljaHRleHQ0LnZ1ZSdcbkFwcC5lbCA9ICcjcm9vdCdcbm5ldyBWdWUoQXBwKVxuICAgIFxuXG5cbi8vIFdFQlBBQ0sgRk9PVEVSIC8vXG4vLyAuLy50ZW1wL3JpY2h0ZXh0NC5qcyJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///0\n");

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

eval("var __vue_exports__, __vue_options__\nvar __vue_styles__ = []\n\n/* styles */\n__vue_styles__.push(__webpack_require__(2)\n)\n__vue_styles__.push(__webpack_require__(3)\n)\n\n/* script */\n__vue_exports__ = __webpack_require__(4)\n\n/* template */\nvar __vue_template__ = __webpack_require__(5)\n__vue_options__ = __vue_exports__ = __vue_exports__ || {}\nif (\n  typeof __vue_exports__.default === \"object\" ||\n  typeof __vue_exports__.default === \"function\"\n) {\nif (Object.keys(__vue_exports__).some(function (key) { return key !== \"default\" && key !== \"__esModule\" })) {console.error(\"named exports are not supported in *.vue files.\")}\n__vue_options__ = __vue_exports__ = __vue_exports__.default\n}\nif (typeof __vue_options__ === \"function\") {\n  __vue_options__ = __vue_options__.options\n}\n__vue_options__.__file = \"E:\\\\WeexSource\\\\weexdemo\\\\src\\\\richtext4.vue\"\n__vue_options__.render = __vue_template__.render\n__vue_options__.staticRenderFns = __vue_template__.staticRenderFns\n__vue_options__._scopeId = \"data-v-49da4a2d\"\n__vue_options__.style = __vue_options__.style || {}\n__vue_styles__.forEach(function (module) {\n  for (var name in module) {\n    __vue_options__.style[name] = module[name]\n  }\n})\nif (typeof __register_static_styles__ === \"function\") {\n  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)\n}\n\nmodule.exports = __vue_exports__\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9yaWNodGV4dDQudnVlP2IxNzQiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7QUFDQTs7QUFFQTtBQUNBLG9CQUFvQixtQkFBTyxDQUFDLENBQXlYO0FBQ3JaO0FBQ0Esb0JBQW9CLG1CQUFPLENBQUMsQ0FBeVg7QUFDclo7O0FBRUE7QUFDQSxrQkFBa0IsbUJBQU8sQ0FBQyxDQUF1UTs7QUFFalM7QUFDQSx1QkFBdUIsbUJBQU8sQ0FBQyxDQUFtUjtBQUNsVDtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0Esc0RBQXNELG1EQUFtRCxJQUFJO0FBQzdHO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxDQUFDO0FBQ0Q7QUFDQTtBQUNBOztBQUVBIiwiZmlsZSI6IjEuanMiLCJzb3VyY2VzQ29udGVudCI6WyJ2YXIgX192dWVfZXhwb3J0c19fLCBfX3Z1ZV9vcHRpb25zX19cbnZhciBfX3Z1ZV9zdHlsZXNfXyA9IFtdXG5cbi8qIHN0eWxlcyAqL1xuX192dWVfc3R5bGVzX18ucHVzaChyZXF1aXJlKFwiISFDOlxcXFxVc2Vyc1xcXFxCcmlnaHRMaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHN0eWxlLWxvYWRlciFDOlxcXFxVc2Vyc1xcXFxCcmlnaHRMaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHN0eWxlLXJld3JpdGVyP2lkPWRhdGEtdi00OWRhNGEyZCFDOlxcXFxVc2Vyc1xcXFxCcmlnaHRMaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHNlbGVjdG9yP3R5cGU9c3R5bGVzJmluZGV4PTAhLi9yaWNodGV4dDQudnVlXCIpXG4pXG5fX3Z1ZV9zdHlsZXNfXy5wdXNoKHJlcXVpcmUoXCIhIUM6XFxcXFVzZXJzXFxcXEJyaWdodExpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc3R5bGUtbG9hZGVyIUM6XFxcXFVzZXJzXFxcXEJyaWdodExpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc3R5bGUtcmV3cml0ZXI/aWQ9ZGF0YS12LTQ5ZGE0YTJkIUM6XFxcXFVzZXJzXFxcXEJyaWdodExpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc2VsZWN0b3I/dHlwZT1zdHlsZXMmaW5kZXg9MSEuL3JpY2h0ZXh0NC52dWVcIilcbilcblxuLyogc2NyaXB0ICovXG5fX3Z1ZV9leHBvcnRzX18gPSByZXF1aXJlKFwiISFDOlxcXFxVc2Vyc1xcXFxCcmlnaHRMaVxcXFwud3hcXFxcbW9kdWxlc1xcXFxub2RlX21vZHVsZXNcXFxcX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXJcXFxcbGliXFxcXHNjcmlwdC1sb2FkZXIhYmFiZWwtbG9hZGVyIUM6XFxcXFVzZXJzXFxcXEJyaWdodExpXFxcXC53eFxcXFxtb2R1bGVzXFxcXG5vZGVfbW9kdWxlc1xcXFxfd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlclxcXFxsaWJcXFxcc2VsZWN0b3I/dHlwZT1zY3JpcHQmaW5kZXg9MCEuL3JpY2h0ZXh0NC52dWVcIilcblxuLyogdGVtcGxhdGUgKi9cbnZhciBfX3Z1ZV90ZW1wbGF0ZV9fID0gcmVxdWlyZShcIiEhQzpcXFxcVXNlcnNcXFxcQnJpZ2h0TGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFx0ZW1wbGF0ZS1jb21waWxlcj9pZD1kYXRhLXYtNDlkYTRhMmQhQzpcXFxcVXNlcnNcXFxcQnJpZ2h0TGlcXFxcLnd4XFxcXG1vZHVsZXNcXFxcbm9kZV9tb2R1bGVzXFxcXF93ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyXFxcXGxpYlxcXFxzZWxlY3Rvcj90eXBlPXRlbXBsYXRlJmluZGV4PTAhLi9yaWNodGV4dDQudnVlXCIpXG5fX3Z1ZV9vcHRpb25zX18gPSBfX3Z1ZV9leHBvcnRzX18gPSBfX3Z1ZV9leHBvcnRzX18gfHwge31cbmlmIChcbiAgdHlwZW9mIF9fdnVlX2V4cG9ydHNfXy5kZWZhdWx0ID09PSBcIm9iamVjdFwiIHx8XG4gIHR5cGVvZiBfX3Z1ZV9leHBvcnRzX18uZGVmYXVsdCA9PT0gXCJmdW5jdGlvblwiXG4pIHtcbmlmIChPYmplY3Qua2V5cyhfX3Z1ZV9leHBvcnRzX18pLnNvbWUoZnVuY3Rpb24gKGtleSkgeyByZXR1cm4ga2V5ICE9PSBcImRlZmF1bHRcIiAmJiBrZXkgIT09IFwiX19lc01vZHVsZVwiIH0pKSB7Y29uc29sZS5lcnJvcihcIm5hbWVkIGV4cG9ydHMgYXJlIG5vdCBzdXBwb3J0ZWQgaW4gKi52dWUgZmlsZXMuXCIpfVxuX192dWVfb3B0aW9uc19fID0gX192dWVfZXhwb3J0c19fID0gX192dWVfZXhwb3J0c19fLmRlZmF1bHRcbn1cbmlmICh0eXBlb2YgX192dWVfb3B0aW9uc19fID09PSBcImZ1bmN0aW9uXCIpIHtcbiAgX192dWVfb3B0aW9uc19fID0gX192dWVfb3B0aW9uc19fLm9wdGlvbnNcbn1cbl9fdnVlX29wdGlvbnNfXy5fX2ZpbGUgPSBcIkU6XFxcXFdlZXhTb3VyY2VcXFxcd2VleGRlbW9cXFxcc3JjXFxcXHJpY2h0ZXh0NC52dWVcIlxuX192dWVfb3B0aW9uc19fLnJlbmRlciA9IF9fdnVlX3RlbXBsYXRlX18ucmVuZGVyXG5fX3Z1ZV9vcHRpb25zX18uc3RhdGljUmVuZGVyRm5zID0gX192dWVfdGVtcGxhdGVfXy5zdGF0aWNSZW5kZXJGbnNcbl9fdnVlX29wdGlvbnNfXy5fc2NvcGVJZCA9IFwiZGF0YS12LTQ5ZGE0YTJkXCJcbl9fdnVlX29wdGlvbnNfXy5zdHlsZSA9IF9fdnVlX29wdGlvbnNfXy5zdHlsZSB8fCB7fVxuX192dWVfc3R5bGVzX18uZm9yRWFjaChmdW5jdGlvbiAobW9kdWxlKSB7XG4gIGZvciAodmFyIG5hbWUgaW4gbW9kdWxlKSB7XG4gICAgX192dWVfb3B0aW9uc19fLnN0eWxlW25hbWVdID0gbW9kdWxlW25hbWVdXG4gIH1cbn0pXG5pZiAodHlwZW9mIF9fcmVnaXN0ZXJfc3RhdGljX3N0eWxlc19fID09PSBcImZ1bmN0aW9uXCIpIHtcbiAgX19yZWdpc3Rlcl9zdGF0aWNfc3R5bGVzX18oX192dWVfb3B0aW9uc19fLl9zY29wZUlkLCBfX3Z1ZV9zdHlsZXNfXylcbn1cblxubW9kdWxlLmV4cG9ydHMgPSBfX3Z1ZV9leHBvcnRzX19cblxuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIC4vcmljaHRleHQ0LnZ1ZVxuLy8gbW9kdWxlIGlkID0gMVxuLy8gbW9kdWxlIGNodW5rcyA9IDAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///1\n");

/***/ }),
/* 2 */
/***/ (function(module, exports) {

eval("module.exports = {\n  \"slide-fade-enter\": {\n    \"transform\": \"translateX(40px)\",\n    \"opacity\": 0\n  }\n}//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9yaWNodGV4dDQudnVlPzdjN2IiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBIiwiZmlsZSI6IjIuanMiLCJzb3VyY2VzQ29udGVudCI6WyJtb2R1bGUuZXhwb3J0cyA9IHtcbiAgXCJzbGlkZS1mYWRlLWVudGVyXCI6IHtcbiAgICBcInRyYW5zZm9ybVwiOiBcInRyYW5zbGF0ZVgoNDBweClcIixcbiAgICBcIm9wYWNpdHlcIjogMFxuICB9XG59XG5cblxuLy8vLy8vLy8vLy8vLy8vLy8vXG4vLyBXRUJQQUNLIEZPT1RFUlxuLy8gQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zdHlsZS1sb2FkZXIuanMhQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zdHlsZS1yZXdyaXRlci5qcz9pZD1kYXRhLXYtNDlkYTRhMmQhQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zZWxlY3Rvci5qcz90eXBlPXN0eWxlcyZpbmRleD0wIS4vcmljaHRleHQ0LnZ1ZVxuLy8gbW9kdWxlIGlkID0gMlxuLy8gbW9kdWxlIGNodW5rcyA9IDAiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///2\n");

/***/ }),
/* 3 */
/***/ (function(module, exports) {

eval("module.exports = {\n  \"logo\": {\n    \"width\": 50,\n    \"height\": 50\n  },\n  \"title\": {\n    \"fontSize\": 42,\n    \"color\": \"#FF5400\"\n  },\n  \"but\": {\n    \"width\": 250,\n    \"height\": 250\n  }\n}//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9yaWNodGV4dDQudnVlP2VmNmUiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0g7QUFDQTtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQTtBQUNBO0FBQ0EiLCJmaWxlIjoiMy5qcyIsInNvdXJjZXNDb250ZW50IjpbIm1vZHVsZS5leHBvcnRzID0ge1xuICBcImxvZ29cIjoge1xuICAgIFwid2lkdGhcIjogNTAsXG4gICAgXCJoZWlnaHRcIjogNTBcbiAgfSxcbiAgXCJ0aXRsZVwiOiB7XG4gICAgXCJmb250U2l6ZVwiOiA0MixcbiAgICBcImNvbG9yXCI6IFwiI0ZGNTQwMFwiXG4gIH0sXG4gIFwiYnV0XCI6IHtcbiAgICBcIndpZHRoXCI6IDI1MCxcbiAgICBcImhlaWdodFwiOiAyNTBcbiAgfVxufVxuXG5cbi8vLy8vLy8vLy8vLy8vLy8vL1xuLy8gV0VCUEFDSyBGT09URVJcbi8vIEM6L1VzZXJzL0JyaWdodExpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc3R5bGUtbG9hZGVyLmpzIUM6L1VzZXJzL0JyaWdodExpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc3R5bGUtcmV3cml0ZXIuanM/aWQ9ZGF0YS12LTQ5ZGE0YTJkIUM6L1VzZXJzL0JyaWdodExpLy53eC9tb2R1bGVzL25vZGVfbW9kdWxlcy9fd2VleC12dWUtbG9hZGVyQDAuNy4wQHdlZXgtdnVlLWxvYWRlci9saWIvc2VsZWN0b3IuanM/dHlwZT1zdHlsZXMmaW5kZXg9MSEuL3JpY2h0ZXh0NC52dWVcbi8vIG1vZHVsZSBpZCA9IDNcbi8vIG1vZHVsZSBjaHVua3MgPSAwIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///3\n");

/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("\n\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nvar modal = weex.requireModule('modal');\nmodule.exports = {\n  data: function data() {\n    return {\n      i: 0\n    };\n  },\n  created: function created() {\n    // setInterval(() => {\n    //   this.i ++\n\n    //       modal.toast({\n    //         message: 'i is '+ this.i,\n    //         duration: 1\n    //       });\n    // }, 1000)\n  },\n\n  methods: {\n    listener: function listener(foo) {\n      modal.toast({\n        message: 'My pseudoRef is ' + foo.pseudoRef,\n        duration: 3\n      });\n    },\n    clickbutton: function clickbutton(foo) {\n      this.i += 3;\n      this.$forceUpdate();\n      modal.toast({\n        message: 'click button' + this.i,\n        duration: 3\n      });\n    }\n  }\n};//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9yaWNodGV4dDQudnVlPzhmYTciXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQWE7O0FBRWI7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEdBQUc7QUFDSDtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0EsY0FBYztBQUNkLFFBQVE7QUFDUixHQUFHOztBQUVIO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQSxPQUFPO0FBQ1AsS0FBSztBQUNMO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLE9BQU87QUFDUDtBQUNBO0FBQ0EiLCJmaWxlIjoiNC5qcyIsInNvdXJjZXNDb250ZW50IjpbIid1c2Ugc3RyaWN0JztcblxuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuLy9cbi8vXG4vL1xuXG52YXIgbW9kYWwgPSB3ZWV4LnJlcXVpcmVNb2R1bGUoJ21vZGFsJyk7XG5tb2R1bGUuZXhwb3J0cyA9IHtcbiAgZGF0YTogZnVuY3Rpb24gZGF0YSgpIHtcbiAgICByZXR1cm4ge1xuICAgICAgaTogMFxuICAgIH07XG4gIH0sXG4gIGNyZWF0ZWQ6IGZ1bmN0aW9uIGNyZWF0ZWQoKSB7XG4gICAgLy8gc2V0SW50ZXJ2YWwoKCkgPT4ge1xuICAgIC8vICAgdGhpcy5pICsrXG5cbiAgICAvLyAgICAgICBtb2RhbC50b2FzdCh7XG4gICAgLy8gICAgICAgICBtZXNzYWdlOiAnaSBpcyAnKyB0aGlzLmksXG4gICAgLy8gICAgICAgICBkdXJhdGlvbjogMVxuICAgIC8vICAgICAgIH0pO1xuICAgIC8vIH0sIDEwMDApXG4gIH0sXG5cbiAgbWV0aG9kczoge1xuICAgIGxpc3RlbmVyOiBmdW5jdGlvbiBsaXN0ZW5lcihmb28pIHtcbiAgICAgIG1vZGFsLnRvYXN0KHtcbiAgICAgICAgbWVzc2FnZTogJ015IHBzZXVkb1JlZiBpcyAnICsgZm9vLnBzZXVkb1JlZixcbiAgICAgICAgZHVyYXRpb246IDNcbiAgICAgIH0pO1xuICAgIH0sXG4gICAgY2xpY2tidXR0b246IGZ1bmN0aW9uIGNsaWNrYnV0dG9uKGZvbykge1xuICAgICAgdGhpcy5pICs9IDM7XG4gICAgICB0aGlzLiRmb3JjZVVwZGF0ZSgpO1xuICAgICAgbW9kYWwudG9hc3Qoe1xuICAgICAgICBtZXNzYWdlOiAnY2xpY2sgYnV0dG9uJyArIHRoaXMuaSxcbiAgICAgICAgZHVyYXRpb246IDNcbiAgICAgIH0pO1xuICAgIH1cbiAgfVxufTtcblxuXG4vLy8vLy8vLy8vLy8vLy8vLy9cbi8vIFdFQlBBQ0sgRk9PVEVSXG4vLyBDOi9Vc2Vycy9CcmlnaHRMaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3NjcmlwdC1sb2FkZXIuanMhQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL19iYWJlbC1sb2FkZXJANy4xLjVAYmFiZWwtbG9hZGVyL2xpYiFDOi9Vc2Vycy9CcmlnaHRMaS8ud3gvbW9kdWxlcy9ub2RlX21vZHVsZXMvX3dlZXgtdnVlLWxvYWRlckAwLjcuMEB3ZWV4LXZ1ZS1sb2FkZXIvbGliL3NlbGVjdG9yLmpzP3R5cGU9c2NyaXB0JmluZGV4PTAhLi9yaWNodGV4dDQudnVlXG4vLyBtb2R1bGUgaWQgPSA0XG4vLyBtb2R1bGUgY2h1bmtzID0gMCJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///4\n");

/***/ }),
/* 5 */
/***/ (function(module, exports) {

eval("module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;\n  return _c('div', [_c('richtext', {\n    key: _vm.i\n  }, [_vm._v(\"\\n        -\" + _vm._s(_vm.i) + \"-\\n    \")]), _c('text', {\n    staticStyle: {\n      width: \"250\",\n      height: \"250\"\n    },\n    on: {\n      \"click\": _vm.clickbutton\n    }\n  }, [_vm._v(\"\\n       按钮\\n    \")])], 1)\n},staticRenderFns: []}\nmodule.exports.render._withStripped = true//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9yaWNodGV4dDQudnVlPzRjMDMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsZ0JBQWdCLG1CQUFtQixhQUFhLDBCQUEwQjtBQUMxRTtBQUNBO0FBQ0EsR0FBRztBQUNIO0FBQ0E7QUFDQTtBQUNBLEtBQUs7QUFDTDtBQUNBO0FBQ0E7QUFDQSxHQUFHO0FBQ0gsQ0FBQztBQUNEIiwiZmlsZSI6IjUuanMiLCJzb3VyY2VzQ29udGVudCI6WyJtb2R1bGUuZXhwb3J0cz17cmVuZGVyOmZ1bmN0aW9uICgpe3ZhciBfdm09dGhpczt2YXIgX2g9X3ZtLiRjcmVhdGVFbGVtZW50O3ZhciBfYz1fdm0uX3NlbGYuX2N8fF9oO1xuICByZXR1cm4gX2MoJ2RpdicsIFtfYygncmljaHRleHQnLCB7XG4gICAga2V5OiBfdm0uaVxuICB9LCBbX3ZtLl92KFwiXFxuICAgICAgICAtXCIgKyBfdm0uX3MoX3ZtLmkpICsgXCItXFxuICAgIFwiKV0pLCBfYygndGV4dCcsIHtcbiAgICBzdGF0aWNTdHlsZToge1xuICAgICAgd2lkdGg6IFwiMjUwXCIsXG4gICAgICBoZWlnaHQ6IFwiMjUwXCJcbiAgICB9LFxuICAgIG9uOiB7XG4gICAgICBcImNsaWNrXCI6IF92bS5jbGlja2J1dHRvblxuICAgIH1cbiAgfSwgW192bS5fdihcIlxcbiAgICAgICDmjInpkq5cXG4gICAgXCIpXSldLCAxKVxufSxzdGF0aWNSZW5kZXJGbnM6IFtdfVxubW9kdWxlLmV4cG9ydHMucmVuZGVyLl93aXRoU3RyaXBwZWQgPSB0cnVlXG5cblxuLy8vLy8vLy8vLy8vLy8vLy8vXG4vLyBXRUJQQUNLIEZPT1RFUlxuLy8gQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi90ZW1wbGF0ZS1jb21waWxlci5qcz9pZD1kYXRhLXYtNDlkYTRhMmQhQzovVXNlcnMvQnJpZ2h0TGkvLnd4L21vZHVsZXMvbm9kZV9tb2R1bGVzL193ZWV4LXZ1ZS1sb2FkZXJAMC43LjBAd2VleC12dWUtbG9hZGVyL2xpYi9zZWxlY3Rvci5qcz90eXBlPXRlbXBsYXRlJmluZGV4PTAhLi9yaWNodGV4dDQudnVlXG4vLyBtb2R1bGUgaWQgPSA1XG4vLyBtb2R1bGUgY2h1bmtzID0gMCJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///5\n");

/***/ })
/******/ ]);
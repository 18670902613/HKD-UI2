import Vue from 'vue';
import VueRouter from 'vue-router';
import Vue2Filters from 'vue2-filters';
import App from './app.vue'; // 路由挂载
import Routers from './router.js'; // 路由列表
import iView from 'iview';
// import '../node_modules/iview/dist/styles/iview.css'; // 使用 CSS
import './styles/style.less';
import '../my-theme/index.less';

Vue.use(VueRouter);
Vue.use(iView);
Vue.use(Vue2Filters);

// 路由配置
const RouterConfig = {
    routes: Routers
};
const router = new VueRouter(RouterConfig);

new Vue({
    el: '#app',
    router: router,
    render: h => h(App)
});
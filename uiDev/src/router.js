import App from './app.vue'

const home = r => require.ensure([], () => r(require('./pages/home/home.vue')), 'home'),
    login = r => require.ensure([], () => r(require('./pages/login/login.vue')), 'login'),
    registe = r => require.ensure([], () => r(require('./pages/registe/registe.vue')), 'registe'),
    service = r => require.ensure([], () => r(require('./pages/service/service.vue')), 'service'),
    page1 = r => require.ensure([], () => r(require('./pages/page1/page1.vue')), 'page1'),
    page2 = r => require.ensure([], () => r(require('./pages/page2/page2.vue')), 'page2'),
    page3 = r => require.ensure([], () => r(require('./pages/page3/page3.vue')), 'page3'),
    page4 = r => require.ensure([], () => r(require('./pages/page4/page4.vue')), 'page4'),
    page5 = r => require.ensure([], () => r(require('./pages/page5/page5.vue')), 'page5');

export default [{
    path: '/',
    component: App,
    children: [{       
        path: '',
        redirect: 'home'
    }, {
        name: 'home',
        path: 'home',
        component: home
    }, {
        name: 'service',
        path: 'service/:serviceID',
        component: service,
        children: [{
            name: 'page1',
            path: 'page1',
            component: page1
        }, {
            name: 'page2',
            path: 'page2',
            component: page2
        }, {
            name: 'page3',
            path: 'page3',
            component: page3
        }, {
            name: 'page4',
            path: 'page4',
            component: page4
        }, {
            name: 'page5',
            path: 'page5',
            component: page5
        }, ]
    }, {
        name: 'login',
        path: 'login',
        component: login
    }, {
        name: 'registe',
        path: 'registe',
        component: registe
    }]

}]
import { createRouter, createWebHashHistory, RouterOptions, Router, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
    // @ts-ignore
    { path: '/', name: 'Home', component: () => import('../components/home.vue') },
    // @ts-ignore
    { path: '/all', name: 'ALL', component: () => import('../components/all.vue') },
]

const options: RouterOptions = {
    history: createWebHashHistory(),
    routes,
}

const router: Router = createRouter(options)

export default router

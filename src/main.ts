import {createApp} from 'vue'
import router from './router/index'
// @ts-ignore
import App from './App.vue'
import './style.less'

createApp(App)
    .use(router)
    .mount('#app')

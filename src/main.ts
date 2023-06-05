import {createApp} from 'vue'
import router from './router/index'
// @ts-ignore
import App from './App.vue'
import './style.css'

createApp(App)
    .use(router)
    .mount('#app')

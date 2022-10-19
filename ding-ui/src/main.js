import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import $ from 'jquery'

import "@/assets/css/gloub.css"


import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import 'dayjs/locale/zh-cn';
import locale from 'element-plus/lib/locale/lang/zh-cn';

// 导入所有的el-icon图标
import * as ElIconModules from '@element-plus/icons'

const app = createApp(App)
//  统一注册el-icon图标
for(let iconName in ElIconModules){
    app.component(iconName,ElIconModules[iconName])
}
createApp(App).use(store).use(router).use($)
    .use(ElementPlus,{locale})
    .mount('#app')
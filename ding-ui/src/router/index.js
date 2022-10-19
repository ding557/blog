import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import User from '../views/User.vue'
import person from '../views/Person.vue'
import Book from '../views/logBook/Book.vue'
import Index from '../views/Index.vue'


const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/index",
    children:[
      {
        path: 'user',
        name: 'User',
        component: () => User
      },{
        path: '/person',
        name: 'Person',
        component: person
      },{
        path: '/book',
        name: 'Book',
        component: Book
      }

    ]

  }, {
    path: '/index',
    name: 'Index',
    component: Index
  }, {
    path: '/login',
    name: 'Login',
    component: Login
  }, {
    path: '/register',
    name: 'Register',
    component: Register
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router
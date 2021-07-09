import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/citasusuario/:usu',
    name: 'Home',
    component: Home
  },
  {
    path: '/reporteGeneral',
    name: 'ReporteGeneral',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "reporteGeneral" */ '../views/ReporteGeneral.vue')
  },
  {
    path: '/reporteMensual',
    name: 'ReporteMensual',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "reporteMensual" */ '../views/ReporteMensual.vue')
  },
  {
    path: '/graficoBar',
    name: 'GraficoBar',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "graficoBar" */ '../views/GraficoBar.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

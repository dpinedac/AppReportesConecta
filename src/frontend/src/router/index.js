import { createRouter, createWebHistory } from 'vue-router'

const routes = [

  {
    path: '/error',
    name: 'Error',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "reporteGeneral" */ '../views/Error.vue')
  },
  {
    path: '/reporteCitasTel',
    name: 'ReporteCitasTel',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "reporteGeneral" */ '../views/ReporteCitasTel.vue')
  },
  {
    path: '/ReporteAnalisisCartera',
    name: 'ReporteAnalisisCartera',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "reporteMensual" */ '../views/ReporteAnalisisCartera.vue')
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

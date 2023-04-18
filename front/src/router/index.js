import Vue from 'vue'
import VueRouter from 'vue-router'
import login from "../views/login";
import DaoHang from "../views/DaoHang";
import movies from "../views/movies";
import information from "../views/information";
import VideoSubmit from "../views/VideoSubmit";
import Center from "../views/Center";
import search from "../views/search";
import userback from "../views/userBack/userback";
import user from "../views/userBack/user";
import upVideo from "../views/userBack/upVideo";
import upComment from "../views/userBack/upComment";
import adminback from "../views/back/adminback";
import adminUser from "../views/back/adminUser";
import adminVideo from "../views/back/adminVideo";
import adminComment from "../views/back/adminComment";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    components: {
      movieMain: login
    },
    meta: {
      title: '登录'
    }
  },
  {
    path: '/movies',
    name: 'movies',
    components: {
      header: DaoHang,
      movieMain: movies
    },
    meta: {
      requireAuth: true,
      title: '靓仔影视'
    }
  },
  {
    path: '/search',
    name: 'search',
    components: {
      header: DaoHang,
      movieMain: search
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/information/:id',
    name: 'information',
    components: {
      movieMain: information
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/video_submit',
    name: 'video_submit',
    components: {
      header: DaoHang,
      movieMain: VideoSubmit
    },
    meta: {
      requireAuth: true,
      title: '视频发布'
    }
  },
  {
    path: '/center',
    name: 'center',
    components: {
      movieMain: Center
    },
    meta: {
      requireAuth: true,
    }
  },
  {
    path: '/user/back',
    name: 'userback',
    components: {
      movieMain: userback
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/user/back/user',
    name: 'userbckuser',
    components: {
      movieMain: user
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/user/back/video/update',
    name: 'userbackvideo',
    components: {
      movieMain: upVideo
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/user/back/comment/update',
    name: 'userbackcomment',
    components: {
      movieMain: upComment
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/back',
    name: 'adminback',
    components: {
      movieMain: adminback
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/user',
    name: 'adminuser',
    components: {
      movieMain: adminUser
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/video',
    name: 'adminvideo',
    components: {
      movieMain: adminVideo
    },
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/comment',
    name: 'admincomment',
    components: {
      movieMain: adminComment
    },
    meta: {
      requireAuth: true
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

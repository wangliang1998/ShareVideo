<template>
  <div>
    <div class="main">
      <h1 style="margin-top: 10px">{{this.$route.query.userid}} 的视频空间</h1>
      <el-card class="box-card" style="margin-top: 20px">
        <div slot="header" class="clearfix">
          <span>个人信息</span>
        </div>
        <div class="text item">
          用户名：{{user.username}}
        </div>
        <div class="text item">
          性别：{{user.sex}}
        </div>
        <div class="text item">
          年龄：{{user.age}}
        </div>
        <div class="text item">
          地址：{{user.address}}
        </div>
        <div class="text item">
          手机：{{user.phone}}
        </div>
        <div class="text item">
          注册时间：{{user.register_time}}
        </div>
      </el-card>
      <div class="index-area clearfix" style="margin-top: 30px;">
        <ul>
          <li class="p1 m1" v-for="item in movies" v-bind:key="item">
            <a  class="link-hover" target="_blank" @click="toInformation(item.video_id)">
              <img   :alt="item.title" :src=" 'http://localhost:8888' + item.picture" class="lazy" style="display: inline;">
              <span class="video-bg"></span>
              <span class="lzbz">
                          <p class="name" style="text-align: center;">{{item.title}}</p>
                          <p class="name" style="text-align: center;">简介：{{item.info}}</p>
                          <p class="name" style="text-align: center;" >时间：{{item.time}}</p>
              </span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Element, { Loading } from 'element-ui'
let loading
export default {
  name: 'movies',
  current_url: null,
  currentPage: 1,
  methods: {
    toInformation (id) {
      window.open('/information/' + id, '_blank')
    },
    startloading () {
      loading = Loading.service({
        lock: true,
        text: '拼命加载中...',
        background: 'rgba(0,0,0,.7)'
      })
    },
    closeloading () {
      loading.close()
    }
  },
  data () {
    return {
      count: null,
      father_url:"",
      user:null,
      movies: [{
      }
      ],
      kinds: [
        {
        }
      ],
      loading: true
    }
  },
  created: function () {
    document.title = '电影库'
    const _this = this
    this.startloading()
    this.father_url = "http://localhost:8888"
    axios.get(axios.defaults.baseURL + '/user/video/submit',{
      params:{
          username:this.$route.query.userid,
          state:1
      }
    }).then(function (resp) {
      _this.movies = resp.data.data
      _this.closeloading()
      document.title = _this.$route.query.userid + " 的视频空间"
      if(_this.movies.length===0){
        Element.Message.success('无视频信息')
      }
    })
    axios.get(axios.defaults.baseURL + '/getUser',{
      params:{
        username: this.$route.query.userid
      }
    }).then(function (resp) {
      _this.user = resp.data.data
    })
  }

}
</script>

<style scoped>
@import "../assets/css/style.css";
ul.pagination {
  display: inline-block;
  padding: 0;
  margin: 0;
}
ul.pagination li {display: inline;}

ul.pagination li a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  border-radius: 5px;
  width: 30px;
  height: 25px;
  line-height: 25px;

}
ul.pagination li a:hover:not(.active) {background-color: #ddd;}
.bb a{color:#d8d8d8;}.nav-qt li:hover a{color:#ff6600;}
.el-header, .el-footer {
  background-color: #E6E6E6;
  color: #333;
  text-align: center;
  height: 100px;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>

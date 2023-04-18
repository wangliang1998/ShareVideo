<template>
  <div>
    <div class="main">
      <ul class="title"></ul>
      <div class="sy-all mb clearfix" id="csdfsd" style="width: 98%">
        <div class="sy-nav-down clearfix">
          <div  class="sy clearfix">
            <dl class="clearfix" style="width: auto"><dt><span style="color: red">按分类</span></dt>
              <dd>
                <a rel="nofollow" @click="openKind(-1)" >全部</a>
              </dd>
              <dd v-for="kind in kinds" v-bind:key="kind">
                <a rel="nofollow" @click="openKind(kind.kind_id)" :id="'kind-'+kind.kind_id">{{kind.kind_name}}</a>
              </dd>
            </dl>
          </div>
        </div>
      </div>
      <div class="index-area clearfix">
        <ul>
          <li class="p1 m1" v-for="item in movies" v-bind:key="item" >
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
    <el-pagination
      background
      layout="prev, pager, next"
      page-size="30"
      style="text-align:center;margin-bottom: 20px;"
      :total="count"
      pager-count="5"
      :current-page="currentPage"
      @current-change="handleCurrentChange">
    </el-pagination>
  </div>
</template>

<script>
import axios from 'axios'
import { Loading } from 'element-ui'
let loading
export default {
  name: 'movies',
  current_url: null,
  currentPage: 1,
  kind:1,
  methods: {
    openKind (kind) {
      if(kind===-1){
        this.current_url = axios.defaults.baseURL + '/video/all'
        this.currentPage = 1
      }else{
        this.current_url = axios.defaults.baseURL + '/video/kind'
        this.currentPage = 1
        this.kind = kind
      }
      this.handleCurrentChange(1)
    },
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
    },
    handleCurrentChange (page) {
      const _this = this
      _this.currentPage = page
      this.startloading()
      axios.get(_this.current_url,{
        params:{
          page:page,
          kind:_this.kind
        }
      }).then(function (resp) {
        _this.movies = resp.data.data.data
        _this.count = resp.data.data.count
        _this.closeloading()
      })
    }
  },
  data () {
    return {
      count: null,
      father_url:"",
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
    document.title = '短视频库'
    const _this = this
    this.startloading()
    axios.get(axios.defaults.baseURL + '/api/getKinds').then(function (resp) {
      _this.kinds = resp.data.data
    })
    axios.get(axios.defaults.baseURL + '/video/all/?page=1').then(function (resp) {
      _this.movies = resp.data.data.data
      _this.count = resp.data.data.count
      _this.current_url = axios.defaults.baseURL + '/video/all/'
      _this.closeloading()
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

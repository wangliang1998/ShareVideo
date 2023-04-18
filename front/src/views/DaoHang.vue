<template>
  <div class="header-all" style="height: 60px">
    <div class="top clearfix">
      <ul class="top-nav">
        <li>
          <el-menu class="el-menu-demo" mode="horizontal"
                   background-color="black"
                   text-color="#fff"
                   active-text-color="#ffd04b">
            <el-menu-item index="1" @click="MovieLibrary">短视频</el-menu-item>
            <el-menu-item index="2" @click="Submit">视频发布</el-menu-item>
            <el-menu-item index="6" >
              <el-input placeholder="请输入内容" v-model="searchname" class="input-with-select">
                <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
              </el-input>
            </el-menu-item>
            <el-submenu index="8">
              <template slot="title">
                <el-avatar :size="50" :src="circleUrl" style="text-align: left;margin-left: 20px;margin-top:5px;float: left"></el-avatar>
              </template>
              <el-menu-item index="8-1" @click="userBack"><i class="el-icon-user"></i>{{username}}的个人后台管理 ></el-menu-item>
              <el-menu-item index="8-3" @click="exit"><i class="el-icon-switch-button"></i>退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Element from 'element-ui'
export default {
  name: 'DaoHang',
  data () {
    return {
      searchname: '',
      circleUrl: '',
      username:localStorage.getItem("username")
    }
  },
  methods: {
    userBack () {
      window.open('/user/back', '_blank')
    },
    search () {
      if (this.searchname === '') {
        alert('搜索内容不能为空')
        return
      }
      this.$router.push('/search?name=' + this.searchname)
      this.searchname = ''
    },
    MovieLibrary () {
      this.$router.push('/movies')
    },
    Submit () {
      this.$router.push('/video_submit')
    },
    exit () {
      this.$store.commit('REMOVE_USERNAME')
      this.$store.commit('REMOVE_ICON')
      this.$router.push('/')
    }
  },
  created () {
    this.circleUrl = axios.defaults.baseURL + localStorage.getItem("icon")
  }

}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #fff;
  margin-left: 20px;
  font-size: 15px;
}
.el-icon-arrow-down {
  font-size: 17px;
}
</style>
